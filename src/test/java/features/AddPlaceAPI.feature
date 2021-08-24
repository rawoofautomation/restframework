
Feature:  I want to validate Add place API

  @AddPlaceAPI
  Scenario Outline: Verify if the place is being successfully getting added using AddPlaceAPI
  
    Given Add place payload "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "post" http request
    Then the API call will get success code 200
    And chech "status" in response body is "OK"
 		And verify "<name>" from "getPlaceAPI" is matching with "get" http request  
Examples:

|  name   |  language  |  address  |
|  john vadentiy  |  telugu   |  nalapur |
#|  ahmed bhai  |  Arbic     |  cyderabad  |
@DeletePlaceAPI
Scenario:  Verify if the place is being successfully getting deleted using deletePlaceAPI

		Given delete palce payload
		When user calls "deletePlaceAPI" with "post" http request
    Then the API call will get success code 200
    And chech "status" in response body is "OK"


