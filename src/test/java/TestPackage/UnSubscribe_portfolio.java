package TestPackage;

import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.Test;
import MainModules.MyPortfolio;
import TestComponents.*;

public class UnSubscribe_portfolio extends BaseTest{
	
	@Test(groups = "Smoke",retryAnalyzer=RetryAnalyzer.class, testName="UnSubscribe portfolio")
	public void unSubscribe() throws InterruptedException {
		// TODO Auto-generated constructor stub
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		MyPortfolio myPortfolio = new MyPortfolio(driver);
		myPortfolio.goToMyportfolioPage();
		myPortfolio.UnSubscribePortfolio("Brahmastra");
	}
}
