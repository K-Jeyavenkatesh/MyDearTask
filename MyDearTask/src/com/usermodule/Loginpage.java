package com.usermodule;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.alert.Gmail;
import com.database.LoginValidation;
import com.frame.FrameCreation;
import com.maintask.MainTask;

public class Loginpage {
	
	private JFrame frame;
	private JPanel panel;
	private JLabel quote, welcome, userLabel, pinLabel;
	private JTextField userId;
	private JPasswordField pin;
	private JButton show, forgetpassword, submit, register;
	
	public Loginpage() {
		
		frame = new FrameCreation().FrameCreation();
		
		quote = new JLabel();
		ImageIcon image=new ImageIcon(new ImageIcon("images/quote.png").getImage().getScaledInstance(500, 480, Image.SCALE_SMOOTH));
		quote.setIcon(image);
		quote.setBounds(50, 150, 500, 550);
		frame.add(quote);
		
		panel = new JPanel();
		panel.setBounds(500, 50, 625, 750);
		panel.setVisible(true);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		frame.add(panel);
		
		welcome = new JLabel("WELCOME !!");
		welcome.setBounds(200, 50, 250, 100);
		welcome.setFont(new Font("Helvetica", Font.BOLD, 40));
		panel.add(welcome);
		
		userLabel = new JLabel("E-MAIL ID");
		userLabel.setBounds(100, 200, 250, 100);
		userLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		panel.add(userLabel);
		
		userId = new JTextField();
		userId.setBounds(100, 275, 450, 50);
		userId.setFont(new Font("Helvetica", Font.BOLD, 25));
		panel.add(userId);
		
		pinLabel = new JLabel("PIN");
		pinLabel.setBounds(100, 325, 250, 100);
		pinLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		panel.add(pinLabel);
		
		pin = new JPasswordField();
		pin.setBounds(100, 400, 325, 50);
		pin.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(pin);
		
		show = new JButton("SHOW");
		show.setBounds(450, 400, 100, 50);
		show.setFont(new Font("Helvetica", Font.BOLD, 18));
		show.setBackground(new Color(0).decode("#3b61a1"));
		show.setForeground(Color.WHITE);
		panel.add(show);
		
		forgetpassword = new JButton("Forget PIN ?");
		forgetpassword.setBounds(400, 475, 150, 50);
		forgetpassword.setFont(new Font("Helvetica", Font.BOLD, 18));
		forgetpassword.setBackground(new Color(0).decode("#3b61a1"));
		forgetpassword.setForeground(Color.WHITE);
		panel.add(forgetpassword);
		
		register = new JButton("SIGNUP WITH NEW ACCOUNT");
		register.setBounds(100, 550, 450, 50);
		register.setFont(new Font("Helvetica", Font.BOLD, 20));
		register.setBackground(new Color(0).decode("#5e9aff"));
		register.setForeground(Color.WHITE);
		panel.add(register);
		
		submit = new JButton("SUBMIT");
		submit.setBounds(250, 650, 200, 50);
		submit.setFont(new Font("Helvetica", Font.BOLD, 18));
		submit.setBackground(new Color(0).decode("#3b61a1"));
		submit.setForeground(Color.WHITE);
		panel.add(submit);
		
		pin.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				
				String getPin = pin.getText();
				Pattern p = Pattern.compile("[^0-9]");
				Matcher m = p.matcher(getPin);
				
				while(m.find()) {
					
					JOptionPane.showMessageDialog(frame, "Only 0-9 digits are allowed", "Invalid PIN", JOptionPane.WARNING_MESSAGE);
					pin.setText("");
					break;
				}
				if(getPin.length() > 6) {
					pin.setText(getPin.substring(0,6));
				}
			}
		});
		
		show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = show.getText();
				if(name.equals("SHOW")) {
					show.setText("HIDE");
					pin.setEchoChar((char)0);
				} else {
					show.setText("SHOW");
					pin.setEchoChar('\u26ab');
				}
			}
		});
		
		forgetpassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String t = JOptionPane.showInputDialog(frame, "Enter Registered Email ID");
				String r = new LoginValidation().forgetPin(t);
				if(r == null) {
					JOptionPane.showMessageDialog(frame, "This Email ID is not Registered", "Invalid Data", JOptionPane.WARNING_MESSAGE);
				} else {
					String title = "Regarding Forget PIN - MyDearTask";
					String message = "Your PIN is "+r;
					new Gmail().Gmail("19euit065@skcet.ac.in", t, "19euit065JeyaVenkateshK19euit065", title, message);
				}
			}
		});
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Registerpage();
				frame.dispose();
			}
		});
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String id = userId.getText();
				String pin_id = pin.getText();
				boolean flag = true;
				if(id.equals("")) {
					JOptionPane.showMessageDialog(frame, "Email ID can't be Empty", "Email ID is Mandatory", JOptionPane.WARNING_MESSAGE);
					flag = false;
				}
				if(pin_id.equals("") && flag) {
					JOptionPane.showMessageDialog(frame, "PIN can't be Empty", "PIN is Mandatory", JOptionPane.WARNING_MESSAGE);
					flag = false;
				}
				Pattern p = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
				Matcher m = p.matcher(id);
				String tempId = "";
				while(m.find()) {
					tempId += m.group();
				}
				
				if(!id.equals(tempId)) {
					JOptionPane.showMessageDialog(frame, "You have entered Invalid E-mail ID", "Invalid E-Mail ID", JOptionPane.WARNING_MESSAGE);
					userId.setText("");
					flag = false;
				}
				if(flag) {
					
					ArrayList<Object> array= new LoginValidation().LoginValidation(id, pin_id);
					Integer result = (Integer)array.get(0);
					if(result == -1) {
						JOptionPane.showMessageDialog(frame, "You have entered UnRegsitered E-mail ID", "Invalid E-Mail ID", JOptionPane.WARNING_MESSAGE);
						userId.setText("");
						flag = false;
					}
					if(result == -2 && flag) {
						JOptionPane.showMessageDialog(frame, "You have entered Wrong PIN", "Wrong PIN", JOptionPane.WARNING_MESSAGE);
						pin.setText("");
						flag = false;
					}
					if(result == 2 && flag) {

						UserBean user = (UserBean)array.get(1);
						new MainTask(user);
						frame.dispose();
					}
				}
			}
		});
		
		frame.validate();
		frame.repaint();
	}
}
