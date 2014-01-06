package com.amadeus.selenium.sqmobile.page.paxinfo.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.paxinfo.IPaxInfo;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.IPayment;
import com.amadeus.selenium.sqmobile.page.payment.customers.ELALPaymentPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;
/**
 * ELAL PaxInfo Page
 * @author vbalasubramanian
 *
 */
public class ELALPaxInfoPage extends PaxInfoPage implements IPaxInfo{

  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS = By.cssSelector(".pax>div>span>div>h2>.data");

  //Passengar Information
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_TITLE = By.id("TITLE_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME = By.id("FIRST_NAME_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME = By.id("LAST_NAME_1");
  protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_NUMBER = By.cssSelector("input[id^='PREF_AIR_FREQ_NUMBER_']");

  //Contact information
  protected static final By LOC_IN_CONTACT_MOBILE_PHONE = By.name("CONTACT_POINT_MOBILE_1");
  protected static final By LOC_IN_PAX_INFO_CONTACT_EMAIL = By.name("CONTACT_POINT_EMAIL_1");

  protected static final By LOC_IN_CONTACT_HOME_PHONE = By.id("CONTACT_POINT_HOME_PHONE");

  protected static final By LOC_IN_PAX_INFO_UNMR_TEXT = By.id("warn18text");
  protected static final By LOC_IN_PAX_INFO_UNMR_LINK = By.id("alpi_umnranc");
  protected static final By LOC_BT_PAX_INFO_SELECT_SEAT = By.className("navigation");
  protected static final By LOC_BT_PAX_INFO_CONTINUE_PAYMENT = By.className("validation");
  protected static final By LOC_BT_PAX_INFO_BACK = By.className("back");


  //Continue payment checkbox
  protected static final By LOC_CB_PAYMENT_CONDITION = By.id("CheckPenaliesBox");

  //Select payment type
  protected static final By LOC_IN_PAYMENT_TYPE_VISA = By.name("VISA_brand");
  protected static final By LOC_IN_PAYMENT_TYPE_EURO_CARD = By.name("Eurocard_brand");
  protected static final By LOC_IN_PAYMENT_TYPE_UATP_BRAND = By.name("UATP_brand");

  // Error message
  protected static final By LOC_IN_Error_Message = By.cssSelector(".msg.warning");

  public ELALPaxInfoPage(SeleniumSEPTest test) {
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME, 30)){
      reporter.fail("This is not ELALPaxInfo Page");
    }else{
      reporter.reportPassed("ELALPaxInfo Page", "In ELALPaxInfo Page");
    }
  }

 /**
   * Fill Contact Information
   * @param contacType
   * @param areaCode
   * @param contactNumber
   * @param email
   * @param countryCode
   * @param smsNotificationNumber
   * - History Created by : Vigneshwaran Balsubramanian
   */
  public  void fillContactInfo(String contacType, String areaCode, String contactNumber, String email,
      String countryCode) {

    waitForOverlayLoading(10);
    WebElement contactEmailElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_EMAIL);
    if (contactEmailElt!=null && contactEmailElt.isDisplayed()){
      contactEmailElt.clear();
      FillUtils.fillInputOrFail(getTest(), contactEmailElt, email, "Email not entered");
      reporter.reportPassed("Passengar Information page", "Email entered");
    }
    else{
      reporter.reportFailed("Passengar Information page", "Contact Email not displayed");
    }

    WebElement homePhoneElt = CheckUtils.getElement(getTest(), LOC_IN_CONTACT_HOME_PHONE);
    if (homePhoneElt != null && homePhoneElt.isDisplayed()) {
      FillUtils.fillInputOrFail(getTest(), LOC_IN_CONTACT_HOME_PHONE, contactNumber, "HomePhone number not entered");
    }

    WebElement mobileNumberElt = CheckUtils.getElement(getTest(), LOC_IN_CONTACT_MOBILE_PHONE);
    if (mobileNumberElt != null && mobileNumberElt.isDisplayed()) {
      FillUtils.fillInputOrFail(getTest(), LOC_IN_CONTACT_MOBILE_PHONE, contactNumber, "Mobile number not entered");
    }

  }

  /**
   * Fill passengar Information
   * - History : Created by Vigneshwaran Balsubramanian
   * @throws Exception
   */
  @Override
  public void fillPaxInfo() throws Exception {
    super.fillPaxInfo();
  }


  public IPayment continuePayment(String paymentType) throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BT_PAX_INFO_CONTINUE_PAYMENT, "Continue paymnet button not found");
    waitForOverlayLoading(120);
    reporter.reportPassed("Passengar Information page", "Continue to Payment link is clicked");

    WebElement paymentcondition = CheckUtils.getElement(getTest(), LOC_CB_PAYMENT_CONDITION);
    if (paymentcondition != null) {

      ClickUtils.click(getTest(), LOC_CB_PAYMENT_CONDITION);
      ClickUtils.clickButtonOrFail(getTest(), LOC_BT_PAX_INFO_CONTINUE_PAYMENT, "Continue payment button not found");
      waitForOverlayLoading(30);
      if (paymentType.contains("Visa")) {
        ClickUtils.click(getTest(), LOC_IN_PAYMENT_TYPE_VISA);
      }
      else if (paymentType.contains("Euro card")) {
        ClickUtils.click(getTest(), LOC_IN_PAYMENT_TYPE_EURO_CARD);
      }
      else {
        ClickUtils.click(getTest(), LOC_IN_PAYMENT_TYPE_UATP_BRAND);
      }
    }
    else {
      WebElement errormsg = CheckUtils.getElement(getTest(), LOC_IN_Error_Message);
      if (errormsg != null && errormsg.isDisplayed()) {
        reporter.fail(errormsg.getText().trim());
      }
  }
    waitForOverlayLoading(120);
    return PageFactory.getPageObject(ELALPaymentPage.class);
  }
  public void selectSeat() {

  }

}
