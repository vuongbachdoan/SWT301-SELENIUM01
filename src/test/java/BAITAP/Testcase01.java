/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/

package BAITAP;

import driver.driverFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Testcase01 {
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

        // Step 2: Verify Title of the page
        String title = driver.getTitle();
        System.out.println("Page Title is: " + title);

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 3: Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4: In the list of all mobile , select SORT BY -> dropdown as name
        Select dropdown = new Select(driver.findElement(By.cssSelector("select[title='Sort By']")));
        dropdown.selectByVisibleText("Name");

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: Verify all products are sorted by name
        List<WebElement> products = driver.findElements(By.cssSelector(".product-name"));
        for (WebElement product : products) {
            System.out.println(product.getText());
        }

        // Capture screenshot at the end
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File("src/main/resources/screenshots/screenshot_tc01.png"));
            System.out.println("Screenshot captured!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot!");
        }

        // Close the browser
        driver.quit();
    }
}
