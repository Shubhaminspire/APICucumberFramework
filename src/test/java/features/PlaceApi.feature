Feature: To test Place APIs

  Scenario Outline: Verify that user will be able to add the place using Add Place APIs
    Given Add Place Payload "<name>" "<address>" "<language>"
    When User call the "addPlaceApi" API with POST Http Method
    Then User should see the status as success with code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"

    Examples:
      | name    | address                  | language |
      | Shubham | P No 149 Govindpura      | Hindi    |
      | Anish   | C-131, Kushak No-2 Delhi | English  |


