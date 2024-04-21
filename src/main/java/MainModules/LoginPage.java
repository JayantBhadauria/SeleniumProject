package MainModules;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import AbstractClasses.AbstractClass;

public class LoginPage extends AbstractClass{
	Logger log=(Logger) LogManager.getLogger(getClass());
		WebDriver driver;
		public LoginPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
		}
		
		public void GoTo(String url) throws InterruptedException {
			driver.get(url);
		}
	
		public void LoginApplication(String username, String password) {
			driver.findElement(By.xpath("// div[@class='social-logins ng-star-inserted']/button[@tabindex='2']")).click();
			driver.findElement(By.xpath("// input[@tabindex='3']")).sendKeys(username);
			driver.findElement(By.id("mat-input-1")).sendKeys(password);
			driver.findElement(By.xpath("// button[@tabindex='7']")).click();
			By elementLocator = By.xpath("//div[@class='overlay-container']/div[@id='toast-container']/app-custom-toaster");
			if(isElementPresent(elementLocator)) {
				WebElement toastContainer=driver.findElement(By.xpath("//div[@class='overlay-container']/div[@id='toast-container']/app-custom-toaster"));
				String errorMsg=toastContainer.findElement(By.xpath("./div/div/div/div")).getText();
				log.error(errorMsg);	
			}
			else {
				log.info("User Logged In");
				driver.findElement(By.xpath("// div[@class='flex-centered-container-hr']/button")).click();
			}
		
		}
		
		public PortfolioForm AddPortfolio() throws InterruptedException {
			goToMyportfolioPage();
			driver.findElement(By.xpath("// button[@color='accent' and @type='button']")).click();
			By elementLocator=By.xpath("//div[text()=' Create Portfolio ']");
			
			if(isElementPresent(elementLocator)) {
				WebElement portfolioForm=driver.findElement(elementLocator);
				log.info("Portfolio form opened");
			}
			else {
				log.warn("Portfolio form not visible");
			}
			
			return new PortfolioForm(driver);
			
		}

}
