package com.yedam.common;

import lombok.Data;

//전체건수를 기준으로 페이지계산하는 클래스
@Data
public class PageDTO {
	private int startPage, endPage;
	private boolean prev, next;

	private int total;
	private int curPage; // 현재페이지

	public PageDTO(int curPage, int total) {
		this.curPage = curPage;
		this.total = total;
		
		this.endPage = (int) Math.ceil((double) curPage / 10) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil((double)total / 10));
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

	@Override
	public String toString() {
		return "PageDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", curPage=" + curPage + "]";
	}
	
}
