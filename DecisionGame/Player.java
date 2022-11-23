package DecisionGame;

import java.util.ArrayList;

public class Player {
    private String nickname;
    private String gender;

    public Player(){
        this("Not given","Not given");
    }

    public Player(String nickname, String gender) {
        setNickname(nickname);
        setGender(gender);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname=nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
       this.gender=gender;
    }

    public String toString() {
        return "Player:\n "+ "Nickname:" + getNickname() + "\nGender:" + getGender();
    }
}
