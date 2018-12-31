package com.github.developermobile.sisvenda.venda;

import com.github.developermobile.sisvenda.cliente.Cliente;
import java.sql.Date;
import java.util.List;



/**
 *
 * @author tiago
 */
public class Venda {
    
    private Integer id;
    private Cliente cliente;
    private Date dataVenda;
    private List<ItensVenda> itensVendas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItensVenda> getItensVendas() {
        return itensVendas;
    }

    public void setItensVendas(List<ItensVenda> itensVendas) {
        this.itensVendas = itensVendas;
    }
    
}
