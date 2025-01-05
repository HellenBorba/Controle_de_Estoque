/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.controle_de_estoque.View.Controladores;

import javafx.fxml.FXML;

/**
 *
 * @author jully
 */
public interface NewInterface {

    void abrirRelatorio();

    void carregarTela(String fxmlFile);

    void fecharJanela();

    @FXML
    void initialize();
    
}
