package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FetchDataPending {
	
	private ArrayList<ArrayList<String>> arr;
	
	public ArrayList<ArrayList<String>> FetchDataPending(String user) {
		
		arr = new ArrayList<ArrayList<String>>(); 
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable where user=\""+user+"\";");
			while(result.next()) {
				if(result.getString(7).equals("Pending")) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(result.getString(1));
					temp.add(result.getString(2));
					temp.add(result.getString(3));
					temp.add(result.getString(4));
					temp.add(result.getString(5));
					temp.add(result.getString(6));
					temp.add(result.getString(7));
					arr.add(temp);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			//System.out.println(arr);
			return arr;
		}
	}
	
	public int changePending(String key) {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable");
			int y = st.executeUpdate("update tasktable set status=\"Completed\" where id=\""+key+"\";");
			return y;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return 0;
	}
	
	public int postponePending(String key) {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable where id=\""+key+"\";");
			result.next();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
			Calendar c = Calendar.getInstance();
			String date1 = sdf.format(c.getTime());
			try {
				c.setTime(sdf.parse(date1));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			c.add(Calendar.DATE, 1);  
			date1 = sdf.format(c.getTime());
			System.out.println(date1);
			int y = st.executeUpdate("update tasktable set date=\""+date1+"\" where id=\""+key+"\";");
			y = st.executeUpdate("update tasktable set status=\"Upcoming\" where id=\""+key+"\";");
			return y;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return 0;
	}
	
	public int cancelPending(String key) {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable");
			int y = st.executeUpdate("update tasktable set status=\"Cancelled\" where id=\""+key+"\";");
			return y;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return 0;
	}
}
