package com.biz.dbms.exec;

import com.biz.dbms.service.BBsServiceV2;

public class BBsEx_01 {

	public static void main(String[] args) {

		BBsServiceV2 bbs = new BBsServiceV2();
		
		// 키보드를 사용해서 게시판에 글쓰기를 구현.
		bbs.viewBBsList();
		bbs.bbsMenu();
		
		// 입력된 게시판 보기
		
	}

}
