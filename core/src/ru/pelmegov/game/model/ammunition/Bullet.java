package ru.pelmegov.game.model.ammunition;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.graphic.sprite.SpriteContainer;
import ru.pelmegov.graphic.sprite.SpriteName;
import ru.pelmegov.util.GraphicUtils;

public class Bullet {

    public static final int BULLET_VELOCITY = 100;

    private final Vector2 position;

    private final double xVelocity;
    private final double yVelocity;

    private Sprite sprite;

    public Bullet(int screenX, int screenY) {
        this.position = GameContext.currentPlayer.getBody().getPosition();

        Vector2 clickedPosition = GraphicUtils.unprojectVector(new Vector2(screenX, screenY));
        float length = (float) Math.sqrt((clickedPosition.x - position.x) * (clickedPosition.x - position.x) + (clickedPosition.y - position.y) * (clickedPosition.y - position.y));

        this.xVelocity = (clickedPosition.x - position.x) / length * (float) BULLET_VELOCITY;
        this.yVelocity = (clickedPosition.y - position.y) / length * (float) BULLET_VELOCITY;

        this.sprite = SpriteContainer.getInstance().getSprite(SpriteName.BULLET);
    }

    public void update() {
        position.x += xVelocity;
        position.y += yVelocity;
        sprite.setPosition(getPosition().x, getPosition().y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}