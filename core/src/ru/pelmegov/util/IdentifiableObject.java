package ru.pelmegov.util;

import java.util.Random;

public abstract class IdentifiableObject {

    private final Integer id;

    public IdentifiableObject() {
        this.id = new Random().nextInt(Integer.MAX_VALUE);
    }

    public IdentifiableObject(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
