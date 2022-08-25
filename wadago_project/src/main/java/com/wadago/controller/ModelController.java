package com.wadago.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wadago.repository.ImgRepository;
import com.wadago.vo.FileVo;
import com.wadago.vo.ImgVo;

@Controller
@RequestMapping("/model")
public class ModelController {

	@Autowired
	ImgRepository ir;

	@GetMapping("/try.do")
	public String modelTry(ImgVo imgvo, Model model) {

		model.addAttribute("img", ir.findAll());
		model.addAttribute("count", ir.getImgCount());
		return "/model/try";
	}

	@PostMapping("/insertImg")
	public String insertImg(ImgVo imgVo, FileVo fileVo) {
		MultipartFile uploadfile = fileVo.getUploadfile();
		Integer count = ir.getImgCount() + 1;
		String fileName = count + ".jpg";
		if (uploadfile != null) {
			fileVo.setImg_name(fileName);
			try {
				File file = new File(
						"D:\\sping\\wadago\\wadago_project\\src\\main\\resources\\static\\img\\" + fileName);
				uploadfile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		boolean insert = false;

		try {
			ImgVo createImg = ir.save(imgVo);
			if (createImg != null) {
				insert = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (insert) {
			return "redirect:/model/test.do?fileName=" + fileName;
		} else {
			return "redirect:/model/";
		}
	}

	@GetMapping("/result/{page}")
	public String modelResult(@PathVariable int page, @RequestParam(defaultValue = "postTime") String sort,
			@RequestParam(defaultValue = "desc") String desc, Model model) {
		int size = 8;
		Pageable pageable = null;
		if (desc.equals("desc")) {
			pageable = PageRequest.of(page - 1, size, Sort.by(sort).descending());
		} else if (desc.equals("asc")) {
			pageable = PageRequest.of(page - 1, size, Sort.by(sort).ascending());
		}

		Page<ImgVo> result = ir.findByGrade(pageable, 1);

		model.addAttribute("result", result);
		return "/model/result";
	}

	@GetMapping("/introduce")
	public ModelAndView modelIntroduce(ModelAndView model) {

		model.setViewName("/model/introduce");

		return model;
	}

	@GetMapping("/thanks")
	public ModelAndView thanks(ModelAndView model) {

		model.setViewName("/model/thanks");

		return model;
	}

	@RequestMapping(value = "/test.do", method = RequestMethod.GET)
	public ModelAndView Test(@RequestParam(value = "fileName", required = false) String fileName) {
		ModelAndView mav = new ModelAndView();

		try {
			String apiURL = "http://127.0.0.1:6666/predict_price/" + fileName;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer responseStr = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				responseStr.append(inputLine);
			}
			br.close();

			JSONObject jObject = new JSONObject(responseStr.toString());
			String result = jObject.getString("result");
			String label = jObject.getString("label");

			mav.addObject("result", result);
			mav.addObject("label", label);

		} catch (Exception e) {
			System.out.println(e);
		}

		// sb.toString은 value값(여기에선 test)
		mav.addObject("count", ir.getImgCount());
		mav.addObject("fail", false);
		mav.setViewName("/model/test");
		return mav;
	}

	@PostMapping("/imgResult")
	public String imgResult(ImgVo imgVo) {
		boolean update = false;
		try {
			ImgVo imgOption = ir.save(imgVo);

			if (imgOption != null) {
				update = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (update) {
			return "redirect:/model/thanks";
		} else {
			return "redirect:/model/thanks";
		}
	}

}
