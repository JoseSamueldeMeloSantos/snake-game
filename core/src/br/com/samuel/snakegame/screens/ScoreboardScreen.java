package br.com.samuel.snakegame.screens;

import br.com.samuel.snakegame.entities.Score;
import br.com.samuel.snakegame.model.ScoreSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScoreboardScreen implements Screen {

    private SnakeGame snakeGame;
    private ScoreSystem scoreSystem;
    private Texture scoreBackground;

    public ScoreboardScreen(SnakeGame snakeGame,String name, Integer score) {
        this.snakeGame = snakeGame;
        this.scoreSystem = new ScoreSystem();
        this.scoreBackground = new Texture(Gdx.files.internal("scoreBackground.png"));
        scoreSystem.addScore(new Score(name, score));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        snakeGame.batch.begin();
        snakeGame.batch.draw(scoreBackground, 0,0);

        int cont = 0;
        for (Score score: scoreSystem.getScores()) {
           snakeGame.font.draw(
                   snakeGame.batch,
                   score.getName() + "...."  + score.getPoints(),
                   170, 100 - cont);
           cont += 15;
       }
        snakeGame.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            snakeGame.setScreen(new MainScreen(snakeGame));
        }
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
