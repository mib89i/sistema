/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seguranca.controller;

import br.com.seguranca.model.Empresa;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Claudemir Rtools
 */
@ManagedBean
@RequestScoped
public class EmpresaController implements Serializable{
    private Empresa empresa = new Empresa();

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
}
