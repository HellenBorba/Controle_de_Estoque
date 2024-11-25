package com.mycompany.controle_de_estoque.View.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControladorRelatorioCategoria {
    @FXML
    private TableView tableMovimentacoes;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colNome;

    @FXML
    private TableColumn colDescricao;

    @FXML
    private TableColumn colRefBaixoestoque;

}
