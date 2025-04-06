package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private final By HeroHoodieMSize = By.cssSelector("div[option-id='168']");
    private final By HeroHoodieAddToCartButton = By.id("product-addtocart-button");
    private final By HeroHoodieBlackColor = By.cssSelector("div[option-id='49']");
    private final By CartIcon =By.xpath("//a[@class='action showcart']");
    private final By CheckOutButton =By.id("top-cart-btn-checkout");
    private final By cartCounterLabel =By.xpath("(//span[@class='counter qty'])[1]");
    private WebDriver driver;
    private final By checkoutBanner = By.xpath("(//span[@data-bind='i18n: item.title, click: $parent.navigateTo'])[2]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    //TODO: Methods Section:
    public void SelectHeroHoodieMSize (){
        Utility.scrolling(driver,HeroHoodieMSize);
        Utility.hoverOverElement(driver,HeroHoodieMSize);
        Utility.clickingOnElement(driver,HeroHoodieMSize);
    }
    public void SelectHeroHoodieBlackColor (){
        Utility.scrolling(driver,HeroHoodieBlackColor);
        Utility.hoverOverElement(driver,HeroHoodieBlackColor);
        Utility.clickingOnElement(driver,HeroHoodieBlackColor);
    }
    public void clickOnHeroHoodieAddToCartButton (){
        Utility.scrolling(driver,HeroHoodieAddToCartButton);
        Utility.hoverOverElement(driver,HeroHoodieAddToCartButton);
        Utility.clickingOnElement(driver,HeroHoodieAddToCartButton);
    }
    public void clickOnCartIcon (){
        Utility.hoverOverElement(driver,CartIcon);
        Utility.clickingOnElement(driver,CartIcon);
    }
    public void clickOnCheckOutButton (){
        Utility.scrolling(driver,CheckOutButton);
        Utility.hoverOverElement(driver,CheckOutButton);
        Utility.clickingOnElement(driver,CheckOutButton);
        new CheckOutPage(driver);
    }
    //TODO: Assertions:

    public Boolean verifyCartCounterLabelIsDisplayed() {
        Utility.waitForVisibility(driver,cartCounterLabel);
        return driver.findElement(cartCounterLabel).isDisplayed();
    }

    public Boolean verifyUserRedirectedToCheckoutPage(String expectedValue) {
        Utility.waitForPageToLoad(driver,10);
        return driver.getCurrentUrl().equals(expectedValue);
    }
    public Boolean verifyCheckoutBannerIsDisplayed() {
        return driver.findElement(checkoutBanner).isDisplayed();
    }




}
