package main;

import GUI.Controller;
import Repository.AppointmentRepoDB;
import Repository.PatientRepositoryDB;
import Service.ServicePatients;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("/GUI/hello-view.fxml"));
        PatientRepositoryDB pRepo=new PatientRepositoryDB("src/Repository/Hospital.db");
        AppointmentRepoDB aRepo=new AppointmentRepoDB("src/Repository/Hospital.db");
        ServicePatients service=new ServicePatients(pRepo,aRepo);
        Controller controller=new Controller(service);
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 800, 650);
        stage.setTitle("Hospital");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}