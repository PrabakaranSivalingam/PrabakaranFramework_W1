package com.training.functional.tests;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddNewPOM;
import com.training.pom.AllPostLinkPOM;
import com.training.pom.CategoriesPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PostLinkPOM;
import com.training.pom.TagsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class NewTagTest {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private PostLinkPOM postLinkPOM;
	private AllPostLinkPOM allPostLinkPOM;
	private AddNewPOM addNewPOM;
	private CategoriesPOM categoriesPOM;
	private TagsPOM tagsPOM;

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
		postLinkPOM = new PostLinkPOM(driver);
		screenShot.captureScreenShot("PostLink");
		postLinkPOM.selectPostLink();
		
			
		String[] actualLinks = postLinkPOM.postLinksValidation();
		screenShot.captureScreenShot("PostLink");
		String[] expectedLinks = {"All Posts","Add New","Categories","Tags"};
		Assert.assertEquals(actualLinks, expectedLinks);
		postLinkPOM.selectTagsLink();
		
		tagsPOM = new TagsPOM(driver);
		
				
		String expected = "Tags";
		String actual = tagsPOM.tagTitleCheck();
		Assert.assertEquals(actual, expected);
		
		String nameTextBox = "t1erq1";
		String slugTextBox = "Launch";
		String desTextBox = "New Launches of vilas, apartments, flats";
		tagsPOM.addNewTags(nameTextBox, slugTextBox, desTextBox);
		
		boolean nameAvailable = tagsPOM.nameCheck(nameTextBox);
		assertTrue(nameAvailable);
		
		Thread.sleep(3000);   
		
		}
}