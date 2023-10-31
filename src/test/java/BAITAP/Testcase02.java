package BAITAP;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/*

Test Steps:

1. Goto http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

4. Click on Sony Xperia mobile

5. Read the Sony Xperia mobile from detail page.

6. Compare Product value in list and details page should be equal ($100).

*/

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.driverFactory;

public class Testcase02 {
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

        // Step 3: In the list of all mobile, read the cost of Sony Xperia mobile (which
        // is $100)
        WebElement sonyXperiaPrice = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]/span"));
        String listPrice = sonyXperiaPrice.getText();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4: Click on Sony Xperia mobile
        WebElement sonyXperiaLink = driver
                .findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/a"));
        sonyXperiaLink.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: Read the Sony Xperia mobile from detail page.
        WebElement detailPriceElement = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]/span"));
        String detailPrice = detailPriceElement.getText();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 6: Compare Product value in list and details page should be equal
        // ($100).
        if (listPrice.equals(detailPrice)) {
            System.out.println("The prices match!");
        } else {
            System.out.println("The prices do not match!");
        }

        // Capture screenshot at the end
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File("src/main/resources/screenshots/screenshot_tc02.png"));
            System.out.println("Screenshot captured!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot!");
        }

        // Close the browser
        driver.quit();
    }
}
