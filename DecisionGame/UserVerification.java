package DecisionGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserVerification extends JFrame implements ActionListener {

    private JFrame verifyFrame;
    private JPanel verifyPanel;
    private JLabel textLabel, nickname, DOB, ageVerification;
    private JButton yes, no;
    private JTextField userInput;

    public UserVerification(){
        verifyFrame = new JFrame();
        verifyFrame.setTitle("authentication");
        verifyFrame.setSize(500,350);
        verifyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        verifyFrame.setLocationRelativeTo(null);
        verifyFrame.setResizable(false);

        verifyPanel = new JPanel();
        verifyPanel.setLayout(null);

        textLabel = new JLabel("Please enter required data to proceed");
        textLabel.setFont(new Font("Congenial Black",1,20));
        textLabel.setBounds(45,10,450,20);

        nickname =new JLabel("Player nickname: ");
        nickname.setBounds(100,40,200,30);

        userInput= new JTextField();
        userInput.setBounds(225,45,150,20);

        DOB =new JLabel("Are you 18 years of age or older?");
        DOB.setBounds(130,70,450,30);

        yes = new JButton("Yes");
        yes.setBounds(140,110,75,25);
        verifyPanel.add(yes);

        no = new JButton("No");
        no.setBounds(230,110,75,25);
        verifyPanel.add(no);

        verifyPanel.add(DOB);
        verifyPanel.add(userInput);
        verifyPanel.add(nickname);
        verifyPanel.add(textLabel);
        verifyFrame.add(verifyPanel);
        verifyFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        //  if();
    }
}

