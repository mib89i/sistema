/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.controller;

import br.com.conexao.Dao;
import br.com.estoque.dao.EstoqueDao;
import br.com.estoque.model.Estoque;
import br.com.estoque.model.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author claudemir
 */
@ManagedBean
@ViewScoped
public class EstoqueController implements Serializable {

    private List<ObjetoEstoque> listaEstoque = new ArrayList();

    public EstoqueController() {
        loadListaEstoque();
    }

    public final void loadListaEstoque() {
        listaEstoque.clear();

        List<Object> result = new EstoqueDao().listaEstoque();
        
        Dao dao =  new Dao();
        
        for (Object ob : result){
            Object[] linha = (Object[]) ob;
            
            listaEstoque.add(
                    new ObjetoEstoque(
                            (Produto) dao.find(new Produto(), (Integer) linha[0]), 
                            (Integer) linha[3] == null ? 0 : (Integer) linha[3]
                    )
            );
            
        }
    }

    public List<ObjetoEstoque> getListaEstoque() {
        return listaEstoque;
    }

    public void setListaEstoque(List<ObjetoEstoque> listaEstoque) {
        this.listaEstoque = listaEstoque;
    }

    public class ObjetoEstoque {
        private Produto produto;
        private Integer quantidade;

        public ObjetoEstoque(Produto produto, Integer quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
        }

        public Produto getProduto() {
            return produto;
        }

        public void setProduto(Produto produto) {
            this.produto = produto;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }

    }
}
