package com.cafe24.choiyooq1.controller;

import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.cafe24.choiyooq1.service.BoardtestService;

@Controller
public class BoardTestController {

	@Autowired BoardtestService boardtest;
	
	//게시판 삭제
	@GetMapping("/board/boardDelete")
	public String deleteBoard(@RequestParam(value="boardNo")int boardNo) {
		boardtest.deleteBoard(boardNo);
		return "redirect:/board/boardlist";
	}
	
	//게시판 수정으로 
	@PostMapping("/board/boardUpdate")
	public String UpdateBoard(CenterBoard centerBoard,@RequestParam("boardFile")MultipartFile boardFile) {
		boardtest.updateOneBoard(centerBoard, boardFile);
		return "redirect:/board/boardlist";
	}
	
	//게시판 수정화면으로 ㄱㄱㄱㄱ
	@GetMapping("/board/boardUpdate")
	public String UpdateBoard(@RequestParam(value = "boardNo")int boardNo
								,Model model) {
		model.addAttribute("detail", boardtest.getBoardDetail(boardNo));
		return "boardtest/boardUpdateForm";
	}
	
	//파일 다운로드 
	@GetMapping("/board/boardFile/{boardFile:.+}")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@PathVariable("boardFile") String boardFile) throws MalformedURLException {
		Resource file = boardtest.downloadFile(boardFile);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	//게시판 글쓰기 
	@PostMapping("/board/boardWirte")
	public String insertBoard(HttpSession session, CenterBoard centerBoard,@RequestParam("file")MultipartFile file) {
		String id = (String) session.getAttribute("SID");
		String name = (String) session.getAttribute("SMANAGERNAME");
		String centerCode = (String) session.getAttribute("SCENTERCODE");
		String centerName = (String) session.getAttribute("SCENTERNAME");
		centerBoard.setBoardUser(name);
		centerBoard.setBoardUserId(id);
		centerBoard.setCenterCode(centerCode);
		centerBoard.setCenterName(centerName);
		boardtest.insertBoard(centerBoard, file);
		return "redirect:/board/boardlist";
	}
	
	//게시판 글쓰기로 ㄱㄱㄱ
	@GetMapping("/board/boardWirte")
	public String wirteBoard() {
		
		return "boardtest/boardForm";
	}
	
	//게시판 상세보기
	@GetMapping("/board/boardDetail")
	public String getBoardDetail(@RequestParam(value = "boardNo")int boardNo
									,Model model
									,HttpSession session) {
		String sId = (String)session.getAttribute("SID");
		model.addAttribute("Detail", boardtest.getBoardDetail(boardNo));
		model.addAttribute("sId", sId);
		return "boardtest/boardDetail";
	}
	
	
	//게시판 리스트!
	@GetMapping("/board/boardlist")
	public String getBoardList(@RequestParam(value ="currentPage",defaultValue = "1") int currentPage
								,Model model
								,HttpSession session) {
		String centerCode = (String) session.getAttribute("SCENTERCODE");
		Map<String,Object> map = boardtest.getBoardList(currentPage, centerCode);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPageNum", map.get("lastPageNum"));
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("startPage", map.get("startPage"));
		model.addAttribute("lastPage", map.get("lastPage"));
		return "boardtest/boardList";
	}
}