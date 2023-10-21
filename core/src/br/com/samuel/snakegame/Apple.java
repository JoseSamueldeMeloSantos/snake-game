package br.com.samuel.snakegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Apple extends Rectangle {

    Texture spr;

    public Apple(int width, int heigth) {
        this.width = width;
        this.height = heigth;
        spr = new Texture(Gdx.files.internal("apple.png"));
    }

    public Apple(int x, int y, int width, int heigth) {
        this(width, heigth);
        this.x = x;
        this.y = y;
    }

    public void setPosition(int x, int y) {
       this.x = x;
       this.y = y;
    }
}
