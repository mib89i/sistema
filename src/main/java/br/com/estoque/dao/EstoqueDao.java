/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.dao;

import br.com.conexao.Conexao;
import br.com.estoque.model.Estoque;
import br.com.seguranca.model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author claudemir
 */
public class EstoqueDao extends Conexao {

    public List<Estoque> listaEstoque() {
        try {
            Query qry = getEntityManager().createNativeQuery(
                    " SELECT e.* \n "
                    + " FROM estoque e \n "
                    + "INNER JOIN produto p ON p.id = e.id_produto \n "
                    + "ORDER BY p.nome", Estoque.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }
}
