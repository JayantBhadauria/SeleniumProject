package TestPackage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.io.IOException;
import java.lang.annotation.IncompleteAnnotationException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import MainModules.HomePage;
import TestComponents.*;


@Listeners(Listners.class)
public class BookMark_Functionality extends BaseTest {
	public String portfolioName=null;
	Logger log=(Logger) LogManager.getLogger(getClass());
	// @Test(testName="Bookmark Case", dataProvider= "StrategyDetailsData", retryAnalyzer=RetryAnalyzer.class)
	// public void BookMark_testing(LinkedHashMap<String,String> input) throws InterruptedException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	// 	// uAlgos
	// 	LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
	// 	PortfolioForm portfolioform=LoginPage.AddPortfolio();
	// 	StrategyForm strategyform=portfolioform.addStrategy();
	// 	SetFormParams(input,strategyform,portfolioform);
	// 	strategyform.SubmitStrategy();	
	// 	String []tagsList= {"Profitable","Strategy","uTrade"};
	// 	String []executionDaysList= {"Mon","Tue"};
	// 	portfolioform.ChangePortfolioDetails("PF1", "11", "10", "2", "30",tagsList,executionDaysList);
	// 	portfolioform.SubmitPortfolioForm();
		
	// 	String toasterText=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
	// 	String[] wordsArray = toasterText.split(" ");
	// 	portfolioName=wordsArray[0].toString();
	// 	MyPortfolio myportfolio=new MyPortfolio(driver);	
	// 	myportfolio.goToMyportfolioPage();
	// 	myportfolio.BookMarkPortfolio(portfolioName);
	// 	Assert.assertTrue(myportfolio.findPortfolio(portfolioName, "BookMark"));	
	// }
	
//	@Test(dependsOnMethods = { "BookMark_testing" },testName="UnBookmark case", retryAnalyzer=RetryAnalyzer.class)
//	public void unBookMarking() throws IOException, InterruptedException {
//		LoginPage.LoginApplication(prop.getProperty("username"),prop.getProperty("password"));
//		MyPortfolio myportfolio= LoginPage.goToMyportfolioPage();	
//		Thread.sleep(2000);
//		myportfolio.BookMarkPortfolio(portfolioName);
//		Assert.assertFalse(myportfolio.findPortfolio(portfolioName, "BookMark"));
//	}

@Parameters({"portfolioName"})
@Test(testName = "Bookmark from home page", retryAnalyzer =  RetryAnalyzer.class)
public void bookmarkFromHomePage(String portfolioName) throws IOException, IncompleteAnnotationException, InterruptedException{
	LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
	HomePage homepage = new HomePage(driver);
	homepage.BookMarkPortfolio(portfolioName, "Subscribed");
	
}

}
