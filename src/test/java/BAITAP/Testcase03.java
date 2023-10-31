package BAITAP;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.driverFactory;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Testcase03 {
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

        // Step 2: Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 3: In the list of all mobile, click on -> ADD TO CART -> for Sony Xperia mobile
        WebElement addToCartButton = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/button"));
        addToCartButton.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4: Change -> QTY -> value to 1000 and click -> UPDATE -> button.
        WebElement qtyInput = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/input"));
        qtyInput.clear();
        qtyInput.sendKeys("1000");
        
        WebElement updateButton = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/button"));
        updateButton.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: Verify the error message
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/ul/li/ul/li/span"));
        String expectedErrorMessage = "The requested quantity for \"Sony Xperia\" is not available.";
        
        if (errorMessage.getText().equals(expectedErrorMessage)) {
            System.out.println("The error message is correct!");
        } else {
            System.out.println("The error message is incorrect!");
        }

         // Add sleep
         try {
             Thread.sleep(500);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

         // Step 6: Then click on -> EMPTY CART -> link in the footer of list of all mobiles.
         WebElement emptyCartLink = driver.findElement(By.xpath("//*[@id=\"empty_cart_button\"]"));
         emptyCartLink.click();

         // Add sleep
         try {
             Thread.sleep(500);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

         // Step 7: Verify cart is empty
         WebElement cartMessage = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div[1]/h1"));
        
         if (cartMessage.getText().contains("Shopping Cart is Empty")) {
             System.out.println("The cart is empty!");
         } else {
             System.out.println("The cart is not empty!");
         }

         // Capture screenshot at the end
         File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        
         try {
             FileUtils.copyFile(scrFile, new File("src/main/resources/screenshots/screenshot_tc03.png"));
             System.out.println("Screenshot captured!");
         } catch (IOException e) {
             e.printStackTrace();
             System.out.println("Failed to capture screenshot!");
         }

         // Close the browser
         driver.quit();
    }
}
