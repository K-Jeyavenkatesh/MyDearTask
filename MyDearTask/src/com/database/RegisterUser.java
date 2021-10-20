package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;
import com.usermodule.UserBean;

public class RegisterUser {
	
	private Connection connection;
	int t;
	
	public int RegisterUser(UserBean detail) {
		
		try {
			
			connection = new DatabaseConnection().DatabaseConnection();
			String temp = this.checkData(detail);
			if(temp != null) {
				if(temp.equals("2")) {
					t = 2;
					return t;
				}
				if(temp.equals("3")) {
					t = 3;
					return t;
				}
			}
			PreparedStatement st = connection.prepareStatement("insert into user_info(username,emailid,phone,pin) values(?,?,?,?)");
			st.setString(1, detail.getUser());
			st.setString(2, detail.getEmailid());
			st.setString(3, detail.getPhone());
			st.setString(4, detail.getPin());
			
			int r = st.executeUpdate();
			t = r;
			
		} catch (SQLException e) {
			if(e.getClass().getName().toString().equals("java.sql.SQLIntegrityConstraintViolationException")) {
				t = -1;
			}
		} finally {
			return t;
		}
	}
	
	public String checkData(UserBean detail) {
		
		String res = null;
		try {
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from user_info");
			while(result.next()) {
				
				if(result.getString(2).equals(detail.getEmailid())) {
					res = "2";
					break;
				}
				if(result.getString(3).equals(detail.getPhone())) {
					res = "3";
					break;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			return res;
		}
	}
}
