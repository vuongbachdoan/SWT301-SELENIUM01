import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Testcase04 {
    public static void main(String[] args) {
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 3: In mobile products list, click on -> Add To Compare -> for 2 mobiles (Sony Xperia & Iphone)
        WebElement addToCompareSony = driver.findElement(By.xpath("//a[contains(text(),'Sony Xperia')]/following-sibling::div/button[contains(@title, 'Add to Compare')]"));
        addToCompareSony.click();
        
        WebElement addToCompareIphone = driver.findElement(By.xpath("//a[contains(text(),'Iphone')]/following-sibling::div/button[contains(@title, 'Add to Compare')]"));
        addToCompareIphone.click();

        // Step 4: Click on -> COMPARE -> button. A popup window opens
        WebElement compareButton = driver.findElement(By.cssSelector("button[title='Compare']"));
        compareButton.click();
        
        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        // Step 5: Verify the pop-up window and check that the products are reflected in it
        WebElement heading = driver.findElement(By.cssSelector(".page-title title"));
        
        if (heading.getText().equals("COMPARE PRODUCTS")) {
            System.out.println("Heading is correct!");
        } else {
            System.out.println("Heading is incorrect!");
        }
        
        WebElement sonyProduct = driver.findElement(By.xpath("//h2/a[contains(text(),'Sony Xperia')]"));
        WebElement iphoneProduct = driver.findElement(By.xpath("//h2/a[contains(text(),'Iphone')]"));
        
        if (sonyProduct.isDisplayed() && iphoneProduct.isDisplayed()) {
            System.out.println("Both products are reflected in the popup!");
        } else {
            System.out.println("One or both of the products are not reflected in the popup!");
        }

        // Capture screenshot at the end
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        
        try {
            FileUtils.copyFile(scrFile, new File("src/main/resources/screenshots/screenshot_tc04.png"));
            System.out.println("Screenshot captured!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot!");
        }

        // Close the Popup Windows
        driver.close();

        // Switch back to original browser (first window)
        String originalWindow = driver.getWindowHandle();
        driver.switchTo().window(originalWindow);

        // Close the browser
        driver.quit();
    }
}
