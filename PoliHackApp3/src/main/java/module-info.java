module com.example.polihackapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.polihackapp to javafx.fxml;
    exports com.example.polihackapp;
    exports com.example.polihackapp.gui;
    opens com.example.polihackapp.gui to javafx.fxml;
    exports com.example.polihackapp.Services;
}