package my.booking.page.components;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;

public class GiftPopup {

    private WebDriver webDriver;

    private WebDriverWait wait;

    private Logger logger;

    private static final int SHORT_TIMEOUT = 5;

    private static By POPUP = By.cssSelector(".incentives-popup");

    private static By NO_THANKS_BUTTON = By.cssSelector("input[type=submit].-no");

    @Inject
    GiftPopup(WebDriver webDriver, WebDriverWait wait, Logger logger) {
        this.webDriver = webDriver;
        this.wait = wait;
        this.logger = logger;
    }

    public void submitIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(webDriver, SHORT_TIMEOUT);
            shortWait.until(visibilityOfElementLocated(POPUP));
            wait.until(elementToBeClickable(NO_THANKS_BUTTON));
            webDriver.findElement(NO_THANKS_BUTTON).click();
            wait.until(invisibilityOfElementLocated(POPUP));
        } catch (WebDriverException ex) {
            logger.info("No Gift popup was shown this time!");
        }

    }
}
