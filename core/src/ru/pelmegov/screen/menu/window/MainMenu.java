package ru.pelmegov.screen.menu.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import ru.pelmegov.screen.menu.MenuScreen;
import ru.pelmegov.util.GameHolder;

import static ru.pelmegov.screen.ScreenName.START_GAME;
import static ru.pelmegov.util.Constant.*;

public class MainMenu extends AbstractMenu {

    private static final String title = "Main Menu";

    public MainMenu(Skin skin, MenuScreen menuScreen) {
        super(title, skin, menuScreen);
        this.initialize();
    }

    @Override
    protected void initialize() {
        this.buildWidgets();
        this.finalizeWindow();
    }

    private void buildWidgets() {
        Table table = new Table();
        table.pad(10);

        table.row();
        addMultiplayerButton(table);

        table.row();
        addSettingsButton(table);

        add(table);
    }

    private void addSettingsButton(Table table) {
        TextButton textButtonSettings = new TextButton("Settings", skin);
        textButtonSettings.pad(BUTTONS_PADDING);
        table.add(textButtonSettings).size(BUTTONS_WIDTH, BUTTONS_HEIGHT).pad(BUTTONS_ROW_PADDING);
    }

    private void addMultiplayerButton(Table table) {
        TextButton textButtonMultiplayer = new TextButton("Start Game", skin);
        textButtonMultiplayer.pad(BUTTONS_PADDING);
        textButtonMultiplayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onStartGameButtonClicked();
            }
        });
        table.add(textButtonMultiplayer).size(BUTTONS_WIDTH, BUTTONS_HEIGHT).pad(BUTTONS_ROW_PADDING);
    }

    private void finalizeWindow() {
        setDefaultSizeAndPosition();
        setDefaultStyle();
        setVisible(false);
    }

    private void setDefaultSizeAndPosition() {
        setWidth(200);
        setHeight(320);

        float x = Gdx.graphics.getWidth() / 2f - getWidth() / 2;
        float y = Gdx.graphics.getHeight() / 2f - getHeight() / 2;
        setPosition(x, y);
    }

    private void setDefaultStyle() {
        setColor(1, 1, 1, 0.8f);
    }

    private void onStartGameButtonClicked() {
        GameHolder.getGameInstance().setNextScreen(START_GAME);
    }

}
