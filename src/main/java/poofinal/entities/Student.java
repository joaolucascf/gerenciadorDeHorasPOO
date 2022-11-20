package poofinal.entities;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Student extends Pessoa implements Serializable {
    private String password;
    private String matriculation;
    private LocalDate joined;
    private LocalDate graduationForecast;
    private String email;
    private List<File> listFilesActivities = new ArrayList<File>();
    private List<Activities> listActivities = new ArrayList<>();
    private Course course;
    public Student(String password, String matriculation, LocalDate joined, LocalDate graduationForecast, String name, String email, Course course) {
        super(name);
        this.password = password;
        this.matriculation = matriculation;
        this.joined = joined;
        this.graduationForecast = graduationForecast;
        this.email = email;
        this.course = course;
    }

    public String getMatriculation() {
        return matriculation;
    }

    public String getPassword() {
        return password;
    }

    public String getJoined() {
        return joined.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    public String getGraduationForecast() {
        return graduationForecast.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    public String getEmail() {
        return email;
    }

    public Course getCourse() {
        return course;
    }

    public void setActivities(Activities activitie) throws IOException {
        listActivities.add(activitie);
        File file = new File("src\\main\\resources\\files\\studentActivities\\" + activitie.getDescription() + ".dat");
        ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file));
        outStream.writeObject(activitie);
        outStream.close();
        listFilesActivities.add(file);
    }

    public List<Activities> getListActivities(){
        return listActivities;
    }
}
