package parkbee.automation.acceptance.ui.steps;

import net.thucydides.core.annotations.Step;
import parkbee.automation.acceptance.ui.pages.ReservationPage;

public class ReservationSteps {
    private ReservationPage reservationPage;
    @Step("Change the parking end time in the calendar")
    public void setEndTime(String endTime) {
        reservationPage.setEndTime(endTime);
    }

    public void confirmTime() {
        reservationPage.confirmTime();
    }

    public void verifyNearestLocationUpdatedPrice(String newPrice) {
        reservationPage.verifyPrice(newPrice);
    }
}
