package com.mycompany.controle_de_estoque.View.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControladorRelatorioMovimentacao {
    @FXML
    private TableView tableMovimentacoes;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colData;

    @FXML
    private TableColumn colProduto;

    @FXML
    private TableColumn colQuantidade;

    @FXML
    private TableColumn colTipo;
}
