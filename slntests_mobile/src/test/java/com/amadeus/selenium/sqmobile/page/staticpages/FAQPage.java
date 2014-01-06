package com.amadeus.selenium.sqmobile.page.staticpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class FAQPage extends SqMobileCommonPage {

  public FAQPage(SeleniumSEPTest test) throws Exception{
    super(test);
    CheckUtils.checkPageTitle(getTest(), "SQ Mobile");
    reporter.report("CHECKPOINT", "FAQ Page is DISPLAYED");

    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), BUTTON_BACK, 30);
    if(elementPresent){
      reporter.reportPassed("FAQ Page : ", "FAQ Page loaded");
    }else{
      reporter.fail("FAQ Page NOT loaded ");
    }

  }


  //LOCATORS - FAQ PAGE---------------------------------------------------------

  protected static By BUTTON_BACK = By.cssSelector(".buttonHolder.noButtonholder");
  protected static By FAQ_HEADERS = By.cssSelector(".panelContent>h2");

  String pageName = "FAQ Page";
  //----------------------------------------------------------------------------


  /**
   * Validates FAQPage
   */
  public void validateFAQPage() {
    List<WebElement> headerElts = CheckUtils.getElements(getTest(), FAQ_HEADERS);
    String headerText = "" ;
    for(WebElement header : headerElts){
      headerText += header.getText();
    }
    if(headerText.contains("Check-in")&&headerText.contains("Flight Status")&&headerText.contains("Booking & Change Booking")&&headerText.contains("KrisFlyer Services")){
      reporter.reportPassed(pageName , "Header displayed properly");
    }else{
      reporter.reportFailed(pageName, "Headers are not displayed properly");
    }
  }


  /**
   * Clicks Back Button
   */
  public void clickBack() {
    ClickUtils.clickButtonOrFail(getTest(), BUTTON_BACK, "Back Button couldn't be clicked", "Back Button clicked successfully");
  }

}