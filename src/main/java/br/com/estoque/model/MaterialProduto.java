/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.model;

import java.io.Serializable;
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
 * @author claudemir
 */
@Entity
@Table(name = "material_produto")
public class MaterialProduto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_material", referencedColumnName = "id")
    @ManyToOne
    private Material material;
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    @ManyToOne
    private Produto produto;

    public MaterialProduto() {
        this.id = null;
        this.material = new Material();
        this.produto = new Produto();
    }

    public MaterialProduto(Integer id, Material material, Produto produto) {
        this.id = id;
        this.material = material;
        this.produto = produto;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
