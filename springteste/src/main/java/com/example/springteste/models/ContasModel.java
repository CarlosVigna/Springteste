package com.example.springteste.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="contas")
public class ContasModel implements Serializable {

    private static final long serialVersionUTD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeConta;
    private BigDecimal saldoInicial;

    @ManyToMany(mappedBy = "contas")

    private Set<UsuariosModel> usuarios = new HashSet<>();


    public Set<UsuariosModel> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuariosModel> usuarios) {
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public void addUsuario(UsuariosModel usuariosModel) {
        this.usuarios.add(usuariosModel);
        usuariosModel.getContas().add(this);
    }

    public void removeUsuario(UsuariosModel usuarioModel) {
        this.usuarios.remove(usuarioModel);
        usuarioModel.getContas().remove(this);
    }

}
