package br.com.samuel.snakegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Apple extends Rectangle {

    Texture spr;

    public Apple(int x, int y, int width, int heigth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = heigth;
        spr = new Texture(Gdx.files.internal("apple.png"));
    }

    public void setRandomPosition() {
        this.x = 16 * MathUtils.random(0, 25);
        this.y = 16 * MathUtils.random(0, 15);
    }

    public boolean isAppleOverLapsSnake() {
        return false;
    }
}
