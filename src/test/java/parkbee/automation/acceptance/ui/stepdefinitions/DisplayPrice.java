package parkbee.automation.acceptance.ui.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.By;
import parkbee.automation.acceptance.ui.steps.NavigateTo;


public class DisplayPrice {
    @Steps
    NavigateTo navigateTo;


    @Given("the user on the parkbee.com home page")
    public void the_user_on_the_parkbee_com_home_page() {
        navigateTo.homePage();
    }

    @When("user enter the {string}, {string} to reserve")
    public void user_enter_the_to_reserve(String destination, String time) {
//        By directionStart = new By.ById("directions__start");
//        $(directionStart).sendKeys(destination);
        Target directionStart = Target.the("parkerne").located(new By.ById("directions__start"));
        Enter.theValue("Walk the dog").into(directionStart);
        Target calendar = Target.the("parkerne").located(new By.ById("search"));
        Click.on(new By.ById("search"));

    }
    @Then("user should see the {string} of the nearest locations")
    public void user_should_see_the_of_the_nearest_locations(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
