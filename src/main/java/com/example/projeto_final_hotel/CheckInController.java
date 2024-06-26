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
    //Atributos do checkIn.fxml
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

    //Método para dar reset no textField, combobox e DatePicker
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
        // Verifica se algum dos campos está vazio
        if (nomeCheckIn.getText().isEmpty() || telefoneCheckIn.getText().isEmpty() || emailcheckIn.getText().isEmpty() || roomTypeCheckIn.getSelectionModel().getSelectedItem() == null || roomNumberCheckIn.getSelectionModel().getSelectedItem() == null || dataEntradaCheckIn.getValue() == null || dataSaidaCheckIn.getValue() == null){
            // Exibe um alerta de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos");
            alert.showAndWait();
        }else {
            try{
                // Tenta abrir uma conexão com a base de dados
                Connection conn = ConexaoBD.openDB();
                if(conn != null){
                    // Obtém os valores dos campos de entrada
                    String nome = String.valueOf(nomeCheckIn.getText());
                    String telefone = String.valueOf(telefoneCheckIn.getText());
                    int numQuarto = Integer.parseInt((String) roomNumberCheckIn.getSelectionModel().getSelectedItem());
                    String tipo = String.valueOf(roomTypeCheckIn.getSelectionModel().getSelectedItem());
                    String email = String.valueOf(emailcheckIn.getText());
                    LocalDate dataEntrada = LocalDate.parse(String.valueOf(dataEntradaCheckIn.getValue()));
                    LocalDate dataSaida = LocalDate.parse(String.valueOf(dataSaidaCheckIn.getValue()));

                    // Exibe um alerta de confirmação para adicionar a reserva
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("Deseja mesmo adicionar?");
                    alert.setContentText("Nome da Reserva: " + nome + "\nTelefone: " + telefone + "\nE-mail: " + email + "\nData de entrada:  " + dataEntrada + "\nData de saída: " + dataSaida);
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);

                    alert.showAndWait().ifPresent(Btn ->{
                        try{
                            // Prepara a instrução SQL para inserir uma nova reserva
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


                            // instrução SQL para atualizar o status do quarto para 'Ocupado'
                            String sqlEditStatus = "UPDATE QuartosDisponiveis SET status ='Ocupado' WHERE numQuarto='"+numQuarto+"';";
                            Statement st = conn.createStatement();
                            st.executeUpdate(sqlEditStatus);

                            // Obtém o ID gerado para a nova reserva
                            int gerarID = -1;
                            ResultSet gerarKey = stmt.getGeneratedKeys();
                            if(gerarKey.next()){
                                gerarID = gerarKey.getInt(1);
                            }

                            // Exibe um alerta de sucesso
                            Alert alertAddFun = new Alert(Alert.AlertType.INFORMATION);
                            alertAddFun.setTitle("Informação");
                            alertAddFun.setHeaderText("Reserva adicionada com sucesso");
                            alertAddFun.setContentText(null);
                            alertAddFun.showAndWait();

                            // Adiciona a nova reserva à lista de reservas
                            Reserva r = new Reserva(gerarID,numQuarto, tipo, nome, telefone, email, dataEntrada, dataSaida);
                            Settings.getListaReserva().add(r);

                            //Dei clear na lista depois listei a mesma em seguida para fazer as alterações na TableView dos quartos
                            Settings.getListaQuartos().clear();
                            QuartosDisponiveisDAO.listarQuartos();

                            // Limpa os campos
                            nomeCheckIn.clear();
                            telefoneCheckIn.clear();
                            emailcheckIn.clear();
                            roomNumberCheckIn.getSelectionModel().clearSelection();
                            roomTypeCheckIn.getSelectionModel().clearSelection();
                            dataEntradaCheckIn.setValue(null);
                            dataSaidaCheckIn.setValue(null);


                        }catch (Exception e){
                            // Em caso de erro, exibe um alerta com a mensagem de erro
                            Alert alertErro = new Alert(Alert.AlertType.ERROR);
                            alertErro.setTitle("ERRO");
                            alertErro.setHeaderText("Erro ao adicionar a Reserva: " + e.getMessage());
                            alertErro.showAndWait();
                        }
                    });
                }else {
                    // Exibe um alerta se não for possível estabelecer uma conexão com a base de dados
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            }catch (NumberFormatException e){
                // Exibe um alerta se os dados não estiverem no formato correto
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Coloque os respetivos dados de acordo com o formato.");
                alert.showAndWait();
            }finally {
                //Fecha a conexão com a base de dados
                ConexaoBD.closeDB();
            }
        }
    }

    // Método para listar os tipos de quartos disponíveis
    public void roomTypeList(){
        // Consulta SQL para obter os tipos distintos de quartos disponíveis, agrupados e ordenados
        String listarTipo = "SELECT DISTINCT tipo FROM QuartosDisponiveis WHERE status ='Disponível' GROUP BY tipo ORDER BY tipo ASC";

        // Abre uma conexão com a base de dados
        Connection conn = ConexaoBD.openDB();
        try {

            // Cria uma lista observável para armazenar os tipos de quartos
            ObservableList listData = FXCollections.observableArrayList();
            // Prepara a instrução SQL
            PreparedStatement stmt = conn.prepareStatement(listarTipo);
            // Executa a consulta SQL
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                // Adiciona o tipo de quarto à lista
                listData.add(res.getString("tipo"));
            }
            // Define os itens da ComboBox de tipos de quarto com a lista obtida
            roomTypeCheckIn.setItems(listData);

            // Atualiza a lista de números de quartos com base no tipo selecionado
            roomNumberList();

        }catch (Exception e){
            // Imprime os erros em caso de exceção
            e.printStackTrace();
        }
    }

    // Método para listar os números dos quartos disponíveis com base no tipo selecionado
    public void roomNumberList(){
        // Obtém o tipo de quarto selecionado da ComboBox
        String type = (String) roomTypeCheckIn.getSelectionModel().getSelectedItem();
        // Consulta SQL para obter os números de quartos disponíveis do tipo especificado
        String QuartoDisponivel = "SELECT * FROM QuartosDisponiveis WHERE tipo ='"+type+"' AND status ='Disponível';";

        // Abre uma conexão com a base de dados
        Connection conn = ConexaoBD.openDB();

        try{
            // Cria uma Observablelist para armazenar os números dos quartos, prepara a instrução SQL e depois define o parametro para o tipo de quarto
            ObservableList listData = FXCollections.observableArrayList();
            PreparedStatement stmt = conn.prepareStatement(QuartoDisponivel);
            // Executa a consulta SQL
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                // Adiciona o número do quarto à Observablelist
                listData.add(res.getString("numQuarto"));
            }
            // Define os itens da ComboBox de números de quarto com a lista obtida
            roomNumberCheckIn.setItems(listData);

        }catch (Exception e){
            //Mostra os erros em caso de exceção
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomTypeList();
        roomNumberList();
    }
}
