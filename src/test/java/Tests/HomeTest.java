package Tests;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.HomePage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class HomeTest {
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
    public void hoverOverMenCategory() {
        new HomePage(getDriver()).hoverOverMenCategory();
        LogsUtils.info("the hover is done");
        Assert.assertTrue(new HomePage(getDriver()).menTopsCategoryIsDisplayed());
    }
    @Test
    public void hoverOverMenTopsCategory() {
        new HomePage(getDriver()).hoverOverMenTopsCategory();
        LogsUtils.info("the hover is done");
        Assert.assertTrue(new HomePage(getDriver()).menTopsHoodiesSweatshirtsCategoryIsDisplayed());
    }
     @Test
    public void clickOnMenTopsHoodiesSweatshirtsCategory() {
        new HomePage(getDriver()).clickOnMenTopsHoodiesSweatshirtsCategory();
         LogsUtils.info("the category page is opened");
        Assert.assertTrue(new HomePage(getDriver()).hoodiesAndSweatshirtsTitleIsDisplayed());
    }

    @Test(testName = "Navigate to Hoodies Section", groups = {"Navigation"})
    @Description("Verify that the user can successfully navigate through categories to reach the Hoodies & Sweatshirts section.")
    @Owner("Mohamed Adel")
    @Severity(CRITICAL)
    @AllureId("1")
    @Epic("Categories")
    @Feature("Navigate to category")
    @Story("Categories")
    public void navigateThroughCategoriesToReachTheHoodiesAndSweatshirtsSection() {
        new HomePage(getDriver()).hoverOverMenCategory()
                .hoverOverMenTopsCategory().hoverOverMenTopsHoodiesSweatshirtsCategory()
                .clickOnMenTopsHoodiesSweatshirtsCategory();
        LogsUtils.info("the category page is opened");
        Assert.assertTrue(new HomePage(getDriver()).hoodiesAndSweatshirtsTitleIsDisplayed());
    }

    @Test
    public void clickOnSearchBar() {
        new HomePage(getDriver()).clickOnSearchBar();
        LogsUtils.info("search bar is active");

    }
    @Test
    public void enterTheNameOfTheProduct() {
        new HomePage(getDriver()).enterTheNameOfTheProduct();
        LogsUtils.info("entering the name of the product is done");

    }
    @Test
    public void pressEnter() {
        new HomePage(getDriver()).pressEnter();
        LogsUtils.info("the search is done");

    }
    @Test(testName = "Product Search Functionality", groups = {"Search"})
    @Description("VEnsure that the product search bar retrieves relevant hoodie products.")
    @Owner("Mohamed Adel")
    @Severity(CRITICAL)
    @AllureId("2")
    @Epic("Search Bar")
    @Feature("Search category")
    @Story("Search Bar")
    public void theProductSearchBarRetrievesRelevantHoodieProducts() {
        new HomePage(getDriver()).clickOnSearchBar()
                        .enterTheNameOfTheProduct().pressEnter();
        LogsUtils.info("the search is done");
        Assert.assertTrue(new HomePage(getDriver()).searchTitleForHeroHoodieIsDisplayed());
    }







    @AfterMethod
    public void quit() throws IOException {
        quitDriver();
    }

}
