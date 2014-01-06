package com.amadeus.selenium.sqmobile.page.calendar;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CalendarPage extends CommonCalendarPage{

  //LOCATORS - CALENDAR PAGE----------------------------------------------------


  protected static By LOC_LABEL_CALENDAR_FLIGHT_DETAILS = By.cssSelector(".panel.cale>header>h1");

  protected static By LOC_HEADER_CALENDAR_RETURN_DAYS = By.className("[id^='ch']");
  protected static By LOC_HEADER_CALENDAR_DEPT_DAYS = By.className("[id^='rh']");
  protected static final By LOC_BUTTON_CONTINUE= By.className("validation");

  String pageName = "Calendar Page";

  public CalendarPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_FareDeal_Matrix_Table, 120);
    if(elementPresent){
      reporter.reportPassed("Matrix Calender page", "Matrix Calendar Page loaded");
    }else{
      reporter.fail("Matrix Calendar Page NOT loaded ");
    }
  }


  //----------------------------------------------------------------------------


  /**
   * Validates Calendar Page
   * @throws Exception
   * @author bsingh
   */
  public void validateCalendarPage()throws Exception {

    //Validate flight details
    WebElement cityElt = CheckUtils.getElement(getTest(), LOC_LABEL_CALENDAR_FLIGHT_DETAILS);
    if(cityElt!=null && cityElt.isDisplayed()){
    	System.out.println("calendar ele ::::::::"+CheckUtils.getElement(getTest(), LOC_LABEL_CALENDAR_FLIGHT_DETAILS).getText().toString());
      String fromCity = CheckUtils.getElement(getTest(), LOC_LABEL_CALENDAR_FLIGHT_DETAILS).getText().split("-")[0];
      String toCity = CheckUtils.getElement(getTest(), LOC_LABEL_CALENDAR_FLIGHT_DETAILS).getText().split("-")[1];
      if (fromCity.contains(getValue("FROM").trim()) && toCity.contains(getValue("TO").trim())){
        reporter.reportPassed(pageName, "The source and destination are displayed properly");
      }
      else{
        reporter.reportFailed(pageName, "The source and destination are not displayed properly");
      }
      addValue("FROM CITY", fromCity);
      addValue("TO CITY", toCity);

    }else{
      reporter.reportFailed(pageName ,"FROM - TO Cities are not displayed properly");
    }

    validateCalendarForNumOfDays();

  }



  /**
   * Validates Calendar displayed for +/- 3 days
   * @throws Exception
   * @author bsingh
   */
  public void validateCalendarForNumOfDays() throws Exception {
    List<WebElement> returnHeaderElts = CheckUtils.getElements(getTest(), LOC_HEADER_CALENDAR_RETURN_DAYS);
    if(returnHeaderElts!=null && returnHeaderElts.size()==6){
      reporter.reportPassed(getName(), "Return Header Displayed for +/- 3 days");
    }else{
      reporter.reportFailed(getName(), "Return Header is not displayed for +/- 3 days");
    }
    if(getValue("TRIP TYPE").equalsIgnoreCase("RT")){
      List<WebElement> deptHeaderElts = CheckUtils.getElements(getTest(), LOC_HEADER_CALENDAR_DEPT_DAYS);
      if(deptHeaderElts!=null && deptHeaderElts.size()==6){
        reporter.reportPassed(getName(), "Departure Header Displayed for +/- 3 days");
      }else{
        reporter.reportFailed(getName(), "Departure Header is not displayed for +/- 3 days");
      }
    }
  }


  /**
   * Selects Fare From the Calendar
   * @throws Exception
   * @author bsingh
   */
  public void selectFare()throws Exception {

  }


  /**
   * Clicks Continue Button
   */
  public void clickContinue() {
    WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_CONTINUE, 30);
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CONTINUE, "Continue button not found");
    waitForOverlayLoading(20);
  }

  /**
   * Validates Matrix Calendar Page
   *
   * @throws IOException
   * @author sankar
   */
  public void ValidateMatrixCalendarPage() throws IOException, ParseException{

    ValidateMatrixFormatDate();
    VerifyMarksForDateCombination();
    VerifyOriginDestinationDisplayed();
  }

  /**
   * Select Flight having Deal Offer in Matrix Calendar page
   *
   * @throws IOException
   * @author sankar
   */
  public void SelectDealFlightfromMatrixCalendar() throws IOException {

    SelectFlightfromMatrixCalendar("FareDeal");
  }

  /**
   * Select Regular Flight in Matrix Calendar page
   *
   * @throws IOException
   * @author sankar
   */
  public void SelectRegularFlightfromMatrixCalendar() throws IOException {

    SelectFlightfromMatrixCalendar("Regular");
  }

}