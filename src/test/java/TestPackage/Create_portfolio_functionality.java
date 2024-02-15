package TestPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import MainModules.LoginPage;
import MainModules.MyPortfolio;
import MainModules.PortfolioForm;
import MainModules.StrategyForm;
import TestComponents.BaseTest;

public class Create_portfolio_functionality extends BaseTest{
	@Test(groups = "Smoke")
	public void CreatePortfolioTest() throws IOException, InterruptedException {
		
		// uAlgos
		
		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
		PortfolioForm portfolioform=LoginPage.AddPortfolio();
		
		StrategyForm strategyform=portfolioform.addStrategy();
		strategyform.strategySetting("Bear Call Spread", "Monthly", "Price", "100", "150");
		strategyform.SubmitStrategy();	
		
		String []tagsList= {"Profitable","Strategy","uTrade"};
		String []executionDaysList= {"Mon","Tue"};
		portfolioform.ChangePortfolioDetails("PF1", "11", "10", "2", "30",tagsList,executionDaysList);
		portfolioform.SubmitPortfolioForm();
							
		// Check if it is in Created Section 
		String toasterText=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
		String[] wordsArray = toasterText.split(" ");
		String portfolioName=wordsArray[0].toString();
		MyPortfolio myportfolio=new MyPortfolio(driver);
		myportfolio.goToMyportfolioPage();
		Assert.assertTrue("Portfolio not Saved", myportfolio.findPortfolio(portfolioName,"Created"));
	}

}
