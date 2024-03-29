package parkbee.automation.acceptance.ui.pages;

import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parkbee.automation.acceptance.ui.exception.ElementIsDisabledException;
import parkbee.automation.acceptance.utils.DateUtil;

import java.util.List;

import static parkbee.automation.acceptance.ui.pages.HomePage.TIMEOUT_IN_MILISECOND;

public class ReservationPage extends PageObject {
    private static Logger LOGGER = LoggerFactory.getLogger(ReservationPage.class);

    public void setEndTime(String endTime) {
        WebElement endTimeBox = getDriver().findElement(By.xpath("//div[@class='header__search__field header__search__field--date is-end']"));
        endTimeBox.click();
        List<WebElement> allDates = getDriver().findElements(By.xpath("//table[@class='table-condensed']//td"));
        try {
            DateUtil.clickGivenDay(allDates, endTime);
        } catch (ElementIsDisabledException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void confirmTime() {
        WebElement confirmTime = getDriver().findElement(By.xpath("//div[@class='timepicker-confirm']"));
        confirmTime.click();
    }

    public void verifyPrice(String newPrice) {
        try {
            Thread.sleep(TIMEOUT_IN_MILISECOND);
        } catch (InterruptedException e) {
            LOGGER.error("Error during sleep " + e.getMessage());
        }

        WebElement cardPrice = waitForCondition().until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.card__price"))).get(0);

        Assert.assertTrue("Expected prices is not same actual is:" + cardPrice.getText(), newPrice.equalsIgnoreCase(cardPrice.getText()));
    }
}
