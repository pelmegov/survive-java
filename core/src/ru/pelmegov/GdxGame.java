package ru.pelmegov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import ru.pelmegov.screen.menu.MenuScreen;

import static ru.pelmegov.util.Constant.DEFAULT_LOG_LEVEL;

public class GdxGame extends Game {

    private final int logLevel;

    public GdxGame() {
        this.logLevel = DEFAULT_LOG_LEVEL;
    }

    public GdxGame(int logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void create() {
        Gdx.app.setLogLevel(logLevel);
        setScreen(new MenuScreen());
    }

    @Override
    public void render() {
    }

    @Override
    public void dispose() {
    }
}
