package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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

        // Override Spark report output path with timestamped file
        String reportPath = System.getProperty("user.dir")
                + "/target/SparkReport/ExtentReport_" 
                + System.currentTimeMillis() + ".html";
        System.setProperty("extent.reporter.spark.out", reportPath);

        String browser = config.getProperty("browser");
        WebDriver driverInstance;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driverInstance = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driverInstance = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverInstance = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driverInstance.manage().window().maximize();
        DriverFactory.setDriver(driverInstance); // ThreadLocal set
    }

    @After
    public void tearDown(Scenario scenario) throws IOException, InterruptedException {
        WebDriver driver = DriverFactory.getDriver();

        if (scenario.isFailed() && driver != null) {
            String screenshotPath = System.getProperty("user.dir")
                    + "/target/SparkReport/screenshots/"
                    + scenario.getName().replaceAll(" ", "_") + ".png";

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(screenshotPath));

            // Attach to Cucumber report
            final byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");

            // Attach to Extent Spark report
            ExtentCucumberAdapter.addTestStepLog("Scenario failed: " + scenario.getName());
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(screenshotPath);
        }

        if (driver != null) {
            Thread.sleep(1000);
            DriverFactory.cleanupDriver(); // ThreadLocal cleanup
        }

        
    }
}
