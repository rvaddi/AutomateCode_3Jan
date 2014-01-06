package com.amadeus.selenium.sqmobile.page.checkin;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class CheckInFlightSelection extends SqMobileCommonPage{

  public CheckInFlightSelection(SeleniumSEPTest test) throws Exception{
    super(test);
    /*
     * CheckUtils.checkPageTitle(getTest(), "MSQ Mobile - Send flight info");
     * reporter.report("CHECKPOINT", "Checkin Page is DISPLAYED");
     */
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_CANCEL, 40);
    if (elementPresent) {
      reporter.reportPassed(pageName, "CheckinFlightSelection Page loaded");
    }
    else {
      reporter.fail("CheckinFlightSelection Page NOT loaded ");
    }
  }

  //LOCATORS - CHECK IN PAGE--------------------------------------------------------
  protected static final By LOC_TX_CHECKIN_BACK_BUTTON = By.cssSelector(".validation.cancel");
  protected static final By LOC_TRIP_SECTIONS = By.className("checkin-list");
  protected static final By LOC_PANELS = By.cssSelector(".panel.list");
  protected static final By LOC_BUTTON_CANCEL = By.cssSelector("button.cancel");
  protected static final By LOC_CHKBX_FLIGHTS = By.id("flight00");
  protected static final By LOC_YES_NO_SWITHCH = By.className("onoffswitch-switch");
  protected static final By LOC_YES_SWITCH = By.className("onoffswitch-active");
  protected static final By LOC_NO_SWITCH = By.className("onoffswitch-inactive");
  protected static final By LOC_CHKBX_FLIGHTS_OPEN = By.id("[id^='flight0']");
  protected static final By LOC_TX_TERMS_AND_CONDITIONS = By.className("onoffswitch-general-label");
  protected static final By LOC_PANEL_PAX_INFO = By.cssSelector("[id^='passInfo']");
  protected static final By LOC_TX_PAX_INFO = By.cssSelector("li>input");
  protected static final By LOC_TX_COUNTRY_CODE = By.cssSelector("[id^='areaCode']");
  protected static final By LOC_TX_PHONE = By.cssSelector("[id^='phoneNumber']");
  protected static final By LOC_TX_EMAIL = By.cssSelector("[id^='emailDetails']");
  protected static final By LOC_TX_FF_NUMBER = By.cssSelector("[id^='fqtvinput']");
  protected static final By LOC_BUTTON_SUBMIT = By.className("validation");
  protected static final By LOC_TX_FF_INFO = By.cssSelector("[id^='freqFlyerInfo']");
  protected static final By LOC_TX_FF_CARRIER = By.cssSelector("li>select");
  protected static final By LOC_BUTTON_EDIT = By.className("edit");

  protected static final By LOC_CHKBOX_MULTI_PAX = By.cssSelector("[id^='cust']");
  protected static final By LOC_BUTTON_MULTIPAX_CONTINUE = By.id("multiPaxSelContinue");

  private String pageName = "CheckinFlightSelection";


  //----------------------------------------------------------------------------

  public enum Agree {
    YES , NO ;
  }

  public enum TripType{
    OW , RT ;
  }

  public enum Itinerary {
    DEPARTURE, RETURN;
  }

  /**
   * Validate Checkin Page
   * This also Handles the MuliPax page
   * @author Sankar
   * @throws Exception
   */
  public void ValidateCheckInFlightSelectionPage() throws Exception {
    waitForOverlayLoading(20);
    validateCheckInFlightSelectionPage(getValue("Trip Type"));
  }

  /**
   * Fill Check-In page
   *
   * @author Sankar
   * @throws IOException
   */
  public void selectFlightsForCheckin() throws Exception {
    selectaAllOpenFlights();
    clickAgreedSwitch(Agree.YES);
  }

  /**
   * Fill Check-In page
   * @author bsingh
   * @throws IOException
   */
  public void selectFlightsForCheckin(Itinerary itinerary) throws Exception {
    selectFlight(itinerary);
    clickAgreedSwitch(Agree.YES);
  }

  /**
   * Validate Checkin Page
   * 
   * @param tripType
   *          RT or OW
   * @author Sankar
   */
  public void validateCheckInFlightSelectionPage(String tripType) {

    int tripsectioncount = 0;
    List<WebElement> panelElts = CheckUtils.getElements(getTest(), LOC_PANELS);
    if (!panelElts.isEmpty() && panelElts.size() > 2) {

      for (WebElement openflights : panelElts) {
        WebElement OpenForCheckIn = CheckUtils.getElement(getTest(), openflights, By.tagName("header"));
        String Header = OpenForCheckIn.getText().trim();

        if (Header.toLowerCase().contains("open")) {

          List<WebElement> tripSectionElts = CheckUtils.getElements(getTest(), openflights, LOC_TRIP_SECTIONS);
          for (WebElement section : tripSectionElts) {
            tripsectioncount++;
            reporter.reportPassed(pageName,
                "FLIGHTS OPEN FOR CHECK-IN section displayed the Trip. (" + section.getText() + ")");
          }
        }
        else if (Header.toLowerCase().contains("pending")) {
          List<WebElement> tripSectionElts = CheckUtils.getElements(getTest(), openflights, LOC_TRIP_SECTIONS);
          for (WebElement section : tripSectionElts) {
            tripsectioncount++;
            reporter.reportPassed(pageName,
                "FLIGHTS PENDING CHECK-IN section displayed the Trip. (" + section.getText() + ")");
          }
        }
      }
      if (tripType.equalsIgnoreCase("OW")) {
        if (tripsectioncount == 1) {
          reporter.reportPassed(pageName, "All the sections of trip are displayed properly");
        }
        else {
          reporter.reportFailed(pageName, "Not all the sections of trip are displayed properly");
        }
      }
      else if (tripType.equalsIgnoreCase("RT")) {
        if (tripsectioncount >= 1) {
          reporter.reportPassed(pageName, "All the sections of trip are displayed properly");
        }
        else {
          reporter.reportFailed(pageName, "Not all the sections of trip are displayed properly");
        }
      }
      else {
        reporter.reportFailed(pageName, "Trip Type Input is not proper. (Trip Type: " + tripType + ")");
      }
    }
    else {
      reporter.reportFailed(pageName, "All the panels are not displayed properly");
    }

    WebElement termsElt = CheckUtils.getElement(getTest(), LOC_TX_TERMS_AND_CONDITIONS);
    if (termsElt != null && termsElt.getText().contains(" terms and conditions")) {
      reporter.reportPassed(pageName, "Dangerous Goods Terms and Conditions are displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "Dangerous Goods Terms and Conditions are not displayed properly");
    }
  }

  /**
   * Validate Checkin Page
   * @param tripType RT or OW
   * @author bsingh
   * @throws Exception
   */
  public void validateCheckinPage(String tripType) throws Exception {

    List<WebElement> panelElts = CheckUtils.getElements(getTest(), LOC_PANELS);
    if(!panelElts.isEmpty() && panelElts.size()>2){
      List<WebElement> tripSectionElts = CheckUtils.getElements(getTest(), panelElts.get(1), LOC_TRIP_SECTIONS);
      if(!tripSectionElts.isEmpty()){
        if(tripType.equals(TripType.RT)&& tripSectionElts.size()>1){
          reporter.reportFailed(pageName, "Not all the sections of trip are displayed properly");
        }
        if(tripType.equals(TripType.OW)&& tripSectionElts.size()>0){
          reporter.reportFailed(pageName, "Not all the sections of trip are displayed properly");
        }
      }
    }else{
      reporter.reportFailed(pageName, "All the panels are not displayed properly");
    }
    WebElement termsElt = CheckUtils.getElement(getTest() , LOC_TX_TERMS_AND_CONDITIONS);
    if(termsElt!=null && termsElt.getText().contains(" terms and conditions")){
      reporter.reportPassed(pageName, "Dangerous Goods Terms and Conditions are displayed properly");
    }else{
      reporter.reportFailed(pageName, "Dangerous Goods Terms and Conditions are not displayed properly");
    }
  }


  /**
   * Clicks the Agree - conditional switch button to YES or NO
   * @param agree YES or NO
   * @author bsingh
   */
  public void clickAgreedSwitch(Agree agree){
    WebElement yesElt = CheckUtils.getElement(getTest(), LOC_YES_SWITCH);
    WebElement noElt = CheckUtils.getElement(getTest(), LOC_NO_SWITCH);
    if (agree.equals(Agree.YES)) {
      if (yesElt != null && yesElt.isDisplayed() && yesElt.isEnabled()) {
      }
      ClickUtils.clickButtonOrFail(getTest(), LOC_YES_NO_SWITHCH, "Agree Switch Yes Cannot be clicked",
          "Agree Switch Yes clicked successfully");
    }
    else {
      if (noElt != null && noElt.isDisplayed() && noElt.isEnabled()) {
      }
      ClickUtils.clickButtonOrFail(getTest(), LOC_YES_NO_SWITHCH, "Agree Switch Yes Cannot be clicked",
          "Agree Switch Yes clicked successfully");
    }
  }


  /**
   * Selects Flights for the given Itinerary
   * @param numberOfFlights
   * @author bsingh
   * @throws Exception
   */
  public void selectFlight(Itinerary itinerary) throws Exception{
    List<WebElement> selectBoxElts = CheckUtils.getElements(getTest(), LOC_CHKBX_FLIGHTS_OPEN);
    if(selectBoxElts!=null && selectBoxElts.size()>0){
      FillUtils.fillCheckboxOrFail(getTest(), selectBoxElts.get(0), true, "Passengers for DEPUARTURE Itinerary coudln't be selected" ) ;
      if(itinerary.equals(Itinerary.RETURN) && selectBoxElts.size()>1){
        FillUtils.fillCheckboxOrFail(getTest(), selectBoxElts.get(1), true, "Passengers for RETURN Itinerary coudln't be selected" ) ;
      }
    }
  }

  /**
   * Selects Flights for the given number of passengers
   *
   * @param numberOfFlights
   */
  public void selectaAllOpenFlights() throws Exception {
    List<WebElement> selectBoxElts = CheckUtils.getElements(getTest(), LOC_CHKBX_FLIGHTS_OPEN);
    for (int i = 0; i < selectBoxElts.size(); i++) {
      FillUtils.fillCheckboxOrFail(getTest(), selectBoxElts.get(i), true, "Flight Couldn't be selected for passenger :  " + i);
    }
  }

  /**
   * Updates Passengers FirstName
   * @param paxFirstName
   */
  public void updatePaxFirstName(String paxFirstName ){
    WebElement paxInfoElt = CheckUtils.getElement(getTest(), LOC_PANEL_PAX_INFO);
    List<WebElement> textFields = CheckUtils.getElements(getTest(),paxInfoElt , LOC_TX_PAX_INFO);
    if(!textFields.isEmpty() && textFields.size()>0){
      textFields.get(0).clear();
      FillUtils.fillInputOrFail(getTest(), textFields.get(0), paxFirstName, "Passenger's First Name couldn't be updated");
      reporter.reportPassed("FirstName : ", paxFirstName);
    }else {
      reporter.reportFailed(pageName, "First Name Text Field didn't display");
    }
  }


  /**
   * Updates Passengers LastName
   * @param lastName
   */
  public void updatePaxLastName(String lastName ){
    WebElement paxInfoElt = CheckUtils.getElement(getTest(), LOC_PANEL_PAX_INFO);
    List<WebElement> textFields = CheckUtils.getElements(getTest(),paxInfoElt , LOC_TX_PAX_INFO);
    if(!textFields.isEmpty() && textFields.size()>1){
      textFields.get(1).clear();
      FillUtils.fillInputOrFail(getTest(), textFields.get(1), lastName, "Passenger's Last Name couldn't be updated");
      reporter.reportPassed("LastName : ", lastName);
    }else {
      reporter.reportFailed(pageName, "Last Name Text Field didn't display");
    }
  }


  /**
   * Updates Country Code
   * @param countryCode
   * @author bsingh
   */
  public void updateCountryCode(String countryCode){
    WebElement countryCodeElt = CheckUtils.getElement(getTest(), LOC_TX_COUNTRY_CODE);
    if(countryCodeElt!=null && countryCodeElt.isDisplayed()){
      countryCodeElt.clear();
      FillUtils.fillInputOrFail(getTest(),  countryCodeElt , countryCode , "CountryCode couldn't be updated");
    }
  }



  /**
   * Updates Phone Number
   * @param phoneNumber
   * @author bsingh
   */
  public void updatePhoneNumber(String phoneNumber){
    WebElement phoneElt = CheckUtils.getElement(getTest(), LOC_TX_PHONE);
    if(phoneElt!=null && phoneElt.isDisplayed()){
      phoneElt.clear();
      FillUtils.fillInputOrFail(getTest(),  phoneElt , phoneNumber , "PhoneNumber couldn't be updated");
    }
  }


  /**
   * Updates Email Address
   * @param emailAddress
   * @author bsingh
   */
  public void updateEmailAddress(String emailAddress){
    WebElement emailElt = CheckUtils.getElement(getTest(), LOC_TX_EMAIL);
    if(emailElt!=null && emailElt.isDisplayed()){
      emailElt.clear();
      FillUtils.fillInputOrFail(getTest(), emailElt , emailAddress , "EmailAddress couldn't be updated");
    }
  }


  /**
   * Updates Frequent Flyer Number
   * @param frequentFlyerNumber

   * @author bsingh
   */
  public void updateFFNumber(String frequentFlyerNumber){
    WebElement ffElt = CheckUtils.getElement(getTest(), LOC_TX_FF_NUMBER);
    if(ffElt!=null && ffElt.isDisplayed()){
      ffElt.clear();
      FillUtils.fillInputOrFail(getTest(), ffElt , frequentFlyerNumber , "EmailAddress couldn't be updated");
    }
  }


  /**
   * Selects Carrier Airline
   * @param carrierNames
   * @author bsingh
   */
  public void selectCarrierAirline(String carrierAirlineName){
    WebElement ffInfoElt = CheckUtils.getElement(getTest(), LOC_TX_FF_INFO);
    WebElement carrierElt = CheckUtils.getElement(getTest(), ffInfoElt, LOC_TX_FF_CARRIER);
    if(carrierElt!=null && carrierElt.isDisplayed()){
      carrierElt.clear();
      FillUtils.fillSelectByValueOrFail(getTest(), carrierElt , carrierAirlineName , "CarrierAirline couldn't be updated");
    }
  }


  public void clickEditForNthPax(int forNthPax){
    List<WebElement> editElts = CheckUtils.getElements(getTest(), LOC_BUTTON_EDIT);
    if(!editElts.isEmpty() && editElts.size()>= forNthPax)  {
      ClickUtils.clickButtonOrFail(getTest(), editElts.get(forNthPax-1), "Edit Button Couldn't be clicked", "Edit Button clicked successfully for "+forNthPax + " Passenge");
    }else{
      reporter.reportFailed(pageName,"Edit Button couldn't be clicked properly");
    }
  }

  /**
   * Clicks Continue Button
   * @author bsingh
   */
  public void clickContinue()throws Exception {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    boolean clicked = false;
    for (WebElement button : buttonElts) {
      if (button.isDisplayed() && button.getText().toLowerCase().contains("continue")) {
        ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SUBMIT, "Continue Button clicked couldn't be clicked");
        clicked = true;
        waitForOverlayLoading(90);
        break;
      }
    }
    if (clicked) {
      reporter.reportPassed(pageName, "Continue  button clicked successfully");
    }
    else {
      reporter.reportFailed(pageName, "Continue button couldn't be clicked successfully");
    }
  }


  /**
   * Clicks Cancel Button
   * @author bsingh
   */
  public void clickCancel(){
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    boolean clicked = false;
    for (WebElement button : buttonElts) {
      if (button.isDisplayed() && button.getText().toLowerCase().contains("cancel")) {
        ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SUBMIT, "Cancel Button clicked couldn't be clicked");
        clicked = true;
        waitForOverlayLoading(90);
        break;
      }
    }
    if (clicked) {
      reporter.reportPassed(pageName, "Cancel  button clicked successfully");
    }
    else {
      reporter.reportFailed(pageName, "Cancel button couldn't be clicked successfully");
    }
  }



}
