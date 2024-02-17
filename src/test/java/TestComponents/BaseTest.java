package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import MainModules.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import resourceFiles.DataReader;
import resourceFiles.ExtentReportsClass;

public class BaseTest extends DataReader{
	
	// TODO Auto-generated method stub
	public WebDriver driver;
	public LoginPage LoginPage;
	public TakesScreenshot ss;
	public Properties prop= new Properties();
	public ExtentReportsClass extentReport;
//	public ExtentReports extent;
	public static ExtentReports extent;

		public WebDriver initialization() throws IOException {
			
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//Gobaldata.properties");
			prop.load(fis);
			String browser=prop.getProperty("browserName");
			if(browser.contains("Google")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
//		        options.addArguments("--headless");
				this.driver=new ChromeDriver(options);
				
			}
			
			System.out.println("Welcome to uTradeAlgos");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			return driver;
		}
		
		@BeforeMethod()
		public void launchApplication() throws IOException, InterruptedException {
			this.driver=initialization();
			LoginPage=new LoginPage(driver);
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//Gobaldata.properties");
			prop.load(fis);
			LoginPage.GoTo(prop.getProperty("url"));
		}
		
		public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
			ss=(TakesScreenshot)driver;
			File source=ss.getScreenshotAs(OutputType.FILE);
			File outputFile=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
			FileUtils.copyFile(source,outputFile );
			return outputFile.getAbsolutePath();
		}
		
		@AfterMethod()
		public void closeDriver() {
			driver.quit();
		}
		
		@DataProvider()
		public Object[][] StrategyDetailsData() throws IOException{	
			List<HashMap<String,String>> maps=getJSONDataToMap();
			return new Object [][] {
					{maps.get(0)}
			}; 	
		}
		
		@BeforeSuite
		public void InitiateReport() {
			System.out.println("Before Asigning");
			ExtentReportsClass extentReport=new ExtentReportsClass();
			ExtentReports extent=extentReport.getObjectReport();
			this.extent=extent;
		}
		@AfterSuite
		public void flushReport() {
		    extent.flush();
		}
	
}
