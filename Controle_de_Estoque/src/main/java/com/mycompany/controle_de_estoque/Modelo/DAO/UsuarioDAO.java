package com.mycompany.controle_de_estoque.Modelo.DAO;

import com.mycompany.controle_de_estoque.Modelo.Conexao.Conexao;
import com.mycompany.controle_de_estoque.Modelo.Conexao.ConexaoMSQL;

public class UsuarioDAO {
    private final Conexao conexao;
    
    public UsuarioDAO(){
        this.conexao = new ConexaoMSQL();
    }
}
