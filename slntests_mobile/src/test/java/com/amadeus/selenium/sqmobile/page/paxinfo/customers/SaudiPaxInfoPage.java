package com.amadeus.selenium.sqmobile.page.paxinfo.customers;

import org.openqa.selenium.By;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Saudi PaxInfo page
 * @author vbalasubramanian
 *
 */
public class SaudiPaxInfoPage extends PaxInfoPage{

  public SaudiPaxInfoPage(SeleniumSEPTest test) {
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME, 30)){
      reporter.fail("This is not SaudiPaxInfo Page");
    }else{
      reporter.reportPassed("SaudiPaxInfo Page", "In SaudiPaxInfo Page");
    }
  }

  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS = By.cssSelector(".pax>div>span>div>h2>.data");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_TITLE = By.id("TITLE_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME = By.id("FIRST_NAME_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME = By.id("LAST_NAME_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_DOB = By.cssSelector("select[id*='paxDobYear_']");
  protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_PROGRAM = By.cssSelector("input[id^='PREF_AIR_FREQ_AIRLINE_']");
  protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_NUMBER = By.cssSelector("input[id^='PREF_AIR_FREQ_NUMBER_']");
  protected static final By LOC_IN_PAX_INFO_CONTACT_TYPE = By.id("PREFERRED_PHONE_NO");
  protected static final By LOC_IN_PAX_INFO_CONTACT_COUNTRY = By.id("COUNTRY");
  protected static final By LOC_TX_PAX_INFO_CONTACT_COUNTRY_AUTO_COMPLETE = By.id("ui-active-menuitem");
  protected static final By LOC_IN_PAX_INFO_CONTACT_CODE = By.id("AREA_CODE");
  protected static final By LOC_IN_PAX_INFO_CONTACT_NUMBER = By.id("PHONE_NUMBER");
  protected static final By LOC_IN_PAX_INFO_CONTACT_EMAIL = By.name("CONTACT_POINT_EMAIL_1");
  protected static final By LOC_IN_PAX_INFO_UNMR_TEXT = By.id("warn18text");
  protected static final By LOC_IN_PAX_INFO_UNMR_LINK = By.id("alpi_umnranc");
  protected static final By LOC_BT_PAX_INFO_SELECT_SEAT = By.className("navigation");
  protected static final By LOC_BT_PAX_INFO_CONTINUE_PAYMENT = By.className("validation");
  protected static final By LOC_BT_PAX_INFO_BACK = By.className("back");
  protected static final By LOC_IN_PAX_INFO_SMS_NOTIFICATION = By.name("NOTIF_VALUE_1_1");


 /**
  * Fill Passengar Information
  */
  public void fillPaxInfo() throws Exception {
   super.fillPaxInfo();
  }

  /**
   * Fill Contact Information
   */
  @Override
  public void fillContactInfo(String contacType, String areaCode, String contactNumber, String email,
      String countryCode, String smsNotificationNumber) {
    // TODO Auto-generated method stub
    super.fillContactInfo(contacType, areaCode, contactNumber, email, countryCode, smsNotificationNumber);
  }

  /**
   * Select seat
   */
  public void selectSeat() {

  }

  /**
   * Validate PRice details popup
   */
  public void validatePriceDetailsPopUp(String title) {

  }
}
