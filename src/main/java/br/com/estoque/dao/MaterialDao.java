/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.dao;

import br.com.conexao.Conexao;
import br.com.estoque.model.Material;
import br.com.estoque.model.Medida;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author claudemir
 */
public class MaterialDao extends Conexao {

    public List<Material> listaMaterial() {
        try {

            Query qry = getEntityManager().createNativeQuery(
                    "  SELECT m.* \n"
                    + "  FROM material m \n "
                    + " ORDER BY m.nome", Material.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }
    
    public List<Medida> listaMedida() {
        try {

            Query qry = getEntityManager().createNativeQuery(
                    "  SELECT m.* \n"
                    + "  FROM medida m \n "
                    + " ORDER BY m.id", Medida.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }
}
