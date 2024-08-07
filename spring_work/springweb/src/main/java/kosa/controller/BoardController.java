package kosa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kosa.model.Board;
import kosa.model.BoardDao;

@Controller
public class BoardController {
	
	@Autowired // dao ��ü�� ���Թ޴´�.
	private BoardDao dao;
	
	//board_insert => insert_form.jsp ���
	@GetMapping("/board_insert")
	public String board_form() {
		return "insert_form";
	}
	
	// �ߺ����� ���� ���� �ʱ� ���� redirect ����Ѵ�.
	// ���̷�Ʈ�� jsp ���� �ȵȴ�.
	// �����������ϱ�����
	// post�� �����͸� �������� Ŀ�ǵ� ��ü�� �޴´�. (form�ȿ� name�� Ŭ���� �������� ���ƾ��Ѵ�.)
	@PostMapping("/board_insert")
	public String board_insert(Board board) {
		dao.insertBoard(board);
		return "redirect:board_list";
	}
	
	@GetMapping("/board_list")
	public String board_list(Model model) {
		List<Board> list = dao.listBoard();
		model.addAttribute("list", list); // �信�� ��� ���� �ϵ����Ѵ�.
		return "list";
	}
	
//	@GetMapping("/board_detail")
//	public String board_detail(@RequestParam("seq") int seq, Model model) {
//		Board board = dao.detailBoard(seq);
//		model.addAttribute("board", board);
//		return "detail";
//	}
	
	@GetMapping("/board_detail/{seq}")
	public String board_detail(@PathVariable int seq, Model model) {
		Board board = dao.detailBoard(seq);
		model.addAttribute("board", board);
		return "detail";
	}
	
}



