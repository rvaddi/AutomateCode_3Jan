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
 * IcelandAir Payment page
 * @author vbalasubramanian
 *
 */
public class IcelandAirPaymentPage extends CommonPaymentPage{


  protected final static By LOC_BT_PAYMENT_CONFIRM_PURCHASE = By.id("btnConfirm");
  protected final static By LOC_BT_PAYMENT_CANCEL_BOOKING = By.cssSelector(".validation.cancel");

  public IcelandAirPaymentPage(SeleniumSEPTest test) throws Exception {
    super(test);
    // TODO Auto-generated constructor stub
  }

  /**
   * Fill Address Details
   */
  @Override
  public void fillAddressDetails(String Address1, String Address2, String City, String State, String zipCode,
      String Country) throws Exception {
    // TODO Auto-generated method stub
    super.fillAddressDetails(Address1, Address2, City, State, zipCode, Country);
  }

  /**
   * Fill payment Info
   */
  @Override
  public void fillPaymentInfo() throws Exception {
    // TODO Auto-generated method stub
    super.fillPaymentInfo();
  }

  /**
   * Cancel Booking
   */
  public void cancelBooking() throws Exception {
    ClickUtils
    .clickButtonOrFail(getTest(), LOC_BT_PAYMENT_CANCEL_BOOKING, "CancelButton Couldn't be pressed");
    driver.switchTo().alert().accept();

  }


  public ConfirmationPage clickContinue()throws Exception {
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

    return PageFactory.getPageObject(IcelandAirConfirmationPage.class);

  }
  //****************************************************************************


}
