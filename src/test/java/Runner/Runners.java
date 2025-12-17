package Runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
		features = "src/test/resources/Features/StudentLogin.feature",
		//features = "src/test/resources/Features/ExceptionPage.feature",
				//dryRun=true,
				//tags = "@valid1",
	    glue = {"StepDefinitions","Utilities"},
	    		plugin = {
	    				"pretty",
	    		        "html:target/cucumber-reports.html",
	    		        "json:target/cucumber.json",
	    		        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
	    		    },

	    monochrome = true
	)


public class Runners extends AbstractTestNGCucumberTests {

    // Enables parallel execution of scenarios
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}