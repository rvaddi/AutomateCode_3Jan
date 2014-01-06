package com.amadeus.selenium.sqmobile.page.search;

import org.openqa.selenium.By;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class FareDealFlightSearchPage extends CommonSearchPage{

  public FareDealFlightSearchPage(SeleniumSEPTest test) throws Exception {
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_SEARCH, 30);
    if(elementPresent){
      reporter.reportPassed(getName(), "FareDealFlightSearch Page loaded");
    }else{
      reporter.fail("FareDealFlightSearch Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "MeRCI - Book flight");
    reporter.report("CHECKPOINT", "FareDealFlightSearch PAGE is DISPLAYED");
  }


  //LOCATORS - FareCondtionsPage------------------------------------------------

  protected static By LOC_TAB_FARE_CONDITIONS = By.className("fare-cond");
  protected static By LOC_BUTTON_SEARCH = By.cssSelector(".validation");
  protected static By LOC_CALENDAR_ICON = By.className("ui-datepicker-trigger");
  protected static By LOC_CALENDAR_MONTH = By.className("ui-datepicker-month");
  protected static By LOC_CALENDAR_YEAR = By.className("ui-datepicker-year");
  protected static By LOC_CALENDAR_DATE = By.cssSelector(".ui-state-default");

  //----------------------------------------------------------------------------



  /**
   * Validates FareDealFlightSearchPage
   * @throws Exception
   * @author bsingh
   */
  public void validateFareDealFlightSearchPage() throws Exception{
    validateFlexi();
    validateFlexi();
    validatePAXCount();
  }


  /**
   * Clicks FareConditions Tab
   * @throws Exception
   * @author bsingh
   */
  public void clickFareConditions() throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_TAB_FARE_CONDITIONS, "FareConditions Tab couldn't be clicked", "FareConditions Tab clicked successfully" );
  }

  /**
   * Fills the data and searches the flight with  deals and offers
   * @throws Exception
   */
  public void actionSearchFlight() throws Exception {

    ClickUtils.clickButtonOrFail(getTest(), LOC_CALENDAR_ICON, "Calendar Icon clicked");
    fillDeptDate(Integer.parseInt(getValue("DEPARTURE GAP")), LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    ClickUtils.clickButtonOrFail(getTest(), LOC_CALENDAR_ICON, "Calendar Icon clicked");
    fillRetDate(Integer.parseInt(getValue("RETURN GAP")), LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    handleFlexiDates();
    selectPAXCountAndCabin();
    this.automaticScreenShot();
    clickSearchButton();
  }


  /**
   * Fills Departure Date
   * @param daysGap the gap after which date is to be filled in the fiel
   * @param LOC_CALENDAR_DATE reference for the date
   * @param LOC_CALENDAR_MONTH reference for the month
   * @param LOC_CALENDAR_YEAR reference for year
   * @throws Exception
   */
  public void fillDeptDate(int daysGap ,By LOC_CALENDAR_DATE , By LOC_CALENDAR_MONTH , By LOC_CALENDAR_YEAR) throws Exception {
    CommonUtils utils = new CommonUtils(getTest());
    String deptDate = utils.fillDateUsingCalendar(daysGap, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    updateValue("DEPARTURE DATE", deptDate);
  }


  /**
   * Fills Return Date
   * @param daysGap the gap after which date is to be filled in the fiel
   * @param LOC_CALENDAR_DATE reference for date
   * @param LOC_CALENDAR_MONTH reference for month
   * @param LOC_CALENDAR_YEAR reference for year
   * @throws Exception
   */
  public void fillRetDate(int daysGap ,By LOC_CALENDAR_DATE , By LOC_CALENDAR_MONTH , By LOC_CALENDAR_YEAR) throws Exception {
    CommonUtils utils = new CommonUtils(getTest());
    String retDate = utils.fillDateUsingCalendar(daysGap, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    updateValue("RETURN DATE", retDate);
  }

  


}
