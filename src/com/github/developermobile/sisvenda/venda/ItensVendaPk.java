/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.developermobile.sisvenda.venda;

import com.github.developermobile.sisvenda.produto.Produto;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author tiago
 */
@Embeddable
public class ItensVendaPk {

    public ItensVendaPk() {
    }

    public ItensVendaPk(Produto produto) {
        this.produto = produto;
    }
    
    public ItensVendaPk(Venda venda) {
        this.venda = venda;
    }
    
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Produto produto;
    
    @JoinColumn(name = "ID_VENDA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Venda venda;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
