package ru.pelmegov.game.player;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player {

    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 24;

    public static Body createPlayer(World world) {
        Body pBody;
        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(500, 500);
        def.fixedRotation = true;

        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH / 2f, PLAYER_HEIGHT / 2f);

        pBody.createFixture(shape, 1.0f);
        shape.dispose();
        return pBody;
    }

}
