package com.amadeus.selenium.sqmobile.page.availabililty;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * OutBound Availability Page
 * @author rshivaswamy
 *
 */

public class OutBoundAvailPage  extends CommanAvailPage{

  static String pageName = "OutBound Avail Page";

  protected static By LOC_PB_OUTBOUNDAVAIL_BACK = By.className("back");
  protected static By LOC_PB_OUTBOUNDAVAIL_TITLE = By.cssSelector(".sum>h1");
  protected static By LOC_PB_OUTBOUND_TOGGLE = By.className("toggle");
  protected static By LOC_PB_OUTBOUND_FLIGHTNUMBER = By.className("flight-number");
  protected static By LOC_PB_OUTBOUND_OPERATEDBY = By.className("op");
  protected static By LOC_PB_OUTBOUND_ROUTE = By.className("route");
  protected static By LOC_PB_OUTBOUND_SCHEDULE = By.className("schedule");
  protected static By LOC_PB_OUTBOUND_Date = By.cssSelector(".sum>p");

  protected static By LOC_PB_OUTBOUND_CITYPAIR_SPAN = By.tagName("span");

  protected static By LOC_PB_OUTBOUND_CSS_TOTALTIME = By.cssSelector(".total.time");
  protected static By LOC_PB_OUTBOUNDAVAIL_CSS_TABS = By.cssSelector(".tabs.order-by");
  protected static By LOC_PB_OUTBOUNDPANEL_CSS_LIST = By.cssSelector(".panel.list");

  protected final static By LOC_PB_OUTBOUND_WARNMESSAGE = By.cssSelector(".msg.warning");

  public OutBoundAvailPage(SeleniumSEPTest test) throws Exception {
    super(test, pageName);

    // Checking if we are on the right page
    boolean OutBoundAvailPage = WaitUtils.waitForElementPresent(getTest(), LOC_PB_OUTBOUND_FLIGHTNUMBER, 120);
    if (!OutBoundAvailPage) {
      WebElement errormsg = CheckUtils.getElement(getTest(), LOC_PB_OUTBOUND_WARNMESSAGE);
      if (errormsg != null && errormsg.isDisplayed()) {
        reporter.fail(errormsg.getText().trim());
      }
      else {
        reporter.fail("OutBoundAvail Page not loaded ");
      }
    }
    else {
      reporter.reportPassed(pageName, "OutBoundAvail Page loaded");
    }
  }


 /**
  * Validate OutBound availability Page
  * @throws IOException
  */
  public void validatePage() throws IOException{

    //TODO: Add title checking here

    CheckUtils.checkElementValue(getTest(), LOC_PB_OUTBOUNDAVAIL_TITLE, "Title", "Select departure");

    /*	Use Title as webelement and get the dates using "P" tag */

    WebElement title = CheckUtils.getElement(getTest(), LOC_PB_OUTBOUND_Date);

    if(!getValue("Flexible Dates").equalsIgnoreCase("TRUE")){
    validateDates(title);
    }

    // Validate the From and To cities which are displayed
    // Use className "sum" for getting from and to cities.

    validateCityPair(title);

    List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_OUTBOUNDPANEL_CSS_LIST);

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
   * Validate City Pair
   * @param title : Used as a ref element to get the respective elements
   * @throws IOException
   */
  private void validateCityPair(WebElement title) throws IOException {

    List<WebElement> cityPair = CheckUtils.getElements(getTest(), title, By.tagName("span"));

    if(cityPair.size() == 2 &&
        ( (cityPair.get(0)).getText().length() >= 1 &&  (cityPair.get(1)).getText().length() >= 1 ) ){
      reporter.reportPassed(pageName, "Cities is displayed correctly");

      addValue("FROM CITY", (cityPair.get(0)).getText().trim());
      addValue("TO CITY", (cityPair.get(1)).getText().trim());
    }else{
      reporter.reportFailed(pageName, "Cities is not displayed correctly");
    }
  }


  /**
   * Validate Departure Dates
   * @param title : Use the ref WebElement to perform the operation
   * @throws IOException
   */
  private void validateDates(WebElement title) throws IOException {

    if(!(title==null)){
    String depDates =  CheckUtils.getElement(getTest(), title, By.tagName("p")).getText();
    System.out.println(getValue("DEPARTURE DATE"));

    // get the DepDates to compare
    String[] depDate = getValue("DEPARTURE DATE").trim().split(" ");

    if(depDates.contains(depDate[0]) && depDates.contains(depDate[1]) && depDates.contains(depDate[2])){
      reporter.reportPassed(pageName, "Departure date is displayed correctly");

      String depStringToken[] = depDates.split(" ");
      String depDay = depStringToken[1];
      String depMonthYear = depStringToken[2] + " " + depStringToken[3];
      depDates = depDay + " " + depMonthYear;

      updateValue("DEPARTURE DATE",depDates);

    }else{
      reporter.reportFailed(pageName, "Departure date is not displayed correctly");
    }
    }else{
      reporter.reportFailed(pageName, "Departure date is not displayed correctly");
    }
  }



/**
 * Select a Direct Flight
 * @throws IOException
 */
  public void selectDirectFlight() throws IOException{
    if (getValue("Dept Flight Number") == null || getValue("Dept Flight Number").trim().equals("")) {
      selectDirectFlight("DEPARTURE");
    }
    else {
      selectDirectFlight("DEPARTURE", getValue("Dept Flight Number"));
    }
  }




/**
 * Select a code shared Direct Flight
 * @throws IOException
 */

  public void selectCodeShareDirectFlight() throws IOException{
    selectCodeShareDirectFlight("DEPARTURE");
  }

  /**
   * Select a connecting flight
   *
   * @throws IOException
   */

  public void selectConnectingFlight() throws IOException {
    selectConnectingFlight("DEPARTURE");
  }

  /**
   * Select a Fare Deal Flight
   *
   * @throws IOException
   */
  public void SelectFareDealFlight() throws IOException {
    SelectFareDealFlight("DEPARTURE", "FareDeal");
  }

  /**
   * Validate a Fare Deal Flight
   *
   * @throws IOException
   */
  public void ValidateFareDealFlight() throws IOException {
    ValidateFareDealFlight("DEPARTURE", "FareDeal");
  }

  /**
   * Select a Fare Deal Flight
   *
   * @throws IOException
   */
  public void SelectRegularFlight() throws IOException {
    SelectFareDealFlight("DEPARTURE", "Regular");
  }

  /**
   * Validate a Fare Deal Flight
   *
   * @throws IOException
   */
  public void ValidateRegularFlight() throws IOException {
    ValidateFareDealFlight("DEPARTURE", "Regular");
  }

  /**
   * Validate Header Section
   *
   * @author sankar
   */
  public void ValidateHeaderSection() {
    VerifyHeaderSection("DEPARTURE");
  }
}
