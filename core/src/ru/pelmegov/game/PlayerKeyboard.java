package ru.pelmegov.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;

import static ru.pelmegov.graphic.animation.PlayerAnimation.PLAYER_DEFAULT_SPEED;

public class PlayerKeyboard {


    public Direction getDirectionKeyPressed(Body player) {
        Direction direction = null;

        int horizontalForce = 0;
        int verticalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 1;
            direction = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 1;
            direction = Direction.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            verticalForce += 1;
            direction = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            verticalForce -= 1;
            direction = Direction.DOWN;
        }

        player.setLinearVelocity(
                horizontalForce * PLAYER_DEFAULT_SPEED,
                verticalForce * PLAYER_DEFAULT_SPEED
        );

        return direction;
    }

}
