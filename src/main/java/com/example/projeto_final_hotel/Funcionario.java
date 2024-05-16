package com.example.projeto_final_hotel;

import java.time.LocalDate;
import java.util.Date;

public class Funcionario {
    private int idFun;
    private String primeiroNome;
    private String segundoNome;
    private LocalDate dataNascimento;
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(int idFun) {
        this.idFun = idFun;
    }

    public Funcionario(int idFun, String primeiroNome, String segundoNome, LocalDate dataNascimento, String cargo) {
        this.idFun = idFun;
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    public Funcionario(String primeiroNome, String segundoNome, LocalDate dataNascimento, String cargo) {
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    public int getIdFun() {
        return idFun;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }

    public void setSegundoNome(String segundoNome) {
        this.segundoNome = segundoNome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
