package com.amadeus.selenium.sqmobile.page.payment.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.confirmation.customers.AirCarabConfirmationPage;
import com.amadeus.selenium.sqmobile.page.payment.CommonPaymentPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Carribian Payment page
 * @author vbalasubramanian
 *
 */
public class CarabPaymentPage extends CommonPaymentPage{

  protected static final By LOC_IN_RB_INSURANCE = By.cssSelector(".insurance>input");

  public CarabPaymentPage(SeleniumSEPTest test) throws Exception {
    super(test);
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_CB_PAYMENT_CHECK_BOX, 30)){
      reporter.fail("This is not CarabPayment Page");
    }else{
      reporter.reportPassed("CarabPayment Page", "In CarabPayment Page");
    }
  }

  /**
   * Fill Payment info
   */
  @Override
  public void fillPaymentInfo() throws Exception {
    // TODO Auto-generated method stub
    super.fillPaymentInfo();
  }

  /**
   * Fill Address details
   */
  @Override
  public void fillAddressDetails(String Address1, String Address2, String City, String State, String zipCode,
      String Country) throws Exception {
    // TODO Auto-generated method stub
    super.fillAddressDetails(Address1, Address2, City, State, zipCode, Country);
  }

  /**
   * Fill Insurance Details
   * -History : created by Vigneshwaran Balasubramanian
   */
  public void fillInsurance(int radionbuttonIndex){
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_RB_INSURANCE, "Insurance Fields not present");
    WebElement insuranceElts = CheckUtils.getElements(getTest(), LOC_IN_RB_INSURANCE).get(radionbuttonIndex-1);
    ClickUtils.click(getTest(), insuranceElts);
    reporter.report("Payment page", "Insurance type selected");

  }


  public ConfirmationPage clickContinue()throws Exception {
    CheckUtils.getElement(getTest(), LOC_CB_PAYMENT_CHECK_BOX).click();
    WaitUtils.wait(1);
    WebElement confirm = CheckUtils.getElement(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE);
    if (confirm!=null && confirm.isDisplayed()){
      confirm.click();
      waitForOverlayLoading(100);
      reporter.reportPassed("Payment Page", "Confirm Purchase button is clicked");
    }
    else{
      reporter.reportFailed("Payment Page", "Confirm purchase button is not displayed");
    }

    return PageFactory.getPageObject(AirCarabConfirmationPage.class);
  }

}
