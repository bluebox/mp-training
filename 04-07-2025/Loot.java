package com.prana;


public enum Loot {
    GOLD_COIN(100),
    PEARL_NECKLACE(250),
    RUBY(500),
    SILVER_RING(50),
    EMERALD(300);

    private final int score;

    Loot(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    }