package kosa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kosa.model.Board;
import kosa.model.BoardDao;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDao dao;
	
	//board_insert => insert_form.jsp 출력
	@GetMapping("/board_insert")
	public String board_form() {
		return "insert_form";
	}
	
	// 중복으로 글을 쓰지 않기 위해 redirect 사용한다.
	// 다이렉트로 jsp 가면 안된다.
	// 데이터전송하기위해
	// post로 데이터를 받을때는 커맨드 객체로 받는다. (form안에 name과 클래스 변수명이 같아야한다.)
	@PostMapping("/board_insert")
	public String board_insert(Board board) {
		dao.insertBoard(board);
		return "redirect:board_list";
	}
	
	@GetMapping("/board_list")
	public String board_list(Model model) {
		List<Board> list = dao.listBoard();
		model.addAttribute("list", list); // 뷰에서 사용 가능 하도록한다.
		return "list";
	}
	
	@GetMapping("/board_detail")
	public String board_detail(@RequestParam("seq") int seq, Model model) {
		Board board = dao.detailBoard(seq);
		model.addAttribute("board", board);
		return "detail";
	}
	
}
