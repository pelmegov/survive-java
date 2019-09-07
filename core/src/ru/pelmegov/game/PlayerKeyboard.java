package ru.pelmegov.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerKeyboard {

    public Direction getDirectionKeyPressed() {
        Direction direction = null;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = Direction.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = Direction.DOWN;
        }
        return direction;
    }

}
