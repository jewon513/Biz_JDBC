package com.biz.iolist.service.pro;

import java.util.List;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV2 extends ProductServiceV1 {

	public void searchPName() {
		System.out.print("검색할 상품명(enter:전체) >> ");
		String strName = scan.nextLine();
		
		List<ProductDTO> proList = null;
		if(strName.trim().isEmpty()){
			proList = proDao.selectAll();
		}else {
			proList = proDao.findByName(strName);
		}
		
		for (ProductDTO productDTO : proList) {
			System.out.println(productDTO);
		}
	}
	
}
