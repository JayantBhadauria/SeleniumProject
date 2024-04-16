package TestPackage;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import MainModules.HomePage;
import MainModules.MyPortfolio;
import MainModules.*;
import TestComponents.BaseTest;
import TestComponents.RetryAnalyzer;

public class Copy_functionality extends BaseTest{
	public String portfolioName=null;

	@Parameters("portfolioName")
	@Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="Copy portfolio from Originals section in home tab")
	public void createCopyfromOriginals(String portfolioName) throws InterruptedException {
		LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
		HomePage homePage=LoginPage.goToHomePage();
		homePage.CopyPortfolio(portfolioName, "Originals");
	}

	@Parameters("portfolioName")
	@Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="Copy portfolio from Subscribed section in home tab")
	public void createCopyfromSubscribed(String portfolioName) throws InterruptedException{
		LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
		HomePage homePage=LoginPage.goToHomePage();
		homePage.CopyPortfolio(portfolioName, "Subscribed");
	}

	@Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class,dataProvider= "StrategyDetailsData", testName="Copy portfolio from Created tab of Myportfolio")
	public void createCopyfromCreatedMyPortfolio(LinkedHashMap<String,String> input) throws InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		PortfolioForm portfolioform=LoginPage.AddPortfolio();
		StrategyForm strategyform=portfolioform.addStrategy();
		SetFormParams(input,strategyform,portfolioform);
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
		myportfolio.createdTab();
		Thread.sleep(2500);
		myportfolio.CopyPortfolio(portfolioName);
	}

	@Test(dependsOnMethods = {"createCopyfromCreatedMyPortfolio"},groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="Copy portfolio from Created section in home tab")
	public void createCopyfromCreatedHome(String portfolioName) throws InterruptedException{
		LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
		HomePage homePage=LoginPage.goToHomePage();
		homePage.CopyPortfolio(portfolioName, "Created");
	}


}
