package com.amadeus.selenium.sqmobile.page.availabililty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Common Availability page operation
 * @author rshivaswamy
 *
 */
public class CommanAvailPage extends SqMobileCommonPage{


  // Locators CommonAvail Page -----------------------------------------------------------

  protected final static By LOC_PB_BOUND_CLASS_FLIGHTNUMBER = By.className("flight-number");
  protected final static By LOC_PB_BOUND_CLASS_SCHEDULE = By.className("schedule");
  protected final static By LOC_PB_BOUND_CLASS_ROUTE = By.className("route");
  protected final static By LOC_PB_BOUND_CLASS_OPERATEDBY = By.className("op");
  protected final static By LOC_PB_BOUND_TOGGLE = By.className("toggle");
  protected final static By LOC_PB_AVAIL_BACK = By.className("back");
  protected final static By LOC_PB_BOUND_CLASS_TRANSIT = By.className("transit");
  protected final static By LOC_PB_BOUNDAVAIL_CLASS_PRICE = By.className("price");
  protected final static By LOC_PB_BOUNDAVAIL_CLASS_DURATION = By.className("duration");
  protected final static By LOC_PB_BOUNDAVAIL_CLASS_CITY = By.className("city");
  protected final static By LOC_PB_BOUNDAVAIL_TAGNAME_FLIGHTSECTION = By.tagName("section");
  protected final static By LOC_PB_BOUNDAVAIL_SPAN_FLIGHT = By.cssSelector("section>span");
  protected final static By LOC_PB_BOUND_CSS_TOTALTIME = By.cssSelector(".total.time");
  protected final static By LOC_PB_BOUNDAVAIL_CSS_PANEL_LIST = By.cssSelector(".panel.list");
  protected final static By LOC_PB_BOUNDAVAIL_CSS_TABS = By.cssSelector(".tabs.order-by");
  protected final static By LOC_PB_BOUNDAVAIL_CSS_Header = By.cssSelector("header.sum");

  //--------------------------------------------------------------------------------------

  public CommanAvailPage(SeleniumSEPTest test, String pageName) throws Exception {
    super(test);
    this.pageName = pageName;
  }

  private String pageName = "Avail Page";

  /**
   * Select Code shared Direct Flight
   * 
   * @param segmentType
   *          : Trip Type (RT or OW)
   * @throws IOException
   */

  public void selectCodeShareDirectFlight(String segmentType) throws IOException{


    List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_BOUNDAVAIL_CSS_PANEL_LIST);

    for(WebElement singleFareFamily : fareFamily ){


      String familyFareName = CheckUtils.getElement(getTest(), singleFareFamily, By.tagName("a")).getText();
      expandFFList(singleFareFamily);

      List<WebElement> flightList = getValidFlightList(singleFareFamily);

      boolean isFlightSelected = false;
      for(WebElement flight : flightList){
        Boolean isConnectingFlight = false;
        //check if its a connecting flight
        try{
          flight.findElement(LOC_PB_BOUND_CSS_TOTALTIME);
          isConnectingFlight = true;
        }catch(NoSuchElementException e){
          reporter.report(pageName, "This is not connection flight");
        }
        if (isConnectingFlight) {

          reporter.report(pageName, "This is connecting flight, hence not selecting this");

        } else {

          /*
           * get the firstFlight details which is being displayed
           */

          WebElement flightNumber = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_FLIGHTNUMBER);
          WebElement schedule = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_SCHEDULE);
          WebElement operatedBy =  CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_OPERATEDBY);

          addValue(segmentType + " FARE FAMILY", familyFareName);

          String[] flightNo = (flightNumber.getText().split(" "));
          addValue(segmentType + " FLIGHT NUMBER", flightNo[0]);
          addValue(segmentType + " FLIGHT TIMING", schedule.getText());
          addValue(segmentType + " OPERATED BY", operatedBy.getText());

          ClickUtils.clickButtonOrFail(getTest(), flightNumber, "Flight could not be selected");
          reporter.reportPassed(pageName, "Code shared Outbound flight has been selected");
          isFlightSelected = true;
          break;
        }
      }
      if(!isFlightSelected){
        reporter.fail("Code share direct flight was not selected or available for selection");
      }
    }
  }

  /**
   * Select Connecting Flight
   * 
   * @param segmentType
   *          : Trip Type ( RT or OW )
   * @throws IOException
   */

  public void selectConnectingFlight(String segmentType) throws IOException{

    List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_BOUNDAVAIL_CSS_PANEL_LIST);

    for(WebElement singleFareFamily : fareFamily ){


      String familyFareName = CheckUtils.getElement(getTest(), singleFareFamily, By.tagName("a")).getText();
      // Expand the FF, if its collapsed
      expandFFList(singleFareFamily);

      List<WebElement> flightList = getValidFlightList(singleFareFamily);

      boolean isFlightSelected = false;
      for(WebElement flight : flightList){
        Boolean isConnectingFlight = false;
        //check if its a connecting flight
        try{
          flight.findElement(LOC_PB_BOUND_CSS_TOTALTIME);
          isConnectingFlight = true;
        }catch(NoSuchElementException e){
          reporter.report(pageName, "This is not connection flight");
        }

        if (isConnectingFlight) {

          addValue(segmentType + " FARE FAMILY", familyFareName );
          addValue(segmentType + " FLIGHT NUMBER", "");
          addValue(segmentType + " FLIGHT TIMING", "");
          addValue(segmentType + " FLIGHT LAYOVER TIME", "");
          addValue(segmentType + " FLIGHT TOTAL TRAVEL TIME", "");
          addValue(segmentType + " OPERATED BY", "");
          addValue(segmentType + " CONNECTING ROUTE", "");

          // get the count of flight legs and iterate through each legs
          List<WebElement> flightNumberList = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_FLIGHTNUMBER);
          int flightNumberListCount = flightNumberList.size();

          List<WebElement> operator = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_OPERATEDBY);
          int operatorCount = operator.size();

          List<WebElement> route = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_ROUTE);
          int routeCount = route.size();

          List<WebElement> schedule = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_SCHEDULE);
          int scheduleCount = schedule.size();

          List<WebElement> transit = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_TRANSIT);
          int transitCount = transit.size();

          int maxCount = findMaxCountElement(flightNumberListCount,operatorCount,routeCount,
              scheduleCount,transitCount);

          for( int eachLeg = 0 ; eachLeg < maxCount ; eachLeg++ ){


            if(flightNumberListCount != 0){
              WebElement legFlightNum = flightNumberList.get(flightNumberListCount - flightNumberListCount + eachLeg);
              System.out.println(legFlightNum.getText() );
              String[] flightNo = (legFlightNum.getText().split(" "));
              System.out.println("Filight : "+flightNo[0]);
              updateValue(segmentType + " FLIGHT NUMBER", getValue(segmentType + " FLIGHT NUMBER") + " " + flightNo[0]);
              System.out.println("Dept : "+getValue("DEPARTURE FLIGHT NUMBER"));
              flightNumberListCount--;
            }

            if(operatorCount != 0){
              WebElement legOperatedBy = operator.get(operatorCount - operatorCount + eachLeg);
              if(legOperatedBy.isDisplayed()){
                updateValue(segmentType + " OPERATED BY", getValue(segmentType + " OPERATED BY") + " " + legOperatedBy.getText());
                operatorCount--;
              }
            }

            if(routeCount != 0){
              WebElement legFlightRoute = route.get(routeCount - routeCount + eachLeg);
              updateValue(segmentType + " CONNECTING ROUTE", getValue(segmentType + " CONNECTING ROUTE") + " " + legFlightRoute.getText());
              routeCount--;
            }

            if(scheduleCount != 0){
              WebElement legSchedule = schedule.get(scheduleCount - scheduleCount + eachLeg);
              updateValue(segmentType + " FLIGHT TIMING", getValue(segmentType + " FLIGHT TIMING") + " " +
                  legSchedule.getText().replace(" ", ""));
              scheduleCount--;
            }

            if(transitCount != 0){
              WebElement legTransit = transit.get(transitCount - transitCount + eachLeg);
              if(legTransit.isDisplayed()){
                updateValue(segmentType + " FLIGHT LAYOVER TIME", getValue(segmentType + " FLIGHT LAYOVER TIME") + " " +
                    legTransit.getText().split(":")[1].trim().replace(" ", ""));
                transitCount--;
              }
            }

            ClickUtils.clickButtonOrFail(getTest(), flightNumberList.get(0), "Flight could not selected");
            reporter.reportPassed(pageName, "Connecting outbound flight has been selected");
            isFlightSelected = true;
            break;
          }
        }
        if(isFlightSelected) {
          break;
        }
      }
      if(!isFlightSelected){
        reporter.fail("There were no connecting Flights displayed in this avail page or connecting flight was not selected");
      }
    }
  }

  /**
   * Select Direct Flight
   * 
   * @param segmentType
   *          : Trip type ( RT or OW )
   * @throws IOException
   */
  public void selectDirectFlight(String segmentType) throws IOException{

    WaitUtils.wait(4);
    waitForOverlayLoading(10);
    List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_BOUNDAVAIL_CSS_PANEL_LIST);
    boolean isFlightSelected = false;
    for(WebElement singleFareFamily : fareFamily ){
      String familyFareName = CheckUtils.getElement(getTest(), singleFareFamily, By.tagName("a")).getText();
      expandFFList(singleFareFamily);
      List<WebElement> flightList =  getValidFlightList(singleFareFamily);
      for(WebElement flight : flightList){
        Boolean isConnectingFlight = false;
        //check if its a connecting flight
        try{
          flight.findElement(LOC_PB_BOUND_CSS_TOTALTIME);
          isConnectingFlight = true;
        }catch(NoSuchElementException e){
          reporter.report(pageName, "This is not connection flight");
        }

        if (isConnectingFlight) {
          reporter.report(pageName, "This is connecting flight, hence not selecting this");

        } else {

          /*
           * get the firstFlight details which is being displayed
           */

          WebElement flightNumber = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_FLIGHTNUMBER);
          WebElement schedule = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_SCHEDULE);

          addValue(segmentType + " FARE FAMILY", familyFareName);

          String[] flightNo = (flightNumber.getText().split(" "));
          addValue(segmentType + " FLIGHT NUMBER", flightNo[0]);
          addValue(segmentType + " FLIGHT TIMING", schedule.getText().replace(" ", ""));
          ClickUtils.clickButtonOrFail(getTest(), flightNumber, "Could not click on fligth no : " + flightNo[0]);
          reporter.reportPassed(pageName, "Direct Outbound flight has been selected");
          isFlightSelected = true;
          break;
        }
      }
      if(!isFlightSelected){
        reporter.fail("No Direct flights were selected or available");
      }else{
        break;
      }
    }
  }



  /**
   * Select Direct Flight
   * @param segmentType : Trip type ( RT or OW )
   * @param flightNumber : flightNumber of the flight which is to be selected
   * @throws IOException
   */
  public void selectDirectFlight(String segmentType, String flightNumb) throws IOException {

    WaitUtils.wait(4);
    waitForOverlayLoading(30);
    List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_BOUNDAVAIL_CSS_PANEL_LIST);
    boolean isFlightSelected = false;
    for (WebElement singleFareFamily : fareFamily) {
      String familyFareName = CheckUtils.getElement(getTest(), singleFareFamily, By.tagName("a")).getText();
      expandFFList(singleFareFamily);
      List<WebElement> flightList = getValidFlightList(singleFareFamily);
      for (WebElement flight : flightList) {
        Boolean isConnectingFlight = false;
        // check if its a connecting flight
        try {
          flight.findElement(LOC_PB_BOUND_CSS_TOTALTIME);
          isConnectingFlight = true;
        }
        catch (NoSuchElementException e) {
          reporter.report(pageName, "This is not connection flight");
        }

        if (isConnectingFlight) {
          reporter.report(pageName, "This is connecting flight, hence not selecting this");

        }
        else {
          WebElement flightNumber = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_FLIGHTNUMBER);
          if (flightNumber != null && flightNumber.getText().toLowerCase().contains(flightNumb.toLowerCase())) {
            WebElement schedule = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_SCHEDULE);
            addValue(segmentType + " FARE FAMILY", familyFareName);
            String[] flightNo = (flightNumber.getText().split(" "));
            addValue(segmentType + " FLIGHT NUMBER", flightNo[0]);
            addValue(segmentType + " FLIGHT TIMING", schedule.getText().replace(" ", ""));
            ClickUtils.clickButtonOrFail(getTest(), flightNumber, "Could not click on fligth no : " + flightNo[0]);
            reporter.reportPassed(pageName, "Direct Outbound flight has been selected");
            isFlightSelected = true;
            break;
          }
        }
      }
      if (!isFlightSelected) {
        reporter.fail("No Direct flights were selected or available");
      }
      else {
        break;
      }
    }
  }

  /**
   * Expand Family Fare list displayed in the avial page
   * 
   * @param singleFareFamily
   */
  protected void expandFFList(WebElement singleFareFamily) {
    WebElement expanded = CheckUtils.getElement(getTest(), singleFareFamily, LOC_PB_BOUND_TOGGLE);
    if(!expanded.getAttribute("aria-expanded").equalsIgnoreCase("true") && expanded.isDisplayed()){
      ClickUtils.clickButtonOrFail(getTest(), expanded, "Could not click on expandable Icon","Expandable Icon is Clicked");
    }
  }

  /**
   * Find the Max count element for iterating through the list.
   * @param flightNumberListCount
   * @param operatorCount
   * @param routeCount
   * @param scheduleCount
   * @param transitCount
   * @return
   */
  protected int findMaxCountElement(int flightNumberListCount,
      int operatorCount, int routeCount, int scheduleCount,
      int transitCount) {
    return Math.max(flightNumberListCount, Math.max(operatorCount, Math.max(routeCount, Math.max(scheduleCount, transitCount))));
  }


  /**
   * Click Back, Used to traverse back a page
   */
  public void clickBack() throws IOException{
    ClickUtils.clickButtonOrFail(getTest(), LOC_PB_AVAIL_BACK, "Back button NOT found or is NOT displayed");
    reporter.reportPassed(pageName, "Back Button clicked successfully");
  }

  /**
   * Get specific sorting web element displayed for the family fare
   * @param sortingTabs
   * @param sortingString
   * @return
   */
  public WebElement getSortingWebElement(List<WebElement> sortingTabs, String sortingString) {
    for(WebElement temp : sortingTabs){
      if(temp.getText().equalsIgnoreCase(sortingString)){
        return temp;
      }
    }
    return null;
  }


  /**
   * Validate Each Flight
   * @param flightNumberList : List of flightNumber displayed per Bound
   * @param flightNumberListCount : count of flightList.
   * @param operator: list of operators in the availability page displayed per Bound
   * @param operatorCount: count of the operators displayed
   * @param route: list of route displayed per Bound
   * @param routeCount: count of routes displayed
   * @param schedule: list of schedule displayed per Bound
   * @param scheduleCount: count of schedule displayed
   * @param transit: list of transit displayed per Bound
   * @param transitCount: count of transit displayed
   * @param eachLeg: Leg count
   * @return
   */
  public Boolean validateEachFlightLeg(List<WebElement> flightNumberList,
      int flightNumberListCount, List<WebElement> operator,
      int operatorCount, List<WebElement> route, int routeCount,
      List<WebElement> schedule, int scheduleCount,
      List<WebElement> transit, int transitCount, int eachLeg) {
    if(flightNumberListCount != 0){
      WebElement legFlightNum = flightNumberList.get(flightNumberListCount - flightNumberListCount + eachLeg);
      if(legFlightNum.getText().isEmpty()){
        reporter.reportFailed(pageName, "Price is not displayed for flight");return false;
      }
      flightNumberListCount--;
    }

    if(operatorCount != 0){
      WebElement legOperatedBy = operator.get(operatorCount - operatorCount + eachLeg);
      if(legOperatedBy.isDisplayed()){
        if(legOperatedBy.getText().isEmpty()){
          reporter.reportFailed(pageName, "Operated By is not Displayed");return false;
        }
        operatorCount--;
      }
    }

    if(routeCount != 0){
      WebElement legFlightRoute = route.get(routeCount - routeCount + eachLeg);
      if(legFlightRoute.getText().isEmpty()){
        reporter.reportFailed(pageName, "FlightRoute is not Displayed");return false;
      }
      routeCount--;
    }


    if(scheduleCount != 0){
      WebElement legSchedule = schedule.get(scheduleCount - scheduleCount + eachLeg);
      if(legSchedule.getText().isEmpty()){
        reporter.reportFailed(pageName, "Schedule is not Displayed");return false;
      }
      scheduleCount--;
    }


    if(transitCount != 0){
      WebElement legTransit = transit.get(transitCount - transitCount + eachLeg);
      if(legTransit.isDisplayed()){
        if(legTransit.getText().isEmpty()){
          reporter.reportFailed(pageName, "Transit is not Displayed");return false;
        }
        transitCount--;
      }

    }
    return true;
  }



  /**
   * Validate Direct Flight
   * @param flight: Flight which is to be validated
   * @return
   */
  public Boolean validateDirectFlight(WebElement flight) {
    // TODO Auto-generated method stub

    //flight number
    WebElement flightNumber = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_FLIGHTNUMBER);
    if(flightNumber.getText().isEmpty()){
      reporter.reportFailed(pageName, "flightNumber is not Displayed");return false;
    }

    //price
    WebElement flightPrice =  CheckUtils.getElement(getTest(), flight, LOC_PB_BOUNDAVAIL_CLASS_PRICE);
    if(flightPrice.getText().isEmpty()){
      reporter.reportFailed(pageName, "flightPrice is not Displayed");return false;
    }

    //route
    WebElement flightRoute = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_ROUTE);
    List<WebElement> routeList = CheckUtils.getElements(getTest(), flightRoute, LOC_PB_BOUNDAVAIL_CLASS_CITY);
    if(routeList.get(0).getText().isEmpty() || routeList.get(1).getText().isEmpty()){
      reporter.reportFailed(pageName, "Route is not Displayed");return false;
    }

    //time
    WebElement schedule = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CLASS_SCHEDULE);
    if(schedule.getText().isEmpty()){
      reporter.reportFailed(pageName, "schedule is not Displayed");return false;
    }


    //Duration
    WebElement flightDuration = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUNDAVAIL_CLASS_DURATION);
    if(flightDuration.getText().isEmpty()){
      reporter.reportFailed(pageName, "flightDuration is not Displayed");return false;
    }

    return true;
  }



  /**
   * Validate Connecting Flight
   * @param flight: Flight which is to be validated
   * @return
   */
  public boolean validateConnectingFlight(WebElement flight) {

    List<WebElement> flightNumberList = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_FLIGHTNUMBER);
    int flightNumberListCount = flightNumberList.size();

    List<WebElement> operator = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_OPERATEDBY);
    int operatorCount = operator.size();

    List<WebElement> route = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_ROUTE);
    int routeCount = route.size();

    List<WebElement> schedule = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_SCHEDULE);
    int scheduleCount = schedule.size();

    List<WebElement> transit = CheckUtils.getElements(getTest(), flight, LOC_PB_BOUND_CLASS_TRANSIT);
    int transitCount = transit.size();

    int maxCount = findMaxCountElement(flightNumberListCount,operatorCount,routeCount,
        scheduleCount,transitCount);

    for( int eachLeg = 0 ; eachLeg < maxCount ; eachLeg++ ){

      Boolean isFlightLegValid = validateEachFlightLeg(flightNumberList,
          flightNumberListCount, operator, operatorCount,
          route, routeCount, schedule, scheduleCount,
          transit, transitCount, eachLeg);

      if(!isFlightLegValid){
        reporter.fail(pageName + " Failed in validating Each Leg " );
        break;
      }
    }

    // Total time duration of all the flight legs

    WebElement totalTimeDuration = CheckUtils.getElement(getTest(), flight, LOC_PB_BOUND_CSS_TOTALTIME);
    if(totalTimeDuration.getText().isEmpty()){
      reporter.reportFailed(pageName, "totalTimeDuration is not Displayed");
      return false;
    }
    return true;
  }

  /**
   * Get Valid Flight List
   * @param singleFareFamily : Used as a ref for getting the valid fligth list.
   * @return
   */
  public List<WebElement> getValidFlightList(WebElement singleFareFamily) {
    WaitUtils.waitForElementPresent(getTest(), LOC_PB_BOUNDAVAIL_SPAN_FLIGHT, 5);
    WebElement flightSpan = CheckUtils.getElement(getTest(), singleFareFamily, LOC_PB_BOUNDAVAIL_SPAN_FLIGHT);
    List<WebElement> flightSpans =  CheckUtils.getElements(getTest(), flightSpan, By.tagName("li"));
    List<WebElement> flightList = new ArrayList<WebElement>();

    for (WebElement tempFlight : flightSpans) {
      if (tempFlight.isDisplayed()) {
        WebElement validElement = CheckUtils.getElement(getTest(), tempFlight, LOC_PB_BOUND_CLASS_FLIGHTNUMBER);
        if (validElement != null) {
          flightList.add(tempFlight);
        }
      }
    }
    return flightList;
  }

  /**
   * Validate the Sorting elements displayed in the avail page of each Family fare
   * @param singleFareFamily
   */
  protected void validateSorting(WebElement singleFareFamily) {
    WebElement FFHeader = CheckUtils.getElement(getTest(), singleFareFamily, By.tagName("a"));
    WebElement orderByTab  = CheckUtils.getElement(getTest(), singleFareFamily, LOC_PB_BOUNDAVAIL_CSS_TABS);
    List<WebElement> tempSortingTabs = CheckUtils.getElements(getTest(), orderByTab, By.tagName("li"));
    List<WebElement> sortingTabs =  new ArrayList<WebElement>();

    for(WebElement temp : tempSortingTabs){

      if(temp.getText().equalsIgnoreCase("price")){
        sortingTabs.add(temp);
      }
      if(temp.getText().equalsIgnoreCase("time")){
        sortingTabs.add(temp);
      }
      if(temp.getText().equalsIgnoreCase("duration")){
        sortingTabs.add(temp);
      }
    }

    WebElement price = getSortingWebElement(sortingTabs,"price");
    WebElement time = getSortingWebElement(sortingTabs,"time");
    WebElement duration = getSortingWebElement(sortingTabs,"duration");

    // check for the sorting options being displayed
    if (!(price == null || time == null || duration == null)) {
      if (price.isDisplayed() && time.isDisplayed() && duration.isDisplayed() &&
          price.getAttribute("aria-expanded").equalsIgnoreCase("true")) {
        reporter.reportPassed(pageName, FFHeader.getText() + " Fare Family is displayed with all the sorting options");
      }
      else {
        reporter.reportFailed(pageName, FFHeader.getText() +
            " Fare Family is not displayed properly with all the sorting options");
      }
    }
    else {
      reporter.reportFailed(pageName, FFHeader.getText() +
          " Fare Family is not displayed properly with all the sorting options");
    }
  }

  /**
   * Validate Each Flight displayed in the list
   * @param flightList
   */
  protected void validateEachFlight(List<WebElement> flightList) {
    for(WebElement flight : flightList){
      Boolean isConnectingFlight = false;
      //check if its a connecting flight
      try{
        flight.findElement(LOC_PB_BOUND_CSS_TOTALTIME);
        isConnectingFlight = true;
      }catch(NoSuchElementException e){
        // reporter.report(pageName, "This is not connection flight");
      }

      if (isConnectingFlight) {
        if(!validateConnectingFlight(flight)){
          reporter.fail(pageName + "  ConnectingFlight Validation failed ");
          break;
        }
      } else {
        if(!validateDirectFlight(flight)){
          break;
        }
      }
    }
  }

  /**
   * Expand and Validate Each Family Fare displayed in the avail page
   * 
   * @param singleFareFamily
   */
  protected void expandAndValidateEachFamily(WebElement singleFareFamily) {

    if (singleFareFamily.isDisplayed()) {
      expandFFList(singleFareFamily);

      validateSorting(singleFareFamily);

      List<WebElement> flightList = getValidFlightList(singleFareFamily);

      validateEachFlight(flightList);
    }
  }


  /**
   * Select Fare Deal Flight
   * @param segmentType : Trip type ( RT or OW )
   * @throws IOException
   */
  public void SelectFareDealFlight(String Triptype, String DealType) {

    Map<String, Object> GetFlightDetail = new HashMap<String, Object>();
    WebElement ClickFlight;
    boolean IsDealFlightAvailable;

    GetFlightDetail = GetFareDealFlight(DealType);

    ClickFlight = (WebElement)GetFlightDetail.get("ClickFlight");
    IsDealFlightAvailable = (Boolean)GetFlightDetail.get("IsDealFlightAvailable");

    if (!(ClickFlight == null)) {

      String FlightDetail = ClickFlight.getText().trim();
      FlightDetail = FlightDetail.replaceAll("[\n]", ", ");

      if (DealType.equalsIgnoreCase("Fare Deal")) {
        if (IsDealFlightAvailable) {
          reporter.reportPassed(Triptype, "Fare Deal flight with distinguish mark is available. (Flight: " + FlightDetail + ")");
          ClickUtils.click(getTest(), ClickFlight);
          reporter.reportPassed(Triptype, "Fare Deal Flight is selected in "+ Triptype +" page.");
        }
        else {
          // reporter.reportFailed(Triptype, "Fare Deal flight with distinguish mark is NOT available. (Flight: " +
          // FlightDetail + ")");
          reporter.reportWarning(Triptype, "Fare Deal flight with distinguish mark is NOT available. (Flight: " +  FlightDetail + ")");
          ClickUtils.click(getTest(), ClickFlight);
          reporter.reportPassed(Triptype, "Regular Flight is selected in " + Triptype + " page.");
        }
      }
      else {
        if (!IsDealFlightAvailable) {
          reporter.reportPassed(Triptype, "Regular flight is available. (Flight: " + FlightDetail + ")");
          ClickUtils.click(getTest(), ClickFlight);
          reporter.reportPassed(Triptype, "Regular Flight is selected in "+Triptype +" page.");
        }
        else {
          // reporter.reportFailed(Triptype, "Regular flight is NOT available. (Flight: " + FlightDetail + ")");
          reporter.reportWarning(Triptype, "Regular flight is NOT available. (Flight: " + FlightDetail + ")");
          ClickUtils.click(getTest(), ClickFlight);
          reporter.reportPassed(Triptype, "Fare Deal Flight is selected in " + Triptype + " page.");
        }
      }
    }
    else {
      // reporter.reportFailed(Triptype, "No Flights are available to select in " + Triptype + " page.");
      reporter.fail("No Flights are available to select in " + Triptype + " page.");
    }
  }


  /**
   * Validate Fare Deal Flight
   * @param segmentType : Trip type ( RT or OW )
   * @throws IOException
   */
  public void ValidateFareDealFlight(String Triptype, String DealType) {

    Map<String, Object> GetFlightDetail = new HashMap<String, Object>();
    WebElement ClickFlight;
    boolean IsDealFlightAvailable;

    GetFlightDetail = GetFareDealFlight(DealType);

    ClickFlight = (WebElement)GetFlightDetail.get("ClickFlight");
    IsDealFlightAvailable = (Boolean)GetFlightDetail.get("IsDealFlightAvailable");

    if (!(ClickFlight == null)) {

      String FlightDetail = ClickFlight.getText().trim();
      FlightDetail = FlightDetail.replaceAll("[\n]", ", ");

      if (DealType.equalsIgnoreCase("Fare Deal")) {
        if (IsDealFlightAvailable) {
          reporter.reportPassed(Triptype, "Fare Deal flight with distinguish mark is available. (Flight: " + FlightDetail + ")");
        }
        else {
          // reporter.reportFailed(Triptype, "Fare Deal flight with distinguish mark is NOT available. (Flight: " +
          // FlightDetail + ")");
          reporter.reportWarning(Triptype, "Fare Deal flight with distinguish mark is NOT available. (Flight: " + FlightDetail + ")");
        }
      }
      else {
        if (!IsDealFlightAvailable) {
          reporter.reportPassed(Triptype, "Regular flight is available. (Flight: " + FlightDetail + ")");
        }
        else {
          // reporter.reportFailed(Triptype, "Regular flight is NOT available. (Flight: " + FlightDetail + ")");
          reporter.reportWarning(Triptype, "Regular flight is NOT available. (Flight: " + FlightDetail + ")");
        }
      }
    }
    else {
      // reporter.reportFailed(Triptype, "No Flights are available to Validate in " + Triptype + " page.");
      reporter.fail("No Flights are available to Validate in " + Triptype + " page.");
    }
  }


  public Map<String, Object> GetFareDealFlight(String DealType) {

    Map<String, Object> SetFlightDetail = new HashMap<String, Object>();
    WebElement ClickFlight = null;
    boolean IsDealFlightAvailable = false;

    List<WebElement> fareFamily = CheckUtils.getElements(getTest(), LOC_PB_BOUNDAVAIL_CSS_PANEL_LIST);

    DealFlight: for (WebElement singleFareFamily : fareFamily) {

      expandFFList(singleFareFamily);
      List<WebElement> FlightList = getValidFlightList(singleFareFamily);

      if (!(FlightList == null)) {
        for (WebElement Flight : FlightList) {

          System.out.println(DealType + ": " + Flight.getText());

          if (ClickFlight == null) {
            ClickFlight = Flight;
          }

          WebElement Marks = CheckUtils.getElement(getTest(), Flight, By.tagName("img"));

          if (DealType.equalsIgnoreCase("FareDeal")) {
            if (!(Marks == null)) {
              ClickFlight = Flight;
              IsDealFlightAvailable = true;
              break DealFlight;
            }
          }
          else {
            if ((Marks == null)) {
              ClickFlight = Flight;
              IsDealFlightAvailable = false;
              break DealFlight;
            }
          }
        }
      }
    }

    SetFlightDetail.put("ClickFlight", ClickFlight);
    SetFlightDetail.put("IsDealFlightAvailable", IsDealFlightAvailable);

    return SetFlightDetail;
  }


  public void VerifyHeaderSection(String PageName) {

    WebElement Header = CheckUtils.getElement(getTest(), LOC_PB_BOUNDAVAIL_CSS_Header);
    String headername;
    String headervalue;

    if (!(Header == null)) {

      headername = CheckUtils.getElement(getTest(), Header, By.tagName("h1")).getText().trim();
      headervalue = CheckUtils.getElement(getTest(), Header, By.tagName("p")).getText().trim();

      if (!(headername.isEmpty() && headervalue.isEmpty())) {
        reporter.reportPassed(PageName, "Header is displayed properly. (Header Name: " + headername + " and Header Value: " + headervalue + ")");
      }
      else {
        reporter.reportFailed(PageName, "Header is NOT displayed properly. (Header Name: " + headername + " and Header Value: " + headervalue + ")");
      }
    }
    else {
      reporter.reportFailed(PageName, "Header Section is NOT displayed properly.");
    }

  }
}


