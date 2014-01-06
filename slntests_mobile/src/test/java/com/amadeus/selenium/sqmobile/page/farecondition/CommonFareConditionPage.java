package com.amadeus.selenium.sqmobile.page.farecondition;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;

public class CommonFareConditionPage extends SqMobileCommonPage {

  public CommonFareConditionPage(SeleniumSEPTest test) {
    super(test);
    // TODO Auto-generated constructor stub
  }

  protected static By LOC_FareDeal_Flexible_Date= By.id("DATE_RANGE_VALUE_1");
  protected static By LOC_FareDeal_Search_Button= By.cssSelector(".validation");
  protected static By LOC_FareDeal_Adult= By.id("FIELD_ADT_NUMBER");
  protected static By LOC_FareDeal_Child= By.id("FIELD_CHD_NUMBER");
  protected static By LOC_FareDeal_MSG_Warning= By.cssSelector(".msg.warning>ul");
  protected static By LOC_FareContion_Tab = By.cssSelector("*.fare-cond");
  protected static By LOC_FareDeal_Flexible_Date_label = By.cssSelector("[for=DATE_RANGE_VALUE_1]");

  // added for calendar UI
  protected static By LOC_CALENDAR_DATE= By.cssSelector(".ui-state-default");
  protected static By LOC_CALENDAR_MONTH= By.className("ui-datepicker-month");
  protected static By LOC_CALENDAR_YEAR= By.className("ui-datepicker-year");
  protected static By LOC_LI_FARE_DEALS_DepDate = By.id("depdate");
  protected static By LOC_LI_FARE_DEALS_RetDate = By.id("retdate");

  String pageName = "Fare Condition Page";


  /**
   * Fills the departure date using calendar
   * @throws Exception
   * @author Sankar
   */
  public String fillDeptDateUsingCalendar(int noOfDays) throws Exception{

    String date= null;
    WebElement  deptDateElt = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_DepDate);

    if(!(deptDateElt==null)){
      ClickUtils.click(getTest(), deptDateElt);
      CommonUtils utils = new CommonUtils(getTest());
      date =  utils.fillDateUsingCalendar(noOfDays, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
      reporter.reportPassed(pageName, "Departure Date filled successfully using calendar. (Date: " + date + ")");
    }else{
      reporter.reportFailed(pageName, "Departure Date couldn't be filled successfully using calendar ");
    }
    return date;
  }


  /**
   * Fills the Return date using calendar
   * @throws Exception
   * @author Sankar
   */
  public String fillReturnDateUsingCalendar(int noOfDays) throws Exception{

    String date = null;
    WebElement  retDateElt = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_RetDate);

    if(!(retDateElt==null)){
      ClickUtils.click(getTest(), retDateElt);
      CommonUtils utils = new CommonUtils(getTest());
      date = utils.fillDateUsingCalendar(noOfDays, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
      reporter.reportPassed(pageName, "Return Date filled successfully using calendar. (Date: " + date + ")");
    }else{
      reporter.reportFailed(pageName, "Return Date couldn't be filled successfully using calendar ");
    }
    return date;
  }

  /**
   * Click Search Button
   *
   * @author Sankar
   */

  public void clickSearchButton() {

    List<WebElement> submitButtons = CheckUtils.getElements(getTest(), LOC_FareDeal_Search_Button);

    if (!(submitButtons == null)) {
      for (WebElement tempButoon : submitButtons) {
        if (tempButoon.getText().trim().toLowerCase().contains("search")) {
          ClickUtils.clickButtonOrFail(getTest(), tempButoon, "Search Button could not clicked","Search Button is clicked on Fare Condition Page");
          // waitForOverlayLoading(60);
          break;
        }
      }
    }else{
      reporter.reportFailed(pageName, "Search Button element is not displayed in the page");
    }
  }

  /**
   * Click flexible date check box
   *
   * @throws IOException
   * @author Sankar
   */
  public void ClickFlexibleDate() throws IOException {

      ClickUtils.clickButtonOrFail(getTest(), LOC_FareDeal_Flexible_Date, "Flexible Dates is NOT Selected","Flexible Date is Selected successfully");
  }

  /**
   * Fills Adults from the dropdown
   *
   * @param adult
   *          to be selected from the dropdown
   * @author Sankar
   */
   public void FillAdults(String adult){

     WebElement adults = CheckUtils.getElement(getTest(), LOC_FareDeal_Adult);

    if (!(adults == null) && adults.isDisplayed()) {
      FillUtils.selectByValue(getTest(), LOC_FareDeal_Adult, adult);
      reporter.reportPassed("Adults Dropdown", "Adults is selected from the dropdown. (Adult: "+adult+")");
    } else {
       reporter.reportFailed("Adults Dropdown", "Adults Dropdown element is not displayed in the page");
     }
   }

  /**
   * Fills Children from the dropdown
   *
   * @param children
   *          to be selected from the dropdown
   * @author Sankar
   */
   public void FillChildren(String children){

     WebElement child = CheckUtils.getElement(getTest(), LOC_FareDeal_Child);

    if (!(child == null) && child.isDisplayed()) {
      FillUtils.selectByValue(getTest(), LOC_FareDeal_Child, children);
      reporter.reportPassed("Children Dropdown", "Children is selected from the dropdown. (Children: "+children+")");
    }else {
       reporter.reportFailed("Children Dropdown", "Children Dropdown element is not displayed in the page");
     }
   }

  /**
   * Validate the error message
   *
   * @author Sankar
   */
  public void ValidateErrorMessage(String ErrorMsg, String Restriction, String MinValue, String MaxValue)
      throws IOException {

    WebElement MsgWarning = CheckUtils.getElement(getTest(), LOC_FareDeal_MSG_Warning);
    String ErrMsg = "";
    boolean MsgDisplayed = false;

    if (!(MsgWarning == null) && MsgWarning.isDisplayed()) {
       WebElement ErrList = CheckUtils.getElement(getTest(), MsgWarning, By.className("lstErr"));
       if(ErrList==null){
         ErrMsg = MsgWarning.getText().trim();
         if(!ErrMsg.isEmpty()){
          MsgDisplayed = VerifyErrorMessage(ErrorMsg, MsgDisplayed, ErrMsg);
         }
       } else {
        List<WebElement> Errors = CheckUtils.getElements(getTest(), ErrList, By.tagName("li"));
         for(WebElement list:Errors){
           ErrMsg = list.getText().trim();
          if (!ErrMsg.isEmpty()) {
            MsgDisplayed = VerifyErrorMessage(ErrorMsg, MsgDisplayed, ErrMsg);

            if (MsgDisplayed) {
              break;
            }
           }
         }
      }

      if (MsgDisplayed) {
        reporter.reportPassed(Restriction, "Error message is displayed properly. (Error Message: " + ErrMsg + "). [" +
             MinValue + ": " + getValue(MinValue) + ", " + MaxValue + ": " + getValue(MaxValue) + "]");
      }
      else {
        reporter.reportFailed(Restriction, "Error message is NOT displayed properly");
      }
    }
    else {
      reporter.reportFailed(Restriction, "Error message is NOT displayed in the Fare Condition page. [" +
             MinValue + ": " + getValue(MinValue) + ", " + MaxValue + ": " + getValue(MaxValue) + "]");
    }
   }

  /**
   * Verify the error message
   * @return true if the error message is displayed
   * @author Sankar
   */
  public boolean VerifyErrorMessage(String ErrorMsg, boolean MsgDisplayed, String ErrMsg) {

    if (ErrorMsg.equalsIgnoreCase("Max Pax")) {
      if (ErrMsg.matches("Maximum allowed travelers is \\d.*")) {
        MsgDisplayed = true;
      }
    }
    else if (ErrorMsg.equalsIgnoreCase("Min Pax")) {
      if (ErrMsg.matches("Minimum allowed travelers is \\d.*")) {
        MsgDisplayed = true;
      }
    }
    else if (ErrorMsg.equalsIgnoreCase("Max Stay")) {
      if (ErrMsg.matches("The selected travel period exceeds the maximum stay period of \\d.+ \\w.*")) {
        MsgDisplayed = true;
      }
    }
    else if (ErrorMsg.equalsIgnoreCase("Min Stay")) {
      if (ErrMsg.matches("The selected travel period exceeds the minimum stay period of \\d.+ \\w.*")) {
        MsgDisplayed = true;
      }
    }
    return MsgDisplayed;
  }

  /**
   * Click Fare condition tab
   *
   * @author Sankar
   */
  public void ClickFareConditionsTab() {

    ClickUtils.clickButtonOrFail(getTest(), LOC_FareContion_Tab, "Fare Conditions Tab is NOT Clicked","Fare Conditions Tab is clicked successfully");
  }

  /**
   * Validate dates displayed in the calendar
   * @param IsDateEnabled to check whether dates should be enabled or disabled
   * @author Sankar
   */
  public void ValidateCalendarDates(String IsDateEnabled) throws Exception {

    boolean IsDepDateDisabled = false;
    boolean IsRetDateDisabled = false;

    HashMap<String, Object> GetDate = new HashMap<String, Object>();

    WebElement  deptDateElt = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_DepDate);
    WebElement retDateElt = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_RetDate);

    if(getValue("Trip Type").equalsIgnoreCase("OW")) {
      GetDate = GetDisabledDateStatus(deptDateElt, Integer.parseInt(getValue("Validate Date")));
      IsDepDateDisabled = (Boolean)GetDate.get("IsDateDisable");
    }else if(getValue("Trip Type").equalsIgnoreCase("RT")){
      GetDate = GetDisabledDateStatus(deptDateElt, Integer.parseInt(getValue("Validate Date")));
      IsDepDateDisabled = (Boolean)GetDate.get("IsDateDisable");

      GetDate = GetDisabledDateStatus(retDateElt, Integer.parseInt(getValue("Validate Date")));
      IsRetDateDisabled = (Boolean)GetDate.get("IsDateDisable");
    }

    if (IsDateEnabled.equalsIgnoreCase("Enabled")) {
      if (!IsDepDateDisabled) {
        reporter.reportPassed("Departure Calendar Date", "Date is Enabled in the calendar");
      }
      else {
        reporter.reportFailed("Departure Calendar Date", "Date is Enabled in the calendar");
      }
    }
    else {
      if (!IsDepDateDisabled) {
        reporter.reportPassed("Departure Calendar Date", "Date is Enabled in the calendar");
      }
      else {
        reporter.reportFailed("Departure Calendar Date", "Date is Enabled in the calendar");
      }
      if (!IsRetDateDisabled) {
        reporter.reportPassed("Return Calendar Date", "Date is Enabled in the calendar");
      }
      else {
        reporter.reportFailed("Return Calendar Date", "Date is Enabled in the calendar");
      }
    }
  }

  /**
   * Get the Disabled date status
   * @param noOfDays Gap between present date and date to be checked
   * @author Sankar
   */
  public HashMap<String, Object> GetDisabledDateStatus(WebElement DateElt, int noOfDays) throws Exception {

    HashMap<String, Object> GetDate = new HashMap<String, Object>();

    if (!(DateElt == null) && DateElt.isDisplayed()) {
      ClickUtils.click(getTest(), DateElt);
      CommonUtils utils = new CommonUtils(getTest());
      GetDate = utils.VerifyDateDisabledInCalendar(noOfDays, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    }
    else {
      reporter.reportFailed(pageName, "Date Picker element is not displayed in the page");
    }
    return GetDate;

  }
}
