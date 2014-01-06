package com.amadeus.selenium.sqmobile.page.payment.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.confirmation.customers.IcelandAirConfirmationPage;
import com.amadeus.selenium.sqmobile.page.payment.CommonPaymentPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;
/**
 * Payemnt Page for Saudi Airlines
 * @author vbalasubramanian
 *
 */
public class SaudiPaymentPage extends CommonPaymentPage {

  protected final static By LOC_BT_PAYMENT_CONFIRM_PURCHASE = By.id("btnConfirm");
  protected final static By LOC_BT_PAYMENT_CANCEL_BOOKING = By.cssSelector(".buttonDirection.floatL");

  public SaudiPaymentPage(SeleniumSEPTest test) throws Exception {
    super(test);
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE, 30)){
      reporter.fail("This is not SaudiPayment Page");
    }else{
      reporter.reportPassed("SaudiPayment Page", "In SaudiPayment Page");
    }
  }
  /**
   * To Fill Address Details
   * @param Address1
   * @param Address2
   * @param City
   * @param State
   * @param zipCode
   * @param Country
   * @throws Exception
   *  - History Created by : Vigneshwaran Balasubramanian
   */
  @Override
  public void fillAddressDetails(String Address1, String Address2, String City, String State, String zipCode,
      String Country) throws Exception {
    // TODO Auto-generated method stub
    super.fillAddressDetails(Address1, Address2, City, State, zipCode, Country);
  }

  /**
   * To Fill Payment Details
   * @throws Exception
   *  - History Created by : Vigneshwaran Balasubramanian
   */
  public  void fillPaymentInfo() throws Exception {
   super.fillPaymentInfo();
  }

  /**
   * Cancel Booking
   * - Histroy  : created by Vigneshwaran Balasubramanian
   */
  public void cancelBooking() throws Exception {
    ClickUtils
    .clickButtonOrFail(getTest(), LOC_BT_PAYMENT_CANCEL_BOOKING, "CancelButton Couldn't be pressed");
    driver.switchTo().alert().accept();

  }

  public SaudiPaymentPasswordPage clickContinueToPasswordPage()throws Exception {
    CheckUtils.getElement(getTest(), LOC_CB_PAYMENT_CHECK_BOX).click();
    WaitUtils.wait(1);
    WebElement confirm = CheckUtils.getElement(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE);
    if (confirm!=null && confirm.isDisplayed()){
      confirm.click();
      waitForOverlayLoading(45);
      reporter.reportPassed("IcelandAirPayment Page : ", "Confirm Purchase button is clicked");
    }
    else{
      reporter.reportFailed("IcelandAirPayment Page : ", "Confirm purchase button is not displayed");
    }

    return PageFactory.getPageObject(SaudiPaymentPasswordPage.class);

  }

  public void test(){

  }

}
