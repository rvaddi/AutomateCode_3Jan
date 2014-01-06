package com.amadeus.selenium.sqmobile.page.faredeal;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CommonFareDealPage extends SqMobileCommonPage{

  public CommonFareDealPage(SeleniumSEPTest test) {
    super(test);
    // TODO Auto-generated constructor stub
  }

  protected static By LOC_LI_FARE_DEALS_Src_City = By.id("srcCity");
  protected static By LOC_LI_FARE_DEALS_Des_City= By.id("destCity");
  protected static By LOC_LI_FARE_DEALS_From_Dropdown = By.id("sourceFilter");
  protected static By LOC_LI_FARE_DEALS_To_DropDown = By.id("destFilter");

  protected static By LOC_LI_FARE_DEALS_OFFER_HEADER = By.cssSelector("#portrait>section>h3");
  protected static By LOC_LI_FARE_DEALS_OFFERS = By.cssSelector("#offerIDN>li");
 // protected static By LOC_LI_FARE_DEALS_OFFER_HEADER = By.cssSelector(".dealspatch");
 // protected static By LOC_LI_FARE_DEALS_OFFERS = By.cssSelector(".dealsListdiv>ul");
  protected static By LOC_LI_FARE_DEALS_Travel_From = By.id("depdate");
  protected static By LOC_LI_FARE_DEALS_Travel_To = By.id("retdate");
  protected static By LOC_Calendar_Close_Button = By.cssSelector("[class*=ui-datepicker-close]");

  // added for calendar UI
  protected static By LOC_CALENDAR_DATE = By.className("ui-state-default");
  protected static By LOC_CALENDAR_MONTH = By.className("ui-datepicker-month");
  protected static By LOC_CALENDAR_YEAR = By.className("ui-datepicker-year");


  /**
   * Select value from the Drop Down
   *
   * @param locator By value of the Drop Down
   * @param value value to be selected
   * @param FieldName Dropdown name
   * @author sankar
   */
  public void SelectFromTo(By locator, String value, String FieldName){
   // WaitUtils.wait(2);
    WebElement element = CheckUtils.getElement(getTest(), locator);

    if (element != null && element.isDisplayed()) {
    FillUtils.selectByValue(getTest(), element, value);
    reporter.reportPassed(FieldName, value+" is selected");
    }
    else {
      reporter.reportFailed(FieldName, FieldName + " Dropdown is not displayed in the page.");
    }

  }

  /**
   * Get the value from Dropdown
   *
   * @param locator By value of the Drop Down
   * @return Returns first selected value from the Dropdown
   * @author sankar
   */
  public String getvalueFromDropdown(By locator){

    WebElement getvalue;
    String value = null;

    WebElement element = CheckUtils.getElement(getTest(), locator);

    if (element != null && element.isDisplayed()) {
    Select option = new Select(element) ;
    getvalue = option.getFirstSelectedOption();
    value = getvalue.getText().trim();
    value = value.replaceAll("^[^\\w]*", "");
    }else {
      reporter.reportFailed("Dropdown", "Dropdown element is not displayed in the page");
    }
    return value;
  }

  /**
   * Get the Date value from DatePicker
   *
   * @param locator By value of the Drop Down
   * @return Returns date Date entered in Date Picker
   * @author sankar
   */
  public Date getvalueFromDatePicker(By locator) throws ParseException{

    Date date = null;

    WebElement Date = CheckUtils.getElement(getTest(), locator);
    if (Date != null && Date.isDisplayed()) {
    String getdate = Date.getText().trim();
    SimpleDateFormat format = new SimpleDateFormat("MMMM dd , yyyy");
    date = format.parse(getdate);
    } else {
      reporter.reportFailed("DatePicker", "DatePicker element is not displayed in the page");
    }
    return date;
  }

  /**
   * Validates Multiple Offers displayed and Deal Header
   *
   * @author Sankar
   */
  public void validateMultipleOffers()  {

    WaitUtils.wait(2);
    WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_OFFER_HEADER, 20);

    WebElement Header = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_OFFER_HEADER);

    if (Header != null && Header.isDisplayed()) {

      String offerHeader = Header.getText();
      List<WebElement> offerList = CheckUtils.getElements(getTest(), LOC_LI_FARE_DEALS_OFFERS);

      if (!(offerHeader.contains(offerList.size() + " Deals offers"))) {
        reporter.reportFailed("Deal Header", "Offers Header is Not displayed based on Filtering. (Deal Header: " +
            offerHeader + " and Total Offers: " + offerList.size() + ")");
      }
      else {
        reporter.reportPassed("Deal Header", "Offers Header is displayed based on Filtering. (Deal Header: " +
            offerHeader + " and Total Offers: " + offerList.size() + ")");
      }
    }else{
      reporter.reportFailed("Deal Header", "Deal Header element is not displayed in the page");
    }
  }

  /**
   * Verify the Drop down Value
   *
   * @param dropdown webelement of the Drop Down
   * @param value value to be verified in Dropdown
   * @param FieldName Dropdown name
   * @author sankar
   */
  public void verifyDropDownvalue(WebElement dropdown,String value, String FieldName){

    String defaultvalue ;
    WebElement getvalue;

    if (dropdown != null && dropdown.isDisplayed()) {

      Select option = new Select(dropdown);
      getvalue = option.getFirstSelectedOption();
      defaultvalue = getvalue.getText();

      if (defaultvalue.equalsIgnoreCase(value)) {

        reporter.reportPassed(FieldName, "Value in the Drop down is displayed properly. (" + defaultvalue + ")");
      }
      else {
        reporter.reportFailed(FieldName, "Value in the Drop down is not displayed properly. (" + defaultvalue + ")");
      }
    }else{
      reporter.reportFailed("Dropdown", "Dropdown element is not displayed in the page");
    }
  }

  /**
   * Verify DatePicker value
   *
   * @param dropdown webelement of the Date Picker
   * @param value value to be verified in DatePicker
   * @param FieldName DatePicker name
   * @author Sankar
   */
  public void verifyDatePickervalue(WebElement date,String value, String FieldName){

    String defaultvalue ;

    if (date != null && date.isDisplayed()) {
      defaultvalue = date.getText().trim();

      if (defaultvalue.equalsIgnoreCase(value)) {

        reporter.reportPassed(FieldName, "Value in the Date picker is displayed properly. (" + defaultvalue + ")");
      }
      else {
        reporter.reportFailed(FieldName, "Value in the Date picker is not displayed properly. (" + defaultvalue + ")");
      }
    }else{
      reporter.reportFailed("DatePicker", "DatePicker element is not displayed in the page");
    }
  }

  /**
   * Get Default DatePicker value
   *
   * @param Datefield DatePicker name
   * @author Sankar
   */
  public String GetDefaultdate(String Datefield){

    String defaultdate;

    Calendar calender = GregorianCalendar.getInstance();
    calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), calender.get(calender.DATE), 0, 0, 0);

    if(Datefield.equalsIgnoreCase("DepDate")){
      defaultdate = new SimpleDateFormat("MMMM dd , yyyy").format(calender.getTime());

    }else if(Datefield.equalsIgnoreCase("RetDate")){
      calender.add(calender.YEAR, 1);
      calender.add(calender.DAY_OF_YEAR, -1);
      defaultdate = new SimpleDateFormat("MMMM dd , yyyy").format(calender.getTime());
    } else {
      defaultdate = defaultdate = new SimpleDateFormat("MMMM dd , yyyy").format(calender.getTime());
      reporter.reportFailed("Input Value", "Field name is not proper for DatePicker filter option");
    }
    return defaultdate;
  }

  /**
   * Select Past date from DatePicker
   *
   * @param Datefield DatePicker name
   * @throws Exception
   * @author Sankar
   */
  public void selectpastdate(String FieldName) throws Exception {

    WaitUtils.waitForElementPresent(getTest(), LOC_CALENDAR_YEAR, 20);
    boolean DateDisabled;
    String Date;
    HashMap<String, Object> GetDate = new HashMap<String, Object>();

    CommonUtils utils = new CommonUtils(getTest());
    GetDate = utils.VerifyDateDisabledInCalendar(-1, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);

    DateDisabled = (Boolean)GetDate.get("IsDateDisable");
    Date = (String)GetDate.get("Date");

    if (DateDisabled) {
      reporter.reportPassed(FieldName, "Not able to select Past Date in calendar. (Date: " + Date + ")");
   //   ClickUtils.click(getTest(), LOC_Calendar_Close_Button);
      ClickUtils.clickButtonOrFail(getTest(), LOC_Calendar_Close_Button, "Close button is not clicked in calendar page", "Close button is clicked in calendar page" );
    }
    else {
      reporter.reportFailed(FieldName, "Able to select Past Date in calendar. (Date: " + Date + ")");
     // ClickUtils.click(getTest(), LOC_Calendar_Close_Button);
      ClickUtils.clickButtonOrFail(getTest(), LOC_Calendar_Close_Button, "Close button is not clicked in calendar page", "Close button is clicked in calendar page" );
    }
  }

  /**
   * Change To value Drop down in filter options
   *
   * @throws IOException
   * @author Sankar
   */
  public void ChangeTovalue() throws IOException{

    SelectFromTo(LOC_LI_FARE_DEALS_From_Dropdown, "ALL","From DropDown");
    SelectFromTo(LOC_LI_FARE_DEALS_To_DropDown, getValue("To"),"To DropDown");

    verifyFromToDealOffer(LOC_LI_FARE_DEALS_OFFERS,LOC_LI_FARE_DEALS_From_Dropdown,LOC_LI_FARE_DEALS_To_DropDown,"To Value Changed");
    validateMultipleOffers();
  }

  /**
   * Change From value Drop down in filter options
   *
   * @throws IOException
   * @author Sankar
   */
  public void ChangeFromValue() throws IOException{

    SelectFromTo(LOC_LI_FARE_DEALS_From_Dropdown, getValue("From"),"From DropDown");
    SelectFromTo(LOC_LI_FARE_DEALS_To_DropDown, "ALL","To DropDown");

    verifyFromToDealOffer(LOC_LI_FARE_DEALS_OFFERS,LOC_LI_FARE_DEALS_From_Dropdown,LOC_LI_FARE_DEALS_To_DropDown,"From Value Changed");
    validateMultipleOffers();
  }

  /**
   * Change Both From and To value Drop down in filter options
   *
   * @throws IOException
   * @author Sankar
   */
  public void ChangeBothFromToValue() throws IOException{

    SelectFromTo(LOC_LI_FARE_DEALS_From_Dropdown, getValue("From"),"From DropDown");
    SelectFromTo(LOC_LI_FARE_DEALS_To_DropDown, getValue("To"),"To DropDown");

    verifyFromToDealOffer(LOC_LI_FARE_DEALS_OFFERS,LOC_LI_FARE_DEALS_From_Dropdown,LOC_LI_FARE_DEALS_To_DropDown,"Both From and To Changed");
    validateMultipleOffers();
  }

  /**
   * Verify Deal offer for FromTo Filter option
   *
   * @param offer By value of the offer list
   * @param FromCity By value of the From city
   * @param ToCity By value of the To city
   * @param FilterValue Value to be selected from Dropdown
   * @author Sankar
   */
  public void verifyFromToDealOffer(By offer,By FromCity,By ToCity,String FilterValue){

    String orgin;
    String destination;
    boolean deal = true;

    WaitUtils.wait(2);
    WaitUtils.waitForElementPresent(getTest(), offer, 10);

    List<WebElement> offerList= CheckUtils.getElements(getTest(), offer);

    String SourceCity = getvalueFromDropdown(FromCity);
    String DestinationCity = getvalueFromDropdown(ToCity);

    if(!(offerList==null)){
      for(WebElement list:offerList){
        List<WebElement> Route = CheckUtils.getElements(getTest(), list, By.className("route"));
        for(WebElement origins: Route){
          List<WebElement> Destinations = CheckUtils.getElements(getTest(), origins, By.className("city"));
          for(int i=0;i<Destinations.size();i=i+2){

           orgin = Destinations.get(i).getText().trim();
           destination = Destinations.get(i+1).getText().trim();

            if(!((SourceCity.equalsIgnoreCase("Anywhere")||SourceCity.equalsIgnoreCase(orgin)) &&
                (DestinationCity.equalsIgnoreCase("Anywhere")||DestinationCity.equalsIgnoreCase(destination)))){
              deal = false;
            }

          }
        }
      }
      if(deal){
        reporter.reportPassed(FilterValue, "Deal offer is displayed based on Filter option. (Source City: "+SourceCity+" and Destination City:"+DestinationCity+")");
      } else {
        reporter.reportFailed(FilterValue, "Deal offer is not displayed based on Filter option. (Source City: "+SourceCity+" and Destination City:"+DestinationCity+")");
      }
    } else {
      reporter.reportWarning(FilterValue, "Deal offer is not available for the selected Destinations. (Source City: "+SourceCity+" and Destination City:"+DestinationCity+")");
    }
  }

  /**
   * Verify Deal offer for Travel Period Filter option
   *
   * @param offer By value of the offer list
   * @param Depdate By value of the Departure Date picker
   * @param Retdate By value of the Departure Date picker
   * @param FilterValue Value to be selected from Dropdown
   * @author Sankar
   */
  public void verifyTravelPeriodDealOffer(By offer,By Depdate,By Retdate,String FilterValue) throws ParseException{

    Date Departure;
    Date Arrival;
    boolean deal = true;

    WaitUtils.wait(2);
    WaitUtils.waitForElementPresent(getTest(), offer, 10);

    List<WebElement> offerList= CheckUtils.getElements(getTest(), offer);

    Date TravelFrom = getvalueFromDatePicker(Depdate);
    Date TravelTo = getvalueFromDatePicker(Retdate);

    if(TravelFrom.compareTo(TravelTo)<0 || TravelFrom.compareTo(TravelTo)==0){
    if(!(offerList==null)){
      for(WebElement list:offerList){
          List<WebElement> Route = CheckUtils.getElements(getTest(), list, By.className("period"));
        for(WebElement origins: Route){
            List<WebElement> Destinations = CheckUtils.getElements(getTest(), origins, By.className("time"));
          for(int i=0;i<Destinations.size();i=i+2){

            Departure = new SimpleDateFormat("dd MMM yyyy").parse(Destinations.get(i).getText().trim());
            Arrival = new SimpleDateFormat("dd MMM yyyy").parse(Destinations.get(i+1).getText().trim());

            if(!(((TravelFrom.compareTo(Departure)>0||TravelFrom.compareTo(Departure)==0) || TravelTo.compareTo(Departure)>0||TravelTo.compareTo(Departure)==0)&&
                ((TravelFrom.compareTo(Arrival)<0||TravelFrom.compareTo(Arrival)==0) || TravelTo.compareTo(Arrival)<0||TravelTo.compareTo(Arrival)==0))){
              deal = false;
            }
          }
        }
      }
      if(deal){
        reporter.reportPassed(FilterValue, "Deal offer is displayed based on Filter option. (Travel Between "+TravelFrom+" and "+TravelTo+")");
      } else {
        reporter.reportFailed(FilterValue, "Deal offer is not displayed based on Filter option. (Travel Between "+TravelFrom+" and "+TravelTo+")");
      }
    } else {
      reporter.reportWarning(FilterValue, "Deal offer is not available for the selected Travel Period.. (Travel Between "+TravelFrom+" and "+TravelTo+")");
    }
   } else {
     reporter.reportWarning(FilterValue, "Travel From date is should be less than travel To date. (From Date: "+TravelFrom+" , To Date: "+TravelTo+")");
   }
  }


  /**
   * Fills the departure date using calendar
   *
   * @param Gap between current date and the date to be selected
   * @return Returns selected DepartureDate from DatePicker
   * @throws Exception
   * @author Sankar
   */
  public String fillDeptDateUsingCalendar(int noOfDays) throws Exception{
    String date= null;
    WebElement  deptDateElt = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_From);
    if (deptDateElt != null && deptDateElt.isDisplayed()) {
      ClickUtils.click(getTest(), deptDateElt);
      CommonUtils utils = new CommonUtils(getTest());
      date =  utils.fillDateUsingCalendar(noOfDays, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    }else{
      // reporter.reportFailed("Departure Date", "Departure Date element is not displayed in Fare Deal page.");
      reporter.fail("Departure Date element is not displayed in Fare Deal page.");
    }
    return date;
  }


  /**
   * Fills the departure date using calendar
   *
   * @param Gap between current date and the date to be selected
   * @return Returns selected ReturnDate from DatePicker
   * @throws Exception
   * @author Sankar
   */
  public String fillReturnDateUsingCalendar(int noOfDays) throws Exception{
    String date = null;
    WebElement  retDateElt = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_To);
    if (retDateElt != null && retDateElt.isDisplayed()) {
      ClickUtils.click(getTest(), retDateElt);
      CommonUtils utils = new CommonUtils(getTest());
      date = utils.fillDateUsingCalendar(noOfDays, LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    }else{
      // reporter.reportFailed("Return Date", "Return Date element is not displayed in Fare Deal page.");
      reporter.fail("Return Date element is not displayed in Fare Deal page.");
    }
    return date;
  }

}
