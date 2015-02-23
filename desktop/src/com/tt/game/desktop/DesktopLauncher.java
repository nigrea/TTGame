package com.tt.game.desktop;
//My Amazing Double Swag
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tt.game.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Epic Music... also Game";
		config.addIcon("testicon.png", Files.FileType.Internal);
		config.width = 1024;
		config.height = 768;
		new LwjglApplication(new MyGame(), config);
		//test 2
	}
}
//my swag
//screw ur swag nubbbb