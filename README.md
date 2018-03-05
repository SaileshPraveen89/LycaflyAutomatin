[![Build Status](https://travis-ci.org/serzhshakur/booking-com-test-automation.svg?branch=master)](https://travis-ci.org/serzhshakur/booking-com-test-automation)

Test automation project for [booking.com](https://booking.com) website written in Java and Selenium

#### Supported operating systems
- Linux
- Windows

#### Supported browsers
- Chrome
- Firefox

To set browser change `driver.name` property under _$projectDir/src/main/resources/test.properties_.
By default **Chrome** in headless mode is used. 

### Getting webdriver

Downloading proper webdrivers is handled automatically using [WebDriverManager library](https://github.com/bonigarcia/webdrivermanager).

### Cucumber test framework

[Cucumber](https://cucumber.io/) was chosen as a testing tool. It supports Behavior Driven Development (BDD) framework. The reason behind that is that Cucumber defines application behavior using simple English text which describes user behaviour.


### Tests execution

Tests can be executed either from your IDE or from command line using Gradle. 
1. To run a tests from IntelliJ IDEA get to `.feature` file under _test/resources/features_ and run a separate test scenario or the whole suite. To do that just right click on a **Scenario** or a **Feature** and choose "Run 'Scenario ...''"/ "Run 'Feature ...''"

2. To run test from command line use the following Gradle command

- _for Linux machines_
```bash
./gradlew clean runTests
```
- _for Windows machines_
```bash
gradlew.bat clean runTests
```

### Cucumber reports
A pretty cucumber report is generated each time after tests being executed with Gradle task. [Gradle Cucumber Reporting plugin](https://github.com/SpacialCircumstances/gradle-cucumber-reporting) is used for that purpose. You'll be notified in command line about the location of a report.

### Continuous integration
This project has integration with travis-ci so each time a new code is pushed to repo a pipeline on Travis is automatically triggered and Cucumber tests are executed. The current state of a build is [![Build Status](https://travis-ci.org/serzhshakur/booking-com-test-automation.svg?branch=master)](https://travis-ci.org/serzhshakur/booking-com-test-automation)
. You can also see the current status at a top of this readme. A stable Chrome version in a headless mode is used for running tests on CI. 