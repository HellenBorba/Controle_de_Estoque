/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controle_de_estoque;

import com.mycompany.controle_de_estoque.Modelo.DAO.UsuarioDAO;
import com.mycompany.controle_de_estoque.Modelo.Entidades.Usuario;
import java.time.LocalDateTime;

/**
 *
 * @author jully
 */
public class TesteDAOS {
    public static void main(String[] args){
            Usuario usuario = new Usuario(0L, "Noah");
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            String mensagem = usuarioDAO.salvar(usuario);
            System.out.println(mensagem);
}
}
