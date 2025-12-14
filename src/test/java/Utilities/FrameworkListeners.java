package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import Base.BaseClass;

public class FrameworkListeners extends BaseClass implements ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		System.out.println("----------Onstart-------");

		String reportPath = System.getProperty("user.dir")
    + "/target/SparkReport/ExtentReport_" 
    + System.currentTimeMillis() + ".html";
System.setProperty("extent.reporter.spark.out", reportPath);
System.out.println("Global setup: Report path set to " + reportPath);
}

	
	
	@Override
	public void onFinish(ISuite suite) {
		System.out.println("After on finish Suite");
        String reportPath = System.getProperty("extent.reporter.spark.out");
        if (reportPath != null) {
            File reportFile = new File(reportPath);
            if (reportFile.exists()) {
                try {
					Desktop.getDesktop().browse(reportFile.toURI());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println("Report opened: " + reportPath);
            }
        }
    }

}
