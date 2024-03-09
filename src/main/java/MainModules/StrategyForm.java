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
	public List<WebElement> getDropdownElements() {
		String xpath="//div[@class='cdk-overlay-container']/div[2]/div/div/div/mat-option";
		List<WebElement> elements=driver.findElements(By.xpath(xpath));
		return elements;
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
	
	public void strategyType(String strategyType) {
		// Strategy Type
		driver.findElement(By.xpath("//mat-select[@id='mat-select-5']")).click();
		List<WebElement> stgTyp= getDropdownElements();
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
		List<WebElement> expTyp= getDropdownElements();
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
		List<WebElement> strikeTyp= getDropdownElements();
		for(int i=0;i<strikeTyp.size();i++) {
			String strikeName=strikeTyp.get(i).findElement(By.xpath("./span")).getText();
			if(strikeName.contains(strikeType)) {
				strikeTyp.get(i).findElement(By.xpath("./span")).click();
				break;
			}
		}
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
	
	public void legField(String fieldName, String fieldValue) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String updatedFieldName=fieldName.substring(0, fieldName.length()-1);
		Method method=getClass().getMethod(updatedFieldName, String.class, char.class);
		char legNumber=fieldName.charAt(fieldName.length()-1);
		method.invoke(this, fieldValue,legNumber);
	}
	
	public void legPrice(String price,char legNumber) {
		// Price Entry
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div/div[2]/mat-form-field[5]/div/div/div[3]/div/div/input[@formcontrolname='strikePriceInput']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(price);
	}
	
	public void legLotMultiplier(String multiplier,char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div/div[2]/mat-form-field[6]/div/div/div[3]/div/div/input[@formcontrolname='lotMultiplier']", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(multiplier);
	}
	
	public void enableLegSettings(char legNumber) {
		int legNum=legNumber;
		List<WebElement> togglebuttons=driver.findElements(By.xpath("//mat-slide-toggle[@formcontrolname='legSettings']"));
		WebElement togglebutton= togglebuttons.get(legNum-48-1);
		togglebutton.click();
	}
	
	
	
	public void legExpiryType(String expiryType,char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div/div[2]/mat-form-field[3]/div/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement>expTyp=getDropdownElements();
		for(int i=0;i<expTyp.size();i++) {
			String expName=expTyp.get(i).findElement(By.xpath("./span")).getText();
			if(expName.contains(expiryType)) {
				expTyp.get(i).findElement(By.xpath("./span")).click();
					break;
				}
		}
	}
	
	public void legInstrumentType(String instType,char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div/div[2]/mat-form-field[2]/div/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement>instTypList=getDropdownElements();
		for(int i=0;i<instTypList.size();i++) {
			String instName=instTypList.get(i).findElement(By.xpath("./span")).getText();
			if(instName.contains(instType)) {
				instTypList.get(i).findElement(By.xpath("./span")).click();
					break;
				}
		}
	}
	
	public void legOrderMode(String odrType,char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div/div[2]/mat-form-field[1]/div/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement>odrTypList=getDropdownElements();
		for(int i=0;i<odrTypList.size();i++) {
			String odrName=odrTypList.get(i).findElement(By.xpath("./span")).getText();
			if(odrName.contains(odrType)) {
				odrTypList.get(i).findElement(By.xpath("./span")).click();
					break;
				}
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
	
	public void enableWTT(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/div/div/mat-checkbox[@formcontrolName='waitToTradeToggle']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legWTT(String WTTvalue,char legNumber) {
		enableWTT(legNumber);
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/div/div[2]/mat-form-field[2]/div/div/div[3]/div/div/div/input", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(WTTvalue);
	}
	
	public void enableRangeThreshold(char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/div/div[2]/div/div/mat-checkbox[@formcontrolName='rangeThreshold']/label", legNumber);
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void legRangeThreshold(String WTTvalue,char legNumber) {
		enableRangeThreshold(legNumber);
		// To be implement
	}
	
	public void enableRange(String rangeType, char legNumber) {
		String xpath = String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form/div/div/mat-form-field", legNumber);
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
		String xpath ="//app-strategy-leg-form["+legNumber+"]/div/form/div/div[2]/div/form/div[2]/div/div[2]/mat-form-field/div/div/div[3]/div/div/div/input";
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void legMaxRangeValue(String value,char legNumber) throws InterruptedException {
		sleep(500);
		String xpath ="//app-strategy-leg-form["+legNumber+"]/div/form/div/div[2]/div/form/div[2]/div[2]/mat-form-field/div/div/div[3]/div/div/div/input";
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void legEnableReOrder(char legNumber) {
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-slide-toggle[@formcontrolName='reOrderConditionToggle']/label/div", legNumber);
		String toggleButtonState=driver.findElement(By.xpath(xpath+"/input")).getAttribute("aria-checked");
		if(toggleButtonState.contains("false")) {
			driver.findElement(By.xpath(xpath+"/div/div")).click();
			System.out.println("Element clicked");
		}
	}
	
	public void legTpReEntry(String value,char legNumber) {
		legEnableReOrder(legNumber);
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='tpReOrderToggle']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		xpath=String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form[2]/div/div[1]/div[2]/mat-form-field[1]/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement>reordertypList=getDropdownElements();
		for(int i=0;i<reordertypList.size();i++) {
			String reorderType=reordertypList.get(i).findElement(By.xpath("./span")).getText();
			if(reorderType.contains("Entry")) {
				reordertypList.get(i).findElement(By.xpath("./span")).click();
					break;
				}
		}
		xpath=String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form[2]/div/div[1]/div[2]/mat-form-field[2]/div/div/div[3]/div/div/div/input", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	
	public void legTpReExecution(String value,char legNumber) {
		legEnableReOrder(legNumber);
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='tpReOrderToggle']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		xpath=String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form[2]/div/div[1]/div[2]/mat-form-field[1]/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement>reordertypList=getDropdownElements();
		for(int i=0;i<reordertypList.size();i++) {
			String reorderType=reordertypList.get(i).findElement(By.xpath("./span")).getText();
			if(reorderType.contains("Execution")) {
				reordertypList.get(i).findElement(By.xpath("./span")).click();
					break;
				}
		}
		xpath=String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form[2]/div/div[1]/div[2]/mat-form-field[2]/div/div/div[3]/div/div/div/input", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void legSlReEntry(String value,char legNumber) {
		legEnableReOrder(legNumber);
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='slReOrderToggle']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		xpath=String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form[2]/div/div[2]/div[2]/mat-form-field[1]/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement>reordertypList=getDropdownElements();
		for(int i=0;i<reordertypList.size();i++) {
			String reorderType=reordertypList.get(i).findElement(By.xpath("./span")).getText();
			if(reorderType.contains("Entry")) {
				reordertypList.get(i).findElement(By.xpath("./span")).click();
					break;
				}
		}
		xpath=String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form[2]/div/div[2]/div[2]/mat-form-field[2]/div/div/div[3]/div/div/div/input", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	public void legSlReExecution(String value,char legNumber) {
		legEnableReOrder(legNumber);
		String xpath=String.format("//app-strategy-leg-form[%s]//mat-checkbox[@formcontrolName='slReOrderToggle']", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		xpath=String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form[2]/div/div[2]/div[2]/mat-form-field[1]/div", legNumber);
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement>reordertypList=getDropdownElements();
		for(int i=0;i<reordertypList.size();i++) {
			String reorderType=reordertypList.get(i).findElement(By.xpath("./span")).getText();
			if(reorderType.contains("Execution")) {
				reordertypList.get(i).findElement(By.xpath("./span")).click();
					break;
				}
		}
		xpath=String.format("//app-strategy-leg-form[%s]/div/form/div/div[2]/div/form[2]/div/div[2]/div[2]/mat-form-field[2]/div/div/div[3]/div/div/div/input", legNumber);
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
}
