package com.wadago.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wadago.repository.ImgRepository;
import com.wadago.repository.MemberRepository;
import com.wadago.vo.ImgVo;
import com.wadago.vo.MemberVo;

@Controller
@RequestMapping("/mem")
public class MemberController {

	@Autowired
	MemberRepository mr;

	@Autowired
	ImgRepository ir;

	@GetMapping("/login")
	public ModelAndView login(ModelAndView model) {
		model.setViewName("/mem/login");

		return model;
	}

	@PostMapping("/loginCheck")
	public String loginCheck(HttpSession session, MemberVo memVo, Model model) {
		String id = memVo.getId();
		String pw = memVo.getPw();
		MemberVo memCheck = mr.selectMemCheck(id, pw);

		if (memCheck != null) {
			session.setAttribute("memVo", memCheck);
			return "redirect:/";
		} else {
			return "/mem/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {

		session.invalidate(); // 세션의 모든 속성을 삭제

		return "redirect:/";
	}

	@GetMapping("/signup")
	public ModelAndView sighnup(ModelAndView model) {
		model.setViewName("/mem/signup");
		return model;
	}

	@PostMapping("/signup")
	public String signup(MemberVo memVo, Model model) {
		boolean insert = false;
		try {
			Optional<MemberVo> memOption = mr.findById(memVo.getId());
			if (memOption.isEmpty()) {
				MemberVo insertMem = mr.save(memVo);
				if (insertMem != null) {
					insert = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (insert) {
			return "redirect:/";
		} else {
			return "redirect:/mem/signup";
		}
	}

	@GetMapping("/memberManagement/{page}")
	public String memberManagement(@PathVariable int page, Model model) {

		Page<MemberVo> pageList = mr.findAllByGrade(PageRequest.of(page - 1, 8, Sort.by(Sort.Direction.DESC, "id")), 1);
		// 불러올 페이지의 데이터 1페이지는 0부터 시작

		model.addAttribute("page", pageList);

		return "/mem/memberManagement";
	}

	@PostMapping("/memberDelete")
	public String memberUpdate(HttpServletRequest request) {

		String[] ajaxMsg = request.getParameterValues("memberIdArray");
		int size = ajaxMsg.length;

		try {
			for (int i = 0; i < size; i++) {
				mr.updateGrade(ajaxMsg[i]);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

	@GetMapping("/memberUpdate")
	public ModelAndView memberUpdateGet(MemberVo memberVo, ModelAndView model) {

		MemberVo member = mr.selectMeberId(memberVo.getId());
		model.setViewName("/mem/memberM");

		model.addObject("member", member);

		return model;
	}

	@PostMapping("/memberUpdate")
	public String memberUpdatePost(MemberVo memVo, HttpSession session) {

		boolean insert = false;

		try {
			Optional<MemberVo> memOption = mr.findById(memVo.getId()); // 기본으로 제공되는 함수
			if (!memOption.isEmpty()) {
				MemberVo insertMem = mr.save(memVo);
				if (insertMem != null) {
					insert = true;
				} else {
					session.setAttribute("msg", "회원정보 수정에 실패해였습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (insert) {
			return "redirect:/mem/memberManagement/1";
		} else {
			return "redirect:/mem/memberUpdate?id=" + memVo.getId();
		}
	}

	@GetMapping("/img/{page}")
	public String imgList(@PathVariable int page, @RequestParam(defaultValue = "postTime") String sort,
			@RequestParam(defaultValue = "desc") String desc, Model model) {
		int size = 8;
		Pageable pageable = null;
		if (desc.equals("desc")) {
			pageable = PageRequest.of(page - 1, size, Sort.by(sort).descending());
		} else if (desc.equals("asc")) {
			pageable = PageRequest.of(page - 1, size, Sort.by(sort).ascending());
		}

		Page<ImgVo> result = ir.findAll(pageable);
		Page<ImgVo> img = ir.findAll(pageable);

		model.addAttribute("result", result);
		model.addAttribute("img", img);
		return "/mem/img";
	}

	@GetMapping("/imgModify")
	public String modifyImg(Model model, int img_num) {

		model.addAttribute("img", ir.modifyImg(img_num));
		return "/mem/imgModify";
	}

	@PostMapping("/updateGrade")
	public String updateGtade(ImgVo imgVo) {
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
			return "redirect:/mem/img/1";
		} else {
			return "redirect:/mem/img/1";
		}
	}

}
