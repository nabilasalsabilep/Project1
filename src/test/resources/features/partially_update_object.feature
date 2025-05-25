Feature: Partially Update Object API

  Background:
    Given The base url in this feature is "https://whitesmokehouse.com"
    When Send a http "POST" request to "/webhook/api/login" with body:
      """
      {
        "email": "user_15@test.com",
        "password": "Password1!"
      }
      """
    And Save the token from the response to local storage

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "PATCH" request to "/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/567" with body:
      """
      {
        "name": "Apple MacBook Pro 14",
        "year": "2032"
      }
      """
    Then The response status must be 200
    And The response schema should be match with schema "partially_update_object_schema.json"
    And Partially updated object id in the response must be 567
    And Partially updated object name in the response must be "Apple MacBook Pro 14"
    And Partially updated object year in the response must be "2032"