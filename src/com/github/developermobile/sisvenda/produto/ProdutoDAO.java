
package com.github.developermobile.sisvenda.produto;

import com.github.developermobile.sisvenda.db.AcessoDB;
import com.github.developermobile.sisvenda.fornecedor.Fornecedor;
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
public class ProdutoDAO {
    
    private PreparedStatement stmt;
    private Connection conn;
    private ResultSet rs;
    AcessoDB acessoDB = new AcessoDB();
    
    private final String CONSULTA_PRODUTO = "SELECT p.*, f.* FROM PRODUTO p JOIN FORNECEDOR f ON p.ID_FORNECEDOR = f.ID";
    private final String CONSULTA_PRODUTO_NOME = "SELECT p.*, f.* FROM PRODUTO p JOIN FORNECEDOR f ON p.ID_FORNECEDOR = f.ID WHERE PRODUTO.NOME LIKE ?";
    private final String INCLUI_PRODUTO = "INSERT INTO PRODUTO (id_fornecedor, nome, qtde_estoque, valor) VALUES (?,?,?,?)";
    private final String ALTERA_PRODUTO = "UPDATE PRODUTO SET id_fornecedor=?, nome=?, qtde_estoque=?, valor=? WHERE PRODUTO.ID=?";
    private final String EXCLUI_PRODUTO = "DELETE FROM PRODUTO WHERE PRODUTO.ID=?";
    
    public List<Produto> consultaProduto() {
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareStatement(CONSULTA_PRODUTO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = resultSetToProduto(rs);
                produtos.add(produto);
            }
            rs.close();
            return produtos;
        } catch (Exception e) {
            System.out.println("consultaProduto(): " + e.getMessage());
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
    
    public List<Produto> consultaProduto(String nome) {
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareStatement(CONSULTA_PRODUTO_NOME);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = resultSetToProduto(rs);
                produtos.add(produto);
            }
            rs.close();
            return produtos;
        } catch (Exception e) {
            System.out.println("consultaProduto(nome): "+ e.getMessage());
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
    
    public boolean incluiProduto(Produto produto) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareStatement(INCLUI_PRODUTO);
            stmt.setInt(1, produto.getFornecedor().getId());
            stmt.setString(2, produto.getNome());
            stmt.setInt(3, produto.getQtdeEstoque());
            stmt.setDouble(4, produto.getValor());
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
    
    public boolean alteraProduto(Produto produto) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareStatement(ALTERA_PRODUTO);
            stmt.setInt(1, produto.getFornecedor().getId());
            stmt.setString(2, produto.getNome());
            stmt.setInt(3, produto.getQtdeEstoque());
            stmt.setDouble(4, produto.getValor());
            stmt.setInt(5, produto.getId());
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
    
    public boolean excluiProduto(Produto produto) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(EXCLUI_PRODUTO);
            stmt.setInt(1, produto.getId());
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
    
    private Produto resultSetToProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getInt("p.id"));
        produto.setNome(rs.getString("p.nome"));
        produto.setFornecedor(resultSetToFornecedor(rs));
        produto.setQtdeEstoque(rs.getInt("p.qtde_estoque"));
        produto.setValor(rs.getDouble("p.valor"));
        return produto;
    }

    private Fornecedor resultSetToFornecedor(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(rs.getInt("f.id"));
        fornecedor.setNome(rs.getString("f.nome"));
        fornecedor.setEndereco(rs.getString("f.endereco"));
        fornecedor.setBairro(rs.getString("f.bairro"));
        fornecedor.setCidade(rs.getString("f.cidade"));
        fornecedor.setUf(rs.getString("f.uf"));
        fornecedor.setCep(rs.getString("f.cep"));
        fornecedor.setTelefone(rs.getString("f.telefone"));
        fornecedor.setEmail(rs.getString("f.email"));
        return fornecedor;
    }
    
}
