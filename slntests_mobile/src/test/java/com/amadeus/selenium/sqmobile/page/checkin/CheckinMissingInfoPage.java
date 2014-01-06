package com.amadeus.selenium.sqmobile.page.checkin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CheckinMissingInfoPage extends SqMobileCommonPage{

  public CheckinMissingInfoPage(SeleniumSEPTest test) throws Exception{
    super(test);

    reporter.report("CHECKPOINT", "CheckInMissingInfo Page is DISPLAYED");

    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_CHECKIN, 30);
    if (elementPresent) {
      reporter.reportPassed(getName(), "CheckInMissingInfo Page loaded");
    }
    else {
      reporter.fail("CheckInMissingInfo Page NOT loaded ");
    }

  }

  //LOCATORS - CHECK IN PAGE--------------------------------------------------------
  protected static By LOC_BUTTON_CHECKIN = By.className("validation");
  protected static By LOC_TX_PASSPORT_NUMBER = By.cssSelector("[id^='pnumber']");
  protected static By LOC_CALENDAR_DATE= By.cssSelector(".ui-state-default");
  protected static By LOC_CALENDAR_MONTH= By.className("ui-datepicker-month");
  protected static By LOC_CALENDAR_YEAR= By.className("ui-datepicker-year");
  protected static By LOC_PASSPORT_ISSUING_AUTHORITY = By.cssSelector("[id^='psp_Country_issue']");
  // protected static By LOC_RADIO_GENDER= By.cssSelector("#genderId>span>input");
  protected static By LOC_RADIO_GENDER = By.cssSelector(".input-radio>li");
  protected static By LOC_Nationality = By.id("nationality_code_0_0");
  protected static By LOC_Button_OK = By.cssSelector("button.nationalityTrigger");
  protected static By LOC_First_Name = By.id("FirstNameField_0");
  protected static By LOC_Last_Name = By.id("LastNameField_0");
  protected static By LOC_CONTACT_INFO_SECTION = By.cssSelector("[id^='contactInfo']");
  protected static By LOC_Place_Of_Birth = By.id("pob_0_0");
  protected static By LOC_SELECT_CALENDAR_DATE = By.cssSelector(".ui-datepicker-trigger");
  protected static By LOC_PASSPORT_INFO_SECTION = By.cssSelector("[id^='passportInfo']");
  protected static By LOC_Passport_Expiry_Date = By.cssSelector("#14733_0_0 button");
  protected static By LOC_Passport_Issuing_Authority = By.id("psp_Country_issue_0_0");
  protected static By LOC_DOCUMENT_TYPE = By.cssSelector("select.inputField");
  protected static By LOC_OTHER_DOCUMENT_SECTION = By.cssSelector("#OTHER_DRD_0");
  protected static By LOC_DOCUMENT_NUMBER = By.id("onumber_0_0");
  protected static By LOC_BUTTON_CONTINUE = By.id("continueButton");
  protected static By LOC_BUTTON_CANCEL = By.cssSelector("button.cancel");
  //----------------------------------------------------------------------------


  public enum Gender {
    MALE , FEMALE;
  }

  /**
   * Fill CheckIn Missing Information Page
   *
   * @author Sankar
   * @throws Exception
   */
  public void fillCheckInMissingInfoPage() throws Exception {

    fillNationality("India");
    clickOkButton();
    waitForOverlayLoading(30);
    fillCustomerInformation(Gender.MALE, "ADT", "TEST", -7000, "BANGALORE");
    fillPassportInformation("SD234T", 2000, "INDIA");
    fillOtherDocuments("Identity Card", 1500, "123AS321");

  }


  /**
   * Validates the Message displayed if No Nationality is entered in the Nationality TextBox.
   * @throws Exception
   */
  public void validateNationality() throws Exception {
    fillNationality("");
    clickOkButton();
    validateMessage("41001");
  }

  /**
   * Fill Nationality
   * @param String nationality
   *
   * @author Sankar
   */
  public void fillNationality(String nationality) {

    WebElement Nationality = CheckUtils.getElement(getTest(), LOC_Nationality);
    if (Nationality != null && Nationality.isDisplayed()) {
      Nationality.clear();
      FillUtils.fillInputOrFail(getTest(), Nationality, nationality, "Nationality  couldn't be filled");
      reporter.reportPassed("Nationality is filled : ", nationality);
    }else {
      reporter.reportFailed("Nationality", "Nationality field is not displayed");
    }


  }

  /**
   * Fill Customer Information Section
   *@param gender Gender to be selected
   *@param firstname First Name to be entered
   *@param lastname Last Name to be entered
   *@param dob Date of Birth to be entered
   *@param placeofbirth Place Of Birth to be entered
   * @author Sankar
   * @throws Exception
   */
  public void fillCustomerInformation(Gender gender, String firstname, String lastname, int dob, String placeofbirth)
      throws Exception {

    selectGender(gender);

    /*
     * WebElement FirstName = CheckUtils.getElement(getTest(), LOC_First_Name);
     * if (FirstName != null && FirstName.isDisplayed()) {
     * FirstName.clear();
     * FillUtils.fillInputOrFail(getTest(), FirstName, firstname, "First Name couldn't be filled");
     * reporter.reportPassed("First Name is filled : ", firstname);
     * }else {
     * reporter.reportFailed("Customer Info", "First Name field is not displayed");
     * }
     *
     * WebElement LastName = CheckUtils.getElement(getTest(), LOC_Last_Name);
     * if (LastName != null && LastName.isDisplayed()) {
     * LastName.clear();
     * FillUtils.fillInputOrFail(getTest(), LastName, lastname, "Last Name couldn't be filled");
     * reporter.reportPassed("Last Name is filled : ", lastname);
     * }else {
     * reporter.reportFailed("Customer Info", "Last Name field is not displayed");
     * }
     */

    fillDateOfBirth(dob);

    WebElement PlaceofBirth = CheckUtils.getElement(getTest(), LOC_Place_Of_Birth);
    if (PlaceofBirth != null && PlaceofBirth.isDisplayed()) {
      PlaceofBirth.clear();
      FillUtils.fillInputOrFail(getTest(), PlaceofBirth, placeofbirth, "Place of Birth couldn't be filled");
      reporter.reportPassed("Place of Birth is filled : ", placeofbirth);
    }
  }

  /**
   * Fill Passport Information Section
   *
   *@param passportno Passport Number to be entered
   *@param expirydate Passport expiry date
   *@param issuingauthority Issuing authority of the passport
   * @author Sankar
   * @throws Exception
   */
  public void fillPassportInformation(String passportno, int expirydate, String issuingauthority) throws Exception {
    fillPassportNumber(passportno);
    fillPassportExpiryDate(expirydate);
    fillPassportIssuingAuthority(issuingauthority);
  }

  /**
   * @param passportNumber
   * @author bsingh
   */
  public void fillPassportNumber(String passportNumber) {
    WebElement passportNoElt = CheckUtils.getElement(getTest(), LOC_TX_PASSPORT_NUMBER);
    if (passportNoElt != null && passportNoElt.isDisplayed()) {
      passportNoElt.clear();
      FillUtils.fillInputOrFail(getTest(), passportNoElt, passportNumber, "Passport Number couldn't be filled");
      reporter.reportPassed("Passport Number is filled : ", passportNumber);
    }
    else {
      reporter.reportFailed("Passport Info", "Passport Number field is not displayed");
    }
  }

  /**
   * Fills Passport Issuing Authority
   * @param authorityName
   * @author bsingh
   */
  public void fillPassportIssuingAuthority(String authorityName) {
    FillUtils.fillInputOrFail(getTest(), LOC_PASSPORT_ISSUING_AUTHORITY, authorityName,
        "Passport issuing authority couldn't be filled");
    reporter.reportPassed(getName(), "Passport issuing authority filled successfully");
  }


  /**
   * Fill Other Documents Section
   *
   * @param documenttype Document type to be selected from the dropdown
   * @param expirydate Document expiry date
   * @param documentnumber Document number to be entered
   * @author Sankar
   * @throws Exception
   */
  public void fillOtherDocuments(String documenttype, int expirydate, String documentnumber) throws Exception {

    WebElement DocumentType = CheckUtils.getElement(getTest(), LOC_DOCUMENT_TYPE);
    if (DocumentType != null && DocumentType.isDisplayed()) {
      // DocumentType.clear();
      // FillUtils.fillInputOrFail(getTest(), DocumentType, documenttype, "Document Type couldn't be filled");
      FillUtils.selectByValue(getTest(), DocumentType, "I");
      reporter.reportPassed("Document Type is filled : ", documenttype);
    }
    else {
      reporter.reportFailed("Other Documents", "Document Type field is not displayed");
    }

    fillDocumentExpiryDate(expirydate);

    WebElement DocumentNo = CheckUtils.getElement(getTest(), LOC_DOCUMENT_NUMBER);
    if (DocumentNo != null && DocumentNo.isDisplayed()) {
      DocumentNo.clear();
      FillUtils.fillInputOrFail(getTest(), DocumentNo, documentnumber, "Document Number couldn't be filled");
      reporter.reportPassed("Document Number is filled : ", documentnumber);
    }
    else {
      reporter.reportFailed("Other Documents", "Document Number field is not displayed");
    }
  }


  /**
   * Fills Date Of Birth
   *
   * @param dobGap
   * @throws Exception
   * @author Sankar
   */
  public void fillDateOfBirth(int dobGap) throws Exception {
    String date = null;
    WebElement contactInfoSectionElt = CheckUtils.getElement(getTest(), LOC_CONTACT_INFO_SECTION);

    if (!(contactInfoSectionElt == null)) {
      WebElement calendarElt = CheckUtils.getElement(getTest(), contactInfoSectionElt, LOC_SELECT_CALENDAR_DATE);
      if (calendarElt != null) {
        WaitUtils.wait(2);
        // ClickUtils.click(getTest(), calendarElt);
        ClickUtils.clickButtonOrFail(getTest(), calendarElt, "Calendar Icon couldn't be clicked");
        CommonUtils utils = new CommonUtils(getTest());
        date = utils.fillDateUsingCalendar(dobGap, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
        reporter.reportPassed("DOB", date);
      }
    }
    else {
      reporter.reportFailed(getName(), "Date of Birth couldn't be filled successfully using calendar ");
    }
  }

  /**
   * Fills passport Expiry Date
   *
   * @param expirygap
   * @throws Exception
   * @author Sankar
   */
  public void fillPassportExpiryDate(int expirygap) throws Exception {

    String date = null;
    WebElement passportSectionElt = CheckUtils.getElement(getTest(), LOC_PASSPORT_INFO_SECTION);
    WebElement calendarElt = CheckUtils.getElement(getTest(), passportSectionElt, LOC_SELECT_CALENDAR_DATE);

    if (!(calendarElt == null)) {
      ClickUtils.clickButtonOrFail(getTest(), calendarElt, "Passport Calenadar Icon couldn't be clicked");
      CommonUtils utils = new CommonUtils(getTest());
      date = utils.fillDateUsingCalendar(expirygap, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
      reporter.reportPassed(getName(), "Passport Expiry Date filled successfully using calendar. (Date: " + date + ")");
    }
    else {
      reporter.reportFailed(getName(), "Passport Expiry Date couldn't be filled successfully using calendar ");
    }
  }

  /**
   * Fills passport Expiry Date
   *
   * @param expirygap
   * @throws Exception
   * @author Sankar
   */
  public void fillDocumentExpiryDate(int expirygap) throws Exception {

    String date = null;
    WebElement otherDocElt = CheckUtils.getElement(getTest(), LOC_OTHER_DOCUMENT_SECTION);

    if (!(otherDocElt == null)) {
      WebElement docTypeElt = CheckUtils.getElement(getTest(), otherDocElt, LOC_SELECT_CALENDAR_DATE);
      if (docTypeElt != null) {
        ClickUtils.clickButtonOrFail(getTest(), docTypeElt, "DocType Calendar Icon couldn't be clicked",
            "DocType Calendar Icon Clicked successfully");
        CommonUtils utils = new CommonUtils(getTest());
        date = utils.fillDateUsingCalendar(expirygap, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
        reporter.reportPassed(getName(), "Document Expiry Date filled successfully using calendar. (Date: " + date +
            ")");
      }
      else {
        reporter.reportFailed(getName(), "Document Expiry Date couldn't be filled successfully using calendar ");
      }
    }
    else {
      reporter.reportFailed(getName(), "Document Expiry Date couldn't be filled successfully using calendar ");
    }
  }


  /**
   * Selectes Gender
   * @param gender MALE / FEMALE
   * @author bsingh
   */
  public void selectGender(Gender gender)  {
    List<WebElement> genderElts = CheckUtils.getElements(getTest(), LOC_RADIO_GENDER);
    if(gender.equals(Gender.MALE)){
      for(WebElement gndr : genderElts){
        if(gndr.getText().contains("Male")){
          WebElement maleRadioElt = CheckUtils.getElement(getTest(), gndr, By.id("li1"));
          ClickUtils.clickButtonOrFail(getTest(), maleRadioElt, "Gender - Male couldn't be selected",
              "Gender selected sucessfully");
          break;
        }
      }
    }else if(gender.equals(Gender.FEMALE)){
      for (WebElement gndr : genderElts) {
        if (gndr.getText().contains("Female")) {
          WebElement femaleRadioElt = CheckUtils.getElement(getTest(), gndr, By.id("li2"));
          ClickUtils.clickButtonOrFail(getTest(), femaleRadioElt, "Gender - Female couldn't be selected",
              "Gender selected sucessfully");
          break;
        }
      }
    }
  }

  /**
   * Clicks Continue Button
   *
   * @author Sankar
   */
  public void clickOkButton() throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_Button_OK, "OK Button couldn't be clicked",
        "OK Button clicked successfully");
  }

  /**
   * Clicks Continue Button
   *
   * @author Sankar
   */
  public void clickContinueButton() throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CONTINUE, "Continue Button couldn't be clicked",
        "Continue Button clicked successfully");
    waitForOverlayLoading(90);
  }

  /**
   * Clicks Cancel Button
   *
   * @author Sankar
   */
  public void clickCancelButton() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CANCEL, "Cancel Button couldn't be clicked",
        "Cancel Button clicked successfully");
  }

}
