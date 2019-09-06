package ru.pelmegov.graphic.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

public class SpriteContainer {

    private static SpriteContainer INSTANCE = null;

    private HashMap<SpriteName, Sprite> sprites = new HashMap<>();

    public static SpriteContainer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpriteContainer();
        }
        return INSTANCE;
    }

    public Sprite getSprite(SpriteName spriteName) {
        if (sprites.get(spriteName) != null) {
            return sprites.get(spriteName);
        }

        Gdx.app.debug(SpriteContainer.class.getName(), "Sprite " + spriteName + " not found");
        return null;
    }

    public void addSprite(SpriteName spriteName, Sprite sprite) {
        sprites.put(spriteName, sprite);
    }

}
