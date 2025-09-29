package com.utility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.base.Home_Base;

public class Home_Utility extends Home_Base {
	
	public static WebDriver driver;
	public static com.aventstack.extentreports.ExtentReports extent;
	
	public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/E_Reports/ExtentReport3.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Extent Report Example");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Anita Raj");
            extent.setSystemInfo("Environment", "QA Automation Tester");
        }
        return extent;
    }

}
