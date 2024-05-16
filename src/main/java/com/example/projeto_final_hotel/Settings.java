package com.example.projeto_final_hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Settings {
    private static Stage primaryStage;
    public static void setPrimaryStage(Stage primaryStage) {
        Settings.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static ObservableList<QuartosDisponiveis> listaQuartos = FXCollections.observableArrayList();
    public static ObservableList<Funcionario> listaFuncionarios = FXCollections.observableArrayList();
    public static ObservableList<Reserva> listaReserva = FXCollections.observableArrayList();

//----------------------------------------------------------------------------------------------------------------------

    public static ObservableList<Funcionario>getListaFuncionarios(){
        return listaFuncionarios;
    }

    public static ObservableList<QuartosDisponiveis>getListaQuartos(){
        return listaQuartos;
    }
    public static ObservableList<Reserva>getListaReserva(){
        return listaReserva;
    }
    //----------------------------------------------------------------------------------------------------------------------
    public static QuartosDisponiveis EditarQuarto;
    public static Funcionario EditarFuncionario;

//----------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------------------------------
    // Métodos para obter as listas
    public static QuartosDisponiveis getEditarQuarto(){
        return EditarQuarto;
    }
    public static Funcionario getEditarFuncionario(){
        return EditarFuncionario;
    }
//----------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------

    // Objetos para editar quartos, funcionários
    public static void setEditarQuarto(QuartosDisponiveis EditarQuarto){
        Settings.EditarQuarto = EditarQuarto;
    }

    public static void setEditarFuncionario(Funcionario EditarFuncionario){
        Settings.EditarFuncionario = EditarFuncionario;
    }

//----------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------
    // Métodos para definir os objetos de edição

    public static void setListaQuartosDisponiveis(ObservableList<QuartosDisponiveis> listaQuartos) {
        Settings.listaQuartos = listaQuartos;
    }
    public static void setListaFuncionarios(ObservableList<Funcionario> listaFuncionarios){
        Settings.listaFuncionarios = listaFuncionarios;
    }
    public static void setListaReserva(ObservableList<Reserva>listaReserva){
        Settings.listaReserva = listaReserva;
    }



}
