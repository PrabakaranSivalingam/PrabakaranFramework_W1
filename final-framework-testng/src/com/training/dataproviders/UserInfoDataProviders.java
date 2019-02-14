package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.UserBean;
import com.training.dao.RealEstateDAO;

/*  UserInfoDataProviders class will read the data from Userbean using the getter method and assgined to 
 * 2 dimensional object arrry and return this oject array to calling method    */
public class UserInfoDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<UserBean> list = new RealEstateDAO().getUserInfo(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(UserBean temp : list){
			Object[]  obj = new Object[7]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.geteMail();
			obj[2] = temp.getFirstName();
			obj[3] = temp.getLastName();
			obj[4] = temp.getWebSite();
			obj[5] = temp.getPasswd();
			obj[6] = temp.getRole(); 
			
			result[count ++] = obj; 
		}
		
		return result;
	}
	
}
