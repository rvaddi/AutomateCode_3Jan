package com.amadeus.selenium.sqmobile.page.seat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CheckInSeatMapPage extends CommonSeatMapPage{


  //LOCATORS - CHECKINSEATMAP PAGE------------------------------------------------
  protected static By LOC_SUBMIT_BUTTON = By.className("validation");

  private String pageName = "CheckInSeatMap Page";
  //------------------------------------------------------------------------------

  public CheckInSeatMapPage(SeleniumSEPTest test) throws Exception{
    super(test , "CheckInSeatMap Page");
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_SUBMIT_BUTTON, 30)){
      reporter.fail("This is not CheckInSeatMap Page");
    }else{
      reporter.reportPassed("CheckInSeatMap Page", "In CheckInSeatMap Page");
    }
  }



  /**
   * Selects Seat For All the available passengers
   * @throws Exception
   * @author bsingh
   */
  public void selectSeatForAllPax() throws Exception {
    super.bookSeatsForAllPax();
  }

  /**
   * Clicks Proceed to Payment Button
   * 
   * @author bsingh
   */
  public void clickProceed() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_SUBMIT_BUTTON);
    boolean clicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("Proceed")){
        ClickUtils.clickButtonOrFail(getTest(), LOC_SUBMIT_BUTTON, "Proceed Button clicked couldn't be clicked");
        clicked = true;
        waitForOverlayLoading(90);
        break;
      }
    }
    if(clicked ){
      reporter.reportPassed("CheckIn SeatMap : ", "Proceed button clicked successfully");
    }else{
      reporter.reportFailed("CheckIn SeatMap : ", "Proceed button couldn't be clicked successfully");
    }
  }


  /**
   * Clicks Revert Button
   * @author bsingh
   */
  public void clickRevert() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_SUBMIT_BUTTON);
    boolean clicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("Revert")){
        ClickUtils.clickButtonOrFail(getTest(), LOC_SUBMIT_BUTTON, "Revert Button clicked couldn't be clicked");
        clicked = true;
        break;
      }
    }
    if(clicked ){
      reporter.reportPassed(pageName, "Revert button clicked successfully");
    }else{
      reporter.reportFailed(pageName, "Revert button couldn't be clicked successfully");
    }
  }




  /**
   * Clicks Cancel Button
   * @author bsingh
   */
  public void clickCancel() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_SUBMIT_BUTTON);
    boolean clicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("Cancel")){
        ClickUtils.clickButtonOrFail(getTest(), LOC_SUBMIT_BUTTON, "Cancel Button clicked couldn't be clicked");
        clicked = true;
        break;
      }
    }
    if(clicked ){
      reporter.reportPassed(pageName, "Cancel button clicked successfully");
    }else{
      reporter.reportFailed(pageName, "Cancel button couldn't be clicked successfully");
    }
  }

}
