package com.amadeus.selenium.sqmobile.page.paxinfo.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.IPayment;
import com.amadeus.selenium.sqmobile.page.payment.customers.EgyptAirPaymentPage;
import com.amadeus.selenium.sqmobile.page.seat.SeatMapPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * AirEgypt PaxInfo page
 * @author vbalasubramanian
 *
 */
public class EgyptAirPaxInfoPage extends PaxInfoPage{

  protected static final By LOC_CB_AGREE_CHECKBOX = By.id("CheckPenaliesBox");
  protected static final By LOC_BT_CONFIRM_PURCHASE = By.className("validation");
  protected static final By LOC_IN_PAYMENT_TYPE_VISA = By.name("VISA_brand");
  protected static final By LOC_IN_PAYMENT_TYPE_EURO_CARD = By.name("Eurocard_brand");
  protected static final By LOC_IN_PAYMENT_TYPE_UATP_BRAND = By.name("UATP_brand");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME = By.cssSelector("[id^='FIRST_NAME']");


  public EgyptAirPaxInfoPage(SeleniumSEPTest test) {
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME, 30)){
      reporter.fail("This is not AirEgyptPaxInfo Page");
    }else{
      reporter.reportPassed("AirEgyptPaxInfo Page", "In AirEgyptPaxInfo Page");
    }
  }


  /**
   * Validate Total PRice
   * -History : Created by Vigneshwaran Balasubramanian
   */
  @Override
  public void validateTotalPrice() throws Exception {
    super.validateTotalPrice();
  }

  /**
   * Select Seat
   * -History : Created by Vigneshwaran Balasubramanian
   * @return
   * @throws Exception
   */
  @Override
  public SeatMapPage clickSelectSeat() throws Exception {
    return super.clickSelectSeat();
  }

  /**
   * Validate Pax Info page
   * -History : Created by Vigneshwaran Balasubramanian
   */
  @Override
  public void validatePaxInfoPage() throws Exception {
    super.validatePaxInfoPage();
  }

  /**
   * Fill Pax Info
   * -History : Created by Vigneshwaran Balasubramanian
   */
  @Override
  public void fillPaxInfo() throws Exception {
    super.fillPaxInfo();
  }

  /**
   * Fill Contact Information
   * -History : Created by Vigneshwaran Balasubramanian
   */
  @Override
  public void fillContactInfo(String contacType, String areaCode, String contactNumber, String email,
      String countryCode, String smsNotificationNumber) {
    super.fillContactInfo(contacType, areaCode, contactNumber, email, countryCode, smsNotificationNumber);
  }

  /**
   * Continue Payment
   * -History : Created by Vigneshwaran Balasubramanian
   */
  public IPayment continuePayment(String paymentType) throws Exception {
    WebElement payment = CheckUtils.getElement(getTest(), LOC_BT_PAX_INFO_CONTINUE_PAYMENT);
    if (payment!=null && payment.isDisplayed()){
      payment.click();
      waitForOverlayLoading(40);
/*      reporter.reportPassed("Passengar Information page", "Continue to Payment link is clicked");
      CheckUtils.checkMandatoryElementPresent(getTest(), LOC_CB_AGREE_CHECKBOX, "Agree Checkbox not present");
      ClickUtils.click(getTest(), LOC_CB_AGREE_CHECKBOX);
      CheckUtils.checkMandatoryElementPresent(getTest(), LOC_BT_CONFIRM_PURCHASE, "Confirm Purhcase button");
      ClickUtils.click(getTest(), LOC_BT_CONFIRM_PURCHASE);
      waitForOverlayLoading(25);
      CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_PAYMENT_TYPE_VISA, "Visa brand not present");
      CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_PAYMENT_TYPE_EURO_CARD, "Euro card brand not present");
      CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_PAYMENT_TYPE_UATP_BRAND, "UATP brand not present");
      if(paymentType.contains("Visa")){
        ClickUtils.click(getTest(), LOC_IN_PAYMENT_TYPE_VISA);
      }
      else if(paymentType.contains("Euro card")){
        ClickUtils.click(getTest(), LOC_IN_PAYMENT_TYPE_EURO_CARD);
      }
      else{
        ClickUtils.click(getTest(), LOC_IN_PAYMENT_TYPE_UATP_BRAND);
      }
      waitForOverlayLoading(25);*/
    }
    else{
      reporter.reportFailed("Passengar Information page", "Continue to Payment link is not displayed");
    }
    return PageFactory.getPageObject(EgyptAirPaymentPage.class);

  }
	// rvaddi
	public void fillEgyptContactInfo(String contactNumber , String email) {
		WebElement mobileNumber = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_MOBILE_NUMBER);
		if (mobileNumber!=null && mobileNumber.isDisplayed()){
			FillUtils.fillInputOrFail(getTest(), mobileNumber , contactNumber , "MobileNumber not entered");
		}
		WebElement contactEmailElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_EMAIL);
		if (contactEmailElt!=null && contactEmailElt.isDisplayed()){
			contactEmailElt.clear();
			FillUtils.fillInputOrFail(getTest(), contactEmailElt, email, "Email not entered");
		}
	}
}
