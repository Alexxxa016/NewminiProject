package DecisionGame;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;
import com.sun.tools.javac.Main;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Background;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.*;

public class MainGame extends JFrame implements ActionListener {

    String position="";
    private JLabel WelcomeSign, GameLabel, gameImage;
    private JPanel HeadingPanel, GamePanel, StartButtonPanel, Rbuttons,GameBackgroundImage;
    private JButton StartButton;
    private JFrame MenuFrame;
    private JFrame GameFrame;
    private Container container;
    private LayoutManager overlay;
    private JRadioButton option1, option2;
    private ButtonGroup Btngroup;
    static MediaPlayer mediaPlayer;
    ChoiceHandler choiceHandler = new ChoiceHandler();

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
        Rbuttons.setLayout(new FlowLayout());

        option1 = new JRadioButton(storyButton1);
        option1.setFont(new Font("Chiller",1,30));
        option1.addActionListener(choiceHandler);
        option1.setActionCommand("opt1");

        option2 = new JRadioButton(storyButton2);
        option2.setFont(new Font("Chiller",1,30));
        option2.addActionListener(choiceHandler);
        option2.setActionCommand("opt2");

        Btngroup = new ButtonGroup();
        Btngroup.add(option1);
        Btngroup.add(option2);

        Rbuttons.add(option1);
        Rbuttons.add(option2);
        GameFrame.add(Rbuttons, SOUTH);
        container.add(GameBackgroundImage);
        container.add(gameImage);

        GameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GameFrame.setVisible(true);
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
    public void Slide1(){
        position = "mainMenu";
        displayUI("Story1.jpg", "You're at a party, Its getting late...", "Go Home", "Stay");
    }
    public void Slide2()
    {
        position = "Stay";
        displayUI("Story1.jpg","You are offered drinks...","Accept","Decline");
    }
    public void Slide3()
    {
        position ="";
        displayUI("Story1.jpg","You had a great night its time to go home","Go home", "");
    }
    public void Slide4()
    {
        position = "goHome";
        displayUI("Story1.jpg","You walk through the woods and hear a noise within...","Investigate","Ignore");
    }
    public void Slide5()
    {
        position = "Investigate";
        displayUI("Story1.jpg","You walk further the noise intensifies","Keep going?","Turn around");
    }
    public void Slide6()
    {
        position = "Ignore";
        displayUI("Story1.jpg","You made it home, it appears you are alone","Go inside","");
    }
    public void Slide7()
    {
        position = "";
        displayUI("Story1.jpg","You find the source of the noise, Its around the corner..","Look","RUN! Youre terrified");
    }
    public void Slide8()
    {
        position = "";
        displayUI("Story1.jpg","Your curiosity killed you","","");
    }
    public void Slide9()
    {
        position = "";
        displayUI("Story1.jpg","The noise chases you","Run home","Run to party for help");
    }
    public void Slide10()
    {
        position = "";
        displayUI("Story1.jpg","Youre getting tired","Go to sleep","Stay up a little longer");
    }
    public void Slide11()
    {
        position = "";
        displayUI("Story1.jpg","You slept through the night and made it to safety","","");
    }
    public void Slide12()
    {
        position = "";
        displayUI("Story1.jpg","Which way was the party?!!","Left","Right");
    }// skip 13 go straight to 14
    public void Slide14()
    {
        position = "";
        displayUI("Story1.jpg","You fell and passed out, You wakeup in the middle of the woods","Find your way home","Try to find help");
    }
    public void Slide15()
    {
        position = "";
        displayUI("Story1.jpg","You hear that same mound and unnerving noise...","Investigate","Ignore");
    }
    public void Slide16()
    {
        position = "";
        displayUI("Story1.jpg","You see the party in the distance, the creature is catching up..","Run inside","Surrender");
    }
    public void Slide17()
    {
        position = "";
        displayUI("Story1.jpg","The creature sees you and you can see it","Youre done","");
    }// 17 will take you directly to 19
    public void Slide19()
    {
        position = "";
        displayUI("Story1.jpg","The creature followed you to the party and slaughtered every member","Investigate","Ignore");
    }
    public void Slide20()
    {
        position = "";
        displayUI("Story1.jpg","That noise you hear it again...","Peek out the window","Ignore it, youre hearing things");
    }
    public void Slide21()
    {
        position = "";
        displayUI("Story1.jpg","You see.. something but you cant make it out","Squint","Go to sleep youre seeing things");
    }
    public void Slide22()
    {
        position = "";
        displayUI("Story1.jpg","The noise gets louder and the monster charges towards you","HIDE","");
    }
    public void Slide23()
    {
        position = "";
        displayUI("Story1.jpg","You go back to sleep","Close your eyes","");
    }
    public void Slide24()
    {
        position = "";
        displayUI("Story1.jpg","You run to the hall and need to find a safe place","Run Upstairs","Basement");
    }
    public void Slide25()
    {
        position = "";
        displayUI("Story1.jpg","You burn the house down Cremating the creature inside ","","");
    }
    public void Slide26()
    {
        position = "";
        displayUI("Story1.jpg","Youre woken by a loud creek, as your door swings open","Sleep it off","Inspect");
    }
    public void Slide27()
    {
        position = "";
        displayUI("Story1.jpg","You hear the creature has shifted to the basement..","Run..lock basement door","Arm yourself");
    }
    public void Slide28()
    {
        position = "";
        displayUI("Story1.jpg","You are face to face with the monster","Run..Lock yourself in basement","surrender");
    }
    public void Slide29()
    {
        position = "";
        displayUI("Story1.jpg","You see it","push it into the basement and lock it","any better ideas?");
    }
    public void Slide30()
    {
        position = "";
        displayUI("Story1.jpg","You couldnt lock the door, the lock was smashed","It knows where you are","");
    }
    public void Slide31()
    {
        position = "";
        displayUI("Story1.jpg","The creature shreds you with his teeth and you are devoured","","");
    }// no 32
    public void Slide33()
    {
        position = "";
        displayUI("Story1.jpg","The entity is trapped but not for long","Burn the house down","Run for your life");
    }
    public void Slide34()
    {
        position = "";
        displayUI("Story1.jpg","Youre so close,but the basement door has been broken down","What now","");
    }
    public void Slide35()
    {
        position = "";
        displayUI("Story1.jpg","It takes you to the forest and tears off your libs.. your soul leaves your body you get burried alive","","");
    }




    public void actionPerformed(ActionEvent e) {
        String UI = e.getActionCommand();

        if (UI == "Press to Play" || e.getSource() == this.StartButton) {
            playAudio(gameAudio);
            Slide1();


        }

    }
        public class ChoiceHandler implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                String yourOption = e.getActionCommand();

                switch(position)
                {
                    case "mainMenu":
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
                    case "goHome":
                    switch (yourOption)
                        {
                            case "opt1":
                                //   Investigate();
                                break;
                            case "opt2":
                             //   Ignore();
                                break;
                        }
                        break;
                    case "Stay":
                        switch(yourOption)
                        {
                            case //
                                //accept

                                //case

                                //decline
                        }


                }
            }
        }
    }


