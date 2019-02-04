package com.training.functional.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.BlogPOM;
import com.training.pom.CommentsPOM;
import com.training.pom.CreatePropertyPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class BlogTest {

	private WebDriver driver;			// Test case of W2-Four-RETC_049
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CreatePropertyPOM createPropertyPOM;
	private DashboardPOM dashboardPOM;
	private BlogPOM blogPOM;
	private CommentsPOM commentsPOM;


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
		baseUrl = properties.getProperty("baseUserURL"); // User
		//parent = driver.getWindowHandle();
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
		
	}

	@Test(priority = 0)
	public void validLoginTest() {

		loginPOM.clickSignINLink();
		loginPOM.sendUserName("prabu");
		loginPOM.sendPassword("prabu123456789");
		screenShot.captureScreenShot("Login");
		loginPOM.clickLoginBtn();
	}

	@Test(priority = 1)
	public void userBlogSection() throws InterruptedException {

		blogPOM = new BlogPOM(driver);

		blogPOM.selectBlogMenuLink();
		String postName = "Testing";
		blogPOM.userSelectPostReadMoreLink(postName);
		String commentFromUser = "NewOne";
		blogPOM.blogComment(commentFromUser);
		blogPOM.selectPostCommentButton();
		driver.close();

	}

	@Test(priority = 2)
	public void validAdminLoginTest() {
		
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
//		Actions action = new Actions(driver);
//		action.sendKeys(Keys.chord(Keys.CONTROL,"N")).build().perform();
		driver.get(baseUrl);

		loginPOM = new LoginPOM(driver);

		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		screenShot.captureScreenShot("Login");
		loginPOM.clickLoginBtn();
		
	}

	@Test(priority = 3)
	public void adminCommentReply() throws InterruptedException {

		dashboardPOM = new DashboardPOM(driver);
		dashboardPOM.CommentsLinkSelection();

		commentsPOM = new CommentsPOM(driver);
		commentsPOM.findUserComment("NewOne");
		int beforeCountValue = commentsPOM.beforeInResponseToCount("NewOne");
		int expected = beforeCountValue + 1;
		commentsPOM.enterReplyComment();
		int afterCountValue = commentsPOM.afterInResponseToCount("NewOne");

		Assert.assertEquals(afterCountValue, expected);
		
		
	}

}
