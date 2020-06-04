package com.cafe24.choiyooq1.service;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.choiyooq1.domain.CenterBoard;
import com.cafe24.choiyooq1.mapper.BoardMapper;

@Service
@Transactional
public class BoardtestService {

	@Value("${service.file.uploadurl}")
	private String fileUploadPath;
	
	
	@Autowired BoardMapper boardTestMapper;
	
	//Path객체 받기
	private Path getPath() {
		return Paths.get(fileUploadPath);
	}
	
	
	
	//게시판 삭제
	public void deleteBoard(int boardNo) {
		String fileName = boardTestMapper.getOneBoardList(boardNo).getBoardFile();
		System.out.println(fileName);
		Path file = getPath().resolve(fileName);
		System.out.println(file.toUri());
		URI fileURL = file.toUri();
		File f = new File(fileURL);
		if (f.exists()) f.delete();
		boardTestMapper.deleteBoard(boardNo);
	}
	
	
	//파일 다운로드
	public Resource downloadFile(String boardFile) throws MalformedURLException {
		Path file = getPath().resolve(boardFile);
		Resource resource = new UrlResource(file.toUri());
		if(resource.exists() || resource.isReadable()) {
			return resource;
		}
		return null;
	}
	
	//수정하기
	public void updateOneBoard(CenterBoard centerBoard,MultipartFile file) {
		String fileName = file.getOriginalFilename();
		centerBoard.setBoardFile(fileName);
		System.out.println(centerBoard.toString());
		if(file.isEmpty()) {
			boardTestMapper.updateBoard(centerBoard);
		}else {
			try {						//첨부파일을 복사합니다.
				InputStream inputStream = file.getInputStream();
				Files.copy(	inputStream,
							getPath().resolve(fileName),
							StandardCopyOption.REPLACE_EXISTING);
				boardTestMapper.updateBoard(centerBoard);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	//게시판 등록
	public void insertBoard(CenterBoard centerBoard,MultipartFile file) {
		String fileName = file.getOriginalFilename();
		//파일 이름 centerBoardVO에 담습니다.
		centerBoard.setBoardFile(fileName);
		
		if(file.isEmpty()) {							//첨부 파일이없을때 게시글만 insert 합니다.
			boardTestMapper.insertBoard(centerBoard);
		}else {											//첨부파일이 있을때
			try {						
				//첨부파일을 복사합니다.
				InputStream inputStream = file.getInputStream();
				
				Files.copy(	inputStream,								//복사할 파일
							getPath().resolve(fileName),				//대상파일의 이름 
							StandardCopyOption.REPLACE_EXISTING);		//REPLACE_EXISTING 은 만약 같은 파일이있어도 바꾸는 옵션입니다.
				boardTestMapper.insertBoard(centerBoard);				//DB에 insert 합니다.
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	//게시판 디테일
	public CenterBoard getBoardDetail(int boardNo ) {
		return boardTestMapper.getOneBoardList(boardNo);	
	}
	
	//게시판 리스트랑 페이징
	public Map<String,Object> getBoardList(int currentPage ,String centerCode) {
		//객체를 모아 보낼 map선언
		Map<String,Object> map = new HashMap<String,Object>();
		//보여줄 줄 의 수
		final int LIST_ROW = 10;
		//보여줄 첫 페이지 초기화
		int startPage = 1;
		//보여줄 마지막 페이지 초기화
		int lastPage = LIST_ROW;
		
		if(currentPage > (LIST_ROW/2)) {
			startPage = currentPage -((lastPage/2)-1);
			lastPage += (startPage-1);
		}
		
		Map<String,Object> intMap = new HashMap<String,Object>();
		int startPageRow = (currentPage-1)*LIST_ROW;
		intMap.put("startPage", startPageRow);
		intMap.put("LIST_ROW", LIST_ROW);
		intMap.put("SCENTERCODE", centerCode);
		double boardCount = boardTestMapper.getBoardCount();
		int lastPageNum = (int) Math.ceil((boardCount/LIST_ROW));
		
		if(currentPage >= (lastPageNum-4)) {
			lastPage = lastPageNum;
		}
		
		//현제 페이지를 담습니다.
		map.put("currentPage", currentPage);
		//리스트 를 map에 담습니다.
		map.put("list", boardTestMapper.getBoardList(intMap));
		//시작페이지
		map.put("startPage", startPage);
		//마지막페이지
		map.put("lastPage", lastPage);
		map.put("lastPageNum", lastPageNum);
		
		//컨트롤러로 고고
		return map;
	}
}
