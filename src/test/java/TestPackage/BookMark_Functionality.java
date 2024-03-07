package TestPackage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import MainModules.LoginPage;
import MainModules.MyPortfolio;
import MainModules.PortfolioForm;
import MainModules.StrategyForm;
import TestComponents.BaseTest;
import TestComponents.Listners;
import TestComponents.RetryAnalyzer;

@Listeners(Listners.class)
public class BookMark_Functionality extends BaseTest {
	public String portfolioName=null;
	Logger log=(Logger) LogManager.getLogger(getClass());
	@Test(testName="Bookmark Case", dataProvider= "StrategyDetailsData", retryAnalyzer=RetryAnalyzer.class)
	public void BookMark_testing(LinkedHashMap<String,String> input) throws InterruptedException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// uAlgos
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		PortfolioForm portfolioform=LoginPage.AddPortfolio();
		StrategyForm strategyform=portfolioform.addStrategy();
		
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
	            		strategyform.EnablelegSettings(legNumber);
	            		legStatus.put(Integer.valueOf(legNumber)-48, true);
	            	}
	            	strategyform.legField(key, value);
	          
	            }
	            else {
	            	strategyform.strategyField(key, value);
	            }
	            
	                   
	     }
		 
		strategyform.priceEntry("100", "150");
		strategyform.SubmitStrategy();	
		String []tagsList= {"Profitable","Strategy","uTrade"};
		String []executionDaysList= {"Mon","Tue"};
		portfolioform.ChangePortfolioDetails("PF1", "11", "10", "2", "30",tagsList,executionDaysList);
		portfolioform.SubmitPortfolioForm();
		
		String toasterText=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
		String[] wordsArray = toasterText.split(" ");
		portfolioName=wordsArray[0].toString();
		MyPortfolio myportfolio=new MyPortfolio(driver);	
		myportfolio.goToMyportfolioPage();
		myportfolio.BookMarkPortfolio(portfolioName);
		Assert.assertTrue(myportfolio.findPortfolio(portfolioName, "BookMark"));	
	}
	
//	@Test(dependsOnMethods = { "BookMark_testing" },testName="UnBookmark case", retryAnalyzer=RetryAnalyzer.class)
//	public void unBookMarking() throws IOException, InterruptedException {
//		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
//		MyPortfolio myportfolio= LoginPage.goToMyportfolioPage();	
//		Thread.sleep(2000);
//		myportfolio.BookMarkPortfolio(portfolioName);
//		Assert.assertFalse(myportfolio.findPortfolio(portfolioName, "BookMark"));
//	}
	

}
