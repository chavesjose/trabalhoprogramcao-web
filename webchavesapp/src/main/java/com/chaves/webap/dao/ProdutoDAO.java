package com.chaves.webap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.chaves.webap.conexaodb.conexaodb;
import com.chaves.webap.model.Produto;

public class ProdutoDAO {

    public boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE product SET name = ?, price = ?, qt = ? WHERE id = ?";
        boolean atualizado = false;

        try (Connection con = conexaodb.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             stmt.setString(1, produto.getName());
            stmt.setDouble(2, produto.getPrice());
            stmt.setDouble(3, produto.getQt());
            stmt.setInt(4, produto.getId());

            int rowsUpdated = stmt.executeUpdate();
            atualizado = rowsUpdated > 0; // Retorna true se ao menos uma linha foi alterada
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atualizado;
    }
    
    
    
    public Produto buscarProdutoPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (Connection con = conexaodb.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getDouble("qt")
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

}
