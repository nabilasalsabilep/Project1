# 🧪 Selenium Page Factory Project - Checkout Flow Automation

## 🔍 Overview
This project implements automated UI testing using Selenium WebDriver, Page Factory Pattern, Cucumber BDD, and TestNG. The project structure is designed for scalability, modularity, and maintainability, especially for behavior-driven test development.

## 🧪 E2E Flow Scenarios
1. **Register** with valid data
2. **Login** with valid credentials  
3. **Search** for a product  
4. **Add product to cart**  
5. **Proceed to checkout**  
6. **Place the order**  
7. **Verify order is created successfully**

## ⚙️ Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- ChromeDriver

## 📁 Project Structure
- main/java/com/demo/testng/program/selenium_page_factory/base/BaseObject - Used for initializing all PageFactory objects in a centralized way.
- main/java/com/demo/testng/program/selenium_page_factory/base/BasePage - Base class for all page classes. Contains shared utilities like wait, click, and input helpers.
- test/java/selenium_page_factory/object_repository/ - Contains references to all WebElement objects from each page.
- test/java/selenium_page_factory/pages/ - Page classes like LoginPage.java, RegisterPage.java, DashboardPage.java, etc. These use Page Factory (@FindBy) pattern.
- test/java/selenium_page_factory/test_suite_cucumber/ - This folder contains Page Classes that define actions and verifications for specific application pages.
- test/java/selenium_page_factory/test_suite_cucumber/definitions - Step definitions for the feature files.
- test/java/selenium_page_factory/test_suite_cucumber/helper - Contains utility classes that support test execution, reporting, and configuration logic.
- test/java/selenium_page_factory/test_suite_cucumber/runner - Connects feature files and step definitions with @CucumberOptions.
- resources/selenium_page_factory/checkout_flow.feature - Gherkin syntax feature file for checkout test scenario.
- resources/selenium_page_factory/runner_regression.xml - TestNG suite file to run the regression scenarios.
- resources/selenium_page_factory/staging.properties - Configuration file for staging environment (base URL, credentials, etc).


## 🚀 How to Run
### Run tests using Maven and custom TestNG XML:
mvn test -DsuiteXml=selenium_page_factory/runner_regression.xml