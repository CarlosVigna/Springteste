package com.example.springteste.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="contas")
public class ContasModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id;
    @Column(name = "nome_conta")
    private String nome;
    @Column(name = "saldo_inicial")
    private BigDecimal saldo;

    @ManyToMany(mappedBy = "contas")
    @JsonManagedReference

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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
