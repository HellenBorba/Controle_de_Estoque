/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controle_de_estoque;

import com.mycompany.controle_de_estoque.Modelo.DAO.CategoriaDAO;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Categoria;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Produto;


/**
 *
 * @author jully
 */
public class TesteDAOS{
    
    public static void main(String[] args){
            
            Categoria categoria =  new Categoria(0L, "BlusasM", "Conjunto de blusas", 50);
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            String mensagem = categoriaDAO.salvar(categoria);
            System.out.println(mensagem);

}

}
