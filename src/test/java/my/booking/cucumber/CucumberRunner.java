package my.booking.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = { "pretty", "html:build/cucumber/report" },
        tags = "@test"
)
public class CucumberRunner {

}
