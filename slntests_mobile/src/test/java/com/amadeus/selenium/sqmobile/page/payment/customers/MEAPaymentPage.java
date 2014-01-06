package com.amadeus.selenium.sqmobile.page.payment.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.payment.CommonPaymentPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * MEA payment page
 * @author vbalasubramanian
 *
 */
public class MEAPaymentPage extends CommonPaymentPage{

  protected final static By LOC_IN_PAYMENT_CARD_NUMBER = By.id("Ecom_Payment_Card_Number");
  protected final static By LOC_IN_PAYMENT_CARD_EXPIRY_MONTH = By.id("Ecom_Payment_Card_ExpDate_Month");
  protected final static By LOC_IN_PAYMENT_CARD_EXPIRY_YEAR = By.id("Ecom_Payment_Card_ExpDate_Year");
  protected final static By LOC_IN_PAYMENT_CARD_CVV= By.id("Ecom_Payment_Card_Verification");

  protected final static By LOC_TX_PAYMENT_ADDRESS_PREFILL = By.id("checkbox1");
  protected final static By LOC_PB_PAYMENT_FARE_CONDITIONS_POPUP_CLOSE = By.className("calendarClose");
  protected final static By LOC_BT_PAYMENT_CONFIRM_PURCHASE = By.className("ncol");
  protected final static By LOC_BT_PAYMENT_CANCEL_BOOKING = By.cssSelector(".buttonDirection.floatL");

  public MEAPaymentPage(SeleniumSEPTest test, String pageName) throws Exception {
    super(test);
  }

  /**
   * To Fill Payment Details
   * @throws Exception
   *  - History Created by : Vigneshwaran Balasubramanian
   */
  public  void fillPaymentInfo() throws Exception {

    WebElement cardNumberElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_NUMBER);
    WebElement cardExpiryMonthElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_EXPIRY_MONTH);
    WebElement cardExpiryYearElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_EXPIRY_YEAR);
    WebElement cardCVVElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_CVV);

    CommonUtils utils =  PageFactory.getPageObject(CommonUtils.class);
    String Dep = utils.addDate("dd MM yyyy",  new Integer (getValue("Card Expiry Days")));
    String CardExpiryMonth = (Dep.split(" "))[1];
    String CardExpiryYear = (Dep.split(" "))[2];
    String CardNumber =getValue("Card Number").trim();
    String CardCVV = getValue("Card CVV").trim();

    WaitUtils.wait(3);

    //Update Card Number
    if (cardNumberElt!=null && cardNumberElt.isDisplayed()){
      if (!cardNumberElt.getAttribute("value").equals("")){
        reporter.reportFailed("Payment Page", "Card Number field is not empty");
      }
      cardNumberElt.clear();
      FillUtils.fillInputOrFail(getTest(), cardNumberElt, CardNumber, "Card Number not entered");
      reporter.reportPassed("Card Number filled : ", CardNumber);
    }
    else {
      reporter.reportFailed("Payment Page", "Card Number field is not displayed");}

    //Update Card expiry date
    if (cardExpiryMonthElt!=null && cardExpiryMonthElt.isDisplayed()){
      FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAYMENT_CARD_EXPIRY_MONTH, CardExpiryMonth, "Card Expiry month not selected");
      reporter.reportPassed("CardExpiry Month filled : ", CardExpiryMonth);
    }
    else {
      reporter.reportFailed("Payment Page", "Card Expiry Month  field is not displayed");}

    if (cardExpiryYearElt!=null && cardExpiryYearElt.isDisplayed()){
      Select cardexpiryYear = new Select (cardExpiryYearElt);
      cardexpiryYear.selectByVisibleText(CardExpiryYear);
      reporter.reportPassed("CardExpiry Year filled : ", CardExpiryYear);
    }
    else {
      reporter.reportFailed("Payment Page", "Card Expiry Year field is not displayed");}

    //Update Card CVV
    if (cardCVVElt!=null && cardCVVElt.isDisplayed()){
      if (!cardCVVElt.getAttribute("value").equals("")){
        reporter.reportFailed("Payment Page", "Card CVV field is not empty");
      }
      cardCVVElt.clear();
      FillUtils.fillInputOrFail(getTest(), cardCVVElt, CardCVV, "Card CVV not entered");
      reporter.reportPassed("CardCVV filled : ", CardCVV);
    }
    else {
      reporter.reportFailed("Payment Page", "Card Holder Name field is not displayed");}

  }

  /**
   * Click Continue button
   * - Histroy : Created by Vigneshwaran Balsubramanian
   */
  public ConfirmationPage clickContinue() throws Exception {

    WebElement confirm = CheckUtils.getElement(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE);
    if (confirm!=null && confirm.isDisplayed()){
      confirm.click();
      reporter.reportPassed("Payment Page", "Confirm Purchase button is clicked");
      getDriver().switchTo().alert().accept();
      waitForOverlayLoading(45);
    }
    else{
      reporter.reportFailed("Payment Page", "Confirm purchase button is not displayed");
    }
    return PageFactory.getPageObject(ConfirmationPage.class);
  }


  /**
   * Cancel Booking
   * - Histroy  : created by Vigneshwaran Balasubramanian
   */
  public void cancelBooking() throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BT_PAYMENT_CANCEL_BOOKING, "CancelButton Couldn't be pressed");
    driver.switchTo().alert().accept();

  }
}
