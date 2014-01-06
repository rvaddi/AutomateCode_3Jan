package com.amadeus.selenium.sqmobile.page.krishflyer;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CommonKFServicesPage extends SqMobileCommonPage {

  public CommonKFServicesPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_LOGOUT, 30);
    if(elementPresent){
      reporter.reportPassed(getName(), "KFServices Page loaded");
    }else{
      reporter.fail("KFServices Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "SQ Mobile- KF Services");
    reporter.report("CHECKPOINT", "KFServices PAGE is DISPLAYED");
  }

  //LOCATORS - KFServices-------------------------------------------------------

  protected static By LOC_BUTTON_LOGOUT= By.className("kf_logout");
  protected static By LOC_BUTTON_EXPAND = By.className("kf_open");
  protected static By LOC_BUTTON_HIDE = By.className("kf_close");
  protected static By LOC_KF_DETAILS = By.cssSelector(".tierHolder>dl>dt");

  //----------------------------------------------------------------------------


  /**
   * Clicks Expand Icon
   * @throws Exception
   */
  public void expandKFDetails() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_EXPAND, "Expand Icon couldn't be clicked" , "Expand Icon clicked successfully" );
  }


  /**
   * Clicks HideIcon
   * @author bsingh
   */
  public void hideKFDetails() throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_HIDE, "Expand Icon couldn't be clicked" , "Expand Icon clicked successfully" );
  }


  /**
   * Validates KFDetails for the existence of various details
   * @author bsingh
   */
  public void validateKFDetails() throws Exception {
    List<WebElement> kfDetailElts = CheckUtils.getElements(getTest(), LOC_KF_DETAILS);
    String detailText =  "";
    for(WebElement elt : kfDetailElts){
      detailText += elt.getText();
    }
    if(detailText.contains("CURRENT PPS VALUE")){
      reporter.reportPassed(getName(), "KFDetail contains -  CURRENT PPS VALUE ");
    }else{
      reporter.reportFailed(getName(), "KFDetails doesn't contain - CURRENT PPS VALUE ");
    }
    if(detailText.contains("CURRENT ELITE MILES")){
      reporter.reportPassed(getName(), "KFDetail contains -  CURRENT ELITE MILES ");
    }else{
      reporter.reportFailed(getName(), "KFDetails doesn't contain  - CURRENT ELITE MILES ");
    }
    if(detailText.contains("RESERVE PPS VALUE")){
      reporter.reportPassed(getName(), "KFDetail contains - RESERVE PPS VALUE ");
    }else{
      reporter.reportFailed(getName(), "KFDetails doesn't contain - RESERVE PPS VALUE ");
    }
    if(detailText.contains("RESERVE EXPIRY")){
      reporter.reportPassed(getName(), "KFDetail contains -  RESERVE EXPIRY ");
    }else{
      reporter.reportFailed(getName(), "KFDetails doesn't contain - RESERVE EXPIRY ");
    }
    if(detailText.contains("TIER EXPIRY")){
      reporter.reportPassed(getName(), "KFDetail contains  - TIER EXPIRY ");
    }else{
      reporter.reportFailed(getName(), "KFDetails doesn't contain - TIER EXPIRY");
    }
    if(detailText.contains("RETAIN TIER WITH")){
      reporter.reportPassed(getName(), "KFDetail contains - RETAIN TIER WITH" );
    }else{
      reporter.reportFailed(getName(), "KFDetails doesn't contain - RETAIN TIER WITH ");
    }
  }



  /**
   * Clicks Logout Button
   * @author bsingh
   */
  public void clickLogout() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_LOGOUT, "Logout Button couldn't be clicked successfully", "Logout Button Clicked Successfully");
  }

}

