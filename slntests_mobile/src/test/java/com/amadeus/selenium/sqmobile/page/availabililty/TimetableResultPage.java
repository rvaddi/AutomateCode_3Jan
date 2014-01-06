package com.amadeus.selenium.sqmobile.page.availabililty;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class TimetableResultPage extends SqMobileCommonPage {

  protected static final By LOC_BUTTON_DAY = By.id("dayTab");
  protected static final By LOC_BUTTON_WEEK = By.id("weekTab");
  protected static final By LOC_SELECT_FLIGHT = By.id("dates");
  protected static final By LOC_LIST_FLIGHTS = By.cssSelector("[id*= 'dayContent']");
  protected static final By LOC_SCHEDULE_DETAILS = By.className("trip");
  protected static final By LOC_FLIGHT_DETAILS = By.cssSelector(".details>dl");
  protected static final By LOC_BUTTON_BACK = By.className("back");
  protected static final By LOC_SELECT_FLIGHTS = By.className("arrival");
  public CommonUtils utils;

  public TimetableResultPage(SeleniumSEPTest test) throws Exception {
    super(test);
    utils = PageFactory.getPageObject(CommonUtils.class);

    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_DAY, 30);
    if(elementPresent){
      reporter.reportPassed("TimetableResult Page", "In TimetableResult Page");
    }else{
      reporter.fail("TimetableResult Page not loaded ");
    }
  }

  public enum Itinerary {
    Departure, Arrival;
  }

  public enum TripType {
    RT, OW;
  }

  /**
   * Validates TimetableSearchPage
   * @throws Exception
   */
  public void validateTimetableSearchPage() throws Exception {

    WebElement dayElt = CheckUtils.getElement(getTest(), LOC_BUTTON_DAY);
    WebElement weekElt = CheckUtils.getElement(getTest(), LOC_BUTTON_WEEK);
    if (dayElt != null && dayElt.isDisplayed()) {
      reporter.reportPassed("TimetableResult Page", "Day Tab is displayed");

      if (dayElt.getAttribute("class").toString().contains("active")) {
        reporter.reportPassed("TimetableResult Page","Day Tab is default");
      }
      else {
        reporter.reportFailed("TimetableResult Page", "Day Tab is not default");
      }
    }
    else {
      reporter.reportFailed(getName(), "Day Tab is not displayed properly");
    }

    if (weekElt != null && weekElt.isDisplayed()) {
      reporter.reportPassed("TimetableResult Page", "Week Tab is displayed");
    }
    else {
      reporter.reportFailed("TimetableResult Page", "Week Tab is not displayed properly");
    }
  }

  /**
   * Clicks DayTab
   *
   * @throws Exception
   */
  public void clickDayTab() throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_DAY, "Day Tab couldn't be clicked", "Day Tab clicked ");
    reporter.report("TimetableResult Page", "Day button clicked");
  }

  /**
   * Clicks WeekTab
   *
   * @throws Exception
   */
  public void clickWeekTab() throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_WEEK, "Week Tab couldn't be clicked", "Week Tab clicked");
    reporter.report("TimetableResult Page", "Week button clicked");
  }

  /**
   * Selects the Date for departure or arrival flights
   *
   * @param itinerary
   * @throws Exception
   */
  public void selectSearchOption(Itinerary itinerary) throws Exception {
    if (itinerary.toString().equals("Departure")) {
      FillUtils.fillSelectByIndexOrFail(getTest(), LOC_SELECT_FLIGHT, 0,
          "Select For departure flights couldn't be selected");
    }
    else if (itinerary.toString().equals("Arrival")) {
      FillUtils.fillSelectByIndexOrFail(getTest(), LOC_SELECT_FLIGHT, 1,
          "Select For arrival flights couldn't be selected");
    }
  }

  /**
   * Validates flight listed for a particular day
   *@param Triptype
   * @throws Exception
   */
  public void validateFlights(TripType tripType) throws Exception {
     WebElement flightElt = CheckUtils.getElement(getTest(), LOC_LIST_FLIGHTS);
     List<WebElement> flights = flightElt.findElements(By.tagName("li"));
     int i = 0;
    for (i=0  ; i<flights.size(); i++) {
      flightElt = CheckUtils.getElement(getTest(), LOC_LIST_FLIGHTS);
      flights = flightElt.findElements(By.tagName("li"));
      ClickUtils.clickButtonOrFail(getTest(), flights.get(i), "Flight " + flights.get(i).getText() + " couldn't be clicked");
      WaitUtils.wait(5);
      validateFlightStatus(tripType);
      clickBack();
      WaitUtils.wait(5);
    }
  }

  /**
   * Validates the status of flight
   *
   * @param TripType
   *          RT for RoundTrip and OW for OneWay
   * @throws Exception
   */
  public void validateFlightStatus(TripType tripType) throws Exception {
    /* if(tripType.toString().equals("RT")){ */
    WebElement tripDetailsElt = CheckUtils.getElement(getTest(), LOC_SCHEDULE_DETAILS);

    if (tripDetailsElt != null && tripDetailsElt.isDisplayed()) {
      String tripDetails = tripDetailsElt.getText();
      if (tripDetails.contains("Departure")) {
        reporter.reportPassed("Time table Result page", "Departure detail is displayed");
      }
      else {
        reporter.reportFailed("Time table Result page", "Departure detail is not displayed");
      }
      if (tripDetails.contains("Scheduled Time")) {
        reporter.reportPassed("Time table Result page", "Scheduled Time  is displayed");
      }
      else {
        reporter.reportFailed("Time table Result page", "Scheduled Time  is not displayed");
      }
      if (tripDetails.contains("Estimated")) {
        reporter.reportPassed("Time table Result page", "Estimated detail is displayed");
      }
      else {
        reporter.reportFailed("Time table Result page", "Estimated detail is not displayed");
      }
      if (tripDetails.contains("Actual")) {
        reporter.reportPassed("Time table Result page", "Actual is displayed");
      }
      else {
        reporter.reportFailed("Time table Result page", "Actual is not displayed");
      }
      if (tripDetails.contains("Terminal")) {
        reporter.reportPassed("Time table Result page", "Terminal is displayed");
      }
      else {
        reporter.reportFailed("Time table Result page", "Terminal is not displayed");
      }

      WebElement flightDetailsElt = CheckUtils.getElement(getTest(), LOC_FLIGHT_DETAILS);
      String flightDetails = flightDetailsElt.getText();
      if (flightDetails.contains("Aircraft")) {
        reporter.reportPassed("Time table Result page", "Aircraft is displayed");
      }
      else {
        reporter.reportFailed("Time table Result page", "Aircraft is not displayed");
      }
      if (flightDetails.contains("Meal")) {
        reporter.reportPassed("Time table Result page", "Aircraft is displayed");
      }
      else {
        reporter.reportFailed("Time table Result page", "Aircraft is not displayed");
      }
      if (flightDetails.contains("Meal")) {
        reporter.reportPassed("Time table Result page", "Meal is displayed");
      }
      else {
        reporter.reportFailed("Time table Result page", "Meal is not displayed");
      }
      /* } */

    }
  }

  /**
   * Clicks Back Button
   *
   * @throws Exception
   */
  public void clickBack() throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_BACK, "Back Button couldn't be clicked");
    reporter.report("TimetableResult Page", "Back button clicked");
  }

  public void clickOnFlight(){
	  ClickUtils.clickButtonOrFail(getTest(), LOC_SELECT_FLIGHTS, "select Filght couldn't be clicked");
	    reporter.report("TimetableResult Page", "Back button clicked");
  }
}