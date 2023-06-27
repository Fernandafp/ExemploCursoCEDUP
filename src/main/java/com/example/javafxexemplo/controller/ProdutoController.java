package com.example.javafxexemplo.controller;

import com.example.javafxexemplo.HelloApplication;
import com.example.javafxexemplo.model.Produto;
import com.example.javafxexemplo.model.ProdutoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProdutoController implements Initializable {


    @FXML
    TableView<Produto> tableProdutos;

    @FXML
    TableColumn<Produto, Integer> colunaCodigo;

    @FXML
    TableColumn<Produto, String> colunaNome;

    @FXML
    TableColumn<Produto, Double> colunaPreco;

    //metodo que executa a tela
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Configurar as colunas da tabela da interface gráfica

        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            List<Produto> produtos = ProdutoDAO.getAll();
            tableProdutos.getItems().addAll(produtos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void novo() throws IOException, SQLException {
        ProdutoModalController.produto = null;

        HelloApplication.showModal("produto-modal-view");
        //Modal foi fechado
        //variavel global
        Produto novoProduto = ProdutoModalController.produto;

        if (novoProduto != null) {
            tableProdutos.getItems().add(novoProduto);
            new ProdutoDAO().insert(novoProduto);

        }
    }

    @FXML
    public void editar() throws IOException, SQLException {
        Produto produtoSelecionado = tableProdutos.getSelectionModel().getSelectedItem();
        ProdutoModalController.produto = produtoSelecionado;

        //O modal de edição foi salvo
        if (produtoSelecionado != null) {

            HelloApplication.showModal("produto-modal-view");

            Produto produtoEditado = ProdutoModalController.produto;

            //
            produtoSelecionado.codigo = produtoEditado.codigo;
            produtoSelecionado.nome = produtoEditado.nome;
            produtoSelecionado.preco = produtoEditado.preco;
            //

            new ProdutoDAO().update(produtoSelecionado);
            tableProdutos.refresh();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR, ATENÇÃO!");
            alert.setHeaderText(null);
            alert.setContentText("Selecione o item que deseja editar primeiro!");
            alert.showAndWait();
        }
    }

    @FXML
    public void excluir() throws SQLException {

        //obter produto selecionavel
        Produto produtoSelecionado = tableProdutos.getSelectionModel().getSelectedItem();

        if (produtoSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Excluir Produto");
            alert.setHeaderText(null);
            alert.setContentText("Deseja realmente excluir?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                new ProdutoDAO().delete(produtoSelecionado);
                tableProdutos.getItems().remove(produtoSelecionado); // ... user chose OK
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR, ATENÇÃO!");
                alert.setHeaderText(null);
                alert.setContentText("Selecione o item que deseja excluir primeiro!");
                alert.showAndWait();
        }
    }
    public void voltar() throws IOException {
        HelloApplication.setRoot("main-view");
    }
}

