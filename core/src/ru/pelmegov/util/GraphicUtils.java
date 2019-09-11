package ru.pelmegov.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ru.pelmegov.game.GameContext;

public class GraphicUtils {

    private GraphicUtils() {
        throw new UnsupportedOperationException();
    }

    public static float getWindowCenterX(float width) {
        return Gdx.graphics.getWidth() / 2f - width / 2;
    }

    public static float getWindowCenterY(float height) {
        return Gdx.graphics.getHeight() / 2f - height / 2;
    }

    public static Vector2 unprojectVector(Vector2 vector) {
        Vector3 unproject = GameContext.worldCamera.unproject(new Vector3(vector.x, vector.y, 0));
        vector = new Vector2(unproject.x, unproject.y);
        return vector;
    }

}
