module Controle_de_Estoque {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;

    exports com.mycompany.controle_de_estoque;
    opens com.mycompany.controle_de_estoque to javafx.fxml;
}