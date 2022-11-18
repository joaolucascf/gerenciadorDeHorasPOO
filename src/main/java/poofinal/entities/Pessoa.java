package poofinal.entities;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public abstract class Pessoa implements Serializable {
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
