package com.amadeus.selenium.sqmobile.page.krishflyer;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class RetroClaimStatusPage extends CommonKFServicesPage {


  public RetroClaimStatusPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_KFSERVICES, 30);
    if(elementPresent){
      reporter.reportPassed(pageName, "RetroClaimStatus Page loaded");
    }else{
      reporter.fail("RetroClaimStatus Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "SQ Mobile- Retroactive claim status ");
    reporter.report("CHECKPOINT", "RetroClaimStatus PAGE is DISPLAYED");
  }

  //LOCATORS - RetroClaimStatusPage---------------------------------------------

  protected static By LOC_BUTTON_KFSERVICES= By.className("more");
  protected static By LOC_TRANSACTION_HEADING= By.className("boundsTitle");
  protected static By LOC_LABELS_DATE_DESC= By.cssSelector(".week.timetableResuts.statmentTd.txnDet>tbody>tr>th");

  //----------------------------------------------------------------------------

  private String pageName = "RetroClaimStatus Page ";

  /**
   * Validates RetroClaimStatusPage
   * @author bsingh
   */
  public void validateRetroClaimStatusPage() throws Exception{
    WebElement transactionElt = CheckUtils.getElement(getTest(), LOC_TRANSACTION_HEADING);
    if(transactionElt!=null && transactionElt.getText().equalsIgnoreCase("Transaction details")){
      reporter.reportPassed(pageName, "Transaction Details Header is displayed properly");
    }else {
      reporter.reportFailed(pageName, "Transaction Details Header is not displayed properly");
    }

  String labels = "" ;
  List<WebElement> dateLabelElts = CheckUtils.getElements(getTest(), LOC_LABELS_DATE_DESC);
  for(WebElement elt : dateLabelElts){
    labels += elt.getText();
  }
  if(labels.contains("Date")){
    reporter.reportPassed( pageName , "Date is displayed properly");
  }else{
    reporter.reportFailed(pageName, "Date is not displayed properly");
  }
  if(labels.contains("Description")){
    reporter.reportPassed(pageName, "Description is displayed properly");
  }else{
    reporter.reportFailed(pageName, "Description is not displayed properly");
  }
}

}

