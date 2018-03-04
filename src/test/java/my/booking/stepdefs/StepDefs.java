package my.booking.stepdefs;

import org.openqa.selenium.WebDriver;

import com.google.inject.Inject;
import cucumber.api.java8.En;
import my.booking.page.components.PropertiesFilter;
import my.booking.page.components.MainSearchForm;
import my.booking.page.components.SearchResults;
import my.booking.page.components.GiftPopup;
import my.booking.page.components.Header;

public class StepDefs implements En {

    @Inject
    private WebDriver webDriver;

    @Inject
    private MainSearchForm homePage;

    @Inject
    private GiftPopup giftPopup;

    @Inject
    private PropertiesFilter filter;

    @Inject
    private SearchResults searchResultsPage;

    @Inject
    private Header header;

    public StepDefs() {

        Given("^client is at a (.*) main page$", (String website) -> {
            webDriver.get(website);
            giftPopup.submitIfPresent();
            header.expectElements();
            homePage.expectElements();
        });

        Given("^currency is set to (.*)$",
                (String currency) -> header.chooseCurrency(currency)
        );

        Given("^interface language is set to (.*)",
                (String language) -> header.chooseLanguage(language));

        When("^client chooses \"([^\"]*)\" as a destination$", (String searchTerm) ->
                homePage.enterSearchTerm(searchTerm)
        );

        When("^he sets checkin date as a last day of current month$", () -> homePage.setCheckInDate());

        When("^he sets checkout date as a first day of next month$", () -> homePage.setCheckOutDate());

        When("^he sets number of adults to (\\d+)$", (Integer count) -> homePage.setNumberOfAdults(count));

        When("^he sets number of children to (\\d+)$", (Integer count) -> homePage.setNumberOfChildren(count));

        When("^he sets child age to (\\d+) years$", (Integer age) -> homePage.setChildAge(age));

        When("^he sets number of rooms to (\\d+)$", (Integer count) -> homePage.setNumberOfRooms(count));

        When("^he sets he's travelling for work$", () -> homePage.chooseTravelPurposeToWork());

        When("^he submits search form$", () -> homePage.submitSearchForm());

        When("^he chooses only available properties$", () -> filter.chooseToShowAvailableOnly());

        When("^he chooses only properties with review rate above 8$",
                () -> filter.chooseToShowOnlyHighlyRated());

        Then("^search results contain items with price less than (.*) and rating more than (.*)$",
                (Double highestPrice, Double leastReviewScore) ->
                        searchResultsPage.checkAtLeastSomeMeetTheCriteria(leastReviewScore, highestPrice)
        );
    }
}
