package com.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.database.FetchDataPending;
import com.database.FetchDataUpcoming;

public class UpcomingFrame implements ActionListener{

	JPanel mainpanel;
	JPanel[] pp;
	private String user;
	
	public JScrollPane UpcomingFrame(String user) {
		
		this.user = user;
		mainpanel=new JPanel();
		mainpanel.setBounds(280, 170, 850, 550); 
		mainpanel.setBackground(Color.WHITE); 
		mainpanel.setVisible(true);
		
		ArrayList<ArrayList<String>> arr = new FetchDataUpcoming().FetchDataUpcoming(user);
		//System.out.println("h"+arr);
		mainpanel.setPreferredSize(new Dimension(0, arr.size()*200));
		mainpanel.setLayout(new GridLayout(arr.size(),1));
		pp = new JPanel[arr.size()];
		
		for(int i = 0; i < arr.size(); i++) {
			
			ArrayList<String> temp = arr.get(i);
				JPanel m=new JPanel();
				pp[i] = m;
				m.setName(temp.get(0));
				
				m.setSize(new Dimension(100,75));
				m.setBackground(Color.WHITE);
				m.setLayout(null);
				m.setVisible(true);
				m.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				mainpanel.add(m);
				JLabel taskTitle = new JLabel(temp.get(2));
				taskTitle.setBounds(10, 5, 1000, 50); 
				taskTitle.setFont(new Font("Helvetica", Font.BOLD, 27));
				m.add(taskTitle);
				int limit_desc = temp.get(3).length();
				if(limit_desc > 63) {
					limit_desc = limit_desc/2;
				}
				JLabel taskdesc = new JLabel("<html>"+temp.get(3).substring(0,limit_desc)+"<br/>"
						+temp.get(3).substring(limit_desc)+"</html>");
				taskdesc.setBounds(10, 55, 1000, 50); 
				taskdesc.setFont(new Font("Helvetica", Font.BOLD, 20));
				m.add(taskdesc);
				JLabel taskdate = new JLabel("Date : "+temp.get(4));
				taskdate.setBounds(10, 125, 200, 50);
				taskdate.setFont(new Font("Helvetica", Font.BOLD, 20));
				m.add(taskdate);
				JLabel tasktime = new JLabel("Time : "+temp.get(5));
				tasktime.setBounds(250, 125, 1000, 50);
				tasktime.setFont(new Font("Helvetica", Font.BOLD, 20));
				m.add(tasktime);
				JLabel taskstatus = new JLabel("Status : "+temp.get(6));
				taskstatus.setBounds(450, 125, 1000, 50); 
				taskstatus.setFont(new Font("Helvetica", Font.BOLD, 20));
				m.add(taskstatus);
				
				JButton taskCompleted = new JButton("COMPLETED");
				taskCompleted.setActionCommand("COMPLETED "+i);
				taskCompleted.setBounds(850, 10, 175, 50);
				taskCompleted.setFont(new Font("Helvetica", Font.BOLD, 18));
				taskCompleted.setBackground(new Color(0).decode("#3b61a1"));
				taskCompleted.setForeground(Color.WHITE);
				taskCompleted.addActionListener(this);
				m.add(taskCompleted);
				JButton taskPostpone = new JButton("POSTPONE");
				taskPostpone.setActionCommand("POSTPONE "+i);
				taskPostpone.setBounds(850, 75, 175, 50);
				taskPostpone.setFont(new Font("Helvetica", Font.BOLD, 18));
				taskPostpone.setBackground(new Color(0).decode("#3b61a1"));
				taskPostpone.setForeground(Color.WHITE);
				taskPostpone.addActionListener(this);
				m.add(taskPostpone);
				JButton taskCancel = new JButton("CANCEL");
				taskCancel.setActionCommand("CANCEL "+i);
				taskCancel.setBounds(850, 140, 175, 50);
				taskCancel.setFont(new Font("Helvetica", Font.BOLD, 18));
				taskCancel.setBackground(new Color(0).decode("#3b61a1"));
				taskCancel.setForeground(Color.WHITE);
				taskCancel.addActionListener(this);
				m.add(taskCancel);
			}
		
		JScrollPane scrollPanel = new JScrollPane(
			    mainpanel,
			    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			);
		
		scrollPanel.setBounds(50, 250, 1090, 550);
		
		return scrollPanel;
	} 
	
	public void actionPerformed(ActionEvent e) { 
		
		String[] button_type = e.getActionCommand().split(" ");
		int t = Integer.valueOf(button_type[1]);
		mainpanel.remove(pp[t]);

		if(button_type[0].equals("COMPLETED")) {
			
			int change_com = new FetchDataUpcoming().changeUpcoming(pp[t].getName());
			if(change_com == 1) {
				JOptionPane.showMessageDialog(mainpanel, "Task Completed Successfully", "Updated", JOptionPane.INFORMATION_MESSAGE);
			} 
		} else if(button_type[0].equals("POSTPONE")) {
			
			int change_post = new FetchDataUpcoming().postponeUpcoming(pp[t].getName());
			if(change_post == 1) {
				JOptionPane.showMessageDialog(mainpanel, "Task Postponed Successfully", "Updated", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if(button_type[0].equals("CANCEL")) {
			int change_cancel = new FetchDataUpcoming().cancelUpcoming(pp[t].getName());
			if(change_cancel == 1) {
				JOptionPane.showMessageDialog(mainpanel, "Task Cancelled Successfully", "Updated", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		new UpcomingFrame().UpcomingFrame(user);
		mainpanel.validate();
		mainpanel.repaint();
		
	}
}
