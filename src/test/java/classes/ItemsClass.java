package classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ItemsClass {

    protected WebDriver driver;
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-sauce-labs-%s']";
    private static final String REMOVE_FROM_CART_LOCATOR = "//button[@id='remove-sauce-labs-%s']";

    @FindBy (className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy (className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;

    public ItemsClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToTheCart(String productName){
        String xpathOfElementToBeAdded = String.format(ADD_TO_CART_LOCATOR, productName);
        WebElement addToCartButton = driver.findElement(By.xpath(xpathOfElementToBeAdded));
        addToCartButton.click();
    }

    public boolean removeItemFromTheCart(String productName){
        String xpathOfElementToBeRemoved = String.format(REMOVE_FROM_CART_LOCATOR, productName);
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));

        WebElement removeButton = driver.findElement(By.xpath(xpathOfElementToBeRemoved));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeButton));

        if (removeButton.getText().equals("Remove")) {
            removeButton.click();
            return true;
        }

        return false;
    }

    public int getItemsInTheCart(){
        if (driver.findElements(By.className("shopping_cart_badge")).isEmpty()) {
            return 0;
        }

        return Integer.parseInt(shoppingCartCounter.getText());
    }

    public boolean isHamburgerMenuDisplayed() {
        WebElement userAllPagesButton = driver.findElement(By.id("react-burger-menu-btn"));
        return userAllPagesButton.isDisplayed();
    }

    public CartClass openCartPage(){
        shoppingCartLink.click();

        return new CartClass(driver);
    }

    public boolean areProductsSortedByPriceAsc() {
        List<WebElement> priceHolders = driver.findElements(By.className("inventory_item_price"));

        Double price = 0.0;
        boolean areSorted = true;
        for (int i = 0; i < priceHolders.size(); i++) {
            String productPriceStr = priceHolders.get(i).getText().replace("$","");

            // convert into Double
            double productPrice = Double.parseDouble(productPriceStr);

            if (productPrice < price) {
                areSorted = false;
                break;
            }

            price = productPrice;
        }

        return areSorted;
    }

    public void sortByPriceAsc() {
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1)) // how often it will be checked for the presence of the element
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement lowToHighPrice = driver.findElement(By.cssSelector("[value=low]"));

        fluentWait.until(ExpectedConditions.elementToBeClickable(lowToHighPrice));
        lowToHighPrice.click();
    }
}

