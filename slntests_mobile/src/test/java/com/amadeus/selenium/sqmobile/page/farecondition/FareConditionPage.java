package com.amadeus.selenium.sqmobile.page.farecondition;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class FareConditionPage extends CommonFareConditionPage {

  public FareConditionPage(SeleniumSEPTest test) {
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_FareDeal_Search_Button, 120);
    if(elementPresent){
      reporter.reportPassed(pageName, "Fare Condition Page loaded");
    }else{
      reporter.fail("Fare Condition Page NOT loaded");
    }
  }

  String pageName = "FareConditionPage";

  /**
   * Fills Fare Condition page
   * 
   * @throws Exception
   * @author Sankar
   */
  public void Fillfareconditionpage() throws Exception {

    FillDepRetDate();

    if(getValue("Flexible Dates").equalsIgnoreCase("TRUE")){

      WebElement flexibledatecheckbox = CheckUtils.getElement(getTest(), LOC_FareDeal_Flexible_Date);

      if (flexibledatecheckbox != null && flexibledatecheckbox.isDisplayed()) {
        if (!flexibledatecheckbox.isSelected()) {

          ClickFlexibleDate();
        }
          String FlexibleDate = CheckUtils.getElement(getTest(), LOC_FareDeal_Flexible_Date_label).getText().trim();
          FlexibleDate = FlexibleDate.replaceAll("[^\\d]", "").trim();
          addValue("Flexible Dates Count", FlexibleDate);

      }else{
        reporter.reportFailed("Flexible Date Checkbox", "Flexible Date Checkbox element is not displayed in the page");
      }
    }

    if (!getValue("Nb Adult").isEmpty()) {
      FillAdults(getValue("Nb Adult"));
    }
    if (!getValue("Nb Child").isEmpty()) {
      FillChildren(getValue("Nb Child"));
    }
   }

  /**
   * Fills Departure and Return date using calendar
   * 
   * @throws Exception
   * @author Sankar
   */
  public void FillDepRetDate() throws Exception {

    String DepDate = fillDeptDateUsingCalendar(Integer.parseInt(getValue("Dep Gap")));
    addValue("Departure Date", DepDate);

    if (getValue("Trip Type").equalsIgnoreCase("RT")) {

      String RetDate = fillReturnDateUsingCalendar(Integer.parseInt(getValue("Ret Gap")));
      addValue("Return Date", RetDate);
    }
  }

  /**
   * Fills Maximum number of Adults and Children
   * 
   * @throws IOException
   * @author Sankar
   */
  public void FillMaxPassenger() throws IOException {

    int adults;
    int child;

    adults = Math.round((Float.parseFloat(getValue("Max Pax"))) / 2);
    child = Integer.parseInt(getValue("Max Pax")) - adults;

    FillAdults(Integer.toString(adults));
    FillChildren(Integer.toString(child));

    addValue("Max Adults", Integer.toString(adults));
    addValue("Max Childern", Integer.toString(adults));
  }

  /**
   * Fills Minimum number of Adults and Children
   * 
   * @throws IOException
   * @author Sankar
   */
  public void FillMinPassenger() throws IOException {
    int adults;
    int child;

    adults = Math.round((Float.parseFloat(getValue("Min Pax"))) / 2);
    child = Integer.parseInt(getValue("Min Pax")) - adults;

    FillAdults(Integer.toString(adults));
    FillChildren(Integer.toString(child));
    addValue("Min Adults", Integer.toString(adults));
    addValue("Min Childern", Integer.toString(child));
  }

  /**
   * Fills Maximum stay period
   * 
   * @throws Exception
   * @author Sankar
   */
  public void FillMaxStay() throws Exception {

    fillDeptDateUsingCalendar(Integer.parseInt("0"));
    fillReturnDateUsingCalendar(Integer.parseInt(getValue("Max Stay")));

    addValue("Max Departure Date", CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_DepDate).getText().trim());
    addValue("Max Return Date", CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_RetDate).getText().trim());
  }

  /**
   * Fills Minimum stay period
   * 
   * @throws Exception
   * @author Sankar
   */
  public void FillMinStay() throws Exception {

    fillDeptDateUsingCalendar(Integer.parseInt("0"));
    fillReturnDateUsingCalendar(Integer.parseInt(getValue("Min Stay")));

    addValue("Min Departure Date", CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_DepDate).getText().trim());
    addValue("Min Return Date", CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_RetDate).getText().trim());
  }

  /**
   * Validate Maximum passenger restriction
   * 
   * @throws IOException
   * @author Sankar
   */
  public void ValidateMaxPaxRestriction() throws IOException {
    ValidateErrorMessage("Max Pax", "Passenger Restriction", "Max Adults", "Max Childern");
  }

  /**
   * Validate Minimum passenger restriction
   * 
   * @throws IOException
   * @author Sankar
   */
  public void ValidateMinPaxRestriction() throws IOException {
    ValidateErrorMessage("Min Pax", "Passenger Restriction", "Min Adults", "Min Childern");
  }

  /**
   * Validate Maximum stay restriction
   * 
   * @throws IOException
   * @author Sankar
   */
  public void ValidateMaxStayRestriction() throws IOException {
    ValidateErrorMessage("Max Stay", "Stay Restriction", "Max Departure Date", "Max Return Date");
  }

  /**
   * Validate Minimum stay restriction
   * 
   * @throws IOException
   * @author Sankar
   */
  public void ValidateMinStayRestriction() throws IOException {
    ValidateErrorMessage("Min Stay", "Stay Restriction", "Min Departure Date", "Min Return Date");
  }

  /**
   * Validate Calendar is Enabled
   * 
   * @throws Exception
   * @author Sankar
   */
  public void ValidateCalendarDatesEnabled() throws Exception {
    ValidateCalendarDates("Enabled");
  }

  /**
   * Validate Calendar is Disabled
   * 
   * @throws IOException
   * @author Sankar
   */
  public void ValidateCalendarDatesDisabled() throws Exception {
    ValidateCalendarDates("Disabled");
  }
}
