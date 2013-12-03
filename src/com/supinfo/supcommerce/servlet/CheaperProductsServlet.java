package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Product;

/**
 * Servlet implementation class CheaperProductsServlet List all products registered lower than a max price
 * 
 * @author Elka
 * @version 4.3
 * @since SupCommerce 4.3
 */
@WebServlet(displayName = "CheaperProducts", description = "Servlet to list cheaper registered products", urlPatterns = { "/cheaperProducts" })
public class CheaperProductsServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	
	private static final float	MAX_PRICE			= 500;
	private static final String	PRODUCTS_REQ_ATT	= "products";
	
	private static final String	LIST_PRODUCT_VIEW	= "/WEB-INF/layout/listProduct.jsp";
	
	private ProductDao			productDao;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheaperProductsServlet() {
		super();
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		this.productDao = DaoFactory.getProductDao();
	}
	
	/**
	 * Handles <code>GET</code> HTTP method Forward to appropriate view
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve all cheapest product
		final List<Product> allProducts = this.productDao.getCheaperProductsThan(MAX_PRICE);
		
		// Set product list for listProduct view
		request.setAttribute(PRODUCTS_REQ_ATT, allProducts);
		
		// Forward to view
		request.getRequestDispatcher(LIST_PRODUCT_VIEW).forward(request, response);
	}
	
}
