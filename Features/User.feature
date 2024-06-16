Feature: Validating PetStore User API's

  @Sanity @Regression
  Scenario Outline: TC_001_Verify Create User Functionality
    Given User Payload with "<Name>"
    When User calls "CreateUserAPI" with "POST" HTTP Request
    Then Verify Status Code "<StatusCode>"

    #   And Verify "status" in Response Body is "OK"
    Examples: 
      | Name      | StatusCode |
      | Varadaraj |        200 |

  @Regression
  Scenario Outline: TC_002_Verify Fetch User Details Functionality
    Given Fetch User details by "<UserName>"
    When User calls "GetUserAPI" with "GET" HTTP Request
    Then Verify Status Code "<StatusCode>"

    Examples: 
      | UserName  | StatusCode |
      | Varadaraj |        200 |
      
  @Regression
  Scenario Outline: TC_003_Verify Update User Details Functionality
    Given Update User details by "<UserName>"
    When User calls "UpdateUserAPI" with "PUT" HTTP Request
    Then Verify Status Code "<StatusCode>"

    Examples: 
      | UserName  | StatusCode |
      | Varadaraj |        200 |

  @Regression
  Scenario Outline: TC_004_Verify Delete User Functionality
    Given Delete User by "<UserName>"
    When User calls "DeleteUserAPI" with "DELETE" HTTP Request
    Then Verify Status Code "<StatusCode>"

    Examples: 
      | UserName  | StatusCode |
      | Varadaraj |        200 |
