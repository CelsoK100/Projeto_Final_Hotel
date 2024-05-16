module com.example.projeto_final_hotel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projeto_final_hotel to javafx.fxml;
    exports com.example.projeto_final_hotel;
}