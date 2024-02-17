package TestPackage;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import MainModules.LoginPage;
import MainModules.MyPortfolio;
import MainModules.PortfolioForm;
import MainModules.StrategyForm;
import TestComponents.BaseTest;


public class BookMark_Functionality extends BaseTest {
	public String portfolioName=null;
	
	@Test(dataProvider= "StrategyDetailsData")
	public void BookMark_testing(HashMap<String,String> input) throws InterruptedException, IOException {
		// uAlgos
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		PortfolioForm portfolioform=LoginPage.AddPortfolio();
		
		StrategyForm strategyform=portfolioform.addStrategy();
		strategyform.strategySetting(input.get("strategyType"), input.get("expiryType"), input.get("strikeSelection"), input.get("price1"), input.get("price2"));
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
	
//	@Test(dependsOnMethods = { "BookMark_testing" })
//	public void unBookMarking() throws IOException, InterruptedException {
//		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
//		MyPortfolio myportfolio= LoginPage.goToMyportfolioPage();	
//		Thread.sleep(2000);
//		myportfolio.BookMarkPortfolio(portfolioName);
//		Assert.assertFalse(myportfolio.findPortfolio(portfolioName, "BookMark"));
//	}
	

}
