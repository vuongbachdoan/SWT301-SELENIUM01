package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginForm {
    private WebDriver driver;
    private WebElement firstNameField;
    private WebElement middlenameField;
    private WebElement lastNameField;
    private WebElement emailAddressField;
    private WebElement passwordField;
    private WebElement confirmationField;

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        this.firstNameField = driver.findElement(By.xpath("//*[@id=\"firstname\"]"));
        this.middlenameField = driver.findElement(By.xpath("//*[@id=\"middlename\"]"));
        this.lastNameField = driver.findElement(By.xpath("//*[@id=\"lastname\"]"));
        this.emailAddressField = driver.findElement(By.xpath("//*[@id=\"email_address\"]"));
        this.passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        this.confirmationField = driver.findElement(By.xpath("//*[@id=\"confirmation\"]"));
    }

    public void fillForm(String firstName, String middlename, String lastName, String emailAddress, String password,
            String confirmation) {
        this.firstNameField.sendKeys(firstName);
        this.middlenameField.sendKeys(middlename);
        this.lastNameField.sendKeys(lastName);
        this.emailAddressField.sendKeys(emailAddress);
        this.passwordField.sendKeys(password);
        this.confirmationField.sendKeys(confirmation);
    }
}
