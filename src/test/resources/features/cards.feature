Feature: Deck of cards

  Scenario: Shuffle a new deck
    Given request specifications have been set
    When get request to shuffle the deck has been sent and we received the response
    Then the deck has been shuffled successfully

  Scenario: Draw 5 cards from the deck
    Given request specifications have been set
    When get request to draw 5 cards from the deck has been sent and we received the response
    Then the remaining cards count now is 47

  Scenario: Draw 2 cards from the deck
    Given request specifications have been set
    Then get request to draw 2 cards from the deck has been sent and the remaining cards count now is 50

  Scenario: Draw all cards from the deck with aces only
    Given request specifications have been set
    When get request to draw all cards from the deck has been sent
    Then each card has value: ACE

  Scenario: Draw 5 specific cards from a button of the deck
    Given request specifications have been set
    Then get request to draw 5 specific cards has been sent, these cards are not present in the deck anymore and the cards count now is 47

