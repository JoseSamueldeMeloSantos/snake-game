package br.com.samuel.snakegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends Game {
	SpriteBatch batch;
	BitmapFont font;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();

		this.setScreen(new GameScreen(this));
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
