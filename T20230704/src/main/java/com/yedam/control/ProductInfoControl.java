package com.yedam.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ProductService;
import com.yedam.service.ProductServiceImpl;
import com.yedam.vo.ProductVO;

public class ProductInfoControl implements Control {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String code = req.getParameter("code");

		ProductService service = new ProductServiceImpl();

		ProductVO vo = service.getProduct(code);

		List<ProductVO> list = service.likeList();

		req.setAttribute("product", vo);
		req.setAttribute("like", list);

		return "product/prodInfo.tiles";
	}

}
