package ru.pelmegov.graphic.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import ru.pelmegov.game.Direction;

public class PlayerAnimation {

    private static final int FRAME_COLS = 9;
    private static final int FRAME_ROWS = 4;

    public static final int PLAYER_DEFAULT_SPEED = 4;

    private TextureRegion[][] textureRegions;
    private float stateTime;

    public PlayerAnimation(Texture playerTexture) {
        this.textureRegions = TextureRegion.split(playerTexture,
                playerTexture.getWidth() / FRAME_COLS,
                playerTexture.getHeight() / FRAME_ROWS);
    }

    public Body createPlayer(World world) {
        Body pBody;
        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(500, 500);
        def.fixedRotation = true;

        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32 / 2 / 32f, 32 / 2 / 32f);

        pBody.createFixture(shape, 1.0f);
        shape.dispose();
        return pBody;
    }

    public TextureRegion getTexture(Direction direction) {
        stateTime += Gdx.graphics.getDeltaTime();

        Animation<TextureRegion> playerAnimation;
        if (direction == null) {
            playerAnimation = new Animation<>(0f, animateMove(Direction.DOWN));
        } else {
            playerAnimation = new Animation<>(0.05f, animateMove(direction));
        }

        return playerAnimation.getKeyFrame(stateTime, true);
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
