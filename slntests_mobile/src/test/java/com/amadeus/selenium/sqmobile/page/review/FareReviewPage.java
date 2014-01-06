
package com.amadeus.selenium.sqmobile.page.review;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.AirchinaPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.CarabPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.ELALPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.EgyptAirPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.IcelandAirPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.MEAPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.SQMobilePaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.SaudiPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.TAMPaxInfoPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class FareReviewPage extends CommonReviewPage{

  // Locators FareReviewPage----------------------------------------------------------

  protected final static By LOC_PB_FAREREVIEW_BACK = By.className("back");
  protected final static By LOC_PB_FAREREVIEW_CONTINUE = By.className("validation");
  protected final static By LOC_PB_FAREREVIEW_WARNMESSAGE = By.cssSelector(".msg.warning");


  //----------------------------------------------------------------------------------

  public FareReviewPage(SeleniumSEPTest test) throws Exception {
    super(test , "FareReview Page");
    // Checking if we are on the right page
    boolean FareReviewPage = WaitUtils.waitForElementPresent(getTest(), LOC_TRIP_SECTION, 120);
    if (!FareReviewPage) {
      WebElement errormsg = CheckUtils.getElement(getTest(), LOC_PB_FAREREVIEW_WARNMESSAGE);
      if (errormsg != null && errormsg.isDisplayed()) {
        reporter.fail(errormsg.getText().trim());
      }
      else {
        reporter.fail("This is not FareReview Page");
      }
    }else{
      reporter.reportPassed("FareReview Page", "In FareReview Page");
    }
    this.automaticScreenShot();
  }


  private String pageName = "FareReview Page";



  public enum Customer{
    SAUDI , AIRCHINA,ICELANDAIR,TAM,CARAB,SQMOBILE,ELAL, MEA ,EGYPTAIR
  }


  /**
   * Validate Fare Review Page
   * @throws Exception
   */
  public void validateFareReviewPage() throws Exception{
    validateDepartureSummary();
    if( getValue("Trip Type").equals("RT")){
      validateArrivalSummary();
    }
    validatePriceBreakDown();
  }



  /**
   * Validates the presence of Warning Message
   */
  public  void validateWarningMessage()  {
    WebElement warningMessage = CheckUtils.getElement(getTest(), LOC_PB_FAREREVIEW_WARNMESSAGE);
    if(warningMessage!=null && warningMessage.isDisplayed()){
      reporter.report(pageName, warningMessage.getText());
    }else{
      reporter.reportFailed(pageName, "Warning Message is not displayed");
    }
  }


  /**
   * Click on Continue
   * @throws Exception
   * @return PaxInfoPage Object
   * Modified By : bsingh
   */
  public PaxInfoPage actionContinue(Customer customer) throws Exception {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_PB_FAREREVIEW_CONTINUE);
    boolean buttonClicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().toUpperCase().contains("CONTINUE")){
        ClickUtils.clickButtonOrFail(getTest(), button , "Continue Button was not clicked" , "Continue Button Clicked Successfully");
        buttonClicked = true ;
        waitForOverlayLoading(50);
        break;
      }
    }
    if(!buttonClicked){
      reporter.reportFailed(pageName, "Continue Button couldn't be clicked");
    }else{
      if(customer!=null){
        if(Customer.SAUDI.equals(customer)){
          return PageFactory.getPageObject(SaudiPaxInfoPage.class);
        }
        else if(Customer.AIRCHINA.equals(customer)){
          return PageFactory.getPageObject(AirchinaPaxInfoPage.class);
        }
        else if(Customer.ICELANDAIR.equals(customer)){
          return PageFactory.getPageObject(IcelandAirPaxInfoPage.class);
        }
        else if(Customer.CARAB.equals(customer)){
          return PageFactory.getPageObject(CarabPaxInfoPage.class);
        }
        else if(Customer.TAM.equals(customer)){
          return PageFactory.getPageObject(TAMPaxInfoPage.class);
        }
        else if(Customer.ELAL.equals(customer)){
          return PageFactory.getPageObject(ELALPaxInfoPage.class);
        }
        else if(Customer.SQMOBILE.equals(customer)){
          return PageFactory.getPageObject(SQMobilePaxInfoPage.class);
        }
        else if(Customer.MEA.equals(customer)){
          return PageFactory.getPageObject(MEAPaxInfoPage.class);
        }
        else if(Customer.EGYPTAIR.equals(customer)){
          return PageFactory.getPageObject(EgyptAirPaxInfoPage.class);
        }
      }
      else{
        reporter.fail("Customer not found");
      }
    }
    return null;
  }

  /**
   * click on Back
   * @throws Exception
   */
  public void clickBack(){
    ClickUtils.clickButtonOrFail(getTest(), LOC_PB_FAREREVIEW_BACK, "Back button NOT found or is NOT displayed" ,"Back Button is clicked successfully");

  }

}
