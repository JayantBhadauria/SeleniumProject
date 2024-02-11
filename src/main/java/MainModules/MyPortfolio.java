package MainModules;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import AbstractClasses.AbstractClass;

public class MyPortfolio extends AbstractClass {
	WebDriver driver;
	public MyPortfolio(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	public void MyPortfolioPage() {
		goToMyportfolioPage();
	}
	
	public void deployedTab() {
		driver.findElement(By.xpath("// div[@class='mat-tab-labels']/div[@aria-setsize=4][1]")).click();
	}
	
	public void createdTab() {
		driver.findElement(By.xpath("// div[@class='mat-tab-labels']/div[@aria-setsize=4][2]")).click();
	}
	
	public void subscribedTab() {
		driver.findElement(By.xpath("// div[@class='mat-tab-labels']/div[@aria-setsize=4][3]")).click();
	}
	
	public void bookmarkedTab() {
		driver.findElement(By.xpath("// div[@class='mat-tab-labels']/div[@aria-setsize=4][4]")).click();
	}
	
	public List<WebElement> getListofPortfolios() {
		List<WebElement> elements =driver.findElements(By.xpath("//div[@class='card-section margin-8-left']/div"));
		return elements;
	}
	
	public boolean findPortfolio(String portfolioName,String SectionName) throws InterruptedException {
		boolean returnValue=false;
		if(SectionName.equalsIgnoreCase("Created")) {
			createdTab();
			sleep(1500);
			List<WebElement>portfoliolist=getListofPortfolios();
			for(int i=0;i<portfoliolist.size();i++) {
				if(portfoliolist.get(i).findElement(By.xpath("./app-strategy-card[1]/div[1]/mat-card[1]/div[1]/div[1]/div[1]/mat-card-header[1]/div[1]/mat-card-title[1]")).getText().equalsIgnoreCase(portfolioName)){
					returnValue=true;
					break;
				}
			}
		}
		
		else if(SectionName.equalsIgnoreCase("Bookmark")) {
			bookmarkedTab();
			sleep(1500);
			List<WebElement>portfoliolist=getListofPortfolios();
			for(int i=0;i<portfoliolist.size();i++) {
				if(portfoliolist.get(i).findElement(By.xpath("./app-strategy-card[1]/div[1]/mat-card[1]/div[1]/div[1]/div[1]/mat-card-header[1]/div[1]/mat-card-title[1]")).getText().equalsIgnoreCase(portfolioName)){
					returnValue=true;
					break;
				}
			}
		}
		
		else if(SectionName.equalsIgnoreCase("Deployed")) {
			deployedTab();
			sleep(1500);
			List<WebElement>portfoliolist=getListofPortfolios();
			for(int i=0;i<portfoliolist.size();i++) {
				if(portfoliolist.get(i).findElement(By.xpath("./app-strategy-card[1]/div[1]/mat-card[1]/div[1]/div[1]/div[1]/mat-card-header[1]/div[1]/mat-card-title[1]")).getText().equalsIgnoreCase(portfolioName)){
					returnValue=true;
					break;
				}
			}
		}
		
		else {
			subscribedTab();
			sleep(1500);
			List<WebElement>portfoliolist=getListofPortfolios();
			for(int i=0;i<portfoliolist.size();i++) {
				if(portfoliolist.get(i).findElement(By.xpath("./app-strategy-card[1]/div[1]/mat-card[1]/div[1]/div[1]/div[1]/mat-card-header[1]/div[1]/mat-card-title[1]")).getText().equalsIgnoreCase(portfolioName)){
					returnValue=true;
					break;
				}
			}
		}
		return returnValue;
	}
	
	public void BookMarkPortfolio(String portfolioName) throws InterruptedException {
		createdTab();
		sleep(1500);
		List<WebElement> elements=getListofPortfolios();
		for(int i=0;i<elements.size();i++) {
			if(elements.get(i).findElement(By.xpath("./app-strategy-card[1]/div[1]/mat-card[1]/div[1]/div[1]/div[1]/mat-card-header[1]/div[1]/mat-card-title[1]")).getText().equalsIgnoreCase(portfolioName)){
				elements.get(i).findElement(By.xpath("./app-strategy-card[1]/div[1]/mat-card[1]/div[1]/div[1]/div[2]/div/button")).click();
				break;
			}
		}
	}
	
	

}
