package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BillingForm {
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

    public BillingForm(WebDriver driver) {
        this.driver = driver;
        this.firstNameField = driver.findElement(By.xpath("//*[@id=\"billing:firstname\"]"));
        this.middlenameField = driver.findElement(By.xpath("//*[@id=\"billing:middlename\"]"));
        this.lastNameField = driver.findElement(By.xpath("//*[@id=\"billing:lastname\"]"));       
        this.companyField = driver.findElement(By.xpath("//*[@id=\"billing:company\"]"));
        this.address1Field = driver.findElement(By.xpath("//*[@id=\"billing:street1\"]"));
        this.address2Field = driver.findElement(By.xpath("//*[@id=\"billing:street2\"]"));        
        this.cityField = driver.findElement(By.xpath("//*[@id=\"billing:city\"]"));
        this.countryField = driver.findElement(By.xpath("//*[@id=\"billing:country_id\"]"));
        this.stateOrRegionField = driver.findElement(By.xpath("//*[@id=\"billing:region_id\"]"));
        this.zipCodeField = driver.findElement(By.xpath("//*[@id=\"postcode\"]"));
        this.telephoneField = driver.findElement(By.xpath("//*[@id=\"billing:telephone\"]"));
        this.faxField = driver.findElement(By.xpath("//*[@id=\"billing:fax\"]"));
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
