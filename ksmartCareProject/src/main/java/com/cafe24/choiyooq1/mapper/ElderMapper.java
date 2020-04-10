package com.cafe24.choiyooq1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.choiyooq1.domain.Elder;
import com.cafe24.choiyooq1.domain.ElderLevelHistory;
import com.cafe24.choiyooq1.domain.ElderRegularCheck;
import com.cafe24.choiyooq1.domain.ElderStatus;

@Mapper
public interface ElderMapper {
	
	/* 수급자 검사 삭제 */
	public void deleteRegularCheck(String elderRegularCheckCode);
	
	/* 수급자 검사 수정 */
	public void updateRegularCheck(ElderRegularCheck elderRegularCheck);
	
	/* 수급자 검사 등록 */
	public void insertRegularCheck(ElderRegularCheck elderRegularCheck);
	
	/* 수급자 검사 리스트 */
	public List<ElderRegularCheck> getOneElderRegularList(String elderId);
	
	/* 수급자 계약 수정 */
	public void updateStatus(ElderStatus elderStatus);
	
	/* 수급자 계약 삭제 */
	public void deleteElderStatus(String statusCode);
	
	/* 수급자 계약 등록 */
	public void insertElderStatus(ElderStatus elderStatus);
	
	/* 수급자 한명의 리스트 가져오기. */
	public List<ElderStatus> getOneElderStatusList(String elderId);
	
	/* 수급자 등급 및 인정기간 삭제 */
	public void deleteElderLevel(String levelCode);
	
	/* 수급자 등급 관리 수정 */
	public void updateElderLevel(ElderLevelHistory elderLevelHistory);
	
	/* 수급자 등급 관리 등록 */
	public void insertElderLevel(ElderLevelHistory elderLevelHistory);
	
	/* 수급자 등급인정 관리 리스트 */
	public List<ElderLevelHistory> getOneElderLevelHistoryList(String elderId);
	
	/* 수급자 초기 레벨 등록 */
	public void insertFirstLevel(ElderLevelHistory elderLevelHistory);

	/* 수급자 등급 맥크코드 가져오기 */
	public int getElderLevelMaxNum();
	
	/* 수급자 상태 맥스코드 가져오기 */
	public int getElderStatusMaxNum();
	
	/* 수급자 초기입력시 검사 등록 */
	public void insertFirstRegularCheck(ElderRegularCheck elderRegularCheck);
	
	/* 수급자 초기입력시 상태 등록 */
	public void insertFirstStatus(ElderStatus elderstatus);
	
	/* 수급자 체크 맥스 코드 가져오기 */
	public int getMaxNum();
	
	/* 수급자 최근 검사 일 가져오기. */
	public List<ElderRegularCheck> getLastElderRegularHistory(String elderId);
	
	/* 수급자 최초 등록일 가져오기 */
	public ElderStatus getElderFirtsStatusDate(String elderId);
	
	/* 수급자 최근 계약상태 가져오기 */
	public ElderStatus getElderLastStatus(String elderId);
	
	/* 수급자 등급인정관리 최근 리스트 */
	public ElderLevelHistory getElderLastLevelHistory(String elderId);
	
	/* 수급자 아이디 체크위한 모든 id가져오기 */
	public List<Elder> checkElderId();
	
	/* 수급자 입력메서드 */
	public void insertElder(Elder elder);
	
	/* 수급자 초기 입력때 검사 입력 */
	public void insertElderCheck(ElderRegularCheck elderRegularCheck);
	
	/* 수급자 리스트 */
	public List<Elder> getElderList();
	
	/* 수급자 한명의 리스트 */
	public Elder getOneElderList(String elderId);
	
}
