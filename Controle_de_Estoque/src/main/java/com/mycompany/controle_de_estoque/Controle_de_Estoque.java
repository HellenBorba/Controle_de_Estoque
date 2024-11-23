package com.mycompany.controle_de_estoque;

import com.mycompany.controle_de_estoque.Modelo.Conexao.Conexao;
import com.mycompany.controle_de_estoque.Modelo.Conexao.ConexaoMSQL;
import java.sql.SQLException;

/**
 *
 * @author jully
 */
public class Controle_de_Estoque {

    public static void main(String[] args) throws SQLException {
        Conexao conexao = new ConexaoMSQL();
        System.out.println(conexao.obterConexao());
    }
}
