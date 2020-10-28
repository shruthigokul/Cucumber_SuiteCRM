@tag
Feature: Creating a Product

  @Scenario4
  Scenario Outline: To use an Examples table to add products
    Given user navigates to  All -> Products-> Create Product
    When user enters product details with product name "<prodName>", price "<price>"
    And user clicks save product
    Then user navigates to View Products page to verify added product
    
    Examples: 
      | prodName  | price |
      | H_Brush |     5 |
      | H_Paint |     7 |
