package parkbee.automation.acceptance.utils;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;

public class SetTheStage {
    EnvironmentVariables environmentVariables;

//    @Before
//    public void recruitTheActors() {
//        OnStage.setTheStage(new Cast());
//    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

}
