/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.model;

import br.com.seguranca.model.Fornecedor;
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
@Table(name = "estoque_material")
public class EstoqueMaterial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_material", referencedColumnName = "id")
    @ManyToOne
    private Material material;
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id")
    @ManyToOne
    private Fornecedor fornecedor;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "es")
    private String es;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_estoque")
    private Date data;
    @Column(name = "hora_estoque", length = 8)
    private String hora;

    public EstoqueMaterial() {
        this.id = null;
        this.material = new Material();
        this.fornecedor = new Fornecedor();
        this.quantidade = 0;
        this.es = "";
        this.data = Datas.dataHoje();
        this.hora = "";
    }

    public EstoqueMaterial(Integer id, Material material, Fornecedor fornecedor, Integer quantidade, String es, Date data, String hora) {
        this.id = id;
        this.material = material;
        this.fornecedor = fornecedor;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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
