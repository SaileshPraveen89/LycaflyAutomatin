package my.booking.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {

    private static final int DEFAULT_TIMEOUT = 10;

    @Override
    protected void configure() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        bind(WebDriver.class).toInstance(driver);
        bind(WebDriverWait.class).toInstance(wait);
    }

}
