package com.example.projeto_final_hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class PrincipalController implements Initializable {
    public TableColumn roomIdNumberCollum;
    public TextField quartoID;
    @FXML
    private Button QuartosDisponiveisBtn;
    @FXML
    private Button ReservaBtn;
    @FXML
    private Button funcionarioBtn;
    @FXML
    private Button btnSair;
    @FXML
    private Button AcercaDeBtn;
    @FXML
    private TextField roomNumber;
    @FXML
    private ComboBox roomStatus;
    @FXML
    private TextField roomPrice;
    @FXML
    private ComboBox roomType;
    @FXML
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button deleteBn;
    @FXML
    private Button checkInBtn;
    @FXML
    private TableView tableViewQuartos;
    @FXML
    private TableColumn roomNumberCollum;
    @FXML
    private TableColumn roomTypeCollum;
    @FXML
    private TableColumn roomStatusCollum;
    @FXML
    private TableColumn roomPriceCollum;
    @FXML
    private TextField roomSearch;
    @FXML
    private AnchorPane FuncionariosForm;
    @FXML
    private ComboBox funcionarioCargo;
    @FXML
    private TextField funcionariosFirstName;
    @FXML
    private TextField funcionariosSecondName;
    @FXML
    private DatePicker funcionarioAge;
    @FXML
    private Button editFunBtn;
    @FXML
    private Button ClearFunBtn;
    @FXML
    private Button delFunbtn;
    @FXML
    private TextField funcionariosId;
    @FXML
    private TableView tableViewFuncionarios;
    @FXML
    private TableColumn FuncionarioIdColumm;
    @FXML
    private TableColumn FuncionarioFirstNameColumm;
    @FXML
    private TableColumn FuncionarioSecondNameColumm;
    @FXML
    private TableColumn FuncionarioAgeColumm;
    @FXML
    private TableColumn FuncionarioCargoColumm;
    @FXML
    private TextField FuncionarioSearch;
    @FXML
    private AnchorPane ReservaForm;
    @FXML
    private TableView ReservaTableView;
    @FXML
    private TableColumn IDReservaCollumn;

    @FXML
    private TableColumn NomeReservaCollumn;
    @FXML
    private TableColumn NumberPhoneReservaCollumn;
    @FXML
    private TableColumn EmailReservaCollumn;
    @FXML
    private TableColumn CheckInReservaCollumn;
    @FXML
    private TableColumn CheckOutReservaCollumn;
    @FXML
    private TextField searchReserva;
    @FXML
    private Button deleteReservaBtn;
    @FXML
    private AnchorPane aboutForm;
    @FXML
    private AnchorPane QuartosDisponiveisForm;
    @FXML
    private Button addFunBtn;

    //------------------------------------------------------------------------------------------------

    public String type[] = {"Um Quarto", "Dois Quartos", "Três Quartos", "Quatro Quartos"};
    // Preenche o ComboBox 'roomType' com tipos de quartos
    public void QuartosDisponiveisRoomType() {
        List<String> listData = new ArrayList<>();

        //Adiciona os tipos de quartos à lista
        for (String data : type) {
            listData.add(data);
        }
        // Converte a lista num ObservableList e associa ao ComboBox 'roomType'
        ObservableList lista = FXCollections.observableArrayList(listData);
        roomType.setItems(lista);
    }

    public String status[] = {"Disponível", "Não Disponível", "Ocupado"};

    public void QuartosDisponiveisRoomStatus() {
        List<String> listData = new ArrayList<>();

        for (String data : status) {
            listData.add(data);
        }

        ObservableList lista = FXCollections.observableArrayList(listData);
        roomStatus.setItems(lista);
    }

    public String cargo[] = {"Superior","gerente de departamento", "supervisor da equipa","auxiliar administrativo"};

    public void FuncionariosCargos(){
        List<String> listFunData = new ArrayList<>();

        for (String data : cargo) {
            listFunData.add(data);
        }

        ObservableList lista = FXCollections.observableArrayList(listFunData);
        funcionarioCargo.setItems(lista);
    }

    //------------------------------------------------------------------------------------------------
    public void ListarQuartos(){
        roomIdNumberCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, Integer>("idQuarto"));
        roomNumberCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, Integer>("numQuarto"));
        roomTypeCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, String>("tipo"));
        roomStatusCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, String>("status"));
        roomPriceCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, Double>("preco"));

        tableViewQuartos.setItems(QuartosDisponiveisDAO.listarQuartos());
        tableViewQuartos.refresh();
    }

    public void listarFuncionarios(){
        FuncionarioIdColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("idFun"));
        FuncionarioFirstNameColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("primeiroNome"));
        FuncionarioSecondNameColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("segundoNome"));
        FuncionarioAgeColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, LocalDate>("dataNascimento"));
        FuncionarioCargoColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cargo"));

        tableViewFuncionarios.setItems(FuncionarioDAO.listarFuncionario());

    }


    public void listarReserva(){
        IDReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, Integer>("idReserva"));
        NomeReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nome"));
        NumberPhoneReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, String>("telefone"));
        EmailReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, String>("email"));
        CheckInReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, LocalDate>("dataEntrada"));
        CheckOutReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, LocalDate>("dataSaida"));

        ReservaTableView.setItems(ReservaDAO.listarReserva());

    }

    //------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------
    public void buttonQDFormOnAction(ActionEvent actionEvent) {
        QuartosDisponiveisForm.setVisible(true);
        FuncionariosForm.setVisible(false);
        ReservaForm.setVisible(false);
        aboutForm.setVisible(false);
        ListarQuartos();
    }

    public void buttonFunFormOnAction(ActionEvent actionEvent) {
        QuartosDisponiveisForm.setVisible(false);
        FuncionariosForm.setVisible(true);
        ReservaForm.setVisible(false);
        aboutForm.setVisible(false);
        Settings.getListaQuartos().clear();

    }

    public void buttonReservaFormOnAction(ActionEvent actionEvent) {
        QuartosDisponiveisForm.setVisible(false);
        FuncionariosForm.setVisible(false);
        ReservaForm.setVisible(true);
        aboutForm.setVisible(false);
        Settings.getListaQuartos().clear();

    }

    public void buttonAcercaDeFormOnAction(ActionEvent actionEvent) {
        QuartosDisponiveisForm.setVisible(false);
        FuncionariosForm.setVisible(false);
        ReservaForm.setVisible(false);
        aboutForm.setVisible(true);
        Settings.getListaQuartos().clear();
    }

    //------------------------------------------------------------------------------------------------

    public void btnRoomEntered(MouseEvent mouseEvent) {
        QuartosDisponiveisBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnRoomExited(MouseEvent mouseEvent) {
        QuartosDisponiveisBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");

    }

    public void btnReservaEntered(MouseEvent mouseEvent) {
        ReservaBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnReservaExited(MouseEvent mouseEvent) {
        ReservaBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }


    public void btnFuncionariosEntered(MouseEvent mouseEvent) {
    funcionarioBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnFuncionariosExited(MouseEvent mouseEvent) {
        funcionarioBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");

    }

    public void btnAcercaDeEntered(MouseEvent mouseEvent) {
        AcercaDeBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnAcercaDeExited(MouseEvent mouseEvent) {
        AcercaDeBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }
    public void btnAddEntered(MouseEvent mouseEvent) {
        addBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnAddExited(MouseEvent mouseEvent) {
        addBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }

    public void btnUpdateEntered(MouseEvent mouseEvent) {
        updateBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }
    public void btnUpdateExited(MouseEvent mouseEvent) {
        updateBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }

    public void btnClearEntered(MouseEvent mouseEvent) {
        ClearFunBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnClearExited(MouseEvent mouseEvent) {
        clearBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }

    public void btnDeleteEntered(MouseEvent mouseEvent) {
        deleteBn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnDeleteExited(MouseEvent mouseEvent) {
        deleteBn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }

    public void btnCheckInEntered(MouseEvent mouseEvent) {
        checkInBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnCheckInExited(MouseEvent mouseEvent) {
        checkInBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }
    public void btnAddFunEntered(MouseEvent mouseEvent) {
        addBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnAddFunExited(MouseEvent mouseEvent) {
        addFunBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }

    public void btnEditFunEntered(MouseEvent mouseEvent) {
        editFunBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnEdtFunExited(MouseEvent mouseEvent) {
        editFunBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }

    public void btnDelFunEntered(MouseEvent mouseEvent) {
        delFunbtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnDelFunExited(MouseEvent mouseEvent) {
        delFunbtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }
    public void btnClearFunEntered(MouseEvent mouseEvent) {
        ClearFunBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnClearFunExited(MouseEvent mouseEvent) {
        ClearFunBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }

    public void btnDeleteReservaEntered(MouseEvent mouseEvent) {
        deleteReservaBtn.setStyle("-fx-background-color: #c0c2c1; -fx-text-fill: #000; -fx-border-radius: 4px; -fx-border-color: #c0c2c1;");
    }

    public void btnDeleteReservaExited(MouseEvent mouseEvent) {
        deleteReservaBtn.setStyle("-fx-background-color: #263F73; -fx-border-color: #fff; -fx-border-radius: 10px ; -fx-background-radius: 10px;-fx-text-fill: #fff;");
    }


    //------------------------------------------------------------------------------------------------

    public void buttonAddOnAction(ActionEvent actionEvent) {
        if (roomNumber.getText().isEmpty() || roomType.getSelectionModel().getSelectedItem()== null || roomStatus.getSelectionModel().getSelectedItem()== null || roomPrice.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("por favor preencha todos os espaços");
            alert.showAndWait();
        } else {
            try {
                Connection conn = ConexaoBD.openDB();
                if(conn != null){
                int numQuarto = Integer.parseInt(roomNumber.getText());
                String tipo = String.valueOf(roomType.getSelectionModel().getSelectedItem());
                String status = String.valueOf(roomStatus.getSelectionModel().getSelectedItem());
                double preco = Double.parseDouble(roomPrice.getText());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Coonfirmação");
                alert.setHeaderText("Deseja realmente adicionar?");
                alert.setContentText("Numero do quarto:" + numQuarto +"\nTipo de Quarto: "+ tipo +"\nStatus: " + status + "\nPreço: " + preco);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert.getButtonTypes().setAll(btnSim, btnNao);

                alert.showAndWait().ifPresent(Btn -> {
                    if (Btn == btnSim) {
                        try {
                            String sql = "INSERT INTO QuartosDisponiveis(numQuarto, tipo ,status, preco) VALUES (?,?,?,?)";
                            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            stmt.setInt(1, numQuarto);
                            stmt.setString(2, tipo);
                            stmt.setString(3, status);
                            stmt.setDouble(4, preco);
                            stmt.executeUpdate();

                            int gerarID = -1;
                            ResultSet gerarKey = stmt.getGeneratedKeys();
                            if (gerarKey.next()) {
                                gerarID = gerarKey.getInt(1);
                            }

                            Alert alertAddQuarto = new Alert(Alert.AlertType.INFORMATION);
                            alertAddQuarto.setTitle("Informação");
                            alertAddQuarto.setHeaderText("Quarto adicionado com sucesso");
                            alertAddQuarto.setContentText(null);
                            alertAddQuarto.showAndWait();

                            QuartosDisponiveis quartos = new QuartosDisponiveis(gerarID, numQuarto, tipo, status, preco);
                            tableViewQuartos.getItems().add(quartos);



                            roomNumber.clear();
                            roomType.getSelectionModel().clearSelection();
                            roomStatus.getSelectionModel().clearSelection();
                            roomPrice.clear();

                        } catch (SQLException e) {
                            Alert alertErro = new Alert(Alert.AlertType.ERROR);
                            alertErro.setTitle("ERRO");
                            alertErro.setHeaderText("Erro ao adicionar o Quarto: " + e.getMessage());
                            alertErro.showAndWait();
                        }
                    }
                });
                } else {
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

    public void buttonEditOnAction(ActionEvent actionEvent) {
        if (roomNumber.getText().isEmpty() || roomType.getSelectionModel().getSelectedItem() == null || roomStatus.getSelectionModel().getSelectedItem() == null || roomPrice.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os espaços");
            alert.showAndWait();
        } else {
            int novoId = Integer.parseInt(quartoID.getText());
            QuartosDisponiveis quartoEdit = null;

            for (QuartosDisponiveis q : Settings.getListaQuartos()) {
                if (q.getIdQuarto() == novoId) {
                    quartoEdit = q;
                    break;
                }
            }
            if (quartoEdit != null) {
                quartoEdit.setNumQuarto(Integer.parseInt(roomNumber.getText()));
                quartoEdit.setTipo((String) roomType.getSelectionModel().getSelectedItem());
                quartoEdit.setStatus((String) roomStatus.getSelectionModel().getSelectedItem());
                quartoEdit.setPreco(Double.parseDouble(roomPrice.getText()));

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editar o quarto");
                alert.setHeaderText("Deseja realmente editar o quarto?");
                alert.setContentText(null);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert.getButtonTypes().setAll(btnSim, btnNao);

                Optional<ButtonType> escolha = alert.showAndWait();
                if (escolha.isPresent() && escolha.get() == btnSim) {
                    Connection conn = null;
                    try {
                        conn = ConexaoBD.openDB();
                        if (conn != null) {
                            String Atualizar = "UPDATE QuartosDisponiveis SET numQuarto = ?, tipo = ?, status = ?, preco = ? WHERE idQuarto = ?;";
                            PreparedStatement stmt = conn.prepareStatement(Atualizar);
                            stmt.setInt(1, Integer.parseInt(roomNumber.getText()));
                            stmt.setString(2, (String) roomType.getSelectionModel().getSelectedItem());
                            stmt.setString(3, (String) roomStatus.getSelectionModel().getSelectedItem());
                            stmt.setDouble(4, Double.parseDouble(roomPrice.getText()));
                            stmt.setInt(5, novoId);

                            int atualizarBD = stmt.executeUpdate();
                            if (atualizarBD > 0) {
                                for (QuartosDisponiveis q : Settings.getListaQuartos()) {
                                    if (q.getIdQuarto() == quartoEdit.getIdQuarto()) {
                                        int forma = Settings.getListaQuartos().indexOf(q);
                                        Settings.getListaQuartos().set(forma, quartoEdit);
                                        break;
                                    }
                                }

                                Alert alertQuartoEdit = new Alert(Alert.AlertType.INFORMATION);
                                alertQuartoEdit.setTitle("CONFIRMAÇÃO!");
                                alertQuartoEdit.setHeaderText("Edição realizada com sucesso!");
                                alertQuartoEdit.setContentText("Clique no botão para continuar.");
                                alertQuartoEdit.showAndWait();
                                Settings.setEditarQuarto(null);

                                tableViewQuartos.refresh();

                                // Limpar os campos
                                roomNumber.clear();
                                roomType.getSelectionModel().clearSelection();
                                roomStatus.getSelectionModel().clearSelection();
                                roomPrice.clear();
                            } else {
                                Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                                alertEdit.setTitle("ERRO!");
                                alertEdit.setHeaderText("Não foi possível atualizar o quarto na base de dados.");
                                alertEdit.setContentText(null);
                                alertEdit.showAndWait();
                            }
                        } else {
                            Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                            alertEdit.setTitle("ERRO!");
                            alertEdit.setHeaderText("Não foi possível estabelecer uma conexão com a base de dados.");
                            alertEdit.setContentText(null);
                            alertEdit.showAndWait();
                        }
                    } catch (Exception e) {
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao editar o Quarto: " + e.getMessage());
                        alertErro.showAndWait();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }

    public void buttonClearOnAction(ActionEvent actionEvent) {
        roomNumber.setText("");
        roomStatus.getSelectionModel().clearSelection();
        roomType.getSelectionModel().clearSelection();;
        roomPrice.setText("");


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (roomNumber.getText().isEmpty() || roomType.getSelectionModel().getSelectedItem() == null || roomStatus.getSelectionModel().getSelectedItem() == null || roomPrice.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Nenhum QUarto foi selecionado.");
            alert.setContentText("Por vavor, Selecione o quarto que deseja remover.");
            alert.showAndWait();
        } else {
            Connection conn = ConexaoBD.openDB();
            if (conn != null) {
                int numQuarto = Integer.parseInt(roomNumber.getText());
                String tipo = String.valueOf(roomType.getSelectionModel().getSelectedItem());
                String status = String.valueOf(roomStatus.getSelectionModel().getSelectedItem());
                double preco = Double.parseDouble(roomPrice.getText());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação");
                alert.setHeaderText("Deseja realmente adicionar?");
                alert.setContentText("Numero do quarto:" + numQuarto + "\nTipo de Quarto: " + tipo + "\nStatus: " + status + "\nPreço: " + preco);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert.getButtonTypes().setAll(btnSim, btnNao);

                Optional<ButtonType> escolha = alert.showAndWait();
                if(escolha.isPresent() && escolha.get() == btnSim){
                    int removerId = Integer.parseInt(quartoID.getText());
                    QuartosDisponiveisDAO.removerQuarto(removerId);
                    QuartosDisponiveis Quartos = (QuartosDisponiveis) tableViewQuartos.getSelectionModel().getSelectedItem();
                    tableViewQuartos.getItems().remove(Quartos);

                    roomNumber.clear();
                    roomType.getSelectionModel().clearSelection();
                    roomStatus.getSelectionModel().clearSelection();
                    roomPrice.clear();

                    Alert alertRmQuarto = new Alert(Alert.AlertType.INFORMATION);
                    alertRmQuarto.setTitle("Informação");
                    alertRmQuarto.setHeaderText("Quarto removido com sucesso");
                    alertRmQuarto.setContentText(null);
                    alertRmQuarto.showAndWait();

                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Não foi possivel estabelecer uma conexão com a base de dados");
                alert.showAndWait();
            }
        }
    }

    public void buttoncheckInOnAction(ActionEvent actionEvent) throws Exception {
        Parent scene = FXMLLoader.load(getClass().getResource("checkIn.fxml"));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        Stage back = new Stage();
        //Definições da Stage
        back.setTitle("");
        back.setResizable(false);
        //back.initOwner(Settings.getPrimaryStage());
        back.initModality(Modality.WINDOW_MODAL);
        back.setScene(new Scene(scene));
        Window window = checkInBtn.getScene().getWindow();
        back.show();

    }
    //------------------------------------------------------------------------------------------------

    public void verQuartos(MouseEvent mouseEvent) {
        QuartosDisponiveis RoomDataVer = (QuartosDisponiveis) tableViewQuartos.getSelectionModel().getSelectedItem();
        quartoID.setText(String.valueOf(RoomDataVer.getIdQuarto()));
        roomNumber.setText(String.valueOf(RoomDataVer.getNumQuarto()));
        roomType.setValue(RoomDataVer.getTipo());
        roomStatus.setValue(RoomDataVer.getStatus());
        roomPrice.setText(String.valueOf(RoomDataVer.getPreco()));
    }


    public void procurarQuarto(KeyEvent keyEvent) {
        FilteredList<QuartosDisponiveis> filter = new FilteredList<>(Settings.listaQuartos, e -> true);

        roomSearch.textProperty().addListener((Observable, oldValue, newValue) ->{

            filter.setPredicate(predicateroom -> {
                if (newValue == null && newValue.isEmpty()) {
                    return true;
                }
                String ProcurarQ = newValue.toLowerCase();
                if (String.valueOf(predicateroom.getIdQuarto()).contains(ProcurarQ)){
                    return true;
            }else if (String.valueOf(predicateroom.getNumQuarto()).contains(ProcurarQ)){
                    return true;
                }else if (predicateroom.getTipo().toLowerCase().contains(ProcurarQ)) {
                    return true;
                } else if (predicateroom.getStatus().toLowerCase().contains(ProcurarQ)) {
                    return true;
                } else if (String.valueOf(predicateroom.getPreco()).contains(ProcurarQ)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<QuartosDisponiveis> sortList =  new SortedList<>(filter);
        sortList.comparatorProperty().bind(tableViewQuartos.comparatorProperty());
        tableViewQuartos.setItems(sortList);
    }
    //------------------------------------------------------------------------------------------------
    
    public void btnAddFunOnAction(ActionEvent actionEvent){
        if(funcionariosFirstName.getText().isEmpty() || funcionariosSecondName.getText().isEmpty() || funcionarioAge.getValue() == null || funcionarioCargo.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("por favor preencha todos os espaços");
            alert.showAndWait();
        } else {
            try{
                Connection conn = ConexaoBD.openDB();
                if(conn != null) {
                    String primeiroNome = String.valueOf(funcionariosFirstName.getText());
                    String segundoNome = String.valueOf(funcionariosSecondName.getText());
                    LocalDate idade = LocalDate.parse(String.valueOf(funcionarioAge.getValue()));
                    String cargo = String.valueOf(funcionarioCargo.getSelectionModel().getSelectedItem());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("Deseja mesmo adicionar?");
                    alert.setContentText("Primeiro nome: " + primeiroNome + "\nSegundo nome: " + segundoNome + "\nIdade: " + idade + "\nCargo:  " + cargo);
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);

                    alert.showAndWait().ifPresent(Btn -> {
                    try{
                        String sql = "INSERT INTO Funcionario(primeiroNome, segundoNome, dataNascimento, cargo) VALUES (?,?,?,?)";
                        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        stmt.setString(1, primeiroNome);
                        stmt.setString(2, segundoNome);
                        stmt.setDate(3, java.sql.Date.valueOf(idade));
                        stmt.setString(4, cargo);
                        stmt.executeUpdate();

                        int gerarID = -1;
                        ResultSet gerarKey = stmt.getGeneratedKeys();
                        if (gerarKey.next()) {
                            gerarID = gerarKey.getInt(1);
                        }
                        Alert alertAddFun = new Alert(Alert.AlertType.CONFIRMATION);
                        alertAddFun.setTitle("Informação");
                        alertAddFun.setHeaderText("Funcionario adicionado com sucesso");
                        alertAddFun.setContentText(null);
                        alertAddFun.showAndWait();

                        Funcionario f = new Funcionario(gerarID, primeiroNome,segundoNome,idade,cargo);
                        tableViewFuncionarios.getItems().add(f);

                        funcionariosFirstName.clear();
                        funcionariosSecondName.clear();
                        funcionarioAge.setValue(null);
                        funcionarioCargo.getSelectionModel().clearSelection();

                    }catch (Exception e){
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao adicionar o Funcionário: " + e.getMessage());
                        alertErro.showAndWait();
                        }
                    });
                }
                else{
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

    public void buttonEditFunOnAction(ActionEvent actionEvent) {
        if (funcionariosFirstName.getText().isEmpty() || funcionariosSecondName.getText().isEmpty() || funcionarioAge.getValue() == null || funcionarioCargo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("por favor preencha todos os espaços");
            alert.showAndWait();
        } else {
            int novoId = Integer.parseInt(funcionariosId.getText());
            Funcionario funEdit = null;

            for(Funcionario f : Settings.getListaFuncionarios()){
                if(f.getIdFun() == novoId){
                    funEdit = f;
                    break;
                }
            }
            if(funEdit != null){
                funEdit.setPrimeiroNome((String) funcionariosFirstName.getText());
                funEdit.setSegundoNome((String) funcionariosSecondName.getText());
                funEdit.setDataNascimento(funcionarioAge.getValue());
                funEdit.setCargo((String) funcionarioCargo.getSelectionModel().getSelectedItem());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("Deseja mesmo editar ?");
                alert.setContentText(null);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert.getButtonTypes().setAll(btnSim, btnNao);

                Optional<ButtonType> escolha = alert.showAndWait();
                if(escolha.isPresent() && escolha.get() == btnSim){
                    Connection conn = null;
                    try{
                        conn = ConexaoBD.openDB();
                        if(conn != null){
                            String Atualizar = "UPDATE Funcionario SET primeiroNome = ?, segundoNome = ?, dataNascimento = ?, cargo = ? WHERE idFun = ?;";
                            PreparedStatement stmt = conn.prepareStatement(Atualizar);
                            stmt.setString(1, (String) funcionariosFirstName.getText());
                            stmt.setString(2, (String) funcionariosSecondName.getText());
                            stmt.setDate(3, java.sql.Date.valueOf(funcionarioAge.getValue()));
                            stmt.setString(4, (String) funcionarioCargo.getSelectionModel().getSelectedItem());
                            stmt.setInt(5, novoId);
                            int atualizarBD = stmt.executeUpdate();
                            if(atualizarBD > 0){
                                for (Funcionario f : Settings.getListaFuncionarios()){
                                    if(f.getIdFun() == funEdit.getIdFun()){
                                        int forma = Settings.getListaFuncionarios().indexOf(f);
                                        Settings.getListaFuncionarios().set(forma, funEdit);
                                        break;
                                    }
                                }

                                Alert alertAddFun = new Alert(Alert.AlertType.CONFIRMATION);
                                alertAddFun.setTitle("Informação");
                                alertAddFun.setHeaderText("Funcionario adicionado com sucesso");
                                alertAddFun.setContentText(null);
                                alertAddFun.showAndWait();
                                Settings.setEditarFuncionario(null);

                                tableViewFuncionarios.refresh();

                                funcionariosFirstName.clear();
                                funcionariosSecondName.clear();
                                funcionarioAge.setValue(null);
                                funcionarioCargo.getSelectionModel().clearSelection();
                            }else{
                                Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                                alertEdit.setTitle("ERRO!");
                                alertEdit.setHeaderText("Nao foi possivel atualizar o funcionário na base de dados");
                                alertEdit.setContentText(null);
                                alertEdit.showAndWait();
                            }
                        } else {
                            Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                            alertEdit.setTitle("ERRO!");
                            alertEdit.setHeaderText("Não foi possível estabelecer uma conexão com a base de dados.");
                            alertEdit.setContentText(null);
                            alertEdit.showAndWait();
                        }
                    }catch (Exception e){
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao adicionar o Funcionário: " + e.getMessage());
                        alertErro.showAndWait();
                    }finally {
                        if(conn != null){
                            try{
                                conn.close();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void buttonClearFunOnAction(ActionEvent actionEvent) {
        funcionariosFirstName.setText("");
        funcionariosSecondName.setText("");
        funcionarioAge.setValue(null);
        funcionarioCargo.getSelectionModel().clearSelection();
    }

    public void buttonDeleteFunOnAction(ActionEvent actionEvent) {
        if(funcionariosFirstName.getText().isEmpty() || funcionariosSecondName.getText().isEmpty() || funcionarioAge.getValue() == null || funcionarioCargo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("por favor preencha todos os espaços");
            alert.showAndWait();
        } else {
            Connection conn = ConexaoBD.openDB();
            if(conn != null){
                String primeiroNome = String.valueOf(funcionariosFirstName.getText());
                String segundoNome = String.valueOf(funcionariosSecondName.getText());
                LocalDate idade = LocalDate.parse(String.valueOf(funcionarioAge.getValue()));
                String cargo = String.valueOf(funcionarioCargo.getSelectionModel().getSelectedItem());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("Deseja mesmo adicionar?");
                alert.setContentText("Primeiro nome: " + primeiroNome + "\nSegundo nome: " + segundoNome + "\nIdade: " + idade + "\nCargo:  " + cargo);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert.getButtonTypes().setAll(btnSim, btnNao);

                Optional<ButtonType> escolha = alert.showAndWait();
                if(escolha.isPresent() && escolha.get() == btnSim){
                    int removerID = Integer.parseInt(funcionariosId.getText());
                    FuncionarioDAO.removerFuncionario(removerID);
                    Funcionario fun = (Funcionario) tableViewFuncionarios.getSelectionModel().getSelectedItem();
                    tableViewFuncionarios.getItems().remove(fun);

                    funcionariosFirstName.clear();
                    funcionariosSecondName.clear();
                    funcionarioAge.setValue(null);
                    funcionarioCargo.getSelectionModel().clearSelection();

                    Alert alertRmFun = new Alert(Alert.AlertType.INFORMATION);
                    alertRmFun.setTitle("INFORMAÇÃO");
                    alertRmFun.setHeaderText("Funcionário removido com sucesso!!");
                    alertRmFun.setContentText(null);
                    alertRmFun.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                alert.setContentText(null);
                alert.showAndWait();
            }
        }
    }


    //------------------------------------------------------------------------------------------------

    public void verFuncionario(MouseEvent mouseEvent) {
        Funcionario funDataVer = (Funcionario) tableViewFuncionarios.getSelectionModel().getSelectedItem();
        funcionariosId.setText(String.valueOf(funDataVer.getIdFun()));
        funcionariosFirstName.setText(String.valueOf(funDataVer.getPrimeiroNome()));
        funcionariosSecondName.setText(String.valueOf(funDataVer.getSegundoNome()));
        funcionarioAge.setValue(funDataVer.getDataNascimento());
        funcionarioCargo.setValue(funDataVer.getCargo());
    }

    public void procurarFuncionario(KeyEvent keyEvent) {
    }
    //------------------------------------------------------------------------------------------------

    public void procurarReserva(KeyEvent keyEvent) {
    }
    //------------------------------------------------------------------------------------------------


    public void buttonDeleteReservaOnAction(ActionEvent actionEvent) {

    }

    //------------------------------------------------------------------------------------------------

    public void ButtonSairOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        QuartosDisponiveisRoomType();
        QuartosDisponiveisRoomStatus();
        FuncionariosCargos();
        listarFuncionarios();
    }



    //------------------------------------------------------------------------------------------------

}
