package my.booking.cucumber.stepdefs;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import cucumber.api.java.After;

public class Hooks {

    @Inject
    private WebDriver webDriver;

    @After
    public void tearDown() {
        webDriver.close();
    }
}
