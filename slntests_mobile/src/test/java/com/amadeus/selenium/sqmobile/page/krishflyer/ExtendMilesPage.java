package com.amadeus.selenium.sqmobile.page.krishflyer;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class ExtendMilesPage extends CommonKFServicesPage {

  public ExtendMilesPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_KF_MILES_EXTD_MILES_MONTHS_CONFIRM, 30);
    if(elementPresent){
      reporter.reportPassed(getName(), "ExtendMiles Page loaded");
    }else{
      reporter.fail("ExtendMiles Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "SQ Mobile- Extend Miles");
    reporter.report("CHECKPOINT", "ExtendMiles PAGE is DISPLAYED");
  }

  //LOCATORS - ExtendMiles------------------------------------------------------

  protected static By LOC_LI_KF_MILES_EXTD_MILES_MONTHS_NAMES = By
  .cssSelector("[id*=_kfcontent]>div>table>tbody>tr>th");
  protected static By LOC_LI_KF_MILES_EXTD_MILES_TOTAL_FEES = By.id("totalFees");
  protected static By LOC_LI_KF_MILES_EXTD_MILES_TOTAL_MILES_EXPIRING = By.className("extendRecap");
  protected static By LOC_LI_KF_MILES_EXTD_MILES_TOTAL_MILES = By.id("totalMiles");
  protected static By LOC_LI_KF_TRANSACTIONS_MILES_EXTN_DETAILS = By.cssSelector("td[id ^='me']");
  protected static By LOC_LI_KF_TRANSACTIONS_MILES_EXTN_BUTTONS = By.cssSelector("[id ^='muBut']");
  protected static By LOC_BUTTON_KF_MILES_EXTD_MILES_MONTHS_CONFIRM = By.className("submitnoPadding");
  protected static By LOC_LI_KF_MILES_EXTD_MILES = By.partialLinkText("Extend");

  String pageName = "MileExtension Page";
  //----------------------------------------------------------------------------


  /**
   * Validates MileExtension
   * @throws Exception
   * @author bsingh
   */
  public void validateMileExtension() throws Exception {

    int miles_value[] = new int[6];
    int numOfExtendButton = 0;
    List<WebElement> months = CheckUtils.getElements(getTest(), LOC_LI_KF_MILES_EXTD_MILES_MONTHS_NAMES);
    WebElement totalFees = CheckUtils.getElement(getTest(), LOC_LI_KF_MILES_EXTD_MILES_TOTAL_FEES);
    WebElement totalMiles = CheckUtils.getElement(getTest(), LOC_LI_KF_MILES_EXTD_MILES_TOTAL_MILES);
    List<WebElement> milesExtnElemnts = CheckUtils.getElements(getTest(), LOC_LI_KF_TRANSACTIONS_MILES_EXTN_DETAILS);
    List<WebElement> extndButtons = CheckUtils.getElements(getTest(), LOC_LI_KF_TRANSACTIONS_MILES_EXTN_BUTTONS);

    miles_value = retrievingMileExtensionValues();
    for (int i = 0; i < 6; i++) {
      if (miles_value[i] > 0) {
        numOfExtendButton++;
      }
    }

    if (months.size() == 18) {
      reporter.reportPassed(pageName, "Months Displayed properly");
    }
    else {
      reporter.reportFailed("MonthSize : ", months.size()+ " not equal to 18");
    }

    if (extndButtons.size() == numOfExtendButton) {
      reporter.reportPassed(pageName, "Extend Buttons are displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "Extend Buttons are not displayed properly");
    }

    if (milesExtnElemnts.size() == 6) {
      reporter.reportPassed(pageName, "Monthwise Extend Miles are displayed properly");
    }
    else {
      reporter.reportFailed(pageName, "Monthwise Extend  Miles are not displayed properly");
    }

    if (Integer.parseInt(totalMiles.getText()) == 0) {
      reporter.reportPassed("Total Miles : ", totalMiles.getText());
    }
    else {
      reporter.reportFailed("Total Miles : ", totalMiles.getText());
    }

    if (Integer.parseInt(totalFees.getText()) == 0) {
      reporter.reportPassed("Total Fees : ", totalFees.getText());
    }
    else {
      reporter.reportFailed("Total Fees : ",  totalFees.getText());
    }
  }


  /**
   * Retrieves the values in MileExtension for each month
   * @return array of integer values
   * @author bsingh
   */
  public int[] retrievingMileExtensionValues() {

    int miles_value[];
    String miles;
    List<WebElement> miles_extn_elemnts = CheckUtils.getElements(getTest(), LOC_LI_KF_TRANSACTIONS_MILES_EXTN_DETAILS);
    int num_of_exnts = miles_extn_elemnts.size();
    miles_value = new int[num_of_exnts];
    for (int i = 0; i < num_of_exnts; i++) {
      miles = miles_extn_elemnts.get(i).getText();
      if (miles.contains(",")) {
        miles = miles.replace(",", "");
      }
      miles_value[i] = Integer.parseInt(miles);
    }

    return miles_value;

  }



  /**
   * ClicksExtendMiles
   * @author bsingh
   */
  public void clickExtendMiles() {
    ClickUtils.clickButtonOrFail(getTest(), LOC_LI_KF_MILES_EXTD_MILES, "Extend Mile Button couldn't be clicked", "Miles Button clicked successfully");

  }


}

