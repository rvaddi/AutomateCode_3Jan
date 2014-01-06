package com.amadeus.selenium.sqmobile.page.payment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class ATCPaymentPage extends CommonPaymentPage{

  // Locators of ATCPayment Page -----------------------------------------------

  protected final static By LOC_BT_PAYMENT_CONFIRM_PURCHASE = By.id("btnConfirm");
  protected final static By LOC_IN_PAX_INFO_CONTACT_TYPE = By.id("PREFERRED_PHONE_NO");
  protected final static By LOC_IN_PAX_INFO_CONTACT_COUNTRY = By.id("COUNTRY");
  protected final static By LOC_TX_PAX_INFO_CONTACT_COUNTRY_AUTO_COMPLETE = By.id("ui-active-menuitem");
  protected final static By LOC_IN_PAX_INFO_CONTACT_CODE = By.id("AREA_CODE");
  protected final static By LOC_IN_PAX_INFO_CONTACT_NUMBER = By.id("PHONE_NUMBER");
  protected final static By LOC_IN_PAX_INFO_CONTACT_EMAIL = By.name("CONTACT_POINT_EMAIL_1");

  //----------------------------------------------------------------------------

  public ATCPaymentPage(SeleniumSEPTest test) throws Exception{
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE, 30)){
      reporter.fail("This is not ATCPayment Page");
    }else{
      reporter.reportPassed("ATCPayment Page", "In ATCPayment Page");
    }

  }

  private String pageName = "Payment Page";

  public enum ContactType {
    MOBILE,HOME,OFFICE ;
  }


  /**
   * To Fill Contact Information
   * @author bsingh
   */
  public void fillContactInfo(ContactType contactType ,String areaCode,String contactNumber , String emailId ,String countryCode) {
    fillPreferredContact(contactType);
    fillCountry(countryCode);
    fillPhoneNumber(contactNumber);
    fillEmailId(emailId);
  }

  /**
   * Selects Preferred Contact Type
   * @param contactType
   * @author bsingh
   */
  public void fillPreferredContact(ContactType contactType) {
    FillUtils.selectByValue(getTest(),   LOC_IN_PAX_INFO_CONTACT_TYPE , contactType.toString());
    reporter.reportPassed("Contact Type : ", contactType.toString());
  }


  /**
   * Fills country name
   * @param countryCode
   * @author bsingh
   */
  public void fillCountry(String countryCode) {
    WebElement contactCountryElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_COUNTRY);
    if (contactCountryElt!=null && contactCountryElt.isDisplayed()){
      contactCountryElt.clear();
      FillUtils.fillInputOrFail(getTest(), contactCountryElt, countryCode, "Country Code not entered");
      reporter.reportPassed("Country Code filled : ", countryCode);
      List<WebElement> autoComplete1 = CheckUtils.getElements(getTest(), By.className("ui-corner-all"));
      for(WebElement opt : autoComplete1){
        if(opt.isDisplayed()){
          opt.click();
        }
      }
    }
    else{
      reporter.reportFailed(pageName, "Contact Country not displayed");
    }
  }


  /**
   * Fills PhoneNumber
   * @param contactNumber
   * @author bsingh
   */
  public void fillPhoneNumber(String contactNumber)  {
    FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_CONTACT_NUMBER, contactNumber, "Contact Number couldn't be filled properly");
    reporter.reportPassed("Cotact Number filled : ", contactNumber);
  }


  /**
   * Fills EmailId
   * @param emailId
   * @author bsingh
   */
  public void fillEmailId (String emailId)  {
    FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_CONTACT_EMAIL, emailId, "EmailId couldn't filled properly");
    reporter.reportPassed("Email Id filled : ",emailId);

  }

}
