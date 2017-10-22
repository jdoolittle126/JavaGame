package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import jon.game.core.GameClient;
import jon.tools.gui.MapEditor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		
		

		config.width = MapEditor.V_WIDTH;
		config.height = MapEditor.V_HEIGHT;
		config.x = -1;
		config.y = -1;
		config.title = "Map Editor";
		
		new LwjglApplication(new MapEditor(), config);
		/*
		if(!GameClient.fullscreen){
			config.width = GameClient.V_WIDTH;
			config.height = GameClient.V_HEIGHT;
		}
		config.x = -1;
		config.y = -1;
		config.fullscreen = GameClient.fullscreen;
		config.title = GameClient.title;
		config.foregroundFPS = 800;
		config.backgroundFPS = 800;

		new LwjglApplication(new GameClient(), config);
		*/
	}
}
