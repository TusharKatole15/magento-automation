# Magento Automation Project

##  How to Run Tests
Run the test runner class:
testRunner.RunTest
This will execute the tests with Cucumber and generate Allure results.

---

## Feature Files
All test scenarios are written using Gherkin and located at:
src/test/resources/features/

---
## Screenshot Location
Failure screenshots are saved in:
test-output/screenshots/

---

## Ad Handling
The framework automatically detects and closes dynamic ad popups.

 Note: It may take 20–30 seconds to close each ad, depending on visibility and iframe nesting.

---

## Logs

Logs are saved at: test-output/logs/test.log

---

## Allure Report
Allure test results are saved in:
test-output/allure-results/

To generate a single-file HTML report run:
allure generate test-output/allure-results --clean --single-file -o test-output/allure-report

A single-file HTML report is generated at:
test-output/allure-report

You can open this HTML file in a browser to view test results.