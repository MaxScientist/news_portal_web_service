Feature: Testing a REST API
  Users should be able to submit GET and POST requests to a web service,
  represented by WireMock

  Scenario: Data Upload to a web-service
    When News is uploaded
    Then server should handle & return status OK

  Scenario: Data retrieval from a web service
    When News retrieval from id 1
    Then requested data returned

  Scenario: Data deletion from web-service
    When News is deleted by id 1
    Then server should handle & return status OK

  Scenario: Return all data news
    When All news retrieval
    Then server should handle & return status OK

#  Scenario: Data retrieval from a web service
#    When News retrieval from id 1
#    Then requested data returned