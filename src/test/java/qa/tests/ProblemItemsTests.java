package qa.tests;

import theTest.theOneAndOnlyTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import classes.LoginClass;
import classes.ItemsClass;

public class ProblemItemsTests extends theOneAndOnlyTest {

    @Test
    public void selectDifferentOrderStandardUser() {
        LoginClass loginClass = new LoginClass(driver);
        ItemsClass itemsClass = loginClass.login("standard_user", "secret_sauce");

        Assert.assertFalse(itemsClass.areProductsSortedByPriceAsc(), "Products should not be sorted by price ASC");

        itemsClass.sortByPriceAsc();

        Assert.assertTrue(itemsClass.areProductsSortedByPriceAsc(), "Products should be sorted by price ASC");
    }

    @Test
    public void selectDifferentOrderProblemUser() {
        LoginClass loginClass = new LoginClass(driver);
        ItemsClass itemsClass = loginClass.login("problem_user", "secret_sauce");

        Assert.assertFalse(itemsClass.areProductsSortedByPriceAsc(), "Products should not be sorted by price ASC");

        itemsClass.sortByPriceAsc();

        // This will fail
        Assert.assertTrue(itemsClass.areProductsSortedByPriceAsc(), "Products should be sorted by price ASC");
    }
}


