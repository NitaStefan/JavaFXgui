module GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens GUI to javafx.fxml;
    exports GUI;
    exports main;
}