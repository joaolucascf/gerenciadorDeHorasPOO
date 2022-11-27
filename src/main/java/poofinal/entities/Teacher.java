package poofinal.entities;

public class Teacher extends Person {

    private String siape;

    public Teacher(String name, String siape) {

        super(name);
        this.siape = siape;
    }

    public String getSiape() {
        return siape;
    }
}
