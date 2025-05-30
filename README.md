# Refactor Cucumber

## 🔍 Overview
This project uses **Cucumber** as a BDD (Behavior-Driven Development) framework to automate REST API testing using **Rest-Assured**. This approach allows test cases to be written in natural language that can be easily understood by all stakeholders.

## 📦 Key Refactoring Highlights
- pom.xml: Added and updated necessary dependencies for read .env files, dotenv, and reporting.
- .env: Introduced an environment configuration file to manage base URL variable.
- apiengene/Endpoints: Centralized endpoint definitions for all API interactions.
- context/TestContext: Manages shared state across step definitions and scenarios.
- definitions/: Refactored step definitions for cleaner and more modular test logic.
- helper/ConfigManager: Loads environment variables and provides configuration access, such as the base URL.
- features/: Updated feature files to align with refactored step definitions and consistent naming conventions.

## 📋 Features
- Testing REST APIs with HTTP methods like GET, POST, PUT, PATCH, DELETE.
- Validating response status codes and JSON body content.
- Deserializing JSON responses into Java models using Jackson.
- Logging requests and responses for easier debugging.

## 📁 Project Structure
- cucumber/apiengine - Contains API endpoint paths
- cucumber/context - Temporary data storage (shared state) during Cucumber test execution.
- cucumber/definitions - Step definitions implementing test steps
- cucumber/helper - Loads environment variables like Base_URL and generating the Cucumber report
- cucumber/runners - Test runners to execute feature files
- resources/features - Feature files written in Gherkin language

## 🚀 Running Tests
### Tests are configured with TestNG using the cucumber_suite.xml file.  
Run all tests by executing:  
mvn test -DsuiteXml="cucumber_suite.xml"
