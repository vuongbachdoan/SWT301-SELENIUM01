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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testcase02 {
    public static void main(String[] args) {
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 3: In the list of all mobile, read the cost of Sony Xperia mobile (which is $100)
        WebElement sonyXperiaPrice = driver.findElement(By.xpath("//span[contains(text(),'$100.00')]"));
        String listPrice = sonyXperiaPrice.getText();

        // Step 4: Click on Sony Xperia mobile
        WebElement sonyXperiaLink = driver.findElement(By.linkText("Sony Xperia"));
        sonyXperiaLink.click();

        // Step 5: Read the Sony Xperia mobile from detail page.
        WebElement detailPriceElement = driver.findElement(By.cssSelector(".price-info .price"));
        String detailPrice = detailPriceElement.getText();

        // Step 6: Compare Product value in list and details page should be equal ($100).
        if (listPrice.equals(detailPrice)) {
            System.out.println("The prices match!");
        } else {
            System.out.println("The prices do not match!");
        }

        // Close the browser
        driver.quit();
    }
}
