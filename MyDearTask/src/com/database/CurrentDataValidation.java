package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CurrentDataValidation {
	
	public void CurrentDataValidation1() {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable");
			
			SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");  
		    Date date = new Date();
		    String second = format.format(date);
		    
		    ArrayList<String> arr1 = new ArrayList<String>();
		    
			while(result.next()) {

			    if (second.equals(result.getString(5))) {
			    	arr1.add(result.getString(1));
			    } 
			}
			while(!arr1.isEmpty()) {
				int r = st.executeUpdate("update tasktable set status=\"Not Completed\" where id=\""+arr1.remove(0)+"\";");
				System.out.println(r);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public void CurrentDataValidation2() {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable");
			
			SimpleDateFormat format = new SimpleDateFormat("hh mm a dd MM yyyy");  
		    Date date = new Date();
		    String second = format.format(date);
		    ArrayList<String> arr1 = new ArrayList<String>();
		    
			while(result.next()) {

			    String h1 = result.getString(6).substring(0,2);
			    String m1 = result.getString(6).substring(3,5);
			    String s1 = result.getString(6).substring(6,8);

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

			    if ((value == 0 || value < 1 ) && second.substring(9).equals(result.getString(5))) {
			    	arr1.add(result.getString(1));
			    } 
			}
			while(!arr1.isEmpty()) {
				int r = st.executeUpdate("update tasktable set status=\"Pending\" where id=\""+arr1.remove(0)+"\";");
				System.out.println(r);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
		}
	}
}
