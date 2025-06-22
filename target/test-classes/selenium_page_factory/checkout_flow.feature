Feature: Checkout Flow

  Background:
    Given Open "https://rahulshettyacademy.com/client"
    Then Init all pages for run automation for checkout flow

  Scenario:
    When Click button Register on Login page
    And Input first name, last name, phone number, select occupation with engineer, select female gender
    And Input password & confirm password "P@ssword!1"
    And Click TnC checkbox on Register page
    And Click button Register for register new account
    And Click button Login to open the Login page
    Then Input email "{email}" and password "P@ssword!1" in login page for checkout flow
    Then Click login button for checkout flow
    Then Search product with keyword "IPHONE" for checkout flow
    Then Verify the product name contains inputted keyword
    Then Click view product for checkout flow
    And Verify product name, to make sure the product name contains inputted keyword is shown for checkout flow
    And Verify product price is the same as the price on the dashboard
    Then Click Add to Cart button for checkout flow
    Then Verify product name, product price on Cart page are the same as the data from product display
    Then Click checkout button for checkout flow
    Then Verify credit card number is match with "4542 9931 9292 2293"
    Then Verify email is match with "{email}"
    Then Enter the details payment for checkout flow
    Then Click place order button for checkout flow
    And Verify order, to make sure product name contains inputted keyword with product price that is same as the product price from the checkout page successfully ordered
    Then Delay for 2 seconds