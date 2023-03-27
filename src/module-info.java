module GUI {
    requires javafx.controls;
    requires javafx.fxml;

    //requires org.controlsfx.controls;
    requires org.xerial.sqlitejdbc;
    requires javafx.graphics;
    requires java.sql;


    opens GUI to javafx.fxml;
    exports GUI;
    exports main;
}