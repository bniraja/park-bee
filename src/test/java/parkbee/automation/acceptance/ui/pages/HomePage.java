package parkbee.automation.acceptance.ui.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import parkbee.automation.acceptance.ui.ElementIsDisabledException;
import parkbee.automation.acceptance.utils.DateUtil;

import java.time.Duration;
import java.util.List;


@DefaultUrl("page:home.page")
public class HomePage extends PageObject {
    public static final String SEARCH = "search";
    public static final int TIMEOUT_IN_MILISECOND = 12000;
    private static String DIRECTION_START = "directions__start";

    WebElementFacade destination;

    public void enterDestination(String destination) {
        WebElement destinationElement = getDriver().findElement(new By.ById(DIRECTION_START));
        destinationElement.sendKeys(destination);
        destinationElement.sendKeys(Keys.TAB);

    }

    public void selectStartTime(String day) throws InterruptedException {
        List<WebElement> allDates = getDriver().findElements(By.xpath("//table[@class='table-condensed']//td"));
        try {
            DateUtil.clickGivenDay(allDates, day);
        } catch (ElementIsDisabledException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void confirmTime() {
        WebElement confirmTimeElement = getDriver().findElement(new By.ByClassName("timepicker-confirm"));
        confirmTimeElement.click();
    }

    public void verifyNearestLocation(String price) {
        WebElement cardPrice;
        try {
            Thread.sleep(TIMEOUT_IN_MILISECOND);
        } catch (InterruptedException e) {
            //TODO
            e.printStackTrace();
        }

        cardPrice = waitForCondition().until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.card__price"))).get(0);

        Assert.assertTrue("Expected prices is not same actual is:" + cardPrice.getText(), price.equalsIgnoreCase(cardPrice.getText()));
    }
}
