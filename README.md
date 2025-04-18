# E-Commerce Website Automation Testing with Java Selenium, TestNG, POM, and Maven

This project demonstrates automation testing for an e-commerce website, using Java with Selenium WebDriver, TestNG, Maven, and Page Object Model (POM) design pattern. The project tests the functionality of browsing categories, searching for products, and completing a checkout process on the website.

## Prerequisites

Before running the tests, ensure you have the following installed on your machine:

- Java 8 or above
- Maven
- Edge WebDriver
- IDE (e.g., IntelliJ IDEA or Eclipse)

## URL to Test
The website being tested is:  
[https://magento.softwaretestingboard.com/](https://magento.softwaretestingboard.com/)

## Test Cases

### Test Case 1: Verify Category and Product Browsing
- **Test Case ID:** TC_01
- **Test Scenario:** Verify that the user can successfully navigate through categories to reach the Hoodies & Sweatshirts section.
- **Preconditions:** User is on the homepage.
- **Steps:**
  1. Open Edge browser.
  2. Go to [https://magento.softwaretestingboard.com/](https://magento.softwaretestingboard.com/).
  3. Hover over "Men" category.
  4. Hover over "Tops" subcategory.
  5. Click on "Hoodies & Sweatshirts".
- **Expected Result:** User lands on the Hoodies & Sweatshirts page, and relevant products are displayed.
- **Pass/Fail Criteria:** Passed.

### Test Case 2: Product Search Functionality
- **Test Case ID:** TC_02
- **Test Scenario:** Ensure that the product search bar retrieves relevant hoodie products.
- **Preconditions:** User is on any page with the search bar visible.
- **Steps:**
  1. Open Edge browser.
  2. Go to [https://magento.softwaretestingboard.com/](https://magento.softwaretestingboard.com/).
  3. Hover over the search bar.
  4. Type "Hoodie" into the search bar.
  5. Press Enter.
- **Expected Result:** A list of products containing "Hoodie" is displayed.
- **Pass/Fail Criteria:** Passed.

### Test Case 3: Checkout Process - Add to Cart and Attempt Checkout
- **Test Case ID:** TC_03
- **Test Scenario:** Validate that the checkout flow works correctly from cart to order placement.
- **Preconditions:** Product is available and user can add it to the cart.
- **Steps:**
  1. Open Edge browser.
  2. Go to [https://magento.softwaretestingboard.com/](https://magento.softwaretestingboard.com/).
  3. Hover over the search bar.
  4. Type "Hoodie" into the search bar.
  5. Press Enter.
  6. Open Hero hoodie product page.
  7. Select size and color.
  8. Click on "Add to cart".
  9. Click on the cart icon.
  10. Click on "Proceed to checkout".
  11. Fill in shipping information and click next.
  12. Check "My billing and shipping address are the same".
  13. Click "Place Order".
- **Expected Result:** The product is ordered successfully.
- **Pass/Fail Criteria:** Passed.


## Installation and Setup

### Install Dependencies
To install all necessary dependencies:
```bash
  mvn install
```
## Run the Tests

Execute the tests using Maven:

```bash
mvn test

```
## Generate Allure Report

After running the tests, generate an interactive Allure report to visualize test results:

```bash
mvn allure:serve
```
## Project Structure

- `src/test/java`: Contains test classes.
- `src/test/resources`: Includes test data files (e.g., JSON).
- `pom.xml`: Maven configuration.
- `testng.xml`: TestNG execution configuration.
- **Allure Reports**: Generated for each test run.
## Dependencies

- Selenium WebDriver
- TestNG
- Maven
- Allure Reporting


