package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/* AddNewPOM class define the webelements and the corresponding method from the Post page of the real estate application */


public class AddNewPOM {
	
		    private WebDriver driver;
	
			public AddNewPOM(WebDriver driver) {
				this.driver = driver; 
				PageFactory.initElements(driver, this);
			}
			
			// Web Elements for Post page
			@FindBy(xpath="//input[@id='title']")
			private WebElement enterTitleNew;
			
			@FindBy(xpath="//textarea[@id='content']")
			private WebElement bodyText;
			
			@FindBy(xpath="//input[@id='publish']")
			private WebElement publishButton;
			    
			@FindBy(xpath="//h1[@class='wp-heading-inline']")
			private WebElement addNewPostHeader;
			
			@FindBy(xpath="//div[@id='message']//p")   //a[contains(text(),'View post')]")
			private WebElement viewPost;
			
			
			// Methods for the Web Elements in Post page
			public String addNewPostTitle() {
				
				String addnewPosttitle = addNewPostHeader.getText();
				return addnewPosttitle;
				
			}
			
			public void addNewPost(String enterTitleHere, String textBody) throws InterruptedException {
				enterTitleNew.sendKeys(enterTitleHere);
				bodyText.sendKeys(textBody);
				Thread.sleep(3000);
				publishButton.click();
			}
			
			public String viewPostMessage() {
				
				String vPost = viewPost.getText();  // Post published. View post
				return vPost;
			}
			
}
