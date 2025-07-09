package Project;

public enum Loot {
    GOLD_COIN(10),
    PEARL_NECKLACE(25),
    RUBY(40),
    EMERALD(35),
    SILVER_RING(15);

    private final int score;

    Loot(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}

