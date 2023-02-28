package Domain;
import java.time.LocalDateTime;

public class Appointment implements Model<String>{
    String id;
    LocalDateTime date;
    int patientId;
    public Appointment(String id, LocalDateTime date, int patientId){
        this.id=id;
        this.date=date;
        this.patientId=patientId;
    }
    public Appointment(){
        this("0",LocalDateTime.of(2022, 4, 24, 14, 33),0);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getIdPatient() {
        return patientId;
    }

    public void setIdPatientId(int pacientId) {
        this.patientId = pacientId;
    }
    public int getHour()
    {
        return date.getHour();
    }
    public int getYear()
    {
        return date.getYear();
    }

    @Override
    public String toString() {
        return "Appointment(with id:"+id+") at "+date+" ,for patient with id: "+Integer.toString(patientId);
    }
}