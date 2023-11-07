package br.com.samuel.snakegame.entities;

public enum Direction {

    STOPPED(0), UP(1), DOWN(2),
    LEFT(3), RIGHT(4);

    int v;

    Direction(int v) {
        this.v = v;
    }
}
