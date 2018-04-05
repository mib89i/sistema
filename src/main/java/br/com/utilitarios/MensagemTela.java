/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.utilitarios;

/**
 *
 * @author claudemir
 */
public class MensagemTela {
    private String tipo;
    private String titulo;
    private String mensagem;

    public MensagemTela() {
        this.tipo = "";
        this.titulo = "";
        this.mensagem = "";
    }
    
    public MensagemTela(String tipo, String titulo, String mensagem) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
