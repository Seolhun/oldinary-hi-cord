package com.brain.home.entity.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	// 현재 페이지
	private int cPage;
	// 페이지 제한 수
	private int limit;
	// 검색 종류 설정
	private int sType;
	// 검색어
	private String sText;
	// 게시판 날짜 검색용
	private int sDate;
	// 총 게시물 갯수
	private int totalCount;
	// 총 페이지 갯수
	private int totalPage;
	// 페이지 블록 제한 수
	private int blockLimit;
	// 총 페이지 블록 수
	private int totalBlock;
	// 현재 블록 위치
	private int currentBlock;
	// 게시물 시작 블록번호
	private int blockStartNo;
	// 게시물 끝 블록번호
	private int blockEndNo;
	// 게시판 종류
	private String entityName;
	private String question;
	private int prev_cPage;
	private int next_cPage;
	
	private Long id;
	
	public Paging() {

	}

	public Paging(int currentPage, int sType, String sText, int sDate, int limit) {
		this.cPage = currentPage;
		this.sType = sType;
		this.sText = sText;
		this.limit = limit;
		this.sDate = sDate;
	}

	public Paging(int currentPage, int sType, String sText, int sDate, int limit, String entityName) {
		this.cPage = currentPage;
		this.sType = sType;
		this.sText = sText;
		this.sDate = sDate;
		this.limit = limit;
		this.entityName = entityName;
	}
}