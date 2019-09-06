package ru.pelmegov.util;

import com.badlogic.gdx.Gdx;

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

}
