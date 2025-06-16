# 🛒 Selenium E2E Checkout Flow

## 🔍 Overview
This project implements an **end-to-end automation test** for the checkout flow using **Selenium WebDriver** and **TestNG**. The flow covers the critical user journey from login to verifying order creation.

## 🧪 E2E Flow Scenarios
1. **Login** with valid credentials  
2. **Search** for a product  
3. **Add product to cart**  
4. **Proceed to checkout**  
5. **Place the order**  
6. **Verify order is created successfully**

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