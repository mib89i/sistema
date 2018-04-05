/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seguranca.controller;

import br.com.conexao.Dao;
import br.com.seguranca.dao.PessoaDao;
import br.com.seguranca.dao.UsuarioDao;
import br.com.seguranca.model.Cargo;
import br.com.seguranca.model.Grupo;
import br.com.seguranca.model.Pessoa;
import br.com.seguranca.model.Usuario;
import br.com.utilitarios.Datas;
import br.com.utilitarios.MensagemFlash;
import br.com.utilitarios.Sessao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Claudemir Rtools
 */
@ManagedBean
@ViewScoped
public class PessoaController implements Serializable {

    private Pessoa pessoa = new Pessoa();
    private Integer indexListaGrupo = 0;
    private List<SelectItem> listaGrupo = new ArrayList();
    private Integer indexListaCargo = 0;
    private List<SelectItem> listaCargo = new ArrayList();
    private String confirmeSenha = "";
    private List<Pessoa> listaPessoa = new ArrayList();

    private String porPesquisa = "nome";
    private String descricaoPesquisa = "";

    private Part arquivo;
    private HtmlDataTable dataTableDependente = new HtmlDataTable();
    private HtmlDataTable dataTableSindicato = new HtmlDataTable();

    public PessoaController() {
        try {
            if (!Sessao.exist("sessao_pessoa_usuario")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                return;
            }


            loadListaGrupo();
            loadListaCargo();

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            if (request.getParameter("id") != null) {

                Integer pessoa_id = Integer.valueOf(request.getParameter("id"));

                pessoa = (Pessoa) new Dao().find(new Pessoa(), pessoa_id);

                if (pessoa == null){
                    //MensagemFlash.fatal("", "PESSOA NÃO ENCONTRADA!");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("lista_pessoa.xhtml");
                    return;
                }

                confirmeSenha = pessoa.getUsuario().getSenha();

                for (int i = 0; i < listaGrupo.size(); i++) {
                    if (pessoa.getUsuario().getGrupo().getId() == Integer.valueOf(listaGrupo.get(i).getDescription())) {
                        indexListaGrupo = i;
                    }
                }
            } else {
                loadListaPessoa();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public final void loadListaGrupo() {
        listaGrupo.clear();

        List<Grupo> result = new PessoaDao().listaGrupo();

        for (int i = 0; i < result.size(); i++) {
            listaGrupo.add(
                    new SelectItem(
                            i,
                            result.get(i).getNome(),
                            Integer.toString(result.get(i).getId())
                    )
            );
        }
    }

    public final void loadListaCargo() {
        listaCargo.clear();

        List<Cargo> result = new PessoaDao().listaCargo();

        for (int i = 0; i < result.size(); i++) {
            listaCargo.add(
                    new SelectItem(
                            i,
                            result.get(i).getNome(),
                            Integer.toString(result.get(i).getId())
                    )
            );
        }
    }

    public final void loadListaPessoa() {
        listaPessoa.clear();

        listaPessoa = new PessoaDao().listaPesquisaPessoa(porPesquisa, descricaoPesquisa);

        descricaoPesquisa = "";
    }

    public Boolean validaSalvarEditarUsuario() {
        // USUÁRIO NÃO É OBRIGATÓRIO
        if (!pessoa.getUsuario().getUsuario().isEmpty() || !pessoa.getUsuario().getSenha().isEmpty() || !confirmeSenha.isEmpty()) {
            if (pessoa.getUsuario().getUsuario().isEmpty()) {
                //MensagemFlash.fatal("", "DIGITE UM USUÁRIO PARA ESTA PESSOA!");
                return false;
            }

            if (pessoa.getUsuario().getSenha().isEmpty()) {
                //MensagemFlash.fatal("", "DIGITE UMA SENHA DE USUÁRIO PARA ESTA PESSOA!");
                return false;
            }

            if (!pessoa.getUsuario().getSenha().equals(confirmeSenha)) {
                //MensagemFlash.fatal("", "SENHAS NÃO CORRESPONDEM!");
                return false;
            }

            UsuarioDao u_dao = new UsuarioDao();

            if (pessoa.getId() == -1) {
                Usuario u = u_dao.pesquisaUsuarioLoginExiste(null, pessoa.getUsuario().getUsuario());
                if (u != null) {
                    //MensagemFlash.fatal("", "NOME DE USUÁRIO JÁ EXISTE, ESCOLHA OUTRO!");
                    return false;
                }
            } else {
                Usuario u = u_dao.pesquisaUsuarioLoginExiste(pessoa.getUsuario().getId(), pessoa.getUsuario().getUsuario());
                if (u != null) {
                    //MensagemFlash.fatal("", "NOME DE USUÁRIO JÁ EXISTE, ESCOLHA OUTRO!");
                    return false;
                }
            }
        }
        return true;
    }

    public void salvarEditarUsuario() {
        if (!validaSalvarEditarUsuario()) {
            return;
        }

        Dao dao = new Dao();
        dao.begin();

        if (!dao.update(pessoa.getUsuario())) {
            dao.rollback();
            //MensagemFlash.fatal("", "NÃO FOI POSSÍVEL ATUALIZAR USUÁRIO, TENTE NOVAMENTE!");
            return;
        }

        //MensagemFlash.info("", "USUÁRIO ATUALIZADO COM SUCESSO!");
        dao.commit();

        // ALTERA A PESSOA SE A MESMA ESTIVER LOGADA NO SISTEMA
        if (pessoa.getId() == ((Pessoa) Sessao.get("sessao_pessoa_usuario")).getId()) {
            Sessao.put("sessao_pessoa_usuario", pessoa);
        }
    }

    public Boolean validaSalvar() {
        if (pessoa.getNome().isEmpty()) {
            //MensagemFlash.fatal("", "DIGITE UM NOME PARA PESSOA!");
            return false;
        }

        validaSalvarEditarUsuario();
        return true;
    }

    public void salvar() {
        if (!validaSalvar()) {
            return;
        }

        Dao dao = new Dao();
        dao.begin();

        pessoa.getUsuario().setGrupo((Grupo) dao.find(new Grupo(), Integer.valueOf(listaGrupo.get(indexListaGrupo).getDescription())));
        if (pessoa.getId() == -1) {
            pessoa.setDataCadastro(Datas.dataHoje());
            pessoa.setHoraCadastro(Datas.hora());

            if (!dao.save(pessoa.getUsuario())) {
                dao.rollback();
                //MensagemFlash.fatal("", "NÃO FOI POSSÍVEL SALVAR USUÁRIO, TENTE NOVAMENTE!");
                return;
            }

            if (!dao.save(pessoa)) {
                dao.rollback();
                //MensagemFlash.fatal("", "NÃO FOI POSSÍVEL SALVAR PESSOA, TENTE NOVAMENTE!");
                return;
            }

            //MensagemFlash.info("", "PESSOA SALVA COM SUCESSO!");
        } else {
            if (!dao.update(pessoa.getUsuario())) {
                dao.rollback();
                //MensagemFlash.fatal("", "NÃO FOI POSSÍVEL ATUALIZAR USUÁRIO, TENTE NOVAMENTE!");
                return;
            }

            if (!dao.update(pessoa)) {
                dao.rollback();
                //MensagemFlash.fatal("", "NÃO FOI POSSÍVEL ATUALIZAR PESSOA, TENTE NOVAMENTE!");
                return;
            }

            //MensagemFlash.info("", "PESSOA ATUALIZADA COM SUCESSO!");
        }

        dao.commit();

        // ALTERA A PESSOA SE A MESMA ESTIVER LOGADA NO SISTEMA
        if (pessoa.getId() == ((Pessoa) Sessao.get("sessao_pessoa_usuario")).getId()) {
            Sessao.put("sessao_pessoa_usuario", pessoa);
        }
    }

    public void excluir() {
        if (pessoa.getId() == ((Pessoa) Sessao.get("sessao_pessoa_usuario")).getId()) {
            //MensagemFlash.fatal("", "VOCÊ NÃO PODE EXCLUIR SEU PRÓPRIO USUÁRIO!");
            return;
        }

        Dao dao = new Dao();
        dao.begin();

        if (!dao.remove(dao.find(pessoa))) {
            dao.rollback();
            //MensagemFlash.fatal("", "NÃO FOI POSSÍVEL EXCLUIR ESTA PESSOA, TENTE NOVAMENTE!");
            return;
        }

        if (!dao.remove(dao.find(pessoa.getUsuario()))) {
            dao.rollback();
            //MensagemFlash.fatal("", "NÃO FOI POSSÍVEL EXCLUIR ESTE USUÁRIO, TENTE NOVAMENTE!");
            return;
        }

        dao.commit();

        //MensagemFlash.info("", "PESSOA EXCLUÍDA COM SUCESSO!");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lista_pessoa.xhtml");
        } catch (Exception e) {

        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<SelectItem> getListaGrupo() {
        return listaGrupo;
    }

    public void setListaGrupo(List<SelectItem> listaGrupo) {
        this.listaGrupo = listaGrupo;
    }

    public Integer getIndexListaGrupo() {
        return indexListaGrupo;
    }

    public void setIndexListaGrupo(Integer indexListaGrupo) {
        this.indexListaGrupo = indexListaGrupo;
    }

    public String getConfirmeSenha() {
        return confirmeSenha;
    }

    public void setConfirmeSenha(String confirmeSenha) {
        this.confirmeSenha = confirmeSenha;
    }

    public List<Pessoa> getListaPessoa() {
        return listaPessoa;
    }

    public void setListaPessoa(List<Pessoa> listaPessoa) {
        this.listaPessoa = listaPessoa;
    }

    public String getPorPesquisa() {
        return porPesquisa;
    }

    public void setPorPesquisa(String porPesquisa) {
        this.porPesquisa = porPesquisa;
    }

    public String getDescricaoPesquisa() {
        return descricaoPesquisa;
    }

    public void setDescricaoPesquisa(String descricaoPesquisa) {
        this.descricaoPesquisa = descricaoPesquisa;
    }

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getIndexListaCargo() {
        return indexListaCargo;
    }

    public void setIndexListaCargo(Integer indexListaCargo) {
        this.indexListaCargo = indexListaCargo;
    }

    public List<SelectItem> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<SelectItem> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public HtmlDataTable getDataTableDependente() {
        return dataTableDependente;
    }

    public void setDataTableDependente(HtmlDataTable dataTableDependente) {
        this.dataTableDependente = dataTableDependente;
    }

    public HtmlDataTable getDataTableSindicato() {
        return dataTableSindicato;
    }

    public void setDataTableSindicato(HtmlDataTable dataTableSindicato) {
        this.dataTableSindicato = dataTableSindicato;
    }
}
