package BAITAP;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import POM.BillingForm;
import POM.CartAddForm;
import POM.LoginForm;
import POM.ShippingForm;
import driver.driverFactory;

public class Testcase06 {
    public static void main(String[] args) throws InterruptedException {
        By myAccountLink = By.linkText("MY ACCOUNT");
        By loginBtn = By.cssSelector("#send2");
        By myWishlistLink = By.linkText("MY WISHLIST");
        By addToCartBtn = By.cssSelector("tr > td.wishlist-cell4.customer-wishlist-item-cart > div > button");
        By estimateBtn = By.cssSelector("#shipping-zip-form > div > button");
        By shippingCost = By.cssSelector("#co-shipping-method-form > dl > dd > ul > li > label > span");
        By shippingCostSelector = By.cssSelector("#co-shipping-method-form > dl > dd > ul > li > label");
        By shippingCostAddToTotal = By.cssSelector("#co-shipping-method-form > div > button");
        By priceNet = By.cssSelector("#shopping-cart-totals-table > tbody > tr:nth-child(1) > td:nth-child(2) > span");
        By priceTotal = By.cssSelector("#shopping-cart-totals-table > tfoot > tr > td:nth-child(2) > strong > span");
        By checkoutProcessBtn = By.cssSelector(
                "body > div > div > div.main-container.col1-layout > div > div > div > div.cart-totals-wrapper > div > ul > li.method-checkout-cart-methods-onepage-bottom > button");
        By billingContinueBtn = By.cssSelector("#billing-buttons-container > button");
        By shippingContinueBtn = By.cssSelector("#shipping-buttons-container > button");
        By shippingMethodBtn = By.cssSelector("#shipping-method-buttons-container button");
        By methodCheckoutMoney = By.cssSelector("#dt_method_checkmo > label");
        By methodCheckoutMoneyContinue = By.cssSelector("#payment-buttons-container > button");
        By placeOrderBtn = By.cssSelector("#review-buttons-container > button");
        By orderNumber = By
                .cssSelector("body > div > div > div.main-container.col1-layout > div > div > p:nth-child(3)");

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

        // Step 4: Click on MY WISHLIST link
        driver.findElement(myWishlistLink).click();
        Thread.sleep(500);

        // Step 5: In next page, Click ADD TO CART button
        driver.findElement(addToCartBtn).click();
        Thread.sleep(500);

        // Step 6: Enter general shipping country, state/province and zip for the
        // shipping cost estimate
        CartAddForm cartAddForm = new CartAddForm(driver);
        cartAddForm.fillForm("United States", "Alaska", "99501");
        Thread.sleep(500);

        // Step 7: Click Estimate
        driver.findElement(estimateBtn).click();
        Thread.sleep(500);

        // Step 8: Verify Shipping cost generated
        System.out.println("Shipping cost is:" + driver.findElement(shippingCost).getText());
        Thread.sleep(500);

        // Step 9: Select Shipping Cost, Update Total
        driver.findElement(shippingCostSelector).click();
        driver.findElement(shippingCostAddToTotal).click();
        Thread.sleep(500);

        // Step 10: Verify shipping cost is added to total
        System.out.println("Net cost is:" + driver.findElement(priceNet).getText());
        System.out.println("Total cost is:" + driver.findElement(priceTotal).getText());
        Thread.sleep(500);

        // Step 11: Click "Proceed to Checkout"
        driver.findElement(checkoutProcessBtn).click();
        Thread.sleep(500);

        // Step 12a: Enter Billing Information, and click Continue
        // Enter billing information
        try {
            BillingForm billingForm = new BillingForm(driver);
            billingForm.fillForm("User", "Mid", "Last", "ABC", "HCM", "HN", "HCM", "United States", "Alaska", "99501",
                    "0122345678", "111222");
        } catch (Exception e) {
            System.out.println("User already enter billing information");
        }
        Thread.sleep(500);

        // Continue billing
        driver.findElement(billingContinueBtn).click();
        Thread.sleep(500);

        // Step 12b: Enter Shipping Information, and click Continue
        // Enter shipping information
        try {
            ShippingForm shippingInformation = new ShippingForm(driver);
            shippingInformation.fillForm("User", "Mid", "Last", "ABC", "HCM", "HN",
                    "HCM", "United States", "Alaska",
                    "99501", "0122345678", "111222");

            // Continue shipping
            driver.findElement(shippingContinueBtn).click();
        } catch (Exception e) {
            System.out.println("User already enter shipping information!");
        }
        Thread.sleep(500);

        // Step 13: In Shipping Method, Click Continue
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodBtn)).click();
        Thread.sleep(500);

        // Step 14: In Payment Information select 'Check/Money Order' radio button.
        // Click Continue
        wait.until(ExpectedConditions.elementToBeClickable(methodCheckoutMoney)).click();
        wait.until(ExpectedConditions.elementToBeClickable(methodCheckoutMoneyContinue)).click();
        Thread.sleep(500);

        // Step 15: Click 'PLACE ORDER' button
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        Thread.sleep(500);

        // Step 16: Verify Oder is generated. Note the order number
        wait.until(ExpectedConditions.elementSelectionStateToBe(orderNumber, false));
        System.out.println(driver.findElement(orderNumber).getText());
        Thread.sleep(500);

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
