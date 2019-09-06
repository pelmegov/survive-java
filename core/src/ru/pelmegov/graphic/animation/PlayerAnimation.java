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

        Animation<TextureRegion> playerAnimation;
        if (direction == null) {
            playerAnimation = new Animation<>(0f, animateMove(Direction.DOWN));
        } else {
            playerAnimation = new Animation<>(0.05f, animateMove(direction));
        }

        return new Sprite(playerAnimation.getKeyFrame(stateTime, true));
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
