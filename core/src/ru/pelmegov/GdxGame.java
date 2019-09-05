package ru.pelmegov;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class GdxGame extends ApplicationAdapter {

    private final static int DEFAULT_LOG_LEVEL = Application.LOG_NONE;

    public GdxGame() {
        Gdx.app.setLogLevel(DEFAULT_LOG_LEVEL);
    }

    public GdxGame(int logLevel) {
        Gdx.app.setLogLevel(logLevel);
    }

    @Override
    public void create() {
    }

    @Override
    public void render() {
    }

    @Override
    public void dispose() {
    }
}
