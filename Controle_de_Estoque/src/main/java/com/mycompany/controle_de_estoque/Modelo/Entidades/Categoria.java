package com.mycompany.controle_de_estoque.Modelo.Entidades;

/**
 *
 * @author jully
 */
public class Categoria {
    private long id;
    private String nome;
    private String descricao;
    private int ref_baixo_estoque;

    public Categoria() {
    }

    public Categoria(long id, String nome, String descricao, int ref_baixo_estoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ref_baixo_estoque = ref_baixo_estoque;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRef_baixo_estoque() {
        return ref_baixo_estoque;
    }

    public void setRef_baixo_estoque(int ref_baixo_estoque) {
        this.ref_baixo_estoque = ref_baixo_estoque;
    }
}
