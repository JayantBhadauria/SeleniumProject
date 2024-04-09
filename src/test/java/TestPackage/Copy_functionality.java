package TestPackage;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import MainModules.MyPortfolio;
import TestComponents.BaseTest;
import TestComponents.RetryAnalyzer;

public class Copy_functionality extends BaseTest{
	@Parameters("portfolioName")
	@Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="Copy portfolio")
	
	public void createCopy(String portfolioName) throws InterruptedException {
		// TODO Auto-generated method stub
		LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
		MyPortfolio myPortfolio = new MyPortfolio(driver);
		myPortfolio.goToMyportfolioPage();
		myPortfolio.bookmarkedTab();
		myPortfolio.CopyPortfolio(portfolioName);
	}
}
