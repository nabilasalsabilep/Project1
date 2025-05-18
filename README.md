# Project1 – Automated API Testing with RestAssured
This repository contains automated API tests built with RestAssured using Java and Maven as the build and dependency management tool. The tests are organized under the restassured folder, and the execution suite configuration is managed through the task2_suite.xml file.

## Overview
This project aims to provide a comprehensive suite of API tests to validate the functionality of various endpoints on the target system. The test classes cover typical CRUD operations such as login, retrieving object lists, adding, updating, and deleting objects.

## Getting Started
To get started with this project, you need to clone the repository and set up the environment to run the tests:

1. Clone the repository
   Open your terminal or command prompt and run:  
   **git clone https://github.com/nabilasalsabilep/Project1.git**  
   **cd Project1**
2. Prerequisites
   Make sure you have the following installed on your machine:  
   **Java Development Kit (JDK)**  
   **Apache Maven**
3. Install Dependencies
   From the project root folder, run the following command to download all necessary dependencies and build the project:  
   **mvn clean install**

## Running Tests
The tests are configured to run using TestNG with a suite file named task2_suite.xml. To execute all tests defined in this suite, run the following command from the project root:  
**mvn test -DsuiteXmlFile=task2_suite.xml**  

## Project Structure
- **restassured/ – Contains all Java test classes, each targeting different API endpoints and test scenarios.**
- **task2_suite.xml – TestNG suite configuration file specifying which tests to run and in what order.**
