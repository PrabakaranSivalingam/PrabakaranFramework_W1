package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/* TagsPOM class define the webelements and the corresponding method from the Tag page of the real estate application */

public class TagsPOM {

	private WebDriver driver;

	public TagsPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Web Elements for the Tag page
	
	@FindBy(xpath = "//input[@id='tag-name']")
	private WebElement nameTags;

	@FindBy(xpath = "//input[@id='tag-slug']")
	private WebElement slugTags;
	@FindBy(xpath = "//textarea[@id='tag-description']")
	private WebElement descriptionTags;
	@FindBy(xpath = "//input[@id='submit']")
	private WebElement addNewTagButton;

	@FindBy(xpath = "//table/tbody[@id='the-list']/tr")
	private List<WebElement> row;

	@FindBy(xpath = "//h1[@class='wp-heading-inline']")
	private WebElement tagsTitle;

	// private String tagRowElement = "//table/tbody[@id='the-list']/tr";

	private String tagBeforeXpath = "//table/tbody[@id='the-list']/tr[";
	private String tagAfterXpath = "]/td[1]";

	// Methods for the Web Elements in the Tag page
	
	public String tagTitleCheck() {
		String tagTitle = tagsTitle.getText(); // Tags
		return tagTitle;
	}

	public void addNewTags(String nameTextBox, String slugTextBox, String desTextBox) {

		nameTags.sendKeys(nameTextBox);
		slugTags.sendKeys(slugTextBox);
		descriptionTags.sendKeys(desTextBox);
		addNewTagButton.click(); // No message on adding Tags, so not able to verify

	}

	public boolean nameCheck(String inputName) throws InterruptedException {

		driver.navigate().refresh();
		Thread.sleep(5000);

		boolean namePresent = false;

		for (int i = 1; i <= row.size(); i++) {
			String actualXpath = tagBeforeXpath + i + tagAfterXpath;
			WebElement element = driver.findElement(By.xpath(actualXpath));

			if (element.getText().equals(inputName)) {
				namePresent = true;
				break;
			}

		}

		return namePresent;
	}

}
