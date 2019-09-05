package ru.pelmegov.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.pelmegov.GdxGame;

import javax.swing.*;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = configureApp();

        try {
            new LwjglApplication(new GdxGame(Application.LOG_DEBUG), config);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, prepareErrorMessage(e));
            System.exit(0);
        }
    }

    private static LwjglApplicationConfiguration configureApp() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Survive Java";
        config.width = 1024;
        config.height = 680;

        config.backgroundFPS = 120;
        config.foregroundFPS = 120;
        config.vSyncEnabled = true; // Warning: affects game world update rate
        return config;
    }

    private static String prepareErrorMessage(Exception e) {
        StringBuilder errorMessage = new StringBuilder("Serious error occurred and the execution can not continue." + "\n\n");
        errorMessage.append("Error information" + ": " + "\n");
        errorMessage.append(e.toString()).append("\n");
        errorMessage.append("Call stack" + ": " + "\n");
        for (StackTraceElement element : e.getStackTrace()) {
            errorMessage.append(element.toString()).append("\n");
        }
        return errorMessage.toString();
    }

}
