package com.amadeus.selenium.sqmobile.page.krishflyer;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class MyTransactionsPage extends SqMobileCommonPage {

  public MyTransactionsPage(SeleniumSEPTest test) {
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_LI_KF_TRANSACTIONS_TYPE, 30);
    if(elementPresent){
      reporter.reportPassed("MyTransaction : ", "MyTransaction Page loaded");
    }else{
      reporter.fail("MyTransaction Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "My Transactions - ALL");
    reporter.report("CHECKPOINT", "MyTransaction PAGE is DISPLAYED");
  }

  // LOCATORS - MY TRANSACTIONS PAGE--------------------------------------------------------

  protected static By LOC_LI_KF_TRANSACTIONS_TYPE = By.id("txnType");
  protected static By LOC_LI_KF_TRANSACTIONS_TXN_DETAILS = By.className("txnDet");
  protected static By LOC_LI_KF_TRANSACTIONS_MILEAGE_CLAIM_STATUS = By.partialLinkText("Mileage claim status");

  String pageName = "MyTransactions Page";

  // ----------------------------------------------------------------------------

  /**
   * Validates MyTransactionPage
   * @author bsingh
   */
  public void validateTransactionPage()  {
    validateTransactionType();
    validateDateAndDesc();
    validateTransactionDefaultValues();
  }
  // ****************************************************************************


  /**
   * Validates Transaction Type
   * @author bsingh
   */
  public void validateTransactionType()  {
    Select txnType = new Select(CheckUtils.getElement(getTest(), LOC_LI_KF_TRANSACTIONS_TYPE));
    if (txnType.getOptions().size()==3){
      reporter.reportPassed(pageName, "Transaction Type dropdown is displayed properly");
    }
    else{
      reporter.reportFailed(pageName, "Transaction Type dropdown is notdisplayed properly");
    }
  }



  /**
   * Validates Transaction date and description
   * @author bsingh
   */
  public void validateDateAndDesc()  {
    WebElement txnDetails = CheckUtils.getElement(getTest(), LOC_LI_KF_TRANSACTIONS_TXN_DETAILS);
    List<WebElement> txnHeader = txnDetails.findElements(By.tagName("th"));
    if (txnHeader!=null && txnHeader.size()== 2){
      if (txnHeader.get(0).getText().equals("Date") && txnHeader.get(1).getText().equals("Description")){
        reporter.reportPassed("Date : Description - ", txnHeader.get(0).getText() +" : " + txnHeader.get(1).getText());
      }
      else{
        reporter.reportPassed("Date : Description - ", txnHeader.get(0).getText() +" : " + txnHeader.get(1).getText());
      }
    }
    else{
      reporter.reportFailed(pageName, "Txn Details table is not displayed properly");
    }
  }


  /**
   * Validates Default Transaction values
   * @author bsingh
   */
  public void validateTransactionDefaultValues()  {
    Select txnType = new Select(CheckUtils.getElement(getTest(), LOC_LI_KF_TRANSACTIONS_TYPE));
    boolean defaultOption = txnType.getFirstSelectedOption().getText().equals("All");
    boolean allOptions = txnType.getOptions().get(1).getText().equals("Miles earned") &&
        txnType.getOptions().get(2).getText().equals("Miles redeemed");

    if (defaultOption && allOptions) {
      reporter.reportPassed("TransactionTypes -  ", txnType.getOptions().get(1).getText() + " and " + txnType.getOptions().get(2).getText());
    }
    else {
      reporter.reportFailed("TransactionTypes -  ", txnType.getOptions().get(1).getText() + " and " + txnType.getOptions().get(2).getText());
    }
  }


  /**
   * Validates TransactionDetails
   * @
   */
  public void validateTransactionDetails()  {
    int earned_count = 0;
    int redeemed_count = 0;
    int all_count = 0;
    WebElement txnDetails = CheckUtils.getElement(getTest(), LOC_LI_KF_TRANSACTIONS_TXN_DETAILS);
    List<WebElement> txn_details_all = txnDetails.findElements(By.tagName("tr"));
    int num_of_txns = txn_details_all.size();
    WaitUtils.wait(1);
    for (int i = 0; i < num_of_txns; i++) {
      if (txn_details_all.get(i).getText().contains("redeemed: -") ||
          txn_details_all.get(i).getText().contains("earned")) {
        all_count++;
      }
    }

    ClickUtils.selectByValue(getTest(), LOC_LI_KF_TRANSACTIONS_TYPE, "EARNED");
    txnDetails = CheckUtils.getElement(getTest(), LOC_LI_KF_TRANSACTIONS_TXN_DETAILS);
    List<WebElement> txn_details_earned = txnDetails.findElements(By.tagName("tr"));
    num_of_txns = txn_details_earned.size();
    for (int i = 0; i < num_of_txns; i++) {
      if (txn_details_earned.get(i).getText().contains("earned") &&
          !txn_details_earned.get(i).getText().contains("redeemed: -")) {
        earned_count++;
      }
    }
    ClickUtils.selectByValue(getTest(), LOC_LI_KF_TRANSACTIONS_TYPE, "REDEEM");
    txnDetails = CheckUtils.getElement(getTest(), LOC_LI_KF_TRANSACTIONS_TXN_DETAILS);
    List<WebElement> txn_details_redeemed = txnDetails.findElements(By.tagName("tr"));
    num_of_txns = txn_details_redeemed.size();
    for (int i = 0; i < num_of_txns; i++) {
      if (txn_details_redeemed.get(i).getText().contains("redeemed: -") &&
          !txn_details_redeemed.get(i).getText().contains("earned")) {
        redeemed_count++;
      }
    }
    if (earned_count + redeemed_count == all_count) {
      reporter.reportPassed(pageName, "The total of Earned and Redeemed is equal to that of All");
    }
    else {
      reporter.reportFailed(pageName, "The total of Earned and Redeemed is not equal to that of All - Total Earned : " + earned_count + redeemed_count + " All count : "+all_count );
    }
  }



  /**
   * Clicks on MileageClaim Status Button
   * @author bsingh
   */
  public void clickMileageClaimStatus()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_LI_KF_TRANSACTIONS_MILEAGE_CLAIM_STATUS,
        "Mileage Claim Status Button Couldn't be clicked");
  }

}
