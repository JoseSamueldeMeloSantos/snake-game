package br.com.samuel.snakegame.screens;

import br.com.samuel.snakegame.entities.Apple;
import br.com.samuel.snakegame.entities.Snake;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.*;
import java.awt.*;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private final SnakeGame snakeGame;
    private Snake snake;
    private Apple apple;
    private Texture headSprite;
    private Texture tailSprite;
    private Texture backGroundSprite;
    private Integer score;

    public GameScreen(SnakeGame snakeGame) {
        this.snake = new Snake(0, 0, 16, 16);
        this.snakeGame = snakeGame;
        this.score = 0;
        this.headSprite = new Texture(Gdx.files.internal("head.png"));
        this.tailSprite = new Texture(Gdx.files.internal("tail.png"));
        this.backGroundSprite = new Texture(Gdx.files.internal("backGround.png"));
        this.camera = new OrthographicCamera();
        this.apple = new Apple(16, 16);
        setAppleRandomPosition();
        this.camera.setToOrtho(false, 400, 240);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);

        camera.update();

        snakeGame.batch.setProjectionMatrix(camera.combined);
        snakeGame.batch.begin();
        snakeGame.batch.draw(backGroundSprite, 0, 0);
        spawSnake();
        snakeGame.batch.draw(apple.spr, apple.x, apple.y,16, 16);
        snakeGame.font.draw(snakeGame.batch, "Score: " + score, 10, 225);
        snakeGame.batch.end();

        snake.moveSnake();
        checkEatApple();
        checkSnakeDeath();

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

    private void spawSnake() {
        snakeGame.batch.draw(headSprite, snake.body.get(0).x, snake.body.get(0).y);
        for (int i = 1; i < snake.body.size; i++) {
            snakeGame.batch.draw(tailSprite, snake.body.get(i).x, snake.body.get(i).y);
        }
    }

    private void checkEatApple() {
        Rectangle snakeHead = snake.body.get(0);
        if (snakeHead.overlaps(apple)) {
            setAppleRandomPosition();
            this.score += 3;
            snake.body.add(new Rectangle(snake.body.get(1).x, snake.body.get(1).y, 16, 16));
        }
    }

    private void setAppleRandomPosition() {
        boolean invalidPosition = false;
        int x;
        int y;
        do {
            x = 16 * MathUtils.random(1, 24) - 16;
            y = 16 * MathUtils.random(1, 14) - 16;
            Apple appleTest = new Apple(x, y, 16, 16);
            for (int i = 0; i < snake.body.size; i++) {
                if (appleTest.overlaps(snake.body.get(i))) {
                    invalidPosition = true;
                    break;
                }
                else {
                    invalidPosition = false;
                }
            }
        } while (invalidPosition);

        apple.setPosition(x, y);
    }

    public void checkSnakeDeath() {
        for (int i = snake.body.size - 1; i > 0; i--) {
            if (snake.head.overlaps(snake.body.get(i))) {
                String name = getName();
                snakeGame.setScreen(new ScoreboardScreen(snakeGame,name, score));
            }
        }
    }

    private String getName() {
        String name = "";
        do {
            name = JOptionPane.showInputDialog(
                    null,
                    "Put Your Nickname\n(Your name need to have three words)",
                    "", JOptionPane.NO_OPTION);

        } while (name.length() != 3);

        return name.toUpperCase();
    }
}
