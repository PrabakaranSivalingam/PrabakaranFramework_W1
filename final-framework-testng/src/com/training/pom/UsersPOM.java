package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/* UsersPOM class define the webelements and the corresponding method from the User page of the real estate application */

public class UsersPOM {
	
	WebDriver driver;
	
		public UsersPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	// WebElements under AddNew User page
		
	@FindBy(xpath = "//a[@class='page-title-action']") 
	private WebElement addNewButton;
	
	@FindBy(css = "input#user_login") 
	private WebElement addNewUser_UserName;
	
	@FindBy(css = "input#email") 
	private WebElement addNewUser_Email;
	
	@FindBy(css = "input#first_name") 
	private WebElement addNewUser_FirstName;
	
	@FindBy(css = "input#last_name") 
	private WebElement addNewUser_LastName;
	
	@FindBy(css = "input#url") 
	private WebElement addNewUser_Website;
	
	
	@FindBy(css = "button[class*='wp-generate-pw']") 
	private WebElement addNewUser_ShowPasswordButton;
	
	@FindBy(css = "input#pass1-text") 
	private WebElement addNewUser_PasswordTextBox;
	
	@FindBy(css = "button[class*='wp-hide-pw']")
	private WebElement addNewUser_PasswordHideButton;
	
	
	@FindBy(css = "button[class*='wp-cancel-pw']")
	private WebElement addNewUser_PasswordCancelButton;
	
	
	@FindBy(css = "select#role")
	private WebElement addNewUser_Role;
	
	
	@FindBy(css = "input#createusersub")
	private WebElement addNewUserButton;
	
	@FindBy(xpath = "//div[@id='message']/p")  //div#message   //div[@id='message']/p  ////p/a[contains(text(),'Edit user')]
	private WebElement userCreationMessage;
	
	
	@FindBy(xpath = "//div[@class='error']/p")
	private WebElement errorUserCreationMessage;
	
	// Creating Methods for AddNew User page
	
	public void clickAddNewButton() {
		addNewButton.click();
		
	}
	
	public void enterUsername(String username) {
		addNewUser_UserName.click();
		addNewUser_UserName.clear();
		addNewUser_UserName.sendKeys(username);
		
	}
	
	public String getAppUserName() {
		String appUserName = addNewUser_UserName.getAttribute("value");
		return appUserName;
		
	}
	
	public String getAppEmail() {
		String appEmail = addNewUser_Email.getAttribute("value");
		return appEmail;
	}
	
	public String getAppFirstName() {
		String appFirstName = addNewUser_FirstName.getAttribute("value");
		return appFirstName;
	}
	
	public String getAppLastName() {
		String appLastName = addNewUser_LastName.getAttribute("value");
		return appLastName;
	}
	
	public String getAppWebsite() {
		String appWebsite = addNewUser_Website.getAttribute("value");
		return appWebsite;
	}
	
	public String getAppPassword() {
		String appPassword = addNewUser_PasswordTextBox.getAttribute("value");
		return appPassword;
	}
	
	public String getAppRole() {
		//String appRole=null;
		/* if(addNewUser_Role.isSelected()) {
		appRole = addNewUser_Role.getText(); }
		return appRole; */
		
		Select role = new Select(addNewUser_Role);
		WebElement element = role.getFirstSelectedOption();
		String appRole = element.getText();
		return appRole;
	}
	
	public void enterEmail(String email) {
		addNewUser_Email.click();
		addNewUser_Email.clear();
		addNewUser_Email.sendKeys(email);
		
	}
	
	public void enterFirstName(String firstname) {
		addNewUser_FirstName.click();
		addNewUser_FirstName.clear();
		addNewUser_FirstName.sendKeys(firstname);
		
	}
	
	public void enterLastName(String lastname) {
		addNewUser_LastName.click();
		addNewUser_LastName.clear();
		addNewUser_LastName.sendKeys(lastname);
		
	}
	
	public void enterWebsite(String website) {
		addNewUser_Website.click();
		addNewUser_Website.clear();
		addNewUser_Website.sendKeys(website);
		
	}
	
	public void clickShowPasswordButton() {
		addNewUser_ShowPasswordButton.click();
	}
	
	public void enterPassword(String password) {
		addNewUser_PasswordTextBox.click();
		addNewUser_PasswordTextBox.clear();
		addNewUser_PasswordTextBox.sendKeys(password);
		
	}
	
	public void selectRole(String userRole) {
		
		Select role = new Select(addNewUser_Role);
		role.selectByVisibleText(userRole);
		
	}
	
	public void clickAddNewUserButton() {
		addNewUserButton.click();
	}
	
	public String confirmUserCreationMessage() {
		String actual=userCreationMessage.getText();
		return actual;
	}
	
	public String errorUserCreationMessage() {
		String actual = errorUserCreationMessage.getText();
		return actual;
	}
	
	/* public String borderColorValidation() {
		String colorCode=null;
		colorCode = addNewUser_UserName.getCssValue("border-color");
		System.out.println(colorCode);
		
		return colorCode;
	} */
	
}
