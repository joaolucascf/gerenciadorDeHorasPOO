package poofinal.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student extends Pessoa{
    private String password;
    private String cpf;
    private String matriculation;
    private LocalDate joined;
    private LocalDate graduationForecast;
    private String email;
    //private List<File> ListActivities = new ArrayList<>();
    private Course course;
    public Student(String password, String cpf, String matriculation, LocalDate joined, LocalDate graduationForecast, String name, String email, Course course) {
        super(name);
        this.password = password;
        this.cpf = cpf;
        this.matriculation = matriculation;
        this.joined = joined;
        this.graduationForecast = graduationForecast;
        this.email = email;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "password='" + password + '\'' +
                ", cpf='" + cpf + '\'' +
                ", matriculation='" + matriculation + '\'' +
                ", joined=" + joined.format(DateTimeFormatter.ofPattern("LLLL' de 'yyyy")) +
                ", graduationForecast=" + graduationForecast.format(DateTimeFormatter.ofPattern("LLLL' de 'yyyy")) +
                ", email='" + email + '\'' +
                ", course=" + course.getDescription() +
                '}';
    }
}
