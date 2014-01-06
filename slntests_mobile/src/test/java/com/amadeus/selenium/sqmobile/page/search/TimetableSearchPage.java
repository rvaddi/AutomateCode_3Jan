package com.amadeus.selenium.sqmobile.page.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.availabililty.TimetableResultPage;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class TimetableSearchPage extends SqMobileCommonPage{
  protected static final By LOC_TEXT_FROM =By.id("B_LOCATION_1");
  protected static final By LOC_TEXT_TO =By.id("E_LOCATION_1");
  protected static final By LOC_RADIO_RT = By.id("roundTrip");
  protected static final By LOC_RADIO_OW = By.id("oneWay");
  protected static final By LOC_TIMETABLE_AUTOCOMPLETE = By.className("ui-menu-item");
  protected static final By LOC_BUTTON_GET_TIMETABLE = By.className("validation");
  protected static final By LOC_DEPT_DATE = By.id("depdate");
  protected static final By LOC_RETURN_DATE = By.id("retdate");
  protected static final By LOC_SELECT_DEPT_DATE = By.id("Date");
  protected static final By LOC_SELECT_DEPT_MONTH = By.id("Month");
  protected static final By LOC_SELECT_DEPT_YEAR = By.id("Year");
  protected static final By LOC_SELECT_RETURN_DATE = By.id("date_e");
  protected static final By LOC_SELECT_RETURN_MONTH = By.id("month_e");
  protected static final By LOC_SELECT_RETURN_YEAR = By.id("year_e");
  protected static final By LOC_CALENDAR_ICON = By.className("ui-datepicker-trigger");
  protected static final By LOC_CALENDAR_MONTH = By.className("ui-datepicker-month");
  protected static final By LOC_CALENDAR_YEAR = By.className("ui-datepicker-year");
  protected static final By LOC_CALENDAR_DATE = By.cssSelector(".ui-state-default");
  public CommonUtils utils;

  public TimetableSearchPage(SeleniumSEPTest test) throws Exception{
    super(test);
    utils =  PageFactory.getPageObject(CommonUtils.class);

    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_GET_TIMETABLE, 30);
    if(elementPresent){
      reporter.reportPassed("Timetable Search Page", "In TimetableSearch Page");
    }else{
      reporter.fail("TimetableSearch Page not loaded ");
    }
  }

  public enum TripType {
    RT , OW ;
  }

  public enum Field {
    From , To ;
  }




  /**
   * Fills the From Field of the search
   * @param from the text to be entered in the From Field of search
   * @author bsingh
   */

  public void fillFromField(String from) {
    FillUtils.fillInputOrFail(getTest(), LOC_TEXT_FROM, from, "From Field couldn't be filled");
    reporter.reportPassed("Timetable Search Page", "From field filled properly");
  }


  /**
   *  Fills the text in To Field of the search
   * @param to text to be filled in To Field of search
   * @author bsingh
   */
  public void fillToField(String to) {
    FillUtils.fillInputOrFail(getTest(), LOC_TEXT_TO, to, "From Field couldn't be filled");
    reporter.reportPassed("Timetable Search Page", "To field filled properly");
  }


  /**
   * Selects the type of trip , checks the radio button either for RoundTrip or for OneWay Trip
   * @param tripType either OW (for OneWay) or RT (for RoundTrip)
   * @author bsingh
   */
  public void selectTripType(TripType tripType) {
    if(tripType.toString().equals(TripType.RT)){
      ClickUtils.clickButtonOrFail(getTest(), LOC_RADIO_RT, "RoundTrip RadioButton couldn't be clicked" ,"RoundTrip RadioButton clicked successfully");
      reporter.reportPassed("Timetable Search Page", "From field filled properly");
    }else {
      ClickUtils.clickButtonOrFail(getTest(), LOC_RADIO_OW, "OneWay RadioButton couldn't be clicked" , "OneWay RadioButton clicked successfully");
      reporter.reportPassed("Timetable Search Page", "oneway radio button clicked");
    }
  }


  /**
   * Validates whether the auto-complete of From and To Fields are working or not
   * @author bsingh
   */
  public void validateAutocompleteFunctionality(Field field , String value) {
    if(field.toString().equals("From")){
      fillFromField(value);
    }else{
      fillToField(value);
    }
    List<WebElement> itemList = CheckUtils.getElements(getTest(), LOC_TIMETABLE_AUTOCOMPLETE);
    if (itemList.size()>0) {
      reporter.reportPassed("Timetable Search Page", "Auto Complete menu list displayed properly");
    }
    else {
      reporter.reportFailed("Timetable Search Page", "Auto Complete menu list is NOT being displayed");
    }
  }

  /**
   * Clicks GetTimeTable Submit Button
   * @author bsingh
   */
  public void clickGetTimetable() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_GET_TIMETABLE, "GetTimeTable Button couldn't be clicked" , "GetTimetable Button clicked successfully");
    reporter.reportPassed("Timetable Search Page", "GetTimeTable button clicked");
  }



  /**
   * Validates that  the default departure date is after the number of days passed in the argument
   * @param deptGap number of days from the current day
   * @author bsingh
   */
  public void validateDefaultDeptDate(int deptGap) {

    String dateAfterGap = utils.addDate("d MMM yyyy" , deptGap);

    String depDate = (dateAfterGap.split(" "))[0];
    String depMonth = (dateAfterGap.split(" "))[1] ;
    String depYear =(dateAfterGap.split(" "))[2];

    Select date = new Select(CheckUtils.getElement(getTest(), LOC_SELECT_DEPT_DATE));
    String displayedDate = date.getFirstSelectedOption().getText();

    Select month = new Select(CheckUtils.getElement(getTest(), LOC_SELECT_DEPT_MONTH));
    String displayedMonth = month.getFirstSelectedOption().getText();

    Select year = new Select(CheckUtils.getElement(getTest(), LOC_SELECT_DEPT_YEAR));
    String displayedYear = year.getFirstSelectedOption().getText();

    if(depDate.equalsIgnoreCase(displayedDate) && depMonth.equalsIgnoreCase(displayedMonth) && depYear.equalsIgnoreCase(displayedYear)){
         reporter.reportPassed("TimeTable Page", "Default Departure date is displayed properly");
    }else{
        reporter.reportFailed("TimeTable Page", "Default Departure date is NOT displayed properly");
    }

  }



  /**
   * Validates whether defaultReturnDate is after the gap as mentioned in the arguments
   * @param returnGap  the gap for which the default arrival date is to checked
   * @author bsingh
   */
  public void validateDefaultReturnDate(int returnGap) {

    String dateAfterGap = utils.addDate("d MMM yyyy" , returnGap);

    String retDate = (dateAfterGap.split(" "))[0];
    String retMonth = (dateAfterGap.split(" "))[1] ;
    String retYear =(dateAfterGap.split(" "))[2];

    Select date = new Select(CheckUtils.getElement(getTest(), LOC_SELECT_RETURN_DATE));
    String displayedDate = date.getFirstSelectedOption().getText();

    Select month = new Select(CheckUtils.getElement(getTest(), LOC_SELECT_RETURN_MONTH));
    String displayedMonth = month.getFirstSelectedOption().getText();

    Select year = new Select(CheckUtils.getElement(getTest(), LOC_SELECT_RETURN_YEAR));
    String displayedYear = year.getFirstSelectedOption().getText();



    if(retDate.equalsIgnoreCase(displayedDate) && retMonth.equalsIgnoreCase(displayedMonth) && retYear.equalsIgnoreCase(displayedYear)){
         reporter.reportPassed("TimeTable Page", "Default Return date is displayed properly");
    }else{
        reporter.reportFailed("TimeTable Page", "Default Return date is NOT displayed properly");
    }

  }


  /**
   * Fills the departure date i.e. the date after the gap from the current date given in data excel-sheet
   * @throws Exception
   * @author bsingh
   */
  public void fillDepartureDate() throws Exception {

      String dateAfterGap = utils.addDate("d MMM yyyy", new Integer (getValue("Dep Gap").trim()));
      String depDate = (dateAfterGap.split(" "))[0];
      String depMonth = (dateAfterGap.split(" "))[1] ;
      String depYear =(dateAfterGap.split(" "))[2];
      FillUtils.selectByVisibleText(getTest(), LOC_SELECT_DEPT_DATE, depDate);
      FillUtils.selectByVisibleText(getTest(), LOC_SELECT_DEPT_MONTH,depMonth);
      FillUtils.selectByVisibleText(getTest(), LOC_SELECT_DEPT_YEAR, depYear);
      reporter.report("Timetable Search page","Departure date filled");

  }



  /**
   * Fills the return date i.e. the date after the gap from the current date given in data excel-sheet
   * @author bsingh
   */
  public void fillReturnDate() throws Exception {

      String dateAfterGap = utils.addDate("d MMM yyyy", new Integer (getValue("RETURN GAP").trim()));
      String retDate = (dateAfterGap.split(" "))[0];
      String retMonth = (dateAfterGap.split(" "))[1] ;
      String retYear =(dateAfterGap.split(" "))[2];
      FillUtils.selectByVisibleText(getTest(), LOC_SELECT_RETURN_DATE, retDate);
      FillUtils.selectByVisibleText(getTest(), LOC_SELECT_RETURN_MONTH,retMonth);
      FillUtils.selectByVisibleText(getTest(), LOC_SELECT_RETURN_YEAR, retYear);
      reporter.report("Timetable Search page","return date filled");

  }


  /**
   *  Fills the From Field , To Field (in case of RT TriptType) , Selects the TripType , Fills the Dates and clicks the GetTimetable Button
   * @param tripType Type Of the trip whether RT Or OW
   * @throws Exception
   * @author bsingh
   */
  public TimetableResultPage actionSearchTimetable(TripType tripType)throws Exception {

    fillFromField(getValue("FROM").trim());
    fillToField(getValue("TO").trim());
    selectTripType(tripType);
    fillDepartureDate();
    if(tripType.toString().equalsIgnoreCase("RT")){
     fillReturnDate();
    }
    this.automaticScreenShot();
    clickGetTimetable();
    return PageFactory.getPageObject(TimetableResultPage.class);
  }


  /**
   * Fills the departure date using calendar
   * @author bsingh
   */
  public void fillDeptDateUsingCalendar() throws Exception {

    List<WebElement> calendarIconsElt = CheckUtils.getElements(getTest(), LOC_CALENDAR_ICON);

    if(calendarIconsElt!=null && calendarIconsElt.size()>0){
    ClickUtils.clickButtonOrFail(getTest(), calendarIconsElt.get(0), "Calendar couldn't be clicked", "Calendar clicked successfully");
    reporter.report("Timetable Search page","Calendar clicked");

    String dateAfterGap = utils.addDate("d MMM yyyy", new Integer (getValue("DEPARTURE GAP").trim()));
    String depDate = (dateAfterGap.split(" "))[0];
    String depMonth = (dateAfterGap.split(" "))[1] ;
    String depYear =(dateAfterGap.split(" "))[2];

    FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_MONTH, depMonth, "Month in the calendar couln't be filled properly");
    reporter.report("Timetable Search page","Departure Month selected in calendar");
    FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_YEAR, depYear, "Month in the calendar couln't be filled properly");
    reporter.report("Timetable Search page","Departure Year selected in calendar");
    selectCalendarDate(depDate);

  }else{
    reporter.reportFailed("Timetable Search page", "CalendarIcon for Departure couldn't be clicked");
  }
  }


  /**
   * Fills the return date using calendar
   * @author bsingh
   */
  public void fillReturnDateUsingCalendar() throws Exception {

    List<WebElement> calendarIconsElt = CheckUtils.getElements(getTest(), LOC_CALENDAR_ICON);

    if(calendarIconsElt!=null && calendarIconsElt.size()>1){
    ClickUtils.clickButtonOrFail(getTest(), calendarIconsElt.get(1), "Calendar couldn't be clicked", "Calendar clicked successfully");

    String dateAfterGap = utils.addDate("d MMM yyyy", new Integer (getValue("RETURN GAP").trim()));

    String retDate = (dateAfterGap.split(" "))[0];
    String retMonth = (dateAfterGap.split(" "))[1] ;
    String retYear =(dateAfterGap.split(" "))[2];

    FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_MONTH, retMonth, "Month in the calendar couln't be filled properly");
    reporter.report("Timetable Search page","Return Month selected in calendar");
    FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_CALENDAR_YEAR, retYear, "Month in the calendar couln't be filled properly");
    reporter.report("Timetable Search page","Return year selected in calendar");
    selectCalendarDate(retDate);

  }else{
    reporter.reportFailed("Timetable Search page", "CalendarIcon for Return couldn't be clicked");
  }

  }


  /**
   * Clicks on the date in the calendar as passed in the argument
   * @param date Date which is to be clicked in the calendar
   * @author bsingh
   */
  public void selectCalendarDate(String date) {
    List<WebElement> dateList = CheckUtils.getElements(getTest(), LOC_CALENDAR_DATE);
    boolean dateSelected = false ;
    for(WebElement dateElt : dateList){
      if(dateElt.getText().equalsIgnoreCase(date)){
        ClickUtils.click(getTest(), dateElt);
        reporter.reportPassed("Timetable Search page", "Date selected successfully in calendar");
        dateSelected=true ;
        break;
      }
    }
    if(!dateSelected){
      reporter.reportFailed("Timetable Search page", "Date couldn't be selected");
    }
  }




}