package AbstractClasses;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import MainModules.MyPortfolio;

public class AbstractClass {
	Logger log=(Logger) LogManager.getLogger(getClass());
	WebDriver driver;
	public AbstractClass(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	public void WaitImplicit(int time) {

		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
	}
	
	public void sleep(int time) throws InterruptedException {
		Thread.sleep(time);
	}
	
	public MyPortfolio goToMyportfolioPage() {
		driver.findElement(By.id("mat-button-toggle-2-button")).click();
		log.info("My portfolio page opened");
		return new MyPortfolio(driver);
	}
	
	public void goToHomePage() {
		driver.findElement(By.id("mat-button-toggle-1-button")).click();
		log.info("Home Page opened");
	}
	
	public void goToBooksPage() {
		driver.findElement(By.id("mat-button-toggle-3-button")).click();
		log.info("Books page opened");
	}
	
	public void goToPayoffcurvePage() {
		driver.findElement(By.id("mat-button-toggle-4-button")).click();
		log.info("Payoff page opened");
	}
	
	public void goToMargincalculatorPage() {
		driver.findElement(By.id("mat-button-toggle-5-button")).click();
		log.info("Margin Calculator opened");
	}


}
