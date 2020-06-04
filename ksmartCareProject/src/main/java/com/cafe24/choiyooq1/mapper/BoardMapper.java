package com.cafe24.choiyooq1.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.CenterBoard;

@Mapper
public interface BoardMapper {
	
	//게시판 수정
	public void updateBoard(CenterBoard centerBoard);
	
	//게시판 삭제
	public void deleteBoard(int boardNo);
	
	//게시판 insert
	public void insertBoard(CenterBoard centerBoard);
	
	//하나의 게시판 디테일
	public CenterBoard getOneBoardList(int boardNo);
	
	//게시판 전체리스트 가져오기
	public List<CenterBoard> getBoardList(Map<String,Object> map);
	
	//게시판 전체 행의 갯수 
	public double getBoardCount();
}
