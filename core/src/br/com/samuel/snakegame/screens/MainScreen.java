package br.com.samuel.snakegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class MainScreen implements Screen {

    private Texture mainBackground;
    private SnakeGame snakeGame;

    public MainScreen(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
        snakeGame.font.getData().setScale(1);
        this.mainBackground = new Texture(Gdx.files.internal("mainBackground.png"));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        snakeGame.batch.begin();
        snakeGame.batch.draw(mainBackground, 0, 0);
        snakeGame.font.draw(snakeGame.batch, "Press any key to start", 130, 50);
        snakeGame.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
            snakeGame.setScreen(new GameScreen(snakeGame));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
