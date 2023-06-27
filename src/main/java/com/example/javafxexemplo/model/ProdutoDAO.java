package com.example.javafxexemplo.model;

import com.example.javafxexemplo.ConnectioSingleton;
import com.example.javafxexemplo.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public static List<Produto> getAll() throws SQLException {


        try (Statement statement = ConnectioSingleton.getConnection().createStatement();
             ResultSet rs = statement.executeQuery("select * from produto")) {

            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.codigo = rs.getInt(1);
                produto.nome = rs.getString(2);
                produto.preco = rs.getDouble(3);
                produtos.add(produto);
            }
            return produtos;
        }
    }
    public void insert(Produto produtoNovo) throws SQLException {


        String sql = "insert into produto (nome, preco) values (?,?)";

        try (PreparedStatement preparedStatement = ConnectioSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
          // preparedStatement.setInt(1, produtoNovo.codigo);
            preparedStatement.setString(1, produtoNovo.nome);
            preparedStatement.setDouble(2, produtoNovo.preco);

            preparedStatement.execute();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()){
                rs.next();
                produtoNovo.codigo = rs.getInt(1);
            }
        }
    }
    public void delete(Produto produtoSelect) throws SQLException {


        String sql = " delete from produto where codigo = ?";
        try (PreparedStatement preparedStatement = ConnectioSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, produtoSelect.codigo);

            preparedStatement.execute();
        }
    }

    public void update (Produto atualizaProduto) throws SQLException {

        String sql = "update produto set nome = ?, preco = ? where codigo = ?";
        //try fecha e abre o statement com banco de dados (o comando)
        try (PreparedStatement preparedStatement = ConnectioSingleton.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,atualizaProduto.nome);
            preparedStatement.setDouble(2,atualizaProduto.preco);
            preparedStatement.setInt(3,atualizaProduto.codigo);

            preparedStatement.execute();
        }
    }
}
