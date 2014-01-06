package com.amadeus.selenium.sqmobile.page.confirmation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class ATCConfirmationPage extends  CommonConfirmationPage {

  //LOCATORS - ATCCONFIRMATION PAGE--------------------------------------------------------

  protected final static By LOC_PRICE_BREAKDOWN_PASSENGER_ROWS = By.cssSelector(".panel.breakdown.price>section>table>tbody>tr");
  protected final static By LOC_PRICE_BREAKDOWN_TABLE_DATA = By.tagName("td");
  protected final static By LOC_TABLE_HEADER = By.tagName("th");
  protected final static By LOC_HEADING_TAG = By.tagName("h1");

  //----------------------------------------------------------------------------

  public ATCConfirmationPage(SeleniumSEPTest test) throws Exception{
    super(test , "ATCConfirmation Page");
    this.automaticScreenShot();
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_HEADER_TRIP_SUMMARY, 30)){
      reporter.fail("This is not ATCConfirmation Page");
    }else{
      reporter.reportPassed("ATCConfirmation Page", "In ATCConfirmation Page");
    }
  }

  private String pageName = "ATCConfirmation Page";



  /**
   * Validates ATCPriceBreakdown
   * @throws Exception
   * @author bsingh
   */
  public void validateATCPriceBreakDown() throws Exception {
    validatePriceBreakDown();
    validateNewPaymentDetails();
  }


  public void validateNewPaymentDetails() {
    List<WebElement> pricingDetailElts = CheckUtils.getElements(getTest(), LOC_PRICE_BREAKDOWN_PASSENGER_ROWS);
    String breakdownTdLables = "" ;
    if(pricingDetailElts!=null && !pricingDetailElts.isEmpty()){
      for(WebElement tdLabels : pricingDetailElts){
        breakdownTdLables += tdLabels.getText();
      }
      if(breakdownTdLables.contains("Previously paid")){
        reporter.reportPassed(pageName, "PriceBreakdown contains Previously Paid section");
      }else{
        reporter.reportFailed(pageName, "PriceBreakdown doesn't contains Previously Paid section");
      }
      if(breakdownTdLables.contains("Fare difference")){
        reporter.reportPassed(pageName, "PriceBreakdown contains Fare difference section");
      }else{
        reporter.reportFailed(pageName, "PriceBreakdown doesn't contains Fare difference section");
      }
    }
  }

}