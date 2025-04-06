package com.chaves.webap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chaves.webap.conexaodb.conexaodb;
import com.chaves.webap.model.Produto;
import com.chaves.webap.model.product;


public class productDao {
    
    public product getProduct(int id) {
        product p = null;
     
          Connection con = conexaodb.conectar(); 
        if (con != null) {
            try {
                String sql = "SELECT * FROM product WHERE id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    p = new product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getString("price"));
                    p.setQt(rs.getString("qt"));
                }
                
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return p;
    }
    
    
    
    
    public List<product> getAllProducts() {
        List<product> produtos = new ArrayList<>();
        Connection con = conexaodb.conectar();
        
        if (con != null) {
            try {
                String sql = "SELECT * FROM product";  // Consulta para buscar todos os produtos
                PreparedStatement stmt = con.prepareStatement(sql);
                
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    product p = new product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getString("price"));
                    p.setQt(rs.getString("qt"));
                    produtos.add(p);
                }
                
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Adicionando um log para verificar se os produtos estão sendo adicionados corretamente
        System.out.println("Número de produtos encontrados: " + produtos.size());
        
        return produtos;
    }
    
    
    
    
    
    
    
    
    
    public boolean atualizar(Produto produto) {
    	 Connection con = conexaodb.conectar();
        String sql = "UPDATE produtos SET name = ?, price = ?, qt = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, produto.getName());
            stmt.setDouble(2, produto.getPrice());
            stmt.setDouble(3, produto.getQt());
            stmt.setInt(4, produto.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
    
    public boolean deleteProduct(int id) {
   	 Connection con = conexaodb.conectar();
        String sql = "DELETE FROM product WHERE id = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) { // Usa a conexão existente
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Produto com ID " + id + " deletado com sucesso.");
                return true;
            } else {
                System.out.println("Nenhum produto encontrado com ID " + id + ".");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
        
        return false;
    }

   
    
    
}
