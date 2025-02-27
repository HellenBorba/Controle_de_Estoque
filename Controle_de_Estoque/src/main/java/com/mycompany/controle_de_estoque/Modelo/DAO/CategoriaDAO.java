package com.mycompany.controle_de_estoque.Modelo.DAO;

import com.mycompany.controle_de_estoque.Modelo.Conexao.Conexao;
import com.mycompany.controle_de_estoque.Modelo.Conexao.ConexaoMSQL;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final Conexao conexao;

    public CategoriaDAO() {
        this.conexao = new ConexaoMSQL();
    }

    public String salvar(Categoria categoria) {
        return categoria.getId() == 0L ? adicionar(categoria) : editar(categoria);
    }

    private String adicionar(Categoria categoria) {
        String sql = "INSERT INTO categoria(nome, descricao, ref_baixo_estoque) VALUES(?, ?, ?)";
        
        Categoria categoriaTemp = buscarCategoriaPeloNome(categoria.getNome());
        
        if(categoria.getRef_baixo_estoque() <= 20)
        {
         return String.format("Estoque Baixo");

        }
        if(categoriaTemp != null) {
            return String.format("Error: %s ja existe no banco de dados", categoria.getNome());
        }
        
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);

            preparedStatementSet(preparedStatement, categoria);

            int resultado = preparedStatement.executeUpdate();
            return resultado == 1 ? "Categoria adicionado com sucesso." : "Nao possivel adicionar a categoria";
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    private String editar(Categoria categoria) {
        String sql = "UPDATE categoria SET nome = ?, descricao = ?, ref_baixo_estoque = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);

            preparedStatementSet(preparedStatement, categoria);

            int resultado = preparedStatement.executeUpdate();
            return resultado == 1 ? "Categoria editado com sucesso." : "Nao possivel editar a categoria";
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    private void preparedStatementSet(PreparedStatement preparedStatement, Categoria categoria) throws SQLException {
        preparedStatement.setString(1, categoria.getNome());
        preparedStatement.setString(2, categoria.getDescricao());
        preparedStatement.setInt(3, categoria.getRef_baixo_estoque());


        if (categoria.getId() != 0L) {
            preparedStatement.setLong(3, categoria.getId());
        }
    }

    public List<Categoria> todasCategorias() {
        String sql = "SELECT * FROM categoria";
        List<Categoria> categorias = new ArrayList<>();

        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();

            while (result.next()) {
                categorias.add(getCategoria(result));
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return categorias;
    }

    public Categoria buscarCategoriaPeloId(Long id) {
        String sql = String.format("SELECT * FROM categoria WHERE id = %d", id);
        try {
            
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();

            if (result.next()) {
                return getCategoria(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return null;
    }
    
    public Categoria buscarCategoriaPeloNome(String nome) {
        String sql = String.format("SELECT * FROM categoria WHERE nome = '%s'", nome);
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();

            if (result.next()) {
                return getCategoria(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return null;
    }
    
    public String deleteCategoriaPeloId(Long id) {
        String sql = String.format("DELETE FROM categoria WHERE id = %d", id);
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);

            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ? "Categoria apagado com sucesso." : "Nao foi possivel apagar categoria";
            
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
            if(e.getMessage().startsWith("Cannot delete or update a parent row")) {
                return "Nao foi possivel apagar - Categoria associado ao produto";
            }
            return String.format("Error: %s", e.getMessage());
        }
    }

    private Categoria getCategoria(ResultSet result) throws SQLException {
        Categoria categoria = new Categoria();

        categoria.setId(result.getLong("id"));
        categoria.setNome(result.getString("nome"));
        categoria.setDescricao(result.getString("descricao"));
        categoria.setRef_baixo_estoque(result.getInt("ref_baixo_estoque"));

        return categoria;
    }

}
