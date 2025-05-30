Feature: Detele Object API

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
    When Send a http "GET" request to "/webhook/api/objects" with body:
      """
      {}
      """
    And Get first id from the list of objects
    When Send a http "DELETE" request to "/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/{id}" with body:
      """
      {}
      """
    Then The response status must be 200
    And The status in the response must be "deleted"
    And The message in the response must be "Object with id = {id}, has been deleted."

  Scenario:
    Given Make sure token in local storage not empty
    When Send a http "DELETE" request to "/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/{id}" with body:
      """
      {}
      """
    Then The response status must be 200
    And The status in the response must be "failed"
    And The message in the response must be "object with id = {id} has been deleted or doesn't exist"