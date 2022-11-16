package poofinal.entities;

import java.io.Serializable;

public enum Course implements Serializable {
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