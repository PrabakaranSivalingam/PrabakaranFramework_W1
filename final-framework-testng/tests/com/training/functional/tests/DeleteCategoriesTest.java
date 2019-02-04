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
import com.training.pom.CategoriesPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class DeleteCategoriesTest {
	
		private WebDriver driver;			//Test case of W1-Four-RETC_019
		private String baseUrl;
		private LoginPOM loginPOM;
		private static Properties properties;
		private ScreenShot screenShot;
		private DashboardPOM dashboardPOM;
		private AllPostLinkPOM allPostLinkPOM;
		private AddNewPOM addNewPOM;
		private CategoriesPOM categoriesPOM;

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
			dashboardPOM.selectCatagoriesLink();
			
			categoriesPOM = new CategoriesPOM(driver);
			
			categoriesPOM.deleteCategory();
			
			String expected = "Categories deleted.";
			String actual = categoriesPOM.categoryDeleteConfirmation();
			Assert.assertEquals(actual, expected);
			
			Thread.sleep(3000);   
			
			}
	}