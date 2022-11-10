package poofinal.entities;

import java.time.LocalDate;

public class Student {
    private String senha;
    private String cpf;
    private String matriculation;
    private LocalDate joined;
    private LocalDate graduationForecast;

    public enum Course{
        ENGENHARIA("Engenharia de Computação"),
        CIENCIA("Ciência da Computação");

        private String description;

        Course(String description){
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Student(String senha, String cpf, String matriculation, LocalDate joined, LocalDate graduationForecast) {
        this.senha = senha;
        this.cpf = cpf;
        this.matriculation = matriculation;
        this.joined = joined;
        this.graduationForecast = graduationForecast;
    }
}
