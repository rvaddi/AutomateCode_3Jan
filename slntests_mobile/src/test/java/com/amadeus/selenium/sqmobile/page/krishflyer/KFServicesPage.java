package com.amadeus.selenium.sqmobile.page.krishflyer;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class KFServicesPage extends CommonKFServicesPage {


  public KFServicesPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_KFSERVICES, 30);
    if(elementPresent){
      reporter.reportPassed(getName(), "KFServices Page loaded");
    }else{
      reporter.fail("KFServices Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "SQ Mobile- KF Services");
    reporter.report("CHECKPOINT", "KFServices PAGE is DISPLAYED");
  }

  private String pageName = "KFServices Page";

  //LOCATORS - KFServices-------------------------------------------------------

  protected static By LOC_BUTTON_KFSERVICES= By.className("more");

  //----------------------------------------------------------------------------


  /**
   * Clicks MyProfile Button
   * @author bsingh
   */
  public void clickMyProfile() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_KFSERVICES);
    boolean buttonClicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("My profile")){
        ClickUtils.clickButtonOrFail(getTest(), button, "MyProfile Button couldn't be clicked " , "MyProfile Button clicked successfully");
        buttonClicked = true ;
      }
    }if(!buttonClicked){
      reporter.reportFailed(pageName,"MyProfile Button couldn't be clicked");
    }
  }


  /**
   * Clicks MyMiles Button
   * @author bsingh
   */
  public void clickMyMiles() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_KFSERVICES);
    boolean buttonClicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("My miles")){
        ClickUtils.clickButtonOrFail(getTest(), button, "MyMiles Button couldn't be clicked " , "MyMiles Button clicked successfully");
        buttonClicked = true ;
      }
    }if(!buttonClicked){
      reporter.reportFailed(pageName ,"MyMiles Button couldn't be clicked");
    }

  }


  /**
   * Clicks MyTransactions Button
   * @author bsingh
   */
  public void clickMyTransactions() {

    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_KFSERVICES);
    boolean buttonClicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("My transactions")){
        ClickUtils.clickButtonOrFail(getTest(), button, "MyTransactions Button couldn't be clicked " , "MyTransactions Button clicked successfully");
        buttonClicked = true ;
      }
    }if(!buttonClicked){
      reporter.reportFailed(pageName,"MyTransactions Button couldn't be clicked");
    }

  }


}

