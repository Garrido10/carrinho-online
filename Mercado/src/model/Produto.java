package model;

import utils.Utils;

public class Produto {

    private static int contador = 1;

    private int id;
    private String nome;
    private Double preco;

    public Produto(String nome, Double preco) {
        this.id = Produto.contador;
        this.nome = nome;
        this.preco = preco;
        Produto.contador += 1;          //Sempre que for registrado um novo produto, o contador do "id" subirá (+1)
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String toString() {
        return "Id: " + this.getId() +
                "\nNome: " + this.getNome() +
                "\nPreço: " + Utils.doubleParaString(this.getPreco());

    }

}
