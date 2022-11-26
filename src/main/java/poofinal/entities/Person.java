package poofinal.entities;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
