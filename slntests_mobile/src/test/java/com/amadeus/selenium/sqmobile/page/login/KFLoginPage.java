package com.amadeus.selenium.sqmobile.page.login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class KFLoginPage extends SqMobileCommonPage {


  //LOCATORS - KFLoginPage------------------------------------------------------

  protected final static By LOC_KF_HEADER= By.cssSelector(".cityPair.colorWhite");
  protected final static By LOC_TX_KF_NUMBER= By.id("KF_NUMBER");
  protected final static By LOC_TX_PIN= By.id("PIN");
  protected final static By LOC_CHK_REMEMBER_KF_NUMBER= By.id("chkBox");
  protected final static By BUTTON_KF_LOGIN = By.cssSelector(".buttonDirection");
  protected final static By LOC_FORGOT_PIN= By.className("colorBlack");
  //----------------------------------------------------------------------------

  public KFLoginPage(SeleniumSEPTest test) throws Exception{
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), BUTTON_KF_LOGIN, 30)){
      reporter.fail("This is not KFLogin Page");
    }else{
      reporter.reportPassed("KFLogin Page", "In KFLogin Page");
    }
  }

  private String pageName = "KFLogin Page ";


  /**
   * Fills KFNumber
   * @param kFNumber KFNumber
   * @author bsingh
   */
  public void fillKFNumber(String kFNumber) {
    FillUtils.fillInputOrFail(getTest(), LOC_TX_KF_NUMBER, kFNumber, "KFNumber couldn't be filled ");
    reporter.reportPassed("KF Number filled : " , kFNumber);
  }


  /**
   * Fills PIN Number
   * @param pin PIN
   */
  public void fillPIN(String pin) throws Exception {
    FillUtils.fillInputOrFail(getTest(), LOC_TX_PIN, pin, "Pin couldn't be filled ");
    reporter.reportPassed("PIN filled : " , "Pin filled successfully");
  }


  /**
   * Checks or UnChecks the Checkbox as per the given arguments
   * @param remember boolean true or false
   * @author bsingh
   */
  public void clickRememberMe(boolean remember) {
    WebElement rememberMeElt =  CheckUtils.getElement(getTest() , LOC_CHK_REMEMBER_KF_NUMBER);
    if(rememberMeElt!=null && remember && !rememberMeElt.isSelected()){
      ClickUtils.clickButtonOrFail(getTest(), rememberMeElt, "Remember KFNumber Checkbox couldn't be clicked", "Remember KFNumber clicked successfully");
    }else if(rememberMeElt!=null && !remember && rememberMeElt.isSelected()){
      ClickUtils.clickButtonOrFail(getTest(), rememberMeElt, "Remember KFNumber Checkbox couldn't be UnChecked", "Remember KFNumber Checkbox UnChecked successfully");
    }
  }


  /**
   * Clicks KFLogin Button
   * @author bsingh
   */
  public void clickKFLogin() {
     List<WebElement> buttonElts = CheckUtils.getElements(getTest(), BUTTON_KF_LOGIN);
     boolean clicked = false;
     for(WebElement button : buttonElts){
       if(button.getText().contains("Login")){
         ClickUtils.clickButtonOrFail(getTest(), button, "Login Button couldn't be clicked");
         clicked =  true ;
       }
     }
     if(clicked){
       reporter.reportPassed(pageName, "Login Button clicked successfully");
     }else{
       reporter.reportFailed(pageName, "Login Button couldn't clicked");
     }
  }


  /**
   * Clicks ForgotPin Button
   * @author bsingh
   */
  public void clickForgotPin() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_FORGOT_PIN, "Forgot Pin Link couldn't be clicked", "Forgot Pin Link clicked successfully");
  }


}

