package my.booking.utils;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;

public class WebDriverUtils {

    private WebDriver webDriver;

    private WebDriverWait wait;

    @Inject
    public WebDriverUtils(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void waitAndClick(By selector) {
        wait.until(elementToBeClickable(selector));
        webDriver.findElement(selector).click();
    }

    public void selectValue(By selector, String value) {
        wait.until(visibilityOfElementLocated(selector));
        new Select(webDriver.findElement(selector)).selectByValue(value);
        wait.until(textToBePresentInElementValue(selector, value));
    }

    public void type(By selector, String text) {
        wait.until(visibilityOfElementLocated(selector));
        webDriver.findElement(selector).sendKeys(text);
    }
}
