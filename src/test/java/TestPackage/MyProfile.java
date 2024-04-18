package TestPackage;

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
}
