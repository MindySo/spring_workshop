package com.ssafy.exam.model.dto;

public class BoardSearch {
	private String searchKey;
	private String searchWord;
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	@Override
	public String toString() {
		return "Search [searchKey=" + searchKey + ", searchWord=" + searchWord + "]";
	}
}
