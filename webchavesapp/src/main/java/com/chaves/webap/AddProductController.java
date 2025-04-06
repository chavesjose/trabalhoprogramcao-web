package com.chaves.webap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.chaves.webap.dao.productDao;
import com.chaves.webap.model.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        // Verificar se o parâmetro "id" está presente
        if (idParam == null || idParam.isEmpty()) {
            response.getWriter().println("Erro: Parâmetro 'id' ausente.");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            // Aqui você pode buscar o produto com o ID
            productDao dao = new productDao();
            product p = dao.getProduct(id);

            // Se o produto foi encontrado
            if (p != null) {
                // Adicionar o produto ao carrinho ou fazer outra ação de adição
                // Por exemplo, você pode armazenar no session, em uma lista no banco de dados ou em uma variável de aplicação
                // Exemplo de adicionar a lista na session
                List<product> cart = (List<product>) request.getSession().getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<>();
                    request.getSession().setAttribute("cart", cart);
                }
                cart.add(p);

                // Redireciona para a página do carrinho ou de confirmação
                response.sendRedirect("cart.jsp");
            } else {
                response.getWriter().println("Erro: Produto não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: Parâmetro 'id' inválido.");
        }
    }
}
