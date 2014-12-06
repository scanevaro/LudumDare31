package com.deeep.jam.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.deeep.jam.Game;
import com.deeep.jam.screens.Core;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 512;
        config.height = 512;
		new LwjglApplication(new Core(), config);
	}
}
