package com.chaves.webap;

import java.io.IOException;

import com.chaves.webap.dao.productDao;
import com.chaves.webap.model.product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EditarProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        System.err.println("O ID selecionado é " + idParam);

        if (idParam == null || idParam.isEmpty()) {
            response.getWriter().println("Erro: ID do produto não fornecido.");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            productDao dao = new productDao();
            product produto = dao.getProduct(id);  // Buscar produto no banco

            if (produto != null) {
                request.setAttribute("produto", produto);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editarProduto.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter().println("Erro: Produto não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: ID inválido.");
        }
    }
}
