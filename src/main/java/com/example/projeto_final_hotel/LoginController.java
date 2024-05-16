package com.example.projeto_final_hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    public Label MensagemLoginLabel;
    @FXML
    private TextField nameTxt;
    @FXML
    private PasswordField passPf;
    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonCancel;

    public void buttonLoginOnAction(ActionEvent actionEvent) {


        if(!nameTxt.getText().isBlank() && !passPf.getText().isBlank()){
            validarLogin();
        } else{
            MensagemLoginLabel.setText("Preencha todos os espaços por favor");
        }
    }


    public void buttonCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    public void validarLogin(){
        ConexaoBD connection = new ConexaoBD();
        Connection connDB = connection.getConn();

        String verificarLogin = "SELECT count(1) FROM Login WHERE username = '" + nameTxt.getText() + "' AND password = '" + passPf.getText() + "'";

        try{
            Statement stmt = connDB.createStatement();
            ResultSet resultado = stmt.executeQuery(verificarLogin);

            while (resultado.next()){
                if(resultado.getInt(1) == 1) {
                    MensagemLoginLabel.setText("BEM VINDO!");

                }else{
                    MensagemLoginLabel.setText("Login Inválido. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
