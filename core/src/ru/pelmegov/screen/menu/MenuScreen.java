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

        this.initialize();
        this.initializeWindows();
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

    private void initialize() {
        this.initializeSkin();
    }

    private void initializeSkin() {
        this.skin = new Skin(
                Gdx.files.internal("layout/skin.json"),
                new TextureAtlas("layout/skin.atlas"));
    }

    private void initializeWindows() {
        this.stage.clear();
        this.initializeMainMenuWindow();
    }

    private void initializeMainMenuWindow() {
        MainMenu mainMenuWindow = new MainMenu(skin, this);
        mainMenuWindow.setVisible(true);
        this.stage.addActor(mainMenuWindow);
    }

}
