/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/

package element;

import driver.driverFactory;
import element.ElementController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Testcase01 {
    public static void main(String[] args) {
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Step 2: Verify Title of the page
        String title = driver.getTitle();
        System.out.println("Page Title is: " + title);

        // Step 3: Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 4: In the list of all mobile , select SORT BY -> dropdown as name
        Select dropdown = new Select(driver.findElement(By.cssSelector("select[title='Sort By']")));
        dropdown.selectByVisibleText("Name");

        // Step 5: Verify all products are sorted by name
        List<WebElement> products = driver.findElements(By.cssSelector(".product-name"));
        for (WebElement product : products) {
            System.out.println(product.getText());
        }

        // Close the browser
        driver.quit();
    }
}
