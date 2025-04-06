package com.chaves.webap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import com.chaves.webap.conexaodb.conexaodb;

public class LoginServlet extends HttpServlet {

    private Connection con;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar se a requisição é POST
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            // Captura os parâmetros do formulário
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Verificar se os parâmetros username e password não estão vazios
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                System.out.println("Campos de login não preenchidos.");
                response.sendRedirect("index.jsp?error=empty_fields");
                return;
            }

            // Debug: imprimir os valores dos parâmetros recebidos
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            // Valida as credenciais com o banco de dados
            if (validateCredentials(username, password)) {
                // Se a autenticação for bem-sucedida, armazena o nome de usuário na sessão
                request.getSession().setAttribute("username", username);  // A sessão é configurada aqui
                System.out.println("Login bem-sucedido para o usuário: " + username);
                // Redireciona para a página principal ou dashboard
                response.sendRedirect("home.jsp"); // Página de sucesso (home.jsp)
            } else {
                // Se a autenticação falhar, redireciona para o login com erro
                System.out.println("Falha na autenticação para o usuário: " + username);
                response.sendRedirect("index.jsp?error=true");
            }
        } else {
            // Se a requisição não for POST, redireciona para o login ou página inicial
            System.out.println("Método inválido para login. Apenas POST é permitido.");
            response.sendRedirect("index.jsp?error=invalid_method");
        }
    }

    private boolean validateCredentials(String username, String password) {
        boolean isValid = false;
        try {
            // Conectar ao banco de dados (usando a classe ConexaoDB já existente)
            con = conexaodb.conectar(); // Conectar ao banco de dados usando a classe ConexaoDB
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");

                // Verifica se o hash da senha informada corresponde ao armazenado no banco
                isValid = PasswordUtilService.checkPassword(password, hashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }
}
