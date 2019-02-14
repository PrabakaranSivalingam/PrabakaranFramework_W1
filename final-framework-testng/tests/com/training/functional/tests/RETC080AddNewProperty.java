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
import com.training.pom.RealEstatePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/* 
 * Author					:	Prabakaran Sivalingam
 * Test Case ID				:	RETC_080
 * Test Case Description	:	To verify whether application allows admin to add new property with all details & added 
 *                              property details in home screen for user
 * Pre Condition			:	1. user should have launched the application by entering valid URL
 *								2. admin should be logged in
 */

public class RETC080AddNewProperty {
  
	private WebDriver driver; 				
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CreatePropertyPOM createPropertyPOM;
	private DashboardPOM dashboardPOM;
	private RealEstatePOM realEstatePOM;

	
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
	
	//Test method to create a new property with all the complete details

	@Test(priority = 1)
	public void CreateNewPropertyWithCompleteDetails() throws InterruptedException {

		dashboardPOM = new DashboardPOM(driver);
		dashboardPOM.PropertyLinkSelection();
		
		createPropertyPOM = new CreatePropertyPOM(driver);
		createPropertyPOM.AddNewButtonSelection();
		String propertyName = "Manyatablr";
		createPropertyPOM.EnterPropertyTitleHere(propertyName);
		String textMessage = "manyatablr";
		createPropertyPOM.EnterTextBoxMessage(textMessage);
		double price=50000.00;
		createPropertyPOM.enterPrice(price);
		double pricePer=200.00;
		createPropertyPOM.enterPricePerSq(pricePer);
		createPropertyPOM.selectMainDetailsTab();
		String status = "New";
		createPropertyPOM.enterStatus(status);
		String location = "Electronic city";
		createPropertyPOM.enterLocation(location);
		String possession = "immediate";
		createPropertyPOM.enterPossession(possession);
		createPropertyPOM.selectLocationTab();
		String address = "yeshwanthapur";
		createPropertyPOM.enterLocationAddress(address);
		String mapAddress = "yeshwanthapur";
		createPropertyPOM.enterGoogleMapAddress(mapAddress);
		double latitude = 120;
		createPropertyPOM.enterLatitude(latitude);
		double longitude = 56;
		createPropertyPOM.enterLongitude(longitude);
		createPropertyPOM.selectDetailsTab();
		int room = 3;
		createPropertyPOM.enterStoreageRoom(room);
		createPropertyPOM.selectKeywordTag();
		
		createPropertyPOM.AllFeatureCheckBoxSelection();
		createPropertyPOM.AllRegionCheckBoxSelection();
		createPropertyPOM.PublishButtonSelection();
		
		String expect = "Post published. View post"; 
		String actual = createPropertyPOM.viewPostMessage();
		Assert.assertEquals(actual, expect);
		
		dashboardPOM = new DashboardPOM(driver);
		dashboardPOM.selectLogout();
		
		realEstatePOM = new RealEstatePOM(driver);
		realEstatePOM.selectRealEstateLogo();
		realEstatePOM.inputSearchTextBox(propertyName);
		String actualSearchPropertyName = realEstatePOM.searchResultInfo();
		Assert.assertEquals(actualSearchPropertyName, propertyName);
		
	}

}
