package my.booking.cucumber.stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Hooks {

    @Inject
    private WebDriver webDriver;

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            embedScreenshot(scenario);
        }
        webDriver.close();
    }

    private void embedScreenshot(Scenario scenario) {
        final byte[] screenshotBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshotBytes, "image/png");
    }

}
