package GUI;

import Domain.Appointment;
import Domain.Patient;
import Repository.AppointmentRepo;
import Repository.AppointmentRepoDB;
import Repository.PatientRepo;
import Repository.PatientRepositoryDB;
import Service.ServicePatients;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Controller {
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextArea textUp,textDown;
    @FXML
    private VBox rightMenu;
    @FXML
    private VBox centerMenu;
    @FXML
    private TextField tf1,tf2,tf3,tf4,tf5;
    @FXML
    private Button helper;
    private ServicePatients service;

    public Controller(ServicePatients service) {
        this.service = service;
    }

    /*public ServicePatients load()
        {
            Patient p1=new Patient(23,"John",34,"headache");
            Patient p2=new Patient(86,"Jeremy",17,"allergies");
            Patient p3=new Patient(76,"Sam",42,"Pneumonia");
            Patient p4=new Patient(59,"Alfred",16,"Acne");
            Appointment a1=new Appointment("sl", LocalDateTime.of(2022,1,19,13,0),86);
            Appointment a2=new Appointment("2p",LocalDateTime.of(2023,2,11,17,40),23);
            Appointment a3=new Appointment("m4",LocalDateTime.of(2023,1,7,8,30),76);
            Appointment a4=new Appointment("tn",LocalDateTime.of(2022,12,4,15,45),86);
            Appointment a5=new Appointment("uu",LocalDateTime.of(2023,6,1,11,45),59);
            PatientRepositoryDB pRepo=new PatientRepositoryDB("src/Repository/Hospital.db");
            AppointmentRepoDB aRepo=new AppointmentRepoDB("src/Repository/Hospital.db");
            pRepo.add(23,p1);
            pRepo.add(86,p2);
            pRepo.add(76,p3);
            pRepo.add(59,p4);
            aRepo.add("sl",a1);
            aRepo.add("2p",a2);
            aRepo.add("m4",a3);
            aRepo.add("tn",a4);
            aRepo.add("uu",a5);
            ServicePatients service=new ServicePatients(pRepo,aRepo);
            return service;
        }*/
    @FXML
    void eventHandler(KeyEvent event) {
        if(tf1.getPromptText()!=null && tf2.getPromptText()==null)
        {
            addPatient2();
        }
    }
    @FXML
    protected void showAll() {
        Iterator<Patient> it=service.getPtRepo().findAll().iterator();
        while (it.hasNext())
        {
            textUp.appendText(String.valueOf(it.next())+"\n");
        }
        Iterator<Appointment> it2=service.getAppRepo().findAll().iterator();
        while (it2.hasNext())
        {
            textUp.appendText(String.valueOf(it2.next())+"\n");
        }
    }
    @FXML
    protected void addPatient()
    {
        tf1=new TextField();
        tf2=new TextField();
        tf3=new TextField();
        tf4=new TextField();
        tf1.setPromptText("Id");
        tf2.setPromptText("Name");
        tf3.setPromptText("Age");
        tf4.setPromptText("Illness");
        helper=new Button("Add Patient");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                addPatient2();
            }
        };
        helper.setOnAction(event);
        rightMenu.getChildren().addAll(tf1,tf2,tf3,tf4,helper);
    }
    @FXML
    protected void addPatient2()
    {
        int id=Integer.valueOf(tf1.getText());
        String name=tf2.getText();
        int age=Integer.valueOf(tf3.getText());
        String illness=tf4.getText();
        Patient p=new Patient(id,name,age,illness);
        service.getPtRepo().add(id,p);
        textUp.clear();
        showAll();
        rightMenu.getChildren().clear();
    }
    @FXML
    protected void addAppointment()
    {
        tf1=new TextField();
        tf2=new TextField();
        tf3=new TextField();
        tf1.setPromptText("Id");
        tf2.setPromptText("Date( YYYY-MM-DD HH:MM )");
        tf3.setPromptText("IdPatient");
        helper=new Button("Add Appointment");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                addAppointment2();
            }
        };
        helper.setOnAction(event);
        rightMenu.getChildren().addAll(tf1,tf2,tf3,helper);
    }
    @FXML
    public void addAppointment2()
    {
        String id=tf1.getText();
        String[] date=tf2.getText().split("[ :-]+");
        Integer idP=Integer.valueOf(tf3.getText());
        Appointment ap=new Appointment(id,LocalDateTime.of(Integer.valueOf(date[0]),Integer.valueOf(date[1]),Integer.valueOf(date[2]),Integer.valueOf(date[3]),Integer.valueOf(date[4])),idP);
        service.getAppRepo().add(id,ap);
        textUp.clear();
        showAll();
        rightMenu.getChildren().clear();
    }
    @FXML
    public void updatePatient()
    {
        tf1=new TextField();
        tf2=new TextField();
        tf3=new TextField();
        tf4=new TextField();
        tf1.setPromptText("Id");
        tf2.setPromptText("Name");
        tf3.setPromptText("Age");
        tf4.setPromptText("Illness");
        helper=new Button("Update Patient");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                updatePatient2();
            }
        };
        helper.setOnAction(event);
        rightMenu.getChildren().addAll(tf1,tf2,tf3,tf4,helper);
    }
    @FXML
    protected void updatePatient2()
    {
        int id=Integer.valueOf(tf1.getText());
        String name=tf2.getText();
        int age=Integer.valueOf(tf3.getText());
        String illness=tf4.getText();
        Patient p=new Patient(id,name,age,illness);
        service.getPtRepo().updateId(id,p);
        textUp.clear();
        showAll();
        rightMenu.getChildren().clear();
    }
    @FXML
    protected void updateAppointment()
    {
        tf1=new TextField();
        tf2=new TextField();
        tf3=new TextField();
        tf1.setPromptText("Id");
        tf2.setPromptText("Date( YYYY-MM-DD HH:MM )");
        tf3.setPromptText("IdPatient");
        helper=new Button("Update Appointment");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                updateAppointment2();
            }
        };
        helper.setOnAction(event);
        rightMenu.getChildren().addAll(tf1,tf2,tf3,helper);
    }
    @FXML
    public void updateAppointment2()
    {
        String id=tf1.getText();
        String[] date=tf2.getText().split("[ :-]+");
        Integer idP=Integer.valueOf(tf3.getText());
        Appointment ap=new Appointment(id,LocalDateTime.of(Integer.valueOf(date[0]),Integer.valueOf(date[1]),Integer.valueOf(date[2]),Integer.valueOf(date[3]),Integer.valueOf(date[4])),idP);
        service.getAppRepo().updateId(id,ap);
        textUp.clear();
        showAll();
        rightMenu.getChildren().clear();
    }
    @FXML
    public void deletePatient()
    {
        tf1=new TextField();
        tf1.setPromptText("Id");
        helper=new Button("Delete Patient");
        EventHandler<ActionEvent> event=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deletePatient2();
            }
        };
        helper.setOnAction(event);
        rightMenu.getChildren().addAll(tf1,helper);

    }
    @FXML
    public void deletePatient2()
    {
        Integer id=Integer.valueOf(tf1.getText());
        service.removePatient(id);
        textUp.clear();
        showAll();
        rightMenu.getChildren().clear();
    }
    @FXML
    public void deleteAppointment()
    {
        tf1=new TextField();
        tf1.setPromptText("Id");
        helper=new Button("Delete Appointment");
        EventHandler<ActionEvent> event=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteAppointment2();
            }
        };
        helper.setOnAction(event);
        rightMenu.getChildren().addAll(tf1,helper);
    }
    @FXML
    public void deleteAppointment2()
    {
        String id=tf1.getText();
        service.getAppRepo().delete(id);
        textUp.clear();
        showAll();
        rightMenu.getChildren().clear();
    }
    @FXML
    public void showAftApps()
    {
        textDown.clear();
        ArrayList<Appointment> aftApps=service.afternoonAppointments();
        aftApps.forEach(app->{
            textDown.appendText(String.valueOf(app)+"\n");
        });
    }
    @FXML
    public void adultP()
    {
        textDown.clear();
        ArrayList<Patient> aPts=service.adultPatients();
        aPts.forEach(p->{
            textDown.appendText(String.valueOf(p)+"\n");
        });

    }
    @FXML
    public void PAlph(){
        textDown.clear();
        ArrayList<Patient> pts=service.patientsAlphabetically();
        pts.forEach(p->{
            textDown.appendText(String.valueOf(p)+"\n");
        });
    }
}