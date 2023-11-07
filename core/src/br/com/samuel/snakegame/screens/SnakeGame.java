package br.com.samuel.snakegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends Game {
	SpriteBatch batch;
	BitmapFont font;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
		font.setColor(new Color(34 / 255f, 32 / 255f, 52 / 255f, 1.0f));
		this.setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
