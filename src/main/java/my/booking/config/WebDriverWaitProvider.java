package my.booking.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class WebDriverWaitProvider implements Provider<WebDriverWait> {

    @Inject
    @Named("driver.defaultTimeout")
    private Integer defaultTimeout;

    @Inject
    private WebDriver webDriver;

    @Override
    public WebDriverWait get() {
        return new WebDriverWait(webDriver, defaultTimeout);
    }
}
