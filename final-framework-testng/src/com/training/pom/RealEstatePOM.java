package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/* RealEstatePOM class define the webelements and the corresponding method from the Real estate page of the 
 * real estate application */

public class RealEstatePOM {

	private WebDriver driver;
	
	public RealEstatePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Web Elements for the Real estate page
	
	@FindBy(xpath="//div[@id='logo']//a")
	private WebElement realEstateLogo;
	
	@FindBy(css="input.orig")
	private WebElement searchTextBox;
	
	@FindBy(css="div.innericon")
	private WebElement searchIcon;
	
	@FindBy(xpath="(//div[@class='asl_content']//a)[1]")
	private WebElement searchResult;
	
	//Methods for the Web Elements in the Real estate page
	
	public String searchResultInfo() {
		
		String result = searchResult.getText();
		return result;
		
	}
	public void selectRealEstateLogo() {
		
		realEstateLogo.click();
	}
	
	public void inputSearchTextBox(String propertyName) {
		
		searchTextBox.click();
		searchTextBox.sendKeys(propertyName);
		searchIcon.click();
	}
	
}
