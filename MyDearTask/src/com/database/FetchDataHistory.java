package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FetchDataHistory {

	private ArrayList<ArrayList<String>> arr;
	
	public ArrayList<ArrayList<String>> FetchDataHistory(String user) {
		
		arr = new ArrayList<ArrayList<String>>(); 
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable where user=\""+user+"\";");
			while(result.next()) {
				if(result.getString(2).equals(user)  && (result.getString(7).equals("Completed") || result.getString(7).equals("Cancelled"))) {
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
	
	public int deleteHistory(String key) {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable where id=\""+key+"\";");
			int y = st.executeUpdate("delete from tasktable where id=\""+key+"\";");
			System.out.println(y);
			return y;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return 0;
	}
	
	public int deleteAllHsitory(String user) {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from tasktable where (status=\"Completed\" or status=\"Cancelled\" and user=\""+user+"\");");
			ArrayList<String> arr = new ArrayList<String>();
			while(result.next()) {
				arr.add(result.getString(1));
			}
			while(!arr.isEmpty()) {
				st.executeUpdate("delete from tasktable where id=\""+arr.remove(0)+"\";");
			}
			return 1;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return 0;
	}
}
