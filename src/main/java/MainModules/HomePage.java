package MainModules;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import AbstractClasses.AbstractClass;
import freemarker.log.Logger;

public class HomePage extends AbstractClass{
		Logger log= (Logger)LogManager.getLogger(HomePage.class);
		WebDriver driver;
		public HomePage(WebDriver driver) {
			super(driver);
			this.driver=driver;
		}
		

		public WebElement findPortfolio(String portfolioName,String section) {
			Actions actions=new Actions(driver);
			List<WebElement>sectionsList=driver.findElements(By.xpath("//app-marketplace-cards"));
			WebElement portfolioCard=null;
			for(int i=0;i<sectionsList.size();i++) {
				String sectionName=sectionsList.get(i).findElement(By.xpath("./div/div/h2")).getText();
				System.out.println(sectionName);
				if(sectionName.contains(section)) {
					WebElement inputfield=sectionsList.get(i).findElement(By.xpath(".//input"));
					actions.click(inputfield).sendKeys(portfolioName).build().perform();
					portfolioCard=sectionsList.get(i).findElement(By.xpath(".//swiper/div/div"));
					break;
				}
			}
			return portfolioCard;
		}
		
		public void SubscribePortfolio(String portfolioName) {
			WebElement portfolioCard=findPortfolio(portfolioName,"Originals");
			if(portfolioCard!=null) {
				portfolioCard.findElement(By.xpath(".//app-strategy-card/div/mat-card/mat-card-actions/span/button")).click();
				driver.findElement(By.xpath("//app-confirmation-dialog/div/div[2]/div[2]/button")).click();
                String Response=driver.findElement(By.xpath("//div[@id='toast-container']/app-custom-toaster/div/div/div/div")).getText();
				log.info(Response);
			}
			else {
				log.error(portfolioName+" Not found");
			}
		}

}
