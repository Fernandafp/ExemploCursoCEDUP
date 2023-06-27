package com.example.javafxexemplo.model;

import com.example.javafxexemplo.ConnectioSingleton;
import com.example.javafxexemplo.model.Usuario;

import java.io.IOException;
import java.sql.*;

public class UsuarioDAO {

    public boolean existe(Usuario usuario) throws SQLException, IOException {

        String sql = "select count(*) from usuario where nome = ? and senha = ?";
        try (PreparedStatement preparedStatement = ConnectioSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.usuario);
            preparedStatement.setString(2, usuario.senha);

            try (ResultSet resultado = preparedStatement.executeQuery()) {
                resultado.next();
                int quantidadeUsuarios = resultado.getInt(1);
                if (quantidadeUsuarios > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}







