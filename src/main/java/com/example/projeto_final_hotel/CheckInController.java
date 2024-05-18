package com.example.projeto_final_hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CheckInController implements Initializable {

    @FXML
    private ComboBox roomTypeCheckIn;
    @FXML
    private ComboBox roomNumberCheckIn;
    @FXML
    private DatePicker dataEntradaCheckIn;
    @FXML
    private DatePicker dataSaidaCheckIn;
    @FXML
    private TextField nomeCheckIn;
    @FXML
    private TextField telefoneCheckIn;
    @FXML
    private TextField emailcheckIn;
    @FXML
    private Button ResetCheckInbtn;
    @FXML
    private Button checkInBtn;


    public void buttonResetOnAction(ActionEvent actionEvent) {
        nomeCheckIn.setText("");
        telefoneCheckIn.setText("");
        emailcheckIn.setText("");
        roomTypeCheckIn.getSelectionModel().clearSelection();
        roomNumberCheckIn.getSelectionModel().clearSelection();
        dataEntradaCheckIn.setValue(null);
        dataSaidaCheckIn.setValue(null);
    }

    public void btnResetEntered(MouseEvent mouseEvent) {
    }

    public void btnResetExited(MouseEvent mouseEvent) {
    }

    public void buttonAddOnAction(ActionEvent actionEvent) {
        if (nomeCheckIn.getText().isEmpty() || telefoneCheckIn.getText().isEmpty() || emailcheckIn.getText().isEmpty() || roomTypeCheckIn.getSelectionModel().getSelectedItem() == null || roomNumberCheckIn.getSelectionModel().getSelectedItem() == null || dataEntradaCheckIn.getValue() == null || dataSaidaCheckIn.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos");
            alert.showAndWait();
        }else {
            try{
                Connection conn = ConexaoBD.openDB();
                if(conn != null){
                    String nome = String.valueOf(nomeCheckIn.getText());
                    String telefone = String.valueOf(telefoneCheckIn.getText());
                    int numQuarto = Integer.parseInt((String) roomNumberCheckIn.getSelectionModel().getSelectedItem());
                    String tipo = String.valueOf(roomTypeCheckIn.getSelectionModel().getSelectedItem());
                    String email = String.valueOf(emailcheckIn.getText());
                    LocalDate dataEntrada = LocalDate.parse(String.valueOf(dataEntradaCheckIn.getValue()));
                    LocalDate dataSaida = LocalDate.parse(String.valueOf(dataSaidaCheckIn.getValue()));

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("Deseja mesmo adicionar?");
                    alert.setContentText("Nome da Reserva: " + nome + "\nTelefone: " + telefone + "\nE-mail: " + email + "\nData de entrada:  " + dataEntrada + "\nData de saída: " + dataSaida);
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);

                    alert.showAndWait().ifPresent(Btn ->{
                        try{
                            String sql = "INSERT INTO reserva(numQuarto, tipo, nome, telefone, email, dataEntrada, dataSaida) VALUES (?,?,?,?,?,?,?);";
                            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            stmt.setInt(1, numQuarto);
                            stmt.setString(2, tipo);
                            stmt.setString(3, nome);
                            stmt.setString(4, telefone);
                            stmt.setString(5, email);
                            stmt.setDate(6, java.sql.Date.valueOf(dataEntrada));
                            stmt.setDate(7, java.sql.Date.valueOf(dataSaida));
                            stmt.executeUpdate();



                            String sqlEditStatus = "UPDATE QuartosDisponiveis SET status ='Ocupado' WHERE numQuarto='"+numQuarto+"';";

                            Statement st = conn.createStatement();
                            st.executeUpdate(sqlEditStatus);

                            int gerarID = -1;
                            ResultSet gerarKey = stmt.getGeneratedKeys();
                            if(gerarKey.next()){
                                gerarID = gerarKey.getInt(1);
                            }
                            Alert alertAddFun = new Alert(Alert.AlertType.CONFIRMATION);
                            alertAddFun.setTitle("Informação");
                            alertAddFun.setHeaderText("Reserva adicionada com sucesso");
                            alertAddFun.setContentText(null);
                            alertAddFun.showAndWait();

                            Reserva r = new Reserva(gerarID,numQuarto, tipo, nome, telefone, email, dataEntrada, dataSaida);
                            Settings.getListaReserva().add(r);

                        }catch (Exception e){
                            Alert alertErro = new Alert(Alert.AlertType.ERROR);
                            alertErro.setTitle("ERRO");
                            alertErro.setHeaderText("Erro ao adicionar o Quarto: " + e.getMessage());
                            alertErro.showAndWait();
                        }
                    });
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Coloque os respetivos dados de acordo com o formato.");
                alert.showAndWait();
            }finally {
                ConexaoBD.closeDB();
            }
        }


    }

    public void roomTypeList(){
        String listarTipo = "SELECT DISTINCT tipo FROM QuartosDisponiveis WHERE status ='Disponível' GROUP BY tipo ORDER BY tipo ASC";

        Connection conn = ConexaoBD.openDB();
        try {

            ObservableList listData = FXCollections.observableArrayList();
            PreparedStatement stmt = conn.prepareStatement(listarTipo);
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                listData.add(res.getString("tipo"));
            }
            roomTypeCheckIn.setItems(listData);

            roomNumberList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void roomNumberList(){

        String type = (String) roomTypeCheckIn.getSelectionModel().getSelectedItem();
        String QuartoDisponivel = "SELECT * FROM QuartosDisponiveis WHERE tipo ='"+type+"' AND status ='Disponível';";

        Connection conn = ConexaoBD.openDB();

        try{
            ObservableList listData = FXCollections.observableArrayList();
            PreparedStatement stmt = conn.prepareStatement(QuartoDisponivel);
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                listData.add(res.getString("numQuarto"));
            }
            roomNumberCheckIn.setItems(listData);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomTypeList();
        roomNumberList();
    }
}
