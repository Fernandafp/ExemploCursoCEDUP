package com.example.javafxexemplo.controller;

import com.example.javafxexemplo.HelloApplication;
import javafx.fxml.FXML;

import java.io.IOException;

public class PrincipalController {
    @FXML
    public void produto() throws IOException {
        System.out.println("Residente Evil");
        HelloApplication.setRoot("produtos-view");

    }

    @FXML
    public void sair() throws IOException {
        HelloApplication.setRoot("login-view");
    }
}
