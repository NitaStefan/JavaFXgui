package Repository;

import Domain.Patient;

import java.sql.*;
import java.util.Iterator;

public class PatientRepositoryDB extends MemoryRepo<Integer,Patient> {
    private String path;
    private Connection con=null;

    public PatientRepositoryDB(String path)
    {
        this.path="jdbc:sqlite:"+path;
        createTable();
        populateTable();
        readFromTable();
    }

    public void openConnection()
    {
        try {
            con=DriverManager.getConnection(path);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void closeConnection()
    {
        try {
            if(con!=null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTable()
    {
        try {
            openConnection();

            Statement st = con.createStatement();
            st.executeUpdate("drop table if exists patient");
            st.executeUpdate("create table patient (id integer,name string,age integer,illness string)");

            closeConnection();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateTable()
    {
        try {
            openConnection();

            Statement st = con.createStatement();
            st.executeUpdate("insert into patient values (23,'John',34,'headache')");
            st.executeUpdate("insert into patient values (86,'Jeremy',17,'allergies')");
            st.executeUpdate("insert into patient values (76,'Sam',42,'Pneumonia')");
            st.executeUpdate("insert into patient values (59,'Alfred',16,'Acne')");

            closeConnection();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public void readFromTable()
    {
        try {
            openConnection();
            Statement st= con.createStatement();
            ResultSet result=st.executeQuery("select * from patient");
            while (result.next())
            {
                int id=result.getInt("id");
                String name=result.getString("name");
                int age=result.getInt("age");
                String illness=result.getString("illness");
                Patient p=new Patient(id,name,age,illness);
                super.add(id,p);
            }
            closeConnection();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Integer id, Patient elem) {
        try {
            super.add(id,elem);
            openConnection();
            Statement st=con.createStatement();
            st.executeUpdate("insert into patient values ("+String.valueOf(id)+",'"+elem.getName()+"',"+String.valueOf(elem.getAge())+",'"+elem.getIllness()+"')");
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {
        try {
            super.delete(id);
            openConnection();
            Statement st=con.createStatement();
            st.executeUpdate("delete from patient where id="+String.valueOf(id));
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateId(Integer id, Patient elem) {
        try {
            super.updateId(id,elem);
            openConnection();
            Statement st=con.createStatement();
            st.executeUpdate("update patient set name='"+elem.getName()+"',age="+String.valueOf(elem.getAge())+",illness='"+elem.getIllness()+"' where id="+String.valueOf(id));
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
