package com.training.functional.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.pom.UsersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
//@SuppressWarnings("unused")

/* 
 * Author					:	Prabakaran Sivalingam
 * Test Case ID				:	RETC_078
 * Test Data				:	RETC_078Data
 * Test Case Description	:	To Verify whether application displays error message upon entering invalid details 
 * 								in required fields while creating user by admin
 * Pre Condition			:	1. user should have launched the application by entering valid URL
 *								2. admin should be logged in
 */

public class AddNewUserTestError {
	
	private WebDriver driver;     
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private DashboardPOM dashboardPOM;
	
	
	private UsersPOM usersPOM;
	
	// Create the file and driver object and Open the url in the browser
	 @BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);

		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	// Close the browser window
	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	//Test method to Login to the application using admin role
	@Test(priority=0)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		screenShot.captureScreenShot("Login");
		loginPOM.clickLoginBtn(); 
		
		}
	
	// Test method to display the error message while adding the user in the application by getting the invalid data from the Excel file.
	
 	@Test(priority=1, dataProvider = "RETC_078", dataProviderClass = LoginDataProviders.class)
	public void AddUsertest(String userName, String eMail, String firstName, String lastName, String webSite, String password, String role) {
				
		dashboardPOM = new DashboardPOM(driver);
		dashboardPOM.UsersLinkSelection();
					
		usersPOM = new UsersPOM(driver);
		usersPOM.clickAddNewButton();
		usersPOM.enterUsername(userName);
		usersPOM.enterEmail(eMail);
		usersPOM.enterFirstName(firstName);
		usersPOM.enterLastName(lastName);
		usersPOM.enterWebsite(webSite);
		usersPOM.clickShowPasswordButton();
		usersPOM.enterPassword(password);
		usersPOM.selectRole(role);
		usersPOM.clickAddNewUserButton();
		//usersPOM.borderColorValidation();
		
		String expectedEmail="ERROR: This email is already registered, please choose another one.";
		String expectedUserName = "ERROR: This username is already registered. Please choose another one.";
		String actual=usersPOM.errorUserCreationMessage();
		
		if(actual.contains(expectedEmail))
		  Assert.assertEquals(actual, expectedEmail);
		else
		  Assert.assertEquals(actual, expectedUserName);
		
		
						
	} 
	
 }
