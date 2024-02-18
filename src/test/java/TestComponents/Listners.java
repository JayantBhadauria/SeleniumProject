package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resourceFiles.ExtentReportsClass;

public class Listners extends BaseTest implements ITestListener{

	public ExtentTest test;
	public WebDriver driver;
	public ExtentReports extent;
	String category,Author,Description;	
	
	@Override  
	public void onTestStart(ITestResult result) {  
	// TODO Auto-generated method stub 
		test= extent.createTest(result.getMethod().getMethodName(), Description);
		test.assignCategory(category);
		test.assignDevice(System.getenv("COMPUTERNAME"));
		test.assignAuthor(Author);
	}  
	  
	@Override  
	public void onTestSuccess(ITestResult result) {  
	// TODO Auto-generated method stub  
		test.log(Status.PASS, "Test Passed");
	}  
	  
	@Override  
	public void onTestFailure(ITestResult result) {  
	// TODO Auto-generated method stub  
		try {
			 driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test.addScreenCaptureFromPath(getScreenShot(result.getMethod().getMethodName(), driver));
		} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.FAIL, result.getThrowable());
	}  
	  
	@Override  
	public void onTestSkipped(ITestResult result) {  
	// TODO Auto-generated method stub  
	System.out.println("Skip of test cases and its details are : "+result.getName()); 
	test.log(Status.SKIP, "Test Skipped");
	}  
	  
	  
	@Override  
	public void onStart(ITestContext context) {  
	// TODO Auto-generated method stub  
		category=context.getCurrentXmlTest().getName();
		Author=context.getCurrentXmlTest().getParameter("Author");
		extent=BaseTest.extent;
		Description=context.getCurrentXmlTest().getParameter("Description");
	}  
	  
	
}
