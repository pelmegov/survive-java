package ru.pelmegov.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ru.pelmegov.screen.AbstractScreen;
import ru.pelmegov.screen.game.world.WorldRenderer;

public class GameProcessScreen extends AbstractScreen {

    private WorldRenderer worldRenderer;
    private OrthographicCamera worldCamera;

    @Override
    public void show() {
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

    public OrthographicCamera getWorldCamera() {
        return worldCamera;
    }

    private void initializeWorldRenderer() {
        worldRenderer = new WorldRenderer(this);
    }

    private void initializeCamera() {
        worldCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        worldCamera.lookAt(0, 0, 0);
        worldCamera.translate(800, 800);
        worldCamera.zoom = 4;
        worldCamera.update();
    }

    private void initializeInputManagers() {

    }

}
