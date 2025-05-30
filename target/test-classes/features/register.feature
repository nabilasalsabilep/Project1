Feature: Register API

  Scenario:
    When Send a http "POST" request to "/webhook/api/register" with body:
      """
      {
        "email": "{email}",
        "password": "Password1!",
        "full_name": "Tester C",
        "department": "Executive",
        "phone_number": "081292983102"
      }
      """
    Then The response status must be 200
    And The response schema should be match with schema "register_schema.json"
    And Email in the response must be "{email}"
    And Full name in the response must be "Tester C"
    And Department in the response must be "Executive"
    And Phone number in the response must be "081292983102"