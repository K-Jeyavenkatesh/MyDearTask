package com.maintask;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import com.database.InsertTask;
import com.frame.FrameCreation;

public class CreateTask {
	
	private JFrame frame;
	private JPanel panel;
	private JLabel title, title_count, description, description_count, date, time, datetime_desc;
	private JTextArea description_input;
	private JTextField title_input;
	private JXDatePicker picker;
	private JComboBox<String> hour, minutes, meridiem;
	private Calendar cal;
	private JButton create, cancel;
	private String[] comboboxHour;
	
	public CreateTask(String user) {
		
		frame = new FrameCreation().FrameCreation();
		frame.setTitle("Create Task");
		frame.setBounds(525, 300, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBounds(30, 30, 720, 500);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		frame.add(panel);
		
		title = new JLabel("Add Title");
		title.setBounds(15, 15, 100, 50);
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(title);
		
		title_input = new JTextField();
		title_input.setBounds(15, 75, 650, 40);
		title_input.setFont(new Font("Helvetica", Font.BOLD, 20));
		title_input.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(title_input);
		
		title_count = new JLabel("0/50 words");
		title_count.setBounds(575, 125, 100, 20);
		title_count.setFont(new Font("Helvetica", Font.BOLD, 15));
		panel.add(title_count);
		
		description = new JLabel("Add Description");
		description.setBounds(15, 150, 200, 50);
		description.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(description);
		
		description_input = new JTextArea(2,1);
		description_input.setBounds(15, 200, 650, 60);
		description_input.setFont(new Font("Helvetica", Font.BOLD, 20));
		description_input.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(description_input);
		
		description_count = new JLabel("0/125 words");
		description_count.setBounds(575, 270, 100, 20);
		description_count.setFont(new Font("Helvetica", Font.BOLD, 15));
		panel.add(description_count);
		
		datetime_desc = new JLabel("Date & Time to Perform Your Task");
		datetime_desc.setBounds(15, 290, 400, 20);
		datetime_desc.setFont(new Font("Helvetica", Font.BOLD, 19));
		panel.add(datetime_desc);
		
		date = new JLabel("Date");
		date.setBounds(15, 300, 200, 50);
		date.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(date);
		
		cal = Calendar.getInstance();
		
		picker = new JXDatePicker();
        picker.setDate(cal.getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        picker.setSize(new Dimension(250, 50));
        picker.setFont(new Font("Helvetica", Font.BOLD, 20));
        picker.setLocation(15, 350);
        panel.add(picker);
        
        time = new JLabel("Time");
		time.setBounds(400, 300, 200, 50);
		time.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(time);
		
		comboboxHour = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
		hour = new JComboBox<String>(comboboxHour);
		hour.setBounds(400, 350, 75, 50);
		hour.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(hour);
		
		minutes = new JComboBox<String>(new String[]{"00","01","02","03","04","05","06","07","08","09","10",
													 "11","12","13","14","15","16","17","18","19","20",
													 "21","22","23","24","25","26","27","28","29","30",
													 "31","32","33","34","35","36","37","38","39","40",
													 "41","42","43","44","45","46","47","48","49","50",
													 "51","52","53","54","55","56","57","58","59"});
		minutes.setBounds(500, 350, 75, 50);
		minutes.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(minutes);
		
		meridiem = new JComboBox<String>(new String[]{"AM","PM"});
		meridiem.setBounds(600, 350, 75, 50);
		meridiem.setFont(new Font("Helvetica", Font.BOLD, 20));
		panel.add(meridiem);
		
		create = new JButton("CREATE");
		create.setBounds(150, 425, 150, 50);
		create.setFont(new Font("Helvetica", Font.BOLD, 20));
		create.setBackground(new Color(0).decode("#3b61a1"));
		create.setForeground(Color.WHITE);
		panel.add(create);
		
		cancel = new JButton("CANCEL");
		cancel.setBounds(400, 425, 150, 50);
		cancel.setFont(new Font("Helvetica", Font.BOLD, 20));
		cancel.setBackground(new Color(0).decode("#3b61a1"));
		cancel.setForeground(Color.WHITE);
		panel.add(cancel);
		
        setCurrentTime();
		
		title_input.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				
				String text_title = title_input.getText();
				int l = text_title.length();
				if(l <= 50) {
					title_count.setText(l+"/50 words");
				} else {
					title_input.setText(text_title.substring(0,50));
				}
			}
		});
		
		description_input.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				
				String text_title = description_input.getText();
				int l = text_title.length();
				description_input.setLineWrap(true); //to automatic move cursor down
				if(l <= 125) {
					description_count.setText(l+"/125 words");
				} else {
					description_input.setText(text_title.substring(0,125));
				}
			}
		});
		
		picker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int res = dateValidCheck();
				System.out.println(res);
				if(res == -1 || res != 0 && res != 1) {
					JOptionPane.showMessageDialog(frame, "You chose date which has passed", "Invalid Date", JOptionPane.ERROR_MESSAGE);
					picker.setDate(cal.getTime());
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag = true;
				int res = timeValidCheck();
				if(dateValidCheck() == 0 && res == -1) {
					JOptionPane.showMessageDialog(frame, "You chose time which has passed", "Invalid Time", JOptionPane.ERROR_MESSAGE);
					setCurrentTime();
					flag = false;
				} else if(dateValidCheck() == 0 && res == 0) {
					JOptionPane.showMessageDialog(frame, "You chose current time now and change it someother", "Do it now", JOptionPane.ERROR_MESSAGE);
					flag = false;
				}
				if(flag) {
					String title_text = title_input.getText();
					String desc_text = description_input.getText();
					SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy"); 
					String date_text = format.format(picker.getDate());
					String time_text = (String) hour.getSelectedItem()+" "+(String) minutes.getSelectedItem()+" "+(String) meridiem.getSelectedItem();
					System.out.println(title_text);
					System.out.println(desc_text);
					System.out.println(date_text);
					System.out.println(time_text);
					String[] arr = new String[6];
					arr[0] = user;
					arr[1] = title_text;
					arr[2] = desc_text;
					arr[3] = date_text;
					arr[4] = time_text;
					if(dateValidCheck() == 0) {
						arr[5] = "Not Completed";
					} else if(dateValidCheck() == 1){
						arr[5] = "Upcoming";
					}
					int done = new InsertTask().InsertTask(arr);
					if(done == 1) {
						JOptionPane.showMessageDialog(frame, "Your Task is Created Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					}
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				
				frame.dispose();
			}
		});
		
		timeValidCheck();
		
		frame.validate();
		frame.repaint();
		
	}
	
	public void setCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String current_time = sdf.format(cal.getTime());
        
        if(Integer.valueOf(current_time.substring(0,2)) == 0) {
        	hour.setSelectedItem(comboboxHour[11]);
        	meridiem.setSelectedItem("AM");
        	minutes.setSelectedItem(current_time.substring(3,5));
        	
        } else if(Integer.valueOf(current_time.substring(0,2)) >= 12) {
        	int reset = Integer.valueOf(current_time.substring(0,2))-12;
        	if(reset == 0) {
        		reset = 12;
        	}
        	hour.setSelectedItem(comboboxHour[reset-1]);
        	meridiem.setSelectedItem("PM");
        	minutes.setSelectedItem(current_time.substring(3,5));
        } else {
        	hour.setSelectedItem(comboboxHour[Integer.valueOf(current_time.substring(0,2))-1]);
        	meridiem.setSelectedItem("AM");
        	minutes.setSelectedItem(current_time.substring(3,5));
        }
	}
	
	public int dateValidCheck() {
		
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");  
	    Date date = new Date();  
	    Date date_picked= picker.getDate();

		if(date_picked.before(date)) {
			if(format.format(date).equals(format.format(date_picked))) {
				return 0;
			} else {
				return -1;
			}
		} 
		return 1;
	}
	
	public int timeValidCheck() {
		
		SimpleDateFormat format = new SimpleDateFormat("hh mm a");  
	    Date date = new Date();
	    String h1 = (String) hour.getSelectedItem();
	    String m1 = (String) minutes.getSelectedItem();
	    String s1 = (String) meridiem.getSelectedItem();
	    String second = format.format(date);
	    String h2 = second.substring(0,2);
	    String m2 = second.substring(3,5);
	    String s2 = second.substring(6,8);
	    if(s1.equals("PM")) {
	    	h1 = (Integer.valueOf(h1)+12)+"";
	    }
	    if(s2.equals("PM")) {
	    	h2 = (Integer.valueOf(h2)+12)+"";
	    }
	    LocalTime time1 = LocalTime.parse(h1+":"+m1+":00");
	    LocalTime time2 = LocalTime.parse(h2+":"+m2+":00");
	    int value = time1.compareTo(time2);

	    if (value > 0) {
	        return 1;
	    } else if (value == 0) {
	        return 0;
	    } else {
	        return -1;
	    }
	}
}

