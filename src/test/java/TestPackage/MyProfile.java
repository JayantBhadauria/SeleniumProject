package TestPackage;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
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

    @Test(testName="ChangeProfile", groups="Smoke", retryAnalyzer = RetryAnalyzer.class)
    public void ChangeProfilePicture() throws InterruptedException{
        LoginPage.LoginApplication(prop.getProperty("username"), prop.getProperty("password"));
        HomePage homePage=LoginPage.goToHomePage();
        homePage.MyProfileSection("My Profile");
        Thread.sleep(2000);
                
    }

    @Test(testName ="Edit Profile",retryAnalyzer = RetryAnalyzer.class , dataProvider="ProfileDetails")
    public void EditProfile(LinkedHashMap<String,String> input){
        // To be done
    }

    @Test(testName ="Edit Profile",retryAnalyzer = RetryAnalyzer.class)
    public void EditPAN(String PAN){
        // To be done
    }
}
