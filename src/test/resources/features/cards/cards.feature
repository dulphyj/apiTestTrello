@Card
Feature: Card

  @CreateCard
  Scenario Outline: A user creates a Trello card
    Given The user "<User Name>" is authorized to make a request.
    When The user is on the Trello board BoardName and list ListName
    And The user creates a card with the name "<Card Name>"
    Then response should be "<Status Line>"
    And response must be valid and has a body
    And name should be correct
    Examples:
      | User Name     | Card Name | Status Line |
      | dlphsolutions | Test Card | 200         |

  @GetCard
  Scenario Outline: A user retrieves information about a Trello card
    Given The user "<User Name>" is authorized to make a request.
    When The user is on the Trello board BoardName and list ListName
    And The user looks at a card with the name TestCard
    And The user gets the card
    Then response should be "<Status Line>"
    And response must be valid and has a body
    And name should be correct
    Examples:
      | User Name     | Status Line |
      | dlphsolutions | 200         |

  @UpdateCardName
  Scenario Outline: A user updates the name of a Trello card
    Given The user "<User Name>" is authorized to make a request.
    When The user is on the Trello board BoardName and list ListName
    And The user looks at a card with the name TestCard
    And The user update the name of the card to "<New Card Name>"
    Then response should be "<Status Line>"
    And response must be valid and has a body
    And name should be correct
    Examples:
      | User Name     | New Card Name | Status Line |
      | dlphsolutions | Updated Card  | 200         |

  @DeleteCard
  Scenario Outline: A user deletes a Trello card
    Given The user "<User Name>" is authorized to make a request.
    When The user is on the Trello board BoardName and list ListName
    And The user looks at a card with the name TestCard
    And the user delete the card
    Then response should be "<Status Line>"
    Examples:
      | User Name     | Status Line |
      | dlphsolutions | 200         |

