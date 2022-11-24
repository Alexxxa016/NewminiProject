package DecisionGame;

import java.io.Serializable;

public class Stats extends Player implements Serializable {
    private String gameTime;
    private float shortestGame;
    private float longestGame;

    public Stats() {
        this("Not Given","Not Given","Unknown",0,0);
    }
    public Stats(String nickname, String gender, String gameTime, float shortestGame, float longestGame) {
        super(nickname, gender);
        setGameTime(gameTime);
        setShortestGame(shortestGame);
    }

    public float getLongestGame() {
        return longestGame;
    }

    public void setLongestGame(float longestGame) {
        this.longestGame = longestGame;
    }

    public float getShortestGame() {
        return shortestGame;
    }

    public void setShortestGame(float shortestGame) {
        this.shortestGame = shortestGame;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    @Override
    public String toString() {
        return "Player:\n "+ "Nickname:" + getNickname() + "\nGender:" + getGender() + "\nPlayer ID: "+getPlayerID() +"\nGame Time: " + getGameTime() +
                "\nShortest Game: "+getShortestGame()+"\nLongest Game: "+getLongestGame();
    }
}
