package com.amadeus.selenium.sqmobile.page.payment.customers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class SaudiPaymentPasswordPage extends SqMobileCommonPage{


  protected static final By LOC_IN_MERCHANT_PAGE_PASSWORD = By.cssSelector("#userInput1_password");
  protected static final By LOC_BT_BACK_TO_MERCHANT=By.id("backToMerchant");
  protected static final By LOC_BT_DO_AUTHENTICATION = By.cssSelector("[id*='userInput']>form>input");

  public SaudiPaymentPasswordPage(SeleniumSEPTest test) {
    super(test);
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_IN_MERCHANT_PAGE_PASSWORD, 30)){
      reporter.fail("This is not SaudiPaymentPassword Page");
    }else{
      reporter.reportPassed("SaudiPaymentPassword Page", "In SaudiPaymentPassword Page");
    }
  }

  public void passwordVerification(String password){
    WebElement passwordElt = CheckUtils.getElement(getTest(), LOC_IN_MERCHANT_PAGE_PASSWORD);

    if(passwordElt!=null && passwordElt.isDisplayed()){
      FillUtils.fillInputOrFail(getTest(), LOC_IN_MERCHANT_PAGE_PASSWORD,password,"password not entered");
      List<WebElement> doAuthenticationElt = CheckUtils.getElements(getTest(), LOC_BT_DO_AUTHENTICATION);
      for(WebElement elt : doAuthenticationElt){
        if(elt!=null && elt.isDisplayed()){
          ClickUtils.clickButtonOrFail(getTest(), elt, "Do authentication button not clicked");
          reporter.report("payment Merchant page", "password authenticated");
          break;
        }
      }
    }
    else{
      reporter.fail("password not authenticated in payment merchant page");
    }
    WaitUtils.wait(2);
    WebElement backtoMerchant = CheckUtils.getElement(getTest(), LOC_BT_BACK_TO_MERCHANT);
    if(backtoMerchant!=null && backtoMerchant.isDisplayed()){
      ClickUtils.clickButtonOrFail(getTest(), LOC_BT_BACK_TO_MERCHANT, "back to merchant button not clicked");
      getDriver().switchTo().alert().accept();
      waitForOverlayLoading(10);
    }
  }





}
