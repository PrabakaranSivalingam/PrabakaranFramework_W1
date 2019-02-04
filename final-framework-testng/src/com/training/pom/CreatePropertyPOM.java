package com.training.pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatePropertyPOM {

	private WebDriver driver;

	public CreatePropertyPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	@FindBy(xpath="//li[@id='menu-posts-property']")
//    private WebElement propertiesLink;
	@FindBy(xpath = "//a[@class='page-title-action']") // (//a[contains(text(),'Add New')])[1]
	private WebElement addNewButton;
	@FindBy(xpath = "//input[@name='post_title']")
	private WebElement enterTitleBox;
	@FindBy(xpath = "//textarea[@id='content']")
	private WebElement enterTextBox;
	@FindBy(xpath = "//input[@id='in-property_feature-132']")
	private WebElement allFeatureCheckBox;
	@FindBy(xpath = "//input[@id='in-region-24']")
	private WebElement allRegionCheckBox;
	@FindBy(xpath = "//input[@id='publish']")
	private WebElement publishButton;
	@FindBy(xpath = "//p[contains(text(),'Post published.')]") // p[contains(text(),'Post published.')]
																// //div[@id='message']
	private WebElement postMessageCheck;
	@FindBy(xpath = "//a[@id='property_feature-add-toggle']") // a[@id='property_feature-add-toggle']
	private WebElement addNewFeatureLink; // a[contains(text(),'+ Add New Feature')]
	@FindBy(xpath = "//input[@id='newproperty_feature']") // input[@id='newproperty_feature']
	private WebElement newFeatureTextBox;
	@FindBy(xpath = "//select[@id='newproperty_feature_parent']")
	private WebElement parentFeatureDropdown;
	@FindBy(xpath = "//input[@value='Add New Feature']") // input[@id='property_feature-add-submit']
	private WebElement addNewFeatureButton;

	@FindBy(xpath = "//a[@id='region-add-toggle']")
	private WebElement addNewRegionLink;

	@FindBy(xpath = "//input[@id='newregion']")
	private WebElement newRegionTextBox;

	@FindBy(xpath = "//select[@id='newregion_parent']")
	private WebElement parentRegionDropdown;

	@FindBy(xpath = "//input[@id='region-add-submit']")
	private WebElement addNewRegionButton;

	@FindBy(css = "a[class*='delete']")
	private WebElement moveToTrashLink;

	@FindBy(xpath = "//p[contains(text(),'1 post moved to the Trash.')]")
	private WebElement postMoveTrashMessage;

	@FindBy(css = "li.trash")
	private WebElement trashLink;

	@FindBy(xpath = "//tbody[@id='the-list']//tr[1]//td[1]")
	private WebElement trashPageContent;
	
	@FindBy(xpath="//label[@class='selectit']")
	private List<WebElement> elements;

	//private String allRegionElements = "(//ul[@id='regionchecklist']//ul[@class='children'])[1]";
	
	

	public String trashPageCheck() {

		String trashTitle = trashPageContent.getText();
		return trashTitle;
	}

	public void selectMoveToTrashLink() {

		moveToTrashLink.click();
	}

	public void selectTrashLink() {

		trashLink.click();
	}

	public String viewPostMoveTrashMessage() {

		String vPost = postMoveTrashMessage.getText(); // 1 post moved to the Trash. Undo
		return vPost;
	}

	public void leaveSitePopUp() {

		Alert alert = driver.switchTo().alert();
		alert.accept();

		driver.switchTo().defaultContent();
	}

	public void AddNewRegionLinkSelection() {
		addNewRegionLink.click();
	}

	public void EnterNewRegionTextBox(String inputMessage) {
		newRegionTextBox.sendKeys(inputMessage);
	}

	public void ParentRegionDropdownSelection(String inputSelection) {

		Select Selected = new Select(parentRegionDropdown);
//		List<WebElement> ele = Selected.getOptions();
//		for (WebElement e : ele) {
//			System.out.println(e.getText());
//			
//		}
		// Selected.selectByVisibleText(inputSelection);
		Selected.selectByValue("27");

	}

	public void AddNewRegionButtonSelection() {
		addNewRegionButton.click();
	}

	public void SelectRegionFromAllRegions(String inputRegion) throws InterruptedException {

		
		
		int i = 0;

		while (i < elements.size()) {
			WebElement ele = elements.get(i);
			String id = ele.getText();

			if (id.equals(inputRegion)) {

				elements.get(i).click(); 
				break;
			}
			i++;
		}
		Thread.sleep(5000);

	}

	public void AddNewfeatureLinkSelection() {
		addNewFeatureLink.click();
	}

	public void EnterNewFeatureTextBox(String inputMessage) {
		newFeatureTextBox.sendKeys(inputMessage);
	}

	public void ParentFeatureDropdownSelection(String inputSelection) {

		Select Selected = new Select(parentFeatureDropdown);
		Selected.selectByVisibleText(inputSelection);
	}

	public void AddNewFeatureButtonSelection() {
		addNewFeatureButton.click();
	}

	public void AddNewButtonSelection() {
		addNewButton.click();
	}

	public void EnterPropertyTitleHere(String inputMessage) {

		enterTitleBox.click();
		enterTitleBox.sendKeys(inputMessage);

	}

	public void EnterTextBoxMessage(String inputMessage) {

		enterTextBox.click();
		enterTextBox.sendKeys(inputMessage);
	}

	public void AllFeatureCheckBoxSelection() {
		allFeatureCheckBox.click();
	}

	public void AllRegionCheckBoxSelection() {
		allRegionCheckBox.click();
		;
	}

	public void PublishButtonSelection() throws InterruptedException {

		driver.switchTo().defaultContent();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250)", "");
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_PAGE_UP);
//		robot.keyRelease(KeyEvent.VK_PAGE_UP);

//		Actions action = new Actions(driver);
//		action.moveToElement(publishButton);

		Thread.sleep(3000);
		publishButton.click();
	}

	public String viewPostMessage() {

		String vPost = postMessageCheck.getText(); // Post published. View post
		return vPost;
	}
}
