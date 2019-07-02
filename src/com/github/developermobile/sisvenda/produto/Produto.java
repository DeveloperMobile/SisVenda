
package com.github.developermobile.sisvenda.produto;

import com.github.developermobile.sisvenda.fornecedor.Fornecedor;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tiago
 */
@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "NOME")
    private String nome;
    
    @Column(name = "QTDE_ESTOQUE")
    private Integer qtdeEstoque;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Column(name = "VALOR")
    private Double valor;
    
    @JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Fornecedor idFornecedor;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return idFornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.idFornecedor = fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(Integer qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Fornecedor getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Fornecedor idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.developermobile.sisvenda.produto.Produto[ id=" + id + " ]";
    }
    
}
