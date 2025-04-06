package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.ProductPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class ProductTest {
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
    public void chooseHeroHoodieSize() {
        new ProductPage(getDriver()).SelectHeroHoodieMSize();
        LogsUtils.info("size is selected");
    }
    @Test
    public void chooseHeroHoodieColor() {
        new ProductPage(getDriver()).SelectHeroHoodieBlackColor();
        LogsUtils.info("color is selected");
    }
    @Test
    public void clickOnHeroHoodieAddToCartButton() {
        new ProductPage(getDriver()).clickOnHeroHoodieAddToCartButton();
        LogsUtils.info("product has been added to cart");
    }
    @Test
    public void clickOnCartIcon() {
        new ProductPage(getDriver()).clickOnCartIcon();
        LogsUtils.info("Cart is opened");
    }
    @Test
    public void clickOnCheckOutButton() {
        new ProductPage(getDriver()).clickOnCheckOutButton();
        LogsUtils.info("checkout page is opened");
    }



    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }


}
