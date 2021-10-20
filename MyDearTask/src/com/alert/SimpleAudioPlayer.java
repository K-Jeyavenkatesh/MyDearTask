package com.alert;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer 
{

		Long currentFrame;
		Clip clip;
		String status;
		AudioInputStream audioInputStream;
		static String filePath = "sound/abc.wav";

		public SimpleAudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		 
		   audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		   clip = AudioSystem.getClip();
		   clip.open(audioInputStream);
		}

		public void play() {
			
		   clip.start();
		   status = "play";
		}
		 
		public void stop() throws UnsupportedAudioFileException,IOException, LineUnavailableException {
			
		   currentFrame = 0L;
		   clip.stop();
		   clip.close();
		}
}