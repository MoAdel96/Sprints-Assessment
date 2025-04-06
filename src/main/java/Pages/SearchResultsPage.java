package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
    private final By HeroHoodieCard = By.xpath("//img[@alt='Hero Hoodie']");
    private final By HeroHoodieMSize = By.xpath("//li[6]/div/div/div[2]/div[1]/div/div[3]");
    private final By HeroHoodieAddToCartButton = By.xpath("//li[6]/div/div/div[3]/div/div[1]/form/button");
    private final By HeroHoodieBlackColor = By.xpath("(//div[@id='option-label-color-93-item-49'])[1]");
    private final By CartIcon = By.cssSelector("a[class='action showcart active']");
    private final By CheckOutButton = By.id("top-cart-btn-checkout");
    private final By cartCounterLabel = By.className("counter-label");


    private WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    //TODO: Methods Section:
    public void openHeroHoodiePage() {
        Utility.scrolling(driver, HeroHoodieCard);
        Utility.clickingOnElement(driver, HeroHoodieCard);
        new ProductPage(driver);
    }


    public SearchResultsPage hoverOverHeroHoodieCard() {
        Utility.hoverOverElement(driver, HeroHoodieCard);
        Utility.scrolling(driver, HeroHoodieMSize);
        return this;
    }

    public void SelectHeroHoodieMSize() {
        Utility.scrolling(driver, HeroHoodieMSize);
        Utility.hoverOverElement(driver, HeroHoodieMSize);
        Utility.clickingOnElement(driver, HeroHoodieMSize);
    }

    public void SelectHeroHoodieBlackColor() {
        Utility.scrolling(driver, HeroHoodieBlackColor);
        Utility.hoverOverElement(driver, HeroHoodieBlackColor);
        Utility.clickingOnElement(driver, HeroHoodieBlackColor);
    }

    public void clickOnHeroHoodieAddToCartButton() {
        Utility.scrolling(driver, HeroHoodieAddToCartButton);
        Utility.hoverOverElement(driver, HeroHoodieAddToCartButton);
        Utility.clickingOnElement(driver, HeroHoodieAddToCartButton);
    }

    public void clickOnCartIcon() {
        Utility.hoverOverElement(driver, CartIcon);
        Utility.clickingOnElement(driver, CartIcon);
    }

    public void clickOnCheckOutButton() {
        Utility.scrolling(driver, CheckOutButton);
        Utility.hoverOverElement(driver, CheckOutButton);
        Utility.clickingOnElement(driver, CheckOutButton);
        new CheckOutPage(driver);
    }
    //TODO: Assertions:

    public Boolean verifyCartCounterLabelIsDisplayed() {
        return driver.findElement(cartCounterLabel).isDisplayed();
    }

    public Boolean verifyUserRedirectedToCheckoutPage(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }
    public Boolean verifyUserRedirectedToProductPage(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }


}
