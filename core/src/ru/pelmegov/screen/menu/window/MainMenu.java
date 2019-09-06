package ru.pelmegov.screen.menu.window;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import ru.pelmegov.screen.menu.MenuScreen;
import ru.pelmegov.util.GameHolder;

import static ru.pelmegov.screen.ScreenName.START_GAME;
import static ru.pelmegov.util.Constant.*;
import static ru.pelmegov.util.GraphicUtils.getWindowCenterX;
import static ru.pelmegov.util.GraphicUtils.getWindowCenterY;

public class MainMenu extends AbstractMenu {

    private static final String title = "Main Menu";

    public MainMenu(Skin skin, MenuScreen menuScreen, boolean visible) {
        super(title, skin, menuScreen, visible);
    }

    @Override
    protected void initialize() {
        WidgetGroup menuTable = createMenuTable();
        new WindowRenderer(menuTable).render();
    }

    /**
     * Method prepare table with buttons on the main menu
     */
    private WidgetGroup createMenuTable() {
        Table table = new Table();
        table.pad(BUTTONS_PADDING);

        table.row();
        addStartGameButton(table);

        table.row();
        addSettingsButton(table);

        return table;
    }

    /**
     * Method creates "start game" button
     * and put it in the table from parameter
     *
     * @param table parameter, which holds this button
     */
    private void addStartGameButton(Table table) {
        TextButton textButtonStartGame = new TextButton("Start Game", skin);
        textButtonStartGame.pad(BUTTONS_PADDING);
        textButtonStartGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameHolder.getGameInstance().setNextScreen(START_GAME);
            }
        });
        table.add(textButtonStartGame).size(BUTTONS_WIDTH, BUTTONS_HEIGHT).pad(BUTTONS_ROW_PADDING);
    }

    /**
     * Method creates settings button
     * and put it in the table from parameter
     *
     * @param table parameter, which holds this button
     */
    private void addSettingsButton(Table table) {
        TextButton textButtonSettings = new TextButton("Settings", skin);
        textButtonSettings.pad(BUTTONS_PADDING);
        // todo add listener for settings button
        table.add(textButtonSettings).size(BUTTONS_WIDTH, BUTTONS_HEIGHT).pad(BUTTONS_ROW_PADDING);
    }


    /**
     * Class, which render menu window with buttons
     */
    private class WindowRenderer {

        private final WidgetGroup menuTable;

        private int width = 200;
        private int height = 320;

        WindowRenderer(WidgetGroup menuTable) {
            this.menuTable = menuTable;
        }

        private void render() {
            add(menuTable);

            defaultSize();
            defaultPosition();
            defaultStyle();
        }

        private void defaultSize() {
            setWidth(width);
            setHeight(height);
        }

        private void defaultPosition() {
            setPosition(getWindowCenterX(width), getWindowCenterY(height));
        }

        private void defaultStyle() {
            setColor(1, 1, 1, 0.8f);
        }
    }

}
