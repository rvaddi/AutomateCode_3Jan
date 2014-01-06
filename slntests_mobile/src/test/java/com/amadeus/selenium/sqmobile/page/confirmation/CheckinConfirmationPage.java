package com.amadeus.selenium.sqmobile.page.confirmation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CheckinConfirmationPage extends SqMobileCommonPage{

  // LOCATORS - CHECK IN PAGE--------------------------------------------------------
  protected static By LOC_BUTTON_SUBMIT = By.className("validation");
  protected static By LOC_FLIGHT_NUMBER = By.cssSelector(".data>strong");
  protected static By LOC_LABEL_REFERENCE_NUMBER = By.cssSelector(".sectionDefaultstyle>section>header>h3>strong");
  protected static By LOC_BUTTON_EMAIL = By.cssSelector(".secondary.email");
  protected static By LOC_BUTTON_SMS = By.cssSelector(".secondary.sms");

  protected static By LOC_SECTION_PASSENGER = By.id("TPsection0_2");
  protected static By LOC_CHECKBOX_PASSENGERS = By.cssSelector("[id^='customers']>span>ul>li>input");
  protected static By LOC_BUTTON_CANCEL_CHECKIN = By.className("cancelChkn");
  protected static By LOC_BUTTON_POPUP_OK = By.id("okButton");
  protected static By LOC_BUTTON_POPUP_CANCEL = By.id("cancelButton");
  protected static By LOC_TX_POPUP_EMAIL = By.cssSelector("[id^='eMailPax']");

  protected static By LOC_SERVICES_SECTION = By.className("services-checked");



  private String pageName = "CheckinConfirmation Page ";

  // ----------------------------------------------------------------------------

  public CheckinConfirmationPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_SUBMIT, 30);
    if (elementPresent) {
      reporter.reportPassed("PAGE LOADED : ", "CheckinOverview Page loaded");
    }
    else {
      reporter.fail("CheckinOverview Page NOT loaded ");
    }

  }


  public enum TripType {
    OW,RT ;
  }


  public void validateCheckInConfirmaitonPage() throws Exception {
    // validateReferenceNumber();
    /*
     * if (getValue("Trip Type").equalsIgnoreCase("RT")) {
     * validateFlights(TripType.RT);
     * }
     * else {
     * validateFlights(TripType.OW);
     * }
     */

    validateGetBoardingPassSection();
  }

  /**
   * Validates Reference Number
   * @throws Exception
   * @author bsingh
   */
  public void validateReferenceNumber() throws Exception {
    WebElement refNumElt = CheckUtils.getElement(getTest(), LOC_LABEL_REFERENCE_NUMBER);
    if(refNumElt!=null && refNumElt.isDisplayed() && refNumElt.getText().contains(getValue("PNR/TICKET"))){
      reporter.reportPassed(pageName, "Booking Reference Number is displayed properly");
    }else{
      reporter.reportFailed(pageName, "Booking Reference Number is not displayed properly");
    }
  }


  /**
   * Validates Flights
   * @param roundTrip
   */
  public void validateFlights(TripType tripType) {
    List<WebElement> flightElts = CheckUtils.getElements(getTest(), LOC_FLIGHT_NUMBER);
    if(!flightElts.isEmpty() && !flightElts.get(0).getText().equals("")){
      reporter.reportPassed(pageName, "Departure Flight is displayed");
    }else {
      reporter.reportFailed(pageName, "Departure Flight is not displayed properly");
    }
    if (tripType.equals(TripType.RT)) {
      if(!flightElts.isEmpty() && !flightElts.get(1).getText().equals("")){
        reporter.reportPassed(pageName, "Return Flight is displayed");
      }else {
        reporter.reportFailed(pageName, "Return Flight is not displayed properly");
      }
    }
  }



  /**
   * Validates BoardingPass-Section in Checkin Confirmation Page for various button availability
   * @author bsingh
   */
  public void validateGetBoardingPassSection() {
    WebElement emailElt = CheckUtils.getElement(getTest(), LOC_BUTTON_EMAIL);
    WebElement smsElt = CheckUtils.getElement(getTest(), LOC_BUTTON_SMS);
    if(emailElt!=null && emailElt.isDisplayed() && emailElt.getText().toUpperCase().contains("E-MAIL")) {
      reporter.reportPassed(pageName, "EMail Button is displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "EMail Button is not displayed properly");
    }
    if (smsElt != null && smsElt.getText().toUpperCase().contains("SMS")) {
      reporter.reportPassed(pageName, "SMS Button is displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "SMS Button is not displayed properly");
    }
  }


  /**
   * Cancels Checkin for all the passengers
   * @throws Exception
   * @author bsingh
   */
  public void cancelCheckinForAllPax() throws Exception {
    selectAllPax();
    clickCancelCheckIn();
    handleCancelCheckinPopup(true);
  }

  /**
   * Selects all the passenger available in the CheckinConfirmation Page
   * @author bsingh
   */
  public void selectAllPax() {
    WebElement passengerSectionElt = CheckUtils.getElement(getTest(), LOC_SECTION_PASSENGER);
    if (passengerSectionElt != null && passengerSectionElt.isDisplayed()) {
      List<WebElement> passengerElts = CheckUtils.getElements(getTest(), passengerSectionElt, LOC_CHECKBOX_PASSENGERS);
      for (WebElement chkBox : passengerElts) {
        if (chkBox.isDisplayed() && !chkBox.isSelected()) {
          ClickUtils.click(getTest(), chkBox);
        }
      }
    }
  }


  /**
   * To Receive Boarding Pass via Email Address
   * @param email
   * @author bsingh
   */
  public void receiveBoardingPassByMail(String email){
    clickEmailButton();
    fillEmailInPopup(email);
    clickSendBoardingPass();
  }

  /**
   * Fills Email Address
   * @param email
   * @author bsingh
   */
  public void fillEmailInPopup(String email) {
    WebElement emailElt = CheckUtils.getElement(getTest(), LOC_TX_POPUP_EMAIL);
    if (emailElt != null) {
      FillUtils.fillInputOrFail(getTest(), emailElt, email, "Email couldn't be filled successfully for BoardingPass");
      reporter.reportPassed(pageName, "Email for BoardingPass : " + email);
    }
    else {
      reporter.reportFailed(pageName, "EMail Textfield for Boarding Pass is not displayed properly");
    }
  }



  /**
   * Clicks Send-BoardingPass Button
   * @author bsingh
   */
  public void clickSendBoardingPass() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    for(WebElement button : buttonElts){
      if (button.isDisplayed() && button.getText().toLowerCase().contains("send boarding")) {
        ClickUtils.clickButtonOrFail(getTest(), button, "Send BoardingPass Button couldn't be clicked",
            "Send BoardingPass Button Clicked successfully");
        reporter.reportPassed(pageName, "Send BoardingPass Button clicked successfully");
        waitForOverlayLoading(90);
        break;
      }
    }
  }



  /**
   * Clicks Email Button in CheckinConfirmation Page
   * @author bsingh
   */
  public void clickEmailButton() {
    WebElement emailElt = CheckUtils.getElement(getTest(), LOC_BUTTON_EMAIL);
    if (emailElt != null && emailElt.isDisplayed() && emailElt.getText().toUpperCase().contains("E-MAIL")) {
      ClickUtils.clickButtonOrFail(getTest(), emailElt, "Email Button couldn't be clicked successfully",
          "EmailButton clickced successfully for sending BoardingPass");
      reporter.reportPassed(pageName, "EMail Button is displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "EMail Button is not displayed properly");
    }
  }



  public void clickSeatButton() {
    WebElement servicesSectionElt = CheckUtils.getElement(getTest(), LOC_SERVICES_SECTION);
    if (servicesSectionElt != null) {
      WebElement seatButtonElt = CheckUtils.getElement(getTest(), servicesSectionElt, By.className("secondary"));
      ClickUtils.clickButtonOrFail(getTest(), seatButtonElt, "Seat Button couldn't be clicked successfully",
          "Seat clickced successfully for sending BoardingPass");
      reporter.reportPassed(pageName, "Seat Button is displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "Seat Button is not displayed properly");
    }
    waitForOverlayLoading(90);
  }



  /**
   * Clicks CheckIn Button
   * @throws Exception
   */
  public void clickCheckIn() throws Exception {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    ClickUtils.clickButtonOrFail(getTest(), buttonElts.get(1), "CheckIn Button couldn't be clicked", "CheckIn Button Clicked successfully");
  }

  /**
   * Clicks CancelCheckIn Button
   * @throws Exception
   * @author bsingh
   */
  public void clickCancelCheckIn() throws Exception {
    WebElement buttonElt = CheckUtils.getElement(getTest(), LOC_BUTTON_CANCEL_CHECKIN);
    if(buttonElt!=null) {
      ClickUtils.clickButtonOrFail(getTest(), buttonElt, "Cancel-CheckIn Button couldn't be clicked","Cancel-CheckIn Button Clicked successfully");
    }
    else {
      reporter.reportFailed(pageName, "Cancel CheckIn Button is not displayed");
    }
  }


  /**
   * Handles Cancel Checkin Popup
   * @param accept - true or false
   * @author bsingh
   */
  public void handleCancelCheckinPopup(boolean accept) {
    if (accept) {
      WebElement okButtonElt = CheckUtils.getElement(getTest(), LOC_BUTTON_POPUP_OK);
      if (okButtonElt != null) {
        ClickUtils.clickButtonOrFail(getTest(), okButtonElt, "Cancel-Checkin couldn't be accepted");
      }
      else {
        reporter.reportFailed(pageName, "Cancel-Checkin Ok Button is not displayed");
      }
    }
    else {
      WebElement cancelButtonElt = CheckUtils.getElement(getTest(), LOC_BUTTON_POPUP_OK);
      if (cancelButtonElt != null) {
        ClickUtils.clickButtonOrFail(getTest(), cancelButtonElt, "Cancel-Checkin couldn't be aborted");
      }
      else {
        reporter.reportFailed(pageName, "Cancel-Checkin Abort Button is not displayed");
      }
    }
  }

  public void validateAfterBoardingPassGeneration() {
    WebElement emailElt = CheckUtils.getElement(getTest(), LOC_BUTTON_EMAIL);
    WebElement smsElt = CheckUtils.getElement(getTest(), LOC_BUTTON_SMS);

    if (emailElt != null && emailElt.isDisplayed() && emailElt.getText().toUpperCase().contains("E-MAIL")) {
      reporter.reportPassed(pageName, "EMail Button is displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "EMail Button is not displayed properly");
    }
    if (smsElt != null && smsElt.getText().toUpperCase().contains("SMS")) {
      reporter.reportPassed(pageName, "SMS Button is displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "SMS Button is not displayed properly");
    }

    // Validating if SeatButton is disabled
    WebElement servicesSectionElt = CheckUtils.getElement(getTest(), LOC_SERVICES_SECTION);
    if (servicesSectionElt != null) {
      WebElement seatButtonElt = CheckUtils.getElement(getTest(), servicesSectionElt, By.className("secondary"));
      if (seatButtonElt == null || !seatButtonElt.isEnabled() ||
          seatButtonElt.getAttribute("className").contains("disabled")) {
        reporter.reportPassed(pageName, "Seat Button is displayed properly");
      }else{
        reporter.reportFailed(pageName, "Seat Button is enabled after Boarding pass is generated");
      }
    }

    WebElement buttonElt = CheckUtils.getElement(getTest(), LOC_BUTTON_CANCEL_CHECKIN);
    if (!(buttonElt != null && buttonElt.isEnabled())) {
      reporter.reportFailed(pageName, "Cancel-Checkin Button is Enabled even after BoardingPass generation");
    }
    else {
      reporter.reportPassed(pageName, "Cancel-Checkin Button is not displayed after BoardingPass generation");
    }

  }

  /**
   * Clicks ExitCheckIn Button
   * @throws Exception
   * @author bsingh
   */
  public void clickExitCheckin() throws Exception {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    for(WebElement button : buttonElts){
      if (button.isDisplayed() && button.getText().toLowerCase().contains("exit")) {
        ClickUtils.clickButtonOrFail(getTest(), button, "ExitCheckin Button couldn't be clicked",
            "ExitCheckin Button Clicked successfully");
        waitForOverlayLoading(90);
        break;
      }
    }
  }


}
