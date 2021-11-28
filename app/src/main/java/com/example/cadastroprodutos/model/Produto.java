package com.example.cadastroprodutos.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private Long id;
    private String nome;
    private Integer valor;

    public String toString(){
        return "Id: " + id + "\n" + "nome: " + nome.toString() + "\n" + "R$: " + getValor();
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

    public float getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
