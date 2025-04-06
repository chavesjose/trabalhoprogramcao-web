package com.chaves.webap;

import java.io.IOException;
import com.chaves.webap.dao.productDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteProduct") // Define a URL do servlet
public class DeletarProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.getWriter().println("Erro: ID do produto não fornecido.");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            productDao dao = new productDao();
            boolean deleted = dao.deleteProduct(id);  // Chama o método para deletar o produto

            if (deleted) {
                response.sendRedirect("gestaodeprodutostotal"); // Redireciona para a página principal
            } else {
                response.getWriter().println("Erro: Produto não encontrado ou não pode ser excluído.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: ID inválido.");
        }
    }
}
