package br.com.samuel.snakegame.model;

import br.com.samuel.snakegame.entities.Score;

public interface ScoreManager {

    void addScore(Score score);

    void sortSores();

    void removeLastScore();

    Score reseachValueByName(String name);

    int checkQuantity();

    boolean checkLimit();
}
