package MainModules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import AbstractClasses.AbstractClass;

public class PortfolioForm extends AbstractClass{
	
	WebDriver driver;
	public PortfolioForm(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}
	
	public StrategyForm addStrategy() throws InterruptedException {
		driver.findElement(By.xpath("// div[@role='button' and @tabindex='0']")).click();
		return new StrategyForm(driver);
	}
	
	public void SubmitPortfolioForm() throws InterruptedException {
		sleep(2000);
		driver.findElement(By.xpath("//button/span[text()=' Save & Review Portfolio ']")).click();
		sleep(2000);
	}
	
	public void ChangePortfolioDetails(String portfolioName,String startHour,String startMin, String endHour, String endMin,String []tagsList,String[]days) throws InterruptedException {
		ChangePortfolioName(portfolioName);
		ChangePortfolioStartTime(startHour,startMin);
		ChangePortfolioEndTime(endHour,endMin);
		AddPortfolioTags(tagsList);
		ExecutionDaySelection(days);
	}
	
	public void ChangePortfolioName(String name) {
		driver.findElement(By.xpath("//input[@id='mat-input-8']")).sendKeys(name);
	}
	
	public void ChangePortfolioStartTime(String hour,String min) throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='display-flex margin-10-top']/div[1]/div[2]/mat-form-field/div/div/div[4]/mat-icon")).click();
		sleep(2000);
		Actions action=new Actions(driver);
		WebElement StartHour=driver.findElement(By.xpath("//owl-date-time-timer-box/label[1]/input"));
		action.click(StartHour).keyDown(Keys.CONTROL).keyDown("A").keyUp(Keys.CONTROL).keyUp("A").keyDown(Keys.BACK_SPACE).sendKeys(hour).build().perform();
	
		WebElement StartMin=driver.findElement(By.xpath("//owl-date-time-timer-box[2]/label[1]/input"));
		action.click(StartMin).keyDown(Keys.CONTROL).keyDown("A").keyUp(Keys.CONTROL).keyUp("A").keyDown(Keys.BACK_SPACE).sendKeys(min).build().perform();
		sleep(2000);
		driver.findElement(By.xpath("//owl-date-time-container/div/div/button[2]/span[text()='Set']")).click();
	}
	
	public void ChangePortfolioEndTime(String hour,String min) throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='display-flex margin-10-top']/div[2]/div[2]/mat-form-field/div/div/div[4]/mat-icon")).click();
		sleep(2000);
		Actions action=new Actions(driver);
		WebElement StartHour=driver.findElement(By.xpath("//owl-date-time-timer-box/label[1]/input"));
		action.click(StartHour).keyDown(Keys.CONTROL).keyDown("A").keyUp(Keys.CONTROL).keyUp("A").keyDown(Keys.BACK_SPACE).sendKeys(hour).build().perform();
	
		WebElement StartMin=driver.findElement(By.xpath("//owl-date-time-timer-box[2]/label[1]/input"));
		action.click(StartMin).keyDown(Keys.CONTROL).keyDown("A").keyUp(Keys.CONTROL).keyUp("A").keyDown(Keys.BACK_SPACE).sendKeys(min).build().perform();
		sleep(2000);
		driver.findElement(By.xpath("//owl-date-time-container/div/div/button[2]/span[text()='Set']")).click();
	}
	
	public void AddPortfolioTags(String []tagsList) {
		List<String>tags=new ArrayList<String>();
		tags.addAll(Arrays.asList(tagsList));
		WebElement tagElement=driver.findElement(By.xpath("// input[@formcontrolname='tags']"));
		Actions action=new Actions(driver);
		for(int i=0;i<tags.size();i++) {
			action.click(tagElement).sendKeys(tags.get(i)).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
		}
	}
	
	public void ExecutionDaySelection(String[] days) throws InterruptedException {
		List<String>executionDays=new ArrayList<String>();
		executionDays.addAll(Arrays.asList(days));
		List<WebElement>DaysElementList=driver.findElements(By.xpath("//mat-selection-list[@formcontrolname='executeDays']/mat-list-option"));
		for(int i=0;i<DaysElementList.size();i++) {
			if(i==0) {
				DaysElementList.get(i).findElement(By.xpath("./div/mat-pseudo-checkbox")).click();
				sleep(2000);
			}
			else {
				for(int j=0;j<executionDays.size();j++) {
					String DayElement=DaysElementList.get(i).findElement(By.xpath("./div/div[@class='mat-list-text']")).getText();
					if(DayElement.contains(executionDays.get(j))) {
						DaysElementList.get(i).findElement(By.xpath("./div/mat-pseudo-checkbox")).click();
						sleep(1000);
						break;
					}
				}
			}
			
		}
	}

}
