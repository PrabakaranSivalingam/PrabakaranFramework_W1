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
import com.training.dataproviders.UserInfoDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.pom.UsersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
//@SuppressWarnings("unused")

/* 
 * Author					:	Prabakaran Sivalingam
 * Test Case ID				:	RETC_077
 * Test Case Description	:	To Verify whether application allows admin to add new user & details get displayed in databases
 * Pre Condition			:	1. user should have launched the application by entering valid URL
 *								2. admin should be logged in
 */

public class AddNewUserDBTest {

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
	
	@Test(priority = 0)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		screenShot.captureScreenShot("Login");
		loginPOM.clickLoginBtn();

	}
	
	// Test method to add a new user and Assert against the value read from database

	@Test(priority = 1, dataProvider = "db-inputs", dataProviderClass = UserInfoDataProviders.class)
	public void AddUsertest(String userName, String eMail, String firstName, String lastName, String webSite,
			String password, String role) {

		dashboardPOM = new DashboardPOM(driver);
		dashboardPOM.UsersLinkSelection();

		usersPOM = new UsersPOM(driver);
		usersPOM.clickAddNewButton();
		
		usersPOM.enterUsername("man129zoor");
		String appUserName = usersPOM.getAppUserName();
		Assert.assertEquals(userName, appUserName);
		//System.out.println(userName+"    "+appUserName);
		
		usersPOM.enterEmail("man129zoor@gmail.com");
		String appEmail = usersPOM.getAppEmail();
		Assert.assertEquals(eMail, appEmail);
		//System.out.println(eMail+"    "+appEmail);
		
		usersPOM.enterFirstName("man129zoor");
		String appFirstName = usersPOM.getAppFirstName();
		Assert.assertEquals(firstName, appFirstName);
		//System.out.println(firstName+"    "+appFirstName);
		
		usersPOM.enterLastName("mehadi");
		String appLastName = usersPOM.getAppLastName();
		Assert.assertEquals(lastName, appLastName);
		//System.out.println(lastName+"    "+appLastName);
		
		usersPOM.enterWebsite("www.google.com");
		String appWebSite= usersPOM.getAppWebsite();
		Assert.assertEquals(webSite, appWebSite);
		//System.out.println(webSite+"    "+appWebSite);
		
		usersPOM.clickShowPasswordButton();
		usersPOM.enterPassword("Man129zoor@Mehadi");
		String appPassword= usersPOM.getAppPassword();
		Assert.assertEquals(password, appPassword);
		//System.out.println(password+"    "+appPassword);
		
		usersPOM.selectRole("Agent");
		String appRole= usersPOM.getAppRole();
		Assert.assertEquals(role, appRole);
		//System.out.println(role+"    "+appRole);
		
		usersPOM.clickAddNewUserButton();

		String expected = "New user created. Edit user";
		String actual = usersPOM.confirmUserCreationMessage();

		Assert.assertEquals(actual, expected); 

	}
}
