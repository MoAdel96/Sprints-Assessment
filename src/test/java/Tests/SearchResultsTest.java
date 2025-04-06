package Tests;


import Pages.SearchResultsPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class SearchResultsTest {

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Browser was opened");
        //getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
        getDriver().get(DataUtils.getData("environmentProperties","HOME_URL"));
        LogsUtils.info(" browser is redirected to the url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void openHeroHoodiePage() {
        new SearchResultsPage(getDriver()).hoverOverHeroHoodieCard().openHeroHoodiePage();
        LogsUtils.info("product page is opened");
        Assert.assertTrue(new SearchResultsPage(getDriver())
                .verifyUserRedirectedToProductPage(DataUtils.getData("environmentProperties","HeroHoodie_URL")));
    }

    @Test
    public void chooseHeroHoodieSize() {
        new SearchResultsPage(getDriver()).hoverOverHeroHoodieCard().SelectHeroHoodieMSize();
        LogsUtils.info("size is selected");
    }
    @Test
    public void chooseHeroHoodieColor() {
        new SearchResultsPage(getDriver()).hoverOverHeroHoodieCard().SelectHeroHoodieBlackColor();
        LogsUtils.info("color is selected");
    }
     @Test
    public void clickOnHeroHoodieAddToCartButton() {
        new SearchResultsPage(getDriver()).hoverOverHeroHoodieCard().clickOnHeroHoodieAddToCartButton();
        LogsUtils.info("product has been added to cart");
         Assert.assertTrue(new SearchResultsPage(getDriver()).verifyCartCounterLabelIsDisplayed());
    }
     @Test
    public void clickOnCartIcon() {
        new SearchResultsPage(getDriver()).clickOnCartIcon();
        LogsUtils.info("Cart is opened");
    }
     @Test
    public void clickOnCheckOutButton() {
        new SearchResultsPage(getDriver()).clickOnCheckOutButton();
        LogsUtils.info("checkout page is opened");
         Assert.assertTrue(new SearchResultsPage(getDriver())
                 .verifyUserRedirectedToCheckoutPage(DataUtils.getData("environmentProperties","CHECKOUT_URL")));
    }









    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }
}
