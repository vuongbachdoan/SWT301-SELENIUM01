package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginForm {
    private WebDriver driver;
    private WebElement emailAddressField;
    private WebElement passwordField;

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        this.emailAddressField = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        this.passwordField = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
    }

    public void fillForm(String emailAddress, String password) {
        this.emailAddressField.sendKeys(emailAddress);
        this.passwordField.sendKeys(password);
    }
}
