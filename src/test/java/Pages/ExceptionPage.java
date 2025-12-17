package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import Utilities.WaitUtility;

public class ExceptionPage extends BaseClass {
	private WebDriver driver;

	public ExceptionPage(WebDriver driver) {
		this.driver = BaseClass.getDriver();
		BaseClass.waitUtility = new WaitUtility(this.driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "add_btn")
	WebElement addRowTwo;

	public void addRow2() {
		addRowTwo.click();
	}

	@FindBy(xpath = "//div[@id='row2']/*[@class='input-field']")	WebElement addRow2;
	public void addInput(String str) {
		addRow2.sendKeys(str);
	}
	WaitUtility waitUtility=new WaitUtility(BaseClass.getDriver());
	public boolean isDisplayed1() {
		WebElement element = waitUtility.waitForVisibility(By.id("row2"), 20); // adjust locator & timeout
		if (element != null) {
	        System.out.println("------ Element is displayed: " + element.isDisplayed());
	        return element.isDisplayed();
	    } else {
	        System.out.println("------ Element not found or not visible");
	        return false;
	    }

	}
	

	
	@FindBy (id="confirmation") WebElement confirm;
	public String confirmText() {
		return confirm.getText();
	}

}
