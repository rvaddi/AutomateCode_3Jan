package com.amadeus.selenium.sqmobile.page.review;

import org.openqa.selenium.By;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;
/**
 * ATC FareReview Page
 * @author bsingh
 */
public class ATCFareReviewPage extends CommonReviewPage {


  //LOCATORS - ATCFareReview PAGE---------------------------------------------------

  protected final static By LOC_BUTTON_CONTINUE = By.className("validation");
  protected final static By LOC_PRICE_BREAKDOWN_FOOTER_ROW= By.tagName("section>table>tfoot>tr>td");

  //--------------------------------------------------------------------------------

  public ATCFareReviewPage(SeleniumSEPTest test) throws Exception {
    super(test , "ATCFareReview Page" );
    this.automaticScreenShot();
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_CONTINUE, 30)){
      reporter.fail("This is not ATCFareReview Page");
    }else{
      reporter.reportPassed("ATCFareReview Page", "In ATCFareReview Page");
    }

  }


  /**
   * Validate Fare Review Page
   * @throws Exception
   */
  public void validateFareReviewPage() throws Exception{
    validateDepartureSummary();
    if( getValue("TRIP TYPE").equals("RT")){
      validateArrivalSummary();
    }
    validatePriceBreakDown();
  }


  /**
   * Clicks Continue Button
   */
  public void clickContinue()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CONTINUE, "Continue Button couldn't be clicked", "Successfully clicked Continue Button");
    waitForOverlayLoading(50);
  }


}
