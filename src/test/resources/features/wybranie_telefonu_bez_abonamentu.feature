Feature: Zarządzanie ofertami telefonów bez abonamentu

  Scenario: Wybranie telefonu z listy ofert

    Given user navigates to "https://www.t-mobile.pl/" page

    When user accepts all cookies

    Then homepage should be visible

    When user selects Urządzenia from menu

    Then dropdown list with below items should be visible
      | Z abonamentem           |
      | Z internetem            |
      | Z ofertą na doładowania |
      | Bez abonamentu          |
      | Inne                    |

    When user selects "Smartfony" from "Bez abonamentu" group

    Then devices section count is 18

    When user select first element from the list

    Then product page should be visible

    When user adds product to cart

    Then your basket page should be visible

    And price inside basket should be the same as on the product details page

    When user navigates from basket to home page

    Then homepage should be visible

    And basket items count is 1

