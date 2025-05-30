Feature: Add Object API

  Background:
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
    When Send a http "POST" request to "/webhook/api/objects" with body:
      """
      {
        "name": "Apple MacBook Pro 16",
        "data": {
            "year": 2030,
            "price": 1849.99,
            "cpu_model": "Intel Core i9",
            "hard_disk_size": "1 TB",
            "capacity": "2 cpu",
            "screen_size": "14 Inch",
            "color": "red"
            }
        }
      """
    Then The response status must be 200
    And The response schema should be match with schema "add_object_schema.json"
    And Object name in the response must be "Apple MacBook Pro 16"
    And Object year in the response must be "2030"
    And Object price in the response must be 1849.99
    And Object cpu model in the response must be "Intel Core i9"
    And Object hard disk size in the response must be "1 TB"
    And Object capacity in the response must be "2 cpu"
    And Object screen size in the response must be "14 Inch"
    And Object color in the response must be "red"