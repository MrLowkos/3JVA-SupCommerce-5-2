package com.supinfo.supcommerce.servlet;

import java.io.IOException;

import javax.persistence.PersistenceException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.entity.Category;

/**
 * Servlet implementation class AddCategoryServlet Register category in database by persistence
 * 
 * @author Elka
 * @version 4.1
 * @since SupCommerce 4.1
 */
@WebServlet("/auth/addCategory")
public class AddCategoryServlet extends HttpServlet {
	private static final long	serialVersionUID			= 1L;
	
	private static final String	CATEGORY_NAME_POST_PARAM	= "category-name";
	private static final String	CATEGORY_NAME_REQ_ATT		= "newCategoryName";
	private static final String	ERROR_NAME_REQ_ATT			= "nameError";
	
	private static final String	ADD_CATEGORY_VIEW			= "/WEB-INF/layout/addCategory.jsp";
	
	private CategoryDao			categoryDao;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCategoryServlet() {
		super();
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		this.categoryDao = DaoFactory.getCategoryDao();
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
		// Forward to add category form
		request.getRequestDispatcher(ADD_CATEGORY_VIEW).forward(request, response);
	}
	
	/**
	 * Handles <code>POST</code> HTTP method Process category creation form and add it in database through JPA (Java
	 * Persistence Api)
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// Retrieve category name parameter
		final String name = request.getParameter(CATEGORY_NAME_POST_PARAM);
		
		// New category
		final Category category = new Category();
		category.setName(name);
		
		try {
			this.categoryDao.addCategory(category);
			request.setAttribute(CATEGORY_NAME_REQ_ATT, category.getName());
		} catch (PersistenceException e) {
			request.setAttribute(ERROR_NAME_REQ_ATT, "Error, you should retry.");
		}
		
		// Forward to add category form, to add a new one
		request.getRequestDispatcher(ADD_CATEGORY_VIEW).forward(request, response);
	}
	
}
