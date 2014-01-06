package com.amadeus.selenium.sqmobile.page.checkin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CheckinInfoPage extends SqMobileCommonPage{

  public CheckinInfoPage(SeleniumSEPTest test) throws Exception{
   super(test);
   boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_CHECKIN, 30);
   if(elementPresent){
     reporter.reportPassed("PAGE LOADED : ", "CheckinOverview Page loaded");
   }else{
     reporter.fail("CheckinOverview Page NOT loaded ");
   }

  }

  //LOCATORS - CHECK IN PAGE--------------------------------------------------------
  protected static By LOC_BUTTON_CHECKIN = By.className("validation");
  protected static By LOC_FLIGHT_NUMBER= By.cssSelector(".data>strong");
  protected static By LOC_LABEL_REFERENCE_NUMBER =  By.cssSelector(".sectionDefaultstyle>section>header>h3>strong");

  private String pageName = "CheckinInfo Page ";
  //----------------------------------------------------------------------------

  public enum TripType {
    OW,RT ;
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
  public void validateFlights(boolean roundTrip)  {
    List<WebElement> flightElts = CheckUtils.getElements(getTest(), LOC_FLIGHT_NUMBER);
    if(!flightElts.isEmpty() && !flightElts.get(0).getText().equals("")){
      reporter.reportPassed(pageName, "Departure Flight is displayed");
    }else {
      reporter.reportFailed(pageName, "Departure Flight is not displayed properly");
    }
    if(roundTrip){
      if(!flightElts.isEmpty() && !flightElts.get(1).getText().equals("")){
        reporter.reportPassed(pageName, "Return Flight is displayed");
      }else {
        reporter.reportFailed(pageName, "Return Flight is not displayed properly");
      }
    }
  }


  /**
   * Clicks CheckIn Button
   * @throws Exception
   */
  public void clickCheckIn() throws Exception {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_CHECKIN);
    ClickUtils.clickButtonOrFail(getTest(), buttonElts.get(1), "CheckIn Button couldn't be clicked", "CheckIn Button Clicked successfully");
  }


  /**
   * Clicks Cancel Button
   * @throws Exception
   */
  public void clickCancel() throws Exception {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_CHECKIN);

    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("cancel")){
        ClickUtils.clickButtonOrFail(getTest(), buttonElts.get(2), "Cancel Button couldn't be clicked", "Cancel Button Clicked successfully");
      }
    }
  }

}
