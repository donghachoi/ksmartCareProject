package com.cafe24.choiyooq1.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.CenterBoard;
import com.cafe24.choiyooq1.domain.boardFile;

@Mapper
public interface BoardMapper {
	
	public int boardInsert(CenterBoard centerBoard);
	
	public int fileInsert(boardFile file);
	
	public List<CenterBoard> getBoardList(Map<String, Integer> map);

	public int getBoardAllCount();
	
	public CenterBoard boardDetail(String boardNo);

	public List<CenterBoard> boardSerch(String sk, String sv);
}
