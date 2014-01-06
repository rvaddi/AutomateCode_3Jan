package com.amadeus.selenium.sqmobile.page.confirmation;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;

public class CommonConfirmationPage extends SqMobileCommonPage {


  //LOCATORS - CommonConfirmation Page --------------------------------------------------------

  protected final static By LOC_HEADER_TRIP_SUMMARY = By.className("summ");
  protected final static By LOC_BOOKING_NUM = By.className("booking-number");
  protected final static By LOC_MAIL_DETAILS = By.className("eticket");
  protected final static By LOC_LABLES_BOOKING_DETAILS= By.className("label");
  protected final static By LOC_DATA_BOOKING_DETAILS= By.className("data");
  protected final static By LOC_TICKET_NUM = By.cssSelector("#eTicketSection>ul>li>p");
  protected final static By LOC_TRIP_DATE = By.className("date");
  protected final static By LOC_TRIP_FLIGHT_NUM= By.className("flight-number");
  protected final static By LOC_TRIP_FLIGHT_DEPT= By.className("departure");
  protected final static By LOC_TRIP_FLIGHT_ARRIVAL= By.className("arrival");
  protected final static By LOC_TRIP_SECTION = By.className("trip");
  protected final static By LOC_FLIGHT_DETAILS_SECTION = By.className("details");
  protected final static By LOC_FLIGHT_DURATION = By.className("duration");
  protected final static By LOC_FLIGHT_AIRCRAFT = By.className("aircraft");
  protected final static By LOC_FLIGHT_FARE_FAMILY = By.className("fare-family");
  protected final static By LOC_FLIGHT_BAGGAGE = By.className("baggage");
  //protected final static By LOC_BUTTON_SEAT = By.cssSelector(".secondary.seat");
  protected final static By LOC_BUTTON_SEAT = By.cssSelector(".seat");
  protected final static By LOC_SERVICES_SECTION = By.className("services");
  protected final static By LOC_PASSENGERS = By.className("pax");
  protected final static By LOC_BUTTON_ADD_BAGGAGE = By.cssSelector(".secondary.baggage");
  protected final static By LOC_PRICE_BREAKDOWN_PANEL = By.cssSelector(".panel.breakdown.price");
  protected final static By LOC_PRICE_BREAKDOWN_PASSENGER_ROWS = By.cssSelector(".panel.breakdown.price>section>table>tbody>tr");
  protected final static By LOC_PRICE_BREAKDOWN_TABLE_DATA = By.tagName("td");
  protected final static By LOC_PRICE_BREAKDOWN_FOOTER_ROWS_TOTAL = By.cssSelector(".panel.breakdown.price>section>table>tfoot>tr>td");
  protected final static By LOC_PRICE_DETAILS_POPUP_LINK= By.className("view-price-details");
  protected final static By LOC_FARE_CONDITION_LINK= By.className("popup-fare-cond");
  protected final static By LOC_POPUP_PRICE_DETAILS = By.cssSelector(".panel.price");
  protected final static By LOC_POPUP_DETAIL_LIST = By.cssSelector(".panel.price>dl");
  protected final static By LOC_PRICE_DETAILS_POPUP_TOTAL_FARE = By.id("fareBrkTotalPrice");
  protected final static By LOC_BUTTON_POPUP_CLOSE = By.className("close");
  protected final static By LOC_FARE_CONDITION_POPUP_PANEL = By.cssSelector(".panel.facs");
  protected final static By LOC_FARE_CONDITION_TABS = By.cssSelector("[id^='tab']");
  protected final static By LOC_TABLE_HEADER = By.tagName("th");
  protected final static By LOC_HEADING_TAG = By.tagName("h1");

  //----------------------------------------------------------------------------

  public CommonConfirmationPage(SeleniumSEPTest test , String pageName) throws Exception{
    super(test);
    this.pageName = pageName ;
  }

  private String pageName = "Confirmation Page";


  public enum Itinerary {
    DEPARTURE , RETURN ;
  }


  /**
   * Validates whether the Booking number (PNR) is displayed properly
   * @author bsingh
   * @throws Exception
   */
  public void validateBookingPNR() throws Exception {

    WebElement bookingNum = CheckUtils.getElement(getTest(), LOC_BOOKING_NUM);
    if(bookingNum!=null){
      WebElement bookingNumberLableElt = CheckUtils.getElement(getTest(), bookingNum, LOC_LABLES_BOOKING_DETAILS);
      if(bookingNumberLableElt!=null && bookingNumberLableElt.isDisplayed() && bookingNumberLableElt.getText().toLowerCase().contains("booking number")) {
        reporter.reportPassed("Booking Num Label : ", bookingNumberLableElt.getText());
      }
      else {
        reporter.reportFailed(pageName, "Booking number lable is not displayed properly");
      }

      WebElement pnrElt = CheckUtils.getElement(getTest(), bookingNum, LOC_DATA_BOOKING_DETAILS);
      if(pnrElt!=null && pnrElt.isDisplayed() && !pnrElt.getText().equals("")){
        //Adding PNR into Properties File
        addValue("PNR",pnrElt.getText() );
        reporter.reportPassed(pageName, "PNR : "+pnrElt.getText() + " - is displayed properly");
      }else{
        reporter.fail("Confirmation Page : PNR is not displayed properly");
      }
    }else{
      reporter.reportFailed(pageName, "PNR is not displayed");
    }
  }


  /**
   * Validates the EmailId
   * @author bsingh
   * @throws Exception
   */
  public void validateEmailId() throws Exception {

    WebElement mailId = CheckUtils.getElement(getTest(), LOC_MAIL_DETAILS);

    if(mailId!= null){
      WebElement mailLableElt = CheckUtils.getElement(getTest(), mailId, LOC_LABLES_BOOKING_DETAILS);
      if(mailLableElt!=null && mailLableElt.getText().contains("sent to")) {
        reporter.reportPassed("Email Id Label : ", mailLableElt.getText());
      }else{
        reporter.reportFailed(pageName, "MailId Label is not displayed properly");
      }
      WebElement mailDataElt = CheckUtils.getElement(getTest(), mailId, LOC_DATA_BOOKING_DETAILS);
      if(mailDataElt!=null && ! mailDataElt.getText().equals("")) {
        reporter.reportPassed("Email Id : ", mailDataElt.getText());
      }else{
        reporter.reportFailed(pageName, "MailId is not displayed properly");
      }
    }
  }


  /**
   * Validates the TicketNumber
   * @author bsingh
   * @throws Exception
   */
  public void validateEticket() throws Exception {


    List<WebElement> ticketElt = CheckUtils.getElements(getTest(), LOC_TICKET_NUM);
    int totPassengers = totalPassengers();
    if(ticketElt!=null){
      if(totPassengers==ticketElt.size()/2){
        reporter.reportPassed(pageName, "TicketNumbers for all passengers are displayed");
      }else{
        reporter.reportFailed(pageName, "Ticket Number is not displayed for atleast for one passenger");
      }
      for(int i=0 ; i< ticketElt.size() ;i++){
        WebElement label = CheckUtils.getElement(getTest(), ticketElt.get(i), LOC_LABLES_BOOKING_DETAILS);
        WebElement data = CheckUtils.getElement(getTest(), ticketElt.get(i), LOC_DATA_BOOKING_DETAILS);
        if(label!=null && i % 2  == 0){
          if(label.getText().contains("Ticket number:")){
            reporter.reportPassed("Ticket Number Label : ", label.getText());
          }else{
            reporter.reportFailed("Ticket Number Label : ", label.getText());
          }
        }
        if(data!=null && i %2 ==0){
          if(!data.getText().equals("")){
            reporter.reportPassed("Ticket Number :  ",data.getText());
          }else{
            reporter.reportFailed("Ticket Number : ", data.getText());
          }

        }
      }
    }
  }


  /**
   * Calculates and returns total number of passengers
   * @return total number of passengers
   * @throws Exception
   */
  public int totalPassengers() throws Exception {

    int totPassengers = 0 ;
    //Getting total number of passengers
    if(getValue("Nb Adult")!= null && !getValue("Nb Adult").equalsIgnoreCase("")){
      totPassengers += Integer.parseInt(getValue("Nb Adult").trim()) ;
    }
    if(getValue("Nb Child")!=null && !getValue("Nb Adult").equalsIgnoreCase("")){
      totPassengers +=Integer.parseInt(getValue("Nb Child").trim()) ;
    }

    return totPassengers;
  }

  /**
   * Validates Departure Summary
   * @throws Exception
   */
  public void validateDepartureSummary() throws Exception {
    validateItinerary(Itinerary.DEPARTURE);
    validateFlightDetails(Itinerary.DEPARTURE);;
    validateSeatButton(Itinerary.DEPARTURE);
    validateAddBaggageButton(Itinerary.DEPARTURE);
  }


  /**
   * Validates Arrival Summary
   * @throws Exception
   * @author bsingh
   */
  public void validateArrivalSummary() throws Exception {
    validateItinerary(Itinerary.RETURN);
    validateFlightDetails(Itinerary.RETURN);
    validateSeatButton(Itinerary.RETURN);
    validateAddBaggageButton(Itinerary.RETURN);
  }

  /**
   * Validates ItineraryDetails
   * @param itinerary Departure or Arrival
   * @author bsingh
   * @throws IOException
   */
  public void validateItinerary(Itinerary itinerary) throws IOException {
    int index = 0;
    List<WebElement> flightSectionElt = CheckUtils.getElements(getTest(), LOC_FLIGHT_DETAILS_SECTION);
    if(itinerary.equals(Itinerary.RETURN)){
      index=1;
    }
    if(flightSectionElt!=null && (index==1 && flightSectionElt.size()>1) || flightSectionElt!=null && index==0 ){

      List<WebElement> tripSectionElt = CheckUtils.getElements(getTest(), LOC_TRIP_SECTION);
      if( tripSectionElt!=null && (index==0 || (tripSectionElt.size()>=2 && index==1)) ){
        WebElement dateElt = CheckUtils.getElement(getTest(), tripSectionElt.get(index), LOC_TRIP_DATE);
        // from tripsummary page
        if(dateElt!=null ){
          String date = dateElt.getText().trim();
          if (date.contains(getValue(itinerary + " DATE"))) {
            reporter.reportPassed(itinerary + " Date : ", date);
          }else{
            reporter.reportFailed(itinerary + " Date : ", date);
          }
        }else{
          reporter.reportFailed (pageName , "Date is not displayed properly");
        }

        WebElement flightNumElt = null;
        if (itinerary.equals(Itinerary.RETURN)) {
          if (!(tripSectionElt.size() > 2)) {
            flightNumElt = CheckUtils.getElement(getTest(), tripSectionElt.get(index), LOC_TRIP_FLIGHT_NUM);
          }
          else {
            List<WebElement> segment = CheckUtils.getElements(getTest(), By.cssSelector("article[class=panel]"));
            List<WebElement> tripSection = CheckUtils.getElements(getTest(), segment.get(3), LOC_TRIP_SECTION);
            flightNumElt = CheckUtils.getElement(getTest(), tripSection.get(0), LOC_TRIP_FLIGHT_NUM);
          }
        }
        else {
          flightNumElt = CheckUtils.getElement(getTest(), tripSectionElt.get(index), LOC_TRIP_FLIGHT_NUM);
        }

        // from tripsummary page
        if(flightNumElt!=null ){
          String flightnumber = flightNumElt.getText().trim();
          if (flightnumber.contains(getValue(itinerary + " FLIGHT"))) {
            reporter.reportPassed(itinerary + " Flight Number : ", flightnumber);
          }else{
            reporter.reportFailed(itinerary + " Flight Number  : ", flightnumber);
          }
        }else{
          reporter.reportFailed(pageName, itinerary+" Flight Number is not displayed properly");
        }
      }else{
        reporter.reportFailed(pageName, itinerary+" Summary is not displayed ");
      }
    }
  }


  /**
   * Validates Flights Details in Arrival Section
   * @author bsingh
   * @throws Exception
   */
  public void validateFlightDetails(Itinerary itinerary) throws Exception {

    int index = 0;
    List<WebElement> flightSectionElt = CheckUtils.getElements(getTest(), LOC_FLIGHT_DETAILS_SECTION);
    if(itinerary.equals(Itinerary.RETURN)){
      index=1;
    }
    if(flightSectionElt!=null && (index==0 || (index==1 && flightSectionElt.size()>1))){
      List<WebElement> durationElt = CheckUtils.getElements(getTest(), LOC_FLIGHT_DURATION);
      // from tripsummary page
      if(durationElt!=null ){
        WebElement duration = CheckUtils.getElement(getTest(), durationElt.get(index), LOC_DATA_BOOKING_DETAILS);
        if(duration!=null){
          String durationtime = duration.getText().trim();
          if (durationtime.matches("[\\d]{1,2} hr [\\d]{1,2} min")) {
            reporter.reportPassed(itinerary + " Flight Duration : ", durationtime);
          }else{
            reporter.reportFailed(itinerary + " Flight Duration : ", durationtime);
          }
        }else{
          reporter.reportFailed (pageName,itinerary+ " Flight Duration is not displayed properly");
        }
      }else{
        reporter.reportFailed (pageName,itinerary+ " Flight Duration is not displayed properly");
      }

      WebElement aircraftElt = CheckUtils.getElement(getTest(), flightSectionElt.get(index), LOC_FLIGHT_AIRCRAFT);
      // from tripsummary page
      if(aircraftElt!=null ){
        WebElement aircraft = CheckUtils.getElement(getTest(), aircraftElt , LOC_DATA_BOOKING_DETAILS);
        if (aircraft != null) {
          String flightaircraft = aircraft.getText().trim();
          if (getValue(itinerary + " AIRCRAFT").contains(flightaircraft)) {
            reporter.reportPassed(itinerary + " Flight Aircraft : ", flightaircraft);
          }
        }else{
          reporter.reportFailed (pageName,itinerary+ " Flight Airtcraft is not displayed properly");
        }
      }

      WebElement fareFamilyElt = CheckUtils.getElement(getTest(), flightSectionElt.get(index), LOC_FLIGHT_FARE_FAMILY);
      // from tripsummary page
      if(fareFamilyElt!=null ){
        WebElement fairFamily = CheckUtils.getElement(getTest(), fareFamilyElt , LOC_DATA_BOOKING_DETAILS);
        if(fairFamily!=null ){
          reporter.reportPassed(itinerary+" Flight FairFamily : ",  fairFamily.getText() );
        }else{
          reporter.reportFailed (pageName,itinerary+ " Flight FairFamily is not displayed properly");
        }
      }

/*      WebElement baggageElt = CheckUtils.getElement(getTest(), flightSectionElt.get(index), LOC_FLIGHT_BAGGAGE);
      if(baggageElt!=null ){
        WebElement baggage = CheckUtils.getElement(getTest(), baggageElt , LOC_DATA_BOOKING_DETAILS);
        if(baggage!=null && !baggage.getText().equals("")){
          reporter.reportPassed(pageName, itinerary+ " Flight Baggage is displayed properly");
        }else{
          reporter.reportFailed (pageName,itinerary+ " Baggage is not displayed properly");
        }
      }*/

    }
  }



  /**
   * Validates the presence of SeatSelection Button
   * @param itinerary Departure or Arrival for which button is to be checked
   * @author bsingh
   */
  public void validateSeatButton(Itinerary itinerary) {

    List<WebElement> tripSectionElt = CheckUtils.getElements(getTest(), LOC_SERVICES_SECTION);
    if(tripSectionElt!=null && !tripSectionElt.isEmpty()){
      if(itinerary.equals(Itinerary.DEPARTURE)){
        WebElement addBaggage = CheckUtils.getElement(getTest(), tripSectionElt.get(0) , LOC_BUTTON_SEAT);
        if(addBaggage!=null){
          reporter.reportPassed(pageName, "Seat Button is displayed for Departure section");
        }else{
          reporter.reportPassed(pageName, "Seat Button is not displayed for Departure section");
        }
      }else  if(itinerary.equals(Itinerary.RETURN)){
        WebElement addBaggage = CheckUtils.getElement(getTest(), tripSectionElt.get(1) , LOC_BUTTON_SEAT);
        if(addBaggage!=null){
          reporter.reportPassed(pageName, "Seat Button is displayed for Arrival section");
        }else{
          reporter.reportPassed(pageName, "Seat Button is not displayed for Arrival section");
        }
      }
    }
  }


  /**
   * Validates the presence of AddBaggage Button
   * @param itinerary Departure or Arrival for which button is to be checked
   * @author bsingh
   */
  public void validateAddBaggageButton(Itinerary itinerary) {

    List<WebElement> tripSectionElt = CheckUtils.getElements(getTest(), LOC_SERVICES_SECTION);

    if(tripSectionElt!=null && !tripSectionElt.isEmpty()){
      if(itinerary.equals(Itinerary.DEPARTURE)){
        WebElement addBaggage = CheckUtils.getElement(getTest(), tripSectionElt.get(0) , LOC_BUTTON_ADD_BAGGAGE);
        if(addBaggage!=null){
          reporter.reportPassed(pageName, "AddBAggage Button is displayed for Departure section");
        }else{
          reporter.reportPassed(pageName, "AddBAggage Button is not displayed for Departure section");
        }
      }else  if(itinerary.equals(Itinerary.RETURN)){
        WebElement addBaggage = CheckUtils.getElement(getTest(), tripSectionElt.get(1) , LOC_BUTTON_ADD_BAGGAGE);
        if(addBaggage!=null){
          reporter.reportPassed(pageName, "AddBAggage Button is displayed for Arrival section");
        }else{
          reporter.reportPassed(pageName, "AddBAggage Button is not displayed for Arrival section");
        }
      }
    }
  }




  /**
   * Validate PriceDetails Section
   * @author bsingh
   * @throws Exception
   */
  public void validatePriceBreakDown() throws Exception {
    validatePriceBreakdwonHeaders();
    validatePriceBreakdownPassengers();
    validatePriceBreakdownTotalFare();
  }



  /**
   * Validates Headers in PriceBreakdown Section
   * @author bsingh
   */
  public void validatePriceBreakdwonHeaders()   {
    WebElement pricingSection = CheckUtils.getElement(getTest(), LOC_PRICE_BREAKDOWN_PANEL);
    if(pricingSection!=null ) {
      //Validate Main Header(PricebreakDown Header)
      WebElement pricingSectionHeader = CheckUtils.getElement(getTest(), pricingSection, LOC_HEADING_TAG);
      if (pricingSectionHeader!=null && pricingSectionHeader.isDisplayed() && pricingSectionHeader.getText().toUpperCase().contains("PRICE BREAKDOWN")){
        reporter.reportPassed("Price Section Header : ",pricingSectionHeader.getText() );
      }else{
        reporter.reportFailed(pageName, "PriceBreakdown Header is not displayed properly");
      }
      //Validate Pricing headers
      List<WebElement> PricingSectionHeaders = CheckUtils.getElements(getTest(), pricingSection, LOC_TABLE_HEADER);
      if (PricingSectionHeaders!=null && PricingSectionHeaders.size()>2 && PricingSectionHeaders.get(0).getText().toLowerCase().contains("passengers") && PricingSectionHeaders.get(1).getText().toLowerCase().contains("total fare") && PricingSectionHeaders.get(3).getText().toLowerCase().contains("taxes and surcharges")){
        reporter.reportPassed(pageName, "Pricing Headers are displayed properly");
      }else{
        reporter.reportFailed(pageName, "PricingHeaders are not displayed properly");
      }
    }
  }


  /**
   * Validates PassengerDetails in PriceBreakdown Section
   * @author bsingh
   * @throws Exception
   */
  public void validatePriceBreakdownPassengers() throws Exception  {
    //Validate Adults and Child passengers

    WebElement pricingSection = CheckUtils.getElement(getTest(), LOC_PRICE_BREAKDOWN_PANEL);
    if(pricingSection!=null){
      List<WebElement> pricingSectionRows = CheckUtils.getElements(getTest(), pricingSection, LOC_PRICE_BREAKDOWN_PASSENGER_ROWS);
      if(pricingSectionRows!=null && !pricingSectionRows.isEmpty()){
        List<WebElement> adultTableData = CheckUtils.getElements(getTest(), pricingSectionRows.get(0), LOC_PRICE_BREAKDOWN_TABLE_DATA);
        if(adultTableData!=null && adultTableData.get(0).getText().contains("Adult") &&  adultTableData.get(0).getText().contains(getValue("Nb Adult").trim())){
          reporter.reportPassed("Adult(s) : ",adultTableData.get(0).getText() );
        }else{
          reporter.reportFailed(pageName,  "Number of Adults is not displayed properly");
        }
        if (!getValue("Nb Child").contains("0")) {
          if (getValue("Nb Child")!=null){
            List<WebElement> childTableData = CheckUtils.getElements(getTest(), pricingSectionRows.get(1), LOC_PRICE_BREAKDOWN_TABLE_DATA);
            if(childTableData.get(0).getText().contains("Child") && childTableData.get(0).getText().contains(getValue("Nb Child").trim())){
              reporter.reportPassed("Child : ", childTableData.get(0).getText());
            }else{
              reporter.reportFailed(pageName, "Number of Child passengers is not displayed properly : "+childTableData.get(0).getText());
            }
          }else{
            reporter.reportFailed("Pricing Section : ", "Child Passenger element is not displayed");
          }
        }
      }
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
        System.out.println(totalRowDataElts.get(i).getText());
        System.out.println(totalRowDataElts.get(i+1).getText());
        System.out.println(getValue("TOTAL FARE"));
        if (totalRowDataElts.get(i).getText().toUpperCase().contains("TOTAL")) {
          if (getValue("TOTAL FARE").contains(totalRowDataElts.get(i + 1).getText())) {
            addValue("TOTAL FARE", totalRowDataElts.get(i + 1).getText());
            reporter.reportPassed("Total Fare : ", totalRowDataElts.get(i + 1).getText());
            totFareDisplayed = true;
            break;
          }
          totFareDisplayed = true;
          reporter.reportFailed("Total Fare Mismatch : ", totalRowDataElts.get(i + 1).getText());
          break;
        }
      }
    }
    if(!totFareDisplayed) {
      reporter.reportFailed(pageName, "Total Fare is not displayed properly");
    }
}


  /**
   * Validates Passenger details in  Departure / Arrival section of journey
   * @param itinerary Departure of Arrival
   * @throws Exception
   * @author bsingh
   */
  public void validatePassengers(Itinerary itinerary) throws Exception {
    List<WebElement> tripSectionElt = CheckUtils.getElements(getTest(), LOC_SERVICES_SECTION);

    if(tripSectionElt!=null){
      if(itinerary.equals(Itinerary.DEPARTURE)){
        List<WebElement> passengerElt = CheckUtils.getElements(getTest(), tripSectionElt.get(0) , LOC_PASSENGERS);
        if(passengerElt!=null && !passengerElt.isEmpty() && passengerElt.size()==totalPassengers()){
          reporter.reportPassed("Tot Numb OF Pax : ", passengerElt.size()+"");
        }else{
          reporter.reportPassed(pageName, "Total Number of passengers displayed for Departure is Incorrect");
        }
      }else  if(itinerary.equals(Itinerary.RETURN)){
        List<WebElement> passengerElt = CheckUtils.getElements(getTest(), tripSectionElt.get(1) , LOC_PASSENGERS);
        if(passengerElt!=null && !passengerElt.isEmpty() && passengerElt.size()==totalPassengers()){
          reporter.reportPassed(pageName, "Total Number of passengers displayed for Arrival is correct");
        }else{
          reporter.reportPassed(pageName, "Total Number of passengers displayed for Arrival is Incorrect");
        }
      }
    }
  }


  /**
   * Clicks PriceDetailsPopUp Link
   * @author bsingh
   */
  public void clickPriceDetailsPopUpLink()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_PRICE_DETAILS_POPUP_LINK, "PriceDetailsPopupLink couldn't be clicked " , "PriceDetailsPopupLink is clicked ");
  }


  /**
   * Clicks FareCondition Link
   * @author bsingh
   */
  public void clickFareConditionLink()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_FARE_CONDITION_LINK , "FareCondition Link couldn't be clicked " , "FareCondition Link is clicked ");
  }


  public void validatePriceDetailsPopUp() throws Exception {

    List<WebElement> priceDetailsPopUp = CheckUtils.getElements(getTest(), LOC_POPUP_PRICE_DETAILS);
    if(priceDetailsPopUp!=null && priceDetailsPopUp.size()>1){
      List<WebElement> priceTableHeader = CheckUtils.getElements(getTest(), priceDetailsPopUp.get(1), LOC_LABLES_BOOKING_DETAILS);
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
      if (!getValue("Nb Child").contains("0")){
        if (!priceTableDetails.get(0).getText().contains("Child Passenger") || !priceTableDetails.get(1).getText().contains("Child Passenger")){
          reporter.reportFailed(pageName, "The price details are not displayed properly  for CHILD Passengers in the View Price Details Pop Up");
        }
      }else{
        reporter.reportFailed(pageName, "The price details are not displayed properly  View Price Details Pop Up");
      }

      WebElement totPriceElt = CheckUtils.getElement(getTest(), LOC_PRICE_DETAILS_POPUP_TOTAL_FARE);
      if (totPriceElt!=null && totPriceElt.isDisplayed() && totPriceElt.getText().equals(getValue("TOTAL FARE").trim())) {
        reporter.reportPassed("Total Price matched : ", totPriceElt.getText());
      }
      else {
        reporter.reportFailed(pageName, "Total Price is not displayed properly in the Price Details pop up");
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