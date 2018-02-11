
package com.github.developermobile.sisvenda.venda;

import com.github.developermobile.sisvenda.cliente.Cliente;
import com.github.developermobile.sisvenda.db.AcessoDB;
import com.github.developermobile.sisvenda.produto.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiago
 */
public class VendaDAO {
    
    private PreparedStatement stmt;
    private Connection conn;
    private ResultSet rs, rs2;
    AcessoDB acessoDB = new AcessoDB();
    
    private final String REGISTRA_VENDA = "INSERT INTO VENDA (id_cliente, data_venda) VALUES (?,?)";
    private final String REGISTRA_ITENS_VENDA = "INSERT INTO ITENS_VENDA (id_produto, id_venda, qtde, valor) VALUES (?,?,?,?)";
    private final String CONSULTA_ULTIMO_ID = "SELECT LAST_INSERT_ID() AS id FROM VENDA";
    private final String CONSULTA_VENDA_PERIODO = "SELECT v.*, c.* FROM VENDA v JOIN CLIENTE c ON v.ID_CLIENTE = c.ID WHERE v.DATA_VENDA BETWEEN ? AND ?";
    private final String CONSULTA_ITENS_VENDA = "SELECT i.*, p.* FROM ITENS_VENDA i JOIN PRODUTO p ON i.ID_PRODUTO = p.ID WHERE i.ID_VENDA=?";
    
    public boolean registraVenda(Venda venda) {
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareStatement(REGISTRA_VENDA);
            stmt.setInt(1, venda.getCliente().getId());
            stmt.setDate(2, venda.getDataVenda());
            stmt.executeUpdate();
            
            stmt = conn.prepareCall(CONSULTA_ULTIMO_ID);
            rs = stmt.executeQuery();
            rs.first();
            venda.setId(rs.getInt("id"));
            
            for (int i = 0; i < venda.getItensVendas().size(); i++) {
                stmt = conn.prepareStatement(REGISTRA_ITENS_VENDA);
                stmt.setInt(1, venda.getItensVendas().get(i).getProduto().getId());
                stmt.setInt(2, venda.getId());
                stmt.setInt(3, venda.getItensVendas().get(i).getQtde());
                stmt.setDouble(4, venda.getItensVendas().get(i).getValor());
                stmt.executeUpdate();
            }
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
    
    public List<Venda> consultaVendaPeriodo(Date dataInicial, Date dataFinal) {
        List<Venda> vendas = new ArrayList<>();
        try {
            conn = acessoDB.getConnection();
            stmt = conn.prepareStatement(CONSULTA_VENDA_PERIODO);
            stmt.setDate(1, dataInicial);
            stmt.setDate(2, dataFinal);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Venda venda = resultToVenda(rs);
                stmt = conn.prepareStatement(CONSULTA_ITENS_VENDA);
                stmt.setInt(1, venda.getId());
                rs2 = stmt.executeQuery();
                
                List<ItensVenda> itensVendas = new ArrayList<>();
                while (rs2.next()) {
                    ItensVenda itensVenda = resultToItensVenda(rs2);
                    itensVendas.add(itensVenda);
                }
                venda.setItensVendas(itensVendas);
                vendas.add(venda);
            }
            rs2.close();
            rs.close();
            return vendas;
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
    
    private Venda resultToVenda(ResultSet rs) throws SQLException {
        Venda venda = new Venda();
        venda.setId(rs.getInt("v.id"));
        venda.setCliente(resultCliente(rs));
        venda.setDataVenda(rs.getDate("v.data_venda"));
        return venda;
    }
    
    private Cliente resultCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("c.id"));
        cliente.setNome(rs.getString("c.nome"));
        cliente.setEndereco(rs.getString("c.endereco"));
        cliente.setBairro(rs.getString("c.bairro"));
        cliente.setCidade(rs.getString("c.cidade"));
        cliente.setUf(rs.getString("c.uf"));
        cliente.setCep(rs.getString("c.cep"));
        cliente.setTelefone(rs.getString("c.telefone"));
        cliente.setEmail(rs.getString("c.email"));
        return cliente;
    }
    
    private ItensVenda resultToItensVenda(ResultSet rs) throws SQLException {
        ItensVenda itensVenda = new ItensVenda();
        itensVenda.setId(rs.getInt("i.id"));
        itensVenda.setQtde(rs.getInt("i.qtde"));
        itensVenda.setValor(rs.getDouble("i.valor"));
        itensVenda.setProduto(resultToProduto(rs));
        return itensVenda;
    }
    
    private Produto resultToProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getInt("p.id"));
        produto.setNome(rs.getString("p.nome"));
        produto.setQtdeEstoque(rs.getInt("p.qtde_estoque"));
        produto.setValor(rs.getDouble("p.valor"));
        return produto;
    }
    
}
