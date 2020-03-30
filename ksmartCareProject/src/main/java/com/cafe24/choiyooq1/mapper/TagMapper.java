package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Tag;

@Mapper
public interface TagMapper {
	
	//태그 리스트 불러오기
	public List<Tag> getTagList();
	

}
