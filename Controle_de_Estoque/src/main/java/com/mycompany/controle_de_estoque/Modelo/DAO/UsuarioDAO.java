package com.mycompany.controle_de_estoque.Modelo.DAO;

import com.mycompany.controle_de_estoque.Modelo.Conexao.Conexao;
import com.mycompany.controle_de_estoque.Modelo.Conexao.ConexaoMSQL;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    private final Conexao conexao;
    
    public UsuarioDAO(){
        this.conexao = new ConexaoMSQL();
    }
    
    public String salvar(Usuario usuario){
      return usuario.getId() == 8L ? adicionar (usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome)VALUES(?)";
        try{
            PreparedStatement prepareStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresStatement(prepareStatement, usuario);
            int resultado = prepareStatement.executeUpdate();
            return resultado == 1 ? "USUARIO ADICIONADO" : "USUARIO NÃO ADICIONADO";
        }catch(SQLException e){
            return String.format("Erro: ", e.getMessage());
        }
    }

    private String editar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, WHERE id = ?";
                try{
            PreparedStatement prepareStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresStatement(prepareStatement, usuario);
            int resultado = prepareStatement.executeUpdate();
            return resultado == 1 ? "USUARIO EDITADO" : "USUARIO NÃO EDITADO";
        }catch(SQLException e){
            return String.format("Erro: ", e.getMessage());
        }
    }

    private void preencherValoresStatement(PreparedStatement prepareStatement, Usuario usuario) throws SQLException{
        prepareStatement.setString(1, usuario.getNome());
        
        if(usuario.getId() != 0L)
        {
            prepareStatement.setLong(1, usuario.getId());
        }
    }
}
