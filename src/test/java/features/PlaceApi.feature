Feature: To test Place APIs

  @AddPlace
  Scenario Outline: Verify that user will be able to add the place using Add Place APIs
    Given Add Place Payload "<name>" "<address>" "<language>"
    When User call the "addPlaceApi" API with "POST" Http Method
    Then User should see the status as success with code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And Verify that palce_id created maps to "<name>" using "getPlaceApi"

    Examples:
      | name    | address       | language |
      | Anushka| P No 149 | Hindi   |
      | Anish  | jaipur    | English  |

  @DeletePlace
  Scenario: Verify that user should delete the Added Place
    Given Delete API Payload
    When User call the "deletePlaceApi" API with "delete" Http Method
    Then User should see the status as success with code 200
    And "status" in response is "OK"


