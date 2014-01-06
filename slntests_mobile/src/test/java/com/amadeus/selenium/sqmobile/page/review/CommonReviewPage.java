package com.amadeus.selenium.sqmobile.page.review;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;

public class CommonReviewPage extends SqMobileCommonPage {



  //LOCATORS - COMMONREVIEW PAGE--------------------------------------------------------

  protected final static By LOC_LABLES_BOOKING_DETAIL_LABELS= By.className("label");
  protected final static By LOC_DATA_BOOKING_DETAILS= By.className("data");
  protected final static By LOC_TRIP_DATE = By.className("date");
  protected final static By LOC_TRIP_FLIGHT_NUM= By.className("flight-number");
  protected final static By LOC_TRIP_SECTION = By.className("trip");
  protected final static By LOC_FLIGHT_DETAILS_SECTION = By.className("details");
  protected final static By LOC_FLIGHT_AIRCRAFT = By.className("aircraft");
  protected final static By LOC_FLIGHT_DURATION = By.className("duration");
  protected final static By LOC_FLIGHT_FARE_FAMILY = By.className("fare-family");
  protected final static By LOC_PRICE_BREAKDOWN_PANEL = By.cssSelector(".panel.breakdown.price");
  protected final static By LOC_PRICE_BREAKDOWN_PASSENGER_ROWS = By.cssSelector(".panel.breakdown.price>section>table>tbody>tr");
  protected final static By LOC_PRICE_BREAKDOWN_TABLE_DATA = By.tagName("td");
  protected final static By LOC_PRICE_BREAKDOWN_FOOTER_ROWS= By.cssSelector("section>table>tfoot>tr");
  protected final static By LOC_PRICE_BREAKDOWN_FOOTER_ROWS_TOTAL = By.cssSelector(".panel.breakdown.price>section>table>tfoot>tr>td");
  protected final static By LOC_PRICE_DETAILS_POPUP_LINK= By.className("view-price-details");
  protected final static By LOC_POPUP_PRICE_DETAILS = By.cssSelector(".panel.price");
  protected final static By LOC_POPUP_DETAIL_LIST = By.cssSelector(".panel.price>dl");
  protected final static By LOC_PRICE_DETAILS_POPUP_TOTAL_FARE = By.id("fareBrkTotalPrice");
  protected final static By LOC_BUTTON_POPUP_CLOSE = By.className("close");
  protected final static By LOC_FARE_CONDITION_POPUP_PANEL = By.cssSelector(".panel.facs");
  protected final static By LOC_FARE_CONDITION_TABS = By.cssSelector("[id^='tab']");
  protected final static By LOC_TABLE_HEADER = By.tagName("th");
  protected final static By LOC_HEADING_TAG = By.tagName("h1");
  protected final static By LOC_PANELS = By.className("panel");
  protected final static By LOC_PANEL_HEADER = By.cssSelector("header>h1");

  //----------------------------------------------------------------------------


  public CommonReviewPage(SeleniumSEPTest test , String pageName) throws Exception{
    super(test);
    this.pageName = pageName;
  }

  private String pageName = "Common Page";


  public enum Itinerary {
    DEPARTURE , RETURN ;
  }


  /**
   * Validates Departure Summary
   * @throws Exception
   */
  public void validateDepartureSummary() throws Exception {
    WebElement panel = getPanelForItinerary(Itinerary.DEPARTURE);
    validateTripSection(Itinerary.DEPARTURE, panel);
    validateFlightDetails(Itinerary.DEPARTURE , panel);
  }


  /**
   * Validates Arrival Summary
   * @throws Exception
   * @author bsingh
   */
  public void validateArrivalSummary() throws Exception {
    WebElement panel = getPanelForItinerary(Itinerary.RETURN);
    validateTripSection(Itinerary.RETURN, panel);
    validateFlightDetails(Itinerary.RETURN , panel);
  }

  /**
   * Validates ItineraryDetails
   * @param itinerary Departure or Arrival
   * @throws Exception
   * @return WebElement Panel for the itinerary
   * @author bsingh
   */
  public WebElement getPanelForItinerary(Itinerary itinerary) throws Exception{
    List<WebElement> panelElts = CheckUtils.getElements(getTest(), LOC_PANELS);
    boolean panelFound = false ;
    WebElement panel = null ;
    for(WebElement panelElt: panelElts){
      if(panelElt.isDisplayed()){
        if(itinerary.equals(Itinerary.DEPARTURE) && (panelElt.getText().toUpperCase().contains("DEPARTURE")|| panelElt.getText().toUpperCase().contains("OUTBOUND"))){
          reporter.reportPassed(pageName, "Departure Panel Displayed");
          panel = panelElt;
          panelFound = true ;
          break;
        }
        else if (itinerary.equals(Itinerary.RETURN) &&  (panelElt.getText().toUpperCase().contains("RETURN") || panelElt.getText().toUpperCase().contains("INBOUND"))){
          reporter.reportPassed(pageName, "Return Panel Displayed");
          panel = panelElt;
          panelFound = true ;
          break;
        }
      }
    }
    if(!panelFound) {
      reporter.reportFailed(pageName, itinerary + " Panel not displayed");
    }

    return panel ;
  }



  /**
   * Validate Trip Section for the Itinerary given
   * @param itinerary DEPARUTRE / RETURN
   * @param panel
   * @throws Exception
   */
  public void validateTripSection(Itinerary itinerary , WebElement panel) throws Exception{
    WebElement tripSectionElt = CheckUtils.getElement(getTest(), panel ,LOC_TRIP_SECTION);
    if(tripSectionElt!=null ){
      WebElement dateElt = CheckUtils.getElement(getTest(), tripSectionElt , LOC_TRIP_DATE);
      if(dateElt!=null ){
        reporter.reportPassed(itinerary+" Date : ", dateElt.getText().trim());
        updateValue(itinerary+" DATE", dateElt.getText().trim());
      }else{
        reporter.reportFailed (pageName,itinerary+ " Date is not displayed properly");
      }
      WebElement flightNumElt = CheckUtils.getElement(getTest(), tripSectionElt, LOC_TRIP_FLIGHT_NUM);
      if(flightNumElt!=null ){
        reporter.reportPassed(pageName, itinerary+" Flight Number is displayed properly ");
        addValue(itinerary+" FLIGHT", flightNumElt.getText().trim());
      }else{
        reporter.reportFailed(pageName, itinerary+" Flight Number is not displayed properly");
      }
    }else{
      reporter.reportFailed(pageName, itinerary+" Summary is not displayed ");
    }
  }



  /**
   * Validates Flights Details in Departure Section
   * @param itinerary DEPARTURE/RETURN
   * @param panel Panel WebElement Departure or Return for which flight details are to be valdiated
   * @throws Exception
   * @author bsingh
   */
  public void validateFlightDetails(Itinerary itinerary , WebElement panel) throws Exception{
    WebElement flightSectionElt = CheckUtils.getElement(getTest(), panel,LOC_FLIGHT_DETAILS_SECTION);
    if(flightSectionElt!=null ){
      WebElement durationElt = CheckUtils.getElement(getTest(), panel, LOC_FLIGHT_DURATION);
      if(durationElt!=null ){
        reporter.reportPassed(itinerary+" Duration :", durationElt.getText().trim());
        addValue(itinerary+" DURATION", durationElt.getText().trim());
      }else{
        reporter.reportFailed(pageName, itinerary + " Duration is not displayed");
      }
      WebElement aircraftElt= CheckUtils.getElement(getTest(), flightSectionElt , LOC_FLIGHT_AIRCRAFT);
      if(aircraftElt!=null){
        addValue(itinerary + " AIRCRAFT", aircraftElt.getText().trim());
        reporter.reportPassed(itinerary+" Flight Aircraft : ", aircraftElt.getText().trim());
      }else{
        reporter.reportFailed(pageName, itinerary+" Flight Aircraft is not displayed");
      }
    }
    WebElement fareFamilyElt = CheckUtils.getElement(getTest(), flightSectionElt, LOC_FLIGHT_FARE_FAMILY);
    if(fareFamilyElt!=null ){
      reporter.reportPassed(pageName, "Departure Flight FairFamily is displayed properly");
    }else{
      reporter.reportFailed (pageName,"Departure Flight FairFamily is not displayed properly");
    }
  }




  /**
   * Validate PriceDetails Section
   * @throws Exception
   * @author bsingh
   */
  public void validatePriceBreakDown() throws Exception {
    validatePriceBreakdwonHeaders();
    validatePriceBreakdownPassengers();
    validatePriceBreakdownTotalFare();
  }



  /**
   * Validates Headers in PriceBreakdown Section
   * @throws Exception
   * @author bsingh
   */
  public void validatePriceBreakdwonHeaders()  {
    WebElement pricingSection = CheckUtils.getElement(getTest(), LOC_PRICE_BREAKDOWN_PANEL);
    if(pricingSection!=null ) {
      //Validate Main Header(PricebreakDown Header)
      WebElement pricingSectionHeader = CheckUtils.getElement(getTest(), pricingSection, LOC_HEADING_TAG);
      if (pricingSectionHeader!=null && pricingSectionHeader.isDisplayed() && pricingSectionHeader.getText().toUpperCase().contains("PRICE BREAKDOWN")){
        reporter.reportPassed(pageName, "PriceBreakdown Header is displayed properly");
      }else{
        reporter.reportFailed(pageName, "PriceBreakdown Header is not displayed properly");
      }
      //Validate Pricing headers
      List<WebElement> PricingSectionHeaders = CheckUtils.getElements(getTest(), pricingSection, LOC_TABLE_HEADER);
      if (PricingSectionHeaders!=null && PricingSectionHeaders.size()>2 && PricingSectionHeaders.get(0).getText().contains("Passengers") && PricingSectionHeaders.get(1).getText().contains("Total fare") && PricingSectionHeaders.get(3).getText().contains("Taxes and surcharges")){
        reporter.reportPassed(pageName, "Pricing Headers are displayed properly");
      }else{
        reporter.reportFailed(pageName, "PricingHeaders are not displayed properly");
      }
    }
  }


  /**
   * Validates PassengerDetails in PriceBreakdown Section
   * @throws Exception
   * @author bsingh
   */
  public void validatePriceBreakdownPassengers() throws Exception {
    //Validate Adults and Child passengers

    WebElement pricingSection = CheckUtils.getElement(getTest(), LOC_PRICE_BREAKDOWN_PANEL);
    if(pricingSection!=null && pricingSection.isDisplayed() ){
      List<WebElement> pricingSectionRows = CheckUtils.getElements(getTest(), pricingSection, LOC_PRICE_BREAKDOWN_PASSENGER_ROWS);
      if(pricingSectionRows!=null && !pricingSectionRows.isEmpty()){
        List<WebElement> adultTableData = CheckUtils.getElements(getTest(), pricingSectionRows.get(0), LOC_PRICE_BREAKDOWN_TABLE_DATA);
        boolean adultDisplayedProperly = false ;
        String displayedText = null ;
        for(WebElement adult : adultTableData){
         if(adult.getText().contains("Adult") && adult.getText().contains(getValue("Nb Adult").trim())){
           adultDisplayedProperly = true ;
           displayedText = adult.getText();
           break;
         }
        }
        if(adultDisplayedProperly) {
          reporter.reportPassed("Adult : ",displayedText);
        }
        else {
          reporter.reportFailed("Review Page : ",  "Number of Adults is not displayed properly");
        }

        if (getValue("Nb Child")!=null && !getValue("Nb Child").contains("0") ){
          boolean childDisplayedProperly = false ;
          List<WebElement> childTableData = CheckUtils.getElements(getTest(), pricingSectionRows.get(1), LOC_PRICE_BREAKDOWN_TABLE_DATA);
          for(WebElement child : childTableData){
            if(child.getText().contains("Child") && child.getText().contains(getValue("Nb Child").trim())){
              childDisplayedProperly = true ;
              displayedText = child.getText();
              break;
            }
           }
           if(childDisplayedProperly) {
            reporter.reportPassed("Child : ",displayedText);
          }
          else {
            reporter.reportFailed("Review Page : ",  "Number of Children is not displayed properly");
          }
        }
      }
    }else{
      reporter.reportFailed(pageName, "Pricing Section is not displayed");
    }
  }


  /**
   * Validates TotalFare in PriceBreakDownSection
   * @throws Exception
   * @author bsingh
   */
  public void validatePriceBreakdownTotalFare() throws Exception{

        List<WebElement> totalRowDataElts = CheckUtils.getElements(getTest(), LOC_PRICE_BREAKDOWN_FOOTER_ROWS_TOTAL);
        boolean totFareDisplayed = false ;
        if(totalRowDataElts!=null && !totalRowDataElts.isEmpty()){
          for(int i=0 ;i<=totalRowDataElts.size()-2 ; i++){
            if(totalRowDataElts.get(i).getText().toUpperCase().contains("TOTAL") && !totalRowDataElts.get(i+1).getText().equals("")){
              addValue("TOTAL FARE", totalRowDataElts.get(i+1).getText());
              reporter.reportPassed("Total Fare : ", totalRowDataElts.get(i+1).getText());
              totFareDisplayed = true ;
              break;
            }
          }
        }
        if(!totFareDisplayed) {
          reporter.reportFailed(pageName, "Total Fare is not displayed properly");
        }
  }


  /**
   * Clicks PriceDetailsPopUp Link
   * @author bsingh
   */
  public void clickPriceDetailsPopUpLink() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_PRICE_DETAILS_POPUP_LINK, "PriceDetailsPopupLink couldn't be clicked " , "PriceDetailsPopupLink is clicked ");
  }



  /**
   * Validates Price Details PopUp
   * @throws Exception
   * @author bsingh
   */
  public void validatePriceDetailsPopUp() throws Exception {

    List<WebElement> priceDetailsPopUp = CheckUtils.getElements(getTest(), LOC_POPUP_PRICE_DETAILS);

    if(priceDetailsPopUp!=null && priceDetailsPopUp.size()>1){

      List<WebElement> priceTableHeader = CheckUtils.getElements(getTest(), priceDetailsPopUp.get(1), LOC_LABLES_BOOKING_DETAIL_LABELS);

      if ( priceTableHeader !=null && priceTableHeader.get(0).getText().contains("TOTAL FARE") && priceTableHeader.get(1).getText().contains("TAXES AND SURCHARGES")){
        reporter.reportPassed(pageName, "The price details  displayed properly in the View Price Details Pop Up");
      }else{
        reporter.reportFailed(pageName, "The price details are not displayed properly in the View Price Details Pop Up");
      }

      List<WebElement> priceTableDetails = CheckUtils.getElements(getTest(),  LOC_POPUP_DETAIL_LIST);
      if(priceTableDetails!=null  && priceTableDetails.size()>2) {
        if (!priceTableDetails.get(0).getText().contains("Adult Passenger") || !priceTableDetails.get(1).getText().contains("Adult Passenger")){
          reporter.reportFailed(pageName, "The price details are not displayed properly  for ADULT Passengers in the View Price Details Pop Up");
        }
      }
      if (!getValue("NB CHILD").contains("0")){
        if (!priceTableDetails.get(0).getText().contains("Child Passenger") || !priceTableDetails.get(1).getText().contains("Child Passenger")){
          reporter.reportFailed(pageName, "The price details are not displayed properly  for CHILD Passengers in the View Price Details Pop Up");
        }
      }else{
        reporter.reportFailed(pageName, "The price details are not displayed properly  View Price Details Pop Up");
      }

      WebElement totPriceElt = CheckUtils.getElement(getTest(), LOC_PRICE_DETAILS_POPUP_TOTAL_FARE);
      if (totPriceElt!=null && totPriceElt.isDisplayed() && totPriceElt.getText().equals(getValue("TOTAL FARE").trim())) {
        reporter.reportPassed("Total Price : ", totPriceElt.getText());
      }
      else {
        reporter.reportFailed("Total Price : ", totPriceElt.getText());
      }

      List<WebElement> closeLinks = CheckUtils.getElements(getTest(), LOC_BUTTON_POPUP_CLOSE);
      for (WebElement close : closeLinks){
        if (close.isDisplayed()){
          close.click();
          break;
        }
      }
    }
  }

}