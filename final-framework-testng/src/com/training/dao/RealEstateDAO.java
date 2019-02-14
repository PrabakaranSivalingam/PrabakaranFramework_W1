package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.UserBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

/*  Real estate data access object to read the input from sql.properties and create the connection to the MySQL DB and 
 * execute the SQL query and get the values from the realinfo table to UserBean class using setter method     */

public class RealEstateDAO {
	
	Properties properties; 
	
	public RealEstateDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<UserBean> getUserInfo(){
		String sql = properties.getProperty("get.userdetails"); 
		
		GetConnection gc  = new GetConnection(); 
		List<UserBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<UserBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				UserBean temp = new UserBean(); 
				temp.setUserName(gc.rs1.getString(1));
				temp.seteMail(gc.rs1.getString(2));
				temp.setFirstName(gc.rs1.getString(3));
				temp.setLastName(gc.rs1.getString(4));
				temp.setWebSite(gc.rs1.getString(5));
				temp.setPasswd(gc.rs1.getString(6));
				temp.setRole(gc.rs1.getString(7));

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}

	public static void main(String[] args) {
		new RealEstateDAO().getUserInfo().forEach(System.out :: println);
	}
	
	
}
