package TestPackage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;

import java.io.IOException;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import MainModules.*;
import TestComponents.*;


@Listeners(Listners.class)
public class BookMark_Functionality extends BaseTest {
	public String portfolioName=null;
	Logger log=(Logger) LogManager.getLogger(getClass());

	//To verify Bookmark functionality in Created section 
	@Test(testName="Bookmark Case", dataProvider= "StrategyDetailsData", retryAnalyzer=RetryAnalyzer.class)
	public void BookMark_testing(LinkedHashMap<String,String> input) throws InterruptedException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
		myportfolio.BookMarkPortfolio(portfolioName);
		Assert.assertTrue(myportfolio.findPortfolio(portfolioName, "BookMark"));	
	}
	
	//To verify UnBookmark functionality in Favourites section
	@Test(dependsOnMethods = { "BookMark_testing" },testName="UnBookmark case", retryAnalyzer=RetryAnalyzer.class)
	public void unBookMarking() throws IOException, InterruptedException {
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		MyPortfolio myportfolio= LoginPage.goToMyportfolioPage();	
		Thread.sleep(2000);
		myportfolio.BookMarkPortfolio(portfolioName);
		Assert.assertFalse(myportfolio.findPortfolio(portfolioName, "BookMark"));
	}

	// To verify bookmark functionality in Created Section of homepage
	@Test(testName = "Bookmark from home page", retryAnalyzer =  RetryAnalyzer.class)
	public void bookmarkFromHomePageCreated() throws IOException, IncompleteAnnotationException, InterruptedException{
		LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.BookMarkPortfolio(portfolioName, "Created");
		homepage.WaitImplicit(10);
		homepage.UnBookmarkPortfolio(portfolioName, "Created");
	}

	// To verify bookmark functionality in Subscribed Section of homepage
	@Parameters("portfolioName")
	@Test(testName="Bookmark case", retryAnalyzer=RetryAnalyzer.class)
	public void bookmarkFromHomePageSubscribed(String portfolioName) throws IOException, InterruptedException {
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		HomePage homePage=LoginPage.goToHomePage();
		Thread.sleep(2000);
		homePage.BookMarkPortfolio(portfolioName, "Subscribed");
		Thread.sleep(3000);
		homePage.UnBookmarkPortfolio(portfolioName, "Subscribed");
	}

	// To verify bookmark functionality in Originals Section of homepage
	@Parameters("portfolioName")
	@Test(testName="Bookmark case", retryAnalyzer=RetryAnalyzer.class)
	public void bookmarkFromHomePageOriginals(String portfolioName) throws IOException, InterruptedException {
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		HomePage homePage=LoginPage.goToHomePage();
		Thread.sleep(2000);
		homePage.BookMarkPortfolio(portfolioName, "Originals");
		Thread.sleep(3000);
		homePage.UnBookmarkPortfolio(portfolioName, "Originals");
	}

	@Parameters("portfolioName")
	@Test(testName="Bookmark case", retryAnalyzer=RetryAnalyzer.class, groups = "RunOnly")
	public void bookmarkFromMyPortfolio(String portfolioName) throws IOException, InterruptedException {
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		MyPortfolio portfolioForm=LoginPage.goToMyportfolioPage();
		By locator=By.xpath("");
		portfolioForm.WaitUntilElementVisible(5, );
		portfolioForm.bookmarkedTab();
		portfolioForm.WaitImplicit(5);
		portfolioForm.BookMarkPortfolio(portfolioName);
		PopUpMessage();
	}

	

}
