package parkbee.automation.acceptance.ui.steps;

import net.thucydides.core.annotations.Step;
import parkbee.automation.acceptance.ui.pages.HomePage;

public class NavigateTo {
    HomePage homePage;

    @Step("Open the home page")
    public void homePage() {
        homePage.open();
    }
}
