package ru.pelmegov.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import ru.pelmegov.game.model.player.Player;

import static ru.pelmegov.game.Direction.*;

public class PlayerKeyboardInputProcessor implements InputProcessor {

    private final Player player;

    public PlayerKeyboardInputProcessor(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        float horizontalForce = 0f;
        float verticalForce = 0f;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setDirection(LEFT);
            horizontalForce -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setDirection(RIGHT);
            horizontalForce += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setDirection(UP);
            verticalForce += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setDirection(DOWN);
            verticalForce -= 1;
        }

        player.getBody().setLinearVelocity(
                new Vector2(horizontalForce * Player.PLAYER_DEFAULT_SPEED, verticalForce * Player.PLAYER_DEFAULT_SPEED)
        );

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        player.getBody().setLinearVelocity(new Vector2(0, 0));
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
