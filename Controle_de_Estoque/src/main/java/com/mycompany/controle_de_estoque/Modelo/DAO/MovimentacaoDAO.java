package com.mycompany.controle_de_estoque.Modelo.DAO;

import com.mycompany.controle_de_estoque.Modelo.Conexao.Conexao;
import com.mycompany.controle_de_estoque.Modelo.Conexao.ConexaoMSQL;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Movimentacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {

    private final Conexao conexao;

    public MovimentacaoDAO() {
        this.conexao = new ConexaoMSQL();
    }

    public String salvar(Movimentacao movimentacao) {
        return movimentacao.getId() == 0L ? adicionar(movimentacao) : editar(movimentacao);
    }

    private String adicionar(Movimentacao categoria) {
        String sql = "INSERT INTO movimentacao (data_criacao, produto, quantidade, tipo_entrada) VALUES(?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);

            preparedStatementSet(preparedStatement, categoria);

            int resultado = preparedStatement.executeUpdate();
            return resultado == 1 ? "Movimentacao adicionado com sucesso." : "Nao possivel adicionar a movimentacao";
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    private String editar(Movimentacao movimentacao) {
        String sql = "UPDATE movimentacao SET data_criacao = ?, produto = ?, quantidade = ?, tipo_entrada = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);

            preparedStatementSet(preparedStatement, movimentacao);

            int resultado = preparedStatement.executeUpdate();
            return resultado == 1 ? "Movimentacao editado com sucesso." : "Nao possivel editar a movimentacao";
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    private void preparedStatementSet(PreparedStatement preparedStatement, Movimentacao movimentacao) throws SQLException {
        //preparedStatement.setLocalDateTime(1, movimentacao.getData_criacao());
        preparedStatement.setInt(2, movimentacao.getQuantidade());

        if (movimentacao.getId() != 0L) {
            preparedStatement.setLong(3, movimentacao.getId());
        }
    }

    public List<Movimentacao> todasCategorias() {
        String sql = "SELECT * FROM movimentacao";
        List<Movimentacao> movimentacao = new ArrayList<>();

        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();

            while (result.next()) {
               // movimentacao.add(getMovimentacao(result));
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return movimentacao;
    }

    public Movimentacao buscarCategoriaPeloId(Long id) {
        String sql = String.format("SELECT * FROM categoria WHERE id = %d", id);
        try {
            
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();

            if (result.next()) {
                //return getCategoria(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return null;
    }
    /*
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
    */
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
/*
    private Categoria getCategoria(ResultSet result) throws SQLException {
        Categoria categoria = new Categoria();

        categoria.setId(result.getLong("id"));
        categoria.setNome(result.getString("nome"));
        categoria.setDescricao(result.getString("descricao"));

        return categoria;
    }
*/
}