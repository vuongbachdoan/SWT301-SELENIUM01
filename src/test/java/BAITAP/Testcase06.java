package BAITAP;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import POM.BillingForm;
import POM.CartAddForm;
import POM.LoginForm;
import POM.ShippingForm;
import driver.driverFactory;

public class Testcase06 {
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

        // Step 3: Login in application using previously created credential
        LoginForm loginForm = new LoginForm(driver);
        loginForm.fillForm("idbbvjciqabfci@gmail.com", "password123");

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click Login
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"send2\"]"));
        loginButton.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4: Click on MY WISHLIST link
        WebElement wishlistLink = driver
                .findElement(By.linkText("MY WISHLIST"));
        wishlistLink.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: In next page, Click ADD TO CART link
        WebElement addToCartLink = driver
                .findElement(By.cssSelector("tr > td.wishlist-cell4.customer-wishlist-item-cart > div > button"));
        addToCartLink.click();

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 6: Enter general shipping country, state/province and zip for the
        // shipping cost estimate
        CartAddForm cartAddForm = new CartAddForm(driver);
        cartAddForm.fillForm("United States", "Alaska", "99501");

        // Add sleep
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 7: Click Estimate
        WebElement estimateBtn = driver.findElement(By.xpath("//*[@id=\"shipping-zip-form\"]/div/button"));
        estimateBtn.click();

        // Step 8: Verify Shipping cost generated
        WebElement shippingCost = driver
                .findElement(By.xpath("//*[@id=\"co-shipping-method-form\"]/dl/dd/ul/li/label/span"));
        System.out.println("Shipping cost is:" + shippingCost.getText());

        // Step 9: Select Shipping Cost, Update Total
        WebElement checkboxShippingCost = driver.findElement(By.xpath("//*[@id=\"s_method_flatrate_flatrate\"]"));
        checkboxShippingCost.click();
        WebElement updateTotalBtn = driver.findElement(By.xpath("//*[@id=\"co-shipping-method-form\"]/div/button"));
        updateTotalBtn.click();

        // Step 10: Verify shipping cost is added to total
        WebElement netCost = driver
                .findElement(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tbody/tr[1]/td[2]/span"));
        System.out.println("Net cost is:" + netCost.getText());
        WebElement totalCost = driver
                .findElement(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span"));
        System.out.println("Total cost is:" + totalCost.getText());

        // Step 11: Click "Proceed to Checkout"
        WebElement checkoutBtn = driver
                .findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/div[3]/div/ul/li[1]/button"));
        checkoutBtn.click();

        // Step 12a: Enter Billing Information, and click Continue
        // Enter billing information
        try {
            BillingForm billingForm = new BillingForm(driver);
            billingForm.fillForm("User", "Mid", "Last", "ABC", "HCM", "HN", "HCM", "United States", "Alaska", "99501",
                    "0122345678", "111222");
        } catch (Exception e) {
            System.out.println("User already enter billing information");
        }
        // Continue billing
        WebElement billingContinueBtn = driver.findElement(By.cssSelector("#billing-buttons-container > button"));
        billingContinueBtn.click();

        // Step 12b: Enter Shipping Information, and click Continue
        // Enter shipping information
        try {
            ShippingForm shippingInformation = new ShippingForm(driver);
            shippingInformation.fillForm("User", "Mid", "Last", "ABC", "HCM", "HN",
                    "HCM", "United States", "Alaska",
                    "99501", "0122345678", "111222");

            // Continue shipping
            WebElement billingContinueContinueBtn = driver
                    .findElement(By.xpath("//*[@id=\"shipping-buttons-container\"]/button"));
            billingContinueContinueBtn.click();
        } catch (Exception e) {
            System.out.println("User already enter shipping information!");
        }

        // Step 13: In Shipping Method, Click Continue
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#shipping-method-buttons-container button")))
                .click();

        // Step 14: In Payment Information select 'Check/Money Order' radio button.
        // Click Continue
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#p_method_checkmo")))
                .click();
        WebElement checkboxPaymentMethod = driver.findElement(By.cssSelector("#dt_method_checkmo"));
        // (new Select(checkboxPaymentMethod)).selectByVisibleText("Check / Money
        // order");
        checkboxPaymentMethod.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#payment-buttons-container > button")))
                .click();
        WebElement continueShippingMethodBtn = driver
                .findElement(By.cssSelector("#payment-buttons-container > button"));
        continueShippingMethodBtn.click();

        // Step 15: Click 'PLACE ORDER' button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#review-buttons-container > button")))
                .click();
        WebElement placeOrderBtn = driver.findElement(By.cssSelector("#review-buttons-container > button"));
        placeOrderBtn.click();

        // Step 16: Verify Oder is generated. Note the order number
        wait.until(ExpectedConditions.elementSelectionStateToBe(
                By.cssSelector("body > div > div > div.main-container.col1-layout > div > div > p:nth-child(3)"),
                false));
        WebElement orderNumber = driver.findElement(
                By.cssSelector("body > div > div > div.main-container.col1-layout > div > div > p:nth-child(3)"));
        System.out.println(orderNumber.getText());

        // Capture screenshot at the end
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File("src/main/resources/screenshots/screenshot_tc06.png"));
            System.out.println("Screenshot captured!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot!");
        }

        // Close the browser
        driver.quit();
    }
}
