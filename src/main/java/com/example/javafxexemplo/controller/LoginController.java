package com.example.javafxexemplo.controller;

import com.example.javafxexemplo.HelloApplication;
import com.example.javafxexemplo.model.Usuario;
import com.example.javafxexemplo.model.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    //tem que criar atributo (caracterista da classe)
    @FXML
    TextField usuarioField;
    @FXML
    PasswordField senhaField;
    @FXML
    Label labelEntrar;


    // Tem que sempre fazer uma anotação, vai sempre garantir que vai acessar o método, para isso que serve o @FXML
    @FXML
    public void entrar() throws IOException, SQLException {

        Usuario usuarioLogin = new Usuario(usuarioField.getText(),senhaField.getText());
        boolean usuarioExiste = new UsuarioDAO().existe(usuarioLogin);

        if (usuarioExiste) {
            //Usuario existe
            labelEntrar.setText("Entrando");
            HelloApplication.setRoot("main-view");
        } else {
            //Usuario não existe
            System.out.println("Usuario e ou senha incorretos! Tente novamente!");
        }
    }
}

// if (usuarioField.getText().equals("Fernanda") && senhaField.getText().equals("123")) {
//            System.out.println("Entrando...");
//            labelEntrar.setText("Entrando...");
//Connection connection = DriverManager.getConnection( //
//                "jdbc:mysql://localhost:3306/javafxcedup2023",//
//                "root",
//                "");
//
//        String sql = "select count(*) from usuario " //
//                + "where nome = '" + usuarioField.getText() + "' " //
//                + "AND senha = '" + senhaField.getText() + "'";

//DAO é um objeto para acessar dados
