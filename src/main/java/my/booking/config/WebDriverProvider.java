package my.booking.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class WebDriverProvider implements Provider<WebDriver> {

    @Inject
    @Named("driver.name")
    private String browserName;

    @Override
    public WebDriver get() {
        if (browserName.equals("chrome")) {
            return new ChromeDriver();
        }
        return null;
    }
}
