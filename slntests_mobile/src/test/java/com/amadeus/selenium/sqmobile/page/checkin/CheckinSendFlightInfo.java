package com.amadeus.selenium.sqmobile.page.checkin;

import org.openqa.selenium.By;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CheckinSendFlightInfo extends SqMobileCommonPage{

  //LOCATORS - CHECK IN PAGE--------------------------------------------------------
  protected static By LOC_BUTTON_CHECKIN = By.className("validation");
  protected static By LOC_SELECT_BOOKING_REF = By.id("IdentificationType");
  protected static By LOC_TX_BOOKING_NUM = By.id("recLoc");
  protected static By LOC_TX_Last_Name = By.id("lastName");
  protected static By LOC_BUTTON_GUEST = By.cssSelector("#t2>a");
  protected static By LOC_BUTTON_KRISFLYER = By.cssSelector("#t1>a");
  protected static By LOC_BUTTON_KF_CONTINUE = By.cssSelector("#kfbutton>a");
  protected static By LOC_BUTTON_GUEST_CONTINUE = By.cssSelector("#guestbutton>a");
  protected static By LOC_BUTTON_Get_Trip = By.className("validation");


  private CheckInPaxSelection checkInPaxSelection;
  //----------------------------------------------------------------------------


  public CheckinSendFlightInfo(SeleniumSEPTest test) throws Exception{
   super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_Get_Trip, 30);
   if(elementPresent){
      reporter.reportPassed(getName(), "CheckinSendFlightInfo Page loaded");
   }else{
      reporter.fail("CheckinSendFlightInfo Page NOT loaded ");
   }

  }




  public enum LocatorType{
    BOOKINGREF , TICKET ;
  }

  /**
   * Fills Identification Information Details
   * @throws Exception
   * @author Sankar
   */
  public void fillIdentificationInfoForGuest() throws Exception {
    waitForOverlayLoading(90);
    fillIdentificationInfoForGuest(getValue("PNR"), "TEST");
  }

  /**
   * Fills Identification Information Details that is provided in the Excel Sheet
   * @throws Exception
   */
  public void fillIdentificationInfoForGuestFromExcel() throws Exception {
    waitForOverlayLoading(90);
    fillIdentificationInfoForGuest(getValue("GuestPNR1"), getValue("GuestLastName1"));
  }

  /**
   * Fills Identification Details
   * @param locatorType BOOKINGREF or TICKET
   * @param bookingNum Booking Reference Number or Ticket Number
   * @param lastName LastName
   * @throws Exception
   * @author bsingh
   */
  public void fillIdentificationInfoForGuest(String bookingNum, String lastName) throws Exception {
    // selectBookingLocator(locatorType);
    fillBookingNumberForGuest(bookingNum);
    fillLastNameForGuest(lastName);
  }



  public void selectBookingLocator(LocatorType locatorType) {
    String selectValue = "";
    if(locatorType.equals(LocatorType.BOOKINGREF)){
      selectValue = "bookingNumber";
    }else  if(locatorType.equals(LocatorType.TICKET)){
      selectValue = "eticketNumber";
    }
    FillUtils.selectByValue(getTest(), LOC_SELECT_BOOKING_REF, selectValue);
  }

  /**
   * Fills Booking Number
   * @param bookingNumber
   * @throws Exception
   * @author bsingh
   */
  public void fillBookingNumberForGuest(String bookingNumber) throws Exception {
     FillUtils.fillInputOrFail(getTest(), LOC_TX_BOOKING_NUM, bookingNumber, "Booking Number couldn't be fillled");
     addValue("PNR/TICKET", bookingNumber);
     reporter.reportPassed(getName(), "Booking Number filled successfully");
  }


  /**
   * Fills Last Name
   * @param lastName
   * @throws Exception
   * @author bsingh
   */
  public void fillLastNameForGuest(String lastName) {
    FillUtils.fillInputOrFail(getTest(), LOC_TX_Last_Name, lastName, "Last Name couldn't be fillled");
    reporter.reportPassed(getName(), "Last Name filled successfully");
  }


  /**
   * Clicks Guest Tab
   */
  public void clickGuestTab() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_GUEST, "Guest Tab couldn't be clicked",
        "Guest Tab Clicked successfully");
  }

  /**
   * Clicks KrisFlyer Tab
   */
  public void clickKrisFlyerTab() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_KRISFLYER, "KF Tab couldn't be clicked",
        "KF Tab Clicked successfully");
  }

  /**
   * Clicks Continue Button For GuestTab
   */
  public void clickContinueForGuest() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_Get_Trip, "GetTrip couldn't be clicked",
        "GetTrip Clicked successfully");
    waitForOverlayLoading(90);
  }

  /**
   * Clicks Continue Button For KFTab
   */
  public void clickContinueForKF() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_KF_CONTINUE, "GetTrip couldn't be clicked",
        "GetTrip Clicked successfully");
  }

  /**
   * To select all passengers in case CheckIn MultiPax Page is displayed And Click Continue
   * @author bsingh
   * @throws Exception
   */
  public void handleMultiPaxPage() throws Exception {
    if (Integer.parseInt(getValue("Nb Adult")) > 1) {
      CheckInPaxSelection checkInPaxSelection = getCheckInPaxSelectionObject();
      checkInPaxSelection.selectAllPax();
      checkInPaxSelection.clickContinue();
      waitForOverlayLoading(90);
    }
  }


    /**
     * To get the Single CheckInPaxSelectionObject
     * @return unique {@link CheckInPaxSelection}
     * @throws Exception
     * @author bsingh
     */
    public CheckInPaxSelection getCheckInPaxSelectionObject() throws Exception {
      if (checkInPaxSelection == null) {
        this.checkInPaxSelection = PageFactory.getPageObject(CheckInPaxSelection.class);
      }
      return this.checkInPaxSelection;
    }


}

