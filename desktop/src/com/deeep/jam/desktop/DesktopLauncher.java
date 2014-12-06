package com.deeep.jam.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.deeep.jam.screens.AbstractGame;
import com.deeep.jam.screens.Core;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) AbstractGame.VIRTUAL_WIDTH;
        config.height = (int) AbstractGame.VIRTUAL_HEIGHT;
        new LwjglApplication(new Core(), config);
    }
}
