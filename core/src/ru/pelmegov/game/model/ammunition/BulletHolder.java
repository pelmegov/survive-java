package ru.pelmegov.game.model.ammunition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ru.pelmegov.game.GameContext;

import java.util.ArrayList;
import java.util.List;

public class BulletHolder {

    private static List<Bullet> bullets = new ArrayList<>();
    private static float allowBullet = 0;

    public static List<Bullet> getBullets() {
        if (Gdx.input.isTouched()) {
            if (allowBullet > 0.2) {
                allowBullet = 0;
                int touchX = Gdx.input.getX();
                int touchY = Gdx.input.getY();
                Vector2 clickedPosition = new Vector2(touchX, touchY);

                // revert top left corner for Y to bottom left corner
                Vector3 unproject = GameContext.worldCamera.unproject(
                        new Vector3(clickedPosition.x, clickedPosition.y, 0)
                );
                clickedPosition = new Vector2(unproject.x, unproject.y);

                Vector2 playerPosition = new Vector2(
                        GameContext.currentPlayer.getBody().getPosition().x,
                        GameContext.currentPlayer.getBody().getPosition().y);


                bullets.add(new Bullet(playerPosition, clickedPosition));
            }
        }
        allowBullet += Gdx.graphics.getDeltaTime();
        return bullets;
    }
}
