package ru.pelmegov.game.ammunition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import ru.pelmegov.game.GameContext;

import java.util.ArrayList;
import java.util.List;

public class BulletHolder {

    private static List<Bullet> bullets = new ArrayList<>();
    private static float allowBullet = 0;

    public static List<Bullet> getBullets(GameContext gameContext) {
        if (Gdx.input.isTouched()) {
            if (allowBullet > 0.2) {
                allowBullet = 0;
                int touchX = Gdx.input.getX();
                int touchY = Gdx.input.getY();
                Vector2 clickedPosition = new Vector2(touchX, touchY);

                float playerX = gameContext.getCurrentPlayer().getBody().getPosition().x;
                float playerY = gameContext.getCurrentPlayer().getBody().getPosition().y;
                Vector2 playerPosition = new Vector2(playerX, playerY);

                bullets.add(new Bullet(playerPosition, clickedPosition));
            }
        }
        allowBullet += Gdx.graphics.getDeltaTime();
        return bullets;
    }
}
