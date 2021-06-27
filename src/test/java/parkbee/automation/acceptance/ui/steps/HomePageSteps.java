package parkbee.automation.acceptance.ui.steps;

import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parkbee.automation.acceptance.ui.pages.HomePage;

public class HomePageSteps {
    private static Logger LOGGER = LoggerFactory.getLogger(HomePageSteps.class);
    private HomePage homePage;

    @Step("Enter the location")
    public void enterDestination(String destination) {
        homePage.enterDestination(destination);
    }

    @Step("Select the parking time in the calendar")
    public void selectStartTime(String time) {
        try {
            homePage.selectStartTime(time);
        } catch (InterruptedException e) {
            LOGGER.error("Error during sleep " + e.getMessage());
        }
    }

    @Step("Search for the given location and time")
    public void confirmTime() {
        homePage.confirmTime();
    }

    @Step("verified whether nearby parking location")
    public void verifyNearestLocation(String price) {
        homePage.verifyNearestLocation(price);
    }

    public void verifyErrorThatOldDateDisabled(String currentDayPlus, String errorMessage) {
        homePage.verifyDateIsDisabled(currentDayPlus, errorMessage);
    }
}
