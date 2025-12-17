package StepDefinitions;

import org.testng.Assert;

import Base.BaseClass;
import Pages.ExceptionPage;
import io.cucumber.java.en.*;

public class ExceptionPageStepDef extends BaseClass {
ExceptionPage ep;
	
	@Given("user is on the exception page")
	public void user_is_on_the_exception_page() {
	    ep=new ExceptionPage(BaseClass.getDriver());
	    BaseClass.getDriver().get(config.getProperty("ExceptionPage"));
	}

	@When("the user add row2")
	public void the_user_add_row2() {
	    ep.addRow2();
	}
	@Then("row2 is displayed")
	public void row2_is_displayed() throws InterruptedException {
		System.out.println("------"+ ep.isDisplayed1());
		Assert.assertEquals(ep.isDisplayed1(),true);
	
	}
	@When("enter details in row2")
	public void enter_details_in_row2() {
	    ep.addInput("random");
	}

	@Then("verify message {string}")
	public void verify_message(String string) {
		Assert.assertEquals(ep.confirmText(), string);
		//Assert.assertTrue(ep.confirmText().contains(string),
				//"Expected error message to contain: " + string + " but got: " + ep.confirmText());
	}

}
