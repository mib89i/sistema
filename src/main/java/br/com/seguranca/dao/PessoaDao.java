/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seguranca.dao;

import br.com.conexao.Conexao;
import br.com.seguranca.model.Cargo;
import br.com.seguranca.model.Grupo;
import br.com.seguranca.model.Pessoa;
import br.com.seguranca.model.PessoaComplemento;
import br.com.utilitarios.AnaliseString;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Claudemir Rtools
 */
public class PessoaDao extends Conexao {

    public List<Grupo> listaGrupo() {
        try {
            Query qry = getEntityManager().createNativeQuery(
                    "SELECT g.* FROM grupo g ORDER BY g.id", Grupo.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }

    public List<Cargo> listaCargo() {
        try {
            Query qry = getEntityManager().createNativeQuery(
                    "SELECT c.* FROM cargo c ORDER BY c.id", Cargo.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }

    public List<Pessoa> listaPessoa() {
        try {
            Query qry = getEntityManager().createNativeQuery(
                    "SELECT p.* FROM pessoa p ORDER BY p.nome, p.sobre_nome", Pessoa.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }

    public List<Pessoa> listaPesquisaPessoa(String por_pesquisa, String descricao_pesquisa) {
        String text
                = "SELECT p.* \n "
                + "  FROM pessoa p \n ";
        if (!descricao_pesquisa.isEmpty()) {
            switch (por_pesquisa) {
                case "codigo":
                    text += " WHERE p.id <> 1 AND p.id = " + descricao_pesquisa + " \n ";
                    break;
                case "nome":
                    text += " WHERE p.id <> 1 AND LOWER(p.nome) LIKE '%" + AnaliseString.normalizeLower(descricao_pesquisa) + "%' \n ";
                    break;
                case "sobre_nome":
                    text += " WHERE p.id <> 1 AND LOWER(p.sobre_nome) LIKE '%" + AnaliseString.normalizeLower(descricao_pesquisa) + "%' \n ";
                    break;
                case "cpf":
                    text += " WHERE p.id <> 1 AND p.documento LIKE '%" + descricao_pesquisa + "%' \n ";
                    break;
                case "rg":
                    text += " WHERE p.id <> 1 AND p.rg LIKE '%" + descricao_pesquisa + "%' \n ";
                    break;
                case "usuario":
                    text += " INNER JOIN usuario u ON u.id = p.id_usuario \n ";
                    text += " WHERE p.id <> 1 AND LOWER(u.usuario) LIKE '%" + AnaliseString.normalizeLower(descricao_pesquisa) + "%' \n ";
                    break;
                default:
                    text += " WHERE p.id <> 1 \n ";
                    break;
            }
        } else {
            text += " WHERE p.id <> 1 \n ";
        }
        String order_by
                = " ORDER BY p.nome, p.sobre_nome";

        String limit
                = " LIMIT 50";
        try {
            Query qry = getEntityManager().createNativeQuery(
                    text + order_by + limit, Pessoa.class
            );

            return qry.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return new ArrayList();
    }

    public PessoaComplemento pesquisaPessoaComplementoPorPessoa(Integer id_pessoa) {
        try {
            Query qry = getEntityManager().createNativeQuery(
                    "SELECT pc.* FROM pessoa_complemento pc WHERE pc.id_pessoa = " + id_pessoa, PessoaComplemento.class
            );

            return (PessoaComplemento) qry.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
