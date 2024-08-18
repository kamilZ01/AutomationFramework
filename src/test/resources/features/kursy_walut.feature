Feature: Średnie kursy walut z tabeli A

  @TestAPI
  Scenario: Kursy walut

    Given user gets exchange rates from NBP API

    Then user displays rate for a currency with code "USD"

    And user displays rate for a currency with name "dolar amerykański"

    And user displays currencies with exchange rates above 5.0

    And user displays currencies with exchange rates below 3.0
