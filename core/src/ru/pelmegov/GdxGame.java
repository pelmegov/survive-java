package ru.pelmegov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import ru.pelmegov.screen.ScreenName;
import ru.pelmegov.screen.game.GameProcessScreen;
import ru.pelmegov.screen.menu.MenuScreen;
import ru.pelmegov.util.GameHolder;

import static ru.pelmegov.util.Constant.DEFAULT_LOG_LEVEL;

public class GdxGame extends Game {

    private final int logLevel;

    private boolean paused = false;

    public GdxGame() {
        this.logLevel = DEFAULT_LOG_LEVEL;
    }

    public GdxGame(int logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void create() {
        GameHolder.putGameInstance(this);

        Gdx.app.setLogLevel(logLevel);
        setScreen(new MenuScreen());
    }

    @Override
    public void render() {
        if (!paused) {
            getScreen().render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void dispose() {
        getScreen().dispose();
    }

    public void setNextScreen(ScreenName screenName) {
        switch (screenName) {
            case START_GAME:
                startGame();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void startGame() {
        setScreen(new GameProcessScreen());
    }
}
