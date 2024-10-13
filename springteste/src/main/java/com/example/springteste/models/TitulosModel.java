package com.example.springteste.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "titulos")
public class TitulosModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date vencimento;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "categoria_id")
    private CategoriasModel categoria; // Relacionado com Categoria
    private BigDecimal valor;
    private String descricao;
    private String status;

    public TitulosModel() {
    }

    public TitulosModel(Long id, Date vencimento, CategoriasModel categoria, BigDecimal valor, String descricao, String status) {
        this.id = id;
        this.vencimento = vencimento;
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.status = status;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public CategoriasModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasModel categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
