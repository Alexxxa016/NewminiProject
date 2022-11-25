package Documents;

import java.io.Serializable;

/** An instantiable class which defines a Player.
    @author Aleksandra Buchholz
 */
public class Player implements Serializable {
    private int playerID;
    private String nickname;
    private String gender;
    private static int count;
    /**
     *  Player no-argument constructor. Calls the
     *  2-argument Player constructor to initialise the  attributes of a
     *  Player object with some default initial values
     * ,to leave the Player object in a consistent initial state
     */

    public Player(){
        this("Not given","Not given");
    }
    /**
     * Player 2-argument constructor. Calls the 3 mutators and the
     * incrementCount() method to initialise the attributes of a Player object
     * with some user-supplied values. The PlayerID is  set internally using
     * the value of the count attribute, to ensure unique PlayerID values.
     * @param nickname the player nickname
     * @param gender the players gender
     */

    public Player(String nickname, String gender) {
        setNickname(nickname);
        setGender(gender);
        incrementCount();
        setPlayerID(count);
    }
    /**
     * Method to increment the static count attribute of the class, this is to ensure that every
     * Player object will have a unique ID value, as it tracks the value of this attribute
     */
    public static void incrementCount(){
        count++;
    }
    /**
     * Method to get the value of the static count attribute
     * @return an integer value specifying the current value of the count attribute
     */
    public static int getCount(){
        return count;
    }
    /**
     * Method to get the ID of a Player object
     * @return an integer value specifying the ID of a Player object
     */
    public int getPlayerID(){
        return playerID;
    }

    /**
     * Method to set the ID of a Player object
     * @param playerID the ID of the Player
     */
    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }
    /**
     * Method to get the nickname of a Player object
     * @return a String value specifying the nickname of a Player object
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Method to set the nickname of a Player object
     * @param nickname the nickname of the Player
     */
    public void setNickname(String nickname) {
        this.nickname=nickname;
    }
    /**
     * Method to get the gender of a Player object
     * @return a String value specifying the gender of a Player object
     */
    public String getGender() {
        return gender;
    }
    /**
     * Method to set the gender of a Player object
     * @param gender the gender of the Player
     */
    public void setGender(String gender) {
       this.gender=gender;
    }
    /**
     *  Method to get the state of a Player object
     *  @return a String value specifying the state of a Player object
     */
}
