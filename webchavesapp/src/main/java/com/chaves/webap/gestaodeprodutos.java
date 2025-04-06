package com.chaves.webap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.chaves.webap.dao.productDao;
import com.chaves.webap.model.product;

/**
 * Servlet implementation class gestaodeprodutos
 */
@WebServlet("/gestaodeprodutostotal")
public class gestaodeprodutos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    List<product> produtos = null;

	    try {
	        // Obter todos os produtos através do DAO
	        productDao dao = new productDao();
	        produtos = dao.getAllProducts();  // Método que retorna todos os produtos
	        
	        // Passando a lista de produtos para o request
	        request.setAttribute("produtos", produtos);

	        // Enviar para a página JSP
	        request.getRequestDispatcher("gestaodeproduto.jsp").forward(request, response);  // A URL depende da estrutura de pastas

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().append("Erro ao recuperar produtos");
	    }
	}

}
