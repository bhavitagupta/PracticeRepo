Feature: Verify the data is updated under preview table

  Scenario Outline: update data in preview table
    Given the browser and application URL
    And click on Tab "Component References"
    When in quickfind user search "datatable" 
    And select "Data Table with Inline Edit" from the dropdown
    And click on the run button
    And under the section "preview" update the table values for all colums in row 3
      | Label      | WebSite            | Phone          | CloseAt | Balance |
      | Larry Page | https://google.com | (555)-755-6575 | Today   |  770.54 |
    Then validate the table is updated for given row 3
      | Larry Page | https://google.com | (555)-755-6575 | Today | 770.54 |
