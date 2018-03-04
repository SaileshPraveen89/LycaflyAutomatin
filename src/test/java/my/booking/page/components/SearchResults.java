package my.booking.page.components;

import static java.lang.Double.*;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;

public class SearchResults {

    private static By PROPERTY_BLOCKS = className("sr_property_block");

    private static By PROPERTY_NAME = className("sr-hotel__name");

    private static By RATING = className("review-score-badge");

    private static By PRICE = className("totalPrice");

    private WebDriver webDriver;

    private WebDriverWait wait;

    private Logger logger;

    @Inject
    public SearchResults(WebDriver webDriver, WebDriverWait wait, Logger logger) {
        this.webDriver = webDriver;
        this.wait = wait;
        this.logger = logger;
    }


    public void checkAtLeastSomeMeetTheCriteria(double leastReviewScore, double highestPrice) {
        wait.until(visibilityOfAllElementsLocatedBy(PROPERTY_BLOCKS));
        wait.until(visibilityOfAllElementsLocatedBy(RATING));
        wait.until(visibilityOfAllElementsLocatedBy(PRICE));
        
        List<WebElement> properties = webDriver.findElements(PROPERTY_BLOCKS).stream()
                .filter(el -> {
                    double rating = parseDouble(el.findElement(RATING).getText());
                    double price = parsePrice(el.findElement(PRICE).getText());
                    return rating > leastReviewScore && price < highestPrice;
                })
                .collect(Collectors.toList());
        String firstPropertyName = properties.get(0).findElement(PROPERTY_NAME).getText();
        logger.info(String.format("First property name: %s", firstPropertyName));

        Assert.assertThat(properties.isEmpty(), Is.is(false));
    }

    private double parsePrice(String text) {
        Pattern pattern = Pattern.compile(" (\\d+[,.]*\\d*)$");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return parseDouble(matcher.group(1));
        } else {
            throw new IllegalArgumentException(String.format("Unable to parse double from string: %s", text));
        }
    }
 
}
