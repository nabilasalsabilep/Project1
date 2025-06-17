# 🛒 Selenium E2E Checkout Flow

## 🔍 Overview
This project implements an **end-to-end automation test** for the checkout flow using **Selenium WebDriver** and **TestNG**. The flow covers the critical user journey from login to verifying order creation.

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
- selenium_scenario - Contains Checkout Flow
- resources/selenium_scenario_suite - Suite configuration file specifying which tests to run and in what order.


## 🚀 How to Run
### Run tests using Maven and custom TestNG XML:
mvn test -DsuiteXml="selenium_scenario_suite.xml"