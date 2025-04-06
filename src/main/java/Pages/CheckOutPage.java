package Pages;

import Utilities.DataUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
    private final WebDriver driver;
    private final By Email = By.xpath("(//input[@id='customer-email'])[1]");
    private final By FirstName = By.cssSelector("input[name='firstname']");
    private final By SecondName = By.cssSelector("input[name='lastname']");
    private final By Company = By.cssSelector("input[name='company']");
    private final By StreetAddress = By.cssSelector("input[name='street[0]']");
    private final By city = By.cssSelector("input[name='city']");
    private final By StateDDL = By.cssSelector("input[name='region_id']");
    private final By State = By.cssSelector("input[name='region']");
    private final By zip = By.cssSelector("input[name='postcode']");
    private final By countryDDL = By.xpath("//select[@name='country_id']");
    private final By PhoneNum = By.cssSelector("input[name='telephone']");
    private final By fixedShippingMethod = By.cssSelector("input.radio[name='ko_unique_2']\n");
    private final By nextButton = By.cssSelector("button[data-role='opc-continue']");
    private final By placeOrderButton = By.cssSelector("button[title='Place Order']");
    private final By paymentMethodTitle = By.xpath("(//div[@class='step-title'])[3]");
    private final By successMsgTitle = By.cssSelector("span[data-ui-id='page-title-wrapper']");
    private final By loadingIcon = By.cssSelector("img[alt='Loading...']");


    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }


    //TODO: Methods Section:
    public void enterEmail() {
        Utility.sendData(driver, Email, DataUtils.getData("UserInfo", "Email"));
    }

    public void enterFirstName() {
        Utility.sendData(driver, FirstName, DataUtils.getData("UserInfo", "FirstName"));
    }

    public void enterSecondName() {
        Utility.sendData(driver, SecondName, DataUtils.getData("UserInfo", "SecondName"));
    }

    public void enterCompany() {
        Utility.sendData(driver, Company, DataUtils.getData("UserInfo", "company"));
    }

    public void enterStreetAddress() {
        Utility.sendData(driver, StreetAddress, DataUtils.getData("UserInfo", "streetAddress"));
    }

    public void enterCity() {
        Utility.sendData(driver, city, DataUtils.getData("UserInfo", "city"));
    }

    public void enterZip() {
        Utility.sendData(driver, zip, DataUtils.getData("UserInfo", "zip"));
    }

    public void enterPhoneNum() {
        Utility.sendData(driver, PhoneNum, DataUtils.getData("UserInfo", "PhoneNum"));
    }

    public void selectCountry() {
        Utility.selectingFromDropDown(driver, countryDDL, "Egypt");
    }

    public CheckOutPage selectStateDDL() {
        Utility.selectingFromDropDown(driver, StateDDL, "Alabama");
        return this;
    }
    public void selectState() {
        Utility.sendData(driver, State, DataUtils.getData("UserInfo", "State"));
    }


    public void chooseShippingMethod() {
        Utility.clickingOnElement(driver,fixedShippingMethod);
    }
    public void clickOnNextButton() {
        Utility.clickingOnElement(driver,nextButton);
    }
     public void clickOnPlaceOrderButton() {
        Utility.waitForLoaderToDisappear(driver,loadingIcon);
        Utility.clickingOnElement(driver,placeOrderButton);
     }



    //TODO: Assertions:
    public Boolean verifyUserRedirectedToPaymentPage(String expectedValue) {

        return driver.getCurrentUrl().equals(expectedValue);
    }
    public Boolean verifyUserRedirectedToSuccessPage(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }
    public Boolean verifyPaymentMethodTitleIsDisplayed() {
        return driver.findElement(paymentMethodTitle).isDisplayed();
    }
    public Boolean verifySuccessMsgTitleTitleIsDisplayed() {
        return driver.findElement(successMsgTitle).isDisplayed();
    }






}
