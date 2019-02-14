package com.training.pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/* CreatePropertyPOM class define the webelements and the corresponding method from the property page of the 
 * real estate application */

public class CreatePropertyPOM {

	private WebDriver driver;

	public CreatePropertyPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Web Elements in Property page
	
//	@FindBy(xpath="//li[@id='menu-posts-property']")
//    private WebElement propertiesLink;
	@FindBy(xpath = "//a[@class='page-title-action']") // (//a[contains(text(),'Add New')])[1]
	private WebElement addNewButton;
	@FindBy(xpath = "//input[@name='post_title']")
	private WebElement enterTitleBox;
	@FindBy(xpath = "//textarea[@id='content']")
	private WebElement enterTextBox;
	@FindBy(xpath = "(//ul[@id='property_featurechecklist']//input)[1]") // //ul[@id='property_featurechecklist']//input //input[@id='in-property_feature-132']
	private WebElement allFeatureCheckBox;
	@FindBy(xpath = "(//ul[@id='regionchecklist']//input)[1]")   // //ul[@id='regionchecklist']//input  //input[@id='in-region-24']
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
	
	@FindBy(xpath="//a[contains(text(),'Features')]")
	private WebElement featuresLink;
	
	@FindBy(css = "textarea#_price")
	private WebElement priceTextBox;
	
	@FindBy(css = "input#_price_per")
	private WebElement pricePerSqTextBox;
	
	@FindBy(css = "a#ui-id-2")
	private WebElement mainDetailsTab;
	
	@FindBy(css = "a#ui-id-3")
	private WebElement locationTab;
	
	@FindBy(css = "input#_status")
	private WebElement mainDetailsStatus;
	
	@FindBy(css = "input#_location")
	private WebElement mainDetailsLocation;
	
	@FindBy(css = "input#_possession")
	private WebElement mainDetailsPossession;
	
	@FindBy(css = "input#_friendly_address")
	private WebElement locationAddress;
	
	@FindBy(css = "input#_address")
	private WebElement locationGoogleMapAddress;
	
	@FindBy(css = "input#_geolocation_lat")
	private WebElement locationLatitude;
	
	@FindBy(css = "input#_geolocation_long")
	private WebElement locationLongitude;
	
	@FindBy(css = "input#_storage_room")
	private WebElement detailsStorageRoom;
	
	@FindBy(xpath = "(//div[@class='categorychecklist-holder']//span[contains(text(),'Central Bangalore')]/preceding-sibling::input)[1]")
	private WebElement keywordTag;
	
	@FindBy(css = "a#ui-id-4")
	private WebElement detailsTab;
	
	//Methods for Web Elements in Property page
	
	public void selectKeywordTag() {
		keywordTag.click();
	}
	
	public void enterStoreageRoom(int room)
	{
		detailsStorageRoom.click();
		detailsStorageRoom.sendKeys(String.valueOf(room));
		
	}
	
	public void enterLongitude(double longitude)
	{
		locationLongitude.click();
		locationLongitude.sendKeys(String.valueOf(longitude));
		
	}
	
	public void enterLatitude(double latitude)
	{
		locationLatitude.click();
		locationLatitude.sendKeys(String.valueOf(latitude));
		
	}
	
	
	public void enterGoogleMapAddress(String mapAddress) {
		locationGoogleMapAddress.click();
		locationGoogleMapAddress.sendKeys(mapAddress);
		
	}
	
	public void enterLocationAddress(String address) {
		locationAddress.click();
		locationAddress.sendKeys(address);
		
	}
	
	public void enterPossession(String possession) {
		mainDetailsPossession.click();
		mainDetailsPossession.sendKeys(possession);
		
	}
	
	public void enterLocation(String location) {
		mainDetailsLocation.click();
		mainDetailsLocation.sendKeys(location);
		
	}
	
	public void enterStatus(String status) {
		mainDetailsStatus.click();
		mainDetailsStatus.sendKeys(status);
		
	}
	
		
	public void selectMainDetailsTab() {
		mainDetailsTab.click();
	}
	
	public void selectLocationTab() {
		locationTab.click();
	}
	public void selectDetailsTab() {
		detailsTab.click();
	}
	
	
	
	public void enterPricePerSq(double pricePer)
	{
		pricePerSqTextBox.click();
		pricePerSqTextBox.clear();
		pricePerSqTextBox.sendKeys(String.valueOf(pricePer));
		
	}
	
	public void enterPrice(double price)
	{
		priceTextBox.click();
		priceTextBox.clear();
		priceTextBox.sendKeys(String.valueOf(price));
		
	}
	
	
	
	public void selectFeaturesLink() {
		
		featuresLink.click();
	}
	

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

	public void SelectRegionFromAllRegions(String inputRegion) {

		
		
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
		//Thread.sleep(5000);

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
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -1500)", "");
		allFeatureCheckBox.click();
	}

	public void AllRegionCheckBoxSelection() {
		allRegionCheckBox.click();
		
	}

	public void PublishButtonSelection() {
		
		

		//driver.switchTo().defaultContent();
	//	((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250)", "");
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("span#edit-slug-buttons"))));
								
		//div#edit-slug-box
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_PAGE_UP);
//		robot.keyRelease(KeyEvent.VK_PAGE_UP);

//		Actions action = new Actions(driver);
//		action.moveToElement(publishButton);

		//Thread.sleep(3000);
	
		publishButton.click();
	}

	public String viewPostMessage() {

		String vPost = postMessageCheck.getText(); // Post published. View post
		return vPost;
	}
}
