package com.chaves.webap.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.chaves.webap.dao.productDao;
import com.chaves.webap.model.Produto;


@WebServlet("/atualizarProduto")
public class AtualizarProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private productDao produtoDAO = new productDao(); // Instância do DAO para acessar o banco de dados

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Pegando os dados do formulário
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int qt = Integer.parseInt(request.getParameter("qt"));

            // Criando o objeto Produto
            Produto produto = new Produto(id, name, price, qt);

            // Atualizando no banco de dados
            boolean atualizado = produtoDAO.atualizar(produto);

            if (atualizado) {
                response.sendRedirect("index.jsp?success=ProdutoAtualizado");
            } else {
                response.sendRedirect("editarProduto.jsp?id=" + id + "&error=ErroAoAtualizar");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("editarProduto.jsp?error=DadosInvalidos");
        }
    }
}
