package ru.pelmegov.game.model.ammunition;

import com.badlogic.gdx.math.Vector2;

public class Bullet {

    public static final int BULLET_VELOCITY = 10;

    private final Vector2 bulletPosition;
    private final Vector2 mousePosition;

    private final double xVelocity;
    private final double yVelocity;

    public Bullet(Vector2 bulletStartPosition, Vector2 mousePosition) {
        this.bulletPosition = bulletStartPosition;
        this.mousePosition = mousePosition;
        float length = (float) Math.sqrt((mousePosition.x - bulletPosition.x) * (mousePosition.x - bulletPosition.x) + (mousePosition.y - bulletPosition.y) * (mousePosition.y - bulletPosition.y));
        xVelocity = (mousePosition.x - bulletPosition.x) / length * (float) BULLET_VELOCITY;
        yVelocity = (mousePosition.y - bulletPosition.y) / length * (float) BULLET_VELOCITY;
    }

    public Vector2 update() {
        bulletPosition.x += xVelocity;
        bulletPosition.y += yVelocity;
        return bulletPosition;
    }
}