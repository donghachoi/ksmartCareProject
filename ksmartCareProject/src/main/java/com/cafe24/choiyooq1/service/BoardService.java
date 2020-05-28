package com.cafe24.choiyooq1.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.choiyooq1.domain.CenterBoard;
import com.cafe24.choiyooq1.mapper.BoardMapper;

@Service

public class BoardService {

	@Value("${service.file.uploadurl}")
	private String fileUploadPath;
	
	@Autowired
	private BoardMapper boardMapper;
	
	/**
	 * 게시글 등록
	 * @param filename, centerBoard
	 * @return 없음
	 */	
	public void boardInsert(CenterBoard centerBoard, MultipartFile boardfile) {
		String filename = StringUtils.cleanPath(boardfile.getOriginalFilename());
		centerBoard.setBoardFile(filename);
		
		if(boardfile.isEmpty()) {								//파일이 없으면 게시글만 insert합니다.
			boardMapper.boardInsert(centerBoard);
		}else {
			try {
				//boardfile을  복사하기위해 inputStream으로 변환합니다.
				InputStream inputStream = boardfile.getInputStream();
				
				//지정된 dic에 파일을 복사합니다.
				Files.copy(
						inputStream, 							//복사할 파일
						getPath().resolve(filename),			//대상파일의 이름입니다. 똑같은 파일 이름입니다.
						StandardCopyOption.REPLACE_EXISTING);	//3번쨰 인자는 대상 파일을 덮어쓸수 있으면 true 그렇지 않으면 false입니다.
																//REPLACE_EXISTING 은 만약 같은 파일이있어도 바꾸는 옵션입니다.
				boardMapper.boardInsert(centerBoard);			//DB에 insert합니다.
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * @param centerBoard, filename
	 * @file BoardService.java
	 * @name boardUpdate
	 * @brief 상세보기 -> 수정화면 
	 * @return
	 */
	public void boardUpdate(CenterBoard centerBoard, MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if(file.isEmpty()) {
			boardMapper.boardUpdate(centerBoard);
		}else {
			try {
				InputStream inputStream = file.getInputStream();
				
				Files.copy(inputStream, getPath().resolve(filename), StandardCopyOption.REPLACE_EXISTING);
				centerBoard.setBoardFile(filename);
				boardMapper.boardUpdate(centerBoard);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 파일 다운로드 
	 * @param filename
	 * @return
	 */
	public Resource loadAsResource(String filename) {
		try {
			
			Path file = getPath().resolve(filename); 			//파일의 이름과 경로를 file 객체에 담습니다.
			Resource resource = new UrlResource(file.toUri());	
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 업로드 폴더 없을 경우 생성
	 */
	public void init() {
		try {
			Files.createDirectories(getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 패스 객체 반환
	 * @return
	 */
	private Path getPath() {
		return Paths.get(fileUploadPath);
	}
	
	
	public Map<String, Object> getBoardList(int currentPage){
		
		//페이지 구성 할 행의 갯수
		final int ROW_PER_PAGE = 10;
		
		//보여줄 첫번째 페이지번호 초기화
		int startPageNum = 1;

		//보여줄 페이지번호의 갯수 초기화
		int lastPageNum = ROW_PER_PAGE;
		
		//6 page부터 이동
		if(currentPage > (ROW_PER_PAGE/2)) {
			startPageNum = currentPage -((lastPageNum/2)-1);
			lastPageNum += (startPageNum-1); 
		}
		
		// limit 적용될 값 startRow, 상수ROW_PER_PAGE
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//paging 알고리즘
		int startRow = (currentPage-1)*ROW_PER_PAGE;
		
		map.put("startRow", startRow);
		map.put("rowPerPage", ROW_PER_PAGE);
		
		// 전체행의 갯수를 가져오는 쿼리
		double boardCount = boardMapper.getBoardAllCount();
	
		//라스트 페이지
		int lastPage = (int)(Math.ceil(boardCount/ROW_PER_PAGE));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", boardMapper.getBoardList(map));
		resultMap.put("currentPage", currentPage);
		resultMap.put("lastPage", lastPage);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("lastPageNum", lastPageNum);
		
		return resultMap;
	}
	
	
	public CenterBoard boardDetail(String boardNo) {
		CenterBoard centerBoard = boardMapper.boardDetail(boardNo);
		return centerBoard;
	}

	
	//게시판 검색
	public List<CenterBoard> boardSerch(String sk, String sv) {
		List<CenterBoard> list = boardMapper.boardSerch(sk, sv);
		return list;
	}

	public void boardDelecte(String boardNo) {
		// TODO Auto-generated method stub
		boardMapper.boardDelecte(boardNo);
	}


}
