package DecisionGame;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;

import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.*;

public class MainGame extends JFrame implements ActionListener {

    String position="";
    private JLabel WelcomeSign, GameLabel, gameImage, OptionLabel, stopwatchLabel, watchLabel;
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
    boolean started = false;
    String secondsString = String.format("%02d", seconds);
    String minutesString = String.format("%02d", minutes);
    ChoiceHandler choiceHandler = new ChoiceHandler();
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            stopwatchLabel.setText(minutesString+":"+secondsString);

        }
    });

    String gameAudio = "DecisionGame/BackgroundNoise.mp3";

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

    public void displayUI(String StoryImg, String storyText, String storyButton1, String storyButton2){
        GameFrame = new JFrame();
        GameFrame.setLayout(new BorderLayout());
        GameFrame.setIconImage(new ImageIcon(getClass().getResource("icon.jpg")).getImage());
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

        watchLabel = new JLabel();
        watchLabel.setBounds(500,250,120,50);

        continuePanel.add(watchLabel);
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
    public void reset(){

    }
    public void Slide1(){
        position = "1";
        displayUI("Pictures/Story1.jpg", "You're at a party, Its getting late...", "Go Home", "Stay");
    }
    public void Slide2()
    {
        position = "2";
        displayUI("Pictures/drinks.jpg","You are offered drinks...","Accept","Decline");
    }
    public void Slide3()
    {
        position = "3";
        displayUI("Pictures/opendoorway.jpg","You had a great night its time to go home","Go home", "");
    }
    public void Slide4()
    {
        position = "4";
        displayUI("Pictures/forest.jpg","You walk through the woods and hear a noise within...","Investigate","Ignore");
    }
    public void Slide5()
    {
        position = "5";
        displayUI("Pictures/forest3.jpg","You walk further the noise intensifies","Keep going?","Turn around");
    }
    public void Slide6()
    {
        position = "6";
        displayUI("Pictures/woodshouse.jpg","You made it home, it appears you are alone","Go inside","");
    }
    public void Slide7()
    {
        position = "7";
        displayUI("Pictures/forest7.png","You find the source of the noise, Its around the corner..","Look","RUN! Youre terrified");
    }
    public void Slide8()
    {
        position = "8";
        displayUI("Pictures/monster.png","Your curiosity killed you","","Continue");
    }
    public void Slide9()
    {
        position = "9";
        displayUI("Pictures/forest2.jpg","The noise chases you","Run home","Run to party for help");
    }
    public void Slide10()
    {
        position = "10";
        displayUI("Pictures/nightbed.jpg","Youre getting tired","Go to sleep","Stay up a little longer");
    }
    public void Slide11()
    {
        position = "11";
        displayUI("Pictures/survived.jpg","You slept through the night and made it to safety","","Continue");
    }
    public void Slide12()
    {
        position = "12";
        displayUI("Pictures/forestroad.jpg","Which way was the party?!!","Left","Right");
    }// skip 13 go straight to 14
    public void Slide14()
    {
        position = "14";
        displayUI("Pictures/forestnight.jpg","You fell and passed out, You wakeup in the middle of the woods","Find your way home","Try to find help");
    }
    public void Slide15()
    {
        position = "15";
        displayUI("Pictures/forest7.png","You hear that same mound and unnerving noise...","Investigate","Ignore");
    }
    public void Slide16()
    {
        position = "16";
        displayUI("Pictures/foresthouse.jpg","You see the party in the distance, the creature is catching up..","Run inside","Surrender");
    }
    public void Slide17()
    {
        position = "17";
        displayUI("Pictures/monster4.jpg","The creature sees you and you can see it","Youre done","");
    }// 17 will take you directly to 35
    public void Slide19()
    {
        position = "19";
        displayUI("Pictures/police.jpg","The creature followed you to the party and slaughtered every member","Investigate","Ignore");
    }
    public void Slide20()
    {
        position = "20";
        displayUI("Pictures/bed.jpg","That noise you hear it again...","Peek out the window","Ignore it, youre hearing things");
    }
    public void Slide21()
    {
        position = "21";
        displayUI("Pictures/shadow.jpg","You see.. something but you cant make it out","Squint","Go to sleep youre seeing things");
    }
    public void Slide22()
    {
        position = "22";
        displayUI("Pictures/monsterlooking.png","The noise gets louder and the monster charges towards you","HIDE","");
    }
    public void Slide23()
    {
        position = "23";
        displayUI("Pictures/nightbed.jpg","You go back to sleep","Close your eyes","");
    }
    public void Slide24()
    {
        position = "24";
        displayUI("Pictures/stairs.jpg","You run to the hall and need to find a safe place","Run Upstairs","Basement");
    }
    public void Slide25()
    {
        position = "25";
        displayUI("Pictures/firehouse.jpg","You burn the house down Cremating the creature inside ","","Continue");
    }
    public void Slide26()
    {
        position = "26";
        displayUI("Pictures/doormonster.png","Youre woken by a loud creek, as your door swings open","Sleep it off","Inspect");
    }
    public void Slide27()
    {
        position = "27";
        displayUI("Pictures/downstairs.jpg","You hear the creature has shifted to the basement..","Run..lock basement door","Arm yourself");
    }
    public void Slide28()
    {
        position = "28";
        displayUI("Pictures/monster3.jpg","You are face to face with the monster","Run..Lock yourself in basement","surrender");
    }
    public void Slide29()
    {
        position = "29";
        displayUI("Pictures/monster3.jpg","You see it","push it into the basement and lock it","any better ideas?");
    }
    public void Slide30()
    {
        position = "30";
        displayUI("Pictures/doorknob.jpg","You couldnt lock the door, the lock was smashed","It knows where you are","");
    }
    public void Slide31()
    {
        position = "31";
        displayUI("Pictures/monster2.png","The creature shreds you with his teeth and you are devoured","","Continue");
    }// no 32
    public void Slide33()
    {
        position = "33";
        displayUI("Pictures/gohome.jpg","The entity is trapped but not for long","Burn the house down","Run for your life");
    }
    public void Slide34()
    {
        position = "34";
        displayUI("Pictures/blank.jpg","Youre so close,but the basement door has been broken down","What now","");
    }
    public void Slide35()
    {
        position = "35";
        displayUI("Pictures/monster2.png","It takes you to the forest and tears off your libs.. your soul leaves your body you get burried alive","","Continue");
    }




    public void actionPerformed(ActionEvent e) {
        String UI = e.getActionCommand();

        if (UI == "Press to Play" || e.getSource() == this.StartButton) {
            playAudio(gameAudio);
            Slide1();
            start();

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
                                Slide4();

                                break;
                            case "opt2":
                                Slide2();
                                break;
                        }

                        break;
                    case "2":
                    switch (yourOption)
                        {
                            case "opt1":
                                Slide14();
                                break;
                            case "opt2":
                                Slide3();
                                break;
                        }
                        break;
                    case "3":
                    switch (yourOption)
                    {
                        case "opt1":
                            Slide4();
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
                                Slide5();
                                break;
                            case "opt2":
                                Slide6();
                                break;
                        }
                        break;
                    case "5":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide7();
                                break;
                            case "opt2":
                                Slide6();
                                break;
                        }
                        break;
                    case "6":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide10();
                                break;
                            case "opt2":
                                break;
                        }
                        break;
                    case "7":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide8();
                                break;
                            case "opt2":
                                Slide9();
                                break;
                        }

                    case "8":
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
                    case "9":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide6();
                                break;
                            case "opt2":
                                Slide12();
                                break;
                        }
                        break;
                    case "10":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide11();
                                break;
                            case "opt2":
                                Slide20();
                                break;
                        }
                        break;
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
                                stop();
                                break;
                        }
                        break;
                    case "12":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide14();
                                break;
                            case "opt2":
                                Slide16();
                                break;
                        }
                        break;
                    case "13":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide14();
                                break;
                            case "opt2":
                                break;
                        }
                        break;
                    case "14":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide4();
                                break;
                            case "opt2":
                                Slide15();
                                break;
                        }
                        break;
                    case "16":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide19();
                                break;
                            case "opt2":
                                Slide17();
                                break;
                        }
                        break;
                    case "17":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide35();
                                break;
                            case "opt2":

                                break;
                        }
                        break;
                    case "20":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide21();
                                break;
                            case "opt2":
                                Slide26();
                                break;
                        }
                        break;
                    case "21":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide22();
                                break;
                            case "opt2":
                                Slide23();
                                break;
                        }
                        break;
                    case "22":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide24();
                                break;
                            case "opt2":

                                break;
                        }
                        break;
                    case "23":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide26();
                                break;
                            case "opt2":

                                break;
                        }
                        break;
                    case "24":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide27();
                                break;
                            case "opt2":
                                Slide30();
                                break;
                        }
                        break;
                    case "26":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide30();
                                break;
                            case "opt2":
                                Slide28();
                                break;
                        }
                        break;
                    case "27":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide28();
                                break;
                            case "opt2":
                                Slide29();
                                break;
                        }
                        break;
                    case "28":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide30();
                                break;
                            case "opt2":
                                Slide35();
                                break;
                        }
                        break;
                    case "29":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide33();
                                break;
                            case "opt2":
                                Slide35();
                                break;
                        }
                        break;
                    case "30":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide31();
                                break;
                            case "opt2":

                                break;
                        }
                        break;
                    case "33":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide25();
                                break;
                            case "opt2":
                                Slide34();
                                break;
                        }
                        break;
                    case "34":
                        switch (yourOption)
                        {
                            case "opt1":
                                Slide28();
                                break;
                            case "opt2":

                                break;
                        }
                        break;


                }
            }
        }
    }


