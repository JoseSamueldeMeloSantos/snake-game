package br.com.samuel.snakegame.model;

import br.com.samuel.snakegame.dao.DataRecoder;
import br.com.samuel.snakegame.entities.Score;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class ScoreSystem implements ScoreManager {

    private List<Score> scores = new ArrayList<>();
    private final int LIMIT = 5;
    private DataRecoder dr = new DataRecoder();

    public ScoreSystem() {
        recorverData();

    }

    private void recorverData() {
        try {
            List<Score> aux = dr.recover();
            if (aux != null) {
                scores = aux;
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null
                    ,"It wasn't possible to recover the data");
        }
    }

    private void recorderData() {
        try {
            dr.recorder(scores);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null
                    ,"It wasn't possible to recoder the data");
        }
    }

    @Override
    public void addScore(Score score) {
        sortSores();
        if (checkQuantity() < LIMIT) {
            scores.add(score);
            sortSores();
            recorderData();
        }
        else {
            for (int i = 0; i < scores.size(); i++) {
                if (score.getPoints() > scores.get(i).getPoints()) {
                    scores.add(score);
                    sortSores();
                    removeLastScore();
                    recorderData();
                    break;
                }
            }
        }
    }

    @Override
    public void sortSores() {
        int n = scores.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (scores.get(j).getPoints() < scores.get(j + 1).getPoints()) {
                    Score temp = scores.get(j);
                    scores.set(j, scores.get(j + 1));
                    scores.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }


    @Override
    public void removeLastScore() {
        if (scores != null && !scores.isEmpty()) {
            scores.remove(scores.size() - 1);
        }
    }

    @Override
    public Score reseachValueByName(String name) {
        for (Score s: scores){
            if (s.getName().equals(name)) {
                return s;
            }
        }

        return null;
    }

    @Override
    public int checkQuantity() {
        return scores.size();
    }

    @Override
    public boolean checkLimit() {
        return scores.size() <= LIMIT;
    }

    public List<Score> getScores() {
        return scores;
    }
}
