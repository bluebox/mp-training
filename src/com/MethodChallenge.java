package com;

public class MethodChallenge {
    public static void displayHighScorePosition(String playerName, int playerPosition) {
        System.out.println(playerName + " managed to get into position " + playerPosition + " on the high score list");
    }

    public static int calculateHighScorePosition(int playerScore) {
        if (playerScore >= 1000)
            return 1;
        else if (playerScore >= 500)
            return 2;
        else if (playerScore >= 100)
            return 3;
        return 4;
    }

    public static void main(String[] args) {
        int calcHighScorePosFor1500 = calculateHighScorePosition(1500);
        displayHighScorePosition("Tim", calcHighScorePosFor1500);

        int calcHighScorePosFor1000 = calculateHighScorePosition(1000);
        displayHighScorePosition("Mourya", calcHighScorePosFor1000);

        int calcHighScorePosFor500 = calculateHighScorePosition(500);
        displayHighScorePosition("Surya", calcHighScorePosFor500);

        int calcHighScorePosFor100 = calculateHighScorePosition(100);
        displayHighScorePosition("Yaswini", calcHighScorePosFor100);

        int calcHighScorePosFor25 = calculateHighScorePosition(25);
        displayHighScorePosition("Namratha", calcHighScorePosFor25);
    }
}
