/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.controller;

import br.com.conexao.Dao;
import br.com.seguranca.dao.FornecedorDao;
import br.com.seguranca.model.Fornecedor;
import br.com.utilitarios.Datas;
import br.com.utilitarios.MensagemFlash;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author claudemir
 */

@ManagedBean
@ViewScoped
public class EntradaPFController implements Serializable {

    private Integer indexListaFornecedor = 0;
    private List<SelectItem> listaFornecedor = new ArrayList();
    
    private Integer indexListaPF = 0;
    private List<SelectItem> listaPF = new ArrayList();
    
    public EntradaPFController(){
        loadListaFornecedor();
//        loadListaPF();
//        loadListaProdutoFQ();
    }
    
    public void salvar(){
//        if (produtoFQ.getQuantidade() == null || produtoFQ.getQuantidade() < 1){
//            MensagemFlash.fatal("", "DIGITE UMA QUANTIDADE PARA O PRODUTO!");
//            return;
//        }
        
        Dao dao = new Dao();
//        
//        produtoFQ.setHora(Datas.horaMinuto());
//        produtoFQ.setEs("E");
//        produtoFQ.setProdutoFornecedor((ProdutoFornecedor) dao.find(new ProdutoFornecedor(), Integer.valueOf(listaPF.get(indexListaPF).getDescription())));
                
        dao.begin();

//        if (!dao.update(produtoFQ)) {
//            dao.rollback();
//            MensagemFlash.fatal("", "NÃO FOI POSSÍVEL SALVAR ENTRADA!");
//            return;
//        }

//        MensagemFlash.info("", "ENTRADA CADASTRADA COM SUCESSO!");
        
        dao.commit();
        
//        produtoFQ = new ProdutoFornecedorQuantidade();
//        loadListaProdutoFQ();
    }
    
//    public void selecionarPFQ(ProdutoFornecedorQuantidade pfq){
//        produtoFQSelecionado = pfq;
//    }
//    
    public void excluir(){
        Dao dao = new Dao();
        
        dao.begin();
//        
//        if (!dao.remove(produtoFQSelecionado)){
//            MensagemFlash.fatal("", "ERRO AO EXCLUIR REGISTRO!");
//            dao.rollback();
//            return;
//        }
//        
//        MensagemFlash.info("", "ENTRADA EXCLUÍDA COM SUCESSO!");
//        dao.commit();
//        
//        produtoFQSelecionado = new ProdutoFornecedorQuantidade();
//        loadListaProdutoFQ();
    }
    
    public final void loadListaFornecedor(){
        listaFornecedor.clear();
        
        List<Fornecedor> result = new FornecedorDao().listaFornecedor();
        
        for (int i = 0; i < result.size(); i++){
            listaFornecedor.add(new SelectItem(i, result.get(i).getNome(), Integer.toString(result.get(i).getId())));
        }
    }
    
//    public final void loadListaPF(){
//        listaPF.clear();
//        
//        List<ProdutoFornecedor> result = new FornecedorDao().listaProdutoFornecedor();
//        
//        for (int i = 0; i < result.size(); i++){
//            listaPF.add(new SelectItem(i, result.get(i).getNome(), Integer.toString(result.get(i).getId())));
//        }
//    }
//    
//    public final void loadListaProdutoFQ(){
//        listaProdutoFQ.clear();
//        
//        listaProdutoFQ = new FornecedorDao().listaProdutoFQ();
//        
//    }
    
    public List<SelectItem> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<SelectItem> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public Integer getIndexListaFornecedor() {
        return indexListaFornecedor;
    }

    public void setIndexListaFornecedor(Integer indexListaFornecedor) {
        this.indexListaFornecedor = indexListaFornecedor;
    }

    public Integer getIndexListaPF() {
        return indexListaPF;
    }

    public void setIndexListaPF(Integer indexListaPF) {
        this.indexListaPF = indexListaPF;
    }

    public List<SelectItem> getListaPF() {
        return listaPF;
    }

    public void setListaPF(List<SelectItem> listaPF) {
        this.listaPF = listaPF;
    }

}
