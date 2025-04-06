package Pages;

import Utilities.DataUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class HomePage {

    private final WebDriver driver;
    private final By menCategory = By.id("ui-id-5");
    private final By menTopsCategory = By.id("ui-id-17");
    private final By menTopsHoodiesSweatshirtsCategory = By.id("ui-id-20");
    private final By searchBar = By.cssSelector("input#search");
    private final By searchTitleForHeroHoodie = By.xpath("//*[contains(text(), 'Search results for: ') and @data-ui-id='page-title-wrapper']");
    private final By hoodiesAndSweatshirtsTitle = By.cssSelector("span[data-ui-id='page-title-wrapper']");




    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //TODO: Methods Section:

    public HomePage hoverOverMenCategory (){
        Utility.hoverOverElement(driver,menCategory);
        return this;
    }
    public HomePage hoverOverMenTopsCategory (){
        Utility.hoverOverElement(driver,menCategory);
        Utility.hoverOverElement(driver,menTopsCategory);
        return this;
    }
    public HomePage hoverOverMenTopsHoodiesSweatshirtsCategory (){
        Utility.hoverOverElement(driver,menCategory);
        Utility.hoverOverElement(driver,menTopsCategory);
        Utility.hoverOverElement(driver,menTopsHoodiesSweatshirtsCategory);
        return this;
    }
    public void clickOnMenTopsHoodiesSweatshirtsCategory (){
        Utility.clickingOnElement(driver,menTopsHoodiesSweatshirtsCategory);
    }
    public HomePage clickOnSearchBar (){
        Utility.clickingOnElement(driver,searchBar);
        return this;
    }
    public HomePage enterTheNameOfTheProduct (){
        Utility.sendData(driver,searchBar, DataUtils.getData("products","Hoodie"));
        return this;
    }
    public void pressEnter (){
        Utility.pressEnter(driver,searchBar);
        new SearchResultsPage(driver);
    }




    //TODO: Assertions Sections:

//    public Boolean verifyMenTopsHoodiesSweatshirtsCategoryPageIsOpened(String expectedValue) {
//        return Objects.equals(driver.getCurrentUrl(), expectedValue);
//    }
    public Boolean menTopsCategoryIsDisplayed() {
        return driver.findElement(menTopsCategory).isDisplayed();
    }
    public Boolean menTopsHoodiesSweatshirtsCategoryIsDisplayed() {
        return driver.findElement(menTopsHoodiesSweatshirtsCategory).isDisplayed();
    }
    public Boolean hoodiesAndSweatshirtsTitleIsDisplayed() {
        return driver.findElement(hoodiesAndSweatshirtsTitle).isDisplayed();
    }
    public Boolean searchTitleForHeroHoodieIsDisplayed() {
        return driver.findElement(searchTitleForHeroHoodie).isDisplayed();
    }





}
