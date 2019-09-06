package ru.pelmegov.game;

public enum Direction {

    UP(0),
    LEFT(1),
    DOWN(2),
    RIGHT(3)
    ;

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
