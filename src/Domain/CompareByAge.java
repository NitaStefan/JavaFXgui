package Domain;

import java.util.Comparator;

public class CompareByAge implements Comparator<Patient> {
    public int compare(Patient p1,Patient p2){
        return p1.getAge().compareTo(p2.getAge());
    }
}