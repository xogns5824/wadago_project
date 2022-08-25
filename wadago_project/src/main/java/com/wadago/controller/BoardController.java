package com.wadago.controller;

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

import com.wadago.repository.BoardRepository;
import com.wadago.repository.ReplyRepository;
import com.wadago.vo.BoardVo;
import com.wadago.vo.ReplyVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardRepository br;

	@Autowired
	ReplyRepository rr;

	@GetMapping("/list/{page}")
	public String list(@PathVariable int page, @RequestParam(defaultValue = "postTime") String sort,
			@RequestParam(defaultValue = "desc") String desc, Model model) {
		int size = 8;
		Pageable pageable = null;
		if (desc.equals("desc")) {
			pageable = PageRequest.of(page - 1, size, Sort.by(sort).descending());
		} else if (desc.equals("asc")) {
			pageable = PageRequest.of(page - 1, size, Sort.by(sort).ascending());
		}

		Page<BoardVo> question = br.findAll(pageable);

		model.addAttribute("question", question);

		return "/board/list";
	}

	@GetMapping("/modify")
	public String modify(Model model, int board_num) {

		model.addAttribute("question", br.modifyQuestion(board_num));
		model.addAttribute("reply", rr.modifyReply(board_num));
		return "/board/modify";
	}

	@GetMapping("/question")
	public String boardCount(Model model) {

		model.addAttribute("count", br.getBoardCount());
		return "/board/question";
	}

	@PostMapping("/insertQuestion")
	public String insertQuestion(BoardVo boardVo, HttpSession session) {
		boolean insert = false;
		try {
			BoardVo createQuestion = br.save(boardVo);
			if (createQuestion != null) {
				insert = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (insert) {
			return "redirect:/board/list/1";
		} else {
			return "redirect:/board/question";
		}

	}

	@GetMapping("/reply")
	public String reply(Model model, int board_num) {
		model.addAttribute("count", rr.getCommentCount());
		model.addAttribute("question", br.modifyQuestion(board_num));
		model.addAttribute("num", br.modifyBoardNum(board_num));
		return "/board/reply";
	}

	@PostMapping("/insertReply")
	public String insertComment(ReplyVo replyVo, HttpSession session) {
		boolean insert = false;
		try {
			ReplyVo createReply = rr.save(replyVo);
			if (createReply != null) {
				insert = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (insert) {
			return "redirect:/board/list/1";
		} else {
			return "redirect:/board/list/1";
		}

	}

	@PostMapping("/boardDelete")
	public String boardDelete(HttpServletRequest request, int board_num) {

		br.deleteQuestion(board_num);

		return "redirect:/board/list/1";
	}

	@PostMapping("/replyDelete")
	public String replyDelete(HttpServletRequest request, int comment_num) {

		rr.deleteReply(comment_num);

		return "redirect:/board/list/1";
	}
}
