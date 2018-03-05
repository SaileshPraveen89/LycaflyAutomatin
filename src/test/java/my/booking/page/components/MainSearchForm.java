package my.booking.page.components;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Inject;
import my.booking.utils.WebDriverUtils;

public class MainSearchForm {

    private static By SEARCH_BOX = cssSelector(".sb-searchbox-universal #ss");

    private static By SUGGESTIONS_LIST = cssSelector(".sb-searchbox-universal #ss + ul li");

    private static By CHECKIN_CALENDAR_BODY = cssSelector(".--checkin-field .c2-calendar");

    private static By CHECKOUT_CALENDAR_BODY = cssSelector(".--checkout-field .c2-calendar");

    private static By CHECK_OUT_DATEPICKER = cssSelector(
            ".sb-searchbox-universal .--checkout-field .b-datepicker .sb-searchbox__input:not(.-empty)");

    private static By CHECKIN_CURRENT_MONTH_LAST_WEEK = cssSelector(
            ".c2-month:first-of-type tbody tr:last-of-type .c2-day");

    private static By CHECKOUT_NEXT_MONTH_FIRST_WEEK = cssSelector(
            ".c2-month:nth-of-type(2) tbody tr:first-of-type .c2-day");

    private static By ADULTS_SELECT = cssSelector("#group_adults");

    private static By CHILDREN_SELECT = cssSelector("#group_children");

    private static By CHILD_AGE_SELECT = cssSelector(".sb-group-children-age [name=age]");

    private static By NUMBER_OF_ROOMS = cssSelector("#no_rooms");

    private static By TRAVEL_PURPOSE_CHECKBOX = cssSelector("[name=sb_travel_purpose]");

    private static By SEARCH_BUTTON = cssSelector(".sb-searchbox-universal .sb-searchbox-submit-col button");

    private WebDriver webDriver;
    
    private WebDriverUtils wdUtils;

    private WebDriverWait wait;

    @Inject
    public MainSearchForm(WebDriver webDriver, WebDriverWait wait, WebDriverUtils wdUtils) {
        this.webDriver = webDriver;
        this.wait = wait;
        this.wdUtils = wdUtils;
    }

    public void expectElements() {
        wait.until(visibilityOfElementLocated(SEARCH_BOX));
        wait.until(visibilityOfElementLocated(ADULTS_SELECT));
        wait.until(visibilityOfElementLocated(CHILDREN_SELECT));
        wait.until(visibilityOfElementLocated(NUMBER_OF_ROOMS));
        wait.until(visibilityOfElementLocated(TRAVEL_PURPOSE_CHECKBOX));
        wait.until(visibilityOfElementLocated(SEARCH_BUTTON));
    }

    public void enterSearchTerm(String searchTerm) {
        wdUtils.type(SEARCH_BOX, searchTerm);
        wait.until(visibilityOfElementLocated(SUGGESTIONS_LIST));
        webDriver.findElements(SUGGESTIONS_LIST).stream()
                .filter(el -> el.getAttribute("data-label").equals(searchTerm))
                .findFirst().orElseThrow(NotFoundException::new)
                .click();
        wait.until(invisibilityOfElementLocated(SUGGESTIONS_LIST));
    }

    public void setCheckInDate() {
        wait.until(visibilityOfElementLocated(CHECKIN_CALENDAR_BODY));
        List<WebElement> daysOfLastWeek = webDriver.findElement(CHECKIN_CALENDAR_BODY)
                .findElements(CHECKIN_CURRENT_MONTH_LAST_WEEK);
        daysOfLastWeek.get(daysOfLastWeek.size() - 1).click();
        wait.until(invisibilityOfElementLocated(CHECKIN_CALENDAR_BODY));
    }

    public void setCheckOutDate() {
        wait.until(elementToBeClickable(CHECK_OUT_DATEPICKER));
        webDriver.findElement(CHECK_OUT_DATEPICKER).click();
        wait.until(visibilityOfElementLocated(CHECKOUT_CALENDAR_BODY));
        webDriver.findElement(CHECKOUT_CALENDAR_BODY)
                .findElements(CHECKOUT_NEXT_MONTH_FIRST_WEEK)
                .get(0).click();
        wait.until(invisibilityOfElementLocated(CHECKOUT_CALENDAR_BODY));
    }

    public void setNumberOfAdults(Integer count) {
        wdUtils.selectValue(ADULTS_SELECT, count.toString());
    }

    public void setNumberOfChildren(Integer count) {
        wdUtils.selectValue(CHILDREN_SELECT, count.toString());
    }

    public void setChildAge(Integer age) {
        wdUtils.selectValue(CHILD_AGE_SELECT, age.toString());
    }

    public void setNumberOfRooms(Integer count) {
        wdUtils.selectValue(NUMBER_OF_ROOMS, count.toString());
    }

    public void chooseTravelPurposeToWork() {
        wdUtils.waitAndClick(TRAVEL_PURPOSE_CHECKBOX);
    }

    public void submitSearchForm() {
        wdUtils.waitAndClick(SEARCH_BUTTON);
    }

}
