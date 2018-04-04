/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seguranca.controller;

import br.com.seguranca.dao.FornecedorDao;
import br.com.seguranca.model.Fornecedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Claudemir Rtools
 */
@ManagedBean
@ViewScoped
public class FornecedorController implements Serializable{
    private Fornecedor fornecedor = new Fornecedor();
    private List<Fornecedor> listaFornecedor = new ArrayList();

    public FornecedorController(){
        loadListaFornecedor();
    }
    
    public final void loadListaFornecedor(){
        listaFornecedor.clear();
        
        listaFornecedor = new FornecedorDao().listaFornecedor();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }
}
