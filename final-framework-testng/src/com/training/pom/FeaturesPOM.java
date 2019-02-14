package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/* FeaturesPOM class define the webelements and the corresponding method from the Feature page of the 
 * real estate application */

public class FeaturesPOM {

	private WebDriver driver;

	public FeaturesPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Web Elements for Feature page

	@FindBy(css = "input#tag-name")
	private WebElement nameTextBox;
	
	@FindBy(css = "input#tag-slug")
	private WebElement slugTextBox;
	
	@FindBy(css = "select#parent")
	private WebElement parentFeatureDropDown;
	
	@FindBy(css = "textarea#tag-description")
	private WebElement descriptionTextBox;
	
	@FindBy(css = "input#submit")
	private WebElement addNewFeatureButton;
	
	@FindBy(css ="input#tag-search-input")
	private WebElement searchFeatureTextBox;
	
	@FindBy(css ="input#search-submit")
	private WebElement searchFeatureButton;
	
	@FindBy(xpath ="//table/tbody[@id='the-list']//tr[1]/td[1]//a[@class='row-title']")
	private WebElement featureNameInSearch;
	
	// Methods for the Web Elements in Feature page
	
	public String searchFeatureName(String featureName) {
		
		searchFeatureTextBox.click();
		searchFeatureTextBox.sendKeys(featureName);
		searchFeatureButton.click();
		
		String name = featureNameInSearch.getText();
		System.out.println(name);
		return name;
	}
	
	public void enterName(String name) {
		nameTextBox.click();
		nameTextBox.sendKeys(name);
		
	}
	
	public void enterSlug(String slug) {
		slugTextBox.click();
		slugTextBox.sendKeys(slug);
	}
	
	public void parentFeatureSelection(String featureName) {
		
		//select[@id='parent']/option[contains(text(),'Best')]
		String beforePath = "//select[@id='parent']/option[contains(text(),'";
		String afterPath = "')]";
		String actualPath = beforePath+featureName+afterPath;
		WebElement parentFeatureDropDown1 = driver.findElement(By.xpath(actualPath));
		//Select parentFeature = new Select(parentFeatureDropDown1);
		//parentFeature.selectByVisibleText(featureName);
		parentFeatureDropDown1.click();
	
		
	}
	
	public void enterDescription(String description) {
		descriptionTextBox.click();
		descriptionTextBox.sendKeys(description);
	}
	
	public void selectAddNewFeatureButton() {
		addNewFeatureButton.click();
	}
}
