package com.amadeus.selenium.sqmobile.page.krishflyer;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class MyProfilePage extends CommonKFServicesPage {


  public MyProfilePage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_KFSERVICES, 30);
    if(elementPresent){
      reporter.reportPassed("KFServices : " , "KFServices Page loaded");
    }else{
      reporter.fail("KFServices Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "SQ Mobile- KF Services");
    reporter.report("CHECKPOINT", "KFServices PAGE is DISPLAYED");
  }

  //LOCATORS - MyProfilePage-------------------------------------------------------

  protected static By LOC_BUTTON_KFSERVICES= By.className("more");

  //----------------------------------------------------------------------------

  private String pageName = "MyProfile Page";

  /**
   * Clicks MyProfile Button
   * @throws Exception
   * @author bsingh
   */
  public void clickChangePin() throws Exception {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_KFSERVICES);
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


}

