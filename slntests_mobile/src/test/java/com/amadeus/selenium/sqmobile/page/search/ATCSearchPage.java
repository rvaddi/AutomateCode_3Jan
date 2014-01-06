package com.amadeus.selenium.sqmobile.page.search;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.availabililty.ATCAvailablityPage;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;
/**
 * ATC Search Page
 * @author Vigneshwaran Balasubramanian
 */
public class ATCSearchPage extends SqMobileCommonPage{

  // Locators ATCSearch Page ------------------------------------------------------------

  protected final static By LOC_WL_TRIP_PANEL = By.cssSelector(".panel.trip");
  protected final static By LOC_CB_SELECT_LOCATION = By.cssSelector("#check1");
  protected final static MessageFormat LOC_WL_CHANGE_DATE = new MessageFormat("Month{0}");
  protected final static By LOC_IN_CHANGE_DATE = By.cssSelector(".Month1");
  protected final static By LOC_BT_CONTINUE = By.cssSelector(".validation");
  protected final static By LOC_IN_DEPARTURE_CITY = By.cssSelector(".departure>span.city");
  protected final static By LOC_IN_ARRIVAL_CITY = By.cssSelector(".arrival>span.city");
  protected final static By LOC_IN_DATE_OF_BOOKING  = By.cssSelector(".date>time");
  protected final static By LOC_IN_FLIGHT_NO = By.cssSelector(".flight-number>strong");
  protected final static By LOC_IN_DEPARTURE_LOCATION = By.cssSelector(".departure>abbr");
  protected final static By LOC_IN_ARRIVAL_LOCATION = By.cssSelector(".arrival>abbr");
  protected final static By LOC_CALENDAR_ICON = By.className("ui-datepicker-trigger");
  protected final static By LOC_CALENDAR_MONTH = By.className("ui-datepicker-month");
  protected final static By LOC_CALENDAR_YEAR = By.className("ui-datepicker-year");
  protected final static By LOC_CALENDAR_DATE = By.cssSelector(".ui-state-default");

  //------------------------------------------------------------------------------------


  public ATCSearchPage(SeleniumSEPTest test) throws Exception{
    super(test);
       // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_BT_CONTINUE, 30)){
      reporter.fail("This is not ATCSearch Page");
    }else{
      reporter.reportPassed("ATCSearch Page", "In ATCSearch Page");
    }
  }


  /**
   * Validate ATC Search Page
   * @throws IOException
   */
  public void validateATCSearchPage() throws IOException{
    validateDepartureSegment();
    validateArrivalSegment();
  }

  /**
   * Validate Departure Segment
   * @throws IOException
   */
  private void validateDepartureSegment() throws IOException{
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_WL_TRIP_PANEL, "Trip Panel");
    WebElement departureSegment = CheckUtils.getElements(getTest(), LOC_WL_TRIP_PANEL).get(0);
    WebElement departureLocation = CheckUtils.getElement(getTest(), departureSegment, LOC_IN_DEPARTURE_CITY);
    if(departureLocation!=null && departureLocation.isDisplayed() && departureLocation.equals("")){
      reporter.reportPassed("Validate Deaparture Location", departureLocation.getText());
    }
    else{
      reporter.fail("Invalid Departure Location");
    }

    WebElement arrivalLocation = CheckUtils.getElement(getTest(), departureSegment, LOC_IN_ARRIVAL_CITY);
    if(arrivalLocation!=null && arrivalLocation.isDisplayed()){
      if(!arrivalLocation.getText().equals("")){
      reporter.reportPassed("Validate Arrival Location", arrivalLocation.getText());
      }else{
        reporter.reportFailed("Invalid Arrival Location", arrivalLocation.getText());
      }
    }
    else{
      reporter.fail("Arrival Location Elt is not displayed");
    }

    WebElement dateofBooking = CheckUtils.getElement(getTest(), departureSegment,LOC_IN_DATE_OF_BOOKING);
    if(dateofBooking!=null && dateofBooking.isDisplayed()&& !dateofBooking.equals("")){
      reporter.reportPassed("Validate Datae of Booking", "Date of booking matches");
    }
    else{
      reporter.fail("Date of Booking invalid");
    }

    WebElement flightNo = CheckUtils.getElement(getTest(), departureSegment,LOC_IN_FLIGHT_NO);
    if(flightNo!=null && flightNo.isDisplayed() && flightNo.equals(getValue("DEPARTURE FLIGHT NUMBER").trim())){
      reporter.reportPassed("Validate Flight No",getValue("DEPARTURE FLIGHT NUMBER"));
    }
    else{
      reporter.fail("Flight is not displayed properly");
    }
  }
  /**
   * Validate Arrival Segment
   * @throws IOException
   */
  private void validateArrivalSegment() throws IOException{
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_WL_TRIP_PANEL, "Trip Panel");
    WebElement arrivalSegment = CheckUtils.getElements(getTest(), LOC_WL_TRIP_PANEL).get(1);

    WebElement departureLocation = CheckUtils.getElement(getTest(), arrivalSegment, LOC_IN_DEPARTURE_CITY);
    if(departureLocation!=null && departureLocation.isDisplayed() && departureLocation.equals("")){
      reporter.reportPassed("Validate Deaparture Location", "Departure Location matches");
    }
    else{
      reporter.fail("Invalid Departure Location");
    }

    WebElement arrivalLocation = CheckUtils.getElement(getTest(), arrivalSegment, LOC_IN_ARRIVAL_CITY);
    if(arrivalLocation!=null && arrivalLocation.isDisplayed()&& arrivalLocation.equals("")){
      reporter.reportPassed("Validate Arrival Location", "Arrival Location matches");
    }
    else{
      reporter.fail("Invalid Arrival Location");
    }

    WebElement dateofBooking = CheckUtils.getElement(getTest(), arrivalSegment,LOC_IN_DATE_OF_BOOKING);
    if(dateofBooking!=null && dateofBooking.isDisplayed()&&dateofBooking.equals("")){
      reporter.reportPassed("Validate Datae of Booking", "Date of booking matches");
    }
    else{
      reporter.fail("Date of Booking invalid");
    }

    WebElement flightNo = CheckUtils.getElement(getTest(), arrivalSegment,LOC_IN_FLIGHT_NO);
    if(flightNo!=null && flightNo.isDisplayed() && flightNo.equals(getValue("DEPARTURE FLIGHT NUMBER").trim())){
      reporter.reportPassed("Validate Flight No", flightNo.getText());
    }
    else{
      reporter.fail("Flight Number is invalid");
    }
  }
  /**
   * Change Departure Date
   * @throws Exception
   */
  public void changeDepartureDate() throws Exception{
    int noOfDays = 31;
    int locationIndex = 0;
    String departureLocation;
    String departureLocationConcat = "";
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_WL_TRIP_PANEL, "Trip Panel");
    WebElement departuretrip = CheckUtils.getElements(getTest(), LOC_WL_TRIP_PANEL).get(locationIndex);

    CommonUtils utils = PageFactory.getPageObject(CommonUtils.class);
    String dateFormat = utils.addDate("dd MMMM yyyy",  new Integer(60));
    String Month = (dateFormat.split(" "))[1];
    String Year = (dateFormat.split(" "))[2];

    WebElement depCheckBox = CheckUtils.getElement(getTest(),departuretrip, LOC_CB_SELECT_LOCATION);
    ClickUtils.click(getTest(), depCheckBox);

    //To be added when integeration
    /*WebElement dateofBooking = CheckUtils.getElement(getTest(),departuretrip, LOC_IN_DATE_OF_BOOKING);
     if(dateofBooking.getText().equals(getValue("DEPARTURE DATE").trim())){
      reporter.reportPassed("Departure Date", "Departure Date Matches");
    }
    else{
      reporter.fail("Departure Date on booking done not matching");
    }*/

    List<WebElement> depLocation = CheckUtils.getElements(getTest(), departuretrip,LOC_IN_DEPARTURE_LOCATION);
    for(WebElement depElt:depLocation){
      departureLocation = depElt.getText();
      departureLocation = departureLocation.substring(departureLocation.indexOf('(')+1,departureLocation.indexOf(')'));
      departureLocationConcat =departureLocationConcat+departureLocation;
    }
    addValue("DEPARTURE LOCATION",departureLocationConcat);
    WebElement dateFromDropDownElt = CheckUtils.getElement(getTest(), LOC_IN_CHANGE_DATE);
    if(dateFromDropDownElt!=null){
      Object formatIndex[] = {locationIndex};
      FillUtils.fillSelectByVisibleTextOrFail(getTest(), By.name(LOC_WL_CHANGE_DATE.format(formatIndex)), Month+" "+Year,"Date not selected");
    }

    WebElement dateFromCalendar = CheckUtils.getElement(getTest(), LOC_CALENDAR_ICON);
    if(dateFromCalendar!=null && dateFromCalendar.isDisplayed()){
      fillDeptDateUsingCalendar(noOfDays,locationIndex);
    }

  }

  /**
   * Change Arrival Date
   * @throws Exception
   */
  public void changeArrivalDate() throws Exception{
    int locationIndex = 1;
    int noOfDays = 45;

    String arrivalLocation;
    String arrivalLocationConcat = "";
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_WL_TRIP_PANEL, "Trip Panel");
    WebElement arrivaltrip = CheckUtils.getElements(getTest(), LOC_WL_TRIP_PANEL).get(locationIndex);

    CommonUtils utils = PageFactory.getPageObject(CommonUtils.class);
    String dateFormat = utils.addDate("dd MMMM yyyy",  new Integer(noOfDays));
    String Month = (dateFormat.split(" "))[1];
    String Year = (dateFormat.split(" "))[2];

    WebElement arrCheckBox = CheckUtils.getElement(getTest(),arrivaltrip, LOC_CB_SELECT_LOCATION);
    ClickUtils.click(getTest(), arrCheckBox);

    //To be added during integeration
    /*WebElement dateofBooking = CheckUtils.getElement(getTest(),arrivaltrip, LOC_IN_DATE_OF_BOOKING);
     if(dateofBooking.getText().equals(getValue("ARRIVAL DATE").trim())){
      reporter.reportPassed("Arrival Date", "Arrival Date Matches");
    }
    else{
      reporter.fail("Arrival Date on booking done not matching");
    }*/

    List<WebElement> arrLocation = CheckUtils.getElements(getTest(), arrivaltrip,LOC_IN_ARRIVAL_LOCATION);
    for(WebElement arrElt:arrLocation){
      arrivalLocation = arrElt.getText();
      arrivalLocation = arrivalLocation.substring(arrivalLocation.indexOf('(')+1,arrivalLocation.indexOf(')'));
      arrivalLocationConcat =arrivalLocationConcat+arrivalLocation;
    }

    addValue("ARRIVAL LOCATION",arrivalLocationConcat);

    WebElement dateFromDropDownElt = CheckUtils.getElement(getTest(), LOC_IN_CHANGE_DATE);
    if(dateFromDropDownElt!=null){
      Object formatIndex[] = {locationIndex};
      FillUtils.fillSelectByVisibleTextOrFail(getTest(), By.name(LOC_WL_CHANGE_DATE.format(formatIndex)), Month+" "+Year,"Date not selected");
    }

    WebElement dateFromCalendar = CheckUtils.getElement(getTest(), LOC_CALENDAR_ICON);
    if(dateFromCalendar!=null && dateFromCalendar.isDisplayed()){

      fillDeptDateUsingCalendar(noOfDays,locationIndex);
    }
  }


  /**
   * Click Continue button
   * @throws Exception
   */
  public ATCAvailablityPage clickContinue() throws Exception{
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_BT_CONTINUE, "Continue Button");
    ClickUtils.clickButtonOrFail(getTest(), LOC_BT_CONTINUE, "Continue Button not present or clicked");
    WaitUtils.wait(10);
    ATCAvailablityPage objATCAtcAvailablityPage = PageFactory.getPageObject(ATCAvailablityPage.class);
    return objATCAtcAvailablityPage;

  }

  /**
   * Fills the departure date using calendar
   * @throws Exception
   */
  private void fillDeptDateUsingCalendar(int noOfDays , int index) throws Exception {

    List<WebElement> calendarIconsElt = CheckUtils.getElements(getTest(), LOC_CALENDAR_ICON);

    if(calendarIconsElt!=null && calendarIconsElt.size()>0){
      ClickUtils.clickButtonOrFail(getTest(), calendarIconsElt.get(index), "Calendar couldn't be clicked", "Calendar clicked successfully");
      CommonUtils utils = PageFactory.getPageObject(CommonUtils.class);
      String dateAfterGap = utils.addDate("dd MMMM yyyy", new Integer(noOfDays));
      String date = (dateAfterGap.split(" "))[0];
      String month = (dateAfterGap.split(" "))[1] ;
      String year =(dateAfterGap.split(" "))[2];

      if(index==0){
        updateValue("DEPARUTRE DATE", date+" "+month+" "+year);
      }
      else{
        updateValue("ARRIVAL DATE", date+" "+month+" "+year);
      }

      FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_MONTH, month, "Month in the calendar couln't be filled properly");
      FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_YEAR, year, "Month in the calendar couln't be filled properly");

      selectCalendarDate(date);

    }else{
      reporter.reportFailed("ATCSearchPage : ", "CalendarIcon for Departure couldn't be clicked");
    }
  }

  /**
   * Clicks on the date in the calendar as passed in the argument
   * @param date Date which is to be clicked in the calendar
   * @throws Exception
   */
  public void selectCalendarDate(String date) throws Exception{
    List<WebElement> dateList = CheckUtils.getElements(getTest(), LOC_CALENDAR_DATE);

    for(WebElement dateElt : dateList){
      if(dateElt.getText().equalsIgnoreCase(date)){
        ClickUtils.click(getTest(), dateElt);
        reporter.reportPassed("Date :", "Date selected successfully in calendar");
        break;
      }
    }
  }

}
