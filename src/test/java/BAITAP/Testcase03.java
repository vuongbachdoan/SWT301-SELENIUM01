import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Testcase03 {
    public static void main(String[] args) {
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 3: In the list of all mobile, click on -> ADD TO CART -> for Sony Xperia mobile
        WebElement addToCartButton = driver.findElement(By.xpath("//a[contains(text(),'Sony Xperia')]/following-sibling::div/button"));
        addToCartButton.click();

        // Step 4: Change -> QTY -> value to 1000 and click -> UPDATE -> button.
        WebElement qtyInput = driver.findElement(By.cssSelector("input[name='qty']"));
        qtyInput.clear();
        qtyInput.sendKeys("1000");
        
        WebElement updateButton = driver.findElement(By.cssSelector("button[name='update_cart_action']"));
        updateButton.click();

        // Step 5: Verify the error message
        WebElement errorMessage = driver.findElement(By.cssSelector(".message-error div"));
        String expectedErrorMessage = "The requested quantity for \"Sony Xperia\" is not available.";
        
        if (errorMessage.getText().equals(expectedErrorMessage)) {
            System.out.println("The error message is correct!");
        } else {
            System.out.println("The error message is incorrect!");
        }

        // Step 6: Then click on -> EMPTY CART -> link in the footer of list of all mobiles.
        WebElement emptyCartLink = driver.findElement(By.cssSelector("a[title='Empty Cart']"));
        emptyCartLink.click();

        // Step 7: Verify cart is empty
        WebElement cartMessage = driver.findElement(By.cssSelector(".cart-empty"));
        
        if (cartMessage.getText().contains("SHOPPING CART IS EMPTY")) {
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
