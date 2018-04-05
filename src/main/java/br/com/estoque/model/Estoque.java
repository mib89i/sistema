/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.model;

import br.com.seguranca.model.Pessoa;
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
@Table(name = "estoque")
public class Estoque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    @ManyToOne
    private Produto produto;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "es")
    private String es;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_estoque")
    private Date data;
    @Column(name = "hora_estoque", length = 8)
    private String hora;

    public Estoque() {
        this.id = null;
        this.produto = new Produto();
        this.pessoa = new Pessoa();
        this.quantidade = 0;
        this.es = "";
        this.data = Datas.dataHoje();
        this.hora = "";
    }

    public Estoque(Integer id, Produto produto, Pessoa pessoa, Integer quantidade, String es, Date data, String hora) {
        this.id = id;
        this.produto = produto;
        this.pessoa = pessoa;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
