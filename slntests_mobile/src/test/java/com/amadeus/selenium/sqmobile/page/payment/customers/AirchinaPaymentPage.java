package com.amadeus.selenium.sqmobile.page.payment.customers;

import org.openqa.selenium.By;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.payment.CommonPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.IPayment;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class AirchinaPaymentPage extends CommonPaymentPage implements IPayment{

  protected final static By LOC_TX_IDENTIFICATION_FIELD = By.id("GEN_DOCUMENT_NUMBER_1");

  public AirchinaPaymentPage(SeleniumSEPTest test) throws Exception {
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_TX_IDENTIFICATION_FIELD, 30)){
      reporter.fail("This is not AirchinaPayment Page");
    }else{
      reporter.reportPassed("AirchinaPayment Page", "In AirchinaPayment Page");
    }
    this.automaticScreenShot();
  }


  /**
   * Fills Identification number and clicks on continue button
   * @param alphaNumericString
   * @return
   * @throws Exception
   */
  public ConfirmationPage clickContinue(String alphaNumericString) throws Exception {
    fillIdentificationNumber(alphaNumericString);
     return super.clickContinue();
  }

  /**
   * Fills Identification number and clicks on continue button
   * @return
   * @throws Exception
   */
  public ConfirmationPage clickContinue() throws Exception {
    fillIdentificationNumber("ABCD123");
     return super.clickContinue();
  }


  /**
   * Fills Identification Number
   * @param alphaNumericString
   */
  public void fillIdentificationNumber(String alphaNumericString){
    FillUtils.fillInputOrFail(getTest(), LOC_TX_IDENTIFICATION_FIELD, alphaNumericString, "Identification Details couldn't be filled properly");
  }

}
