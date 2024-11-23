package com.mycompany.controle_de_estoque.Modelo.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jully
 */
public class ConexaoMSQL implements Conexao{

    private final String USUARIO = "root";
    private final String SENHA = "Hellen0507";
    private final String URL = "jdbc:mysql://localhost/Controle_EstoqueA3?useTimezone=true&serverTimezone=America/Sao_Paulo";
    private Connection conectar;
    
    @Override
    public Connection obterConexao() throws SQLException {
        if(conectar == null)
        {
            conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        return conectar;
    }
}