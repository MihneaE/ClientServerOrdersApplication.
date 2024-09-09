module com.example.licenta__ {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ClientController to javafx.fxml;
    exports ClientController;
}