package my.booking.page.components;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;
import my.booking.utils.WebDriverUtils;

public class GiftPopup {

    private WebDriver webDriver;

    private WebDriverWait wait;

    private Logger logger;

    private WebDriverUtils wdUtils;

    private static final int SHORT_TIMEOUT = 5;

    private static By POPUP = By.cssSelector(".incentives-popup");

    private static By NO_THANKS_BUTTON = By.cssSelector("input[type=submit].-no");

    @Inject
    GiftPopup(WebDriver webDriver, WebDriverWait wait, WebDriverUtils wdUtils, Logger logger) {
        this.webDriver = webDriver;
        this.wait = wait;
        this.wdUtils = wdUtils;
        this.logger = logger;
    }

    public void submitIfPresent() {
        try {
            logger.info(String.format("Waiting %d seconds for gift popup to appear", SHORT_TIMEOUT));
            WebDriverWait shortWait = new WebDriverWait(webDriver, SHORT_TIMEOUT);
            shortWait.until(visibilityOfElementLocated(POPUP));
            wdUtils.waitAndClick(NO_THANKS_BUTTON);
            wait.until(invisibilityOfElementLocated(POPUP));
            logger.info("Successfully dismissed gift popup!");
        } catch (WebDriverException ex) {
            logger.info("No Gift popup was shown this time!");
        }

    }
}
