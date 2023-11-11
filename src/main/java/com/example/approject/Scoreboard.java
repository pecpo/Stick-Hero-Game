package com.example.approject;

public class Scoreboard {
    private int score;

    public Scoreboard() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        if (points > 0) {
            score += points;
        }
    }

    public void resetScore() {
        score = 0;
    }
}
