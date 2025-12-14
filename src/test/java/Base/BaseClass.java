package Base;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utilities.ConfigReader;
import Utilities.WaitUtility;

public class BaseClass {

	//public static WebDriver driver;
	// ThreadLocal driver for thread safety
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void cleanupDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

	public static WaitUtility waitUtility;
	public static ExtentSparkReporter extentSparkReporter;
	public String browser;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public ConfigReader config=new ConfigReader();
	public String report=System.getProperty("user.dir")+"/ExtentReport/report.html";
	public String screenShot=System.getProperty("user.dir")+"/ScreenShot/ss.png";

	
}
