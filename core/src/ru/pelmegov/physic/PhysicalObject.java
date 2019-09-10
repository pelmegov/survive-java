package ru.pelmegov.physic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.util.IdentifiableObject;

public abstract class PhysicalObject extends IdentifiableObject {

    private final Body body;

    public PhysicalObject(BodyDef bodyDef) {
        this.body = GameContext.world.createBody(bodyDef);
        this.injectPolygonShape(body);
    }

    public PhysicalObject(Integer id, BodyDef bodyDef) {
        super(id);
        this.body = GameContext.world.createBody(bodyDef);
        this.injectPolygonShape(body);
    }

    public Body getBody() {
        return body;
    }

    protected void injectPolygonShape(Body body) {
        // default do nothing
    }

}
