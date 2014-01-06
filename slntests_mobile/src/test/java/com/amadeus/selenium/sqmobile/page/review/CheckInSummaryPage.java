package com.amadeus.selenium.sqmobile.page.review;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class CheckInSummaryPage extends SqMobileCommonPage{

  //LOCATORS - CHECK IN PAGE--------------------------------------------------------
  protected static By LOC_TX_CHECKIN_BACK_BUTTON = By.cssSelector(".validation.cancel");
  protected static By LOC_TRIP_SECTIONS = By.className("checkin-list");
  protected static By LOC_FLIGHT_NUMBER = By.className("fare-family");

  protected static By LOC_BUTTON_SUBMIT = By.className("validation");
  protected static By LOC_BUTTON_CANCEL = By.className("cancel");
  protected static By LOC_TAB_SEAT = By.className("services-checked");
  protected static By LOC_BUTTON_SEAT = By.className("secondary");

  //----------------------------------------------------------------------------

  private String pageName = "CheckInSummary Page ";

  public CheckInSummaryPage(SeleniumSEPTest test) throws Exception {
    super(test);

    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_SUBMIT, 30);
    if (elementPresent) {
      reporter.reportPassed(pageName, "CheckinSummary Page loaded");
    }
    else {
      reporter.fail("CheckinSummary Page NOT loaded ");
    }
  }

  /**
   * Validates Checkin Summary Page
   * @author bsingh
   */
  public void validateCheckinSummary() {
    WebElement flightNumElt = CheckUtils.getElement(getTest(), LOC_FLIGHT_NUMBER);
    if(flightNumElt!=null && flightNumElt.isDisplayed()){
      reporter.reportPassed("Flight Number : ", flightNumElt.getText());
    }else{
      reporter.reportFailed(pageName, "Flight Number is not displayed ");
    }
    /*
     * WebElement seatElt = CheckUtils.getElement(getTest(), LOC_TAB_SEAT);
     * if(seatElt!=null && seatElt.isDisplayed()){
     * reporter.reportPassed(pageName, "Seat button is displayed");
     * }else{
     * reporter.reportFailed(pageName, "Seat button is not  dispalyed");
     * }
     */
  }


  /**
   * Clicks Seat Button
   * @author bsingh
   */
  public void clickSeat()  {
    WebElement seatElt = CheckUtils.getElement(getTest(), LOC_TAB_SEAT);
    WebElement seatButtonElt = CheckUtils.getElement(getTest(), seatElt, LOC_BUTTON_SEAT);
    if(seatButtonElt!=null){
      ClickUtils.clickButtonOrFail(getTest(), seatButtonElt, "Seat Button couldn't be clicked", "Seat Button clicked successfully");
    }
  }


  /**
   * Clicks Continue Button
   * @author bsingh
   */
  public void clickContinue() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SUBMIT, "Continue Button couldn't be clicked",
        "Continue Button clicked successfully");
    waitForOverlayLoading(120);
  }


  /**
   * Clicks Cancel Button
   * @author bsingh
   */
  public void clickCancel() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CANCEL, "Cancel Button couldn't be clicked", "Cancel Button clicked successfully");
  }


  public void clickManageCheckIn() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    for (WebElement button : buttonElts) {
      if (button.isDisplayed() && button.getText().toLowerCase().contains("manage")) {
        ClickUtils.clickButtonOrFail(getTest(), button, "Manage Check-In Button couldn't be clicked");
        reporter.reportPassed("CheckinSummaryPage : ", "Manage Checkin Button Clicked Successfully");
        waitForOverlayLoading(90);
        break;
      }
    }
  }

}
