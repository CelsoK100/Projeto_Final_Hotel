package com.example.projeto_final_hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
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
        // Verifica se os campos de texto para o nome e a palavra-passe não estão vazios
        if(!nameTxt.getText().isBlank() && !passPf.getText().isBlank()){
            // Se os campos não estiverem vazios, chama o método para validar o login
            validarLogin();
        } else{
            // Se algum dos campos estiver vazio, define a mensagem de erro na label
            MensagemLoginLabel.setText("Preencha todos os espaços por favor");
        }
    }

    //Método para validar o login
    public void buttonCancelOnAction(ActionEvent actionEvent) {
        // Obtém a janela atual através do botão "Cancelar"
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        // Fecha a janela
        stage.close();
    }

    public void validarLogin(){
        // Estabelece uma ligação à base de dados
        Connection connDB = ConexaoBD.openDB();

        // Cria a consulta SQL para verificar o login
        String verificarLogin = "SELECT count(1) FROM Login WHERE username = '" + nameTxt.getText() + "' AND password = '" + passPf.getText() + "'";

        try{
            // Cria um objeto Statement para executar a consulta SQL
            Statement stmt = connDB.createStatement();
            // Executa a consulta e armazena o resultado
            ResultSet resultado = stmt.executeQuery(verificarLogin);

            while (resultado.next()){
                // Verifica se existe um registo correspondente ao username e password fornecidos
                if(resultado.getInt(1) == 1) {
                    // Define a mensagem de boas-vindas na label
                    MensagemLoginLabel.setText("BEM VINDO!");

                    // Exibe um alerta de informação sobre o sucesso do login
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMAÇÃO");
                    alert.setHeaderText(null);
                    alert.setContentText("LOGIN REALIZADO COM SUCESSO!");
                    alert.showAndWait();

                    // Exibe um alerta de boas-vindas
                    Alert alertEntry = new Alert(Alert.AlertType.INFORMATION);
                    alertEntry.setTitle("INFORMAÇÃO");
                    alertEntry.setHeaderText("SEJA BEM VINDO!!!!");
                    alertEntry.setContentText(null);
                    alertEntry.showAndWait();

                    // Cria uma nova janela
                    Stage back = new Stage();

                    // Obtém a janela atual através do botão de login e esconde-a
                    Window window = buttonLogin.getScene().getWindow();
                    window.hide();
                    back.close();

                    // Carrega o layout "principal.fxml"
                    Parent parent = FXMLLoader.load(getClass().getResource("principal.fxml"));
                    // Cria uma nova cena com o layout carregado
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);

                    // Define a nova cena na janela e exibe-a
                    stage.setScene(scene);
                    stage.show();
                }else{
                    // Define a mensagem de erro para login inválido
                    MensagemLoginLabel.setText("Login Inválido. Tente novamente.");
                }
            }
        } catch (SQLException | IOException e) {
            // Imprime a stack trace do erro, se ocorrer
            e.printStackTrace();
        }finally {
            //Fecha a conexão com a base de dados
            ConexaoBD.closeDB();
        }
    }
}
