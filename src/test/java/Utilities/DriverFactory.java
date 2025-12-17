package Utilities;

import org.openqa.selenium.WebDriver;

import Base.BaseClass;

public class DriverFactory extends BaseClass{
	
	public static WebDriver getDriver() {
		System.out.println("--get Driver----");
        return BaseClass.getDriver();
    }

    public static void setDriver(WebDriver driverInstance) {
		System.out.println("--set Driver----");

        BaseClass.setDriver(driverInstance);
    }

    public static void cleanupDriver() {
		System.out.println("--cleanup  Driver----");

        BaseClass.cleanupDriver();
    }

}
