package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlogPOM {
	
	private WebDriver driver;

	public BlogPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@id='responsive']//a[contains(text(),'Blog')]")
	private WebElement blogMenuLink;
	
	@FindBy(xpath = "//a[@class='read-more'][contains(text(),'Read More')]")
	private List<WebElement> readMoreLink;
	
	@FindBy(xpath = "//textarea[@name='comment']")
	private WebElement commentDetails;
	
	@FindBy(xpath = "//input[@id='submit']")
	private WebElement postCommentButton;
	
	@FindBy(xpath = "//div[@class='post-content']/h3")
	private List<WebElement> element;
	
	public void selectBlogMenuLink() {
		
		blogMenuLink.click();
	}
	
	public void blogComment(String commentFromUser ) throws InterruptedException {
		
		commentDetails.click();
		commentDetails.sendKeys(commentFromUser);
		
		Thread.sleep(5000);
		
	}
	
	public void selectPostCommentButton() {
		
		postCommentButton.click();
	}
	
	public void userSelectPostReadMoreLink(String postName) throws InterruptedException {
		
		int i=0;
		
		while(i<element.size()) {
			
			WebElement ele = element.get(i);
			String id = ele.getText();
					
			if(id.equals(postName)) {
								
				readMoreLink.get(i).click();
				break;
			}
			i++;
		}
	}
}
