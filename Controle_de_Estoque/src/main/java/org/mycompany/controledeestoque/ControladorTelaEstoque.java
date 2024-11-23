package org.mycompany.controledeestoque;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.stage.Modality;

public class ControladorTelaEstoque {

    @FXML
    private ImageView abrirRelatorio;
    
    @FXML
    private Button btnFecharJanela;

    @FXML
    private Label lblMeuEstoque;

    @FXML
    private Pane contentPane;

    @FXML
    private Label lblProdutos;

    @FXML
    private Label lblMovimentacoes;

    @FXML
    private Label lblCategorias;
    
    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        btnFecharJanela.setOnMouseClicked(event -> fecharJanela());
        abrirRelatorio.setOnMouseClicked(event -> abrirRelatorio());
        lblMeuEstoque.setOnMouseClicked(event -> carregarTela("TelaEstoque.fxml"));
        lblCategorias.setOnMouseClicked(event -> carregarTela("TelaCategoria.fxml"));
        lblProdutos.setOnMouseClicked(event -> carregarTela("TelaProduto.fxml"));
        lblMovimentacoes.setOnMouseClicked(event -> carregarTela("TelaMovimentacao.fxml"));
    }

    public void abrirRelatorio() {
        try {
            // Carregar o arquivo FXML que vai sobrepor a tela atual
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/telas/RelatorioEstoque.fxml"));
            Parent novaTela = loader.load();

// Criar uma nova janela (Stage) para sobrepor a tela atual
        Stage novaJanela = new Stage();
        novaJanela.setScene(new Scene(novaTela));
        novaJanela.initOwner(abrirRelatorio.getScene().getWindow()); // Define a janela principal como dona
        novaJanela.initModality(Modality.APPLICATION_MODAL); // Define como uma janela modal, bloqueando interações com a janela principal
        novaJanela.setTitle("Relatório de Estoque");
        novaJanela.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void carregarTela(String fxmlFile) {
        try {
            FXMLLoader telaAtual = new FXMLLoader(getClass().getResource("/org/example/telas/" + fxmlFile));
            Parent root = telaAtual.load();

            // Limpa o contentPane e adiciona a nova tela
            contentPane.getChildren().clear();
            contentPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void fecharJanela() {
        Stage stage = (Stage) btnFecharJanela.getScene().getWindow();
        stage.close();
    }
}