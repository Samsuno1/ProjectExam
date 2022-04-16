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

public class SuccessLoginTests extends theOneAndOnlyTest {
    @DataProvider(name = "successfulLogins")
    public static Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/successfulLogins.csv");
    }

    @Test (dataProvider = "successfulLogins")
    public void SuccessfulLogin(String userName, String password) {
        LoginClass loginClass = new LoginClass(driver);
        ItemsClass itemsClass = loginClass.login(userName, password);

        Assert.assertTrue(itemsClass.isHamburgerMenuDisplayed(), "This shall be visible after successful login.");
    }
}