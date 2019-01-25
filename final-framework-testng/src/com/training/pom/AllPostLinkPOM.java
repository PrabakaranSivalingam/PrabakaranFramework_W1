package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllPostLinkPOM {
	
	private WebDriver driver;
	
	public AllPostLinkPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//a[@class='row-title'][contains(text(),'New Launches')])[1]")
	private WebElement Title;
	@FindBy(xpath="(//a[@class='submitdelete'][contains(text(),'Trash')])[1]")
	private WebElement Trash;
	@FindBy(xpath="//div[@id='message']//p")
	private WebElement TrashMessage;
	@FindBy(xpath="//a[contains(text(),'Undo')]")
	private WebElement UndoMessage;
	
	
	public void postTrash() {
		Actions action = new Actions(driver);
		action.moveToElement(Title).perform();
		Trash.click();
	}
	
	public String TrashMessageValidation() {
		String trashMessage = TrashMessage.getText();
		return trashMessage;
	}
	
	public String UndoMessageValidation() {
		String undoMessage = UndoMessage.getText();
		return undoMessage;
	}

}
