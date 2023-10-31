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

public class Testcase04 {
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

        // Step 3: In mobile products list, click on -> Add To Compare -> for 2 mobiles
        // (Sony Xperia & Iphone)
        WebElement addToCompareSony = driver.findElement(By
                .xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a"));
        addToCompareSony.click();

        WebElement addToCompareIphone = driver.findElement(By
                .xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a"));
        addToCompareIphone.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4: Click on -> COMPARE -> button. A popup window opens
        WebElement compareButton = driver
                .findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[3]/div[1]/div[2]/div/button"));
        compareButton.click();

        // Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        // Switch to new window
        for (String winHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(winHandle)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: Verify the pop-up window and check that the products are reflected in
        // it
        WebElement heading = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[1]/h1"));

        if (heading.getText().equals("Compare Products")) {
            System.out.println("Heading is correct!");
        } else {
            System.out.println("Heading is incorrect!");
        }

        WebElement sonyProduct = driver
                .findElement(By.xpath("//*[@id=\"product_comparison\"]/tbody[1]/tr[1]/td[1]/h2/a"));
        WebElement iphoneProduct = driver
                .findElement(By.xpath("//*[@id=\"product_comparison\"]/tbody[1]/tr[1]/td[2]/h2/a"));

        if (sonyProduct.isDisplayed() && iphoneProduct.isDisplayed()) {
            System.out.println("Both products are reflected in the popup!");
        } else {
            System.out.println("One or both of the products are not reflected in the popup!");
        }

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Capture screenshot at the end
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File("src/main/resources/screenshots/screenshot_tc04.png"));
            System.out.println("Screenshot captured!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot!");
        }

        // Close the new window or tab
        driver.close();

        // Switch back to the original window
        driver.switchTo().window(originalWindow);

        // Close the original window
        driver.quit();
    }
}
