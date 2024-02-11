package AbstractClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import MainModules.MyPortfolio;

public class AbstractClass {

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
		return new MyPortfolio(driver);
	}
	
	public void goToHomePage() {
		driver.findElement(By.id("mat-button-toggle-1-button")).click();
	}
	
	public void goToBooksPage() {
		driver.findElement(By.id("mat-button-toggle-3-button")).click();
	}
	
	public void goToPayoffcurvePage() {
		driver.findElement(By.id("mat-button-toggle-4-button")).click();
	}
	
	public void goToMargincalculatorPage() {
		driver.findElement(By.id("mat-button-toggle-5-button")).click();
	}


}
