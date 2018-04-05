/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seguranca.controller;

import br.com.seguranca.dao.UsuarioDao;
import br.com.seguranca.model.Pessoa;
import br.com.seguranca.model.Usuario;
import br.com.utilitarios.MensagemFlash;
import br.com.utilitarios.Sessao;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Claudemir Rtools
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

    private Usuario usuario = new Usuario();
    private String sessaoMessageLogin = "";
    private Pessoa pessoa = new Pessoa();

    public UsuarioController() {

//        try {
//            if (!FacesContext.getCurrentInstance().isPostback()) {
//                Sessao.remove("sessao_mensagem_tela");
//            }
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        
    }

    public String entrar() {
        UsuarioDao dao = new UsuarioDao();

        Usuario u = dao.pesquisaUsuario(usuario.getUsuario(), usuario.getSenha());

        if (u != null) {

            pessoa = dao.pesquisaPessoaUsuario(u.getId());

            if (pessoa == null) {
                new MensagemFlash().danger("Atenção", "USUÁRIO SEM PESSOA CADASTRADA!");
                return null;
            }

            Sessao.put("sessao_pessoa_usuario", pessoa);
            Object redirect_page = Sessao.get("sessao_redirect_page", true);

            sessaoMessageLogin = "";
            usuario = new Usuario();

            if (redirect_page != null) {
                return redirect_page.toString().replace(".xhtml", "");
            } else {
                return "painel_de_controle";
            }
        } else {
            new MensagemFlash().danger("Atenção", "USUÁRIO E/OU SENHA INVÁLIDOS");
            return null;
        }
    }

    public String sair() {
        FacesContext conext = FacesContext.getCurrentInstance();
        //Verifica a sessao e a grava na variavel
        HttpSession session = (HttpSession) conext.getExternalContext().getSession(false);
        //Fecha/Destroi sessao
        session.invalidate();
        return "index";
    }

    public void validacao() throws IOException {
        HttpServletRequest pagina_requerida = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String uri_pagina = pagina_requerida.getRequestURI().replace("/sistema/", "");

        if (!Sessao.exist("sessao_pessoa_usuario")) {
            Sessao.put("sessao_redirect_page", uri_pagina);
            Sessao.put("sessao_message_login", "ENTRE NO SISTEMA PARA TER ACESSO A ESSA PÁGINA");

            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }

        valida_permissao_tela(uri_pagina);
    }

    public void valida_permissao_tela(String pagina) throws IOException {

        // VERIFICAR PÁGINAS E PERMISSÕES DEPOIS
        if (1 == 1) {
            return;
        }

        switch (pagina) {
            case "painel_de_controle.xhtml":
                return;
            case "lista_pessoa.xhtml":
                if (temPermissao("lista_pessoa")) {
                    return;
                }
                break;
            case "pessoa.xhtml":
                if (temPermissao("pessoa")) {
                    return;
                }
                break;
            case "permissoes.xhtml":
                if (temPermissao("permissoes")) {
                    return;
                }
                break;
            default:
                //Object redirect_page = Sessao.get("sessao_redirect_page", true);

                //if (redirect_page != null) {
                //FacesContext.getCurrentInstance().getExternalContext().redirect(redirect_page.toString());
                //} else {
                //}
                break;
//                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//                try {
//                    response.sendRedirect("");
//                    FacesContext.getCurrentInstance().responseComplete();
//                } catch (Exception ex) {
//                    ex.getMessage();
//                }
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("painel_de_controle.xhtml");
    }

    public Boolean temPermissao(String descricao_permissao) {
        if (!Sessao.exist("sessao_pessoa_usuario")) {
            return false;
        }

        Usuario u = ((Pessoa) Sessao.get("sessao_pessoa_usuario")).getUsuario();
        if (u.getAdministrador() == true) {
            return true;
        }
        // SE LISTA DE PERMISSAO FOR VAZIA ENTÃO NÃO TEM PERMISSÃO
        // SE VIER RESULTADO NA LISTA DE PERMISSÃO ENTÃO TEM PERMISSÃO

        return !new UsuarioDao().listaPermissaoUsuario(u.getGrupo().getId(), u.getId(), descricao_permissao).isEmpty();

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSessaoMessageLogin() {
        if (Sessao.exist("sessao_message_login")) {
            sessaoMessageLogin = (String) Sessao.get("sessao_message_login", true);
        }
        return sessaoMessageLogin;
    }

    public void setSessaoMessageLogin(String sessaoMessageLogin) {
        this.sessaoMessageLogin = sessaoMessageLogin;
    }

    public Pessoa getPessoa() {
        if (Sessao.exist("sessao_pessoa_usuario")) {
            pessoa = (Pessoa) Sessao.get("sessao_pessoa_usuario");
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Usuario getUsuarioSessao() {
        return ((Pessoa) Sessao.get("sessao_pessoa_usuario")).getUsuario();
    }

    public static Usuario usuarioSessao() {
        return ((Pessoa) Sessao.get("sessao_pessoa_usuario")).getUsuario();
    }
}
