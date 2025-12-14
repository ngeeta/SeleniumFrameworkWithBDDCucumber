package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Base.BaseClass;
import Pages.StudentLoginPage;
import io.cucumber.java.en.*;

public class StudentLoginPageStepDef extends BaseClass {

	StudentLoginPage sp;

	@Given("user is on the login page")
	public void user_is_on_the_login_page() {
		sp = new StudentLoginPage(BaseClass.getDriver());

		// Always use the ThreadLocal-safe getter
		BaseClass.getDriver().get(config.getProperty("StudentLoginPage"));
	}

	@When("the user enters username {string} and password {string}")
	public void the_user_enters_username_and_password(String user, String pwd) {
		if (user.equals("VALID_USER")) {
			user = config.getProperty("userId");

		}
		if (pwd.equals("VALID_PASS")) {
			pwd = config.getProperty("userPass");

		}
		sp.enterPassword(pwd);
		sp.enterUserName(user);

	}

	@When("user enters valid username and password")
	public void user_enters_valid_username_and_password() {
		sp.enterUserName(config.getProperty("userId"));
		sp.enterPassword(config.getProperty("pwd"));
	}

	@When("clicks on login button")
	public void clicks_on_login_button() {
		sp.clicksubmitButton();
	}

	@Then("verify new page URL contains {string}")
	public void verify_new_page_url_contains(String url) {
		Assert.assertEquals(BaseClass.getDriver().getCurrentUrl(), url);

	}

	@Then("verify new page contains expected text {string}")
	public void verify_new_page_contains_expected_text(String msg) {
		Assert.assertEquals(sp.successText(), msg);
	}

	@Then("Verify button Log out is displayed on the new page")
	public void verify_button_log_out_is_displayed_on_the_new_page() {
		Assert.assertEquals(sp.logOutButton(), "Log out");

	}

	@Then("an error message {string} should be displayed")
	public void an_error_message_should_be_displayed(String err) {
		String actualMessage = sp.getErrorMessage();
		Assert.assertTrue(actualMessage.contains(err),
			    "Expected error message to contain: " + err + " but got: " + actualMessage);
	}

	
}
