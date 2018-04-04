/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.controller;

import br.com.estoque.dao.EstoqueDao;
import br.com.estoque.model.Estoque;
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
    private List<Estoque> listaEstoque = new ArrayList();

    public EstoqueController(){
        loadListaEstoque();
    }
    
    public final void loadListaEstoque(){
        listaEstoque.clear();
        
        listaEstoque = new EstoqueDao().listaEstoque();
    }
    
    public List<Estoque> getListaEstoque() {
        return listaEstoque;
    }

    public void setListaEstoque(List<Estoque> listaEstoque) {
        this.listaEstoque = listaEstoque;
    }
}
