package com.biz.dbms.exec;

import com.biz.dbms.service.BBsServiceV1;

public class BBsEx_02 {

	public static void main(String[] args) {

		BBsServiceV1 bbs = new BBsServiceV1();
		
		// 키보드를 사용해서 게시판에 글쓰기를 구현.
		bbs.writeBBS();
		
		// 입력된 게시판 보기
		
	}

}
