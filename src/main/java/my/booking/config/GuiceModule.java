package my.booking.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import cucumber.runtime.java.guice.ScenarioScoped;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        Names.bindProperties(binder(), new PropertiesLoader().load());
        bind(WebDriver.class).toProvider(WebDriverProvider.class).in(ScenarioScoped.class);
        bind(WebDriverWait.class).toProvider(WebDriverWaitProvider.class).in(ScenarioScoped.class);
    }

}
