# Cucumber Implementation for Rest-Assured

## 🔍 Overview
This project uses **Cucumber** as a BDD (Behavior-Driven Development) framework to automate REST API testing using **Rest-Assured**. This approach allows test cases to be written in natural language that can be easily understood by all stakeholders.

## 📋 Features
- Testing REST APIs with HTTP methods like GET, POST, PUT, PATCH, DELETE.
- Validating response status codes and JSON body content.
- Deserializing JSON responses into Java models using Jackson.
- Logging requests and responses for easier debugging.

## 📁 Project Structure
- cucumber/definitions - Step definitions implementing test steps
- cucumber/helper - Generating the Cucumber report
- cucumber/runners - Test runners to execute feature files
- resources/features - Feature files written in Gherkin language

## 🚀 Running Tests
### Tests are configured with TestNG using the cucumber_suite.xml file.  
Run all tests by executing:  
mvn test -DsuiteXml="cucumber_suite.xml"
