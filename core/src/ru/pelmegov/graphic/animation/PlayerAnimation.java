package ru.pelmegov.graphic.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.pelmegov.game.Direction;

public class PlayerAnimation {

    private static final int FRAME_COLS = 9;
    private static final int FRAME_ROWS = 4;

    public static final int PLAYER_DEFAULT_SPEED = 100;

    private TextureRegion[][] textureRegions;
    private float stateTime;

    public PlayerAnimation(Sprite playerSprite) {
        Texture texture = playerSprite.getTexture();
        this.textureRegions = TextureRegion.split(texture,
                texture.getWidth() / FRAME_COLS,
                texture.getHeight() / FRAME_ROWS);
    }

    public Sprite getSprite(Direction direction) {
        stateTime += Gdx.graphics.getDeltaTime();
        if (direction == null) {
            return new Sprite(new Animation<>(0f, animateMove(Direction.DOWN)).getKeyFrame(stateTime, false));
        } else {
            return new Sprite(new Animation<>(0.05f, animateMove(direction)).getKeyFrame(stateTime, true));
        }
    }

    private TextureRegion[] animateMove(Direction direction) {
        TextureRegion[] moveRegions = new TextureRegion[FRAME_COLS];
        int index = 0;
        for (int i = 0; i < FRAME_COLS; i++) {
            moveRegions[index++] = textureRegions[direction.getValue()][i];
        }
        return moveRegions;
    }

}
