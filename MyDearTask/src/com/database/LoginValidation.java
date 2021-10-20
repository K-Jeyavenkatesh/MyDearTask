package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;
import com.usermodule.UserBean;

public class LoginValidation {

	public ArrayList<Object> LoginValidation(String userid, String pin) {
		
		int res = -1;
		ArrayList<Object> arr = new ArrayList<Object>();
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select * from user_info");
			while(result.next()) {
				
				if(result.getString("emailid").equals(userid)) {
					if(result.getString("pin").equals(pin)) {
						arr.add(2);
						UserBean user = new UserBean();
						user.setUser(result.getString(1));
						user.setEmailid(result.getString(2));
						user.setPhone(result.getString(3));
						user.setPin(result.getString(4));
						arr.add(user);
					} else {
						arr.add(-2);
						arr.add(null);
					}
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			arr.add(-1);
			arr.add(null);
			return arr;
		}
	}
	
	public String forgetPin(String emailid) {
		
		try {
			Connection connection = new DatabaseConnection().DatabaseConnection();
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("select pin from user_info where emailid=\""+emailid+"\";");
			result.next();
			return result.getString(1);
		} catch (SQLException e) {
			return null;
		}
	}
}
