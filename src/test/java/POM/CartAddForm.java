package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CartAddForm {
    // Enter general shipping country, state/province and zip for the shipping cost
    // estimate
    private WebDriver driver;
    private WebElement countryField;    
    private WebElement stateOrRegionField;
    private WebElement zipCodeField;

    public CartAddForm(WebDriver driver) {
        this.driver = driver;
        this.countryField = driver.findElement(By.xpath("//*[@id=\"country\"]"));
        this.stateOrRegionField = driver.findElement(By.xpath("//*[@id=\"region_id\"]"));        
        this.zipCodeField = driver.findElement(By.xpath("//*[@id=\"postcode\"]"));
    }

    public void fillForm(String country, String stateOrRegion, String zipCode) {
        (new Select(this.countryField)).selectByVisibleText(country);        
        (new Select(this.stateOrRegionField)).selectByVisibleText(stateOrRegion);
        this.zipCodeField.sendKeys(zipCode);
    }
}
