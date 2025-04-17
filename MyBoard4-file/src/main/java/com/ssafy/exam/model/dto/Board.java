package com.ssafy.exam.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	private int no;
	private String title;
	private String content;
	private int viewCnt;
	private String regDate;
	private List<MultipartFile> attach;
	private List<BoardFile> boardFiles;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public List<MultipartFile> getAttach() {
		return attach;
	}
	public void setAttach(List<MultipartFile> attach) {
		this.attach = attach;
	}
	public List<BoardFile> getBoardFiles() {
		return boardFiles;
	}
	public void setBoardFiles(List<BoardFile> boardFiles) {
		this.boardFiles = boardFiles;
	}
}
