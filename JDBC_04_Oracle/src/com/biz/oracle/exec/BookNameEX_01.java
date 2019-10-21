package com.biz.oracle.exec;

import com.biz.oracle.service.BookServiceV1;

public class BookNameEX_01 {

	public static void main(String[] args) {

		BookServiceV1 bs = new BookServiceV1();
		
		bs.searchBookName(true);
		System.out.println("도서검색 종료");
		
	}

}
