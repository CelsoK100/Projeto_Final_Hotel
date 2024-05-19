package com.example.projeto_final_hotel;

public class QuartosDisponiveis {
    //Atributos
    private int idQuarto;
    private int numQuarto;
    private String tipo;
    private String status;
    private double preco;

    //Método Construtor vazio
    public QuartosDisponiveis() {
    }

    //Método construtor com apenas o id do quarto
    public QuartosDisponiveis(int idQuarto) {
        idQuarto = idQuarto;
    }

    //Método construtor com todos os atributos
    public QuartosDisponiveis(int idQuarto,int numQuarto, String tipo, String status, double preco) {
        this.idQuarto = idQuarto;
        this.numQuarto = numQuarto;
        this.tipo = tipo;
        this.status = status;
        this.preco = preco;
    }

    //Método construtor com todos os atributos menos o id do quarto
    public QuartosDisponiveis(int numQuarto, String tipo, String status, double preco) {
        this.numQuarto = numQuarto;
        this.tipo = tipo;
        this.status = status;
        this.preco = preco;
    }

    //Métodos getters e setter para todos os atributos mernos o id que tem somento o getter.Servem para obter o valor e alterar.
    public int getIdQuarto() {
        return idQuarto;
    }

    public int getNumQuarto() {
        return numQuarto;
    }

    public void setNumQuarto(int numQuarto) {
        this.numQuarto = numQuarto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
