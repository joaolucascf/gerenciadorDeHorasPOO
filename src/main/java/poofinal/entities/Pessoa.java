package poofinal.entities;

public abstract class Pessoa {
    private String name;

    public Pessoa(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "name='" + name + '\'' +
                '}';
    }
}
