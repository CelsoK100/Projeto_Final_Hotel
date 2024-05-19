package com.example.projeto_final_hotel;

import java.time.LocalDate;
import java.util.Date;

public class Funcionario {
    //Atributos da classe Funcionario
    private int idFun;
    private String primeiroNome;
    private String segundoNome;
    private LocalDate dataNascimento;
    private String cargo;

    //Mètodo construtor sem parametros
    public Funcionario() {
    }

    //Método construtor com apenas o id
    public Funcionario(int idFun) {
        this.idFun = idFun;
    }

    //Mètodo construtor com todos os atrbutos da classe
    public Funcionario(int idFun, String primeiroNome, String segundoNome, LocalDate dataNascimento, String cargo) {
        this.idFun = idFun;
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }
    //Método construtor com todos os atributos da classe exepto o id
    public Funcionario(String primeiroNome, String segundoNome, LocalDate dataNascimento, String cargo) {
        this.primeiroNome = primeiroNome;
        this.segundoNome = segundoNome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    //Métodos getters e setter de todos os atributos da classe exepto o do id que tem somente o getter.Servem para obter e mudar os valores
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
