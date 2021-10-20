package com.frame;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FrameCreation {
	
	private JFrame frame;
	private ImageIcon icon;
	
	public JFrame FrameCreation() {
		
		frame = new JFrame();
		frame.setBounds(325, 100, 1200, 900);
		frame.setTitle("MyDearTask");
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0).decode("#387aff"));
		
		//Setting icon of application
		icon = new ImageIcon("images/task_icon.png");
		frame.setIconImage(icon.getImage());
		
		return frame;
	}
}

