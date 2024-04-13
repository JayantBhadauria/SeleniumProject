package MainModules;

import java.util.LinkedHashMap;
import java.io.IOException;
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
	
	public void selectListOption(String option) {
		List<WebElement>listItems=getDropdownElements();
		for(int i=0;i<listItems.size();i++) {
			String ListItemName=listItems.get(i).findElement(By.xpath("./span")).getText();
			if(ListItemName.contains(option)) {
				listItems.get(i).findElement(By.xpath("./span")).click();
				break;
			}
		}
	}
	
	public void strategySetting(String strategyType, String expiryType, String strikeType, String price1, String price2) {
		strategyType(strategyType);
		expiryType(expiryType);
		strikeSelection(strikeType);
	}
	
	
	public void strategyField(String fieldName, String fieldValue) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method=getClass().getMethod(fieldName, String.class);
		method.invoke(this, fieldValue);
	}
	
	public void stgSqroffType(String sqrOffType) {
		String xpath="//app-strategy-details-form//mat-select[@formcontrolname='squareOff']";
		driver.findElement(By.xpath(xpath)).click();
		selectListOption(sqrOffType);
	}
	
	public void stgUnderlyingType(String underlyingType) {
		String xpath="//app-strategy-details-form//mat-select[@formcontrolname='underlying']";
		driver.findElement(By.xpath(xpath)).click();
		selectListOption(underlyingType);
	}
	
	public void strategyType(String strategyType) {
		// Strategy Type
		driver.findElement(By.xpath("//mat-select[@formcontrolname='strategyType']")).click();
		selectListOption(strategyType);
	}
	
	public void expiryType(String expiryType) {
		// Expiry type 
		driver.findElement(By.xpath("// mat-select[@formcontrolname='expiryType']")).click();
		selectListOption(expiryType);
	}
	
	public void strikeSelection(String strikeType) {
		// Strike type 
		driver.findElement(By.xpath("// mat-select[@formcontrolname='strikeSelection']")).click();
		selectListOption(strikeType);
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
	
	public void addLeg() {
		WebElement legButton=driver.findElement(By.xpath("//app-individual-strategy-form//span[text()='Add Leg']"));
		legButton.click();
	}
	
	public void removeLeg(char legNumber) {
		String xpath =String.format("//app-strategy-leg-form[%s]//span[text()=' [Remove] ']", legNumber);
		WebElement removeButton=driver.findElement(By.xpath(xpath));
		removeButton.click();
	}
	
	public void legField(String fieldName, String fieldValue) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String updatedFieldName=fieldName.substring(0, fieldName.length()-1);
		Method method=getClass().getMethod(updatedFieldName, String.class, char.class);
		char legNumber=fieldName.charAt(fieldName.length()-1);
		method.invoke(this, fieldValue,legNumber);
	}
	
	public void legPrice(String price,char legNumber) {
		// Price Entry
		String xpath = String.format("//app-strategy-leg-form[%s]//input[@formcontrolname='strikePriceInput']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(price);
	}
	
	public void legLotMultiplier(String multiplier,char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//input[@formcontrolname='lotMultiplier']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(multiplier);
	}
	
	public void enableLegSettings(char legNumber) {
		int legNum=legNumber;
		List<WebElement> togglebuttons=driver.findElements(By.xpath("//mat-slide-toggle[@formcontrolname='legSettings']"));
		WebElement togglebutton= togglebuttons.get(legNum-48-1);
		togglebutton.click();
	}
	
	
	
	public void legExpiryType(String expiryType,char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-select[@formcontrolname='expiryType']/div/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		selectListOption(expiryType);
	}
	
	public void legInstrumentType(String instType,char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-select[@formcontrolname='instrumentType']/div/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		selectListOption(instType);
	}
	
	public void legOrderMode(String odrType,char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-select[@formcontrolname='orderMode']/div/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		selectListOption(odrType);
	}
	
	
	public void legTP(String tpValue,char legNumber) {
		enableTP(legNumber);
		String xpath ="//app-strategy-leg-form["+legNumber+"]//input[@formcontrolName='targetProfitValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(tpValue);
	}
	
	public void enableTP(char legNumber) {
		String xpath ="//app-strategy-leg-form["+legNumber+"]//mat-checkbox[@formcontrolName='TPEnabled']/label";
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void enableSL(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='SLEnabled']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legSL(String slValue,char legNumber) {
		enableSL(legNumber);
		String xpath ="//app-strategy-leg-form["+legNumber+"]//input[@formcontrolName='stopLossValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(slValue);
	}
	
	public void enableLegTrailSL(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='trailSLEnabled']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legTrailSLat(String trailATvalue,char legNumber) {
		enableLegTrailSL(legNumber);
		String xpath ="//app-strategy-leg-form["+legNumber+"]//input[@formcontrolName='trailSLValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(trailATvalue);
	}
	
	public void legTrailSLby(String trailByvalue,char legNumber) {
		String xpath ="//app-strategy-leg-form["+legNumber+"]//input[@formcontrolName='increaseBy']";
		driver.findElement(By.xpath(xpath)).sendKeys(trailByvalue);
	}
	
	public void enableLegMoveSL(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='costTypeEnabled']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legMoveSL(String moveSLvalue,char legNumber) {
		enableLegMoveSL(legNumber);
		String xpath ="//app-strategy-leg-form["+legNumber+"]//input[@formcontrolname='costValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(moveSLvalue);
	}
	
	public void enableWTT(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='waitToTradeToggle']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legWTT(String WTTvalue,char legNumber) {
		enableWTT(legNumber);
		String xpath = String.format("//app-strategy-leg-form[%s]//input[@formcontrolname='waitToTradeValue']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(WTTvalue);
	}
	
	public void enableRangeThreshold(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='rangeThreshold']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legRangeThreshold(String WTTvalue,char legNumber) {
		enableRangeThreshold(legNumber);
		// To be implement
	}
	
	public void enableRange(String rangeType, char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]//mat-select[@formcontrolname='underlyingType']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement>elements=getDropdownElements();
		for(int i=0;i<elements.size();i++) {
			WebElement element=elements.get(i).findElement(By.xpath("./span"));
			if(rangeType.contains("1") && element.getText().contains("Underlying (₹)")) {
				element.click();
				break;
			}
			else if(rangeType.contains("2") && element.getText().contains("Underlying (pts)")){
				element.click();
				break;
			}
			else if(rangeType.contains("3") && element.getText().contains("Delta")){
				element.click();
				break;
			}
		}	
	}
	
	public void legMinRangeValue(String value,char legNumber) throws InterruptedException {
		sleep(500);
		String xpath ="//app-strategy-leg-form["+legNumber+"]//input[@formcontrolname='underlyingMinValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void legMaxRangeValue(String value,char legNumber) throws InterruptedException {
		sleep(500);
		String xpath ="//app-strategy-leg-form["+legNumber+"]//input[@formcontrolname='underlyingMaxValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void legEnableReOrder(char legNumber) {
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-slide-toggle[@formcontrolName='reOrderConditionToggle']/label/div", legNumber);
		String toggleButtonState=driver.findElement(By.xpath(xpath+"/input")).getAttribute("aria-checked");
		if(toggleButtonState.contains("false")) {
			driver.findElement(By.xpath(xpath+"/div/div")).click();
		}
	}
	
	public void legTpReEntry(String value,char legNumber) {
		legEnableReOrder(legNumber);
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='tpReOrderToggle']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		xpath=String.format("//app-strategy-leg-form[%s]//mat-select[@formcontrolname='tpReOrderType']/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		selectListOption("Entry");
		xpath=String.format("//app-strategy-leg-form[%s]//input[@formcontrolname='tpReOrderValue']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	
	public void legTpReExecution(String value,char legNumber) {
		legEnableReOrder(legNumber);
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='tpReOrderToggle']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		xpath=String.format("//app-strategy-leg-form[%s]//mat-select[@formcontrolname='tpReOrderType']/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		selectListOption("Execution");
		xpath=String.format("//app-strategy-leg-form[%s]//input[@formcontrolname='tpReOrderValue']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void legSlReEntry(String value,char legNumber) {
		legEnableReOrder(legNumber);
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='slReOrderToggle']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		xpath=String.format("//app-strategy-leg-form[%s]//mat-select[@formcontrolname='slReOrderType']/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		selectListOption("Entry");
		xpath=String.format("//app-strategy-leg-form[%s]//input[@formcontrolname='slReOrderValue']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void legSlReExecution(String value,char legNumber) {
		legEnableReOrder(legNumber);
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='slReOrderToggle']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		xpath=String.format("//app-strategy-leg-form[%s]//mat-select[@formcontrolname='slReOrderType']/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		selectListOption("Execution");
		xpath=String.format("//app-strategy-leg-form[%s]//input[@formcontrolname='slReOrderValue']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void stgTp(String tpValue) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='TPEnabled']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='targetProfitValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(tpValue);
	}
	
	public void stgSl(String slValue) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='SLEnabled']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='stopLossValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(slValue);
	}
	
	public void stgMoveSL(String moveSlValue) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='costTypeEnabled']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='costValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(moveSlValue);
	}
	
	public void stgTpReEntry(String Value) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='tpReOrderToggle']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-select[@formcontrolname='tpReOrderType']";
		driver.findElement(By.xpath(xpath)).click();
		selectListOption("Entry");
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='tpReOrderValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(Value);
	}
	
	public void stgTpReExecution(String Value) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='tpReOrderToggle']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-select[@formcontrolname='tpReOrderType']";
		driver.findElement(By.xpath(xpath)).click();
		selectListOption("Execution");
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='tpReOrderValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(Value);
	}
	
	public void stgSlReEntry(String Value) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='slReOrderToggle']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-select[@formcontrolname='slReOrderType']";
		driver.findElement(By.xpath(xpath)).click();
		selectListOption("Entry");
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='slReOrderValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(Value);
	}
	
	public void stgSlReExecution(String Value) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='slReOrderToggle']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-select[@formcontrolname='slReOrderType']";
		driver.findElement(By.xpath(xpath)).click();
		selectListOption("Execution");
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='slReOrderValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(Value);
	}
	
	public void stgTrailSLat(String Value) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='trailSLEnabled']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='trailSLValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(Value);
	}
	
	public void stgTrailSLby(String Value) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='increaseBy']";
		driver.findElement(By.xpath(xpath)).sendKeys(Value);
	}
	
	public void stgLockProfitOn(String Value) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//mat-checkbox[@formcontrolname='trailLockedProfit']/label/div";
		driver.findElement(By.xpath(xpath)).click();
		xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='trailLockedProfitValue']";
		driver.findElement(By.xpath(xpath)).sendKeys(Value);
	}
	
	public void stgLockProfitAt(String Value) {
		String xpath="//app-individual-strategy-form//app-strategy-exit-form//input[@formcontrolname='lockProfitAt']";
		driver.findElement(By.xpath(xpath)).sendKeys(Value);
		log.info("LockProfit : "+Value);
	}

}
