package com.amadeus.selenium.sqmobile.page.confirmation.customers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class AirCarabConfirmationPage extends ConfirmationPage {

  //LOCATORS - CONFIRMATION PAGE-----------------------------------------------------

  protected final static By LOC_FARE_CONDITION_LINK= By.className("popup-fare-cond");

  // --------------------------------------------------------------------------------

  public AirCarabConfirmationPage(SeleniumSEPTest test) throws Exception{
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
   * Validates Confirmation Page ( Not Checking for E-ticket For AirCarab)
   * @author bsingh
   * @throws Exception
   */
  @Override
  public void validateConfirmationPage() throws Exception {
    validateBookingPNR();
    validateEmailId();
    validateDepartureSummary();
    if(getValue("Trip Type").equalsIgnoreCase("RT")){
      validateArrivalSummary();
    }

    validatePriceBreakDown();
  }

  /**
   * Validates TotalFare in PriceBreakDownSection
   * Here we are not comparing total price displayed on confirmation page with that of FareReview Page as it is
   * including insurance on payment page
   * @throws Exception
   * @author bsingh
   */
  @Override
  public void validatePriceBreakdownTotalFare() throws Exception {
    List<WebElement> totalRowDataElts = CheckUtils.getElements(getTest(), LOC_PRICE_BREAKDOWN_FOOTER_ROWS_TOTAL);
    boolean totFareDisplayed = false;
    if (totalRowDataElts != null && !totalRowDataElts.isEmpty()) {
      for (int i = 0; i <= totalRowDataElts.size() - 2; i++) {
        System.out.println(totalRowDataElts.get(i).getText());
        System.out.println(totalRowDataElts.get(i + 1).getText());
        System.out.println(getValue("TOTAL FARE"));
        if (totalRowDataElts.get(i).getText().toUpperCase().contains("TOTAL")) {
          addValue("TOTAL FARE", totalRowDataElts.get(i + 1).getText());
          reporter.reportPassed("Total  Fare : ", totalRowDataElts.get(i + 1).getText());
          totFareDisplayed = true;
          break;
        }
      }
    }
    if (!totFareDisplayed) {
      reporter.reportFailed("Confirmation Page", "Total Fare is not displayed properly");
    }
  }


}