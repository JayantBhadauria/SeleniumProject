package MainModules;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import AbstractClasses.AbstractClass;


public class HomePage extends AbstractClass{
		WebDriver driver;
		Logger log=(Logger) LogManager.getLogger(getClass());
		public HomePage(WebDriver driver) {
			super(driver);
			this.driver=driver;
		}
		public WebElement findPortfolio(String portfolioName,String section) throws InterruptedException {
			Actions actions=new Actions(driver);
			List<WebElement>sectionsList=driver.findElements(By.xpath("//app-marketplace-cards"));
			WebElement portfolioCard=null;
			for(int i=0;i<sectionsList.size();i++) {
				String sectionName=sectionsList.get(i).findElement(By.xpath("./div/div/h2")).getText();
				if(sectionName.contains(section)) {
					WebElement inputfield=sectionsList.get(i).findElement(By.xpath(".//input"));
					actions.click(inputfield).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).sendKeys(portfolioName).build().perform();
					sleep(1000);
					portfolioCard=sectionsList.get(i).findElement(By.xpath(".//swiper/div/div"));
					break;
				}
			}
			return portfolioCard;
		}
		
		public void SubscribePortfolio(String portfolioName) throws InterruptedException {
			WebElement portfolioCard=findPortfolio(portfolioName,"Originals");
			if(portfolioCard!=null) {
				portfolioCard.findElement(By.xpath(".//app-strategy-card/div/mat-card/mat-card-actions/span/button")).click();
				driver.findElement(By.xpath("//app-confirmation-dialog/div/div[2]/div[2]/button")).click();
                String Response=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
				log.info(Response);
			}
			else {
				log.error(portfolioName+" not found");
			}
		}

		public void UnSubscribePortfolio(String portfolioName) throws InterruptedException{
			WebElement portfolioCard=findPortfolio(portfolioName,"Originals");
			if(portfolioCard!=null){
				portfolioCard.findElement(By.xpath(".//app-strategy-card/div[1]/mat-card[1]/div[1]/div[1]/div[2]/div/button[2]")).click();
				driver.findElement(By.xpath("//div[@class='cdk-overlay-container']/div[2]/div/div/div/button[1]")).click();
                driver.findElement(By.xpath("//app-confirmation-dialog/div/div[2]/div[2]/button")).click();
                String Response=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
                log.info(Response);
			}
			else{
				log.error(portfolioName + " not found");
			}
		}

		public void CopyPortfolio(String portfolioName,String Section) throws InterruptedException{
			WebElement portfolioCard=findPortfolio(portfolioName,Section);
			if(portfolioCard!=null){
				sleep(2000);
				portfolioCard.findElement(By.xpath(".//app-strategy-card/div[1]/mat-card[1]/div[1]/div[1]/div[2]/div/button[2]")).click();
                driver.findElement(By.xpath("//div[@class='cdk-overlay-container']/div[2]/div/div/div/button[3]")).click();
                String Response=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
                log.info(Response);
			}
			else{
				log.error(portfolioName + " not found");
			}
		}

		public PortfolioForm AddPortfolio() throws InterruptedException{
			driver.findElement(By.xpath("//app-strategy-board//span[text()=' Add a New Portfolio ']")).click();
			sleep(1500);
			PortfolioForm portfolioForm=new PortfolioForm(driver);
			return portfolioForm;
		}

		public void BookMarkPortfolio(String portfolioName, String section) throws IncompleteAnnotationException, InterruptedException {
			WebElement portfolioCard= findPortfolio(portfolioName,section);
			portfolioCard.findElement(By.xpath(".//app-strategy-card/div[1]/mat-card[1]/div[1]/div[1]/div[2]/div/button")).click();
			String Response=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
            log.info(Response);
			
		}

		public void UnBookmarkPortfolio(String portfolioName, String section) throws IncompleteAnnotationException, InterruptedException{
			BookMarkPortfolio(portfolioName, section);
		}

		public void deletePortfolio(String portfolioName) throws InterruptedException {
			WebElement portfolioCard=findPortfolio(portfolioName,"Created");
			if(portfolioCard!=null){
				sleep(2000);
				portfolioCard.findElement(By.xpath(".//app-strategy-card/div[1]/mat-card[1]/div[1]/div[1]/div[2]/div/button[2]")).click();
                driver.findElement(By.xpath("//div[@class='cdk-overlay-container']/div[2]/div/div/div/button[2]")).click();
				driver.findElement(By.xpath("//app-confirmation-dialog/div/div[2]/div[2]/button")).click();
                String Response=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
                log.info(Response);
			}
			else{
				log.error(portfolioName + " not found");
			}
		}
}
