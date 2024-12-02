/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controle_de_estoque.Modelo.DAO;

import com.mycompany.controle_de_estoque.Modelo.Conexao.Conexao;
import com.mycompany.controle_de_estoque.Modelo.Conexao.ConexaoMSQL;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Categoria;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Produto;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quitumba
 */
public class ProdutoDAO {
    
    private final Conexao conexao;

    public ProdutoDAO() {
        this.conexao = new ConexaoMSQL();
    }
    
    
    public String salvar(Produto produto) {
        return produto.getId() == 0L ? adicionar(produto) : editar(produto);
    }

    private String adicionar(Produto produto) {
        String sql = "INSERT INTO produto(nome, preco, categoria_id, quantidade, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        Produto produtoTemp = buscarProdutoPeloNome(produto.getNome());
        
        if(produtoTemp != null) {
            return String.format("Produto %s ja existe no banco de dados.", produto.getNome());
        }
        
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            
            preparedStatementSet(preparedStatement, produto);
            
            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ? "Produto adicionado com sucesso" : "Nao foi possivel adicionar o produto";
            
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    private String editar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco  = ?, categoria_id  = ?, quantidade  = ?, usuario_id  = ? WHERE id  = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            
            preparedStatementSet(preparedStatement, produto);
            
            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ? "Produto editado com sucesso" : "Nao foi possivel adicionar o produto";
            
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }
    
    public String actualizarQuantidade(Long idProduto, Integer quantidade, Long usuarioId) {
        String sql = "UPDATE produto SET quantidade = ?, usuario_id = ? WHERE id  = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            
            preparedStatement.setInt(1, quantidade);
            preparedStatement.setLong(2, usuarioId);
            preparedStatement.setLong(3, idProduto);
            
            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ? "Quantidade do produto editado com sucesso" : "Nao foi possivel editar quantidade do produto";
            
        } catch (SQLException e) {
            e.printStackTrace();
            return String.format("Error: %s", e.getMessage());
        }
    }

    private void preparedStatementSet(PreparedStatement preparedStatement, Produto produto) throws SQLException {
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setBigDecimal(3, produto.getPreco());
        preparedStatement.setLong(4, produto.getCategoria().getId());
        preparedStatement.setInt(5, produto.getQuantidade());
        
        if(produto.getId() != 0L)
            preparedStatement.setLong(7, produto.getId());
    }
    
    public List<Produto> todosProdutos() {
        String sql = "SELECT * FROM produto p, categoria c, usuario u WHERE p.categoria_id = c.id AND p.usuario_id = u.id";
        List<Produto> produtos = new ArrayList<>();
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            
            while(result.next()) {
                produtos.add(getProduto(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
        return produtos;
    }
    
    public Produto buscarProdutoPeloId(Long id) {
        String sql = String.format("SELECT * FROM produto p, categoria c, usuario u WHERE p.categoria_id = c.id AND p.usuario_id = u.id AND p.id = %d", id);
        
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            
            if(result.next()) {
                return getProduto(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
        return null;
    }
    
    public List<Produto> buscarProdutosPeloCategoria(String categoria) {
        String sql = String.format("SELECT * FROM produto p, categoria c, usuario u WHERE p.categoria_id = c.id AND p.usuario_id = u.id AND c.nome = '%s'", categoria);
        List<Produto> produtos = new ArrayList<>();
        
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            
            while(result.next()) {
                 produtos.add(getProduto(result));
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
        return produtos;
    }
    
    public Produto buscarProdutoPeloNome(String nome) {
        String sql = String.format("SELECT * FROM produto p, categoria c, usuario u WHERE p.categoria_id = c.id AND p.usuario_id = u.id AND p.nome = '%s'", nome);
        
        try {
            ResultSet result = conexao.obterConexao().prepareStatement(sql).executeQuery();
            
            if(result.next()) {
                return getProduto(result);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
        return null;
    }

    private Produto getProduto(ResultSet result) throws SQLException {
        Produto produto = new Produto();
        Categoria categoria = new Categoria();
        Usuario usuario = new Usuario();
        
        categoria.setId(result.getLong("c.id"));
        categoria.setNome(result.getString("c.nome"));
        
        usuario.setId(result.getLong("u.id"));
        usuario.setNome(result.getString("u.nome"));
        
        produto.setId(result.getLong("p.id"));
        produto.setNome(result.getString("p.nome"));
        produto.setPreco(result.getBigDecimal("p.preco"));
        produto.setQuantidade(result.getInt("p.quantidade"));
        produto.setCategoria(categoria);
        
        return produto;
    }
    
    public String deletaProdutoPeloId(Long id) {
        String sql = String.format("DELETE FROM produto WHERE id = %d", id);
        
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            
            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ? "Produto apagado com sucesso" : "Nao foi possivel apagar produto";
            
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }
}