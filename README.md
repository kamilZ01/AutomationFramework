## AutomationFramework
Tests are written in the Cucumber framework using the Gherkin syntax. They are implemented in Java and use Selenium WebDriver for browser automation. All dependencies are managed by Maven.

### Installation & Prerequisites

1. JDK 21 (Ensure that the Java class path is properly set)
2. Maven (Ensure that the .m2 class path is properly set)
3. Intellij IDEA Community (optional)
5. Chrome/Firefox browser installed.

## Framework Setup

To set up the framework, you can clone the repository from [here](https://github.com/kamilZ01/AutomationFramework), or download the ZIP file and set it up in your local workspace.

## Available tests
**GUI**; Scenario: Wybranie telefonu z listy ofert - Test can be run using @TestWeb tag.

**API**; Scenario: Kursy walut - Test can be run using @TestAPI.

## Running Sample Tests

Access the CLI of your operating system (e.g. PowerShell for Windows) and navigate to the project directory. Then run the following command to execute all features: `mvn clean test`.
By default this command will invoke the Firefox browser for GUI tests. To change the default browser, edit the `browser` parameter in the `src/test/resources/config/config.properties` file.

To run tests filtered by tags, use the following commands:
- `mvn clean test "-Dcucumber.filter.tags=(@TestWeb or @TestAPI)"` - to run both tests,
- `mvn clean test "-Dcucumber.filter.tags=(@TestWeb)"` - to run a single test.

  

Please note that browser drivers are not included as part of this framework. Browser drivers are automatically managed by WebDriverManager library.

## Reporting

After executing the tests, you can view HTML report by navigating to the `/target/htmlreport/` directory in your project. This report provides detailed information about the test results.

If any test scenario fail, screenshots from the browser will be included in the report to help diagnose the issue.
