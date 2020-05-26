package com.cafe24.choiyooq1.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.choiyooq1.domain.CenterBoard;
import com.cafe24.choiyooq1.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/board/boardForm")
	public String boardForm(String centerCode) {
		return "/board/boardForm";
	}

	//게시판 등록
	@PostMapping("/board/boardInsert")
	public String boardInsert(HttpSession session, CenterBoard centerBoard, @RequestParam("file") MultipartFile file) {
		String id = (String)session.getAttribute("SID");
		centerBoard.setBoardUser(id);
		System.out.println("11111111"+ centerBoard);
		boardService.boardInsert(centerBoard, file);
		
		return "redirect:/board/boardList";
	}
	
	
	//게시판 리스트.
	@GetMapping("/board/boardList")
	public String boardList(Model model, @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		Map<String, Object> map = boardService.getBoardList(currentPage);
		model.addAttribute("boardList", map.get("list"));
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("startPageNum", map.get("startPageNum"));
		model.addAttribute("lastPageNum", map.get("lastPageNum"));
		return "/board/boardList";
	}
	
	//게시판 상세보기
	@GetMapping("/board/boardDetail")
	public String boardDetail(@RequestParam(value="boardNo") String boardNo, Model model) {
		CenterBoard centerBoard = boardService.boardDetail(boardNo);
		model.addAttribute("boardDetail", centerBoard);
		return "/board/boardDetail";
	}
	
	//게시판 검색 
	@GetMapping("/board/boardSerch")
	public String boardSerch(@RequestParam(value="sk") String sk, @RequestParam(value="sv") String sv, Model model) {
		List<CenterBoard> list = boardService.boardSerch(sk, sv);
		model.addAttribute("boardList", list);
		return "/board/boardList";
	}
}
