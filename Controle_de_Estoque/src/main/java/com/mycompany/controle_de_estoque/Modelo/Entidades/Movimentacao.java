package com.mycompany.controle_de_estoque.Modelo.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author jully
 */
public class Movimentacao {
    private long id;
    private LocalDateTime data_criacao;
    private Produto produto;
    private int quantidade;
    private String tipo_entrada;

    public Movimentacao() {
    }

    public Movimentacao(long id, LocalDateTime data_criacao, Produto produto, int quantidade, String tipo_entrada) {
        this.id = id;
        this.data_criacao = data_criacao;
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipo_entrada = tipo_entrada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo_entrada() {
        return tipo_entrada;
    }

    public void setTipo_entrada(String tipo_entrada) {
        this.tipo_entrada = tipo_entrada;
    }
}
