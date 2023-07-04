package com.yedam;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.control.ProductInfoControl;
import com.yedam.control.ProductListControl;

public class FrontController extends HttpServlet {
	private HashMap<String, Control> menu;

	public FrontController() {
		menu = new HashMap<>();
	}


	@Override
	public void init() throws ServletException {
		menu.put("/productList.do", new ProductListControl());
		menu.put("/productInfo.do", new ProductInfoControl());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String page = uri.substring(contextPath.length());

		Control control = menu.get(page);
		String viewPage = control.exec(req, resp);

		if (viewPage.endsWith(".jsp")) {
			viewPage = "/WEB-INF/views/" + viewPage;
		} else if (viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
			return;
		} else if (viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().print(viewPage.substring(0, viewPage.length() - 5));
			return;
		}

		RequestDispatcher rd = null;
		rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
	}
}
