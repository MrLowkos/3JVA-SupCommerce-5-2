package com.supinfo.supcommerce.servlet;

import java.io.IOException;

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
 * <b>ShowProductServlet</b>
 * <p>
 * Show product registered in memory (through SupCommerce.jar) by "id" <code>GET</code> parameter use
 * contextPath/showProduct?id=X where X is a Long
 * <p>
 * 
 * @author Elka
 * @version 1.1
 * @since SupCommerce 2.1
 */
@WebServlet(name = "ShowProduct", description = "Servlet to show a registered product by id", urlPatterns = "/showProduct")
public class ShowProductServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	
	private static final String	ID_GET_PARAM		= "id";
	
	private static final String	ERROR_REQ_ATT		= "error";
	private static final String	PRODUCT_REQ_ATT		= "product";
	
	private static final String	SHOW_PRODUCT_VIEW	= "/WEB-INF/layout/showProduct.jsp";
	
	private ProductDao			productDao;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowProductServlet() {
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
	 * Handle <code>GET</code> HTTP method
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
		
		// Recover id parameter through url
		final Object id = request.getParameter(ID_GET_PARAM);
		
		// New empty product
		Product product = new Product();
		
		// Init error message
		String error = "";
		
		if (id != null && id instanceof String) {
			Long idLong = null;
			try {
				idLong = Long.parseLong((String) id);
				
			} catch (NumberFormatException e) {
				error = "\"id\" parameter should be a number !";
			}
			product = this.productDao.findProductById(idLong);
			
			if (product == null)
				error = "Not found !";
		} else {
			error = "No \"id\" parameter specified.";
		}
		
		request.setAttribute(ERROR_REQ_ATT, error);
		request.setAttribute(PRODUCT_REQ_ATT, product);
		
		request.getRequestDispatcher(SHOW_PRODUCT_VIEW).forward(request, response);
		
	}
}
