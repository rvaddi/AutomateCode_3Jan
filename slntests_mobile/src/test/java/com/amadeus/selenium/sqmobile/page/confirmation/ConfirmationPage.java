package com.amadeus.selenium.sqmobile.page.confirmation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class ConfirmationPage extends CommonConfirmationPage {

  //LOCATORS - CONFIRMATION PAGE-----------------------------------------------------

  protected final static By LOC_FARE_CONDITION_LINK= By.className("popup-fare-cond");

  protected final static By LOC_PB_CONFIRMATION_WARNMESSAGE = By.cssSelector(".msg.warning");

  // --------------------------------------------------------------------------------

  public ConfirmationPage(SeleniumSEPTest test) throws Exception{
    super(test , "Confirmation Page");
    // Checking if we are on the right page

    /*WebElement elt = CheckUtils.getElement(getTest(), LOC_BOOKING_NUM);
    if(elt!=null){
      System.out.println(elt.getText());
    }else{
      System.out.println("Webelement is null");
    }*/

    // Checking if we are on the right page
    boolean ConfirmationPage = WaitUtils.waitForElementPresent(getTest(), LOC_BOOKING_NUM, 120);
    if (!ConfirmationPage) {
      WebElement errormsg = CheckUtils.getElement(getTest(), LOC_PB_CONFIRMATION_WARNMESSAGE);
      if (errormsg != null && errormsg.isDisplayed()) {
        reporter.fail(errormsg.getText().trim());
      }
      else {
        reporter.fail("This is not Confirmation Page");
      }
    }
    else {
      reporter.reportPassed("Confirmation Page", "In Confirmation Page");
    }
    this.automaticScreenShot();
  }



  /**
   * Validates Confirmation Page
   * @author bsingh
   * @throws Exception
   */
  public void validateConfirmationPage() throws Exception {
    validateBookingPNR();
    validateEticket();
    validateEmailId();
    validateDepartureSummary();
    if(getValue("Trip Type").equalsIgnoreCase("RT")){
      validateArrivalSummary();
    }

    validatePriceBreakDown();
  }


  /**
   * It will replace the siteName in the current URL with that of passed as in parameter and will launch the desired site
   * directly
   * @param oldSiteName - the siteName that is to be replaced in the current URL
   * @param newSiteName - the siteName that will replace the oldSiteName
   * @throws Exception
   * @author bsingh
   */
  public void actionReplaceSiteNameInURL(String oldSiteName, String newSiteName) throws Exception {
    actionLaunchURL(getURL().replace(oldSiteName, newSiteName));
  }


  /**
   * Launches CheckinPage Directly
   * @throws Exception
   * @author bsingh
   */
  public void actionLaunchCheckinPage() throws Exception {
    actionReplaceSiteNameInURL("plnext/SQMobile/MSearch.action", "plnext/SQNRMobile/checkinFlow.action");
  }

/*
  /**
   * Validates FareCondition PopUp
   * @throws Exception
   *//*
  public void validateFareConditionPopUp() throws Exception {
    WebElement fareConditionPopUpElt = CheckUtils.getElement(getTest(), LOC_FARE_CONDITION_POPUP_PANEL);
    if(fareConditionPopUpElt!=null  && fareConditionPopUpElt.isDisplayed()){
      List<WebElement> fareConditionElts = CheckUtils.getElements(getTest(), fareConditionPopUpElt ,LOC_FARE_CONDITION_TABS);
      for(WebElement fareCondition : fareConditionElts){
        if(fareCondition.isDisplayed()){
          fareCondition.click();
          WaitUtils.wait(1);
          fareCondition.click();
        }
      } List<WebElement> closeLinks = CheckUtils.getElements(getTest(), LOC_BUTTON_POPUP_CLOSE);
      for (WebElement close : closeLinks){
        if (close.isDisplayed()){
          close.click();
          break;
        }
      }
    }
  }*/
  public TAMPaymentPage validateInfo(String msgToBeValidated) throws Exception {
		StringBuffer msgDisplayed = new StringBuffer();
		List<WebElement> listElts = CheckUtils.getElements(getTest(), LOC_LI_MESSAGE);
		if (listElts != null && listElts.size() > 0) {
			for (WebElement elt : listElts) {
				msgDisplayed = msgDisplayed.append(elt.getText());
			}

			if (msgDisplayed.toString().toLowerCase().contains(msgToBeValidated.toLowerCase())) {
				reporter.reportPassed("Message Displayed : ", msgDisplayed.toString());
			}
			else {
				reporter.reportFailed("Message Displayed : ", msgDisplayed.toString());
			}
		}
		else {
			reporter.reportFailed("Validation Failed", "Message is not Displayed");
		}
		 return PageFactory.getPageObject(TAMPaymentPage.class);
	}

}