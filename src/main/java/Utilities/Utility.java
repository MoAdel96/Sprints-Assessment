package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;

public class Utility {

    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";



    public static void clickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void sendData(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }
    public static void hoverOverElement(WebDriver driver, By locator) {
        // Wait until the element is visible and then perform hover action
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        new Actions(driver)
                .moveToElement(driver.findElement(locator))
                .perform(); // Hover over the element


    }


    public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }
    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Wait until a specific element is visible
    public static void  waitForVisibility(WebDriver driver, By locator) {
         new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void waitUntilElementIsActive(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void clickWhenReady(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
    public static void waitForLoaderToDisappear(WebDriver driver, By loaderLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
    }




    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }


    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());
    }


    public static void takeScreenShot(WebDriver driver, String screenshotName) {
        try {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            // Attach the screenshot to Allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);


    }

    public static void takeFullScreenShot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL).highlight(findWebElement(driver, locator)).save(SCREENSHOTS_PATH);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());

        }

    }


    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound);

    }

    public static Set<Integer> generateUniqueNumber(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numberOfProductsNeeded) {
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;


    }

    public static Boolean verifyURl(WebDriver driver, String expectedURl) {
        try {
            Utility.generalWait(driver).until(ExpectedConditions.urlToBe(expectedURl));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        // Check if the folder exists and is not empty
        if (files == null || files.length == 0) {
            return null;
        }

        // Sort the files by last modified date in descending order
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

    // Method to add cookies to the browser
    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

    // Optional: Save cookies if you want to persist them and reuse later
    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static String[] getUserData(int userId) {
        try {
            // URL of the JSONPlaceholder API for a specific user
            String apiUrl = "https://jsonplaceholder.typicode.com/users/" + userId;

            // Create a URL object
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the HTTP method to GET
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the response JSON using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.toString());

            // Extract First Name, Last Name, and Zip/Postal Code
            String firstName = jsonNode.get("name").asText().split(" ")[0];  // Extract First Name
            String lastName = jsonNode.get("name").asText().split(" ")[1];   // Extract Last Name
            String postalCode = jsonNode.get("address").get("zipcode").asText(); // Extract Zip/Postal Code

            // Return the data as an array
            return new String[]{firstName, lastName, postalCode};

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //TODO: keyboard Buttons




    public static void pressKey(WebDriver driver, By locator, Keys key) {
        try {
            // Find the element based on the locator
            WebElement element = driver.findElement(locator);




            // Use Actions to send the key press
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().sendKeys(key).build().perform();



        } catch (Exception e) {


            throw new RuntimeException("Error occurred while pressing key on element: " + locator, e);
        }
    }

    /**
     * Overloaded method for pressing Enter key by default.
     * @param driver The WebDriver instance.
     * @param locator The locator of the element (e.g., search bar).
     */
    public static void pressEnter(WebDriver driver, By locator) {
        pressKey(driver, locator, Keys.ENTER);
    }

    // Additional method to handle other keys, can be extended for other use cases
    public static void pressTab(WebDriver driver, By locator) {
        pressKey(driver, locator, Keys.TAB);
    }

    public static void pressEscape(WebDriver driver, By locator) {
        pressKey(driver, locator, Keys.ESCAPE);
    }



}


