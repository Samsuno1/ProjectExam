package classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginClass {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(css = "[placeholder=Password]")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginBtn;

    public LoginClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ItemsClass login(String username, String password){
        userNameInput.click();
        userNameInput.sendKeys(username);

        passwordInput.click();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new ItemsClass(driver);
    }

    public void tryToLogin(String username, String password){
        userNameInput.click();
        userNameInput.sendKeys(username);

        passwordInput.click();
        passwordInput.sendKeys(password);

        loginBtn.click();
    }

    public boolean isErrorMessageDisplayed(String errorMessage) {
        WebElement errorLogin = driver.findElement(By.xpath("//*[text()='Rip: "+errorMessage+"']"));
        return errorLogin.isDisplayed();
    }
}

