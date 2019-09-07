package ru.pelmegov.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import lombok.Data;
import ru.pelmegov.game.PlayerKeyboard;
import ru.pelmegov.graphic.animation.PlayerAnimation;

import java.util.Random;

@Data
public class Player {

    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 24;

    private final Body body;
    private final PlayerAnimation playerAnimation;
    private final PlayerKeyboard playerKeyboard;

    public Player(World world, PlayerAnimation playerAnimation, PlayerKeyboard playerKeyboard) {
        this.body = makeBody(world);
        this.playerAnimation = playerAnimation;
        this.playerKeyboard = playerKeyboard;
    }

    public Sprite draw() {
        Sprite player = playerAnimation.getSprite(playerKeyboard.getDirectionKeyPressed(this.body));
        player.setPosition(this.body.getPosition().x - PLAYER_WIDTH, this.body.getPosition().y - PLAYER_HEIGHT);
        return player;
    }

    private Body makeBody(World world) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        int xPosition = new Random().nextInt(Gdx.graphics.getWidth());
        int yPosition = new Random().nextInt(Gdx.graphics.getHeight());

        def.position.set(xPosition, yPosition);
        def.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH / 2f, PLAYER_HEIGHT / 2f);

        Body body = world.createBody(def);
        body.createFixture(shape, 1.0f);

        shape.dispose();
        return body;
    }

}
