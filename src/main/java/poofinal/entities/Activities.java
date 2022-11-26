package poofinal.entities;

import java.io.Serializable;

public class Activities implements Serializable {
    private String id;
    private String description;
    private String hours;
    private String justification;
    private boolean flag = false;

    public Activities(String id, String description, String hours) {
        this.id = id;
        this.description = description;
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getJustification() {
        return justification;
    }

    public String getId() {
        return id;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag(){
        return this.flag;
    }
}
