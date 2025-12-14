package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class Hooks extends BaseClass {

    @Before
    public void setUp() {
        System.out.println("--Hook SetUp----");
     // Override the Spark report output path with a timestamped file
        String reportPath = System.getProperty("user.dir")
                + "/target/SparkReport/ExtentReport_" 
                + System.currentTimeMillis() + ".html";
        System.setProperty("extent.reporter.spark.out", reportPath);

        String browser = config.getProperty("browser");

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                BaseClass.driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                BaseClass.driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                BaseClass.driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    @After
    public void tearDown(Scenario scenario) throws IOException, InterruptedException {
        if (scenario.isFailed()) {
            String screenshotPath = System.getProperty("user.dir")
                    + "/target/SparkReport/screenshots/"
                    + scenario.getName() + ".png";

            File srcFile = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(screenshotPath));

            // Attach to Cucumber report
            final byte[] screenshotBytes = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "image");
           
           

            // Attach to Extent Spark report
            ExtentCucumberAdapter.addTestStepLog("Scenario failed: " + scenario.getName());
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(screenshotPath);
            ExtentCucumberAdapter.addTestStepLog("Navigated to Login page");
 

        }

        if (BaseClass.driver != null) {
        	Thread.sleep(1000);

            BaseClass.driver.quit();
        }// Auto-open the latest Spark report in default browser
        String reportPath = System.getProperty("extent.reporter.spark.out");
        if (reportPath != null) {
            File reportFile = new File(reportPath);
            if (reportFile.exists()) {
                java.awt.Desktop.getDesktop().browse(reportFile.toURI());
            }
        }

    }
}