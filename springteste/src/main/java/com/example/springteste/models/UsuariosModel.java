package com.example.springteste.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class UsuariosModel implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private RegrasUsuarios regra;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "usuarios_contas",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_conta")
    )

    private Set<ContasModel> contas = new HashSet<>();

    public UsuariosModel(RegrasUsuarios regra) {
        this.regra = regra;
    }

    public UsuariosModel(String nome, String email, String senha, RegrasUsuarios regra){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.regra = regra;
    }

    public UsuariosModel() {

    }



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

    public RegrasUsuarios getRegra() {
        return regra;
    }

    public void setRegra(RegrasUsuarios regra) {
        this.regra = regra;
    }

    public void addConta(ContasModel contasModel) {
        this.contas.add(contasModel);
        contasModel.getUsuarios().add(this);
    }

    public void removeConta(ContasModel contasModel) {
        this.contas.remove(contasModel);
        contasModel.getUsuarios().remove(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.regra == RegrasUsuarios.ADMIN)
            return List.of(new SimpleGrantedAuthority("REGRA_ADMIN"), new SimpleGrantedAuthority("REGRA_USUARIO"));
            else return List.of(new SimpleGrantedAuthority("REGRA_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
