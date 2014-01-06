package com.amadeus.selenium.sqmobile.page.confirmation.customers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.confirmation.CommonConfirmationPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class SaudiConfirmationPage extends CommonConfirmationPage {

  //LOCATORS - CONFIRMATION PAGE-----------------------------------------------------

  protected final static By LOC_BOOKING_NUM = By.className("marginBottom");

  String pageName = "SaudiConfirmation Page";

  // --------------------------------------------------------------------------------

  public SaudiConfirmationPage(SeleniumSEPTest test) throws Exception{
    super(test , "Confirmation Page");
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_BOOKING_NUM, 40)){
      reporter.fail("This is not SaudiConfirmation Page");
    }else{
      reporter.reportPassed("SaudiConfirmation Page", "In SaudiConfirmation Page");
    }
    this.automaticScreenShot();
  }



  /**
   * Validates Booking Information
   * @throws Exception
   * @author bsingh
   */
  public void validateBookingInfo() throws Exception {
    List<WebElement> bookingElts = CheckUtils.getElements(getTest(), LOC_BOOKING_NUM);

    if(bookingElts!=null){
      WebElement pnrElt = bookingElts.get(0).findElement(By.className("strong"));
      if(bookingElts.get(0).isDisplayed() && pnrElt!= null && pnrElt.isDisplayed() && !pnrElt.getText().isEmpty()){
        reporter.reportPassed("PNR : ",  pnrElt.getText());
        addValue("PNR", pnrElt.getText().trim());
      }else{
        reporter.reportFailed(pageName, "PNR is not displayed properly");
      }
     if(bookingElts.size()>1){
        WebElement tickedElt = CheckUtils.getElement(getTest(), bookingElts.get(1), By.className("strongBlack"));
       if(bookingElts.get(1).isDisplayed() && tickedElt!= null && tickedElt.isDisplayed() && !tickedElt.getText().isEmpty()){
         reporter.reportPassed("E-ticket : ",  tickedElt.getText());
       }else{
         reporter.reportFailed(pageName, "E-ticket is not displayed properly");
       }
     }
    }
  }


  /**
   * Validates Saudi Specific Confirmation Page
   * @throws Exception
   */
  public void validateConfirmationPage() throws Exception {
    validateBookingInfo();
  }

}