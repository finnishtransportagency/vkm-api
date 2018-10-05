*** Settings ***
Documentation  Verify that API is working correctly.

Resource  common.robot

Test Setup  Initialize
Test Teardown  Delete All Sessions

*** Variables ***

*** Test Cases ***

Check API
  [Tags]  API  Requirement
  Check API path   swagger-ui.html

*** Keywords ***

Check API path
  [Arguments]  ${path}
  Get API Request  ${URL_CTX}/${path} 
  Get API Request Should Have Succeed

