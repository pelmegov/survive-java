package ru.pelmegov.screen.menu.window;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import ru.pelmegov.screen.menu.MenuScreen;

public abstract class AbstractMenu extends Window {

    protected MenuScreen menuScreen;
    protected Skin skin;

    public AbstractMenu(String title, Skin skin, MenuScreen menuScreen) {
        super(title, skin);

        this.skin = skin;
        this.menuScreen = menuScreen;
    }

    protected abstract void initialize();

}
