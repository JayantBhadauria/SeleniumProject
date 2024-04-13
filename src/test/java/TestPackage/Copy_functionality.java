package TestPackage;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import MainModules.HomePage;
import MainModules.MyPortfolio;
import TestComponents.BaseTest;
import TestComponents.RetryAnalyzer;

public class Copy_functionality extends BaseTest{
	// @Parameters("portfolioName1")
	// @Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="Copy portfolio MyPortfolio")
	
	// public void createCopy(String portfolioName) throws InterruptedException {
	// 	// TODO Auto-generated method stub
	// 	LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
	// 	MyPortfolio myPortfolio = new MyPortfolio(driver);
	// 	myPortfolio.goToMyportfolioPage();
	// 	myPortfolio.bookmarkedTab();
	// 	myPortfolio.CopyPortfolio(portfolioName);
	// }

	@Parameters("portfolioName2")
	@Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="Copy portfolio Home")
	public void createCopyFromHomePage(String portfolioName) throws InterruptedException {
		// TODO Auto-generated method stub
		LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
		HomePage home=LoginPage.getHomePage();
		home.CopyPortfolio(portfolioName, "Created");
	}

	
}
