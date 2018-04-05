package br.com.utilitarios;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MensagemFlash implements Serializable {

    public MensagemFlash() {

    }

    public void primary(String titulo, String mensagem) {
        Sessao.put("sessao_mensagem_tela", new MensagemTela("alert-primary", titulo, mensagem));
    }

    public void secondary(String titulo, String mensagem) {
        Sessao.put("sessao_mensagem_tela", new MensagemTela("alert-secondary", titulo, mensagem));
    }

    public void success(String titulo, String mensagem) {
        Sessao.put("sessao_mensagem_tela", new MensagemTela("alert-success", titulo, mensagem));
    }

    public void danger(String titulo, String mensagem) {
        Sessao.put("sessao_mensagem_tela", new MensagemTela("alert-danger", titulo, mensagem));
    }

    public void warning(String titulo, String mensagem) {
        Sessao.put("sessao_mensagem_tela", new MensagemTela("alert-warning", titulo, mensagem));
    }

    public void info(String titulo, String mensagem) {
        Sessao.put("sessao_mensagem_tela", new MensagemTela("alert-info", titulo, mensagem));
    }

    public void light(String titulo, String mensagem) {
        Sessao.put("sessao_mensagem_tela", new MensagemTela("alert-light", titulo, mensagem));
    }

    public void dark(String titulo, String mensagem) {
        Sessao.put("sessao_mensagem_tela", new MensagemTela("alert-dark", titulo, mensagem));
    }

    public MensagemTela getMt() {
        if (Sessao.exist("sessao_mensagem_tela")) {
            return (MensagemTela) Sessao.get("sessao_mensagem_tela");
        } else {
            return new MensagemTela();
        }
    }

    public void remove() {
        Sessao.remove("sessao_mensagem_tela");
    }

}
