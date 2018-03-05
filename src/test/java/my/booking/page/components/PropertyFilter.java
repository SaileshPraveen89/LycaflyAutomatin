package my.booking.page.components;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;
import my.booking.utils.WebDriverUtils;

public class PropertyFilter {

    private static By AVAILABLE_ONLY_ANCHOR = cssSelector("#filter_out_of_stock a");

    private static By REVIEW_SCORE_8PLUS_ANCHOR = cssSelector("#filter_review [data-id=review_score-80]");

    private static By FILTER_APPLIED_POPUP = cssSelector(".sr-filter-popup #sr-filter-descr");

    private WebDriverUtils wdUtils;

    private WebDriverWait wait;

    @Inject
    public PropertyFilter(WebDriverUtils wdUtils, WebDriverWait wait) {
        this.wait = wait;
        this.wdUtils = wdUtils;
    }

    public void chooseToShowAvailableOnly() {
        applyFilter(AVAILABLE_ONLY_ANCHOR);
    }

    public void chooseToShowOnlyHighlyRated() {
        applyFilter(REVIEW_SCORE_8PLUS_ANCHOR);
    }

    private void applyFilter(By elementLocator) {
        wdUtils.waitAndClick(elementLocator);
        wait.until(visibilityOfElementLocated(FILTER_APPLIED_POPUP));
        wait.until(invisibilityOfElementLocated(FILTER_APPLIED_POPUP));
    }

}
