module com.example.javafxexemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxexemplo to javafx.fxml;
    exports com.example.javafxexemplo;
    exports com.example.javafxexemplo.controller;
    opens com.example.javafxexemplo.controller to javafx.fxml;
    exports com.example.javafxexemplo.model;
    opens com.example.javafxexemplo.model to javafx.fxml;

}