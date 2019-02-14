package com.training.functional.tests;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.CreatePropertyPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.FeaturesPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/* 
 * Author					:	Prabakaran Sivalingam
 * Test Case ID				:	RETC_079
 * Test Data				:	RETC_079Data
 * Test Case Description	:	To Verify whether application allows admin to Add multiple New Feature in the application
 * Pre Condition			:	1. user should have launched the application by entering valid URL
 *								2. admin should be logged in
 */

public class MultipleNewFeatureAdd {
	private WebDriver driver; 				
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CreatePropertyPOM createPropertyPOM;
	private DashboardPOM dashboardPOM;
	private FeaturesPOM featuresPOM;
	
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
		//Thread.sleep(10000);
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
	
	//Test method to create new property by adding multiple new features with the data read from excel sheet

	@Test(priority=1, dataProvider = "RETC_079", dataProviderClass = LoginDataProviders.class)
	public void CreateNewPropertyWithNewFeatureMultiple(String name, String slug, String parentFeature, String description)    {

		dashboardPOM = new DashboardPOM(driver);
		dashboardPOM.PropertyLinkSelection();
		
		createPropertyPOM = new CreatePropertyPOM(driver);
		createPropertyPOM.selectFeaturesLink();
		
		featuresPOM = new FeaturesPOM(driver);
		featuresPOM.enterName(name);
		featuresPOM.enterSlug(slug);
		featuresPOM.parentFeatureSelection(parentFeature);
		featuresPOM.enterDescription(description);
		featuresPOM.selectAddNewFeatureButton();
		String actualName = featuresPOM.searchFeatureName(name);
		
		Assert.assertEquals(actualName, name);
				
	}
}
