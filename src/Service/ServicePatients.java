package Service;

import Domain.Appointment;
import Domain.CompareByAge;
import Domain.Patient;
import Repository.AppointmentRepo;
import Repository.PatientRepo;

import java.util.*;
import java.util.stream.Collectors;

public class ServicePatients {
    private PatientRepo ptRepo;
    private AppointmentRepo appRepo;
    public ServicePatients(PatientRepo PtRepo, AppointmentRepo AppRepo){
        this.ptRepo=PtRepo;
        this.appRepo=AppRepo;
    }
    public ServicePatients(){
        this(new PatientRepo(),new AppointmentRepo());
    }

    public PatientRepo getPtRepo() {
        return ptRepo;
    }

    public AppointmentRepo getAppRepo() {
        return appRepo;
    }

    public void removePatient(Integer id){
        Iterable<Appointment> itrb=appRepo.findAll();
        Iterator<Appointment> it =itrb.iterator();
        ArrayList<Appointment> deletedAppointments=new ArrayList<>();
        while(it.hasNext()){
            Appointment app=it.next();
            if(app.getIdPatient()==id)
                deletedAppointments.add(app);
        }
        for(Appointment a:deletedAppointments)
            appRepo.delete(a.getId());
        ptRepo.delete(id);
    }
    public ArrayList<Patient> sortByAge(){
        ArrayList<Patient> patients=new ArrayList<>();
        Iterable<Patient> iterable=ptRepo.findAll();
        Iterator<Patient> it=iterable.iterator();
        while(it.hasNext()){
            patients.add(it.next());
        }
        Collections.sort(patients,new CompareByAge());
        return patients;
    }
    //Reports
    public ArrayList<Appointment> patientsAppointments(Integer id)//Appointments of a patient
    {
        ArrayList<Appointment> appointments=new ArrayList<>(appRepo.getRepo().values());
        return (ArrayList<Appointment>)appointments.stream().filter(app -> app.getIdPatient()==id).collect(Collectors.toList());

    }
    public ArrayList<Appointment> afternoonAppointments()//Appointments scheduled afternoon
    {
        ArrayList<Appointment> appointments=new ArrayList<>(appRepo.getRepo().values());
        return (ArrayList<Appointment>) appointments.stream().filter(app -> app.getHour()>12).sorted(Comparator.comparing(Appointment::getHour)).collect(Collectors.toList());
    }
    public ArrayList<Patient> adultPatients()//All adult patients
    {
        ArrayList<Patient> patients=new ArrayList<>(ptRepo.getRepo().values());
        return (ArrayList<Patient>) patients.stream().filter(p -> p.getAge()>=18).sorted(Comparator.comparing(Patient::getAge)).collect(Collectors.toList());
    }
    public ArrayList<Patient> patientsAlphabetically()//sort patients alphabetically
    {
        ArrayList<Patient> patients=new ArrayList<>(ptRepo.getRepo().values());
        return (ArrayList<Patient>) patients.stream().sorted(Comparator.comparing(Patient::getName)).collect(Collectors.toList());
    }
    public HashMap<Integer,List<Appointment>> groupAppsByYear()//group appointments by year they are scheduled
    {
        ArrayList<Appointment> appointments=new ArrayList<>(appRepo.getRepo().values());
        return (HashMap<Integer,List<Appointment>>)appointments.stream().collect(Collectors.groupingBy(Appointment::getYear));
    }


}
