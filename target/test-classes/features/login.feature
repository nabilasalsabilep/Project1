Feature: Login API

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