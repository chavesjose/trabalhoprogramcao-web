package com.chaves.webap;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

import com.chaves.webap.conexaodb.conexaodb;

@WebServlet("/CadastrarUsuarioServlet")
public class CadastrarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String roleIdStr = request.getParameter("role"); // Pega o id do papel selecionado
        int roleId = Integer.parseInt(roleIdStr); // Converte para inteiro

        // Criação da conexão com o banco
        try (Connection con = conexaodb.conectar()) {
            // Verificar se o roleId existe na tabela roles
            String checkRoleQuery = "SELECT COUNT(*) FROM chaves.roles WHERE id = ?";
            try (PreparedStatement psCheckRole = con.prepareStatement(checkRoleQuery)) {
                psCheckRole.setInt(1, roleId);
                try (ResultSet rs = psCheckRole.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // O roleId existe, então prosseguir com a inserção do usuário

                        // Criptografar a senha usando MD5
                        String hashedPassword = PasswordUtilService.toMD5(password);

                        // Inserir o usuário na tabela users
                        String sqlUser = "INSERT INTO chaves.users (email, name, password, username) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement ps = con.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                            ps.setString(1, email);
                            ps.setString(2, name);
                            ps.setString(3, hashedPassword);  // Criptografar a senha antes de salvar
                            ps.setString(4, username);

                            int affectedRows = ps.executeUpdate();

                            if (affectedRows > 0) {
                                // Recuperar o ID do usuário recém inserido
                                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                                    if (generatedKeys.next()) {
                                        long userId = generatedKeys.getLong(1);

                                        // Inserir na tabela user_roles com o ID do usuário e o ID do papel selecionado
                                        String sqlRole = "INSERT INTO chaves.user_roles (user_id, role_id) VALUES (?, ?)";
                                        try (PreparedStatement psRole = con.prepareStatement(sqlRole)) {
                                            psRole.setLong(1, userId);
                                            psRole.setInt(2, roleId); // Usa o id do papel selecionado
                                            psRole.executeUpdate();
                                        }
                                    }
                                }
                            }

                            // Redirecionar para a página de sucesso ou login
                            response.sendRedirect("login.jsp");

                        } catch (SQLException e) {
                            e.printStackTrace();
                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao salvar o usuário.");
                        }
                    } else {
                        // O roleId não existe na tabela roles
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Papel inválido.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao verificar o papel.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro de conexão com o banco de dados.");
        }
    }
}
