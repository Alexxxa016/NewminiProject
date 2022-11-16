package DecisionGame;

import java.awt.event.ActionListener;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.event.*;


public class AudioPlayer implements ActionListener {
    static MediaPlayer soundPlayer;

    public static void BackgroundAudio(String path){

        Media audioClip = new Media(new File(path).toURI().toString());
        soundPlayer = new MediaPlayer(audioClip);
    }




    public void actionPerformed(ActionEvent e) {

    }
}