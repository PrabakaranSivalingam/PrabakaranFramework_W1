package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/* CategoriesPOM class define the webelements and the corresponding method from the Category page of the 
 * real estate application */

public class CategoriesPOM {

	private WebDriver driver;
	
	public CategoriesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Web Elements in Category page
	
	@FindBy(xpath="//h1[@class='wp-heading-inline']")
	private WebElement categoriesTitle;
	@FindBy(xpath="//input[@id='tag-name']")
	private WebElement nameCategory;
	@FindBy(xpath="//input[@id='tag-slug']")
	private WebElement slugCategory;
	@FindBy(xpath="//textarea[@id='tag-description']")
	private WebElement descriptionCategory;
	@FindBy(xpath="//input[@id='submit']")
	private WebElement addNewCategoryButton;
	@FindBy(xpath="//select[@id='bulk-action-selector-bottom']")
	private WebElement bulkActionDeleteSelection;
	@FindBy(xpath="//input[@id='doaction2']")
	private WebElement applyButton;
	
	@FindBy(xpath="//table/tbody[@id='the-list']/tr[1]/th[1]/input[@type='checkbox']")   
	private WebElement categoryCheckbox;
	
	@FindBy(xpath="//p[contains(text(),'Categories deleted.')]")  ////div[@id='message']  //p[contains(text(),'Categories deleted.')] 
	private WebElement deleteMessage;
	
	//private List<WebElement> rows = driver.findElements(By.xpath("//table/tbody[@id='the-list']/tr"));
	
	private String beforeXpath = "//table/tbody[@id='the-list']/tr[";
	private String afterXpath = "]/td[1]";
	
	@FindBy(xpath="//table/tbody[@id='the-list']/tr")   
	private List<WebElement> row;
	
	// Methods for the Web Elements in Category page
	
	public String categoriesTitleCheck() {
		String catTitle = categoriesTitle.getText();    // Categories
		return catTitle;
	}
	
	public void addNewCategory(String nameTextBox, String slugTextBox, String desTextBox) throws InterruptedException {
		
		nameCategory.sendKeys(nameTextBox);
		slugCategory.sendKeys(slugTextBox);
		descriptionCategory.sendKeys(desTextBox);
		Thread.sleep(3000);
		addNewCategoryButton.submit(); //No Action on clicking this button
		
	}
	
	public void deleteCategory() throws InterruptedException {
		
		Thread.sleep(5000);
		categoryCheckbox.click();
		Select delButton = new Select(bulkActionDeleteSelection);   // Bulk Actions   Delete
		delButton.selectByVisibleText("Delete");
		applyButton.click();
		
	}
	
	public String categoryDeleteConfirmation() {
		
		String catDelete = deleteMessage.getText();    // Categories deleted.
		return catDelete;
	                                            
	}
	
	public boolean nameCheck(String inputName) {
		
		boolean namePresent=false;
		
		int rowCount = row.size();		
		
		for (int i = 1; i <= rowCount; i++) {
			String actualXpath = beforeXpath+i+afterXpath;
			WebElement element = driver.findElement(By.xpath(actualXpath));
			
			if(element.getText().equals(inputName))
			{
				namePresent = true;
				break;
			}
			
		}
		
		return namePresent;
	}
	
}
