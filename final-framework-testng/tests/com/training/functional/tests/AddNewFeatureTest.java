package com.training.functional.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CreatePropertyPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/* 
 * Author					:	Prabakaran Sivalingam
 * Test Case ID				:	RETC_047
 * Test Case Description	:	To verify whether application allows admin to add new Feature while adding new property
 * Pre Condition			:	1. user should have launched the application by entering valid URL
 *								2. admin should be logged in
 */

public class AddNewFeatureTest {

	private WebDriver driver; 				
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CreatePropertyPOM createPropertyPOM;
	private DashboardPOM dashboardPOM;

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
	public void tearDown() {
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

	//Test method to create a new property with the new feature
	@Test(priority = 1)
	public void CreateNewPropertyWithNewFeature() {

		dashboardPOM = new DashboardPOM(driver);
		
		dashboardPOM.PropertyLinkSelection();
		
		createPropertyPOM = new CreatePropertyPOM(driver);
		
		createPropertyPOM.AddNewButtonSelection();

		createPropertyPOM.AddNewfeatureLinkSelection();
		String inputMessage = "BEST";
		createPropertyPOM.EnterNewFeatureTextBox(inputMessage);
		String inputSelection = "interior";
		createPropertyPOM.ParentFeatureDropdownSelection(inputSelection);
		createPropertyPOM.AddNewFeatureButtonSelection();

		driver.navigate().refresh();

		String propertyTitle = "prestige";
		createPropertyPOM.EnterPropertyTitleHere(propertyTitle);
		String textBoxMessage = "home town";
		createPropertyPOM.EnterTextBoxMessage(textBoxMessage);
		createPropertyPOM.AllFeatureCheckBoxSelection();
		createPropertyPOM.AllRegionCheckBoxSelection();
		createPropertyPOM.PublishButtonSelection();

		String expect = "Post published. View post";
		String actual = createPropertyPOM.viewPostMessage();
		Assert.assertEquals(actual, expect);

	}

}
