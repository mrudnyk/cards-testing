@api-tests
Feature: Deck of cards

  Scenario Outline: Draw x cards from the deck
    Given request specifications have been set
    Then get request to draw <count> cards from the deck has been sent and the remaining count now is: <remaining>
    Examples:
      |   count  |remaining |
      |    4     |    48    |
      |    6     |    46    |
      |    20    |    32    |

  Scenario: Draw all cards from the deck with aces only
    Given request specifications have been set
    Then get request to draw all cards from the deck has been sent and each card has value: ACE

  Scenario: Draw 5 specific cards from a button of the deck
    Given request specifications have been set
    Then get request to draw 5 specific cards has been sent, these cards are not present in the deck anymore and the cards count now is 47

