package parkbee.automation.acceptance.ui.steps;

import net.thucydides.core.annotations.Step;
import parkbee.automation.acceptance.ui.pages.ReservationPage;

public class ReservationSteps {
    private ReservationPage reservationPage;

    @Step("Change the parking end time in the calendar")
    public void setEndTime(String endTime) {
        reservationPage.setEndTime(endTime);
    }

    @Step("confirm the selected time")
    public void confirmTime() {
        reservationPage.confirmTime();
    }

    @Step("verify the calculated price for the nearest garage")
    public void verifyNearestLocationUpdatedPrice(String newPrice) {
        reservationPage.verifyPrice(newPrice);
    }
}
