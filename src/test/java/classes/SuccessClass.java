package classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SuccessClass {

    protected WebDriver driver;

    public SuccessClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isSuccessMessageDisplayed() {
        WebElement successMessage = driver.findElement(By.xpath("//*[text()='Thank you for ordering!']"));
        return successMessage.isDisplayed();
    }
}

