package com.maintask;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import com.database.FetchDataHistory;
import com.frame.FrameCreation;
import com.frame.HistoryFrame;
import com.frame.PendingFrame;
import com.frame.TodayFrame;
import com.frame.UpcomingFrame;
import com.usermodule.UserBean;

public class MainTask {
	
	private JFrame frame;
	private JLabel title;
	private JButton today_button, pending_button, upcoming_button, history_button, create, clearhistory_button;
	private JScrollPane scrollPanel;
	private JPanel mainpanel;
	private HistoryFrame history;
	
	public MainTask(UserBean user) {
		
		frame = new FrameCreation().FrameCreation();
		
		title = new JLabel("Here Your Task "+user.getUser()+" !!");
		title.setBounds(50, 10, 1000, 100);
		title.setFont(new Font("Helvetica", Font.BOLD, 30));
		title.setForeground(Color.WHITE);
		frame.add(title);
		
		create = new JButton("+ CREATE TASK");
		create.setBounds(940, 30, 200, 50);
		create.setFont(new Font("Helvetica", Font.BOLD, 20));
		create.setBackground(Color.WHITE);
		frame.add(create);
		
		today_button = new JButton("TODAY");
		today_button.setBounds(50, 200, 200, 50);
		today_button.setFont(new Font("Helvetica", Font.BOLD, 30));
		today_button.setBackground(new Color(0).decode("#0000ff"));
		today_button.setForeground(Color.WHITE);
		frame.add(today_button);
		
		pending_button = new JButton("PENDING");
		pending_button.setBounds(250, 200, 200, 50);
		pending_button.setFont(new Font("Helvetica", Font.BOLD, 30));
		pending_button.setForeground(new Color(0).decode("#ff1e00"));
		pending_button.setBackground(Color.WHITE);
		frame.add(pending_button);
		
		upcoming_button = new JButton("UPCOMING");
		upcoming_button.setBounds(450, 200, 200, 50);
		upcoming_button.setFont(new Font("Helvetica", Font.BOLD, 30));
		upcoming_button.setForeground(new Color(0).decode("#0ac200"));
		upcoming_button.setBackground(Color.WHITE);
		frame.add(upcoming_button);
		
		history_button = new JButton("HISTORY");
		history_button.setBounds(650, 200, 200, 50);
		history_button.setFont(new Font("Helvetica", Font.BOLD, 30));
		history_button.setForeground(new Color(0).decode("#e0c600"));
		history_button.setBackground(Color.WHITE);
		frame.add(history_button);
		
		clearhistory_button = new JButton("CLEAR HISTORY");
		clearhistory_button.setBounds(940, 90, 200, 50);
		clearhistory_button.setFont(new Font("Helvetica", Font.BOLD, 20));
		clearhistory_button.setBackground(Color.WHITE);
		clearhistory_button.setVisible(false);
		frame.add(clearhistory_button);
		
		scrollPanel = new TodayFrame().TodayFrame(user.getUser());
		
		frame.getContentPane().add(scrollPanel);
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new CreateTask(user.getUser());
				frame.remove(scrollPanel);
				frame.validate();
				frame.repaint();
				scrollPanel = new TodayFrame().TodayFrame(user.getUser());
				frame.getContentPane().add(scrollPanel);
				frame.validate();
				frame.repaint();
			}
		});
		
		today_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				today_button.setForeground(Color.WHITE);
				today_button.setBackground(new Color(0).decode("#0000ff"));
				pending_button.setForeground(new Color(0).decode("#ff1e00"));
				pending_button.setBackground(Color.WHITE);
				upcoming_button.setForeground(new Color(0).decode("#0ac200"));
				upcoming_button.setBackground(Color.WHITE);
				history_button.setForeground(new Color(0).decode("#e0c600"));
				history_button.setBackground(Color.WHITE);
				clearhistory_button.setVisible(false);
				
				frame.remove(scrollPanel);
				frame.validate();
				frame.repaint();
				scrollPanel = new TodayFrame().TodayFrame(user.getUser());
				frame.getContentPane().add(scrollPanel);
				frame.validate();
				frame.repaint();
			}
		});
		
		pending_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				today_button.setForeground(new Color(0).decode("#0000ff"));
				today_button.setBackground(Color.WHITE);
				pending_button.setForeground(Color.WHITE);
				upcoming_button.setForeground(new Color(0).decode("#0ac200"));
				upcoming_button.setBackground(Color.WHITE);
				pending_button.setBackground(new Color(0).decode("#ff1e00"));
				history_button.setForeground(new Color(0).decode("#e0c600"));
				history_button.setBackground(Color.WHITE);
				clearhistory_button.setVisible(false);
				
				frame.remove(scrollPanel);
				frame.validate();
				frame.repaint();
				scrollPanel = new PendingFrame().PendingFrame(user.getUser());
				frame.getContentPane().add(scrollPanel);
				frame.validate();
				frame.repaint();
			}
		});
		
		upcoming_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				today_button.setForeground(new Color(0).decode("#0000ff"));
				today_button.setBackground(Color.WHITE);
				pending_button.setForeground(new Color(0).decode("#ff1e00"));
				pending_button.setBackground(Color.WHITE);
				upcoming_button.setForeground(Color.WHITE);
				upcoming_button.setBackground(new Color(0).decode("#0ac200"));
				history_button.setForeground(new Color(0).decode("#e0c600"));
				history_button.setBackground(Color.WHITE);
				clearhistory_button.setVisible(false);
				
				frame.remove(scrollPanel);
				frame.validate();
				frame.repaint();
				scrollPanel = new UpcomingFrame().UpcomingFrame(user.getUser());
				frame.getContentPane().add(scrollPanel);
				frame.validate();
				frame.repaint();
			}
		});
		
		history_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				today_button.setForeground(new Color(0).decode("#0000ff"));
				today_button.setBackground(Color.WHITE);
				pending_button.setForeground(new Color(0).decode("#ff1e00"));
				pending_button.setBackground(Color.WHITE);
				upcoming_button.setForeground(new Color(0).decode("#0ac200"));
				upcoming_button.setBackground(Color.WHITE);
				history_button.setForeground(Color.WHITE);
				history_button.setBackground(new Color(0).decode("#e0c600"));
				clearhistory_button.setVisible(true);
				
				frame.remove(scrollPanel);
				frame.validate();
				frame.repaint();
				history = new HistoryFrame();
				scrollPanel = history.HistoryFrame(user.getUser());
				frame.getContentPane().add(scrollPanel);
				frame.validate();
				frame.repaint();
			}
		});
		
		clearhistory_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int deleteAllint = history.deleteAll(user.getUser());
				frame.remove(scrollPanel);
				frame.validate();
				frame.repaint();
				history = new HistoryFrame();
				scrollPanel = history.HistoryFrame(user.getUser());
				frame.getContentPane().add(scrollPanel);
				frame.validate();
				frame.repaint();
			}
		});
		
		frame.validate();
		frame.repaint();
	}
}
