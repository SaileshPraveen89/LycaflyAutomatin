package my.booking.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverProvider implements Provider<WebDriver> {

    @Inject
    @Named("driver.name")
    private String browserName;
    
    @Inject
    @Named("browser.headless")
    private boolean headless;

    @Override
    public WebDriver get() {
        WebDriver webDriver;

        switch (browserName) {
            case "chrome":
                webDriver = getChromeDriver();
                break;
            case "firefox":
                webDriver = getFirefoxDriver();
                break;
            default:
                webDriver = new ChromeDriver();
        }
        
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        
        return webDriver;
    }

    private ChromeDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.setHeadless(headless);
        return new ChromeDriver(opts);
    }

    private FirefoxDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

}
