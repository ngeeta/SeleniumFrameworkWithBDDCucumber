package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseClass;
import Utilities.WaitUtility;

public class StudentLoginPage extends BaseClass{
	/*
	 * public StudentLoginPage(WebDriver driver) { BaseClass.driver=driver;
	 * BaseClass.waitUtility=new WaitUtility(BaseClass.driver);
	 * PageFactory.initElements(driver, this); }
	 */
	 private WebDriver driver;

		public StudentLoginPage(WebDriver driver) {
			// Use ThreadLocal-safe getter/setter
	        BaseClass.setDriver(driver);   // sets ThreadLocal
	        this.driver = BaseClass.getDriver();

			BaseClass.waitUtility=new WaitUtility(this.driver);
			PageFactory.initElements(driver, this);
		}
	@FindBy (id="username") WebElement userName;
	public void enterUserName(String user) {
		userName.clear();
		userName.sendKeys(user);
	}
	
	@FindBy (id="password") WebElement password;
	public void enterPassword(String pwd) {
		password.clear();
		password.sendKeys(pwd);
	}
	
	@FindBy (id="submit") WebElement submitButton;
	public void clicksubmitButton() {
		submitButton.click();
	}

	@FindBy (xpath="//*[contains(text(),'Congratulations')]") WebElement successText;
	public String successText() {
	//	userName.sendKeys(user);
		return successText.getText();
	}
	
	
	@FindBy (xpath="//*[contains(text(),'Log out')]") WebElement logOutButton;
	public String logOutButton() {
		// userName.sendKeys(user);
		return logOutButton.getText();
	}
	// @FindBy(id = "error")   WebElement errorMessage;
		private By errorMessageBy = By.id("error");

	public String getErrorMessage() {
   	WebElement errorElement = waitUtility.waitForVisibility(errorMessageBy, 5);
    return errorElement.getText();


   }

}
