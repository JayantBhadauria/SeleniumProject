package AbstractClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import MainModules.HomePage;
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
	public List<WebElement> getDropdownElements() {
		String xpath="//div[@class='cdk-overlay-container']/div[2]/div/div/div/mat-option";
		List<WebElement> elements=driver.findElements(By.xpath(xpath));
		return elements;
	}
	
	public void sleep(int time) throws InterruptedException {
		Thread.sleep(time);
	}
	
	public MyPortfolio goToMyportfolioPage() {
		driver.findElement(By.id("mat-button-toggle-2-button")).click();
		log.info("My portfolio page opened");
		return new MyPortfolio(driver);
	}
	
	public HomePage goToHomePage() {
		driver.findElement(By.id("mat-button-toggle-1-button")).click();
		log.info("Home Page opened");
		return  new HomePage(driver);
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
	
	public boolean isElementPresent(By locator) {
		try{
			WebElement element = driver.findElement(locator);
            return element.isDisplayed();
		}catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
			return false;
		}
	}

	public void OpenNotificationWidget(){
		WebElement notificationButton=driver.findElement(By.xpath("//mat-toolbar/div[2]/mat-icon"));
		notificationButton.click();
		log.info("Notification widget opened");
	}

	public void OpenIndexWatch(){
		WebElement indexWatchButton=driver.findElement(By.xpath("//button/span[text()='Index Watch']"));
		indexWatchButton.click();
		log.info("Index watch opened" );
	}

	public void OpenCreateStrategyPage(){
		WebElement createStratButton=driver.findElement(By.xpath("//button/span[text()=' + Create Strategy ']"));
		createStratButton.click();
		log.info("Create strategy page opened");
	}
}
