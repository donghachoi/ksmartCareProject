package com.cafe24.choiyooq1.domain;

public class CenterBoard {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardUser;
	private String boardDate;
	private String boardFile;
	private String boardUserId;
	private String centerCode;
	private String centerName;
	
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getBoardUserId() {
		return boardUserId;
	}
	public void setBoardUserId(String boardUserId) {
		this.boardUserId = boardUserId;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardUser() {
		return boardUser;
	}
	public void setBoardUser(String boardUser) {
		this.boardUser = boardUser;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	
	public String getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}
	@Override
	public String toString() {
		return "CenterBoard [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardUser=" + boardUser + ", boardDate=" + boardDate + ", boardFile=" + boardFile + "]";
	}
}
