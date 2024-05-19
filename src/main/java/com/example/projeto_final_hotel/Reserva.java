package com.example.projeto_final_hotel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.IdentityHashMap;

public class Reserva {
    //Atributos da Classe Reserva
    private int idReserva;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private int numQuarto;
    private String tipo;

    //Método construtor com apenas o id
    public Reserva(int idReserva){
        this.idReserva = idReserva;
    }

    //Método construtor vazio
    public Reserva(){
    }

    //Método construtor com todos os atributos da classe
    public Reserva(int idReserva,int numQuarto, String tipo, String nome, String telefone, String email, LocalDate dataEntrada, LocalDate dataSaida) {
        this.idReserva = idReserva;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.numQuarto = numQuarto;
        this.tipo = tipo;
    }



    //Método construtor sem o id
    public Reserva(String nome, String telefone, String email, LocalDate dataEntrada, LocalDate dataSaida) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    //Métodos getters e setters da classe reserva menos o id que tem somente o getter.serveem para obter e alterar os valores.
    public int getIdReserva() {
        return idReserva;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataEntrada() {
        return dataSaida;
    }

    public void setDataEntrada(LocalDate checkIn) {
        this.dataEntrada = checkIn;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
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
}
