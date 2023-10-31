package BAITAP;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import POM.LoginForm;
import driver.driverFactory;

public class Testcase05 {
    public static void main(String[] args) {
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 2: Click on my account link
        WebElement myAccountLink = driver.findElement(By.linkText("MY ACCOUNT"));
        myAccountLink.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 3: Click Create an Account link and fill New User information excluding
        // the registered Email ID.
        WebElement createAccountLink = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/div[1]/div[2]/a"));
        createAccountLink.click();

        // Fill New User information
        LoginForm loginForm = new LoginForm(driver);
        loginForm.fillForm("Test", "Mid","User", "idbbvjciqabfci@gmail.com", "password123", "password123");

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4: Click Register
        WebElement registerButton = driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button"));
        registerButton.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: Verify Registration is done. Expected account registration done.
        WebElement successMessage = driver
                .findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span"));

        if (successMessage.getText().contains("Thank you for registering with Main Website Store.")) {
            System.out.println("Registration is done!");
        } else {
            System.out.println("Registration failed!");
        }

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 6: Go to TV menu
        WebElement tvMenu = driver.findElement(By.linkText("TV"));
        tvMenu.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 7: Add product in your wish list - use product - LG LCD
        WebElement addToWishlistLG = driver.findElement(By
                .xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/ul/li[1]/a"));
        addToWishlistLG.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 8: Click SHARE WISHLIST
        WebElement shareWishlistButton = driver
                .findElement(By.xpath("//*[@id=\"wishlist-view-form\"]/div/div/button[1]"));
        shareWishlistButton.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 9: In next page enter Email and a message and click SHARE WISHLIST
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"email_address\"]"));
        emailInput.sendKeys("friend@example.com");

        WebElement messageInput = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        messageInput.sendKeys("Check out my wishlist!");

        WebElement shareWishlistButton2 = driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button"));
        shareWishlistButton2.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 10: Check wishlist is shared. Expected wishlist shared successfully.
        WebElement successMessage2 = driver.findElement(By.cssSelector(".success-msg"));

        if (successMessage2.getText().contains("Your Wishlist has been shared.")) {
            System.out.println("Wishlist is shared!");
        } else {
            System.out.println("Failed to share wishlist!");
        }

        // Capture screenshot at the end
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File("src/main/resources/screenshots/screenshot_tc05.png"));
            System.out.println("Screenshot captured!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot!");
        }

        // Close the browser
        driver.quit();
    }
}
