package Repository;

import Domain.Appointment;
import Domain.Patient;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Iterator;

public class AppointmentRepoDB extends MemoryRepo<String, Appointment> {
    private String path;
    private Connection con=null;
    public void openConnection(){
        try {
            con= DriverManager.getConnection(path);
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
    public AppointmentRepoDB(String path)
    {
        this.path="jdbc:sqlite:"+path;
        createTable();
        populateTable();
        readFromTable();
    }
    public void createTable()
    {
        try {
            openConnection();
            Statement st = con.createStatement();
            st.executeUpdate("drop table if exists appointment");
            st.executeUpdate("create table appointment (id string,date smalldatetime,idP integer)");
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
            st.executeUpdate("insert into appointment values ('sl','2022-1-19 13:00',86)");
            st.executeUpdate("insert into appointment values ('2p','2023-2-11 17:40',23)");
            st.executeUpdate("insert into appointment values ('m4','2023-1-7 8:30',76)");
            st.executeUpdate("insert into appointment values ('tn','2022-12-4 15:45',86)");
            st.executeUpdate("insert into appointment values ('uu','2023-6-1 11:45',59)");
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
            Statement st = con.createStatement();
            ResultSet res=st.executeQuery("select * from appointment");
            while (res.next())
            {
                String id=res.getString("id");
                String[] date=res.getString("date").split("[- :]+");
                int year=Integer.parseInt(date[0]);
                int month=Integer.parseInt(date[1]);
                int dayOfMonth=Integer.parseInt(date[2]);
                int hour=Integer.parseInt(date[3]);
                int minutes=Integer.parseInt(date[4]);
                LocalDateTime myDate=LocalDateTime.of(year,month,dayOfMonth,hour,minutes);
                int idP=res.getInt("idP");
                Appointment ap=new Appointment(id,myDate,idP);
                super.add(id,ap);
            }
            closeConnection();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void add(String id, Appointment app) {
        try {
            super.add(id,app);
            openConnection();
            Statement st= con.createStatement();
            st.executeUpdate("insert into appointment values ('"+app.getId()+"','"+String.valueOf(app.getDate().getYear())+"-"+String.valueOf(app.getDate().getMonthValue())+"-"+String.valueOf(app.getDate().getDayOfMonth())+" "+String.valueOf(app.getDate().getHour())+":"+String.valueOf(app.getDate().getMinute()+"',"+app.getIdPatient())+")");
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            super.delete(id);
            openConnection();
            Statement st= con.createStatement();
            st.executeUpdate("delete from appointment where id='"+id+"'");
            closeConnection();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateId(String id, Appointment app) {
        try
        {
            super.updateId(id,app);
            openConnection();
            Statement st= con.createStatement();
            st.executeUpdate("update appointment set date="+String.valueOf(app.getDate().getYear())+"-"+String.valueOf(app.getDate().getMonthValue())+"-"+String.valueOf(app.getDate().getDayOfMonth())+" "+String.valueOf(app.getDate().getHour())+":"+String.valueOf(app.getDate().getMinute()+", idP="+String.valueOf(app.getIdPatient())));
            closeConnection();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
