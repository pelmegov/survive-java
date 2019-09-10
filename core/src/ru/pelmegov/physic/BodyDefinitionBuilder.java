package ru.pelmegov.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class BodyDefinitionBuilder {

    private final BodyDef bodyDef = new BodyDef();

    public static BodyDefinitionBuilder builder() {
        return new BodyDefinitionBuilder();
    }

    public BodyDef build() {
        return bodyDef;
    }


    public BodyDefinitionBuilder position(float x, float y) {
        this.bodyDef.position.x = x;
        this.bodyDef.position.y = y;
        return this;
    }

    public BodyDefinitionBuilder position(Vector2 position) {
        this.bodyDef.position.x = position.x;
        this.bodyDef.position.y = position.y;
        return this;
    }


    public BodyDefinitionBuilder linearVelocity(float x, float y) {
        this.bodyDef.linearVelocity.x = x;
        this.bodyDef.linearVelocity.y = y;
        return this;
    }

    public BodyDefinitionBuilder linearVelocity(Vector2 position) {
        this.bodyDef.linearVelocity.x = position.x;
        this.bodyDef.linearVelocity.y = position.y;
        return this;
    }


    public BodyDefinitionBuilder type(BodyDef.BodyType type) {
        this.bodyDef.type = type;
        return this;
    }

    public BodyDefinitionBuilder angle(float angle) {
        this.bodyDef.angle = angle;
        return this;
    }

    public BodyDefinitionBuilder angularVelocity(float angularVelocity) {
        this.bodyDef.angularVelocity = angularVelocity;
        return this;
    }

    public BodyDefinitionBuilder linearDamping(float linearDamping) {
        this.bodyDef.linearDamping = linearDamping;
        return this;
    }

    public BodyDefinitionBuilder angularDamping(float angularDamping) {
        this.bodyDef.angularDamping = angularDamping;
        return this;
    }

    public BodyDefinitionBuilder allowSleep(boolean allowSleep) {
        this.bodyDef.allowSleep = allowSleep;
        return this;
    }

    public BodyDefinitionBuilder awake(boolean awake) {
        this.bodyDef.awake = awake;
        return this;
    }

    public BodyDefinitionBuilder fixedRotation(boolean fixedRotation) {
        this.bodyDef.fixedRotation = fixedRotation;
        return this;
    }

    public BodyDefinitionBuilder bullet(boolean bullet) {
        this.bodyDef.bullet = bullet;
        return this;
    }

    public BodyDefinitionBuilder active(boolean active) {
        this.bodyDef.active = active;
        return this;
    }

    public BodyDefinitionBuilder gravityScale(float gravityScale) {
        this.bodyDef.gravityScale = gravityScale;
        return this;
    }
}
