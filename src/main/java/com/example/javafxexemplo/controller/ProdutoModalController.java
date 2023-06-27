package com.example.javafxexemplo.controller;

import com.example.javafxexemplo.HelloApplication;
import com.example.javafxexemplo.model.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProdutoModalController implements Initializable {
    @FXML
    TextField codigoFiled;

    @FXML
    TextField nomeField;

    @FXML
    TextField precoField;

    public static Produto produto;

    @FXML
    public void salvar() {
        if (!nomeField.getText().isBlank()) {
            if (!precoField.getText().isBlank()) {

                if (precoField.getText().matches("\\d+\\.?\\d*")) {
                    Produto novoProduto = new Produto();

                    if (!codigoFiled.getText().isBlank()) {
                        novoProduto.codigo = Integer.parseInt(codigoFiled.getText());
                    }
                    novoProduto.nome = nomeField.getText();
                    novoProduto.preco = Double.parseDouble(precoField.getText());

                    produto = novoProduto;

                    HelloApplication.closeCurrentWindow();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Preço invalido");
                    alert.setHeaderText(null);
                    alert.setContentText("O preço deve ser informado no formato (99.99)! ");

                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR, ATENÇÃO!");
                alert.setHeaderText(null);
                alert.setContentText("Campos com * devem ser preenchidos! ");

                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Produto produtoSelecionado = ProdutoModalController.produto;

        if (produtoSelecionado != null) {
            codigoFiled.setText(Integer.toString(produtoSelecionado.codigo));
            nomeField.setText(produtoSelecionado.nome);
            precoField.setText(Double.toString(produtoSelecionado.preco));
        }
        precoField.textProperty().addListener((o, oldValue, newValue) -> {
            precoField.setText(newValue.replaceAll("[^\\d.]", ""));
        });

    }
    @FXML
    public void cancelar() {
        HelloApplication.closeCurrentWindow();
    }
}