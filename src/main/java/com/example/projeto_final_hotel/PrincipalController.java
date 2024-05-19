package com.example.projeto_final_hotel;

import javafx.beans.Observable;
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
    //Atributos do ficheiro 'principal.fxml'
    @FXML
    private TableColumn roomIdNumberCollum;
    @FXML
    private  TextField quartoID;
    @FXML
    private  TextField idCheckIn;
    @FXML
    private  TextField telefoneCheckIn;
    @FXML
    private  DatePicker dataEntradaCheckIn;
    @FXML
    private  DatePicker dataSaidaCheckIn;
    @FXML
    private  TextField nomeCheckIn;
    @FXML
    private TextField roomNumberCheckIn;
    @FXML
    private  TextField roomTypeCheckIn;
    @FXML
    private  TableColumn numQuartoReservaTableColumn;
    @FXML
    private  TableColumn tipoQuartoReservaTableColumn;
    @FXML
    private  TextField emailCheckIn;
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
    //Array que armazena os diferentes tipos de quartos disponíveis
    // Preenche o ComboBox 'roomType' com tipos de quartos
    public String type[] = {"Single Room", "Double Room", "Quad Room", "King Room"};
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

    //Array que armazena os diferentes tipos de Status dos quartos
    public String status[] = {"Disponível", "Não Disponível","Ocupado"};

    public void QuartosDisponiveisRoomStatus() {
        // Cria uma lista para armazenar os estados dos quartos
        List<String> listData = new ArrayList<>();

        // Itera sobre cada estado no array 'status' e adiciona-o à lista 'listData'
        for (String data : status) {
            listData.add(data);
        }

        // Converte a lista 'listData' para um ObservableList
        ObservableList lista = FXCollections.observableArrayList(listData);
        roomStatus.setItems(lista);
    }

    //Array que armazena os diferentes tipos cargo que existem nos funcionários
    public String cargo[] = {"Superior","gerente de departamento", "supervisor da equipa","auxiliar administrativo"};

    public void FuncionariosCargos(){
        // Cria uma lista para armazenar os estados dos funcionarios
        List<String> listFunData = new ArrayList<>();

        // Itera sobre cada cargo no array 'cargo' e adiciona-o à lista 'listFunData'
        for (String data : cargo) {
            listFunData.add(data);
        }

        // Converte a lista 'listFunData' para um ObservableList
        ObservableList lista = FXCollections.observableArrayList(listFunData);

        // Define o ObservableList 'lista' como o conjunto de itens do ComboBox 'funcionarioCargo'
        funcionarioCargo.setItems(lista);
    }

    //-------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------
    public void ListarQuartos(){
        //Configura a coluna para usar a propriedade de cada propriedade dos objetos dos QuartosDisponiveis
        roomIdNumberCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, Integer>("idQuarto"));
        roomNumberCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, Integer>("numQuarto"));
        roomTypeCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, String>("tipo"));
        roomStatusCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, String>("status"));
        roomPriceCollum.setCellValueFactory(new PropertyValueFactory<QuartosDisponiveis, Double>("preco"));

        // Define os itens do 'tableViewQuartos' para os dados retornados pelo método 'listarQuartos' da classe 'QuartosDisponiveisDAO'
        tableViewQuartos.setItems(QuartosDisponiveisDAO.listarQuartos());
        // Atualiza o 'tableViewQuartos' para refletir quaisquer alterações
        tableViewQuartos.refresh();
    }

    public void listarFuncionarios(){
        //Configura a coluna para usar a propriedade de cada propriedade dos objetos dos Funcionarios
        FuncionarioIdColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("idFun"));
        FuncionarioFirstNameColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("primeiroNome"));
        FuncionarioSecondNameColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("segundoNome"));
        FuncionarioAgeColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, LocalDate>("dataNascimento"));
        FuncionarioCargoColumm.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cargo"));

        // Define os itens da 'tableViewFuncionarios' para os dados retornados pelo método 'listaFuncionario' da classe 'FuncionarioDAO'
        tableViewFuncionarios.setItems(FuncionarioDAO.listarFuncionario());

    }

    public void listarReserva(){
        //Configura a coluna para usar a propriedade de cada propriedade dos objetos da Reserva
        IDReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, Integer>("idReserva"));
        numQuartoReservaTableColumn.setCellValueFactory(new PropertyValueFactory<Reserva, Integer>("numQuarto"));
        tipoQuartoReservaTableColumn.setCellValueFactory(new PropertyValueFactory<Reserva, String>("tipo"));
        NomeReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nome"));
        NumberPhoneReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, String>("telefone"));
        EmailReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, String>("email"));
        CheckInReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, LocalDate>("dataEntrada"));
        CheckOutReservaCollumn.setCellValueFactory(new PropertyValueFactory<Reserva, LocalDate>("dataSaida"));

        // Define os itens da 'ReservaTableView' para os dados retornados pelo método 'listaReserva' da classe 'ReservaDAO'
        ReservaTableView.setItems(ReservaDAO.listarReserva());
    }

    //------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------
    //Este Método faz com que o anchorpane dos quartos seja visivél e o resto não
    public void buttonQDFormOnAction(ActionEvent actionEvent) {
        QuartosDisponiveisForm.setVisible(true);
        FuncionariosForm.setVisible(false);
        ReservaForm.setVisible(false);
        aboutForm.setVisible(false);

    }

    //Este Método faz com que o anchorpane dos funcionarios seja visivél e o resto não
    public void buttonFunFormOnAction(ActionEvent actionEvent) {
        QuartosDisponiveisForm.setVisible(false);
        FuncionariosForm.setVisible(true);
        ReservaForm.setVisible(false);
        aboutForm.setVisible(false);


    }

    //Este Método faz com que o anchorpane das Reservas seja visivél e o resto não
    public void buttonReservaFormOnAction(ActionEvent actionEvent) {
        QuartosDisponiveisForm.setVisible(false);
        FuncionariosForm.setVisible(false);
        ReservaForm.setVisible(true);
        aboutForm.setVisible(false);
    }

    //Este Método faz com que o anchorpane do Acerca De seja visivél e o resto não
    public void buttonAcercaDeFormOnAction(ActionEvent actionEvent) {
        QuartosDisponiveisForm.setVisible(false);
        FuncionariosForm.setVisible(false);
        ReservaForm.setVisible(false);
        aboutForm.setVisible(true);
    }

    //-------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------
    //O entered serve para quando eçe passa por cima o rato ele fique branco com aspeto de estar selecionado e o exited faz com que volte na cor original botão
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
    //-------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------
    public void buttonAddOnAction(ActionEvent actionEvent) {
        // Verifica se algum dos campos obrigatórios está vazio
        if (roomNumber.getText().isEmpty() || roomType.getSelectionModel().getSelectedItem()== null || roomStatus.getSelectionModel().getSelectedItem()== null || roomPrice.getText().isEmpty()) {

            // Cria e mostra uma mensagem de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("por favor preencha todos os espaços");
            alert.showAndWait();
        } else {
            try {
                // Estabelece uma conexão com a base de dados
                Connection conn = ConexaoBD.openDB();
                if(conn != null){
                    /// Obtém os valores dos campos de entrada
                    int numQuarto = Integer.parseInt(roomNumber.getText());
                    String tipo = String.valueOf(roomType.getSelectionModel().getSelectedItem());
                    String status = String.valueOf(roomStatus.getSelectionModel().getSelectedItem());
                    double preco = Double.parseDouble(roomPrice.getText());


                    // Verifica se o quarto está associado a uma reserva
                    if (QuartosDisponiveisDAO.quartoAssociado(numQuarto)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERRO");
                        alert.setHeaderText("O quarto está associado a uma reserva e não pode ser adicionado.");
                        alert.setContentText(null);
                        alert.showAndWait();
                        return; // Retorna para evitar a continuação do código

                    }else if (status.equals("Ocupado")) {
                        //mostra uma mensagem de erro caso esteja ocupado
                        Alert alertStatus = new Alert(Alert.AlertType.ERROR);
                        alertStatus.setTitle("ERRO");
                        alertStatus.setHeaderText("O quarto nao pode ser adicionado porque nao esta associado a uma reserva, logo nao pode estar Ocupado");
                        alertStatus.setContentText(null);
                        alertStatus.showAndWait();
                        return; // fiz um return para que nao continue o codigo caso este seja verdade
                    }

                    // Verifica se o quarto já existe na lista de quartos disponíveis
                    for (QuartosDisponiveis quarto : Settings.getListaQuartos()) {
                        if (quarto.getNumQuarto() == numQuarto) {

                            // Mostra um alerta de erro se o quarto já estiver na lista
                            Alert alertErro = new Alert(Alert.AlertType.ERROR);
                            alertErro.setTitle("ERRO");
                            alertErro.setHeaderText(null);
                            alertErro.setContentText("Esse numero do quarto já foi adicionado.");
                            alertErro.showAndWait();
                            return;
                        }
                    }

                    // Cria e mostra uma caixa de confirmação antes de adicionar o quarto
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Coonfirmação");
                    alert.setHeaderText("Deseja realmente adicionar?");
                    alert.setContentText("Numero do quarto:" + numQuarto +"\nTipo de Quarto: "+ tipo +"\nStatus: " + status + "\nPreço: " + preco);
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);

                    // Se o utilizador confirmar a adição do quarto
                    alert.showAndWait().ifPresent(Btn -> {
                        if (Btn == btnSim) {
                            try {
                                // Prepara a instrução SQL para inserir um novo quarto
                                String sql = "INSERT INTO QuartosDisponiveis(numQuarto, tipo ,status, preco) VALUES (?,?,?,?)";
                                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                stmt.setInt(1, numQuarto);
                                stmt.setString(2, tipo);
                                stmt.setString(3, status);
                                stmt.setDouble(4, preco);
                                stmt.executeUpdate();

                                // Obtém o ID gerado para o novo quarto
                                int gerarID = -1;
                                ResultSet gerarKey = stmt.getGeneratedKeys();
                                if (gerarKey.next()) {
                                    gerarID = gerarKey.getInt(1);
                                }


                                // Mostra uma mensagem de sucesso após adicionar o quarto
                                Alert alertAddQuarto = new Alert(Alert.AlertType.INFORMATION);
                                alertAddQuarto.setTitle("Informação");
                                alertAddQuarto.setHeaderText("Quarto adicionado com sucesso");
                                alertAddQuarto.setContentText(null);
                                alertAddQuarto.showAndWait();

                                // Adiciona o novo quarto à tabela de visualização
                                QuartosDisponiveis quartos = new QuartosDisponiveis(gerarID, numQuarto, tipo, status, preco);
                                tableViewQuartos.getItems().add(quartos);

                                // Limpa os campos de entrada após a adição
                                roomNumber.clear();
                                roomType.getSelectionModel().clearSelection();
                                roomStatus.getSelectionModel().clearSelection();
                                roomPrice.clear();

                            } catch (SQLException e) {
                                // Mostra uma mensagem de erro em caso de exceção SQL
                                Alert alertErro = new Alert(Alert.AlertType.ERROR);
                                alertErro.setTitle("ERRO");
                                alertErro.setHeaderText("Erro ao adicionar o Quarto: " + e.getMessage());
                                alertErro.showAndWait();
                            }
                        }
                    });
                    } else {
                    // Mostra uma mensagem de erro se não for possível estabelecer a conexão com a base de dados
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                        alert.setContentText(null);
                        alert.showAndWait();
                    }
            }catch (NumberFormatException e){
                    // Mostra uma mensagem de erro se os dados de entrada não estiverem no formato correto
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

    public void buttonEditOnAction(ActionEvent actionEvent) {
        // Verifica se algum dos campos obrigatórios está vazio
        if (roomNumber.getText().isEmpty() || roomType.getSelectionModel().getSelectedItem() == null || roomStatus.getSelectionModel().getSelectedItem() == null || roomPrice.getText().isEmpty()) {

            // Cria e mostra uma mensagem de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os espaços");
            alert.showAndWait();
        } else {
            // Obtém o ID do quarto a ser editado
            int novoId = Integer.parseInt(quartoID.getText());
            QuartosDisponiveis quartoEdit = null;

            // Procura o quarto a ser editado na lista de quartos disponíveis
            for (QuartosDisponiveis q : Settings.getListaQuartos()) {
                if (q.getIdQuarto() == novoId) {
                    quartoEdit = q;
                    break;
                }
            }
            if (quartoEdit != null) {
                // Verifica se o quarto está ocupado
                if (quartoEdit.getStatus().equals("Ocupado")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText(null);
                    alert.setContentText("O quarto está ocupado e não pode ser editado.");
                    alert.showAndWait();
                    return; // Aborta a operação de edição
                }
            }
            // Se o quarto for encontrado, atualiza os seus atributos com os novos valores
            if (quartoEdit != null) {
                quartoEdit.setNumQuarto(Integer.parseInt(roomNumber.getText()));
                quartoEdit.setTipo((String) roomType.getSelectionModel().getSelectedItem());
                quartoEdit.setStatus((String) roomStatus.getSelectionModel().getSelectedItem());
                quartoEdit.setPreco(Double.parseDouble(roomPrice.getText()));

                String RoomNumber = String.valueOf(quartoEdit.getNumQuarto());
                String RoomType = quartoEdit.getTipo();
                String RoomStatus = quartoEdit.getStatus();
                String RoomPrice = String.valueOf(quartoEdit.getPreco());

                if (roomNumber.getText().equals(RoomNumber) &&
                        roomType.getSelectionModel().getSelectedItem().equals(RoomType) &&
                        roomStatus.getSelectionModel().getSelectedItem().equals(RoomStatus) &&
                        roomPrice.getText().equals(RoomPrice)) {

                    // Mostra um alerta de erro se nenhum valor foi alterado
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText(null);
                    alert.setContentText("Nenhuma edição foi realizada.");
                    alert.showAndWait();
                    return;
                }

                // Cria e mostra uma caixa de confirmação antes de editar o quarto
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
                        // Estabelece uma conexão com a base de dados
                        conn = ConexaoBD.openDB();
                        if (conn != null) {
                            // Prepara a instrução SQL para atualizar o quarto na base de dados
                            String Atualizar = "UPDATE QuartosDisponiveis SET numQuarto = ?, tipo = ?, status = ?, preco = ? WHERE idQuarto = ?;";

                            PreparedStatement stmt = conn.prepareStatement(Atualizar);
                            stmt.setInt(1, Integer.parseInt(roomNumber.getText()));
                            stmt.setString(2, (String) roomType.getSelectionModel().getSelectedItem());
                            stmt.setString(3, (String) roomStatus.getSelectionModel().getSelectedItem());
                            stmt.setDouble(4, Double.parseDouble(roomPrice.getText()));
                            stmt.setInt(5, novoId);

                            int atualizarBD = stmt.executeUpdate();
                            if (atualizarBD > 0) {
                                // Atualiza a lista de quartos com os novos dados
                                for (QuartosDisponiveis q : Settings.getListaQuartos()) {
                                    if (q.getIdQuarto() == quartoEdit.getIdQuarto()) {
                                        int forma = Settings.getListaQuartos().indexOf(q);
                                        Settings.getListaQuartos().set(forma, quartoEdit);
                                        break;
                                    }
                                }

                                // Mostra uma mensagem de sucesso após editar o quarto
                                Alert alertQuartoEdit = new Alert(Alert.AlertType.INFORMATION);
                                alertQuartoEdit.setTitle("CONFIRMAÇÃO!");
                                alertQuartoEdit.setHeaderText("Edição realizada com sucesso!");
                                alertQuartoEdit.setContentText("Clique no botão para continuar.");
                                alertQuartoEdit.showAndWait();
                                Settings.setEditarQuarto(null);

                                // Atualiza a visualização da tabela de quartos
                                tableViewQuartos.refresh();

                                // Limpar os campos
                                roomNumber.clear();
                                roomType.getSelectionModel().clearSelection();
                                roomStatus.getSelectionModel().clearSelection();
                                roomPrice.clear();
                            } else {
                                // Mostra uma mensagem de erro se a atualização na base de dados falhar
                                Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                                alertEdit.setTitle("ERRO!");
                                alertEdit.setHeaderText("Não foi possível atualizar o quarto na base de dados.");
                                alertEdit.setContentText(null);
                                alertEdit.showAndWait();
                            }
                        } else {
                            // Mostra uma mensagem de erro se não for possível estabelecer a conexão com a base de dados
                            Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                            alertEdit.setTitle("ERRO!");
                            alertEdit.setHeaderText("Não foi possível estabelecer uma conexão com a base de dados.");
                            alertEdit.setContentText(null);
                            alertEdit.showAndWait();
                        }
                    } catch (Exception e) {
                        // Mostra uma mensagem de erro em caso de exceção
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao editar o Quarto: " + e.getMessage());
                        alertErro.showAndWait();
                    } finally {
                        // Fecha a conexão com a base de dados
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
        //Limpa as comboBoxes e TextFields dos Quartos
        roomNumber.setText("");
        roomStatus.getSelectionModel().clearSelection();
        roomType.getSelectionModel().clearSelection();;
        roomPrice.setText("");


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        // Verifica se algum dos campos obrigatórios está vazio
        if (roomNumber.getText().isEmpty() || roomType.getSelectionModel().getSelectedItem() == null || roomStatus.getSelectionModel().getSelectedItem() == null || roomPrice.getText().isEmpty()) {

            // Cria e mostra uma mensagem de erro se algum campo estiver vazio
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Nenhum QUarto foi selecionado.");
            alert.setContentText("Por vavor, Selecione o quarto que deseja remover.");
            alert.showAndWait();
        } else {
            // Estabelece uma conexão com a base de dados
            Connection conn = ConexaoBD.openDB();
            if (conn != null) {
                // Obtém os dados do quarto a ser removido
                int numQuarto = Integer.parseInt(roomNumber.getText());
                String tipo = String.valueOf(roomType.getSelectionModel().getSelectedItem());
                String status = String.valueOf(roomStatus.getSelectionModel().getSelectedItem());
                double preco = Double.parseDouble(roomPrice.getText());

                // Cria e mostra uma caixa de confirmação antes de remover o quarto
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação");
                alert.setHeaderText("Deseja realmente remover?");
                alert.setContentText("Numero do quarto:" + numQuarto + "\nTipo de Quarto: " + tipo + "\nStatus: " + status + "\nPreço: " + preco);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert.getButtonTypes().setAll(btnSim, btnNao);

                // Processa a escolha do utilizador
                Optional<ButtonType> escolha = alert.showAndWait();
                if(escolha.isPresent() && escolha.get() == btnSim){
                    // Obtém o ID do quarto a ser removido
                    int removerId = Integer.parseInt(quartoID.getText());

                    // Remove o quarto da base de dados utilizando o DAO
                    QuartosDisponiveisDAO.removerQuarto(removerId);

                    // Remove o quarto da tabela de visualização
                    QuartosDisponiveis Quartos = (QuartosDisponiveis) tableViewQuartos.getSelectionModel().getSelectedItem();
                    tableViewQuartos.getItems().remove(Quartos);
                    removerReservaAssociada(numQuarto);

                    // Limpa os campos de entrada após a remoção
                    roomNumber.clear();
                    roomType.getSelectionModel().clearSelection();
                    roomStatus.getSelectionModel().clearSelection();
                    roomPrice.clear();

                    // Mostra uma mensagem de sucesso após remover o quarto
                    Alert alertRmQuarto = new Alert(Alert.AlertType.INFORMATION);
                    alertRmQuarto.setTitle("Informação");
                    alertRmQuarto.setHeaderText("Quarto removido com sucesso");
                    alertRmQuarto.setContentText(null);
                    alertRmQuarto.showAndWait();

                }
            }else {
                // Mostra uma mensagem de erro se não for possível estabelecer a conexão com a base de dados
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Não foi possivel estabelecer uma conexão com a base de dados");
                alert.showAndWait();
            }
        }
    }

    public void removerReservaAssociada(int numQuarto){
        List<Reserva> reservas = new ArrayList<>();
        for(Reserva r : Settings.getListaReserva()){
            if(r.getNumQuarto() == numQuarto){
                // Adiciona a reserva à lista de reservas associadas
                reservas.add(r);
            }
        }

        for(Reserva r : reservas){
            // Remove as reservas associadas da visualização da tabela
            ReservaDAO.removerReserva(r.getIdReserva());
            // Remove a reserva da lista das reservas
            Settings.listaReserva.remove(r);
        }
        // Remove as reservas associadas da visualização da tabela
        ReservaTableView.getItems().remove(reservas);
    }

    //-------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------------------------
    public void buttoncheckInOnAction(ActionEvent actionEvent) throws Exception {
        // Carrega o layout 'checkIn.fxml' que define a interface da tela de check-in
        Parent scene = FXMLLoader.load(getClass().getResource("checkIn.fxml"));
        //primaryStage.initStyle(StageStyle.UNDECORATED);

        // Cria uma nova instância de Stage (janela)
        Stage back = new Stage();

        //Definições da Stage
        back.setTitle(""); // Define o título da janel
        back.setResizable(false);// Define que a janela não pode ser redimensionada
        //back.initOwner(Settings.getPrimaryStage());

        // Inicializa a nova janela como modal, bloqueando interações com outras janelas enquanto esta está aberta
        back.initModality(Modality.WINDOW_MODAL);
        // Define a cena da nova janela com o layout carregado
        back.setScene(new Scene(scene));
        // Obtém a janela atual a partir do botão que foi clicado
        Window window = checkInBtn.getScene().getWindow();

        // Mostra a nova janela
        back.show();

    }
    public void buttonDeleteReservaOnAction(ActionEvent actionEvent) throws SQLException {
        // Verifica se todos os campos obrigatórios estão preenchidos
        if (nomeCheckIn.getText().isEmpty() || telefoneCheckIn.getText().isEmpty() || emailCheckIn.getText().isEmpty() || roomTypeCheckIn.getText().isEmpty() || roomNumberCheckIn.getText().isEmpty() || dataEntradaCheckIn.getValue() == null || dataSaidaCheckIn.getValue() == null){

            //Mostra uma mensagem de erro caso não tenha selecionado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Selecione a Reserva que deseja eliminar");
            alert.showAndWait();
        } else{
            //Abre a conexão com a base de dados
            Connection conn = ConexaoBD.openDB();
            if (conn != null){

                // Obtém os valores dos campos
                int numQuarto = Integer.parseInt(String.valueOf(roomNumberCheckIn.getText()));
                String tipo = String.valueOf(roomTypeCheckIn.getText());
                String nome = String.valueOf(nomeCheckIn.getText());
                String telefone = String.valueOf(telefoneCheckIn.getText());
                String email = String.valueOf(emailCheckIn.getText());
                LocalDate dataEntrada = LocalDate.parse(String.valueOf(dataEntradaCheckIn.getValue()));
                LocalDate dataSaida = LocalDate.parse(String.valueOf(dataSaidaCheckIn.getValue()));

                //Mostra um alerta de confirmaçao se realmente deseja remover
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("Deseja realmente remover a Reserva?");
                alert.setContentText("NUMERO DO QUARTO: "+ numQuarto+"\nTIPO DE QUARTO: "+tipo+"\nNOME: " + nome + "\nTELEFONE: " + telefone + "\nE-MAIL: " + email + "\nDATA DE ENTRADA: "+ dataEntrada + "\nDATA DE SAIDA: " + dataSaida);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert.getButtonTypes().setAll(btnSim, btnNao);

                //Caso escolha sim entao ele vai buscar o id e remove da base de dados
                Optional<ButtonType> escolha = alert.showAndWait();
                if(escolha.isPresent() && escolha.get() == btnSim){
                    int removerID = Integer.parseInt(idCheckIn.getText());
                    ReservaDAO.removerReserva(removerID);

                    // Exibe um alerta de sucesso
                    Alert alertRmReserva = new Alert(Alert.AlertType.INFORMATION);
                    alertRmReserva.setTitle("INFORMAÇÃO");
                    alertRmReserva.setHeaderText("Reserva removida com sucesso!");
                    alertRmReserva.setContentText(null);
                    alertRmReserva.showAndWait();

                    Reserva reserva = (Reserva) ReservaTableView.getSelectionModel().getSelectedItem();
                    ReservaTableView.getItems().remove(reserva);

                    // Atualiza o status do quarto para "Disponível"
                    String sqlUpdateStatus = "UPDATE QuartosDisponiveis SET status ='Disponível' WHERE numQuarto ='"+numQuarto+"';";
                    Statement st = conn.createStatement();
                    st.executeUpdate(sqlUpdateStatus);

                    //Dei clear na lista depois listei a mesma em seguida para fazer as alterações na TableView dos quartos
                    Settings.getListaQuartos().clear();
                    QuartosDisponiveisDAO.listarQuartos();
                }
            }else{
                //Mostra o erro caso não seja possivel estabelecer a ligação com a base de dados
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Não foi possivel estabelecer uma ligação com a base de dados");
                alert.showAndWait();
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------------------------
    
    public void btnAddFunOnAction(ActionEvent actionEvent){
        // Verifica se todos os campos obrigatórios estão preenchidos
        if(funcionariosFirstName.getText().isEmpty() || funcionariosSecondName.getText().isEmpty() || funcionarioAge.getValue() == null || funcionarioCargo.getSelectionModel().getSelectedItem() == null){

            //Mostra uma mensagem de erro caso não tenha preenchido os espaços todos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("por favor preencha todos os espaços");
            alert.showAndWait();
        } else {
            try{
                //Abre a conexão com a base de dados
                Connection conn = ConexaoBD.openDB();
                if(conn != null) {
                    // Obtém os valores dos campos
                    String primeiroNome = String.valueOf(funcionariosFirstName.getText());
                    String segundoNome = String.valueOf(funcionariosSecondName.getText());
                    LocalDate idade = LocalDate.parse(String.valueOf(funcionarioAge.getValue()));
                    String cargo = String.valueOf(funcionarioCargo.getSelectionModel().getSelectedItem());

                    // Exibe uma mensagem de confirmação
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMAÇÃO");
                    alert.setHeaderText("Deseja mesmo adicionar?");
                    alert.setContentText("Primeiro nome: " + primeiroNome + "\nSegundo nome: " + segundoNome + "\nIdade: " + idade + "\nCargo:  " + cargo);
                    ButtonType btnSim = new ButtonType("SIM");
                    ButtonType btnNao = new ButtonType("NÃO");
                    alert.getButtonTypes().setAll(btnSim, btnNao);

                    // Se o utilizador confirmar, insere o funcionário na base de dados
                    alert.showAndWait().ifPresent(Btn -> {
                    try{
                        String sql = "INSERT INTO Funcionario(primeiroNome, segundoNome, dataNascimento, cargo) VALUES (?,?,?,?)";
                        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        stmt.setString(1, primeiroNome);
                        stmt.setString(2, segundoNome);
                        stmt.setDate(3, java.sql.Date.valueOf(idade));
                        stmt.setString(4, cargo);
                        stmt.executeUpdate();

                        // Obtém o ID gerado para o novo funcionário
                        int gerarID = -1;
                        ResultSet gerarKey = stmt.getGeneratedKeys();
                        if (gerarKey.next()) {
                            gerarID = gerarKey.getInt(1);
                        }
                        // Mostra uma mensagem de sucesso
                        Alert alertAddFun = new Alert(Alert.AlertType.INFORMATION);
                        alertAddFun.setTitle("Informação");
                        alertAddFun.setHeaderText("Funcionario adicionado com sucesso");
                        alertAddFun.setContentText(null);
                        alertAddFun.showAndWait();

                        // Adiciona o novo funcionário à tabela
                        Funcionario f = new Funcionario(gerarID, primeiroNome,segundoNome,idade,cargo);
                        tableViewFuncionarios.getItems().add(f);

                        // Limpa os campos
                        funcionariosFirstName.clear();
                        funcionariosSecondName.clear();
                        funcionarioAge.setValue(null);
                        funcionarioCargo.getSelectionModel().clearSelection();

                    }catch (Exception e){
                        // Mostra uma mensagem de erro caso ocorra um problema ao adicionar o funcionário
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao adicionar o Funcionário: " + e.getMessage());
                        alertErro.showAndWait();
                        }
                    });
                }
                else{
                    // Mostra uma mensagem de erro caso não consiga estabelecer uma conexão com a base de dados
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
                }catch (NumberFormatException e){
                // Mostra uma mensagem de erro caso ocorra um problema com a formatação dos dados
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

    public void buttonEditFunOnAction(ActionEvent actionEvent) {
        // Verifica se todos os campos obrigatórios estão preenchidos
        if (funcionariosFirstName.getText().isEmpty() || funcionariosSecondName.getText().isEmpty() || funcionarioAge.getValue() == null || funcionarioCargo.getSelectionModel().getSelectedItem() == null) {

            //Mostra uma mensagem de erro caso não tenha preenchido os espaços todos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("por favor preencha todos os espaços");
            alert.showAndWait();
        } else {

            // Obtém o ID do funcionário a ser editado
            int novoId = Integer.parseInt(funcionariosId.getText());
            Funcionario funEdit = null;

            // Encontra o funcionário na lista de funcionários
            for(Funcionario f : Settings.getListaFuncionarios()){
                if(f.getIdFun() == novoId){
                    funEdit = f;
                    break;
                }
            }

            // Armazena os valores originais dos campos
            String FunPrimeiroNome = funEdit.getPrimeiroNome();
            String FunSegundoNome = funEdit.getSegundoNome();
            String FunData = String.valueOf(funEdit.getDataNascimento());
            String FunCargo = funEdit.getCargo();

            if(funEdit != null){
                // Armazena os valores originais dos campos
                String FirstName = funEdit.getPrimeiroNome();
                String SecondName = funEdit.getSegundoNome();
                LocalDate Age = funEdit.getDataNascimento();
                String Cargo = funEdit.getCargo();

                // Verifica se os valores atuais são diferentes dos valores originais
                if (funcionariosFirstName.getText().equals(FirstName) &&
                        funcionariosSecondName.getText().equals(SecondName) &&
                        funcionarioAge.getValue().equals(Age) &&
                        funcionarioCargo.getSelectionModel().getSelectedItem().equals(Cargo)) {

                    // Mostra um alerta de erro se nenhum valor foi alterado
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText(null);
                    alert.setContentText("Nenhuma edição foi realizada.");
                    alert.showAndWait();
                    return;
                }

                // Atualiza os dados do funcionário
                funEdit.setPrimeiroNome((String) funcionariosFirstName.getText());
                funEdit.setSegundoNome((String) funcionariosSecondName.getText());
                funEdit.setDataNascimento(funcionarioAge.getValue());
                funEdit.setCargo((String) funcionarioCargo.getSelectionModel().getSelectedItem());


                // Exibe uma mensagem de confirmação
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
                        // Abre a conexão com a base de dados
                        conn = ConexaoBD.openDB();
                        if(conn != null){
                            // Prepara a consulta de atualização
                            String Atualizar = "UPDATE Funcionario SET primeiroNome = ?, segundoNome = ?, dataNascimento = ?, cargo = ? WHERE idFun = ?;";
                            PreparedStatement stmt = conn.prepareStatement(Atualizar);
                            stmt.setString(1, (String) funcionariosFirstName.getText());
                            stmt.setString(2, (String) funcionariosSecondName.getText());
                            stmt.setDate(3, java.sql.Date.valueOf(funcionarioAge.getValue()));
                            stmt.setString(4, (String) funcionarioCargo.getSelectionModel().getSelectedItem());
                            stmt.setInt(5, novoId);

                            // Executa a atualização na base de dados
                            int atualizarBD = stmt.executeUpdate();
                            if(atualizarBD > 0){
                                // Atualiza a lista de funcionários
                                for (Funcionario f : Settings.getListaFuncionarios()){
                                    if(f.getIdFun() == funEdit.getIdFun()){
                                        int forma = Settings.getListaFuncionarios().indexOf(f);
                                        Settings.getListaFuncionarios().set(forma, funEdit);
                                        break;
                                    }
                                }

                                // Mostra uma mensagem de sucesso
                                Alert alertAddFun = new Alert(Alert.AlertType.INFORMATION);
                                alertAddFun.setTitle("Informação");
                                alertAddFun.setHeaderText("Funcionario editado com sucesso");
                                alertAddFun.setContentText(null);
                                alertAddFun.showAndWait();
                                Settings.setEditarFuncionario(null);

                                // Atualiza a tabela de funcionários
                                tableViewFuncionarios.refresh();

                                // Limpa os campos
                                funcionariosFirstName.clear();
                                funcionariosSecondName.clear();
                                funcionarioAge.setValue(null);
                                funcionarioCargo.getSelectionModel().clearSelection();
                            }else{
                                // Mostra uma mensagem de erro caso a atualização falhe
                                Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                                alertEdit.setTitle("ERRO!");
                                alertEdit.setHeaderText("Nao foi possivel editar o funcionário na base de dados");
                                alertEdit.setContentText(null);
                                alertEdit.showAndWait();
                            }
                        } else {
                            // Mostra uma mensagem de erro caso não consiga estabelecer uma conexão com a base de dados
                            Alert alertEdit = new Alert(Alert.AlertType.ERROR);
                            alertEdit.setTitle("ERRO!");
                            alertEdit.setHeaderText("Não foi possível estabelecer uma conexão com a base de dados.");
                            alertEdit.setContentText(null);
                            alertEdit.showAndWait();
                        }
                    }catch (Exception e){
                        // Fecha a conexão com a base de dados
                        Alert alertErro = new Alert(Alert.AlertType.ERROR);
                        alertErro.setTitle("ERRO");
                        alertErro.setHeaderText("Erro ao adicionar o Funcionário: " + e.getMessage());
                        alertErro.showAndWait();
                    }finally {
                        // Fecha a conexão com a base de dados
                        if(conn != null){
                            try{
                                conn.close();
                            }catch (Exception e){
                                //Mostra mensagem de erro caso necessário
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void buttonClearFunOnAction(ActionEvent actionEvent) {
        //Limpa os textFileds DatePicker e combobox
        funcionariosFirstName.setText("");
        funcionariosSecondName.setText("");
        funcionarioAge.setValue(null);
        funcionarioCargo.getSelectionModel().clearSelection();
    }

    public void buttonDeleteFunOnAction(ActionEvent actionEvent) {
        // Verifica se todos os campos obrigatórios estão preenchidos
        if(funcionariosFirstName.getText().isEmpty() || funcionariosSecondName.getText().isEmpty() || funcionarioAge.getValue() == null || funcionarioCargo.getSelectionModel().getSelectedItem() == null) {

            // Mostra uma mensagem de erro caso não tenha preenchido todos os espaços
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("por favor preencha todos os espaços");
            alert.showAndWait();
        } else {
            // Abre a conexão com a base de dados
            Connection conn = ConexaoBD.openDB();
            if(conn != null){
                // Obtém os dados do quarto a ser removido
                String primeiroNome = String.valueOf(funcionariosFirstName.getText());
                String segundoNome = String.valueOf(funcionariosSecondName.getText());
                LocalDate idade = LocalDate.parse(String.valueOf(funcionarioAge.getValue()));
                String cargo = String.valueOf(funcionarioCargo.getSelectionModel().getSelectedItem());

                // Exibe uma mensagem de confirmação
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMAÇÃO");
                alert.setHeaderText("Deseja mesmo adicionar?");
                alert.setContentText("Primeiro nome: " + primeiroNome + "\nSegundo nome: " + segundoNome + "\nIdade: " + idade + "\nCargo:  " + cargo);
                ButtonType btnSim = new ButtonType("SIM");
                ButtonType btnNao = new ButtonType("NÃO");
                alert.getButtonTypes().setAll(btnSim, btnNao);

                //Caso o utilizador escolha que deseja remover entao ele vai remover o Funcionario atraves do removerID e depois ele vai atualizar a tableview
                Optional<ButtonType> escolha = alert.showAndWait();
                if(escolha.isPresent() && escolha.get() == btnSim){
                    int removerID = Integer.parseInt(funcionariosId.getText());
                    FuncionarioDAO.removerFuncionario(removerID);
                    Funcionario fun = (Funcionario) tableViewFuncionarios.getSelectionModel().getSelectedItem();
                    tableViewFuncionarios.getItems().remove(fun);

                    // Limpa os campos do formulário
                    funcionariosFirstName.clear();
                    funcionariosSecondName.clear();
                    funcionarioAge.setValue(null);
                    funcionarioCargo.getSelectionModel().clearSelection();

                    // Mostra uma mensagem de sucesso
                    Alert alertRmFun = new Alert(Alert.AlertType.INFORMATION);
                    alertRmFun.setTitle("INFORMAÇÃO");
                    alertRmFun.setHeaderText("Funcionário removido com sucesso!!");
                    alertRmFun.setContentText(null);
                    alertRmFun.showAndWait();
                }
            }else {
                // Mostra uma mensagem de erro caso não consiga estabelecer uma conexão com a base de dados
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Nao foi possivel estabelecer uma conexão com a base de dados");
                alert.setContentText(null);
                alert.showAndWait();
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------

    public void verQuartos(MouseEvent mouseEvent) {
        //Quando selecionar na table view ele vai aparecer nas comboboxes e nos textFields
        QuartosDisponiveis RoomDataVer = (QuartosDisponiveis) tableViewQuartos.getSelectionModel().getSelectedItem();
        quartoID.setText(String.valueOf(RoomDataVer.getIdQuarto()));
        roomNumber.setText(String.valueOf(RoomDataVer.getNumQuarto()));
        roomType.setValue(RoomDataVer.getTipo());
        roomStatus.setValue(RoomDataVer.getStatus());
        roomPrice.setText(String.valueOf(RoomDataVer.getPreco()));
    }
    public void verFuncionario(MouseEvent mouseEvent) {
        //Quando selecionar na table view ele vai aparecer nas comboboxes,textFields e no datePicker
        Funcionario funDataVer = (Funcionario) tableViewFuncionarios.getSelectionModel().getSelectedItem();
        funcionariosId.setText(String.valueOf(funDataVer.getIdFun()));
        funcionariosFirstName.setText(String.valueOf(funDataVer.getPrimeiroNome()));
        funcionariosSecondName.setText(String.valueOf(funDataVer.getSegundoNome()));
        funcionarioAge.setValue(funDataVer.getDataNascimento());
        funcionarioCargo.setValue(funDataVer.getCargo());
    }
    public void verReserva(MouseEvent mouseEvent) {
        //Quando selecionar na table view ele vai aparecer nas comboboxes,TextFields e datePickers
        Reserva resDataVer = (Reserva) ReservaTableView.getSelectionModel().getSelectedItem();
        idCheckIn.setText(String.valueOf(resDataVer.getIdReserva()));
        roomNumberCheckIn.setText(String.valueOf(resDataVer.getNumQuarto()));
        roomTypeCheckIn.setText(String.valueOf(resDataVer.getTipo()));
        nomeCheckIn.setText(String.valueOf(resDataVer.getNome()));
        telefoneCheckIn.setText(String.valueOf(resDataVer.getTelefone()));
        emailCheckIn.setText(String.valueOf(resDataVer.getEmail()));
        dataEntradaCheckIn.setValue(resDataVer.getDataEntrada());
        dataSaidaCheckIn.setValue(resDataVer.getDataSaida());
    }

    //-------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------------------------
    public void procurarQuarto(KeyEvent keyEvent) {
        // Cria uma lista filtrada a partir da lista de quartos disponíveis
        FilteredList<QuartosDisponiveis> filter = new FilteredList<>(Settings.listaQuartos, e -> true);

        // Adiciona um listener ao campo de busca (roomSearch)
        roomSearch.textProperty().addListener((Observable, oldValue, newValue) ->{

            // Define o predicado (condição) para o filtro
            filter.setPredicate(predicateroom -> {
                // Se o campo de busca estiver vazio, mostra todos os quartos
                if (newValue == null && newValue.isEmpty()) {
                    return true;
                }
                // Converte o texto de busca para minúsculas para uma comparação case-insensitive
                String ProcurarQ = newValue.toLowerCase();
                // Verifica se o ID do quarto contém o texto de busca
                if (String.valueOf(predicateroom.getIdQuarto()).contains(ProcurarQ)){
                    return true;
                    // Verifica se o número do quarto contém o texto de busca
                }else if (String.valueOf(predicateroom.getNumQuarto()).contains(ProcurarQ)){
                    return true;
                    // Verifica se o tipo do quarto contém o texto de busca
                }else if (predicateroom.getTipo().toLowerCase().contains(ProcurarQ)) {
                    return true;
                    // Verifica se o status do quarto contém o texto de busca
                } else if (predicateroom.getStatus().toLowerCase().contains(ProcurarQ)) {
                    return true;
                    // Verifica se o preço do quarto contém o texto de busca
                } else if (String.valueOf(predicateroom.getPreco()).contains(ProcurarQ)) {
                    return true;
                }
                // Caso nenhuma das condições acima seja satisfeita, o quarto não será incluído no filtro
                return false;
            });
        });
        // Cria uma lista ordenada a partir da lista filtrada
        SortedList<QuartosDisponiveis> sortList =  new SortedList<>(filter);

        // Vincula a propriedade de comparação da tabela no SortedList
        sortList.comparatorProperty().bind(tableViewQuartos.comparatorProperty());
        // Define o SortedList como os items da tabela
        tableViewQuartos.setItems(sortList);
    }
    public void procurarFuncionario(KeyEvent keyEvent) {
        // Cria uma lista filtrada a partir da lista de funcionários
        FilteredList<Funcionario> filter = new FilteredList<>(Settings.getListaFuncionarios(), e -> true);

        // Adiciona um listener ao campo de busca (FuncionarioSearch)
        FuncionarioSearch.textProperty().addListener((Observable, oldValue, newValue) ->{

            // Define o predicado (condição) para o filtro
            filter.setPredicate(predicatefuncionario ->{
                // Se o campo de busca estiver vazio, mostra todos os funcionários
                if(newValue == null && newValue.isEmpty()){
               return true;
                }
                // Converte o texto de busca para minúsculas para uma comparação case-insensitive
                String ProcurarF = newValue.toLowerCase();

                // Verifica se o ID do funcionário contém o texto de busca
                if(String.valueOf(predicatefuncionario.getIdFun()).contains(ProcurarF)){
                    return  true;
                    // Verifica se o primeiro nome do funcionário contém o texto de busca
                } else if(String.valueOf(predicatefuncionario.getPrimeiroNome()).toLowerCase().contains(ProcurarF)){
                    return true;
                    // Verifica se o segundo nome do funcionário contém o texto de busca
                } else if (String.valueOf(predicatefuncionario.getSegundoNome()).toLowerCase().contains(ProcurarF)){
                    return true;
                    // Verifica se a data de nascimento do funcionário contém o texto de busca
                } else if (String.valueOf(predicatefuncionario.getDataNascimento()).contains(ProcurarF)){
                  return true;
                    // Verifica se o cargo do funcionário contém o texto de busca
                }else if (String.valueOf(predicatefuncionario.getCargo()).toLowerCase().contains(ProcurarF));
                return false;
            });
        });
        // Cria uma lista ordenada a partir da lista filtrada
        SortedList<Funcionario> sortedList = new SortedList<>(filter);

        // Vincula a propriedade de comparação da tabela no SortedList
        sortedList.comparatorProperty().bind(tableViewFuncionarios.comparatorProperty());

        // Define o SortedList como os items da tabela
        tableViewFuncionarios.setItems(sortedList);
    }
    public void procurarReserva(KeyEvent keyEvent) {
        // Cria uma lista filtrada a partir da lista da Reserva
        FilteredList<Reserva> filter = new FilteredList<>(Settings.listaReserva, e -> true);

        // Adiciona um listener ao campo de busca (searchReserva)
        searchReserva.textProperty().addListener((Observable, oldvalue, newvalue ) ->{
           filter.setPredicate(predicateReserva ->{
               // Define o predicado (condição) para o filtro
               if(newvalue == null && newvalue.isEmpty()) {
                    return true;
               }
               // Se o campo de busca estiver vazio, mostra todas as reservas
               String ProcurarR = newvalue.toLowerCase();

               // Converte o texto de busca para minúsculas para uma comparação case-insensitive
               if(String.valueOf(predicateReserva.getIdReserva()).contains(ProcurarR)){
                   return true;
                   // Verifica se o ID da reserva contém o texto de busca
               }else if(String.valueOf(predicateReserva.getNumQuarto()).contains(ProcurarR)){
                   return true;
                   // Verifica se o número do quarto contém o texto de busca
               } else if(String.valueOf(predicateReserva.getTipo()).contains(ProcurarR)){
                   return true;
                   // Verifica se o nome do hóspede contém o texto de busca
               } else if (String.valueOf(predicateReserva.getNome()).contains(ProcurarR)){
                   return true;
                   // Verifica se o telefone contém o texto de busca
               } else if(String.valueOf(predicateReserva.getTelefone()).contains(ProcurarR)){
                   return true;
                   // Verifica se o email contém o texto de busca
               } else if (String.valueOf(predicateReserva.getEmail()).contains(ProcurarR)){
                   return true;
                   // Verifica se a data de entrada contém o texto de busca
               } else if (String.valueOf(predicateReserva.getDataEntrada()).contains(ProcurarR)){
                   return true;
                   // Verifica se a data de saída contém o texto de busca
               } else if (String.valueOf(predicateReserva.getDataSaida()).contains(ProcurarR)) {
                   return true;
               }
               // Caso nenhuma das condições acima seja satisfeita, a reserva não será incluída no filtro
                return false;
           });
        });

        // Cria uma lista ordenada a partir do SortedList
        SortedList<Reserva> sortedList = new SortedList<>(filter);

        // Vincula a propriedade de comparação da tabela ao SortedList
        sortedList.comparatorProperty().bind(ReservaTableView.comparatorProperty());

        // Define o SortedLIst como os itens da tabela
        ReservaTableView.setItems(sortedList);
    }
    //-------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------------------------

    public void ButtonSairOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSair.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMAÇÃO");
        alert.setHeaderText("Deseja realmente sair?");
        alert.setContentText(null);
        alert.showAndWait();

        Alert alertSair = new Alert(Alert.AlertType.INFORMATION);
        alertSair.setTitle("INFORMAÇÃO");
        alertSair.setHeaderText("OBRIGADO POR TER VIDO!!");
        alertSair.setContentText(null);
        alertSair.showAndWait();
        stage.close();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        QuartosDisponiveisRoomType();
        QuartosDisponiveisRoomStatus();
        FuncionariosCargos();
        listarFuncionarios();
        listarReserva();
        ListarQuartos();
    }
    //------------------------------------------------------------------------------------------------

}






