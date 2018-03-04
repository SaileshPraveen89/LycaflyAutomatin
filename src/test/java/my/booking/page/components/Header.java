package my.booking.page.components;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;
import my.booking.config.Currency;
import my.booking.config.Language;

public class Header {

    private static By SELECTED_CURRENCY_INPUT = cssSelector("[name='selected_currency']");

    private static By SELECTED_CURRENCY_ANCHOR = cssSelector("[name='selected_currency'] + a");

    private static By CURRENCY_POPUP = cssSelector("#current_currency");

    private static By SELECTED_LANGUAGE_ANCHOR = cssSelector("[data-id='language_selector'] > a");

    private static By SELECTED_LANGUAGE_FLAG_IMAGE = cssSelector("[data-id='language_selector'] > a img");

    private static By LANGUAGE_POPUP = cssSelector("#current_language_foldout");

    private WebDriver webDriver;

    private WebDriverWait wait;

    @Inject
    public Header(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void expectElements() {
        wait.until(visibilityOfElementLocated(SELECTED_CURRENCY_ANCHOR));
        wait.until(visibilityOfElementLocated(SELECTED_LANGUAGE_ANCHOR));
    }

    public void chooseCurrency(String currency) {
        String currencyDescriptor = Currency.getDescriptorByName(currency);
        String currencySelector = String.format("#currency_dropdown_all [data-currency=%s]", currencyDescriptor);
        By currencyLocator = By.cssSelector(currencySelector);

        wait.until(visibilityOfElementLocated(SELECTED_CURRENCY_ANCHOR));
        webDriver.findElement(SELECTED_CURRENCY_ANCHOR).click();
        wait.until(visibilityOfElementLocated(CURRENCY_POPUP));
        wait.until(visibilityOfElementLocated(currencyLocator));
        WebElement currencyElement = webDriver.findElement(currencyLocator);

        if (currencyElement.getAttribute("aria-checked").equals("true")) {
            webDriver.findElement(SELECTED_CURRENCY_ANCHOR).click();
        } else {
            currencyElement.click();
        }

        wait.until(invisibilityOfElementLocated(CURRENCY_POPUP));
        wait.until(attributeContains(SELECTED_CURRENCY_INPUT, "value", currencyDescriptor));
    }

    public void chooseLanguage(String language) {
        String langDescriptor = Language.getDescriptorByName(language);
        String langSelector = String.format(
                "#current_language_foldout .select_foldout_wrap:nth-of-type(2) [data-lang=%s] a", langDescriptor);
        By langLocator = By.cssSelector(langSelector);

        wait.until(visibilityOfElementLocated(SELECTED_LANGUAGE_ANCHOR));
        webDriver.findElement(SELECTED_LANGUAGE_ANCHOR).click();
        wait.until(visibilityOfElementLocated(LANGUAGE_POPUP));
        wait.until(elementToBeClickable(langLocator));
        webDriver.findElement(langLocator).click();
        wait.until(invisibilityOfElementLocated(LANGUAGE_POPUP));
        wait.until(attributeContains(SELECTED_LANGUAGE_FLAG_IMAGE, "alt", language));
    }

}
