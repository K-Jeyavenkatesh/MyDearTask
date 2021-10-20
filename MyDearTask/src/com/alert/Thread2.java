package com.alert;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Thread2 extends Thread{
	
	long t;
	ArrayList<String> arr;
	SimpleAudioPlayer s;
	 
	public Thread2(long t,  ArrayList<String> arr) {
		this.t = t;
		this.arr = arr;
	}
	public void run() {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		 
		
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 800, 425);
		frame.setTitle("Alert");
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0).decode("#387aff"));
		
		ImageIcon icon = new ImageIcon("images/task_icon.png");
		frame.setIconImage(icon.getImage());
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 30, 720, 325);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(6,1));
		frame.add(panel);
		
		JLabel l1 = new JLabel("Remainder !!");
		l1.setFont(new Font("Helvetica", Font.BOLD, 30));
		panel.add(l1);
		
		JLabel l2 = new JLabel(arr.get(0));
		l2.setFont(new Font("Helvetica", Font.BOLD, 30));
		panel.add(l2);
		
		int l = arr.get(1).length();
		if(l > 45) {
			l = l/2;
			
			panel.setLayout(new GridLayout(7,1));
			JLabel l3 = new JLabel(arr.get(1).substring(0,l));
			l3.setFont(new Font("Helvetica", Font.BOLD, 30));
			panel.add(l3);
			
			JLabel l4 = new JLabel(arr.get(1).substring(l));
			l4.setFont(new Font("Helvetica", Font.BOLD, 30));
			panel.add(l4);
		} else {
			JLabel l3 = new JLabel(arr.get(1));
			l3.setFont(new Font("Helvetica", Font.BOLD, 30));
			panel.add(l3);
		}
		
		JLabel l5 = new JLabel("Date : "+arr.get(2));
		l5.setFont(new Font("Helvetica", Font.BOLD, 30));
		panel.add(l5);
		
		JLabel l6 = new JLabel("Time :"+arr.get(3));
		l6.setFont(new Font("Helvetica", Font.BOLD, 30));
		panel.add(l6);
		
		JButton b = new JButton("CLOSE");
		b.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(b);
		
		Test tt = new Test();
		tt.start();
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				frame.dispose();
			}
		});
		
		frame.validate();
		frame.repaint();
		
	}
}
