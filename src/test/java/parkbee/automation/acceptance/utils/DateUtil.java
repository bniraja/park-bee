package parkbee.automation.acceptance.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.WebElement;
import parkbee.automation.acceptance.ui.exception.ElementIsDisabledException;

public class DateUtil {
    //Get The Current Day
    public static String getCurrentDay() {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        return todayStr;
    }

    //Get The Current Day plus days. You can change this method based on your needs.
    public static String getCurrentDayPlus(int days) {
        LocalDate currentDate = LocalDate.now();
        int selectedDate = currentDate.getDayOfMonth() + days;
        return Integer.toString(selectedDate);
    }

    public static void clickGivenDay(List<WebElement> elementList, String day) throws ElementIsDisabledException {
        for (
            WebElement cell : elementList) {
            String cellText = cell.getText();
            if (cellText.contains(day)) {
                cell.isEnabled();
                if (cell.getAttribute("class").contains("disabled")) {
                    throw new ElementIsDisabledException("Selected" + cell.getTagName() + " Date is disabled " + day);
                }
                cell.click();
                break;
            }
        }
    }
}