package com.amadeus.selenium.sqmobile.page.checkin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class CheckInPaxSelection extends SqMobileCommonPage{

  //LOCATORS - CHECK IN PAGE--------------------------------------------------------

  protected static final By LOC_BUTTON_CANCEL = By.id("multiPaxSelCancel");
  protected static final By LOC_CHKBOX_MULTI_PAX = By.cssSelector("[id^='cust']");
  protected static final By LOC_BUTTON_CONTINUE = By.id("multiPaxSelContinue");

  private String pageName = "CheckInPaxSelection";
  //----------------------------------------------------------------------------


  public CheckInPaxSelection(SeleniumSEPTest test) throws Exception{
    super(test);
     reporter.report("CHECKPOINT", "Checkin Page is DISPLAYED");
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_CANCEL, 40);
    if (elementPresent) {
      reporter.reportPassed(pageName, "CheckInPaxSelection Page loaded");
    }
    else {
      reporter.fail("CheckInPaxSelection Page NOT loaded ");
    }
  }



  /**
   * To Select All the passengers
   * @throws Exception
   */
  public void selectAllPax() throws Exception {
    if (Integer.parseInt(getValue("Nb Adult")) > 1) {
      List<WebElement> paxChkBoxElts = CheckUtils.getElements(getTest(), LOC_CHKBOX_MULTI_PAX);
      if (paxChkBoxElts == null || paxChkBoxElts.size() < Integer.parseInt(getValue("Nb Adult"))) {
        reporter.reportFailed("MULTIPAX CHECKIN : ", "CheckBoxes to select passengers are not displayed properly");
      }
      else {
        for (WebElement chkbox : paxChkBoxElts) {
          FillUtils.fillCheckboxOrFail(getTest(), chkbox, true, "CheckBox couldn't be selected ");
        }
      }
    }
  }


  /**
   * Clicks Continue Button
   * @author bsingh
   */
  public void clickContinue()throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CONTINUE, "Continue Button couldn't be clicked", "Continue Button clicked successfully");
    waitForOverlayLoading(90);
  }


  /**
   * Clicks Cancel Button
   * @author bsingh
   */
  public void clickCancel(){
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CANCEL, "Cancel Button couldn't be clicked", "Cancel Button clicked successfully");
  }



}
