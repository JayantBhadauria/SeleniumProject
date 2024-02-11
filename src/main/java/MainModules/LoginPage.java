package MainModules;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import AbstractClasses.AbstractClass;

public class LoginPage extends AbstractClass{
		WebDriver driver;
		public LoginPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
		}
		
		public void GoTo(String url) throws InterruptedException {
			driver.get(url);
			sleep(2000);
		}
	
		public void LoginApplication(String username, String password) {
			driver.findElement(By.xpath("// div[@class='social-logins ng-star-inserted']/button[@tabindex='2']")).click();
			driver.findElement(By.xpath("// input[@tabindex='3']")).sendKeys(username);
			driver.findElement(By.id("mat-input-1")).sendKeys(password);
			driver.findElement(By.xpath("// button[@tabindex='7']")).click();
			WaitImplicit(10);
			driver.findElement(By.xpath("// div[@class='flex-centered-container-hr']/button")).click();
		}
		
		public PortfolioForm AddPortfolio() throws InterruptedException {
			goToMyportfolioPage();
			sleep(2000);
			driver.findElement(By.xpath("// button[@color='accent' and @type='button']")).click();
			return new PortfolioForm(driver);
			
		}

}
