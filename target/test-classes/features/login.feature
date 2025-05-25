Feature: Login API

  Background:
    Given The base url in this feature is "https://whitesmokehouse.com"

  Scenario:
    When Send a http "POST" request to "/webhook/api/login" with body:
      """
      {
        "email": "user_15@test.com",
        "password": "Password1!"
      }
      """
    Then The response status must be 200
    And Save the token from the response to local storage