package com.chaves.webap;



import com.chaves.webap.dao.ProdutoDAO;
import com.chaves.webap.model.Produto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/editar-produto") // URL para acessar este servlet
public class EditarProdutosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdutoDAO produtoDAO;

    public void init() {
        produtoDAO = new ProdutoDAO();
    }

    // Método GET: Busca o produto pelo ID e envia para o formulário de edição
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Produto produto = produtoDAO.buscarProdutoPorId(id);
            request.setAttribute("produto", produto);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("editar-produto.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Método POST: Atualiza o produto com os novos dados submetidos
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        double qt = Double.parseDouble(request.getParameter("qt"));

        Produto produto = new Produto(id, name, price, qt);
        boolean atualizado = produtoDAO.atualizarProduto(produto);

        if (atualizado) {
            response.sendRedirect("gestaodeprodutostotal"); // Redireciona após atualizar
        } else {
            request.setAttribute("erro", "Erro ao atualizar produto.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editar-produto.jsp");
            dispatcher.forward(request, response);
        }
    }
}
