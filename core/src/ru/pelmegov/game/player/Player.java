package ru.pelmegov.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

public class Player {

    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 24;

    public static Body createPlayer(World world) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        int xPosition = new Random().nextInt(Gdx.graphics.getWidth());
        int yPosition = new Random().nextInt(Gdx.graphics.getHeight());

        def.position.set(xPosition, yPosition);
        def.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH / 2f, PLAYER_HEIGHT / 2f);

        Body pBody = world.createBody(def);
        pBody.createFixture(shape, 1.0f);

        shape.dispose();
        return pBody;
    }

}
