/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.utilitarios;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;

/**
 * CLASSE QUE SERVE APENAS PARA UMA TABLE DENTRO DA TELA
 *
 * @author Claudemir Rtools
 */
@ManagedBean
@ViewScoped
public class DataTableHtml implements Serializable {

    private HtmlDataTable dataTable;

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }
}
