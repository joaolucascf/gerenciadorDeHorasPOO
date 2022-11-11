package poofinal.entities;

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