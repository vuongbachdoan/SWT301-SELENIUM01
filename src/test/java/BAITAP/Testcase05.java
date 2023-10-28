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

        // Step 2: Click on my account link
        WebElement myAccountLink = driver.findElement(By.linkText("MY ACCOUNT"));
        myAccountLink.click();

        // Step 3: Click Create an Account link and fill New User information excluding the registered Email ID.
        WebElement createAccountLink = driver.findElement(By.linkText("CREATE AN ACCOUNT"));
        createAccountLink.click();
        
        // Fill New User information
        LoginForm loginForm = new LoginForm(driver, "Test", "User", "idbciqabfci@gmail.com", "password123", "password123");

        // Step 4: Click Register
        WebElement registerButton = driver.findElement(By.cssSelector("button[title='Register']"));
        registerButton.click();

        // Step 5: Verify Registration is done. Expected account registration done.
        WebElement successMessage = driver.findElement(By.cssSelector(".success-msg"));
        
        if (successMessage.getText().contains("Thank you for registering")) {
            System.out.println("Registration is done!");
        } else {
            System.out.println("Registration failed!");
        }

        // Step 6: Go to TV menu
        WebElement tvMenu = driver.findElement(By.linkText("TV"));
        tvMenu.click();

        // Step 7: Add product in your wish list - use product - LG LCD
        WebElement addToWishlistLG = driver.findElement(By.xpath("//a[contains(text(),'LG LCD')]/following-sibling::div/button[contains(@title, 'Add to Wishlist')]"));
        addToWishlistLG.click();

        // Step 8: Click SHARE WISHLIST
        WebElement shareWishlistButton = driver.findElement(By.cssSelector("button[title='Share Wishlist']"));
        shareWishlistButton.click();

        // Step 9: In next page enter Email and a message and click SHARE WISHLIST
        WebElement emailInput = driver.findElement(By.id("email_address"));
        emailInput.sendKeys("friend@example.com");
        
        WebElement messageInput = driver.findElement(By.id("message"));
        messageInput.sendKeys("Check out my wishlist!");
        
        WebElement shareWishlistButton2 = driver.findElement(By.cssSelector("button[title='Share Wishlist']"));
        shareWishlistButton2.click();

         // Step 10: Check wishlist is shared. Expected wishlist shared successfully.
         WebElement successMessage2 = driver.findElement(By.cssSelector(".success-msg"));
        
         if (successMessage2.getText().contains("Your Wishlist has been shared")) {
             System.out.println("Wishlist is shared!");
         } else {
             System.out.println("Failed to share wishlist!");
         }

         // Capture screenshot at the end
         File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        
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
