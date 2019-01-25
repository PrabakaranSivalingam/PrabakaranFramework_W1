package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostLinkPOM {
	private WebDriver driver; 
	
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
		
		public PostLinkPOM(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
		public void selectPostLink() {
			this.selectPost.click();
		
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
		
		
			
}
