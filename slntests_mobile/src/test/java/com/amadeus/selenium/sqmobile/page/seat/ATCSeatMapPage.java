package com.amadeus.selenium.sqmobile.page.seat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class ATCSeatMapPage extends CommonSeatMapPage{

  //LOCATORS - ATCSEATMAP PAGE----------------------------------------------------

  protected final static By LOC_SUBMIT_BUTTON = By.className("validation");

  //-----------------------------------------------------------------------------

  public ATCSeatMapPage(SeleniumSEPTest test) throws Exception{
    super(test , "ATCSeatMap Page");
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_SUBMIT_BUTTON, 30)){
      reporter.fail("This is not ATCSeatMap Page");
    }else{
      reporter.reportPassed("ATCSeatMap Page", "In ATCSeatMap Page");
    }
  }

  private String pageName = "ATCSeatMap Page";



  /**
   * Clicks Proceed to Payment Button
   * @author bsingh
   */
  public void clickProceed() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_SUBMIT_BUTTON);
    boolean clicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("Proceed")){
        ClickUtils.clickButtonOrFail(getTest(), LOC_SUBMIT_BUTTON, "Proceed Button clicked couldn't be clicked");
        clicked = true;
      }
    }
    if(clicked ){
      reporter.reportPassed(pageName, "Proceed button clicked successfully");
    }else{
      reporter.reportFailed(pageName, "Proceed button couldn't be clicked successfully");
    }
  }


  /**
   * Clicks Proceed to Payment Button
   * @author bsingh
   */
  public void clickRevert() throws Exception{
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_SUBMIT_BUTTON);
    boolean clicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("Revert")){
        ClickUtils.clickButtonOrFail(getTest(), LOC_SUBMIT_BUTTON, "Revert Button clicked couldn't be clicked");
        clicked = true;
      }
    }
    if(clicked ){
      reporter.reportPassed("ATCSeat Map : ", "Revert button clicked successfully");
    }else{
      reporter.reportFailed("ATCSeat Map : ", "Revert button couldn't be clicked successfully");
    }
  }


}
