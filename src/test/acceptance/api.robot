*** Settings ***
Documentation  Verify that API is working correctly.

Resource  common.robot

Test Setup  Initialize
Test Teardown  Delete All Sessions

*** Variables ***

*** Test Cases ***

Check API - muunna
  [Tags]  API  Requirement
  &{params}  Create Dictionary  tie=70397
  Check API path   muunna  ${params}

*** Keywords ***

Check API path
  [Arguments]  ${path}  ${params}
  Get API Request  ${URL_CTX}/${path}  ${params}
  Get API Request Should Have Succeed

