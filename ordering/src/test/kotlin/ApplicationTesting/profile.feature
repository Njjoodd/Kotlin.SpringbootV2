Feature: User Profile Creation

As an authenticated user
I want to create or update my profile
So that my personal data is saved

Scenario: Successfully creating a profile
    Given a user exists with username "testuser" and password "Password123"
    And the user is authenticated
  Given I have a token for username "testuser"
   When I make a POST request to "/profiles/v1/profiles" with body
#      | firstName  | lastName   | phoneNumber |
#      | Njood      | Aldousari  | 96055159    |

      """json
        {
          "firstName": "Njood",
          "lastName": "Aldousari",
          "phoneNumber": "96055159"
        }
      """
    Then the response status code should be 200
    And the response body should contain "Profile saved for testuser"
