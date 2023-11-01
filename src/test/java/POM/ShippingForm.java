package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ShippingForm {
    private WebDriver driver;
    private WebElement firstNameField;
    private WebElement middlenameField;
    private WebElement lastNameField;
    private WebElement companyField;
    private WebElement address1Field;
    private WebElement address2Field;
    private WebElement cityField; 
    private WebElement countryField;    
    private WebElement stateOrRegionField;
    private WebElement zipCodeField;
    private WebElement telephoneField;
    private WebElement faxField;

    public ShippingForm(WebDriver driver) {
        this.driver = driver;
        this.firstNameField = driver.findElement(By.xpath("//*[@id=\"shipping:firstname\"]"));
        this.middlenameField = driver.findElement(By.xpath("//*[@id=\"shipping:middlename\"]"));
        this.lastNameField = driver.findElement(By.xpath("//*[@id=\"shipping:lastname\"]"));       
        this.companyField = driver.findElement(By.xpath("//*[@id=\"shipping:company\"]"));
        this.address1Field = driver.findElement(By.xpath("//*[@id=\"shipping:street1\"]"));
        this.address2Field = driver.findElement(By.xpath("//*[@id=\"shipping:street2\"]"));        
        this.cityField = driver.findElement(By.xpath("//*[@id=\"shipping:city\"]"));
        this.countryField = driver.findElement(By.xpath("//*[@id=\"shipping:country_id\"]"));
        this.stateOrRegionField = driver.findElement(By.xpath("//*[@id=\"shipping:region_id\"]"));
        this.zipCodeField = driver.findElement(By.xpath("//*[@id=\"postcode\"]"));
        this.telephoneField = driver.findElement(By.xpath("//*[@id=\"shipping:telephone\"]"));
        this.faxField = driver.findElement(By.xpath("//*[@id=\"shipping:fax\"]"));
    }

    public void fillForm(String firstName, String middlename, String lastName, String company, String address1, String address2, String city, String country, String stateOrRegion, String zipCode, String telephone, String fax) {
        this.firstNameField.sendKeys(firstName);
        this.middlenameField.sendKeys(middlename);       
        this.lastNameField.sendKeys(lastName);
        this.companyField.sendKeys(company);
        this.address1Field.sendKeys(address1);
        this.address2Field.sendKeys(address2);
        this.cityField.sendKeys(city);
        this.telephoneField.sendKeys(telephone);
        this.faxField.sendKeys(fax);
        (new Select(this.countryField)).selectByVisibleText(country);
        (new Select(this.stateOrRegionField)).selectByVisibleText(stateOrRegion);
        this.zipCodeField.sendKeys(zipCode);
    }
}
