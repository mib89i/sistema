/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seguranca.dao;

import br.com.conexao.Conexao;
import br.com.estoque.model.ProdutoFornecedor;
import br.com.estoque.model.ProdutoFornecedorQuantidade;
import br.com.seguranca.model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author claudemir
 */
public class FornecedorDao extends Conexao {
    public List<Fornecedor> listaFornecedor(){
        try {
            Query qry = getEntityManager().createNativeQuery(
                    "SELECT f.* FROM fornecedor f ORDER BY f.nome", Fornecedor.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }
    
    public List<ProdutoFornecedor> listaProdutoFornecedor(){
        try {
            Query qry = getEntityManager().createNativeQuery(
                    "SELECT pf.* FROM produto_fornecedor pf ORDER BY pf.nome", ProdutoFornecedor.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }
    
    public List<ProdutoFornecedorQuantidade> listaProdutoFQ(){
        try {
            Query qry = getEntityManager().createNativeQuery(
                    "SELECT pfq.* FROM produto_fornecedor_quantidade pfq ORDER BY pfq.id", ProdutoFornecedorQuantidade.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }
}
