package ru.pelmegov.game.ammunition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Bullet {

    public static final int BULLET_VELOCITY = 500;

    private final Vector2 bulletPosition;
    private final Vector2 mousePosition;

    public Bullet(Vector2 bulletStartPosition, Vector2 mousePosition) {
        this.bulletPosition = bulletStartPosition;
        this.mousePosition = mousePosition.nor();
    }

    public void update() {
        bulletPosition.x += BULLET_VELOCITY * mousePosition.x * Gdx.graphics.getDeltaTime();
        bulletPosition.y += BULLET_VELOCITY * mousePosition.y * Gdx.graphics.getDeltaTime();
    }

    public Vector2 getBulletPosition() {
        return bulletPosition;
    }
}