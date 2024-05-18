package com.example.projeto_final_hotel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.IdentityHashMap;

public class Reserva {
    private int idReserva;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private int numQuarto;
    private String tipo;

    public Reserva(int idReserva){
        this.idReserva = idReserva;
    }
    public Reserva(){
    }

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

    public Reserva(int idReserva,String nome, String telefone, String email, LocalDate dataEntrada, LocalDate dataSaida) {
        this.idReserva = idReserva;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public Reserva(String nome, String telefone, String email, LocalDate dataEntrada, LocalDate dataSaida) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

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
}
