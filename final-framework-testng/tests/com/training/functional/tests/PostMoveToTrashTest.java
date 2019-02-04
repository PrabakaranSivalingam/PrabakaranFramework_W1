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

public class PostMoveToTrashTest {

	private WebDriver driver;			// Test case of W2-Five-RETC_050
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CreatePropertyPOM createPropertyPOM;
	private DashboardPOM dashboardPOM;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
//	}
//
//	@BeforeMethod
//	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(10000);
		driver.quit();
	}

	@Test(priority = 0)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		screenShot.captureScreenShot("Login");
		loginPOM.clickLoginBtn();

	}

	@Test(priority = 1)
	public void PostMoveToTrash() throws InterruptedException {

		createPropertyPOM = new CreatePropertyPOM(driver);
		dashboardPOM = new DashboardPOM(driver);
		dashboardPOM.PropertyLinkSelection();
		createPropertyPOM.AddNewButtonSelection();
		String propertyTitle = "prestige";
		createPropertyPOM.EnterPropertyTitleHere(propertyTitle);
		String textBoxMessage = "home town";
		createPropertyPOM.EnterTextBoxMessage(textBoxMessage);
		createPropertyPOM.AllFeatureCheckBoxSelection();
		createPropertyPOM.AllRegionCheckBoxSelection();

		createPropertyPOM.selectMoveToTrashLink();
		createPropertyPOM.leaveSitePopUp();

		String expect = "1 post moved to the Trash. Undo";
		String actual = createPropertyPOM.viewPostMoveTrashMessage();
		Assert.assertEquals(actual, expect);

		createPropertyPOM.selectTrashLink();

		String expectTrash = "prestige";
		String actualTrash = createPropertyPOM.trashPageCheck();
		Assert.assertEquals(actualTrash, expectTrash);

	}

}
