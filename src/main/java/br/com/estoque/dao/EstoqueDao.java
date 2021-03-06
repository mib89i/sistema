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

    public List<Object> listaEstoque() {
        try {

            Query qry = getEntityManager().createNativeQuery(
                    " SELECT p.*, e.quantidade \n "
                    + " FROM produto p \n "
                    + " LEFT JOIN estoque e ON e.id_produto = p.id"
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }

    public List<Object> listaMateriaPrima(Integer id_produto_fornecedor) {
        try {

            Query qry = getEntityManager().createNativeQuery(
                    " SELECT pf.id, sum(pfq.quantidade) \n"
                    + "  FROM produto_fornecedor pf \n"
                    + " INNER JOIN produto_fornecedor_quantidade pfq ON pf.id = pfq.id_produto_fornecedor \n"
                    + " WHERE es = 'E' \n"
                    + "   AND pf.id = " + id_produto_fornecedor + " \n"
                    + " GROUP BY pf.id"
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }
}
