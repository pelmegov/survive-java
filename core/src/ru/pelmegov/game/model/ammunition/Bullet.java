package ru.pelmegov.game.model.ammunition;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.graphic.sprite.SpriteContainer;
import ru.pelmegov.graphic.sprite.SpriteName;
import ru.pelmegov.physic.BodyDefinitionBuilder;
import ru.pelmegov.physic.PhysicalObject;
import ru.pelmegov.util.GraphicUtils;

public class Bullet extends PhysicalObject {

    public static final float BULLET_RADIUS = 4;
    public static final int BULLET_VELOCITY = 10;

    private final Vector2 position;

    private final double xVelocity;
    private final double yVelocity;

    private Sprite sprite;

    public Bullet(int screenX, int screenY) {
        super(makeBodyDefinition());

        this.position = new Vector2(getBody().getPosition());

        Vector2 clickedPosition = GraphicUtils.unprojectVector(new Vector2(screenX, screenY));
        float length = (float) Math.sqrt((clickedPosition.x - position.x) * (clickedPosition.x - position.x) + (clickedPosition.y - position.y) * (clickedPosition.y - position.y));

        this.xVelocity = (clickedPosition.x - position.x) / length * (float) BULLET_VELOCITY;
        this.yVelocity = (clickedPosition.y - position.y) / length * (float) BULLET_VELOCITY;

        this.sprite = SpriteContainer.getInstance().getSprite(SpriteName.BULLET);
    }

    @Override
    protected void injectPolygonShape(Body body) {
        Shape shape = new CircleShape();
        shape.setRadius(BULLET_RADIUS);
        body.createFixture(shape, 1.0f);
        shape.dispose();
    }

    private static BodyDef makeBodyDefinition() {
        return new BodyDefinitionBuilder()
                .position(new Vector2(GameContext.currentPlayer.getBody().getPosition()))
                .type(BodyDef.BodyType.DynamicBody)
                .bullet(true)
                .fixedRotation(true)
                .build();
    }

    public void update() {
        position.x += xVelocity;
        position.y += yVelocity;
        getBody().setTransform(position, getBody().getAngle());
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}