/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.model;

import br.com.seguranca.model.Grupo;
import br.com.utilitarios.Datas;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author claudemir
 */
@Entity
@Table(name = "produto_fornecedor_quantidade")
public class ProdutoFornecedorQuantidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_produto_fornecedor", referencedColumnName = "id")
    @ManyToOne
    private ProdutoFornecedor produtoFornecedor;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "es")
    private String es;
    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Date data;
    @Column(name = "hora", length = 8)
    private String hora;

    public ProdutoFornecedorQuantidade() {
        this.id = null;
        this.produtoFornecedor = new ProdutoFornecedor();
        this.quantidade = null;
        this.es = "";
        this.data = Datas.dataHoje();
        this.hora = "";
    }

    public ProdutoFornecedorQuantidade(Integer id, ProdutoFornecedor produtoFornecedor, Integer quantidade, String es, Date data, String hora) {
        this.id = id;
        this.produtoFornecedor = produtoFornecedor;
        this.quantidade = quantidade;
        this.es = es;
        this.data = data;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProdutoFornecedor getProdutoFornecedor() {
        return produtoFornecedor;
    }

    public void setProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        this.produtoFornecedor = produtoFornecedor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDataString() {
        return Datas.converteData(data);
    }

    public void setDataString(String dataString) {
        this.data = Datas.converte(dataString);
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
