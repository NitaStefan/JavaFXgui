package Domain;

public class Patient implements Model<Integer> {
    private Integer id;
    private String name;
    private Integer age;
    private String illness;

    public Patient(){
        this(0,"",0,"");
    }
    public Patient(Integer id,String name,Integer age,String illness){
        this.id=id;
        this.name=name;
        this.age=age;
        this.illness=illness;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String toString() {
        return name+", "+Integer.toString(age)+" years old (id: "+Integer.toString(id)+") , suffers from "+illness;
    }
}