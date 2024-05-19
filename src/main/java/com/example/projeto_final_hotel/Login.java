package com.example.projeto_final_hotel;

import javafx.scene.control.PasswordField;

public class Login {
    private int idUser;
    private String nome;
    private String username;
    private PasswordField password;

    public Login(int idUser, String nome, String username, PasswordField password) {
        this.idUser = idUser;
        this.nome = nome;
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }
}
