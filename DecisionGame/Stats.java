package DecisionGame;

public class Stats extends Player{
    private String gameTime;
    private String shortestGame;
    private String longestGame;

    public Stats() {
        this("Not Given","Not Given","Unknown","Unknown","Unknown");
    }

    public Stats(String nickname, String gender, String gameTime, String shortestGame, String longestGame) {
        super(nickname, gender);
        setGameTime(gameTime);
        setShortestGame(shortestGame);
        setLongestGame(longestGame);
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getShortestGame() {
        return shortestGame;
    }

    public void setShortestGame(String shortestGame) {
        this.shortestGame = shortestGame;
    }

    public String getLongestGame() {
        return longestGame;
    }

    public void setLongestGame(String longestGame) {
        this.longestGame = longestGame;
    }


}
