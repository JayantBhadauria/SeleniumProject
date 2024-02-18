package resourceFiles;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;

public class ExtentReportsClass {
    public ExtentReports getObjectReport() throws IOException {
    	Properties prop=new Properties();
    	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//Gobaldata.properties");
    	prop.load(fis);
        String path = System.getProperty("user.dir") + "//reports//index.html";
        System.out.println("ExtentReportClass called");
        // Create an ExtentSparkReporter and attach it to the ExtentReports instance
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Additional configurations (if needed)
        reporter.config().setDocumentTitle("uAlgos Test Report");
        reporter.config().setReportName("Test Automation Result ");
        
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", prop.getProperty("tester"));
        return extent;
    }
}
