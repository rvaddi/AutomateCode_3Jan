package com.amadeus.selenium.sqmobile.page.payment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class PaymentPage extends CommonPaymentPage{

  // Locators Payment Page -------------------------------------------------------------------------------

  protected final static By LOC_TX_PAYMENT_FARE_CONDITIONS = By.linkText("Fare Conditions");
  protected final static By LOC_PB_PAYMENT_FARE_CONDITIONS_POPUP = By.cssSelector(".panel.facs>header>h1");
  protected final static By LOC_TX_PAYMENT_TOTAL_PRICE = By.className("totalPrice");
  protected final static By LOC_IN_BOOKING_CONDITIONS_TITLE = By.className("boundsTitle");
  protected final static By LOC_PB_PAYMENT_PROMOTION_CODE = By.id("PROMOTION_CODE");
  protected final static By LOC_PB_PAYMENT_PROMOTION_BUTTON = By.id("promo_btn");
  protected final static By LOC_BT_PAYMENT_CONFIRM_PURCHASE = By.id("btnConfirm");

  protected final static By LOC_PB_PAYMENT_WARNMESSAGE = By.cssSelector(".msg.warning");
  //------------------------------------------------------------------------------------------------------

  public PaymentPage(SeleniumSEPTest test) throws Exception{
    super(test);
    // Checking if we are on the right page

    boolean PaymentPage = WaitUtils.waitForElementPresent(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE, 120);
    if (!PaymentPage) {
      WebElement errormsg = CheckUtils.getElement(getTest(), LOC_PB_PAYMENT_WARNMESSAGE);
      if (errormsg != null && errormsg.isDisplayed()) {
        reporter.fail(errormsg.getText().trim());
      }
      else {
        reporter.fail("This is not Payment Page");
      }
    }
    else {
      reporter.reportPassed("Payment Page", "In Payment Page");
    }
  }

  private String pageName = "Payment Page";

  //****************************************************************************
  //FUNCTION NAME   :validateTotalPrice
  //INPUT PARAM     :NA
  //OUTPUT PARAM    :NA
  //DESCRIPTION     :To validate the currency and fare of the Total price
  //NOTE            :None
  // need to check this method for  any id change (Vigneshwaran)
  public void validateTotalPrice()throws Exception {

    String totalPrice = CheckUtils.getElements(getTest(), LOC_TX_PAYMENT_TOTAL_PRICE).get(1).getText();

    if (totalPrice.contains(getValue("CURRENCY").trim()) && totalPrice.contains(getValue("TOTAL FARE").trim()) && totalPrice.contains("Total price")){
      reporter.reportPassed(pageName, "The total price section is displayed properly");
    }
    else{
      reporter.reportFailed(pageName, "The total price section os not displayed properly");
    }
  }



  //Functionality not working
  //Vigneshwaran

  public void validateFareConditionsPopUp()throws Exception {

    WebElement FareConditions = CheckUtils.getElement(getTest(), LOC_TX_PAYMENT_FARE_CONDITIONS);
    FareConditions.click();

    WebElement FareConditionsPopUp = CheckUtils.getElement(getTest(), LOC_PB_PAYMENT_FARE_CONDITIONS_POPUP);
    if (FareConditionsPopUp!=null && FareConditionsPopUp.isDisplayed()){
      if (FareConditionsPopUp.getText().contains("FARE CONDITIONS")){
        reporter.reportPassed(pageName, "Fare conditions pop up is displayed with proper header");
      }
      else {
        reporter.reportFailed(pageName, "Fare conditions pop up is displayed with incorrect contents");
      }
    }
    else {
      reporter.reportFailed(pageName, "Fare conditions pop up is not displayed");
    }

    List<WebElement> FareConditionsFields = FareConditionsPopUp.findElements(By.className("form"));
    reporter.report(pageName, FareConditionsFields.size() + " clauses displayed in Fare Conditions");
    for (WebElement field : FareConditionsFields){
      WebElement expand = CheckUtils.getElement(getTest(), field, By.className("accordionOn"));
      if (expand!=null && expand.isDisplayed()){
        expand.click();
        WebElement collapse = CheckUtils.getElement(getTest(), field, By.className("accordionOff"));
        if (collapse!=null && collapse.isDisplayed()){
          WebElement fieldTextArea = CheckUtils.getElement(getTest(), field, By.className("faresS"));
          if(fieldTextArea!=null && fieldTextArea.isDisplayed()){
            String fieldText = fieldTextArea.getText();
            if(fieldText!=null){
              collapse.click();
              reporter.reportPassed(pageName, "Fare conditions is displayed properly for " + expand.getAttribute("id"));
            }
            else {
              reporter.reportFailed(pageName, "Fare conditions details is not displayed for " + expand.getAttribute("id"));
            }
          }
        }
        else {
          reporter.reportFailed(pageName, "Collapse link is not displayed for " + expand.getAttribute("id"));
        }
      }
      else{
        reporter.reportFailed(pageName, "Expand link is not displayed for " + expand.getAttribute("id"));
      }
    }

    WebElement close = CheckUtils
    .getElement(getTest(), FareConditionsPopUp, LOC_PB_PAYMENT_FARE_CONDITIONS_POPUP_CLOSE);
    close.click();
  }

  /**
   * Verify Booking Conditons Popup
   * Need to test this method (Vigneshwaran)
   */
  public void verifyBookingConditions(){

    WebElement titleElt = CheckUtils.getElement(getTest(), LOC_IN_BOOKING_CONDITIONS_TITLE);
    if(titleElt.getText().contains("Booking Conditions")){
      reporter.reportPassed("Booking Condition Popup", "Booking Condition Popup displayed");
      List<WebElement> bookingConditionsCategory = CheckUtils.getElements(getTest(), LOC_IN_BOOKING_CONDITIONS_CATEGORY);
      for(WebElement elt : bookingConditionsCategory){
        if(elt.getText().equals("General conditions of carriage")){
          reporter.report("Booking Conditions","General conditions of carriage present");
        }
        else if(elt.getText().contains("Contract and notices")){
          reporter.report("Booking Conditions","Contract and notices present");
        }
        else if(elt.getText().contains("Conditions of Online Booking")){
          reporter.report("Booking Conditions","Conditions of Online Booking present");
        }
        else if(elt.getText().contains("Service Fees and Charges")){
          reporter.report("Booking Conditions","Service Fees and Charges present");
        }
      }
    }
    else{
      reporter.fail("Booking Condition popup not shown");
    }

  }


  // ****************************************************************************
  // FUNCTION NAME :fillPromotionCode
  // INPUT PARAM :Promotion code that is needed to be filled in the PromotionCode Section
  // OUTPUT PARAM :NA
  // DESCRIPTION :To fill Promotion Code
  // NOTE :None

  public void fillPromotionCode(String promotionCode)throws Exception {

    FillUtils.fillInputOrFail(getTest(), LOC_PB_PAYMENT_PROMOTION_CODE, "Promotion Code filled properly",
    "Promotion Code couldn't be filled");
    ClickUtils
    .clickButtonOrFail(getTest(), LOC_PB_PAYMENT_PROMOTION_BUTTON, "PromotionCode Button Couldn't be pressed");

  }
  // ****************************************************************************

}
