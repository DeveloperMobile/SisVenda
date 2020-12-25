/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.developermobile.sisvenda.venda;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    
    @EmbeddedId
    private ItensVendaPk venda;
    
    @Column(name = "QTDE")
    private Integer qtde;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private Double valor;

    public ItensVenda() {}

    public ItensVendaPk getId() {
        return venda;
    }

    public void setId(ItensVendaPk id) {
        this.venda = id;
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
