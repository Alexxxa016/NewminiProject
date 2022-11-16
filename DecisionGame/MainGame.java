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

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;

public class MainGame extends JFrame implements ActionListener {


    private JLabel WelcomeSign, GameLabel, gameImage;
    private JPanel HeadingPanel, GamePanel, StartButtonPanel, Rbuttons,GameBackgroundImage;
    private JButton StartButton;
    private JFrame MenuFrame;
    private JFrame GameFrame;
    private Container container;
    private LayoutManager overlay;
    private JRadioButton option1, option2;
    static MediaPlayer mediaPlayer;
    String gameAudio = "C:\\Users\\Guest1\\Desktop\\JavaProject\\DecisionGame\\BackgroundNoise.mp3";


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
        AudioPlayer.BackgroundAudio("C:\\Users\\Guest1\\Desktop\\JavaProject\\DecisionGame\\IntroNoise.mp3");

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


        option1 = new JRadioButton(storyButton1);
        option2 = new JRadioButton(storyButton2);
        option1.setFont(new Font("Chiller",1,30));
        option2.setFont(new Font("Chiller",1,30));
        Rbuttons.setBounds(200,600,700,100);

        ButtonGroup Btngroup = new ButtonGroup();
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

    public void actionPerformed(ActionEvent e) {
        String UI = e.getActionCommand();

        if(UI == "Press to Play" || e.getSource()== this.StartButton){
            playAudio(gameAudio);
            displayUI("Story1.jpg","You're at a party, Its getting late...","Go Home","Stay");
        }

    }

}
