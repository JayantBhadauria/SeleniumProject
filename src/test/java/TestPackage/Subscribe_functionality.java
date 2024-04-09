package TestPackage;

import org.apache.logging.log4j.core.Logger;

import org.testng.annotations.*;
import MainModules.MyPortfolio;
import TestComponents.*;

public class Subscribe_functionality extends BaseTest{
	@Parameters("portfolioName")
	@Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="UnSubscribe portfolio")
	public void SubscribePortfolioFormFavourite(String portfolioName) throws InterruptedException {
		// TODO Auto-generated constructor stub
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		MyPortfolio myPortfolio = new MyPortfolio(driver);
		myPortfolio.goToMyportfolioPage();
		myPortfolio.SubscribePortfolioFormFavourite(portfolioName);
	}
	
	
	@Parameters("portfolioName")
	@Test(dependsOnMethods = { "Subscribe" },groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="UnSubscribe portfolio")
	public void unSubscribe(String portfolioName) throws InterruptedException {
		// TODO Auto-generated constructor stub
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		MyPortfolio myPortfolio = new MyPortfolio(driver);
		myPortfolio.goToMyportfolioPage();
		myPortfolio.UnSubscribePortfolio(portfolioName);
	}
}
