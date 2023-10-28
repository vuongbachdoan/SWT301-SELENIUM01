package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginForm {
    private WebDriver driver;
    private WebElement firstNameField;
    private WebElement lastNameField;
    private WebElement emailAddressField;
    private WebElement passwordField;
    private WebElement confirmationField;

    public LoginForm(WebDriver driver, String firstName, String lastName, String emailAddress, String password, String confirmation) {
        this.driver = driver;
        this.firstNameField = driver.findElement(By.id("firstname"));
        this.lastNameField = driver.findElement(By.id("lastname"));
        this.emailAddressField = driver.findElement(By.id("email_address"));
        this.passwordField = driver.findElement(By.id("password"));
        this.confirmationField = driver.findElement(By.id("confirmation"));

        fillForm(firstName, lastName, emailAddress, password, confirmation);
    }

    private void fillForm(String firstName, String lastName, String emailAddress, String password, String confirmation) {
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.emailAddressField.sendKeys(emailAddress);
        this.passwordField.sendKeys(password);
        this.confirmationField.sendKeys(confirmation);
    }
}
