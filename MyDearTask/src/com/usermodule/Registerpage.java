package com.usermodule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.database.RegisterUser;
import com.frame.FrameCreation;

public class Registerpage {
	
	private JFrame frame;
	private JLabel account, user, email, phone, pin, limit, cpin;
	private JTextField user_input, email_input, phone_input;
	private JPasswordField pin_input, cpin_input;
	private JPanel panel1, panel2;
	private JButton show1, show2, suggest, back, submit;
	
	public Registerpage() {
		
		frame = new FrameCreation().FrameCreation();
		
		account = new JLabel("Create Your Own Account Here !!");
		account.setBounds(300, 25, 700, 100);
		account.setFont(new Font("Helvetica", Font.BOLD, 40));
		account.setForeground(Color.WHITE);
		frame.add(account);
		
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(100, 150, 450, 525);
		panel1.setVisible(true);
		panel1.setBackground(Color.WHITE);
		frame.add(panel1);
		
		user = new JLabel("USERNAME");
		user.setBounds(20, 50, 200, 50);
		user.setFont(new Font("Helvetica", Font.BOLD, 25));
		panel1.add(user);
		
		user_input = new JTextField();
		user_input.setBounds(20, 105, 400, 50);
		user_input.setFont(new Font("Helvetica", Font.BOLD, 18));
		panel1.add(user_input);
		
		email = new JLabel("E-MAIL ID");
		email.setBounds(20, 200, 200, 50);
		email.setFont(new Font("Helvetica", Font.BOLD, 25));
		panel1.add(email);
		
		email_input = new JTextField();
		email_input.setBounds(20, 255, 400, 50);
		email_input.setFont(new Font("Helvetica", Font.BOLD, 18));
		panel1.add(email_input);
		
		phone = new JLabel("PHONE NUMBER");
		phone.setBounds(20, 350, 400, 50);
		phone.setFont(new Font("Helvetica", Font.BOLD, 25));
		panel1.add(phone);
		
		phone_input = new JTextField();
		phone_input.setBounds(20, 405, 400, 50);
		phone_input.setFont(new Font("Helvetica", Font.BOLD, 18));
		panel1.add(phone_input);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(625, 150, 450, 525);
		panel2.setVisible(true);
		panel2.setBackground(Color.WHITE);
		frame.add(panel2);
		
		pin = new JLabel("CREATE PIN");
		pin.setBounds(20, 50, 200, 50);
		pin.setFont(new Font("Helvetica", Font.BOLD, 25));
		panel2.add(pin);
		
		limit = new JLabel("(length must be 6 digits)");
		limit.setBounds(180, 50, 200, 50);
		limit.setFont(new Font("Helvetica", Font.BOLD, 16));
		panel2.add(limit);
		
		pin_input = new JPasswordField();
		pin_input.setBounds(20, 105, 275, 50);
		pin_input.setFont(new Font("Helvetica", Font.BOLD, 18));
		panel2.add(pin_input);
		
		show1 = new JButton("SHOW");
		show1.setBounds(325, 105, 100, 50);
		show1.setFont(new Font("Helvetica", Font.BOLD, 18));
		show1.setBackground(new Color(0).decode("#3b61a1"));
		show1.setForeground(Color.WHITE);
		panel2.add(show1);
		
		suggest = new JButton("SUGGEST PIN");
		suggest.setBounds(210, 205, 215, 50);
		suggest.setFont(new Font("Helvetica", Font.BOLD, 18));
		suggest.setBackground(new Color(0).decode("#3b61a1"));
		suggest.setForeground(Color.WHITE);
		panel2.add(suggest);
		
		cpin = new JLabel("CONFIRM PIN");
		cpin.setBounds(20, 275, 200, 50);
		cpin.setFont(new Font("Helvetica", Font.BOLD, 25));
		panel2.add(cpin);
		
		cpin_input = new JPasswordField();
		cpin_input.setBounds(20, 330, 275, 50);
		cpin_input.setFont(new Font("Helvetica", Font.BOLD, 18));
		panel2.add(cpin_input);
		
		show2 = new JButton("SHOW");
		show2.setBounds(325, 330, 100, 50);
		show2.setFont(new Font("Helvetica", Font.BOLD, 18));
		show2.setBackground(new Color(0).decode("#3b61a1"));
		show2.setForeground(Color.WHITE);
		panel2.add(show2);
		
		submit = new JButton("SUBMIT");
		submit.setBounds(125, 450, 200, 50);
		submit.setFont(new Font("Helvetica", Font.BOLD, 18));
		submit.setBackground(new Color(0).decode("#3b61a1"));
		submit.setForeground(Color.WHITE);
		panel2.add(submit);
		
		back = new JButton("BACK");
		back.setBounds(485, 725, 200, 50);
		back.setFont(new Font("Helvetica", Font.BOLD, 18));
		frame.add(back);
		
		pin_input.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				
				String getPin = pin_input.getText();
				Pattern p = Pattern.compile("[^0-9]");
				Matcher m = p.matcher(getPin);
				
				while(m.find()) {
					
					JOptionPane.showMessageDialog(frame, "Only 0-9 digits are allowed", "Invalid PIN", JOptionPane.WARNING_MESSAGE);
					pin_input.setText("");
					break;
				}
				if(getPin.length() > 6) {
					pin_input.setText(getPin.substring(0,6));
				}
			}
		});
		
		cpin_input.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				
				String getPin = cpin_input.getText();
				Pattern p = Pattern.compile("[^0-9]");
				Matcher m = p.matcher(getPin);
				
				while(m.find()) {
					
					JOptionPane.showMessageDialog(frame, "Only 0-9 digits are allowed", "Invalid PIN", JOptionPane.WARNING_MESSAGE);
					cpin_input.setText("");
					break;
				}
				if(getPin.length() > 6) {
					cpin_input.setText(getPin.substring(0,6));
				}
			}
		});
		
		suggest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int min = 100000;
				int max = 999999;
				boolean flag = true;
				while(flag) { 
					int pin_final = (int)Math.floor(Math.random()*(max-min+1)+min);
					int loop = JOptionPane.showConfirmDialog(frame, "Your PIN is "+pin_final);
					if(loop == 0) {
						pin_input.setText(pin_final+"");
						flag = false;
					} else if(loop == 2 || loop == -1) {
						break;
					} 
				}
			}
		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Loginpage();
				frame.dispose();
			}
		});
		
		show1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = show1.getText();
				if(name.equals("SHOW")) {
					show1.setText("HIDE");
					pin_input.setEchoChar((char)0);
				} else {
					show1.setText("SHOW");
					pin_input.setEchoChar('\u26ab');
				}
			}
		});
		
		show2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = show2.getText();
				if(name.equals("SHOW")) {
					show2.setText("HIDE");
					cpin_input.setEchoChar((char)0);
				} else {
					show2.setText("SHOW");
					cpin_input.setEchoChar('\u26ab');
				}
			}
		});
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String user_text = user_input.getText();
				String email_text = email_input.getText();
				String phone_text = phone_input.getText();
				String pin_text = pin_input.getText();
				boolean flag = true;
				if(user_text.equals("")) {
					JOptionPane.showMessageDialog(frame, "Username can't be Empty", "Not sufficient Data", JOptionPane.WARNING_MESSAGE);
					flag = false;
				}
				if(email_text.equals("") && flag) {
					JOptionPane.showMessageDialog(frame, "E-Mail Id can't be Empty", "Not sufficient Data", JOptionPane.WARNING_MESSAGE);
					flag = false;
				}
				if(phone_text.equals("") && flag) {
					JOptionPane.showMessageDialog(frame, "Phone Number can't be Empty", "Not sufficient Data", JOptionPane.WARNING_MESSAGE);
					flag = false;
				}
				if(pin_text.equals("") && flag) {
					JOptionPane.showMessageDialog(frame, "PIN can't be Empty", "Not sufficient Data", JOptionPane.WARNING_MESSAGE);
					flag = false;
				}
				String cpin_text = cpin_input.getText();
				String id = email_input.getText();
				Pattern p = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
				Matcher m = p.matcher(id);
				String tempId = "";
				while(m.find()) {
					tempId += m.group();
				}
				if(!id.equals(tempId)) {
					JOptionPane.showMessageDialog(frame, "You have entered Invalid E-mail ID", "Invalid E-Mail ID", JOptionPane.WARNING_MESSAGE);
					email_input.setText("");
					flag = false;
				}
				id = phone_input.getText();
				p = Pattern.compile("^[6-9]\\d{9}$");
				m = p.matcher(id);
				tempId = "";
				while(m.find()) {
					tempId += m.group();
				}
				if(!id.equals(tempId)) {
					JOptionPane.showMessageDialog(frame, "You have entered Invalid Phone Number", "Invalid Phone Number", JOptionPane.WARNING_MESSAGE);
					phone_input.setText("");
					flag = false;
				}
				if(!pin_text.equals(cpin_text)) {
					JOptionPane.showMessageDialog(frame, "PIN and Confirm PIN is not matched", "Invalid PIN", JOptionPane.WARNING_MESSAGE);
				}
				if(flag) {
					
					UserBean detail = new UserBean();
					detail.setUser(user_text);
					detail.setEmailid(email_text);
					detail.setPhone(phone_text);
					detail.setPin(pin_text);
					int usertodb = new RegisterUser().RegisterUser(detail);
					if(usertodb == -1) {
						JOptionPane.showMessageDialog(frame, "User is Already Registered", "Duplicate User is Not Allowed", JOptionPane.WARNING_MESSAGE);
					} else if(usertodb == 2) {
						JOptionPane.showMessageDialog(frame, "Emailid is already registered", "Invalid Register", JOptionPane.INFORMATION_MESSAGE);
					} else if(usertodb == 3) {
						JOptionPane.showMessageDialog(frame, "Phone Number is already registered", "Invalid Register", JOptionPane.INFORMATION_MESSAGE);
					} else if(usertodb == 1) {
						JOptionPane.showMessageDialog(frame, "User is Successfully Registered", "Success", JOptionPane.INFORMATION_MESSAGE);
						new Loginpage();
						frame.dispose();
					} 
				}
			}
		});
		
		frame.validate();
		frame.repaint();
	}
}
