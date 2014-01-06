package com.amadeus.selenium.sqmobile.page.paxinfo.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.paxinfo.IPaxInfo;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * TAMPaxInfo Page
 * @author vbalasubramanian
 *
 */
public class TAMPaxInfoPage extends PaxInfoPage implements IPaxInfo{

  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_TITLE = By.id("TITLE_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME = By.id("FIRST_NAME_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME = By.id("LAST_NAME_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_DOB = By.cssSelector("select[id*='paxDobYear_']");
  protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_PROGRAM = By.cssSelector("input[id^='PREF_AIR_FREQ_AIRLINE_']");
  protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_NUMBER = By.cssSelector("input[id^='PREF_AIR_FREQ_NUMBER_']");
  protected static final By LOC_IN_PAX_INFO_MOBILE_NUMBER = By.name("CONTACT_POINT_MOBILE_1");
  protected static final By LOC_IN_PAX_INFO_CONTACT_EMAIL = By.name("CONTACT_POINT_EMAIL_1");
  protected static final By LOC_IN_PAX_INFO_SMS_NOTIFICATION = By.name("NOTIF_VALUE_1_1");

  public TAMPaxInfoPage(SeleniumSEPTest test) {
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME, 30)){
      reporter.fail("This is not TAMPaxInfo Page");
    }else{
      reporter.reportPassed("TAMPaxInfo Page", "In TAMPaxInfo Page");
    }
  }

  /**
   * Fill ContactInfo
   */
  @Override
  public void fillContactInfo(String contacType, String areaCode, String contactNumber, String email,
      String countryCode, String smsNotificationNumber) {

/*    WebElement contactNumberElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_MOBILE_NUMBER);
    if (contactNumberElt!=null && contactNumberElt.isDisplayed()){
      contactNumberElt.clear();
      FillUtils.fillInputOrFail(getTest(), contactNumberElt, contactNumber, "Contact Number not entered");
    }
    else{
      reporter.reportFailed("Passengar Information page", "Contact Number not displayed");
    }

    WebElement contactEmailElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_EMAIL);
    if (contactEmailElt!=null && contactEmailElt.isDisplayed()){
      contactEmailElt.clear();

      FillUtils.fillInputOrFail(getTest(), contactEmailElt, email, "Email not entered");
    }
    else{
      reporter.reportFailed("Passengar Information page", "Contact Email not displayed");
    }

    WebElement smsNotificationElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_SMS_NOTIFICATION);
    if(smsNotificationElt!=null && smsNotificationElt.isDisplayed()){
      FillUtils.fillInputOrFail(getTest(), smsNotificationElt, smsNotificationNumber , "SMS notification number not entered");

    }*/

    super.fillContactInfo(contacType, areaCode, contactNumber, email, countryCode, smsNotificationNumber);

  }

  /**
   * Fill PaxInfo
   * - History : Created by Vigneshwaran Balsubramanian
   */
  @Override
  public void fillPaxInfo() throws Exception {
    super.fillPaxInfo();
  }
}
