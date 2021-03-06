/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.developermobile.sisvenda.venda;

import com.github.developermobile.sisvenda.produto.Produto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tiago
 */
@Entity
@Table(name = "ITENS_VENDA")
@NamedQueries({
    @NamedQuery(name = "ItensVenda.findAll", query = "SELECT i FROM ItensVenda i"),
    @NamedQuery(name = "ItensVenda.findByQtde", query = "SELECT i FROM ItensVenda i WHERE i.qtde = :qtde"),
    @NamedQuery(name = "ItensVenda.findByValor", query = "SELECT i FROM ItensVenda i WHERE i.valor = :valor")})
public class ItensVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Produto produto;
    
    @Id
    @JoinColumn(name = "ID_VENDA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Venda venda;
    
    @Column(name = "QTDE")
    private Integer qtde;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private Double valor;

    public ItensVenda() {}
    
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

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this != null ? this.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensVenda)) {
            return false;
        }
        ItensVenda other = (ItensVenda) object;
        if ((this == null && this != null) || (this != null && !this.equals(other))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.developermobile.sisvenda.venda.ItensVenda[ itensVendaPK=" + this + " ]";
    }
    
}
