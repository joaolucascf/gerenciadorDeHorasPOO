package poofinal.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student extends Pessoa implements Serializable {
    private String password;
    private String matriculation;
    private LocalDate joined;
    private LocalDate graduationForecast;
    private String email;
    //private List<File> ListActivities = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Student{" +
                "password='" + password + '\'' +
                ", matriculation='" + matriculation + '\'' +
                ", joined=" + joined.format(DateTimeFormatter.ofPattern("LLLL' de 'yyyy")) +
                ", graduationForecast=" + graduationForecast.format(DateTimeFormatter.ofPattern("LLLL' de 'yyyy")) +
                ", email='" + email + '\'' +
                ", course=" + course.getDescription() +
                '}';
    }
}
