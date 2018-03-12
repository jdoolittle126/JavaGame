package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

import jon.game.core.GameClient;
import jon.tools.gui.MapEditor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		if(arg.length != 0 && arg[0].toLowerCase().trim().equals("-editor")){
			launchEditor();
		} else {
			launchGame();
		}
		
	}
	
	public static void launchEditor(){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = MapEditor.V_WIDTH;
		config.height = MapEditor.V_HEIGHT;
		config.x = -1;
		config.y = -1;
		config.title = "Map Editor";
		
		new LwjglApplication(new MapEditor(), config);
	}
	
	public static void launchGame(){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Settings settings = new Settings();
		
		if(!GameClient.fullscreen){
			config.width = GameClient.V_WIDTH;
			config.height = GameClient.V_HEIGHT;
		}
		
		config.x = -1;
		config.y = -1;
		config.fullscreen = GameClient.fullscreen;
		config.title = GameClient.title;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;

		settings.maxWidth = 512;
		settings.maxHeight = 512;
		
		TexturePacker.process(settings, "assets/textures", "assets/packs", "texture_assets");
		System.out.println("Assets Packed!");
		
		new LwjglApplication(new GameClient(), config);
	}
	
}
