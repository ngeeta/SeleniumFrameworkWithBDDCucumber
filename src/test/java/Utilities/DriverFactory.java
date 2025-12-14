package Utilities;

import org.openqa.selenium.WebDriver;

import Base.BaseClass;

public class DriverFactory extends BaseClass{
	public static WebDriver getDriver() {
        return BaseClass.getDriver();
    }

    public static void setDriver(WebDriver driverInstance) {
        BaseClass.setDriver(driverInstance);
    }

    public static void cleanupDriver() {
        BaseClass.cleanupDriver();
    }

}
