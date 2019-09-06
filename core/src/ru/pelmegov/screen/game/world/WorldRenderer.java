package ru.pelmegov.screen.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import ru.pelmegov.graphic.SpriteContainer;
import ru.pelmegov.screen.game.GameProcessScreen;

import static ru.pelmegov.graphic.SpriteName.GRASS_1;
import static ru.pelmegov.util.Constant.TILE_SIZE_PIXELS;
import static ru.pelmegov.util.GraphicUtils.getWindowCenterX;
import static ru.pelmegov.util.GraphicUtils.getWindowCenterY;

public class WorldRenderer implements Disposable {

    private SpriteBatch batch;
    private GameProcessScreen gameProcessScreen;

    public WorldRenderer(GameProcessScreen gameProcessScreen) {
        this.gameProcessScreen = gameProcessScreen;

        initialize();
    }

    @Override
    public void dispose() {
    }

    private void initialize() {
        initializeSprites();
        initializeBatches();
    }

    private void initializeSprites() {
        initializeGroundSprites();
    }

    private void initializeGroundSprites() {
        Texture texture = new Texture("graphic/texture/ground/grass1.jpg");
        Sprite sprite = new Sprite(texture);
        SpriteContainer.getInstance().addSprite(GRASS_1, sprite);
    }

    private void initializeBatches() {
        batch = new SpriteBatch();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(gameProcessScreen.getWorldCamera().combined);

        batch.begin();

        renderGround();
        // todo render players

        batch.end();
    }

    private void renderGround() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Sprite sprite = SpriteContainer.getInstance().getSprite(GRASS_1);
                sprite.setPosition(i * TILE_SIZE_PIXELS, j * TILE_SIZE_PIXELS);
                sprite.draw(batch);
            }
        }
    }

}