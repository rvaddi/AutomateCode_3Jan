package com.amadeus.selenium.sqmobile.page.faredeal;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class FareDealsPage extends CommonFareDealPage{

  public FareDealsPage(SeleniumSEPTest test) {
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_LIST_VIEW, 120);
    if(elementPresent){
      reporter.reportPassed(pageName, "FareDeals Page loaded");
    }else{
      reporter.fail("FareDeals Page NOT loaded ");
    }

    String pageName = "FareDealPage";
  }

  //LOCATORS - HOME PAGE---------------------------------------------------------
  protected static By LOC_LI_FARE_DEALS_LIST_VIEW = By.className("listView");
  protected static By LOC_LI_FARE_DEALS_MAP_VIEW = By.className("mapView");
  protected static By LOC_LI_FARE_DEALS_COUNTRY = By.id("countryBox");
  protected static By LOC_LI_FARE_DEALS_ZOOM_BAR = By.cssSelector(".gmnoprint>div>img");
  protected static By LOC_LI_FARE_DEALS_PRICE_FILTER = By.id("priceLi");
  protected static By LOC_LI_FARE_DEALS_MAP_VIEW_FLIGHTS = By.tagName("p");
  protected static By LOC_LI_FARE_DEALS_FROM_TO_FILTER = By.id("destinationLi");
  protected static By LOC_LI_FARE_DEALS_TRAVEL_PERIOD_FILTER = By.id("travelPeriodLi");
  protected static By LOC_LI_FARE_DEALS_ROUTE = By.className("route");
  protected static By LOC_LI_FARE_DEALS_ORIGIN = By.cssSelector("[id^='origin']");
  protected static By LOC_LI_FARE_DEALS_DESTINATION = By.cssSelector("[id^='destination']");
  protected static By BUTTON_TRAVEL_PERIOD_DEPARTURE_CALENDAR= By.id("depdate");
  protected static By BUTTON_TRAVEL_PERIOD_RETURN_CALENDAR= By.id("retdate");


  String pageName = "Fare Deals Page";
  //-----------------------------------------------------------------------------


  /**
   * Validates the FareDeal Page
   * @author bsingh
   */
  public void validateFareDealsPage()  {

    WebElement ListView = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_LIST_VIEW);
    if (ListView == null || !ListView.isDisplayed()) {
      reporter.reportFailed("List View Tab", "List View is not displayed");
    }
    else {
      reporter.reportPassed("List View Tab", "List View is displayed");
    }

    WebElement MapView = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_MAP_VIEW);
    if (MapView == null || !MapView.isDisplayed()) {
      reporter.reportFailed("Map View Tab", "Map View is not displayed");
    }
    else {
      reporter.reportPassed("Map View Tab", "Map View is displayed");
    }

    WebElement Country = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_COUNTRY);
    if (Country == null || !Country.isDisplayed()) {
      reporter.reportFailed("Country Dropdown", "Dropdown for selecting Country is not displayed");
    }
    else {
      reporter.reportPassed("Country Dropdown", "Dropdown is displayed");
    }
  }


  /**
   * Validates Deals for specific number of Offers
   * @param numOfOffers the number of offers for which deals are to be validated
   * @author bsingh
   */
  public void validateSpecificNumOfOffers(int numOfOffers)  {

    WebElement Header = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_OFFER_HEADER);

    if (!(Header == null)) {

      String offerHeader = Header.getText();
      List<WebElement> offerList = CheckUtils.getElements(getTest(), LOC_LI_FARE_DEALS_OFFERS);

      if (!(offerHeader.equals(numOfOffers + " Deals offers") || offerHeader.contains(offerList.size() +
          " Deals offers"))) {
        reporter.reportFailed("Deal Header", "Number of Offers displayed in Header are - " + offerList.size() +
            " instead of-  " + numOfOffers);
      }
      else {
        reporter.reportPassed("Deal Header", "Header for " + offerList.size() + " Offers is displayed properly");
      }

      if (offerList.size() != new Integer(offerHeader.split(" ")[0])) {
        reporter.reportFailed("Deal Header", "The number of deals dispalyed is not equal to " + numOfOffers + " but " +
            offerList.size());
      }
      else {
        reporter.reportPassed("Deal Header", "Number of deals is displayed properly for deals : " + offerList.size());
      }
    }else{
      reporter.reportFailed("Deal Header", "Deal Header element is not displayed in the page");
    }
  }


  /**
   * Selects Country as per the given argument
   * @param country Name of the country which is to be selected
   * @author bsingh
   */
  public void selectCountry(String country)  {
    FillUtils.selectByValue(getTest(), LOC_LI_FARE_DEALS_COUNTRY, country);
    reporter.reportPassed("Country DropDown", "Country: "+country+" is selected for FareDeals");

  }


  /**
   * Selects Offer as per the given  Country , OriginCountry and DestinationCountry
   * @param country For which offer is to be selected
   * @param originCountry where the offer is to be selected from
   * @param destinationCountry
   * @author bsingh
   */
  public void selectOffer(String country, String originCountry , String destinationCountry)  {
    selectCountry(country);
    List<WebElement> flightList = CheckUtils.getElements(getTest(), LOC_LI_FARE_DEALS_OFFERS);
    for (WebElement flight : flightList) {
      WebElement flightDetails = CheckUtils.getElement(getTest(), flight, LOC_LI_FARE_DEALS_ORIGIN);
      if (flightDetails != null && flightDetails.isDisplayed()) {
        if (flightDetails.getText().equalsIgnoreCase(originCountry) &&
            flightDetails.getText().equalsIgnoreCase(destinationCountry)) {
          ClickUtils.clickElement(getTest(), flight);
          reporter.reportPassed(getName(), "Country is selected for FareDeals");
          break;
        }
      }
    }
  }



  /**
   * Clicks MapView Tab
   * @author bsingh
   */
  public void clickMapView()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_LI_FARE_DEALS_MAP_VIEW, "Map View Couldn't be clicked`", "Map View Clicked Successfully");
  }


  /**
   * Searches Flight while the MapView is selected
   * @param from
   * @param to
   * @author bsingh
   */
  public void searchFlightFromMapView(String from, String to)  {
    List<WebElement> flightList = CheckUtils.getElements(getTest(), LOC_LI_FARE_DEALS_MAP_VIEW_FLIGHTS);
    boolean flightPresent = false;
    for(WebElement  flight: flightList) {
      flightPresent=subStringsInOrderPresent(from, to, flight.getText());
      if(flightPresent) {
        flight.click();
        break;
      }
    }
  }


  /**
   * Clicks to ZoomIn
   * @author bsingh
   */
  public void clickZoomIn()  {
    List<WebElement> zoomBar = CheckUtils.getElements(getTest(), LOC_LI_FARE_DEALS_ZOOM_BAR);
    ClickUtils.clickButtonOrFail(getTest(), zoomBar.get(5), "ZoomIn couldn't be pressed",
    "Map Zoomed - In successfully");
  }


  /**
   * Clicks to ZoomOut
   * @author bsingh
   */
  public void clickZoomOut()  {
    List<WebElement> zoomBar = CheckUtils.getElements(getTest(), LOC_LI_FARE_DEALS_ZOOM_BAR);
    ClickUtils.clickButtonOrFail(getTest(), zoomBar.get(3), "Zoom-Out couldn't be pressed",
    "Map Zoomed -Out successfully");
  }



  /**
   * To check whether subString1 and subString2 are present in mainString having subString2 after subString1
   * @param subString1
   * @param subString2
   * @param mainString
   * @return boolean true if subString2 is present after subString1 in mainString
   * @author bsingh
   */
  public boolean subStringsInOrderPresent(String subString1 , String subString2 , String mainString)  {
    boolean stringPresent = false;
    int lastIndexOfSub1=0;
    if(mainString.contains(subString1)){
      lastIndexOfSub1= mainString.lastIndexOf(subString1, 0);
    }
    if(mainString.contains(subString2)){
      lastIndexOfSub1= mainString.lastIndexOf(subString2, lastIndexOfSub1);
      stringPresent=  true;
    }
    return stringPresent;

  }


  /**
   * Clicks Price Filter
   * @author bsingh
   */
  public void clickPriceFilter()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_LI_FARE_DEALS_PRICE_FILTER, "Price Button couldn't be clicked",
    "Price Button clicked");
  }



  /**
   * Clicks FromTo Filter
   * @author bsingh
   */
  public void clickFromToFilter()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_LI_FARE_DEALS_FROM_TO_FILTER, "From-To Filter couldn't be clicked",
    "From-To Filter clicked");
  }


  /**
   * Clicks TravelPeriod
   * @author bsingh
   */
  public void clickTravelPeriod()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_LI_FARE_DEALS_TRAVEL_PERIOD_FILTER,
        "Travel Period Filter couldn't be clicked",
    "Travel Period Filter is clicked");
  }



  /**
   * Selects the TravelPeriod
   * @param fromNumOfDays
   * @param toNumOfDays
   * @throws Exception
   * @
   */
  public void selectTravelPeroid(int fromNumOfDays, String toNumOfDays) throws Exception  {

    CommonUtils utils = new CommonUtils(getTest());
    ClickUtils.clickButtonOrFail(getTest(), BUTTON_TRAVEL_PERIOD_DEPARTURE_CALENDAR, "Deparutre Calendar couldn't be clicked", "Departure Calendar clicked successfully");
    String deptDate = utils.fillDateUsingCalendar(fromNumOfDays , LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    updateValue("DEPARTURE DATE", deptDate);

    ClickUtils.clickButtonOrFail(getTest(), BUTTON_TRAVEL_PERIOD_DEPARTURE_CALENDAR, "Retrun Calendar couldn't be clicked", "Return Calendar clicked successfully");
    String retDate = utils.fillDateUsingCalendar(fromNumOfDays , LOC_CALENDAR_DATE, LOC_CALENDAR_MONTH, LOC_CALENDAR_YEAR);
    updateValue("RETURN DATE", retDate);
  }

  /**
   * Select the Deal from Offer List
   *
   * @author Sankar
   */
  public void selectDeal()  {

    List<WebElement> flightList = CheckUtils.getElements(getTest(), LOC_LI_FARE_DEALS_OFFERS);
    for (WebElement flight : flightList) {
      if (flight != null && flight.isDisplayed()) {
          ClickUtils.clickElement(getTest(), flight);
          reporter.reportPassed("Deal Offer", "Deal Offer is selected From the Deal List");
          break;
        }
      }
    }

  /**
   * Validate From and To Drop down Default value
   *
   * @author Sankar
   */
  public void ValidateFromToDefaultvalue(){

    WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_To_DropDown, 20);
    WebElement SourceCity = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_From_Dropdown);
    WebElement DestinationCity = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_To_DropDown);

    if (SourceCity != null && SourceCity.isDisplayed()) {
      reporter.reportPassed("From", "From Dropdown is displayed in Filter options");
    }else{
      // reporter.reportFailed("From", "From Dropdown is not displayed in Filter options");
      reporter.fail("From Dropdown is not displayed in Filter options");
    }
    if (DestinationCity != null && DestinationCity.isDisplayed()) {
      reporter.reportPassed("To", "To Dropdown is displayed in Filter options");
    }else{
      // reporter.reportFailed("To", "To Dropdown is not displayed in Filter options");
      reporter.fail("To Dropdown is not displayed in Filter options");
    }
    verifyDropDownvalue(SourceCity,"Anywhere","From DropDown");
    verifyDropDownvalue(DestinationCity,"Anywhere","To DropDown");
  }

  /**
   * Validate TravelPeriod From and To Drop down Default value
   *
   * @author Sankar
   */
  public void ValidateTravelPeriodDefaultValue(){

    WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_Travel_To, 20);
    WebElement DepDate = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_From);
    WebElement RetDate = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_To);

    if (DepDate != null && DepDate.isDisplayed()) {
      reporter.reportPassed("Departure Date", "Departure DatePicker is displayed in Filter options");
    }else{
     reporter.reportFailed("Departure Date", "Departure DatePicker is not displayed in Filter options");
    }
    if (RetDate != null && RetDate.isDisplayed()) {
      reporter.reportPassed("Return Date", "Return DatePicker is displayed in Filter options");
    }else{
     reporter.reportFailed("Return Date", "Return DatePicker is not displayed in Filter options");
    }
    verifyDatePickervalue(DepDate,GetDefaultdate("DepDate"),"From DatePicker");
    verifyDatePickervalue(RetDate,GetDefaultdate("RetDate"),"To DatePicker");

  }

  /**
   * Validate Past Date in Date Picker
   *
   * @throws Exception
   * @author Sankar
   */
  public void VerifyPastDateinDatePicker() throws Exception {

    VerifyDeppastDate();
    VerifyRetpastDate();

  }

  /**
   * Validate Departure Past Date in Date Picker
   *
   * @throws Exception
   * @author Sankar
   */
  public void VerifyDeppastDate() throws Exception {

    WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_Travel_From, 10);
    WebElement  DepDate = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_From);
    if (DepDate != null && DepDate.isDisplayed()) {
      ClickUtils.click(getTest(), DepDate);
      selectpastdate("Departure Date");
    }else{
      // reporter.reportFailed("Departure Date", "Departure Date element is not displayed in Fare Deal page.");
      reporter.fail("Departure Date element is not displayed in Fare Deal page.");
    }
  }

  /**
   * Validate Return Past Date in Date Picker
   *
   * @throws Exception
   * @author Sankar
   */
  public void VerifyRetpastDate() throws Exception {

    WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_Travel_To, 10);
    WebElement  RetDate = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_To);
    if (RetDate != null && RetDate.isDisplayed()) {
      ClickUtils.click(getTest(), RetDate);
      selectpastdate("Return Date");
    }else{
      // reporter.reportFailed("Return Date", "Return Date element is not displayed in Fare Deal page.");
      reporter.fail("Return Date element is not displayed in Fare Deal page.");
    }
  }

  /**
   * Change Travel Period Departure and Return Date in Date Picker
   *
   * @throws Exception
   * @author Sankar
   */
  public void ChangeDepArrivalDate() throws Exception{

    fillDeptDateUsingCalendar(Integer.parseInt(getValue("TravelPeriod From")));
    fillReturnDateUsingCalendar(Integer.parseInt(getValue("TravelPeriod To")));

    verifyTravelPeriodDealOffer(LOC_LI_FARE_DEALS_OFFERS,LOC_LI_FARE_DEALS_Travel_From,LOC_LI_FARE_DEALS_Travel_To,"Travel Period");
    validateMultipleOffers();
  }

  /**
   * Click FromTo Filter option and click back Travel period again.
   *
   * @throws ParseException
   * @author Sankar
   */
  public void ClickFromToAndBack() throws ParseException{

    String DepDateBefore;
    String RetDateBefore;
    String DepDateAfter;
    String RetDateAfter;

    DepDateBefore = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_From).getText().trim();
    RetDateBefore = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_To).getText().trim();

    clickFromToFilter();
    clickTravelPeriod();

    WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_Travel_To, 20);

    DepDateAfter = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_From).getText().trim();
    RetDateAfter = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_To).getText().trim();

    if(DepDateBefore.trim().equalsIgnoreCase(DepDateAfter.trim()) && RetDateBefore.trim().equalsIgnoreCase(RetDateAfter.trim())){
      verifyTravelPeriodDealOffer(LOC_LI_FARE_DEALS_OFFERS,LOC_LI_FARE_DEALS_Travel_From,LOC_LI_FARE_DEALS_Travel_To,"Click FromTo and again press Traval period");
      validateMultipleOffers();
    }
   }

  /**
   * Click Price Filter option and click back Travel period again.
   *
   * @throws ParseException
   * @author Sankar
   */
  public void ClickPriceAndBack() throws ParseException{

    String DepDateBefore;
    String RetDateBefore;
    String DepDateAfter;
    String RetDateAfter;

    DepDateBefore = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_From).getText().trim();
    RetDateBefore = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_To).getText().trim();

    clickPriceFilter();
    clickTravelPeriod();

    WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_Travel_To, 20);

    DepDateAfter = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_From).getText().trim();
    RetDateAfter = CheckUtils.getElement(getTest(), LOC_LI_FARE_DEALS_Travel_To).getText().trim();

    if(DepDateBefore.trim().equalsIgnoreCase(DepDateAfter.trim()) && RetDateBefore.trim().equalsIgnoreCase(RetDateAfter.trim())){
      verifyTravelPeriodDealOffer(LOC_LI_FARE_DEALS_OFFERS,LOC_LI_FARE_DEALS_Travel_From,LOC_LI_FARE_DEALS_Travel_To,"Click Price and again press Traval period");
      validateMultipleOffers();
    }
   }

}
