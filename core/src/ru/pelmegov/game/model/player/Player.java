package ru.pelmegov.game.model.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import ru.pelmegov.game.Direction;
import ru.pelmegov.graphic.sprite.SpriteContainer;
import ru.pelmegov.graphic.sprite.SpriteName;
import ru.pelmegov.physic.BodyDefinitionBuilder;
import ru.pelmegov.physic.PhysicalObject;

import java.util.Random;

public class Player extends PhysicalObject {

    public static final int PLAYER_WIDTH = 12;
    public static final int PLAYER_HEIGHT = 16;
    public static final int PLAYER_DEFAULT_SPEED = 100;

    private Direction direction;
    private Sprite sprite;
    private PlayerAnimation playerAnimation;

    public Player(int id, Vector2 position) {
        super(id, makeBodyDefinition(position));
        this.sprite = SpriteContainer.getInstance().getSprite(SpriteName.PLAYER_1);
        this.playerAnimation = new PlayerAnimation(sprite);
    }

    public Player() {
        super(makeBodyDefinition(null));
        this.sprite = SpriteContainer.getInstance().getSprite(SpriteName.PLAYER_1);
        this.playerAnimation = new PlayerAnimation(sprite);
    }

    @Override
    protected void injectPolygonShape(Body body) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH, PLAYER_HEIGHT);
        body.createFixture(shape, 1f);
        shape.dispose();
    }

    private static BodyDef makeBodyDefinition(Vector2 position) {
        BodyDefinitionBuilder builder = BodyDefinitionBuilder.builder();
        if (position != null) {
            builder.position(position);
        } else {
            builder.position(
                    new Random().nextInt(Gdx.graphics.getWidth()),
                    new Random().nextInt(Gdx.graphics.getHeight())
            );
        }
        return builder
                .type(BodyDef.BodyType.DynamicBody)
                .fixedRotation(true)
                .build();
    }

    public void update() {
        playerAnimation.update();
    }

    // getters and setters

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    private class PlayerAnimation {

        private static final int FRAME_COLS = 9;
        private static final int FRAME_ROWS = 4;
        private TextureRegion[][] textureRegions;
        private float stateTime;

        PlayerAnimation(Sprite playerSprite) {
            Texture texture = playerSprite.getTexture();
            this.textureRegions = TextureRegion.split(texture,
                    texture.getWidth() / FRAME_COLS,
                    texture.getHeight() / FRAME_ROWS);
        }

        void update() {
            stateTime += Gdx.graphics.getDeltaTime();
            Sprite animatedSprite;
            if (getDirection() == null) {
                animatedSprite = new Sprite(new Animation<>(0f, animateMove(Direction.DOWN)).getKeyFrame(stateTime, false));
            } else {
                animatedSprite = new Sprite(new Animation<>(0.05f, animateMove(getDirection())).getKeyFrame(stateTime, true));
            }
            setSprite(animatedSprite);
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

}
