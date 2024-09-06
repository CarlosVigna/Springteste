package com.example.springteste.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "usuariosContas",
            joinColumns = @JoinColumn(name = "usuarioId"),
            inverseJoinColumns = @JoinColumn(name = "contaId")
    )

    private Set<ContasModel> contas = new HashSet<>();



    public Set<ContasModel> getContas() {
        return contas;
    }

    public void setContas(Set<ContasModel> contas) {
        this.contas = contas;
    }

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

    public void addConta(ContasModel contasModel) {
        this.contas.add(contasModel);
        contasModel.getUsuarios().add(this);
    }

    public void removeConta(ContasModel contasModel) {
        this.contas.remove(contasModel);
        contasModel.getUsuarios().remove(this);
    }
}
