package br.com.samuel.snakegame.entities;

public class Score {

    private Integer points;
    private String name;

    public Score(String name, Integer points) {
        this.points = points;
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
