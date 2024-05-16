package com.example.projeto_final_hotel;

public class QuartosDisponiveis {
    private int idQuarto;
    private int numQuarto;
    private String tipo;
    private String status;
    private double preco;

    public QuartosDisponiveis() {
    }


    public QuartosDisponiveis(int idQuarto) {
        idQuarto = idQuarto;
    }

    public QuartosDisponiveis(int idQuarto,int numQuarto, String tipo, String status, double preco) {
        this.idQuarto = idQuarto;
        this.numQuarto = numQuarto;
        this.tipo = tipo;
        this.status = status;
        this.preco = preco;
    }

    public QuartosDisponiveis(int numQuarto, String tipo, String status, double preco) {
        this.numQuarto = numQuarto;
        this.tipo = tipo;
        this.status = status;
        this.preco = preco;
    }

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
