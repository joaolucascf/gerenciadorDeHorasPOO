package poofinal.entities;

import java.io.File;
import java.io.Serializable;
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
}
