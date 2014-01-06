package com.amadeus.selenium.sqmobile.page.confirmation.customers;

import org.openqa.selenium.By;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.confirmation.CommonConfirmationPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.confirmation.CommonConfirmationPage.Itinerary;
import com.amadeus.selenium.utils.WaitUtils;

public class ELALConfirmationPage extends ConfirmationPage {

  //LOCATORS - CONFIRMATION PAGE-----------------------------------------------------

  protected final static By LOC_FARE_CONDITION_LINK= By.className("popup-fare-cond");

  // --------------------------------------------------------------------------------

  public ELALConfirmationPage(SeleniumSEPTest test) throws Exception{
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_BOOKING_NUM, 30)){
      reporter.fail("This is not Confirmation Page");
    }else{
      reporter.reportPassed("Confirmation Page", "In Confirmation Page");
    }
    this.automaticScreenShot();
  }



  /**
   * Validates Confirmation Page
   * @author bsingh
   * @throws Exception
   */
  @Override
  public void validateConfirmationPage() throws Exception {
    validateBookingPNR();
   // validateEticket();
    validateEmailId();
    validateDepartureSummary();
    if(getValue("Trip Type").equalsIgnoreCase("RT")){
      validateArrivalSummary();
    }

    validatePriceBreakDown();
  }


  /**
   * Validates Departure Summary
   * @throws Exception
   */
  @Override
  public void validateDepartureSummary() throws Exception {
    validateItinerary(Itinerary.DEPARTURE);
    validateFlightDetails(Itinerary.DEPARTURE);;
  }


  /**
   * Validates Arrival Summary
   * @throws Exception
   * @author bsingh
   */
  @Override
  public void validateArrivalSummary() throws Exception {
    validateItinerary(Itinerary.RETURN);
    validateFlightDetails(Itinerary.RETURN);
  }


}