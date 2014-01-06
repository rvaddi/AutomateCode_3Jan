package com.amadeus.selenium.sqmobile.page.staticpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class TCPage extends SqMobileCommonPage {

  public TCPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_TC_HEADER, 30);
    if(elementPresent){
      reporter.reportPassed(pageName, "TC Page loaded");
    }else{
      reporter.fail("TC Page not loaded ");
    }


    CheckUtils.checkPageTitle(getTest(), "SQ Mobile");
    reporter.report("CHECKPOINT", "TC PAGE is DISPLAYED");
  }

  //LOCATORS - TCPage-----------------------------------------------------------

  protected static By LOC_TC_HEADER= By.cssSelector(".cityPair.colorWhite.statcontentTitle");
  protected static By LOC_TC_TERMS = By.className("toggleIconCustom");

  //----------------------------------------------------------------------------

  private String pageName = "TCPage" ;

  /**
   * Validates Header Text
   */
  public void validateTCPage() {
    validateHeader();
    validateTC();
  }


  /**
   * Validates TC Header
   */
  public void validateHeader() {
    WebElement tcHeaderElt = CheckUtils.getElement(getTest(),LOC_TC_HEADER);
    if(tcHeaderElt!=null && tcHeaderElt.getText().contains("Terms and Conditions")){
      reporter.reportPassed(pageName, "TC Header is displayed properly");
    }else{
      reporter.reportFailed(pageName, "TC Header is not displayed properly");
    }
  }


  /**
   * Validates TC are displayed
   */
  public void validateTC()  {
    List<WebElement> tcElts = CheckUtils.getElements(getTest(), LOC_TC_TERMS);
    if(tcElts!=null && !tcElts.isEmpty()){
      reporter.reportPassed(pageName, "T & C are displayed ");
    }else {
       reporter.reportFailed(pageName, "T & C are not displayed");
    }
  }
}
