package br.com.samuel.snakegame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import br.com.samuel.snakegame.screens.SnakeGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(10);
		config.setWindowedMode(400, 240);
		config.setTitle("Snake Game");
		new Lwjgl3Application(new SnakeGame(), config);
	}
}
