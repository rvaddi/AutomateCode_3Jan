package com.amadeus.selenium.sqmobile.page.availabililty;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * InBound Availability Page
 * @author rshivaswamy
 *
 */
public class InBoundAvailPage extends CommanAvailPage{

  static String pageName = "InBound Avail Page";


  protected static By LOC_PB_INBOUNDAVAIL_CLASS_TOGGLE = By.className("toggle");
  protected static By LOC_PB_INBOUNDAVAIL_CLASS_FLIGHTNUMBER = By.className("flight-number");
  protected static By LOC_PB_INBOUNDAVAIL_CLASS_OPERATEDBY = By.className("op");
  protected static By LOC_PB_INBOUNDAVAIL_CLASS_ROUTE = By.className("route");
  protected static By LOC_PB_INBOUNDAVAIL_CLASS_SCHEDULE = By.className("schedule");
  protected static By LOC_PB_INBOUNDAVAIL_CLASS_TRANSIT = By.className("transit");
  protected static By LOC_PB_INBOUNDAVAIL_CLASS_PRICE = By.className("price");
  protected static By lOC_PB_INBOUNDAVAIL_CLASS_DURATION = By.className("duration");
  protected static By LOC_PB_INBOUNDAVAIL_CSS_TABS = By.cssSelector(".tabs.order-by");
  protected static By LOC_PB_INBOUNDAVAIL_CSS_TITLE = By.cssSelector(".sum>h1");
  protected static By LOC_PB_INBOUNDAVAIL_CSS_Date = By.cssSelector(".sum>p");
  protected static By LOC_PB_INBOUNDPANEL_CSS_LIST = By.cssSelector(".panel.list");
  protected static By LOC_PB_INBOUNDAVAIL_CSS_TOTALTIME = By.cssSelector(".total.time");
  protected static By LOC_PB_INBOUNDAVAIL_SELECTED_DEPT_FLIGHT = By.cssSelector(".panel.sum");

  protected final static By LOC_PB_INBOUND_WARNMESSAGE = By.cssSelector(".msg.warning");

  public InBoundAvailPage(SeleniumSEPTest test) throws Exception {
    super(test, pageName);

    // Checking if we are on the right page
    boolean InBoundAvailPage = WaitUtils.waitForElementPresent(getTest(), LOC_PB_INBOUNDAVAIL_SELECTED_DEPT_FLIGHT, 120);
    if (!InBoundAvailPage) {
      WebElement errormsg = CheckUtils.getElement(getTest(), LOC_PB_INBOUND_WARNMESSAGE);
      if (errormsg != null && errormsg.isDisplayed()) {
        reporter.fail(errormsg.getText().trim());
      }
      else {
        reporter.fail("InBoundAvail Page not loaded ");
      }
    }
    else {
      reporter.reportPassed(pageName, "InBoundAvail Page loaded");
    }
  }

/**
 * Validate InBound Availability page
 * @throws IOException
 */
  public void validatePage() throws IOException{

    //TODO: Add title checking here

    CheckUtils.checkElementValue(getTest(), LOC_PB_INBOUNDAVAIL_CSS_TITLE, "Title", "Select Return");

    /*	Use Title as webelement and get the dates using "P" tag */

    WebElement title = CheckUtils.getElement(getTest(), LOC_PB_INBOUNDAVAIL_CSS_Date);

    if(!getValue("Flexible Dates").equalsIgnoreCase("TRUE")){
    validateDates(title);
    }

    // Validate the From and To cities which are displayed
    // Use className "sum" for getting from and to cities.

    validateCityPair(title);

    List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_INBOUNDPANEL_CSS_LIST);

    // get the count of FFHeaders displayed
    int counter = 0;
    for (WebElement familyFare : fareFamily) {
        if (familyFare.isDisplayed()) {
          counter++;
      }
    }

    reporter.report(pageName, counter + " fare families are displayed");

    for(WebElement singleFareFamily : fareFamily ){
      expandAndValidateEachFamily(singleFareFamily);
    }
    this.automaticScreenShot();
  }

  /**
   * Validate City Pair displayed for Return Trip
   * @param title : Ref element used
   * @throws IOException
   */
  private void validateCityPair(WebElement title) throws IOException {

    List<WebElement> cityPair = CheckUtils.getElements(getTest(), title, By.tagName("span"));

    if(cityPair.size() == 2 &&
        (cityPair.get(0)).getText().trim().equalsIgnoreCase(getValue("TO CITY")) &&
        (cityPair.get(1)).getText().trim().equalsIgnoreCase(getValue("FROM CITY")) ){

      reporter.reportPassed(pageName, "Cities is displayed correctly");
    }else{
      reporter.reportFailed(pageName, "Cities is not displayed correctly");
    }
  }

  /**
   * Validate return trip Dates
   * @param title : Ref element used for webelements
   * @throws IOException
   */
  private void validateDates(WebElement title) throws IOException {
    String retDates = CheckUtils.getElement(getTest(), title, By.tagName("p")).getText();
    System.out.println(getValue("RETURN DATE"));

    // get the return Date to compare
    String[] retDate = getValue("RETURN DATE").trim().split(" ");

    if(retDates.contains(retDate[0]) && retDates.contains(retDate[1]) && retDates.contains(retDate[2])){
      reporter.reportPassed(pageName, "Return date is displayed correctly");

      String retStringToken[] = retDates.split(" ");
      String retDay = retStringToken[1];
      String retMonthYear = retStringToken[2] + " " + retStringToken[3];
      retDates = retDay + " " + retMonthYear;

      updateValue("RETURN DATE",retDates);
    }else{
      reporter.reportFailed(pageName, "Return date is not displayed correctly");
    }
  }


/**
 * Select Direct Flight
 * @throws IOException
 */

  public void selectDirectFlight() throws IOException{
    WaitUtils.wait(4);
    if (getValue("Ret Flight Number") == null || getValue("Ret Flight Number").trim().equals("")) {
      selectDirectFlight("RETURN");
    }
    else {
      selectDirectFlight("RETURN", getValue("Ret Flight Number"));
    }

  }


 /**
  * Select Connecting Flight
  * @throws IOException
  */

  public void selectConnectingFlight() throws IOException{
    selectConnectingFlight("RETURN");
  }


/**
 * Select Code Shared Direct Flight
 * @throws IOException
 */

  public void selectCodeShareDirectFlight() throws IOException{
    selectCodeShareDirectFlight("RETURN");
  }

  /**
   * Select a Fare Deal Flight
   * @throws IOException
   */
   public void SelectFareDealFlight() throws IOException{
    SelectFareDealFlight("RETURN", "FareDeal");
    }

 /**
   * Validate a Fare Deal Flight
   * @throws IOException
   */
   public void ValidateFareDealFlight() throws IOException{
    ValidateFareDealFlight("RETURN", "FareDeal");
   }

  /**
   * Select a Fare Deal Flight
   *
   * @throws IOException
   */
  public void SelectRegularFlight() throws IOException {
    SelectFareDealFlight("RETURN", "Regular");
  }

  /**
   * Validate a Fare Deal Flight
   *
   * @throws IOException
   */
  public void ValidateRegularFlight() throws IOException {
    ValidateFareDealFlight("RETURN", "Regular");
  }

  public void ValidateSelectedDepartureFlight(){

    WebElement SelectedDepFlight = CheckUtils.getElement(getTest(), LOC_PB_INBOUNDAVAIL_SELECTED_DEPT_FLIGHT);
    boolean SelectedDepartureFlight = false;

    if(!(SelectedDepFlight==null)){

      String DepFlightDetail = SelectedDepFlight.getText().trim();
      DepFlightDetail = DepFlightDetail.replaceAll("[\n]", ", ");

      if(!(DepFlightDetail.isEmpty())){
        SelectedDepartureFlight = true;
        reporter.reportPassed("Inbound Avail Page", "Flight selected for Departure is displayed. (Departure Flight: " +
            DepFlightDetail + ")");
      }
    }

    if(!SelectedDepartureFlight){
      reporter.reportFailed("Inbound Avail Page", "Flight selected for Departure is NOT displayed");
    }
  }

  /**
   * Validate Header Section
   *
   * @author sankar
   */
  public void ValidateHeaderSection() {
    VerifyHeaderSection("RETURN");
  }
}


