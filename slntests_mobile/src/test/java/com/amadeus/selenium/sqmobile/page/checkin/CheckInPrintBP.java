package com.amadeus.selenium.sqmobile.page.checkin;

import org.openqa.selenium.By;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CheckInPrintBP extends SqMobileCommonPage{

  public CheckInPrintBP(SeleniumSEPTest test) {
   super(test);
   CheckUtils.checkPageTitle(getTest(), "MSQ Mobile - Send flight info");
   reporter.report("CHECKPOINT", "CheckInPrintBP Page is DISPLAYED");
   boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_SELF_PRINTED, 30);
   if(elementPresent){
     reporter.reportPassed("PAGE LOADED : ", "CheckInPrintBP Page loaded");
   }else{
     reporter.fail("CheckInPrintBP Page NOT loaded ");
   }

  }

  //LOCATORS - CHECK IN PAGE--------------------------------------------------------

  protected static By LOC_BUTTON_SEND_EMAIL= By.cssSelector("spbpEmailFormSend>span");
  protected static By LOC_BUTTON_CLOSE_EMAIL= By.cssSelector("spbpEmailClose");
  protected static By LOC_TX_EMAIL= By.id("spbpEmailAddress");
  protected static By LOC_BUTTON_SELF_PRINTED = By.cssSelector(".inlineMsg-aLink.secondary.print");

  //----------------------------------------------------------------------------


  /**
   * Clicks SelfPrinted Button
   * @author bsingh
   */
 public void clickSelfPrinted()  {
   ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SELF_PRINTED, "Self-Printed Button couldn't be clicked", "Self-Printed Button clicked successfully");
 }

 /**
  * Fills Email Address
  * @param emailId
  * @author bsingh
  */
 public void fillEmailAddress(String emailId) {
   FillUtils.fillInputOrFail(getTest(), LOC_TX_EMAIL, emailId, "Email Address in BP coudln't be filled successfully");
 }


 /**
  * Closes BP EmailId PopUp
  * @author bsingh
  */
 public void closeEmailPopUp() {
   ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CLOSE_EMAIL, "Close Button in BP-Email popup couldn't be clicked", "Close Button in BP-email clicked successfully");
 }


 /**
  * Clicks Send Button
  * @author bsingh
  */
 public void clickSend() {
   ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SEND_EMAIL, "Send Button couldn't be clicked", "Send Button clicked successfully");
 }
}
