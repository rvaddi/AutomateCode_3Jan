package com.amadeus.selenium.sqmobile.page.paxinfo;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class ATCPaxInfoAPIS extends SqMobileCommonPage{

  // Locators of ATCPaxInfoAPIS Page -----------------------------------------------

  protected final static By LOC_BT_PAX_INFO_APIS_SUBMIT = By.className("validation");
  protected final static By LOC_IN_PAX_INFO_APIS_CONTACT_TYPE = By.id("PREFERRED_PHONE_NO");
  protected final static By LOC_IN_PAX_INFO_APIS_CONTACT_COUNTRY = By.id("COUNTRY");
  protected final static By LOC_TX_PAX_INFO_APIS_CONTACT_COUNTRY_AUTO_COMPLETE = By.id("ui-active-menuitem");
  protected final static By LOC_IN_PAX_INFO_APIS_CONTACT_CODE = By.id("AREA_CODE");
  protected final static By LOC_IN_PAX_INFO_APIS_CONTACT_NUMBER = By.id("PHONE_NUMBER");
  protected final static By LOC_IN_PAX_INFO_APIS_CONTACT_EMAIL = By.name("CONTACT_POINT_EMAIL_1");
  protected final static By LOC_IN_PAX_INFO_APIS_PAX_TYPE = By.className("type");
  protected final static By LOC_IN_PAX_INFO_APIS_PAX_TITLE = By.className("title");
  protected final static By LOC_IN_PAX_INFO_APIS_PAX_NAME = By.className("first-name");
  protected final static By LOC_IN_PAX_INFO_APIS_PAX_FAMILY_NAME = By.className("last-name");
  protected final static By LOC_IN_PAX_INFO_APIS_PAX_DATA = By.className("data");

  //----------------------------------------------------------------------------

  public ATCPaxInfoAPIS(SeleniumSEPTest test) throws Exception{
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_BT_PAX_INFO_APIS_SUBMIT, 30)){
      reporter.fail("This is not ATCPaxInfoAPIS Page");
    }else{
      reporter.reportPassed("ATCPaxInfoAPIS Page", "In ATCPaxInfoAPIS Page");
    }

  }

  private String pageName = "ATCPaxInfoAPISS Page";

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
    FillUtils.selectByValue(getTest(),   LOC_IN_PAX_INFO_APIS_CONTACT_TYPE , contactType.toString());
    reporter.reportPassed("Contact Type : ", contactType.toString());
  }


  /**
   * Fills country name
   * @param countryCode
   * @author bsingh
   */
  public void fillCountry(String countryCode) {
    WebElement contactCountryElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_APIS_CONTACT_COUNTRY);
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
    FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_APIS_CONTACT_NUMBER, contactNumber, "Contact Number couldn't be filled properly");
    reporter.reportPassed("Cotact Number filled : ", contactNumber);
  }


  /**
   * Fills EmailId
   * @param emailId
   * @author bsingh
   */
  public void fillEmailId (String emailId)  {
    FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_APIS_CONTACT_EMAIL, emailId, "EmailId couldn't filled properly");
    reporter.reportPassed("Email Id filled : ",emailId);

  }

  /**
   * Validates Pax Type - Adult or Child
   * @throws Exception
   * @author bsingh
   */
  public void validatePaxTypes() throws Exception{
    List<WebElement> typeElts = CheckUtils.getElements(getTest(), LOC_IN_PAX_INFO_APIS_PAX_TYPE);
    if(typeElts!=null && !typeElts.isEmpty()){
      for(WebElement typeElt : typeElts){
    WebElement typeData = CheckUtils.getElement(getTest(), typeElt, LOC_IN_PAX_INFO_APIS_PAX_DATA);
    if(typeData!=null && (typeData.getText().contains("Adult")|| typeData.getText().contains("Child"))){
      reporter.reportPassed("Pax Type : ", typeData.getText());
    }else{
      reporter.reportFailed(pageName, "Pax Type not displayed properly");
    }
      }
    }else{
      reporter.reportFailed(pageName, "Pax Type Element not displayed");
    }
  }

  /**
   * Validates Pax Title
   * @throws Exception
   * @author bsingh
   */
  public void validatePaxTitles() throws Exception{
    List<WebElement> titleElts = CheckUtils.getElements(getTest(), LOC_IN_PAX_INFO_APIS_PAX_TITLE);
    if(titleElts!=null && !titleElts.isEmpty()){
      for(WebElement typeElt : titleElts){
    WebElement titleDataElt = CheckUtils.getElement(getTest(), typeElt, LOC_IN_PAX_INFO_APIS_PAX_DATA);
    if(titleDataElt!=null && !titleDataElt.getText().equals("")){
      reporter.reportPassed("Pax Title : ", titleDataElt.getText());
    }else{
      reporter.reportFailed(pageName, "Pax Title not displayed properly");
    }
      }
    }else{
      reporter.reportFailed(pageName, "Pax Title Element not displayed");
    }
  }

  /**
   * Validates Pax FirstName
   * @throws Exception
   * @author bsingh
   */
  public void validatePaxFirstNames() throws Exception{
    List<WebElement> firstNameElt = CheckUtils.getElements(getTest(), LOC_IN_PAX_INFO_APIS_PAX_NAME);
    if(firstNameElt!=null && !firstNameElt.isEmpty()){
      for(WebElement typeElt : firstNameElt){
    WebElement nameDataElt = CheckUtils.getElement(getTest(), typeElt, LOC_IN_PAX_INFO_APIS_PAX_DATA);
    if(nameDataElt!=null && !nameDataElt.getText().equals("")){
      reporter.reportPassed("Pax First Name : ", nameDataElt.getText());
    }else{
      reporter.reportFailed(pageName, "Pax First Name not displayed properly");
    }
      }
    }else{
      reporter.reportFailed(pageName, "Pax First Name Element not displayed");
    }
  }

  /**
   * Validates Pax Last Name
   * @throws Exception
   * @author bsingh
   */
  public void validatePaxLastNames() throws Exception{
    List<WebElement> lastNameElt = CheckUtils.getElements(getTest(), LOC_IN_PAX_INFO_APIS_PAX_FAMILY_NAME);
    if(lastNameElt!=null && !lastNameElt.isEmpty()){
      for(WebElement typeElt : lastNameElt){
    WebElement nameDataElt = CheckUtils.getElement(getTest(), typeElt, LOC_IN_PAX_INFO_APIS_PAX_DATA);
    if(nameDataElt!=null && !nameDataElt.getText().equals("")){
      reporter.reportPassed("Pax Last Name : ", nameDataElt.getText());
    }else{
      reporter.reportFailed(pageName, "Pax Last Name not displayed properly");
    }
      }
    }else{
      reporter.reportFailed(pageName, "Pax Last Name Element not displayed");
    }
  }

}
