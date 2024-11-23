module Controle_de_Estoque {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    exports org.mycompany.controledeestoque;
    opens org.mycompany.controledeestoque to javafx.fxml;
}