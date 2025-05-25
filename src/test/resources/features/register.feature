Feature: Register API

  Background:
    Given The base url in this feature is "https://whitesmokehouse.com"

  Scenario:
    When Send a http "POST" request to "/webhook/api/register" with body:
      """
      {
        "email": "user_28@test.com",
        "password": "Password1!",
        "full_name": "Tester C",
        "department": "Executive",
        "phone_number": "081292983102"
      }
      """
    Then The response status must be 200
    And The response schema should be match with schema "register_schema.json"
    And Email in the response must be "user_28@test.com"
    And Full name in the response must be "Tester C"
    And Department in the response must be "Executive"
    And Phone number in the response must be "081292983102"