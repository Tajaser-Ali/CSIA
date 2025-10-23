module com.example.csia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.example.csia to javafx.fxml;
    opens com.example.csia.controllers to javafx.fxml;
    opens com.example.csia.models to javafx.base;

    exports com.example.csia;
    exports com.example.csia.controllers;
}