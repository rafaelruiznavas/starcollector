package com.ruiznavas.starfish.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ruiznavas.starfish.StarCollectorGame;
import com.ruiznavas.starfish.StarfishGame;
import com.ruiznavas.starfish.StarfishGameBeta;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Game juego = new StarCollectorGame();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height= 600;
		new LwjglApplication(juego, config);
	}
}
