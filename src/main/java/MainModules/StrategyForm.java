package MainModules;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import AbstractClasses.AbstractClass;

public class StrategyForm extends AbstractClass{
	WebDriver driver;
	public StrategyForm(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	
	
	public void strategySetting(String strategyType, String expiryType, String strikeType, String price1, String price2) {
		StrategyType(strategyType);
		ExpiryType(expiryType);
		StrikeType(strikeType);
		PriceEntry(price1,price2);
	}
	
	public void StrategyType(String strategyType) {
		// Strategy Type
		driver.findElement(By.xpath("//mat-select[@id='mat-select-5']")).click();
		List<WebElement> stgTyp= driver.findElements(By.xpath("// div[@id='mat-select-5-panel']/mat-option"));
		for(int i=0;i<stgTyp.size();i++) {
			String stgName=stgTyp.get(i).findElement(By.xpath("./span")).getText();
			if(stgName.contains(strategyType)) {
				stgTyp.get(i).findElement(By.xpath("./span")).click();
				break;
			}
		}
	}
	
	public void ExpiryType(String expiryType) {
		// Expiry type 
		driver.findElement(By.xpath("// mat-select[@id='mat-select-8']")).click();
		List<WebElement> expTyp= driver.findElements(By.xpath("// div[@id='mat-select-8-panel']/mat-option"));
		for(int i=0;i<expTyp.size();i++) {
			String expName=expTyp.get(i).findElement(By.xpath("./span")).getText();
			if(expName.contains(expiryType)) {
				expTyp.get(i).findElement(By.xpath("./span")).click();
					break;
				}
		}
	}
	
	public void StrikeType(String strikeType) {
		// Strike type 
		driver.findElement(By.xpath("// mat-select[@id='mat-select-10']")).click();
		List<WebElement> strikeTyp= driver.findElements(By.xpath("// div[@id='mat-select-10-panel']/mat-option"));
		for(int i=0;i<strikeTyp.size();i++) {
			String strikeName=strikeTyp.get(i).findElement(By.xpath("./span")).getText();
			if(strikeName.contains(strikeType)) {
				strikeTyp.get(i).findElement(By.xpath("./span")).click();
				break;
			}
		}
	}
	
	public void PriceEntry(String price1,String price2) {
		// Price Entry
		driver.findElement(By.id("mat-input-28")).sendKeys(price1);
		driver.findElement(By.id("mat-input-29")).sendKeys(price2);
	}
	
	public void SubmitStrategy() {
		driver.findElement(By.xpath("//button[@class='mat-focus-indicator ut-pro-button gradient margin-left margin-right mat-flat-button mat-button-base mat-accent ng-star-inserted']")).click();
	}
	
	
	
	
}
