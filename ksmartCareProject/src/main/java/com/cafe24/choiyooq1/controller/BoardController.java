package com.cafe24.choiyooq1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cafe24.choiyooq1.domain.CenterBoard;

@Controller
public class BoardController {
	
	@GetMapping("/board/boardForm")
	public String boardForm(String centerCode) {
		return "/board/boardForm";
	}

	@PostMapping("/board/boardInsert")
	public String boardInsert(CenterBoard centerBoard) {
		System.out.println("11111111"+ centerBoard);
		return "/board/boardForm";
	}
}
