package com.github.developermobile.sisvenda.cliente;

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
public class ClienteDAO {
    
    private PreparedStatement stmt;
    private Connection conn;
    private ResultSet rs;
    AcessoDB acessoDB = new AcessoDB();
    
    private final String CONSULTA_CLIENTE = "SELECT * FROM CLIENTE";
    private final String CONSULTA_CLIENTE_NOME = "SELECT * FROM CLIENTE WHERE CLIENTE.NOME LIKE ?";
    private final String INCLUI_CLIENTE = "INSERT INTO CLIENTE (nome, endereco, bairro, cidade, uf, cep, telefone, email) "
            + "VALUES (?,?,?,?,?,?,?,?)";
    private final String ALTERA_CLIENTE = "UPDATE CLIENTE SET nome=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE CLIENTE.ID=?";
    private final String EXCLUI_CLIENTE = "DELETE FROM CLIENTE WHERE CLIENTE.ID=?";
    
    public List<Cliente> consultaCliente() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(CONSULTA_CLIENTE);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Cliente cliente = resultToCliente(rs);
                clientes.add(cliente);
            }
            rs.close();
            return clientes;
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
    
    public List<Cliente> consultaCliente(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(CONSULTA_CLIENTE_NOME);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Cliente cliente = resultToCliente(rs);
                clientes.add(cliente);
            }
            rs.close();
            return clientes;
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
    
    public boolean incluiCliente(Cliente cliente) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(INCLUI_CLIENTE);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getBairro());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getUf());
            stmt.setString(6, cliente.getCep());
            stmt.setString(7, cliente.getTelefone());
            stmt.setString(8, cliente.getEmail());
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
    
    public boolean alteraCliente(Cliente cliente) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(ALTERA_CLIENTE);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getBairro());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getUf());
            stmt.setString(6, cliente.getCep());
            stmt.setString(7, cliente.getTelefone());
            stmt.setString(8, cliente.getEmail());
            stmt.setInt(9, cliente.getId());
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
    
    public boolean excluiCliente(Cliente cliente) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareCall(EXCLUI_CLIENTE);
            stmt.setInt(1, cliente.getId());
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
    
    private Cliente resultToCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setBairro(rs.getString("bairro"));
        cliente.setCidade(rs.getString("cidade"));
        cliente.setUf(rs.getString("uf"));
        cliente.setCep(rs.getString("cep"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }
    
}
