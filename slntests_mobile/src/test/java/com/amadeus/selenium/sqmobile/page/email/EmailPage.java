package com.amadeus.selenium.sqmobile.page.email;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyFlightsPage.Itinerary;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class EmailPage extends SqMobileCommonPage {

  protected final static By LOC_TEXT_TO =  By.id("toInput");
  protected final static By LOC_TEXT_FROM =  By.id("fromInput");
  protected final static By LOC_TEXT_SUBJECT =  By.id("subjectInput");
  protected final static By LOC_BUTTON_SUBMIT = By.className("validation");
  protected final static By LOC_MESSAGE_BODY = By.className("mailItinerary");

  public EmailPage(SeleniumSEPTest test) throws Exception{
    super(test);
    // Check that we're on the right page.
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_SUBMIT, 30)){
      reporter.fail("This is not Email page");
    }else{
      reporter.reportPassed("Email page", "In Email page");
    }
  }

  String pageName = "Email Page" ;
  //-------------------------------------------------------------------------------------


  /**
   * Fills toField , fromField and subjectField in the email
   * @param toField
   * @param fromField
   * @param subject
   * @author bsingh
   */
  public void fillEmailInfo(String toField ,String fromField , String subject){
    fillTo(toField);
    fillFrom(fromField);
    fillSubject(subject);
  }


 /**
  * Fills EmailId of the recipient
  * @param to EmailId of the recipient
  * @author bsingh
  */
 public void fillTo(String to){
   FillUtils.fillInputOrFail(getTest(), LOC_TEXT_TO, to, to+" couldn't be filled successfully in TO field");
   reporter.reportPassed(pageName, to+" filled successfully in To Field");
 }


 /**
  * Fills EmailId of the sender
  * @param from EmailId of the sender
  * @author bsingh
  */
 public void fillFrom(String from){
   FillUtils.fillInputOrFail(getTest(), LOC_TEXT_TO, from , from+" couldn't be filled successfully in From field");
   reporter.reportPassed(pageName, from+" filled successfully in From Field");
 }


 /**
  * Fills Subject of the Email
  * @param subject Subject of the Email
  * @author bsingh
  */
 public void fillSubject(String subject){
   FillUtils.fillInputOrFail(getTest(), LOC_TEXT_TO, subject , subject+" couldn't be filled successfully in Subject field");
   reporter.reportPassed(pageName, subject+" filled successfully in Subject Field");
 }


 /**
  * Validates Itinenary
  * @param itinerary DEPARTURE Or RETURN
  * @throws IOException
  * @author bsingh
  */
 public void validateMessageBody(Itinerary itinerary ) throws IOException{
   List<WebElement> msgBodyElts = CheckUtils.getElements(getTest(), LOC_MESSAGE_BODY);
   if(msgBodyElts!=null){
     if(itinerary.equals(Itinerary.DEPARTURE)){
       if(msgBodyElts.get(0).getText().contains(getValue(itinerary+" FLIGHT NUMBER"))){
         reporter.reportPassed(itinerary+" Flight Number : ", getValue(itinerary+" FLIGHT NUMBER"));
       }else{
         reporter.reportFailed(pageName , itinerary+" Flight Number is not displayed properly");
       }
     }else if(msgBodyElts.size()>1 && itinerary.equals(Itinerary.RETURN)){

       if(msgBodyElts.get(1).getText().contains(getValue(itinerary+" FLIGHT NUMBER"))){
         reporter.reportPassed(itinerary+" Flight Number : ", getValue(itinerary+" FLIGHT NUMBER"));
       }else{
         reporter.reportFailed(pageName , itinerary+" Flight Number is not displayed properly");
       }
     }
   }else{
     reporter.reportFailed(pageName, "Message body is not displayed properly");
   }
 }



  /**
   * Clicks Send Button
   * @author bsingh
   */
  public void clickSend(){
    List<WebElement> submitButtons = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    boolean buttonClicked = false ;
    for(WebElement tempButoon : submitButtons){
      if(tempButoon.getText().contains("Send")){
        ClickUtils.clickButtonOrFail(getTest(), tempButoon, "Send Button could not clicked");
        reporter.reportPassed( pageName , "Send Button has been clicked on Search Page");
        buttonClicked = true ;
        break;
      }
  }if(!buttonClicked)
    reporter.reportFailed(pageName, "Send Button couldn't be clicked ");
}


  /**
   * Clicks Cancel Button
   * @author bsingh
   */
  public void clickCancel(){
    List<WebElement> submitButtons = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    boolean buttonClicked = false ;
    for(WebElement tempButoon : submitButtons){
      if(tempButoon.getText().contains("Cancel")){
        ClickUtils.clickButtonOrFail(getTest(), tempButoon, "Cancel Button could not clicked");
        reporter.reportPassed( pageName , "Cancel Button has been clicked on Search Page");
        buttonClicked = true ;
        break;
      }
    } if(!buttonClicked)
       reporter.reportFailed("EmailPage :  ", "Cancel Button couldn't be clicked ");
  }

}
