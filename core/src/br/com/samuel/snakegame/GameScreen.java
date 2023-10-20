package br.com.samuel.snakegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    final SnakeGame snakeGame;
    private Snake snake;
    private Apple apple;
    private Texture headSprite;
    private Texture tailSprite;

    public GameScreen(SnakeGame snakeGame) {
        this.snake = new Snake(0, 0, 16, 16);
        this.snakeGame = snakeGame;
        this.headSprite = new Texture(Gdx.files.internal("head.png"));
        this.tailSprite = new Texture(Gdx.files.internal("tail.png"));
        this.camera = new OrthographicCamera();
        this.apple = new Apple(16, 16);
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
        spawSnake();
        snakeGame.batch.draw(apple.spr, apple.x, apple.y);
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
            snake.body.add(new Rectangle(snake.body.get(1).x, snake.body.get(1).y, 16, 16));
            apple.setRandomPosition();
        }
    }

    public void checkSnakeDeath() {
        for (int i = 1; i < snake.body.size; i++) {
            if (snake.head.overlaps(snake.body.get(i))) {
                snakeGame.setScreen(new GameScreen(snakeGame));
            }
        }
    }
}
