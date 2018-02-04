package com.github.developermobile.sysvenda.fornecedor;

import com.github.developermobile.sisvenda.db.AcessoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiago
 */
public class FornecedorDAO {
    
    private PreparedStatement stmt;
    private Connection conn;
    private ResultSet rs;
    AcessoDB acessoDB = new AcessoDB();
    
    private final String CONSULTA_FORNECEDOR = "SELECT * FROM FORNECEDOR";
    private final String CONSULTA_FORNECEDOR_NOME = "SELECT * FROM FORNECEDOR WHERE FORNECEDOR.NOME LIKE ?";
    private final String INCLUI_FORNECEDOR = "INSERT INTO FORNECEDOR (nome, endereco, bairro, cidade, uf, cep, telefone, email) "
            + "VALUES (?,?,?,?,?,?,?,?)";
    private final String ALTERA_FORNECEDOR = "UPDATE FORNECEDOR SET nome=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE FORNECEDOR.ID=?";
    private final String EXCLUI_FORNECEDOR = "DELETE FROM FORNECEDOR WHERE FORNECEDOR.ID=?";
    
    public List<Fornecedor> consultaFornecedor() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(CONSULTA_FORNECEDOR);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Fornecedor fornecedor = resultToFornecedor(rs);
                fornecedores.add(fornecedor);
            }
            rs.close();
            return fornecedores;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            if (conn != null) { conn.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public List<Fornecedor> consultaFornecedor(String nome) {
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(CONSULTA_FORNECEDOR_NOME);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Fornecedor fornecedor = resultToFornecedor(rs);
                fornecedores.add(fornecedor);
            }
            rs.close();
            return fornecedores;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            if (conn != null) { conn.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public boolean incluiFornecedor(Fornecedor fornecedor) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(INCLUI_FORNECEDOR);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEndereco());
            stmt.setString(3, fornecedor.getBairro());
            stmt.setString(4, fornecedor.getCidade());
            stmt.setString(5, fornecedor.getUf());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getTelefone());
            stmt.setString(8, fornecedor.getBairro());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            if (conn != null) { conn.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
            
    }
    
    public boolean alteraFornecedor(Fornecedor fornecedor) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(ALTERA_FORNECEDOR);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEndereco());
            stmt.setString(3, fornecedor.getBairro());
            stmt.setString(4, fornecedor.getCidade());
            stmt.setString(5, fornecedor.getUf());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getTelefone());
            stmt.setString(8, fornecedor.getEmail());
            stmt.setInt(9, fornecedor.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            if (conn != null) { conn.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
            
    }
    
    public boolean excluiFornecedor(Fornecedor fornecedor) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(EXCLUI_FORNECEDOR);
            stmt.setInt(1, fornecedor.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
                if (conn != null) { conn.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private Fornecedor resultToFornecedor(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(rs.getInt("id"));
        fornecedor.setNome(rs.getString("nome"));
        fornecedor.setEndereco(rs.getString("endereco"));
        fornecedor.setBairro(rs.getString("bairro"));
        fornecedor.setCidade(rs.getString("cidade"));
        fornecedor.setUf(rs.getString("uf"));
        fornecedor.setCep(rs.getString("cep"));
        fornecedor.setTelefone(rs.getString("telefone"));
        fornecedor.setEmail(rs.getString("email"));
        return fornecedor;
    }
    
}
