package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import MainModules.PortfolioForm;
import MainModules.StrategyForm;
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
			
			String browser=System.getProperty("browser")!=null?System.getProperty("browser"): prop.getProperty("browserName");
			if(browser.contains("Chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				if(browser.contains("headless")) {
					options.addArguments("--headless");
				}
				this.driver=new ChromeDriver(options);
				log.info("ChromeDriver initialized");
			}
			else if(browser.contains("Edge")) {
				WebDriverManager.edgedriver().setup();
				EdgeOptions options=new EdgeOptions();
				if(browser.contains("headless")) {
					options.addArguments("--headless");
				}
				this.driver=new EdgeDriver(options);
				log.info("EdgeDriver initialized");
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
		    log.info("Program completed");
		}
		
		@DataProvider()
		public Object[][] StrategyDetailsData() throws IOException{	
			List<LinkedHashMap<String,String>> maps=getJSONDataToMap();
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
		}
		
		public void SetFormParams(LinkedHashMap<String,String> input,StrategyForm strategyform, PortfolioForm portfolioform) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			Iterator<Map.Entry<String, String>> iterator = input.entrySet().iterator();
			Map<Integer,Boolean>legStatus=new HashMap<>();
	        for (int i = 1; i <= 5; i++) {
	            legStatus.put(i, false);
	        }
			while (iterator.hasNext()) {
		            Map.Entry<String, String> entry = iterator.next();
		            String key = entry.getKey();
		            String value = entry.getValue();
		            if(key.contains("leg")) {
		            	char legNumber=key.charAt(key.length()-1);
		            	if(!legStatus.get(Integer.valueOf(legNumber)-48)) {
		            		strategyform.enableLegSettings(legNumber);
		            		legStatus.put(Integer.valueOf(legNumber)-48, true);
		            	}
		            	if(key.contains("ExitRange")) {
		            		strategyform.enableRange(value, legNumber);
		            		
		            	}
		            	else {
		            		strategyform.legField(key, value);
		            	}
		            		
		            }
		          
		            else if(key.contains("portfolio")) {
		            	portfolioform.portfolioField(key,value);
		            }
		            else{
		            	strategyform.strategyField(key, value);
		            }
		     }
		}
		
}
