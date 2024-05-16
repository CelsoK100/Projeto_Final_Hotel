package com.example.projeto_final_hotel;

import java.sql.Date;

public class Reserva {
    private int IdReserva;
    private QuartosDisponiveis idQuarto;
    private String nome;
    private String telefone;
    private String email;

    public Reserva(){

    }

    public Reserva(int idReserva, int idQuarto, String nome, String telefone, String email, Date checkIn, Date checkOut) {
    }



    public Reserva(int idReserva, String nome, String telefone, String email, QuartosDisponiveis idQuarto) {
        IdReserva = idReserva;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.idQuarto = idQuarto;
    }

    public int getIdReserva() {
        return IdReserva;
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

    public QuartosDisponiveis getIdQuarto() {
        return idQuarto;
    }

}
