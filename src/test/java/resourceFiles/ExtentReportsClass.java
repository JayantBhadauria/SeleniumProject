package resourceFiles;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.ExtentReports;

public class ExtentReportsClass {
    public ExtentReports getObjectReport() {
        String path = System.getProperty("user.dir") + "//reports//index.html";
        
        // Create an ExtentSparkReporter and attach it to the ExtentReports instance
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Additional configurations (if needed)
        reporter.config().setDocumentTitle("uAlgos Test Report");
        reporter.config().setReportName("Test Automation Result ");
        
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Jayant Bhadauria");

        return extent;
    }
}
