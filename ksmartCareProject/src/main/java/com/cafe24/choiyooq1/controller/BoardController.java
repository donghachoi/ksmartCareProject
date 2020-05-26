package com.cafe24.choiyooq1.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@PostMapping("/board/boardInsert")
	public String boardInsert(HttpSession session, CenterBoard centerBoard, @RequestParam("file") MultipartFile file) {
		
		String id = (String)session.getAttribute("SID");
		String sname = (String)session.getAttribute("SMANAGERNAME");
		centerBoard.setBoardUser(sname);
		centerBoard.setBoardUserId(id);
		boardService.boardInsert(centerBoard, file);
		
		return "redirect:/board/boardList";
	}
	
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
	
	@GetMapping("/board/boardDetail")
	public String boardDetail(@RequestParam(value="boardNo") String boardNo, Model model, HttpSession session) {
		CenterBoard centerBoard = boardService.boardDetail(boardNo);
		String sid = (String)session.getAttribute("SID");
		model.addAttribute("boardDetail", centerBoard);
		model.addAttribute("sId", sid);
		return "/board/boardDetail";
	}
	
	
	@GetMapping("/board/boardSerch")
	public String boardSerch(@RequestParam(value="sk") String sk, @RequestParam(value="sv") String sv, Model model) {
		List<CenterBoard> list = boardService.boardSerch(sk, sv);
		model.addAttribute("boardList", list);
		return "/board/boardList";
	}
	
	
	@GetMapping("/files/{boardFile:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable("boardFile") String boardFile) {
		//파일 다운로드
		Resource file = boardService.loadAsResource(boardFile);
		System.out.println("파일이름을 알려줘"+ file.getFilename());
		ResponseEntity<Resource>  re = ResponseEntity.ok().header(
				HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\""
		).body(file);
		return re;
	}
	
	@GetMapping("/board/boardUpdateForm")
	public String boardUpdateForm(@RequestParam(value="boardNo") String boardNo, Model model) {
		System.out.println("게시판 번호를 보여줘"+ boardNo);
		CenterBoard centerBoard = boardService.boardDetail(boardNo);
		model.addAttribute("boardDetail", centerBoard);
		return "/board/boardUpdate";
	}
	
	@PostMapping("/board/boardUpdate")
	public String boardUpdate(CenterBoard centerBoard , @RequestParam("file") MultipartFile file, HttpSession session) {
		System.out.println("게시판 번호 알려주세"+ centerBoard);
		int no = centerBoard.getBoardNo();
		centerBoard.setBoardNo(no);
		String SID = (String)session.getAttribute("SID");
		String SMANAGERNAME = (String)session.getAttribute("SMANAGERNAME");
		String SCENTERCODE = (String)session.getAttribute("SCENTERCODE");
		String SCENTERNAME  = (String)session.getAttribute("SCENTERNAME");
		centerBoard.setBoardUserId(SID);
		centerBoard.setBoardUser(SMANAGERNAME);
		centerBoard.setCenterCode(SCENTERCODE);
		centerBoard.setCenterName(SCENTERNAME);
		boardService.boardUpdate(centerBoard, file);
		
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/boardDelecte")
	public String boardDelecte(@RequestParam(value="boardNo") String boardNo) {
		boardService.boardDelecte(boardNo);
		
		return "redirect:/board/boardList";
	}
}
