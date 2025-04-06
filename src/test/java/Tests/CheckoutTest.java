package Tests;

import Pages.CheckOutPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;

import io.qameta.allure.*;
import jdk.jfr.Description;
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
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class CheckoutTest {

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Browser was opened");
        getDriver().get(DataUtils.getData("environmentProperties", "HOME_URL"));
        LogsUtils.info(" browser is redirected to the url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void enterEmail() {
        new CheckOutPage(getDriver()).enterEmail();
        LogsUtils.info("email is entered");
    }

    @Test
    public void enterFirstName() {
        new CheckOutPage(getDriver()).enterFirstName();
        LogsUtils.info("First Name is entered");
    }

    @Test
    public void enterSecondName() {
        new CheckOutPage(getDriver()).enterSecondName();
        LogsUtils.info("Second Name is entered");
    }

    @Test
    public void enterCompany() {
        new CheckOutPage(getDriver()).enterCompany();
        LogsUtils.info("Company is entered");
    }

    @Test
    public void enterStreetAddress() {
        new CheckOutPage(getDriver()).enterStreetAddress();
        LogsUtils.info("Street Address is entered");
    }

    @Test
    public void enterCity() {
        new CheckOutPage(getDriver()).enterCity();
        LogsUtils.info("City is entered");
    }

    @Test
    public void enterZip() {
        new CheckOutPage(getDriver()).enterZip();
        LogsUtils.info("Zip is entered");
    }

    @Test
    public void enterPhoneNum() {
        new CheckOutPage(getDriver()).enterPhoneNum();
        LogsUtils.info("PhoneNum is entered");
    }

    @Test
    public void selectCountry() {
        new CheckOutPage(getDriver()).selectCountry();
        LogsUtils.info("Country is Selected");
    }

    @Test
    public void selectState() {
        new CheckOutPage(getDriver()).selectState();
        LogsUtils.info("State is Selected");
    }

    @Test
    public void chooseShippingMethod() {
        new CheckOutPage(getDriver()).chooseShippingMethod();
        LogsUtils.info("choosing shipping method is done");
    }

    @Test
    public void clickOnNextButton() {
        new CheckOutPage(getDriver()).clickOnNextButton();
        LogsUtils.info("redirected to the next page");
    }

    @Test
    public void clickOnPlaceOrderButton() {
        new CheckOutPage(getDriver()).clickOnPlaceOrderButton();
        LogsUtils.info("redirected to the success page");
    }

    @Test(testName = "the checkout flow", groups = {"CheckOut"})
    @Description("Validate that the checkout flow works correctly from cart to order placement ")
    @Owner("Mohamed Adel")
    @Severity(CRITICAL)
    @AllureId("3")
    @Epic("Checkout")
    @Feature("checkout flow")
    @Story("Checkout process")
    public void ValidateThatTheCheckoutFlowWorksCorrectlyFromCartToOrderPlacement() {

        // Search FLow
        new HomePage(getDriver()).clickOnSearchBar();
        LogsUtils.info("search bar is active");
        new HomePage(getDriver()).enterTheNameOfTheProduct().pressEnter();
        LogsUtils.info("entering the name of the product is done");
        Assert.assertTrue(new HomePage(getDriver()).searchTitleForHeroHoodieIsDisplayed());
        new SearchResultsPage(getDriver()).hoverOverHeroHoodieCard().openHeroHoodiePage();
        LogsUtils.info("product page is opened");
        Assert.assertTrue(new SearchResultsPage(getDriver())
                .verifyUserRedirectedToProductPage(DataUtils.getData("environmentProperties", "HeroHoodie_URL")));


        //Product Page Flow
        new ProductPage(getDriver()).SelectHeroHoodieMSize();
        LogsUtils.info("size is selected");
        new ProductPage(getDriver()).SelectHeroHoodieBlackColor();
        LogsUtils.info("color is selected");
        new ProductPage(getDriver()).clickOnHeroHoodieAddToCartButton();
        LogsUtils.info("product has been added to cart");
        Assert.assertTrue(new ProductPage(getDriver()).verifyCartCounterLabelIsDisplayed());
        //Cart Flow
        new ProductPage(getDriver()).clickOnCartIcon();
        LogsUtils.info("Cart is opened");
        new ProductPage(getDriver()).clickOnCheckOutButton();
        LogsUtils.info("checkout page is opened");
        Assert.assertTrue(new ProductPage(getDriver()).verifyCheckoutBannerIsDisplayed());

        //Checkout Flow
        new CheckOutPage(getDriver()).enterEmail();
        LogsUtils.info("email is entered");
        new CheckOutPage(getDriver()).enterFirstName();
        LogsUtils.info("First Name is entered");
        new CheckOutPage(getDriver()).enterSecondName();
        LogsUtils.info("Second Name is entered");
        new CheckOutPage(getDriver()).enterCompany();
        LogsUtils.info("Company is entered");
        new CheckOutPage(getDriver()).enterStreetAddress();
        LogsUtils.info("Street Address is entered");
        new CheckOutPage(getDriver()).enterCity();
        LogsUtils.info("City is entered");
        new CheckOutPage(getDriver()).enterZip();
        LogsUtils.info("Zip is entered");
        new CheckOutPage(getDriver()).enterPhoneNum();
        LogsUtils.info("PhoneNum is entered");
        new CheckOutPage(getDriver()).selectCountry();
        LogsUtils.info("Country is Selected");
        new CheckOutPage(getDriver()).selectState();
        LogsUtils.info("State is Selected");
        new CheckOutPage(getDriver()).chooseShippingMethod();
        LogsUtils.info("choosing shipping method is done");
        new CheckOutPage(getDriver()).clickOnNextButton();
        LogsUtils.info("redirected to the payment page");
        Assert.assertTrue(new CheckOutPage(getDriver()).verifyPaymentMethodTitleIsDisplayed());
        new CheckOutPage(getDriver()).clickOnPlaceOrderButton();
        LogsUtils.info("redirected to the success page");
        Assert.assertTrue(new CheckOutPage(getDriver()).verifySuccessMsgTitleTitleIsDisplayed());
    }


    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }
}
