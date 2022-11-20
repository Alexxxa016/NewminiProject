package DecisionGame;

public class Player {
    String nickname;

    public Player(String nickname) {
       setNickname(nickname);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        setNickname(nickname);
    }


    public String toString() {
        return "Player nickname: " + getNickname();
    }
}
