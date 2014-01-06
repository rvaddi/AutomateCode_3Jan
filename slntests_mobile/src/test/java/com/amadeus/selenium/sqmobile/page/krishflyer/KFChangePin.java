package com.amadeus.selenium.sqmobile.page.krishflyer;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class KFChangePin extends CommonKFServicesPage {


  public KFChangePin(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_CHANGE_PIN, 30);
    if(elementPresent){
      reporter.reportPassed("KFServices", "KFServices Page loaded");
    }else{
      reporter.fail("KFServices Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "SQ Mobile- KF Services");
    reporter.report("CHECKPOINT", "KFServices PAGE is DISPLAYED");
  }

  private String pageName = "KFChangePin Page";

  //LOCATORS - KFChangePin------------------------------------------------------

  protected static By LOC_BUTTON_KFSERVICES= By.className("more");
  protected static By LOC_BUTTON_CHANGE_PIN= By.className("buttonDirection");
  protected static By LOC_TX_OLD_PIN= By.id("OLD_PIN");
  protected static By LOC_TX_NEW_PIN= By.id("NEW_PIN");
  protected static By LOC_TX_CONFIRM_PIN= By.id("CNFM_PIN");
  //----------------------------------------------------------------------------



  /**
   * Clicks ChangePIN Button
   * @author bsingh
   */
  public void clickChangePIN() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_CHANGE_PIN);
    boolean buttonClicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("Change PIN")){
        ClickUtils.clickButtonOrFail(getTest(), button, "Change PIN Button couldn't be clicked " , "Change PIN Button clicked successfully");
        buttonClicked = true ;
      }
    }if(!buttonClicked){
      reporter.reportFailed(pageName,"Change PIN Button couldn't be clicked");
    }
  }


  /**
   * Fills PIN in OldPIN field
   * @param oldPIN current PIN
   * @author bsingh
   */
  public void fillOldPIN(String oldPIN) {
    FillUtils.fillInputOrFail(getTest(), LOC_TX_OLD_PIN, oldPIN, "Old PIN couldn't be filled properly");
    reporter.reportPassed("Old PIN", "Old PIN filled successfully");
  }


  /**
   * Fills New PIN
   * @param newPIN
   * @author bsingh
   */
  public void fillNewPIN(String newPIN) throws Exception {
    FillUtils.fillInputOrFail(getTest(), LOC_TX_NEW_PIN, newPIN, "New PIN couldn't be filled properly");
    reporter.reportPassed("New PIN : ", newPIN);
  }


  /**
   * Fills Confirm PIN
   * @param confirmPIN
   */
  public void fillConfirmPIN(String confirmPIN) throws Exception {
    FillUtils.fillInputOrFail(getTest(), LOC_TX_CONFIRM_PIN, confirmPIN, "Confirm PIN couldn't be filled properly");
    reporter.reportPassed("Confirm PIN : ",confirmPIN );
  }

}

