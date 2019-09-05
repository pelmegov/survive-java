package ru.pelmegov.util;

import ru.pelmegov.GdxGame;

public class GameHolder {

    private static GdxGame GAME;

    public static void putGameInstance(GdxGame gdxGame) {
        GAME = gdxGame;
    }

    public static GdxGame getGameInstance() {
        if (GAME == null) {
            throw new IllegalStateException();
        }
        return GAME;
    }
}
