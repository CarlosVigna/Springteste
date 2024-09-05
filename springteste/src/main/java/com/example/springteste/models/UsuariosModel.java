package com.example.springteste.models;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class UsuariosModel implements Serializable {

    private static final long serialVersionUTD = 1L;

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
