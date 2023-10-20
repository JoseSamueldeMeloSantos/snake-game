package br.com.samuel.snakegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Apple extends Rectangle {

    Texture spr;

    public Apple(int width, int heigth) {
        setRandomPosition();
        this.width = width;
        this.height = heigth;
        spr = new Texture(Gdx.files.internal("apple.png"));
    }

    public void setRandomPosition() {
        this.x = 16 * MathUtils.random(1, 24) - 16;
        this.y = 16 * MathUtils.random(1, 14) - 16;
    }
}
