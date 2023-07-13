/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AvventuraTestuale;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author Vito e Mattia
 */
public class audio {
    public static void main(String[] args) {
        String audioFilePath = "audio/music.wav";
        playAudio(audioFilePath);
    }

    public static void playAudio(String audioFilePath) {
        try {
            // Caricamento file audio in formato wav
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            
            // Attendi la fine della riproduzione
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.stop();
            clip.close();
            audioStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
