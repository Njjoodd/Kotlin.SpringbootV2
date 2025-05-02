Feature: User Profile Creation

As an authenticated user
I want to create or update my profile
So that my personal data is saved

Scenario: Successfully creating a profile
    Given a user exists with username "testuser" and password "Password123"
    And the user is authenticated
    When they submit a profile with:
      | firstName  | lastName   | phoneNumber |
      | Njood      | Aldousari  | 96055159    |
    Then the response status should be 200
    And the response should contain "Profile saved for testuser"