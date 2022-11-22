package com.wsi.surianodimuro;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.useVsync(true);
		config.setResizable(true);
		config.setForegroundFPS(60);
		config.setWindowedMode(600, 400);
//		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.setTitle("23-19 || Servidor");
		config.setWindowIcon("logo.png");
		
		new Lwjgl3Application(new Juego(), config);
	}
}
