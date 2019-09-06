package ru.pelmegov.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.screen.AbstractScreen;
import ru.pelmegov.screen.game.world.WorldRenderer;

public class GameProcessScreen extends AbstractScreen {

    private GameContext gameContext;
    private WorldRenderer worldRenderer;

    public GameProcessScreen(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    @Override
    public void show() {
        initializeWorld2dBox();
        initializeWorldRenderer();
        initializeCamera();
        initializeInputManagers();
    }

    @Override
    public void render(float delta) {
        worldRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    private void initializeWorld2dBox() {
        gameContext.setWorld(new World(new Vector2(0f, 0f), false));
        gameContext.setB2dr(new Box2DDebugRenderer());
    }

    private void initializeWorldRenderer() {
        worldRenderer = new WorldRenderer(gameContext);
    }

    private void initializeCamera() {
        OrthographicCamera worldCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        worldCamera.lookAt(0, 0, 0);
        worldCamera.translate(800, 800);
        worldCamera.zoom = 1f;
        worldCamera.update();

        gameContext.setWorldCamera(worldCamera);
    }

    private void initializeInputManagers() {
    }

}
