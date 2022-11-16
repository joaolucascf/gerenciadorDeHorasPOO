package poofinal.entities;

public class Teacher extends Pessoa{
    private String password;
    private String siape;

    public Teacher(String password, String siape, String name) {
        super(name);
        this.password = password;
        this.siape = siape;
    }
}
