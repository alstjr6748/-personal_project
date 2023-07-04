package com.yedam.vo;

import lombok.Data;

@Data
public class ProductVO {
	//prod_code char(4) primary key, -- P001, P002, P003...
	//prod_name varchar2(100) not null, -- 상품이름.
	//prod_desc varchar2(500) not null, -- 상품의 설명.
	//price number not null, -- 상품의 원래가격.
	//off_price number not null, -- 할인된 가격.
	//like_it number default 3,  -- 1 ~ 5점의 평점.
	//prod_image varchar2(100)

	private String prodCode;
	private String prodName;
	private String prodDesc;
	private String price;
	private String offPrice;
	private String likeIt;
	private String prodImage;
}
