package com.alert;

import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Test extends Thread{

	public void run() {
		
		try
		   {
		       SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();
		         
		       audioPlayer.play();
		       boolean f = true;
		       while (f) {
		       } 
		   } 
		   catch (Exception ex) 
		   {
		       System.out.println("Error with playing sound.");
		       ex.printStackTrace();
		     }
	}
}
