package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTask {

	public int InsertTask(String[] arr) {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			PreparedStatement st = connection.prepareStatement("insert into tasktable(user, title, description, date, time, status) values(?,?,?,?,?,?)");
			st.setString(1, arr[0]);
			st.setString(2, arr[1]);
			st.setString(3, arr[2]);
			st.setString(4, arr[3]);
			st.setString(5, arr[4]);
			st.setString(6, arr[5]);
			
			int r = st.executeUpdate();
			return r;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
	}
}
