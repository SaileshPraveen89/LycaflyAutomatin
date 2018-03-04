# booking-com-test-automation

Test automation project for booking.com website written in Java and Selenium

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
Pretty cucumber report is generated each time after Gradle being executed. You'll be notified in command line about the location of a report.
