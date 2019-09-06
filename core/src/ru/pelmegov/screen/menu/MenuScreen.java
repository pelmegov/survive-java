package ru.pelmegov.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.pelmegov.screen.AbstractScreen;
import ru.pelmegov.screen.menu.window.MainMenu;

public class MenuScreen extends AbstractScreen {

    private Stage stage;
    private Skin skin;

    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.initializeSkin();
        this.initializeMenuWindow();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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

    /**
     * Method prepare skin for next usage
     */
    private void initializeSkin() {
        this.skin = new Skin(
                Gdx.files.internal("layout/skin.json"),
                new TextureAtlas("layout/skin.atlas")
        );
    }

    /**
     * Method prepare and initialize menu window inside screen
     */
    private void initializeMenuWindow() {
        this.stage.clear();
        this.stage.addActor(
                new MainMenu(skin, this, true)
        );
    }

}
