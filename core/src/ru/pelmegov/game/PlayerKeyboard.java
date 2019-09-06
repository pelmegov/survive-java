package ru.pelmegov.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;

import static ru.pelmegov.graphic.animation.PlayerAnimation.PLAYER_DEFAULT_SPEED;

public class PlayerKeyboard {


    public Direction getDirectionKeyPressed(Body player) {
        Direction direction = null;

        int speed = 100;

        int horizontalForce = 0;
        int verticalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= speed;
            direction = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += speed;
            direction = Direction.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            verticalForce += speed;
            direction = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            verticalForce -= speed;
            direction = Direction.DOWN;
        }

        player.setLinearVelocity(
                horizontalForce * PLAYER_DEFAULT_SPEED,
                verticalForce * PLAYER_DEFAULT_SPEED
        );

        return direction;
    }

}
