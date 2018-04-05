package br.com.seguranca.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome", length = 1000)
    private String nome;
    
    public Fornecedor() {
        this.id = null;
        this.nome = "";
    }

    public Fornecedor(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
