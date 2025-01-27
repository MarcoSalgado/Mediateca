package atec.poo.mediateca.core.exceptions;

import java.io.Serializable;

public class WorkNotFoundException extends Exception implements Serializable {

    private int id;

    public WorkNotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
