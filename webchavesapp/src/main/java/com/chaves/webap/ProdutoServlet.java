package com.chaves.webap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.chaves.webap.conexaodb.conexaodb;

@WebServlet("/cadastroprodutos")
public class ProdutoServlet extends HttpServlet {
	
	conexaodb conex = new conexaodb();
    private static final long serialVersionUID = 1L;

    public ProdutoServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              String nome = request.getParameter("name");
        String preco = request.getParameter("price");
        String quantidade = request.getParameter("qt");

     
        if (nome == null || nome.trim().isEmpty() || preco == null || preco.trim().isEmpty() || quantidade == null || quantidade.trim().isEmpty()) {
            response.sendRedirect("cadastrarProduto.jsp?error=true");
            return;
        }

        // Simulação de salvamento no banco (substituir por inserção real)
        System.out.println("Produto cadastrado: Nome = " + nome + ", Preço = " + preco + ", Quantidade = " + quantidade);
        Connection con = conexaodb.conectar();
        try {
          
            String sql = "INSERT INTO product (name, price, qt) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setDouble(2, Double.parseDouble(preco));
            stmt.setDouble(3, Double.parseDouble(quantidade));
            stmt.executeUpdate();
            stmt.close();
            con.close();

            request.setAttribute("name", nome);
            request.setAttribute("price", preco);
            request.setAttribute("qt", quantidade);
            request.getRequestDispatcher("sucesso.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cadastrarProduto.jsp?error=db");
        }
        
        
        request.setAttribute("nome", nome);
        request.setAttribute("preco", preco);
        request.setAttribute("quantidade", quantidade);
        request.getRequestDispatcher("sucesso.jsp").forward(request, response);
    }
}
