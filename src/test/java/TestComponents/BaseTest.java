package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
	public static ExtentReports extent;
	Logger log=(Logger) LogManager.getLogger(BaseTest.class);

		public WebDriver initialization() throws IOException {
			
			
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//Gobaldata.properties");
			prop.load(fis);
			
			String browser=prop.getProperty("browserName");
			if(browser.contains("Google")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
//		        options.addArguments("--headless");
				this.driver=new ChromeDriver(options);
				log.info("ChromeDriver initialized");
			}
			else if(browser.contains("Edge")) {
				WebDriverManager.edgedriver().setup();
				EdgeOptions options=new EdgeOptions();
				options.addArguments("--headless");
				this.driver=new EdgeDriver(options);
				log.info("EdgeDriver initialized");
			}
			else if(browser.contains("FireFox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options=new FirefoxOptions();
				options.addArguments("--headless");
				this.driver=new FirefoxDriver(options);
				log.info("FirefoxDriver initialized");
			}
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
			log.info("Login page opened");
		}
		
		public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
			ss=(TakesScreenshot)driver;
			File source=ss.getScreenshotAs(OutputType.FILE);
			File outputFile=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
			FileUtils.copyFile(source,outputFile );
			log.info("Screenshot taken");
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
		public void InitiateReport() throws IOException {
			ExtentReportsClass extentReport=new ExtentReportsClass();
			ExtentReports extent=extentReport.getObjectReport();
			this.extent=extent;
			log.info("Report Started");
		}
		@AfterSuite
		public void flushReport() {
		    extent.flush();
		    log.info("Report Ended");
		    log.info("Program completed");
		}
		
	
}
