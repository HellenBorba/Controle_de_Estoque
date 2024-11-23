/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controle_de_estoque.Modelo.Conexao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jully
 */
public interface Conexao {
    public Connection obterConexao() throws SQLException;
}
