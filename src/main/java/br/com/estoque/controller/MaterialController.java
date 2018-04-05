/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.controller;

import br.com.conexao.Dao;
import br.com.estoque.dao.MaterialDao;
import br.com.estoque.model.Material;
import br.com.estoque.model.Medida;
import br.com.seguranca.model.Pessoa;
import br.com.utilitarios.MensagemFlash;
import br.com.utilitarios.Sessao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author claudemir
 */
@ManagedBean
@ViewScoped
public class MaterialController implements Serializable {

    private List<Material> listaMaterial = new ArrayList();

    private Material material = new Material();
    private Integer indexListaMedida = 0;
    private List<SelectItem> listaMedida = new ArrayList();

    public MaterialController() {

        try {
            loadListaMedida();

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            if (request.getParameter("id") != null) {

                Integer material_id = Integer.valueOf(request.getParameter("id"));

                material = (Material) new Dao().find(new Material(), material_id);

                if (material == null) {
                    new MensagemFlash().danger("Atenção", "REGISTRO NÃO ENCONTRADA!");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("lista_material.xhtml");
                    return;
                }

                for (int i = 0; i < listaMedida.size(); i++) {
                    if (material.getMedida().getId().equals(Integer.valueOf(listaMedida.get(i).getDescription()))) {
                        indexListaMedida = i;
                    }
                }
            } else {
                loadListaMaterial();
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }

    public String salvar() {
        Dao dao = new Dao();

        if (material.getNome().isEmpty()) {
            new MensagemFlash().danger("Atenção", "DIGITE UM NOME PARA O MATERIAL!");
            return null;
        }

        material.setMedida((Medida) dao.find(new Medida(), Integer.valueOf(listaMedida.get(indexListaMedida).getDescription())));

        dao.begin();

        if (material.getId() == null) {
            if (!dao.save(material)) {
                new MensagemFlash().danger("Atenção", "ERRO AO SALVAR MATERIAL!");
                dao.rollback();
                return null;
            }
        } else {
            if (!dao.update(material)) {
                new MensagemFlash().danger("Atenção", "ERRO AO ATUALIZAR MATERIAL!");
                dao.rollback();
                return null;
            }
        }

        dao.commit();
        
        new MensagemFlash().success("Atenção", "REGISTRO SALVO COM SUCESSO!");
        
        return "lista_material";
    }

    public void excluir() {
        Dao dao = new Dao();

        dao.begin();

        if (!dao.remove(material)) {
            new MensagemFlash().danger("Atenção", "ERRO AO EXCLUIR MATERIAL!");
            dao.rollback();
            return;
        }

        dao.commit();
        
        new MensagemFlash().success("Sucesso", "REGISTRO EXCLUIDO!");

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista_material.xhtml");
        } catch (IOException e) {

        }
    }

    public final void loadListaMaterial() {
        listaMaterial.clear();

        listaMaterial = new MaterialDao().listaMaterial();
    }

    public final void loadListaMedida() {
        listaMedida.clear();

        List<Medida> result = new MaterialDao().listaMedida();

        for (int i = 0; i < result.size(); i++) {
            listaMedida.add(new SelectItem(i, result.get(i).getNome(), Integer.toHexString(result.get(i).getId())));
        }
    }

    public List<Material> getListaMaterial() {
        return listaMaterial;
    }

    public void setListaMaterial(List<Material> listaMaterial) {
        this.listaMaterial = listaMaterial;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getIndexListaMedida() {
        return indexListaMedida;
    }

    public void setIndexListaMedida(Integer indexListaMedida) {
        this.indexListaMedida = indexListaMedida;
    }

    public List<SelectItem> getListaMedida() {
        return listaMedida;
    }

    public void setListaMedida(List<SelectItem> listaMedida) {
        this.listaMedida = listaMedida;
    }

}
