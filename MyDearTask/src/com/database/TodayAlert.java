package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.usermodule.UserBean;

public class TodayAlert {
	
	public ArrayList<ArrayList<String>> TodayAlert() {
		
		ArrayList<ArrayList<String>> arr1 = new ArrayList<ArrayList<String>>();
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable");
			
			SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");  
		    Date date = new Date();
		    String second = format.format(date);
		    
			while(result.next()) {

			    if (second.equals(result.getString(5))) {
			    	ArrayList<String> arr2 = new ArrayList<String>();
			    	arr2.add(result.getString(3));
			    	arr2.add(result.getString(4));
			    	arr2.add(result.getString(5));
			    	arr2.add(result.getString(6));
			    	arr1.add(arr2);
			    } 
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			return arr1;
		}
	}
}
