Feature: To test Place APIs

  Scenario: Verify that user will be able to add the place using Add Place APIs
    Given Add Place Payload
    When User call the "AddPlace" API with POST Http Method
    Then User should see the status as success with code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"


