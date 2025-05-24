# E2E POJO – Automated API Testing with RestAssured + TestNG

This branch contains automated end-to-end (E2E) API tests using **RestAssured**, **Java**, and **Maven**, focusing on E2E tests and POJO classes. The tests are structured for clarity and validation of API responses through proper mapping to Java objects.

## 🔍 Overview

This project aims to test key API functionality such as login, object creation, updating, and deletion data. Test cases are designed to handle both **positive** and **negative** scenarios, utilizing POJO classes for clear data modeling and validation.

## 🚀 Running Tests  
#### Tests are configured with TestNG using the e2e_suite.xml file. Run all E2E tests using:  
mvn test -DsuiteXml="e2e_suite.xml"  

#### Tests are configured with TestNG using the pojo_e2e_suite.xml file. Run all E2E tests using:  
mvn test -DsuiteXml="pojo_e2e_suite.xml"  

## 📁 Project Structure
- e2e/ – Contains all E2E test classes without POJO, each targeting different API endpoints and test scenarios.
- e2e/pojo - Contains all E2E test classes focusing on POJO, each targeting different API endpoints and test scenarios.
- resources/.schema - All files used for response body validation.
- model/ - Carries and represents JSON responses to Java objects.
- task2_suite.xml – TestNG suite configuration file specifying which tests to run and in what order.
