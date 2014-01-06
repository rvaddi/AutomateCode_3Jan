package com.amadeus.selenium.sqmobile.page.paxinfo.customers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Airchina PaxInfo page
 * @author vbalasubramanian
 *
 */
public class AirchinaPaxInfoPage extends PaxInfoPage{

/*  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS = By.cssSelector(".pax>div>span>div>h2>.data");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_TITLE = By.id("TITLE_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME = By.id("FIRST_NAME_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME = By.id("LAST_NAME_1");
  protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_DOB = By.cssSelector("select[id*='paxDobYear_']");
  protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_PROGRAM = By.cssSelector("input[id^='PREF_AIR_FREQ_AIRLINE_']");
  protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_NUMBER = By.cssSelector("input[id^='PREF_AIR_FREQ_NUMBER_']");
  protected static final By LOC_IN_PAX_INFO_CONTACT_TYPE = By.id("PREFERRED_PHONE_NO");
  protected static final By LOC_IN_PAX_INFO_GENDER = By.id("psptgender1");
  protected static final By LOC_IN_PAX_INFO_DOB_DAY = By.cssSelector("[id*='Day_IDENTITY_DOCUMENT_DATE_OF_BIRTH_1']");
  protected static final By LOC_IN_PAX_INFO_DOB_MONTH = By.cssSelector("[id*='Month_IDENTITY_DOCUMENT_DATE_OF_BIRTH_1']");
  protected static final By LOC_IN_PAX_INFO_DOB_YEAR = By.cssSelector("[id*='Year_IDENTITY_DOCUMENT_EXPIRY_DATE_1']");
  protected static final By LOC_IN_PAX_INFO_NATIONALITY = By.cssSelector("[id*='IDENTITY_DOCUMENT_NATIONALITY_1']");
  protected static final By LOC_IN_PAX_INFO_DOCUMENT_TYPE =  By.cssSelector("[id*='IDENTITY_DOCUMENT_TYPE_1']");
  protected static final By LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_DAY = By.cssSelector("[id*='Day_IDENTITY_DOCUMENT_EXPIRY_DATE_1_PSPT_BK_GLOBAL_DEFAULT_1']");
  protected static final By LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_MONTH = By.cssSelector("[id*='Month_IDENTITY_DOCUMENT_EXPIRY_DATE_1']");
  protected static final By LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_YEAR = By.cssSelector("[id*='Year_IDENTITY_DOCUMENT_EXPIRY_DATE_1']");
  protected static final By LOC_IN_PAX_INFO_ISSUING_COUNTRY_CODE = By.cssSelector("[id*='IDENTITY_DOCUMENT_ISSUING_COUNTRY_1']");
  protected static final By LOC_IN_PAX_INFO_DESTIMATION_COUNTRY = By.cssSelector("[id*='APIS_ADDRESS_COUNTRY_1_BK_ELEMENT_0_1']");


  protected static final By LOC_IN_PAX_INFO_UNMR_TEXT = By.id("warn18text");
  protected static final By LOC_IN_PAX_INFO_UNMR_LINK = By.id("alpi_umnranc");
  protected static final By LOC_BT_PAX_INFO_SELECT_SEAT = By.className("navigation");
  protected static final By LOC_BT_PAX_INFO_CONTINUE_PAYMENT = By.className("validation");
  protected static final By LOC_BT_PAX_INFO_BACK = By.className("back");*/
  protected static final By LOC_IN_PAX_INFO_MOBILE_NUMBER = By.name("CONTACT_POINT_HOME_PHONE");

  public AirchinaPaxInfoPage(SeleniumSEPTest test) {
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_IN_PAX_INFO_CONTACT_EMAIL, 30)){
      reporter.fail("This is not AirChinaPaxInfo Page");
    }else{
      reporter.reportPassed("AirChinaPaxInfo Page", "In AirChinaPaxInfo Page");
    }
  }

/*  *//**
   * Fill passengar Information
   * - History : Created by Vigneshwaran Balsubramanian
   * @throws Exception
   *//*
  public void fillPaxInfo() throws Exception {
    WebElement paxSections = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS);
    String[] adultTitle = new String[] {"Mr", "Mrs"};
    String[] childTitle = new String[] {"Master", "Miss"};
    String gender = "Male";
    String country ="China";
    String adultFirstName = "ADT";
    String childFirstName = "CHD";
    String adultlastName = "TEST";
    WebElement titleName = null ;
    WebElement firstName = null;;
    WebElement lastName = null;;
    CommonUtils utils =  PageFactory.getPageObject(CommonUtils.class);

    int i = 0;

    if (paxSections!=null && paxSections.getText().contains("Adult")){
      titleName = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_TITLE);
      if(titleName!=null && titleName.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_TITLE, adultTitle[i], "Adult Title not entered");
        reporter.reportPassed("Passengar Information page", "Child title entered");
      }

      firstName = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME);
      if(firstName!=null && firstName.isDisplayed()&&firstName.getText().equals("")){
        FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME, adultFirstName, "First Name Field not entered");
        reporter.reportPassed("Passengar Information page", "First name field entered");
      }

      lastName = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME);
      if(lastName!=null && lastName.isDisplayed()&&lastName.getText().equals("")){
        FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME, adultlastName, "Last Name Field not entered");
        reporter.reportPassed("Passengar Information page", "last name entered");
      }

      WebElement genderElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_GENDER);
      if(genderElt!=null && genderElt.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_GENDER, gender, "Gender not selected");
        reporter.report("Passengar Information page", "Gender entered");
      }

      WebElement dayofBirth = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_DOB_DAY);
      if(dayofBirth!=null && dayofBirth.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_DOB_DAY, "01", "DOB Day not selected");
        reporter.report("Passengar Information page", "DOB Day selected");
      }
      WebElement monthOfBirth = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_DOB_MONTH);
      if(monthOfBirth!=null && monthOfBirth.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_DOB_MONTH, "Jan", "DOB month not selected");
        reporter.report("Passengar Information page", "DOB Month selected");
      }
      WebElement yearOFBirth = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_DOB_YEAR);
      if(yearOFBirth!=null && yearOFBirth.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_DOB_YEAR, "1980", "DOB year not selected");
        reporter.report("Passengar Information page", "DOB year selected");
      }

      WebElement nationalityElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_NATIONALITY);
      if(nationalityElt!=null && nationalityElt.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_NATIONALITY, country, "Nationality not selected");
        reporter.report("Passengar Information page", "Nationality selected");
      }

      WebElement documentType = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_DOCUMENT_TYPE);
      if(documentType!=null && documentType.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_DOCUMENT_TYPE, "Passport", "Nationality not selected");
        reporter.report("Passengar Information page", "Document type selected");
      }

      String Dep = utils.addDate("dd MMM yyyy",  new Integer (getValue("Card Expiry Days")));
      String expiryDay =(Dep.split(" "))[0];
      String expiryMonth = (Dep.split(" "))[1];
      String expiryYear = (Dep.split(" "))[2];

      WebElement passwordExpiryDay = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_DAY);
      if(passwordExpiryDay!=null && passwordExpiryDay.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_DAY,expiryDay,"document expiry day not selected");
        reporter.report("Passengar Information page", "document expiry day selected");
      }

      WebElement passwordExpiryMonth = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_MONTH);
      if(passwordExpiryMonth!=null && passwordExpiryMonth.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_MONTH,expiryMonth,"document expiry month not selected");
        reporter.report("Passengar Information page", "document expiry month selected");
      }
      WebElement passwordExpiryYear = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_YEAR);
      if(passwordExpiryYear!=null && passwordExpiryYear.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_DOCUMENT_EXPIRY_YEAR,expiryYear,"document expiry year not selected");
        reporter.report("Passengar Information page", "document expiry year selected");
      }

      WebElement travelDocumentIssuingCountry = CheckUtils.getElement(getTest(),LOC_IN_PAX_INFO_ISSUING_COUNTRY_CODE);
      if(travelDocumentIssuingCountry!=null && travelDocumentIssuingCountry.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_ISSUING_COUNTRY_CODE,country,"issuing country code not selected");
        reporter.report("Passengar Information page", "issuing country code not selected");
      }

      WebElement destinationCountry = CheckUtils.getElement(getTest(),LOC_IN_PAX_INFO_DESTIMATION_COUNTRY);
      if(destinationCountry!=null && destinationCountry.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_DESTIMATION_COUNTRY,country,"issuing country code not selected");
        reporter.report("Passengar Information page", "destination country not selected");
      }

    }
    else if ( paxSections!=null && paxSections.getText().contains("Child")){

      titleName = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_TITLE);
      if(titleName!=null && titleName.isDisplayed()){
        FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_TITLE, childTitle[i], "Child Title not entered");
        reporter.reportPassed("Passengar Information page", "child title entered");
        WaitUtils.wait(3);
      }

      WebElement childFirstNameElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME);
      if(childFirstNameElt!=null && childFirstNameElt.isDisplayed()){
        FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME, childFirstName, "Child First Name not entered");
        reporter.reportPassed("Passengar Information page", "child first name entered");
      }

      WebElement childLastNameElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME);
      if(childLastNameElt!=null && childLastNameElt.isDisplayed()){
        FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME, childFirstName, "Child Last Name not entered");
        reporter.reportPassed("Passengar Information page", "child last name entered");
      }

      WebElement genderElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_GENDER);
      if(genderElt!=null && genderElt.isDisplayed()){
        FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_PAX_INFO_GENDER, gender, "Gender not selected");
        reporter.report("Passengar Information page", "Gender entered");
      }
    }
  }

  *//**
   * Fill Contatct Info
   */
/*@Override
public void fillContactInfo(String contacType, String areaCode, String contactNumber, String email,
    String countryCode, String smsNotificationNumber) {
  super.fillContactInfo(contacType, areaCode, contactNumber, email, countryCode, smsNotificationNumber);
}*/


/**
 * To Fill Contact Information
 * History : Created by Vigneshwaran Balasubramanian
 */
public void fillContactInfo(String contacType ,String areaCode,String contactNumber , String email,String countryCode,String smsNotificationNumber) {

  WebElement contactType = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_TYPE);
  if (contactType!=null && contactType.isDisplayed()){
    Select ContactType = new Select (contactType);
    ContactType.selectByVisibleText(contacType);
  }

  WebElement contactCountry = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_COUNTRY);
  if (contactCountry!=null && contactCountry.isDisplayed()){
    contactCountry.clear();
    FillUtils.fillInputOrFail(getTest(), contactCountry, countryCode, "Country Code not entered");//contactCountry.sendKeys("Sin");
    WaitUtils.wait(3);
    List<WebElement> autoComplete1 = CheckUtils.getElements(getTest(), By.className("ui-corner-all"));
    for(WebElement opt : autoComplete1){
      if(opt.isDisplayed()){
        opt.click();
      }
    }
  }



  WebElement mobileNumber = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_MOBILE_NUMBER);
  if (mobileNumber!=null && mobileNumber.isDisplayed()){
    FillUtils.fillInputOrFail(getTest(), mobileNumber , contactNumber , "MobileNumber not entered");
  }



  WebElement areadCodeElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_CODE);
  if (areadCodeElt!=null && areadCodeElt.isDisplayed()){
    areadCodeElt.clear();
    FillUtils.fillInputOrFail(getTest(), areadCodeElt,areaCode, "Area Code not entered");
  }

  WebElement contactNumberElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_NUMBER);
  if (contactNumberElt!=null && contactNumberElt.isDisplayed()){
    contactNumberElt.clear();
    FillUtils.fillInputOrFail(getTest(), contactNumberElt, contactNumber, "Contact Number not entered");
  }

  WebElement contactEmailElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_EMAIL);
  if (contactEmailElt!=null && contactEmailElt.isDisplayed()){
    contactEmailElt.clear();

    FillUtils.fillInputOrFail(getTest(), contactEmailElt, email, "Email not entered");
  }

  WebElement smsNotificationElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_SMS_NOTIFICATION);
  if(smsNotificationElt!=null && smsNotificationElt.isDisplayed()){
    FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_SMS_NOTIFICATION, smsNotificationNumber, "SMS notification number not entered");
  }
}


}
