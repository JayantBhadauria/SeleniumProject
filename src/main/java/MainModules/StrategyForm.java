package MainModules;

import java.util.LinkedHashMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import AbstractClasses.AbstractClass;

public class StrategyForm extends AbstractClass{
	Logger log=(Logger) LogManager.getLogger(getClass());
	WebDriver driver;
	public StrategyForm(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public void strategySetting(String strategyType, String expiryType, String strikeType, String price1, String price2) {
		strategyType(strategyType);
		expiryType(expiryType);
		strikeSelection(strikeType);
		priceEntry(price1,price2);
	}
	
	
	public void strategyField(String fieldName, String fieldValue) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method=getClass().getMethod(fieldName, String.class);
		method.invoke(this, fieldValue);
	}
	
	public void legField(String fieldName, String fieldValue) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String updatedFieldName=fieldName.substring(0, fieldName.length()-1);
		System.out.println("FieldName : "+updatedFieldName);
		Method method=getClass().getMethod(updatedFieldName, String.class, char.class);
		char legNumber=fieldName.charAt(fieldName.length()-1);
		method.invoke(this, fieldValue,legNumber);
	}
	
	public void strategyType(String strategyType) {
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
	
	public void expiryType(String expiryType) {
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
	
	public void strikeSelection(String strikeType) {
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
	
	public void priceEntry(String price1,String price2) {
		// Price Entry
		driver.findElement(By.id("mat-input-28")).sendKeys(price1);
		driver.findElement(By.id("mat-input-29")).sendKeys(price2);
	}
	
	public void EnablelegSettings(char legNumber) {
		int legNum=legNumber;
		List<WebElement> togglebuttons=driver.findElements(By.xpath("//mat-slide-toggle[@formcontrolname='legSettings']"));
		WebElement togglebutton= togglebuttons.get(legNum-48-1);
		togglebutton.click();
	}
	
	public void SubmitStrategy() {
		driver.findElement(By.xpath("//button[@class='mat-focus-indicator ut-pro-button gradient margin-left margin-right mat-flat-button mat-button-base mat-accent ng-star-inserted']")).click();
		By elementLocator = By.xpath("//div[@class='overlay-container']/div[@id='toast-container']/app-custom-toaster");
		if(isElementPresent(elementLocator)) {
			WebElement toastContainer=driver.findElement(By.xpath("//div[@class='overlay-container']/div[@id='toast-container']/app-custom-toaster"));
			String errorMsg=toastContainer.findElement(By.xpath("./div/div/div/div")).getText();
			log.error(errorMsg);	
		}
		else {
			log.info("Strategy Added");
		}	
	}
	
	public void legTP(String tpValue,char legNumber) {
		enableTP(legNumber);
		String xpath ="//app-strategy-leg-form["+legNumber+"]/div/form/div/div[2]/div/form/div[2]/div/div[2]/mat-form-field[2]/div/div/div[3]/div/div/div/input";
		driver.findElement(By.xpath(xpath)).sendKeys(tpValue);
	}
	
	public void enableTP(char legNumber) {
		String xpath ="//app-strategy-leg-form["+legNumber+"]/div/form/div/div[2]/div/form/div[2]/div/div/mat-checkbox[@formcontrolName='TPEnabled']/label";
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void enableSL(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form/div[2]/div/div[3]/div/mat-checkbox[@formcontrolName='SLEnabled']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legSL(String slValue,char legNumber) {
		enableSL(legNumber);
		String xpath ="//app-strategy-leg-form["+legNumber+"]/div/form/div/div[2]/div/form/div[2]/div/div[3]/mat-form-field[2]/div/div/div[3]/div/div/div/input";
		driver.findElement(By.xpath(xpath)).sendKeys(slValue);
	}
	
	public void enableLegTrailSL(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form/div[2]/div/div[4]/div/mat-checkbox[@formcontrolName='trailSLEnabled']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legTrailSLat(String trailATvalue,char legNumber) {
		enableLegTrailSL(legNumber);
		String xpath ="//app-strategy-leg-form["+legNumber+"]/div/form/div/div[2]/div/form/div[2]/div/div[4]/mat-form-field[2]/div/div/div[3]/div/div/div/input";
		driver.findElement(By.xpath(xpath)).sendKeys(trailATvalue);
	}
	
	public void legTrailSLby(String trailByvalue,char legNumber) {
		String xpath ="//app-strategy-leg-form["+legNumber+"]/div/form/div/div[2]/div/form/div[2]/div/div[4]/mat-form-field[3]/div/div/div[3]/div/div/div/input";
		driver.findElement(By.xpath(xpath)).sendKeys(trailByvalue);
	}
	
	public void enableLegMoveSL(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form/div[2]/div[2]/div/mat-checkbox[@formcontrolName='costTypeEnabled']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legMoveSL(String moveSLvalue,char legNumber) {
		enableLegMoveSL(legNumber);
		String xpath ="//app-strategy-leg-form["+legNumber+"]/div/form/div/div[2]/div/form/div[2]/div[2]/mat-form-field[2]/div/div/div[3]/div/div/div/input";
		driver.findElement(By.xpath(xpath)).sendKeys(moveSLvalue);
	}
	
}
