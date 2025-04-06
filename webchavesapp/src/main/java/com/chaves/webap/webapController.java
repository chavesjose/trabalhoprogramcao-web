package com.chaves.webap;



import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.chaves.webap.dao.productDao;
import com.chaves.webap.model.product;



public class webapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private product p;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String idParam = request.getParameter("id");

	    // Check if "id" parameter is missing or empty
	    if (idParam == null || idParam.isEmpty()) {
	        response.getWriter().println("Error: Missing 'id' parameter.");
	        return;
	    }

	    try {
	        int id = Integer.parseInt(idParam);
	        productDao dao = new productDao();
	        p = dao.getProduct(id);

	        request.setAttribute("product", p);
	        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	        rd.forward(request, response);
	    } catch (NumberFormatException e) {
	        response.getWriter().println("Error: Invalid 'id' parameter. Please provide a valid number.");
	    }
	}
	
}
