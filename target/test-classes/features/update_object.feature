Feature: Update Object API

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
    When Send a http "PUT" request to "/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/566" with body:
      """
      {
        "name": "Apple MacBook Pro 18",
        "data": {
          "year": 2035,
          "price": 1855.88,
          "cpu_model": "Intel Core i10",
          "hard_disk_size": "2 TB",
          "capacity": "3 cpu",
          "screen_size": "24 Inch",
          "color": "red"
        }
      }
      """
    Then The response status must be 200
    And The response schema should be match with schema "update_object_schema.json"
    And Updated object id in the response must be 566
    And Updated object name in the response must be "Apple MacBook Pro 18"
    And Updated object year in the response must be "2035"
    And Updated object price in the response must be 1855.88
    And Updated object cpu model in the response must be "Intel Core i10"
    And Updated object hard disk size in the response must be "2 TB"
    And Updated object capacity in the response must be "3 cpu"
    And Updated object screen size in the response must be "24 Inch"
    And Updated object color in the response must be "red"