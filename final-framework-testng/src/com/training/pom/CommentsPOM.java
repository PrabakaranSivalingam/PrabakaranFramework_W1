package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/* CommentsPOM class define the webelements and the corresponding method from the Comments
 *  page of the real estate application */

public class CommentsPOM {

	private WebDriver driver;

	public CommentsPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Web Elements in Comments page
	
	@FindBy(xpath = "//textarea[@id='replycontent']")
	private WebElement replyCommentTextBox;

	@FindBy(xpath = "//span[@id='replybtn']")
	private WebElement approveAndReplyButton;

	@FindBy(css = "span[class^='comment'][class$='comments']")
	private WebElement responseToCount;
	
	@FindBy(xpath ="//tbody[@id='the-comment-list']//tr//td//p")
	private List<WebElement> element;
	
	@FindBy(css = "span.comment-count-approved")
	private WebElement CountsApproved;
	
	@FindBy(css = "span[class*='comment-count-pending']") //comment-count-no-comments
	private WebElement CountsBeforeApprove;
	
	public String beforeXpath = "//tr[";
	public String afterXpath = "]//td[2]/p";
	
	

	// Methods for the Web Elements in Comments page
	
	public void findUserComment(String userComment) {

		for (int i = 1; i <= element.size(); i++) {

			String actualXpath = beforeXpath + i + afterXpath;

			WebElement ele = driver.findElement(By.xpath(actualXpath));
			String id = ele.getText();

			if (id.equals(userComment)) {

				Actions action = new Actions(driver);
				action.moveToElement(ele).perform();
				String replyLinkXpath = "//tr[" + i + "]//a[contains(text(),'Reply')]";
				WebElement replyLink = driver.findElement(By.xpath(replyLinkXpath));
				replyLink.click();
				
				break;
			}

		}
		//Thread.sleep(3000);
	}

	public void enterReplyComment() {
		replyCommentTextBox.click();
		replyCommentTextBox.sendKeys("Thanks");
		approveAndReplyButton.click();
		//driver.navigate().refresh();
		//Thread.sleep(3000);

	}

	public int beforeInResponseToCount(String userComment) {

		String beforeCountValue = null;

		for (int i = 1; i <= element.size(); i++) {

			String actualXpath = beforeXpath + i + afterXpath;

			WebElement ele = driver.findElement(By.xpath(actualXpath));
			String id = ele.getText();

			if (id.equals(userComment)) {

				WebElement beforeCount = CountsBeforeApprove;
				beforeCountValue = beforeCount.getText();
				break;
			}

		}

		int beforeValue = Integer.parseInt(beforeCountValue);
		return beforeValue;
	}

	public int afterInResponseToCount(String userComment) {

		String afterCountValue = null;

		for (int i = 1; i <= element.size(); i++) {

			String actualXpath = beforeXpath + i + afterXpath;
			WebElement ele = driver.findElement(By.xpath(actualXpath));
			String id = ele.getText();

			if (id.equals(userComment)) {

				WebElement afterCount = CountsApproved;
				afterCountValue = afterCount.getText();
				break;
			}

		}

		int afterValue = Integer.parseInt(afterCountValue);
		return afterValue;
	}

}
