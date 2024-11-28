package com.mycompany.controle_de_estoque.Modelo.DAO;

import com.mycompany.controle_de_estoque.Modelo.Conexao.Conexao;
import com.mycompany.controle_de_estoque.Modelo.Conexao.ConexaoMSQL;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Conexao conexao;
    
    public UsuarioDAO(){
        this.conexao = new ConexaoMSQL();
    }
    
    public String salvar(Usuario usuario){
      return usuario.getId() == 0L ? adicionar (usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome)VALUES(?)";
        
        Usuario usuarioTemp = buscarUsuarioNome(usuario.getNome());
        if(usuarioTemp != null){
            return String.format("ERRO: NOME %s JÁ EXISTE", usuario.getNome());
        }
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
        String sql = "UPDATE usuario SET nome = ?, WHERE id = %d";
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
    public List<Usuario> buscarUsuarios(){
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try{
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            while(result.next()){
            usuarios.add(getUsuario(result));
            }
        }catch (SQLException e){
            System.out.print(String.format("Erro", e.getMessage()));
        }
        return usuarios;
    }
    private Usuario getUsuario(ResultSet result) throws SQLException{
        Usuario usuario = new Usuario();
        
        usuario.setId(result.getLong("id"));
        usuario.setNome(result.getString("nome"));
        
        return usuario;
        }
    
     public Usuario buscarUsuariosID(long id){
        String sql = String.format("SELECT * FROM usuario WHERE id = &d", id);
        List<Usuario> usuarios = new ArrayList<>();
        try{
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            if(result.next()){
            return getUsuario(result);
            }
        }catch (SQLException e){
            System.out.print(String.format("Erro", e.getMessage()));
        }
        return null;
    }
     
     public Usuario buscarUsuarioNome(String nome){
        String sql = String.format("SELECT * FROM usuario WHERE id = &d", nome);
        List<Usuario> usuarios = new ArrayList<>();
        try{
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            if(result.next()){
            return getUsuario(result);
            }
        }catch (SQLException e){
            System.out.print(String.format("Erro", e.getMessage()));
        }
        return null;
    }
}
