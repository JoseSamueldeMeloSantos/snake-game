package br.com.samuel.snakegame.model;

public interface ScoreManager {

    void addScore(String name, Integer score);

    void sortSore();

    void removeLastScore();

    Integer reseachValueByName(String name);

    int checkQuantity();

    boolean checkLimit();
}
