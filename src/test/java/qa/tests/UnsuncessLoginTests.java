package qa.tests;

import theTest.theOneAndOnlyTest;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import classes.LoginClass;
import utils.CsvHelper;
import java.io.IOException;

public class UnsuncessLoginTests extends theOneAndOnlyTest {
    @DataProvider(name = "unsuccessfulLogins")
    public static Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/unsuccessfulLogins.csv");
    }

    @Test (dataProvider = "unsuccessfulLogins")
    public void UnsuccessfulLoginTest(String userName, String password){
        LoginClass loginClass = new LoginClass(driver);
        loginClass.tryToLogin(userName, password);

        String errorMessage = "Username and password do not match any user in this service";
        if (userName.length() == 0) {
            errorMessage = "Username is required";
        } else if (password.length() == 0) {
            errorMessage = "Password is required";
        }

        Assert.assertTrue(loginClass.isErrorMessageDisplayed(errorMessage));
    }
}

