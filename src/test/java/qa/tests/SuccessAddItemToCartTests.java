package qa.tests;

import theTest.theOneAndOnlyTest;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import classes.LoginClass;
import classes.ItemsClass;
import utils.CsvHelper;
import java.io.IOException;

public class SuccessAddItemToCartTests extends theOneAndOnlyTest {

    @DataProvider(name = "addItemsToCart")
    public static Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/addItemsToCart.csv");
    }

    @Test (dataProvider = "addItemsToCart")
    public void addItemToTheCart(String userName, String password, String productOne, String productTwo, String productThree, String productFour, String productFive){
        LoginClass loginClass = new LoginClass(driver);
        ItemsClass itemsClass = loginClass.login(userName, password);

        if (productOne.length() != 0) {
            itemsClass.addItemToTheCart(productOne);
            Assert.assertEquals(itemsClass.getItemsInTheCart(),1, "Because we have only one item so far");
        }
        if (productTwo.length() != 0) {
            itemsClass.addItemToTheCart(productTwo);
            Assert.assertEquals(itemsClass.getItemsInTheCart(),2, "Because we have two items so far");
        }
        if (productThree.length() != 0) {
            itemsClass.addItemToTheCart(productThree);
            Assert.assertEquals(itemsClass.getItemsInTheCart(),3, "Because we have three items so far");
        }
        if (productFour.length() != 0) {
            itemsClass.addItemToTheCart(productFour);
            Assert.assertEquals(itemsClass.getItemsInTheCart(),4, "Because we have four items so far");
        }
        if (productFive.length() != 0) {
            itemsClass.addItemToTheCart(productFive);
            Assert.assertEquals(itemsClass.getItemsInTheCart(),5, "Because we have five items so far");
        }

        if (productOne.length() != 0) {
            itemsClass.removeItemFromTheCart(productOne);
        }
        if (productTwo.length() != 0) {
            itemsClass.removeItemFromTheCart(productTwo);
        }
        if (productThree.length() != 0) {
            itemsClass.removeItemFromTheCart(productThree);
        }
        if (productFour.length() != 0) {
            itemsClass.removeItemFromTheCart(productFour);
        }
        if (productFive.length() != 0) {
            itemsClass.removeItemFromTheCart(productFive);
        }

        Assert.assertEquals(itemsClass.getItemsInTheCart(),0, "Shopping cart should be empty");
    }
}
