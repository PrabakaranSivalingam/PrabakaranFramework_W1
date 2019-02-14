package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/* DashboardPOM class define the webelements and the corresponding method from the Dashboard page of the 
 * real estate application */

public class DashboardPOM {    
		private WebDriver driver; 
		
		public DashboardPOM(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		// Web elements for Dashboard page
		
		@FindBy(xpath="//div[contains(text(),'Posts')]")
		private WebElement selectPost;
		@FindBy(xpath="//a[@class='wp-first-item current']")
		private WebElement selectAllPost;
		@FindBy(xpath="//a[@href='post-new.php']")
		private WebElement selectAddNew;
		@FindBy(xpath="//a[@href='edit-tags.php?taxonomy=category']")
		private WebElement selectCatagories;
		@FindBy(xpath="//a[@href='edit-tags.php?taxonomy=post_tag']")
		private WebElement selectTags;
		@FindBy(xpath="//li[@id='menu-posts-property']")
	    private WebElement propertiesLink;
		
		@FindBy(css="li#menu-comments")
		private WebElement selectComments;
		
		// Navigate Dashboard >> Users link
		@FindBy(css="li#menu-users")
		private WebElement selectUsers;
		
		@FindBy(xpath="//a[contains(text(),'Howdy')]")  //span.display-name
		private WebElement selectadmin;
		
		@FindBy(xpath="//a[contains(@class,'ab-item')][contains(text(),'Log Out')]")
		private WebElement logout;
		
			
		
		// Methods for Web elements in Dashboard page
		
		public void selectPostLink() {
			this.selectPost.click();
		
		}
		
		public void selectLogout() {
			
			Actions action = new Actions(driver);
			action.moveToElement(selectadmin).perform();
			logout.click();
		}
		
		public void selectAllPostLink() {
			this.selectAllPost.click();
		
		}
		public void selectAddNewLink() {
			this.selectAddNew.click();
		
		}
		public void selectCatagoriesLink() {
			this.selectCatagories.click();
		
		}
		public void selectTagsLink() {
			this.selectTags.click();
		
		}
		
		public String[] postLinksValidation() {
						
			String[] actual = new String[4];
			actual[0]= selectAllPost.getText();
			actual[1]= selectAddNew.getText();
			actual[2]= selectCatagories.getText();
			actual[3]= selectTags.getText();
			return actual;
		}
		
		public void PropertyLinkSelection() {
			
			propertiesLink.click();
		}
		
		public void CommentsLinkSelection() {
					
			selectComments.click();
		}
		
		public void UsersLinkSelection() {
			
			selectUsers.click();
		}
		
		
			
}
