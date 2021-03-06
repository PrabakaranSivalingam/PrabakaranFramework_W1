package com.training.functional.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import com.training.generics.ScreenShot;
import com.training.pom.AddNewPOM;
import com.training.pom.AllPostLinkPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
public class AddNewTest {
	
		private WebDriver driver;     //Test case of W1-Two-RETC_017
		private String baseUrl;
		private LoginPOM loginPOM;
		private static Properties properties;
		private ScreenShot screenShot;
		private DashboardPOM dashboardPOM;
		private AllPostLinkPOM allPostLinkPOM;
		private AddNewPOM addNewPOM;

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
		
		@AfterClass
		public void tearDown() throws Exception {
			Thread.sleep(1000);
			driver.quit();
		}
		@Test(priority=0)
		public void validLoginTest() {
			loginPOM.sendUserName("admin");
			loginPOM.sendPassword("admin@123");
			screenShot.captureScreenShot("Login");
			loginPOM.clickLoginBtn(); 
			
			}
		
		@Test(priority=1)
		public void AddNewtest() throws InterruptedException {
			dashboardPOM = new DashboardPOM(driver);
			screenShot.captureScreenShot("PostLink");
			dashboardPOM.selectPostLink();
			
				
			String[] actualLinks = dashboardPOM.postLinksValidation();
			screenShot.captureScreenShot("PostLink");
			String[] expectedLinks = {"All Posts","Add New","Categories","Tags"};
			Assert.assertEquals(actualLinks, expectedLinks);
			dashboardPOM.selectAddNewLink();
			
			addNewPOM = new AddNewPOM(driver);
			String expected = "Add New Post";
			String actual = addNewPOM.addNewPostTitle();
			Assert.assertEquals(actual, expected);
			String enterTitleHere = "New Launches12345";
			String textBody = " New Launch in Home12345";
			addNewPOM.addNewPost(enterTitleHere, textBody);
						
			String expect = "Post published. View post"; 
			String act = addNewPOM.viewPostMessage();
			Assert.assertEquals(act, expect);
					
		}
	}



