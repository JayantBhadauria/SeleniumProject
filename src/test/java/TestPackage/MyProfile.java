package TestPackage;

import java.awt.Robot;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import MainModules.HomePage;
import TestComponents.BaseTest;
import TestComponents.RetryAnalyzer;

public class MyProfile extends BaseTest {
    @Test(testName = "changeProfile", groups = "Smoke", retryAnalyzer = RetryAnalyzer.class)
    public void DeleteProfile() throws InterruptedException {
        LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
        HomePage homePage=LoginPage.goToHomePage();
        homePage.MyProfileSection("My Profile");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[text()=' Remove Photo ']")).click();
    }

    @Test(testName="ChangeProfile", groups= "RunOnly", retryAnalyzer = RetryAnalyzer.class)
    public void ChangeProfilePicture() throws InterruptedException, IOException{
        LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
        HomePage homePage=LoginPage.goToHomePage();
        homePage.MyProfileSection("My Profile");
        driver.findElement(By.xpath("//button[@class='mat-focus-indicator edit-img-button bg-white no-border position-absolute mat-icon-button mat-button-base']")).click();
        Thread.sleep(3000);
        Runtime.getRuntime().exec("C:\\Users\\jayan\\OneDrive\\Pictures\\OneDrive\\Documents\\FileName.exe");
        PopUpMessage();
    }

    @Test(testName ="Edit Profile",retryAnalyzer = RetryAnalyzer.class , dataProvider="ProfileDetails")
    public void EditProfile(LinkedHashMap<String,String> input){
        // To be done
    }

    @Test(testName ="Edit PAN",retryAnalyzer = RetryAnalyzer.class,groups = {"Smoke"},dataProvider = "ProfileDetails")
    public void EditPAN(LinkedHashMap<String,String>input) throws InterruptedException{
        String PAN=input.get("PAN Number");
        LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
        HomePage homepage=LoginPage.goToHomePage();
        homepage.MyProfileSection("My Profile");
        driver.findElement(By.xpath("//span[text()=' Edit Broker Details ']")).click();
        WebElement panField=driver.findElement(By.cssSelector("input[formcontrolname='pan']"));
        homepage.Sendkey(panField, PAN);
        driver.findElement(By.xpath("//span[text()='Save']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()=' "+PAN+" ']")).isDisplayed());
    }
}
