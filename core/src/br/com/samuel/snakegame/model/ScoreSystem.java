package br.com.samuel.snakegame.model;

import br.com.samuel.snakegame.dao.DataRecoder;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class ScoreSystem implements ScoreManager {

    private Map<String, Integer> scores = new HashMap<>();
    private final int LIMIT = 5;
    private DataRecoder dr = new DataRecoder();

    public ScoreSystem() {
        recorverData();
    }

    private void recorverData() {
        try {
            Map<String, Integer> aux = dr.recover();
            if (aux != null) {
                scores = aux;
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null
                    ,"It wasn't possible to recover the data");
        }
    }

    public void recorderData() {
        try {
            dr.recorder(scores);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null
                    ,"It wasn't possible to recoder the data");
        }
    }

    @Override
    public void addScore(String name, Integer score) {
        sortSore();
        if (checkQuantity() < LIMIT) {
            scores.put(name, score);
            sortSore();
        }
        else {
            for (Integer sco: scores.values()) {
                if (score >= sco) {
                    scores.put(name, score);
                    if (!checkLimit()) {
                        removeLastScore();
                    }
                    sortSore();
                    recorderData();
                }
            }

        }
    }

    @Override
    public void sortSore() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        Map<String, Integer> sortedHashMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }

        scores = sortedHashMap;
    }


    @Override
    public void removeLastScore() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());


        if (!list.isEmpty()) {
            list.remove(list.size() - 1);

            HashMap<String, Integer> newHashMap = new HashMap<>();
            for (Map.Entry<String, Integer> entry : list) {
                newHashMap.put(entry.getKey(), entry.getValue());
            }

            scores = newHashMap;
        }
    }

    @Override
    public Integer reseachValueByName(String name) {
        return scores.get(name);
    }

    @Override
    public int checkQuantity() {
        return scores.size();
    }

    @Override
    public boolean checkLimit() {
        return scores.size() <= LIMIT;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }
}
