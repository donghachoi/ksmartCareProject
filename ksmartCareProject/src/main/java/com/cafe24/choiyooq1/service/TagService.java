package com.cafe24.choiyooq1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.choiyooq1.domain.Tag;
import com.cafe24.choiyooq1.mapper.TagMapper;

@Service
@Transactional
public class TagService {
	
	
	@Autowired TagMapper tagMapper;
	
	//태그 리스트 불러오기
	public List<Tag> getTagList(){
		return tagMapper.getTagList();
		
	}
}
