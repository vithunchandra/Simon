/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import javax.sound.sampled.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.DataLine.Info;

public class BackgroundSong extends Thread{
    File audioFile;
    AudioInputStream audioStream;
    Clip clip;
    public BackgroundSong(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        audioFile = new File(filePath);
        
        if(audioFile.exists()){
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            run();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            clip.open(audioStream);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(BackgroundSong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BackgroundSong.class.getName()).log(Level.SEVERE, null, ex);
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }
    
}
