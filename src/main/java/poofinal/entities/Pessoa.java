package poofinal.entities;

public abstract class Pessoa {
    private final String name;

    public Pessoa(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "name='" + name + '\'' +
                '}';
    }
}
