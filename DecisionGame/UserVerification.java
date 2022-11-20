package DecisionGame;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserVerification extends JFrame implements ActionListener {

    private JFrame verifyFrame;
    private JPanel verifyPanel;
    private JLabel textLabel, DOB, ageVerification;
    private JButton yesBtn, noBtn, continueBtn;
    private JTextField  userDOB;

    public UserVerification() {
        verifyFrame = new JFrame();
        verifyFrame.setIconImage(new ImageIcon(getClass().getResource("icon.jpg")).getImage());
        verifyFrame.setTitle("Age Verification");
        verifyFrame.setSize(500, 350);
        verifyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        verifyFrame.setLocationRelativeTo(null);
        verifyFrame.setResizable(false);

        verifyPanel = new JPanel();
        verifyPanel.setLayout(null);

        textLabel = new JLabel("Please enter required data to proceed");
        textLabel.setFont(new Font("Congenial Black", 1, 20));
        textLabel.setBounds(45, 10, 500, 20);

        DOB = new JLabel("Are you 18 years of age or older?");
        DOB.setBounds(130, 70, 700, 30);

        yesBtn = new JButton("Yes");
        yesBtn.setBounds(140, 110, 75, 25);
        yesBtn.addActionListener(this);
        verifyPanel.add(yesBtn);

        noBtn = new JButton("No");
        noBtn.setBounds(230, 110, 75, 25);
        noBtn.addActionListener(this);
        verifyPanel.add(noBtn);

        verifyPanel.add(DOB);
        verifyPanel.add(textLabel);
        verifyFrame.add(verifyPanel);
        verifyFrame.setVisible(true);
    }
    public void ageVerify() {
        yesBtn.hide();
        noBtn.hide();
        DOB.hide();
        ageVerification = new JLabel("Enter your D.O.B :");
        ageVerification.setBounds(150, 100, 500, 30);

        userDOB = new JTextField();
        userDOB.setBounds(260, 105, 100, 23);

        continueBtn = new JButton("Continue");
        continueBtn.setBounds(200, 180, 100, 25);
        continueBtn.addActionListener(this);

        verifyPanel.add(continueBtn);
        verifyPanel.add(userDOB);
        verifyPanel.add(ageVerification);
        ageVerification.setVisible(true);

        }


public boolean DOBverify(String dateOfBirth){


    boolean valid = false;
    while (!dateOfBirth.equals("x")) {
        while (!valid) {
            valid = false;
            if (dateOfBirth.length() == 8) {
                if (Character.isDigit(dateOfBirth.charAt(0)) && (Character.isDigit(dateOfBirth.charAt(1)))
                        && dateOfBirth.charAt(2) == '/' && (Character.isDigit(dateOfBirth.charAt(3))) &&
                        (Character.isDigit(dateOfBirth.charAt(4))) && dateOfBirth.charAt(5) == '/' &&
                        (Character.isDigit(dateOfBirth.charAt(6))) && (Character.isDigit(dateOfBirth.charAt(7)))) {
                    int day = Integer.parseInt(dateOfBirth.substring(0, 2));
                    int month = Integer.parseInt(dateOfBirth.substring(3, 5));
                    int year = Integer.parseInt(dateOfBirth.substring(6, 8));

                    if (year <= 2004) {
                        if (month >= 1 && month <= 12) {
                            if (day >= 1 && day <= 31) {

                                valid = true;

                            } else
                                JOptionPane.showMessageDialog(null, "Date must be in right format Eg. dd/mm/yy day between 1-31");

                        } else
                            JOptionPane.showMessageDialog(null, "Date must be in right format Eg. dd/mm/yy  between 1-12 months");

                    } else
                        JOptionPane.showMessageDialog(null, "Date must be in right format Eg. dd/mm/yy Year less than 2004");

                } else
                    JOptionPane.showMessageDialog(null, "Date must be in right format Eg. dd/mm/yy");

            } else
                JOptionPane.showMessageDialog(null, "Date must be in right format Eg. dd/mm/yy and 8 in length");
            break;
        }
        break;
    }
    return valid;
}
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.noBtn) {
            JOptionPane.showMessageDialog(null, "You must be 18 years old to play this game", "Age Verification", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else if (e.getSource() == this.yesBtn) {
            ageVerify();
        }
        else if(e.getSource() == this.continueBtn)
        {
            if(DOBverify(userDOB.getText()))
            {
                MainGame mg = new MainGame();
            }




        }

    }
}