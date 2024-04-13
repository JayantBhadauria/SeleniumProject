package TestPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import MainModules.HomePage;
import MainModules.LoginPage;
import MainModules.MyPortfolio;
import MainModules.PortfolioForm;
import MainModules.StrategyForm;
import TestComponents.BaseTest;
import TestComponents.RetryAnalyzer;

public class Create_portfolio_functionality extends BaseTest{
	// @Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="Create portfolio")
	// public void CreatePortfolioTest() throws IOException, InterruptedException {
		
	// 	// uAlgos
	// 	Logger log=(Logger) LogManager.getLogger(getClass());
	// 	LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
	// 	PortfolioForm portfolioform=LoginPage.AddPortfolio();
		
	// 	StrategyForm strategyform=portfolioform.addStrategy();
	// 	strategyform.strategySetting("Bear Call Spread", "Monthly", "Price", "100", "150");
	// 	strategyform.SubmitStrategy();
	// 	String []tagsList= {"Profitable","Strategy","uTrade"};
	// 	String []executionDaysList= {"Mon","Tue"};
	// 	portfolioform.ChangePortfolioDetails("PF1", "11", "10", "2", "30",tagsList,executionDaysList);
	// 	portfolioform.SubmitPortfolioForm();
		
	// 	// Check if it is in Created Section 
	// 	String toasterText=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
	// 	String[] wordsArray = toasterText.split(" ");
	// 	String portfolioName=wordsArray[0].toString();
	// 	MyPortfolio myportfolio=new MyPortfolio(driver);
	// 	myportfolio.goToMyportfolioPage();
	// 	if(myportfolio.findPortfolio(portfolioName, "Created")) {
	// 		Assert.assertTrue(myportfolio.findPortfolio(portfolioName, "Created"));	
	// 		log.info("Portfolio sucessfully Saved");
	// 	}
	// }

	@Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="Create portfolio")
	public void CreatePortfolioWithoutTags() throws InterruptedException{
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty( "password"));
		HomePage homepage=LoginPage.goToHomePage();
		homepage.AddPortfolio();
	}

}
