package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import jon.game.INIT.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		if(!MyGdxGame.fullscreen){
			config.width = MyGdxGame.V_WIDTH;
			config.height = MyGdxGame.V_HEIGHT;
		}
		config.x = -1;
		config.y = -1;
		config.fullscreen = MyGdxGame.fullscreen;
		config.title = MyGdxGame.title;
		config.foregroundFPS = 800;
		config.backgroundFPS = 800;
		
		new LwjglApplication(new MyGdxGame(), config);
	}
}
