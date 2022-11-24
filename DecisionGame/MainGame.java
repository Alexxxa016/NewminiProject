package DecisionGame;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;

import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import static java.awt.BorderLayout.*;

public class MainGame extends JFrame implements ActionListener {

    String position="";
    private JLabel WelcomeSign, GameLabel, gameImage, OptionLabel, stopwatchLabel, watchLabel, timeTaken;
    private JPanel HeadingPanel, GamePanel, StartButtonPanel, Rbuttons,GameBackgroundImage, continuePanel;
    private JButton StartButton, viewPlayer, addPlayer, removePlayer;
    private JFrame MenuFrame, continueFrame, GameFrame;
    private Container container;
    private LayoutManager overlay;
    private JRadioButton option1, option2;
    private ButtonGroup Btngroup;
    static MediaPlayer mediaPlayer;
    int elapsedTime =0;
    int seconds =0;
    int minutes =0;
    private String finalTime;
    private float shortestTime = 0;
    private float longestTime = 0;
    boolean started = false;
    private String secondsString = String.format("%02d", seconds);
    private String minutesString = String.format("%02d", minutes);
    ChoiceHandler choiceHandler = new ChoiceHandler();
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {    //i used this to help me create a stop watch : https://www.codespeedy.com/create-a-stopwatch-in-java/#:~:text=Creating%20a%20stopwatch%20in%20Java,next().
            elapsedTime += 1000;
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            finalTime =minutesString+":"+secondsString;
            stopwatchLabel.setText(finalTime);

        }
    });
    String gameAudio = "DecisionGame/BackgroundNoise.mp3";
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Stats> playerStats = new ArrayList<>();
    private Player player;
    private Stats stats;

    public MainGame() {
        MenuFrame = new JFrame();
        final JFXPanel fxPanel = new JFXPanel();
        AudioPlayer player = new AudioPlayer();
        MenuFrame.setIconImage(new ImageIcon(getClass().getResource("icon.jpg")).getImage());

        MenuFrame.setSize(700, 450);
        MenuFrame.getContentPane().setBackground(Color.BLACK);
        MenuFrame.setLayout(null);
        MenuFrame.setTitle("Decision Game");
        MenuFrame.setLocationRelativeTo(null);
        MenuFrame.setResizable(false);

        container = MenuFrame.getContentPane();

        HeadingPanel = new JPanel();
        HeadingPanel.setBounds(0,40,700,100);
        HeadingPanel.setBackground(Color.BLACK);
        WelcomeSign = new JLabel("Welcome to the game, This is where you need to choose to survive.");
        WelcomeSign.setFont(new Font("Chiller",1,30));
        WelcomeSign.setForeground(Color.RED);

        StartButtonPanel = new JPanel();
        StartButtonPanel.setBounds(235,275,190,60);
        StartButtonPanel.setBackground(Color.BLACK);

        StartButton = new JButton("Press to Play");
        StartButton.setFont(new Font("Chiller",1,35));
        StartButton.setBounds(245,300,250,70);
        StartButton.setBackground(Color.BLACK);
        StartButton.setForeground(Color.RED);
        StartButton.addActionListener(this);

        StartButton.setBorder(BorderFactory.createEmptyBorder()); //This code is from https://stackoverflow.com/questions/2713190/how-to-remove-border-around-buttons

        HeadingPanel.add(WelcomeSign);
        StartButtonPanel.add(StartButton);

        container.add(HeadingPanel);
        container.add(StartButtonPanel);

        MenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MenuFrame.setVisible(true);


    }
    public static void main(String[] args) {
        new MainGame();
    }

    public void displayUI(String position, String StoryImg, String storyText, String storyButton1, String storyButton2){
        position = "";
        GameFrame = new JFrame();
        GameFrame.setLayout(new BorderLayout());
        try {
            GameFrame.setIconImage(new ImageIcon(getClass().getResource("icon.jpg")).getImage());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"The image could not be found","Not found",JOptionPane.ERROR_MESSAGE);
        }
        GameFrame.setResizable(false);
        GameFrame.setSize(800, 600);
        GameFrame.getContentPane().setBackground(Color.white);
        GameFrame.setTitle("Choose your Story");
        GameFrame.setLocationRelativeTo(null);

        container = GameFrame.getContentPane();

        GamePanel = new JPanel();
        GamePanel.setBounds(700,100,700,400);
        GamePanel.setBackground(Color.BLACK);
        GameLabel = new JLabel(storyText);
        GameLabel.setFont(new Font("Chiller",1,30));
        GameLabel.setForeground(Color.WHITE);
        GamePanel.add(GameLabel);
        GameFrame.add(GamePanel,BorderLayout.NORTH);

        GameBackgroundImage = new JPanel();

        gameImage = new JLabel();
        gameImage.setIcon(new ImageIcon(getClass().getResource(StoryImg)));
        gameImage.setSize(100,100);

        GameBackgroundImage.add(gameImage);
        GameFrame.add(GameBackgroundImage, CENTER);

        Rbuttons = new JPanel();
        OptionLabel = new JLabel();
        Rbuttons.setLayout(new FlowLayout());
        OptionLabel.setBounds(1,500,450, 300);

        option1 = new JRadioButton(storyButton1);
        option1.setFont(new Font("Chiller",1,30));
        option1.addActionListener(choiceHandler);
        option1.setActionCommand("opt1");

        option2 = new JRadioButton(storyButton2);
        option2.setFont(new Font("Chiller",1,30));
        option2.addActionListener(choiceHandler);
        option2.setActionCommand("opt2");

        stopwatchLabel = new JLabel();
        stopwatchLabel.setFont(new Font("Chiller",1,15));
        stopwatchLabel.setText(minutesString+":"+secondsString);


        Btngroup = new ButtonGroup();
        Btngroup.add(option1);
        Btngroup.add(option2);

        Rbuttons.add(OptionLabel);
        GameFrame.add(OptionLabel);

        Rbuttons.add(option1);
        Rbuttons.add(option2);
        Rbuttons.add(stopwatchLabel);
        GameFrame.add(Rbuttons, SOUTH);
        container.add(GameBackgroundImage);
        container.add(gameImage);

        GameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameFrame.setVisible(true);
    }
    public void continuationFrame(){
        continueFrame = new JFrame("Player Details");
        continueFrame.setIconImage(new ImageIcon(getClass().getResource("icon.jpg")).getImage());
        continuePanel = new JPanel();
        continuePanel.setLayout(null);
        continuePanel.setBackground(Color.BLACK);

        addPlayer = new JButton("Add Player");
        addPlayer.setMnemonic('A');
        addPlayer.setFont(new Font("Chiller",1,20));
        addPlayer.setBackground(Color.DARK_GRAY);
        addPlayer.setBounds(15,25,15,25);
        addPlayer.setLocation(100,575);
        addPlayer.setForeground(Color.WHITE);
        addPlayer.setSize(125,50);
        addPlayer.addActionListener(this);
        continuePanel.add(addPlayer);

        removePlayer = new JButton("Remove Player");
        removePlayer.setBackground(Color.DARK_GRAY);
        removePlayer.setFont(new Font("Chiller",1,20));
        removePlayer.setForeground(Color.WHITE);
        removePlayer.setBounds(300,575,135,50);
        removePlayer.addActionListener(this);
        continuePanel.add(removePlayer);

        viewPlayer = new JButton("View Stats");
        viewPlayer.setBackground(Color.DARK_GRAY);
        viewPlayer.setFont(new Font("Chiller",1,20));
        viewPlayer.setForeground(Color.WHITE);
        viewPlayer.setBounds(500,575,125,50);
        viewPlayer.addActionListener(this);
        continuePanel.add(viewPlayer);

        continueFrame.setSize(700, 700);
        continueFrame.setLocationRelativeTo(null);
        continueFrame.setResizable(false);
        continueFrame.add(continuePanel);
        continueFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        continueFrame.setVisible(true);

        timeTaken = new JLabel("Time it took you to finish the game: ");
        timeTaken.setFont(new Font("Chiller",Font.BOLD,20));
        timeTaken.setForeground(Color.RED);
        timeTaken.setBounds(200,150,250,50);
        continuePanel.add(timeTaken);

        watchLabel = new JLabel();
        watchLabel.setFont(new Font("Chiller",Font.BOLD,20));
        watchLabel.setForeground(Color.RED);
        watchLabel.setBounds(450,150,120,50);
        continuePanel.add(watchLabel);

        System.out.println(elapsedTime);
    }
    public void shortestTime(){
        for(Stats s : playerStats){
            if(elapsedTime < shortestTime){
                shortestTime = elapsedTime;
            }
        }
    }
    public void longestTime(){
        for(Stats s : playerStats){
            if(elapsedTime < longestTime){
                longestTime = elapsedTime;
            }
        }
    }
    public void addPlayer(){
        String nickname = JOptionPane.showInputDialog("Please enter your nickname");
        String[] genderList ={"Male", "Female"};

        boolean valid = false;
        while(!valid)
        {
          try {
              if(nickname.length() <= 16)
              {
                            String gender = (String) JOptionPane.showInputDialog(null,"Pick a Gender","Gender",JOptionPane.INFORMATION_MESSAGE,null,(Object[]) genderList, genderList[0]);
                            valid = true;
                            this.player = new Stats(nickname,gender,finalTime,shortestTime,longestTime);
              }else
                  nickname = JOptionPane.showInputDialog( "nickname must be up to 16 characters long or less");

          }  catch(NullPointerException npe)
          {
              int option = JOptionPane.showConfirmDialog(null, "All fields must be entered, Do you want to continue?", "Confirmation", JOptionPane.WARNING_MESSAGE);
              if(option == 0)
              nickname = JOptionPane.showInputDialog("Please enter your nickname");
                continue;
          }
          break;

        }

        JOptionPane.showMessageDialog(null, "New Player has been added!","Confirmation",JOptionPane.INFORMATION_MESSAGE);

        this.players.add(this.player);

    }

    public void viewStats(){
        JComboBox<String> viewPlayers = new JComboBox();
        JTextArea stats = new JTextArea();
        stats.setText("Player Statistics:\n\n");
        try {
            if (this.players.size() < 1) {
                JOptionPane.showMessageDialog(null, "No players were found in the system", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Iterator<Player> iterator = this.players.iterator();
                while (iterator.hasNext())
                    viewPlayers.addItem(((Player)iterator.next()).getNickname() + "\n");
                JOptionPane.showMessageDialog(null, viewPlayers, "Select Player to view details", JOptionPane.INFORMATION_MESSAGE);
                int selected = viewPlayers.getSelectedIndex();
                stats.append(((Player)this.players.get(selected)).toString());
                JOptionPane.showMessageDialog(null, stats, "Player Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NullPointerException npx) {
            JOptionPane.showMessageDialog(null, "Error due to null pointer", "Error", JOptionPane.ERROR_MESSAGE);
            npx.printStackTrace();
        }
    }


    public void removePlayer(){
        JComboBox<String> RemoveList = new JComboBox();
        for (Player s : this.players)
            RemoveList .addItem(s.getNickname());
        JOptionPane.showMessageDialog(null, "Select player to be removed", "Remove Player", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, RemoveList , "Remove Player", JOptionPane.PLAIN_MESSAGE);
        int selected = RemoveList .getSelectedIndex();
        this.players.remove(selected);
        JOptionPane.showMessageDialog(null, "Player has been Removed", "Player Removed", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void playAudio(String path)
    {

        Media audioClip = new Media(new File(path).toURI().toString());

        mediaPlayer = new MediaPlayer(audioClip);

        try {
            mediaPlayer.play();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null,"The audio file " + path + " could not be played! Please re-check path!");
        }

    }


    public void start(){

        timer.start();
    }
    public void stop(){

        timer.stop();
    }



    public void actionPerformed(ActionEvent e) {
        String UI = e.getActionCommand();

        if (UI == "Press to Play" || e.getSource() == this.StartButton) {
            playAudio(gameAudio);
            displayUI(position = "1","Pictures/Story1.jpg", "You're at a party, Its getting late...", "Go Home", "Stay");
            start();

        }
        else if(e.getSource() == this.addPlayer){
            addPlayer();
        }
        else if(e.getSource() == this.viewPlayer){
            viewStats();
        }
        else if(e.getSource() == this.removePlayer){
            removePlayer();
        }

    }


        public class ChoiceHandler implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                String yourOption = e.getActionCommand();

                switch(position)
                {
                    case "1":
                        switch(yourOption)
                        {
                            case "opt1":
                                displayUI( position = "4","Pictures/forest.jpg","You walk through the woods and hear a noise within...","Investigate","Ignore");
                                break;
                            case "opt2":
                                displayUI(position = "2","Pictures/drinks.jpg","You are offered drinks...","Accept","Decline");
                                break;
                        }

                        break;
                    case "2":
                    switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "14","Pictures/forestnight.jpg","You fell and passed out, You wakeup in the middle of the woods","Find your way home","Try to find help");
                                break;
                            case "opt2":
                                displayUI(position = "3","Pictures/opendoorway.jpg","You had a great night its time to go home","Go home", "");
                                break;
                        }
                        break;
                    case "3":
                    switch (yourOption)
                    {
                        case "opt1":
                            displayUI( position = "4","Pictures/forest.jpg","You walk through the woods and hear a noise within...","Investigate","Ignore");
                            break;
                        case "opt2":
                            break;
                    }
                    break;
                    case "4":
                    case "15":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "5","Pictures/forest3.jpg","You walk further the noise intensifies","Keep going?","Turn around");
                                break;
                            case "opt2":
                                displayUI(position = "6","Pictures/woodshouse.jpg","You made it home, it appears you are alone","Go inside","");
                                break;
                        }
                        break;
                    case "5":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "7","Pictures/forest7.png","You find the source of the noise, Its around the corner..","Look","RUN! Youre terrified");
                                break;
                            case "opt2":
                                displayUI(position = "6","Pictures/woodshouse.jpg","You made it home, it appears you are alone","Go inside","");
                                break;
                        }
                        break;
                    case "6":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "10","Pictures/nightbed.jpg","Youre getting tired","Go to sleep","Stay up a little longer");
                                break;
                            case "opt2":
                                break;
                        }
                        break;
                    case "7":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "8","Pictures/monster.png","Your curiosity killed you","","Continue");
                                break;
                            case "opt2":
                                displayUI(position = "9","Pictures/forest2.jpg","The noise chases you","Run home","Run to party for help");
                                break;
                        }
                        break;
                    case "9":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "6","Pictures/woodshouse.jpg","You made it home, it appears you are alone","Go inside","");
                                break;
                            case "opt2":
                                displayUI(position = "12","Pictures/forestroad.jpg","Which way was the party?!!","Left","Right");
                                break;
                        }
                        break;
                    case "10":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "11","Pictures/survived.jpg","You slept through the night and made it to safety","","Continue");
                                break;
                            case "opt2":
                                displayUI(position = "20","Pictures/bed.jpg","That noise you hear it again...","Peek out the window","Ignore it, youre hearing things");
                                break;
                        }
                        break;
                    case "8" :
                    case "11":
                    case "19":
                    case "25":
                    case "31":
                    case "35":
                        switch (yourOption)
                        {
                            case "opt1":
                                break;
                            case "opt2":
                                continuationFrame();
                                watchLabel.setText(minutesString+":"+secondsString);
                                stop();
                                break;
                        }
                        break;
                    case "12":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "14","Pictures/forestnight.jpg","You fell and passed out, You wakeup in the middle of the woods","Find your way home","Try to find help");
                                break;
                            case "opt2":
                                displayUI(position = "16","Pictures/foresthouse.jpg","You see the party in the distance, the creature is catching up..","Run inside","Surrender");
                                break;
                        }
                        break;
                    case "13":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "14","Pictures/forestnight.jpg","You fell and passed out, You wakeup in the middle of the woods","Find your way home","Try to find help");
                                break;
                            case "opt2":
                                break;
                        }
                        break;
                    case "14":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI( position = "4","Pictures/forest.jpg","You walk through the woods and hear a noise within...","Investigate","Ignore");
                                break;
                            case "opt2":
                                displayUI(position = "15","Pictures/forest7.png","You hear that same mound and unnerving noise...","Investigate","Ignore");
                                break;
                        }
                        break;
                    case "16":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "19","Pictures/police.jpg","The creature followed you to the party and slaughtered every member","","continue");
                                break;
                            case "opt2":
                                displayUI(position = "17","Pictures/monster4.jpg","The creature sees you and you can see it","Youre done","");
                                break;
                        }
                        break;
                    case "17":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "35","Pictures/monster2.png","It takes you to the forest and tears off your libs.. your soul leaves your body you get burried alive","","Continue");
                                break;
                            case "opt2":

                                break;
                        }
                        break;
                    case "20":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "21","Pictures/shadow.jpg","You see.. something but you cant make it out","Squint","Go to sleep youre seeing things");
                                break;
                            case "opt2":
                                displayUI(position = "26","Pictures/doormonster.png","Youre woken by a loud creek, as your door swings open","Sleep it off","Inspect");
                                break;
                        }
                        break;
                    case "21":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "22","Pictures/monsterlooking.png","The noise gets louder and the monster charges towards you","HIDE","");
                                break;
                            case "opt2":
                                displayUI(position = "23","Pictures/nightbed.jpg","You go back to sleep","Close your eyes","");
                                break;
                        }
                        break;
                    case "22":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "24","Pictures/stairs.jpg","You run to the hall and need to find a safe place","Run Upstairs","Basement");
                                break;
                            case "opt2":

                                break;
                        }
                        break;
                    case "23":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "26","Pictures/doormonster.png","Youre woken by a loud creek, as your door swings open","Sleep it off","Inspect");
                                break;
                            case "opt2":

                                break;
                        }
                        break;
                    case "24":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "27","Pictures/downstairs.jpg","You hear the creature has shifted to the basement..","Run..lock basement door","Arm yourself");
                                break;
                            case "opt2":
                                displayUI(position = "30","Pictures/doorknob.jpg","You couldnt lock the door, the lock was smashed","It knows where you are","");
                                break;
                        }
                        break;
                    case "26":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "30","Pictures/doorknob.jpg","You couldnt lock the door, the lock was smashed","It knows where you are","");
                                break;
                            case "opt2":
                                displayUI(position = "28","Pictures/monster3.jpg","You are face to face with the monster","Run..Lock yourself in basement","surrender");
                                break;
                        }
                        break;
                    case "27":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "28","Pictures/monster3.jpg","You are face to face with the monster","Run..Lock yourself in basement","surrender");
                                break;
                            case "opt2":
                                displayUI(position = "29","Pictures/monster3.jpg","You see it","push it into the basement and lock it","any better ideas?");
                                break;
                        }
                        break;
                    case "28":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "30","Pictures/doorknob.jpg","You couldnt lock the door, the lock was smashed","It knows where you are","");
                                break;
                            case "opt2":
                                displayUI(position = "35","Pictures/monster2.png","It takes you to the forest and tears off your libs.. your soul leaves your body you get burried alive","","Continue");
                                break;
                        }
                        break;
                    case "29":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "33","Pictures/gohome.jpg","The entity is trapped but not for long","Burn the house down","Run for your life");
                                break;
                            case "opt2":
                                displayUI(position = "35","Pictures/monster2.png","It takes you to the forest and tears off your libs.. your soul leaves your body you get burried alive","","Continue");
                                break;
                        }
                        break;
                    case "30":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "31","Pictures/monster2.png","The creature shreds you with his teeth and you are devoured","","Continue");
                                break;
                            case "opt2":

                                break;
                        }
                        break;
                    case "33":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "25","Pictures/firehouse.jpg","You burn the house down Cremating the creature inside ","","Continue");
                                break;
                            case "opt2":
                                displayUI(position = "34","Pictures/blank.jpg","Youre so close,but the basement door has been broken down","What now","");
                                break;
                        }
                        break;
                    case "34":
                        switch (yourOption)
                        {
                            case "opt1":
                                displayUI(position = "28","Pictures/monster3.jpg","You are face to face with the monster","Run..Lock yourself in basement","surrender");
                                break;
                            case "opt2":

                                break;
                        }
                        break;


                }
            }
        }
    }


