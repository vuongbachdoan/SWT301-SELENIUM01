package BAITAP;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import POM.LoginForm;
import driver.driverFactory;

public class Testcase07 {
    public static void main(String[] args) throws InterruptedException {
        By myAccountLink = By.linkText("MY ACCOUNT");
        By loginBtn = By.cssSelector("#send2");
        By myOrdersLink = By.linkText("MY ORDERS");
        By viewOrderBtn = By
                .cssSelector("#my-orders-table > tbody > tr.first.odd > td.a-center.view.last > span > a:nth-child(1)");
        By printOrdeBtn = By.cssSelector(
                "body > div > div > div.main-container.col2-left-layout > div > div.col-main > div > div.page-title.title-buttons > a.link-print");

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
        driver.findElement(myAccountLink).click();
        Thread.sleep(500);

        // Step 3: Login in application using previously created credential
        LoginForm loginForm = new LoginForm(driver);
        loginForm.fillForm("idbbvjciqabfci@gmail.com", "password123");
        Thread.sleep(500);

        // Click Login
        driver.findElement(loginBtn).click();
        Thread.sleep(500);

        // Step 4: Click on MY ORDER link
        driver.findElement(myOrdersLink).click();
        Thread.sleep(500);

        // Step 5: Click on 'View Order'
        driver.findElement(viewOrderBtn).click();
        Thread.sleep(500);

        // Step 6: Click on 'Print order'
        driver.findElement(printOrdeBtn).click();
        Thread.sleep(4000);

        // Capture screenshot at the end
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
        FileUtils.copyFile(scrFile, new
        File("src/main/resources/screenshots/screenshot_tc07.png"));
        System.out.println("Screenshot captured!");
        } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Failed to capture screenshot!");
        }

        // Close the browser
        driver.quit();
    }
}
