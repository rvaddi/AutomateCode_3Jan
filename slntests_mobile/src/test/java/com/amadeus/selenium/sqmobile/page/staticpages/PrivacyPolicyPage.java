package com.amadeus.selenium.sqmobile.page.staticpages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class PrivacyPolicyPage extends SqMobileCommonPage {

  public PrivacyPolicyPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_PP_HEADER, 30);
    if(elementPresent){
      reporter.reportPassed(pageName, "PP Page loaded");
    }else{
      reporter.fail("PP Page not loaded ");
    }


    CheckUtils.checkPageTitle(getTest(), "SQ Mobile");
    reporter.report("CHECKPOINT", "PP PAGE is DISPLAYED");
  }

  //LOCATORS - PPPage-----------------------------------------------------------

  protected static By LOC_PP_HEADER= By.cssSelector(".cityPair.colorWhite.statcontentTitle");
  protected static By LOC_TC_TERMS = By.className("toggleIconCustom");

  //----------------------------------------------------------------------------

  private String pageName = "PrivacyPolicy Page";

  /**
   * Validates Header Text
   */
  public void validateTCPage() {
    validateHeader();
    validatePP();
  }


  /**
   * Validates PP Header
   */
  public void validateHeader( ) {
    WebElement tcHeaderElt = CheckUtils.getElement(getTest(),LOC_PP_HEADER);
    if(tcHeaderElt!=null && tcHeaderElt.getText().contains("Privacy policy")){
      reporter.reportPassed(pageName, "PP Header is displayed properly");
    }else{
      reporter.reportFailed(pageName, "PP Header is not displayed properly");
    }
  }


  /**
   * Validates Policies are displayed
   */
  public void validatePP() {
    List<WebElement> tcElts = CheckUtils.getElements(getTest(), LOC_TC_TERMS);
    if(tcElts!=null && !tcElts.isEmpty()){
      reporter.reportPassed(pageName, "Policies are displayed ");
    }else {
       reporter.reportFailed(pageName, "Policies are not displayed");
    }
  }
}
