package com.amadeus.selenium.sqmobile.page.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Search Page
 * @author rshivaswamy
 *
 */

public class SearchPage extends CommonSearchPage{


  /*
   * Web Element Identifiers
   *
   */
  protected static By LOC_IN_SEARCHPAGE_FROM = By.id("B_LOCATION_1");
  protected static By LOC_IN_SEARCHPAGE_TO = By.id("E_LOCATION_1");
  protected static By LOC_RB_SEARCHPAGE_RT = By.id("roundTrip");
  protected static By LOC_RB_SEARCHPAGE_OW = By.id("oneWay");
  protected static By LOC_LS_SEARCHPAGE_DEPARTURE_DATE = By.id("day1");
  protected static By LOC_LS_SEARCHPAGE_DEPARTURE_MONTH = By.id("month1");
  protected static By LOC_LS_SEARCHPAGE_RETURN_DATE = By.id("day2");
  protected static By LOC_LS_SEARCHPAGE_RETURN_MONTH = By.id("month2");
  protected static By LOC_CB_SEARCHPAGE_FLEXIDATES = By.id("DATE_RANGE_VALUE_1");
  protected static By LOC_CB_SEARCHPAGE_FLEXIDATES_VALUE = By.id("DATE_RANGE_VALUE_2");
  protected static By LOC_LS_SEARCHPAGE_CABINCLASS = By.id("CABIN_CLASS");
  protected static By LOC_LS_SEARCHPAGE_ADULT_COUNT = By.id("FIELD_ADT_NUMBER");
  protected static By LOC_LS_SEARCHPAGE_CHILD_COUNT = By.id("FIELD_CHD_NUMBER");
  protected static By LOC_LS_SEARCHPAGE_INFANT_COUNT = By.id("FIELD_INFANTS_NUMBER");

  protected static By LOC_CB_SEARCHPAGE_FLEXIDATES_CHECKBOX = By.name("DATE_RANGE_VALUE_FLEX");

  protected static By LOC_PB_SEARCHPAGE_BACK = By.className("back");

  protected static By LOC_BT_SEARCHPAGE_SEARCHBUTTON = By.cssSelector(".validation");

  protected static By LOC_CE_DEP_NEW_DATE_PICKER = By.id("depdate");
  protected static By LOC_CE_RET_NEW_DATE_PICKER = By.id("retdate");

  String pageName = "Search Page";

  public SearchPage(SeleniumSEPTest test) throws Exception {
    super(test);
  }


/**
 * Click Back button
 */

  public void clickBack(){

    ClickUtils.clickButtonOrFail(getTest(), LOC_PB_SEARCHPAGE_BACK, "Back button NOT found or is NOT displayed");
  }

  /**
   * Validate Search Page
   * @throws Exception
   */

  public void validateSearchPage() throws Exception{


    /*
     * Validate From and To fields
     */

    validateFromNToFields();


    /*
     * Validate RT and OW radio display
     */

    validateRTandOWRadio();

    /*
     * Validate Date
     */

  //  validateDates();


    /*
     * validate Flexi
     */

    validateFlexi();

    /*
     * validate PAX Count
     */

    validatePAXCount();

  }


/**
 * Click on Redeemention Booking
 */

  public void clickRedeemOnOff(){

  }


/**
 * Click on FROM Airport Picker
 */
  public void clickFromAirportPicker(){

  }



/**
 * Click on TO Airport picker
 */
  public void clickToAirportPicker(){

  }


  /**
   * Fill Search Page
   * @throws Exception
   */

  public void fillSearchPage() throws Exception{

    WaitUtils.waitForElementVisible(getTest(), LOC_IN_SEARCHPAGE_FROM, 60);
    WebElement from = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_FROM);
    WebElement to = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_TO);
    WebElement roundTrip = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_RT);
    WebElement oneWay = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_OW);
    CommonUtils utils = new CommonUtils(getTest());
    String[] tripType = new String[]{"OW","RT"};

    //Enter the values in the fields

    fillDestinationFields(from, getValue("From").trim(), "FROM filed could not be entered");
    fillDestinationFields(to, getValue("To").trim(), "TO filed could not be entered");

    reporter.reportPassed("Search Page ", "From : "+getValue("From").trim());
    reporter.reportPassed("Search Page ", "To : "+getValue("To").trim());


	boolean isNewDatePicker = CheckUtils.checkElementPresent(getTest(), LOC_CE_DEP_NEW_DATE_PICKER, "depdate");


	String DepDate = "";
	String DepMonth= "";
	String RetDate = "";
	String RetMonth= "";


	if(isNewDatePicker){
		selectNewDatePicker(roundTrip, oneWay, utils, tripType);

		String departueDate = CheckUtils.getElement(getTest(), LOC_CE_DEP_NEW_DATE_PICKER).getText();
		String returnDate = CheckUtils.getElement(getTest(), LOC_CE_RET_NEW_DATE_PICKER).getText();

		String depDates[] = departueDate.split(" ");
		String retDates[] = returnDate.split(" ");

		DepDate = depDates[1];
		DepMonth = depDates[0] + " " + depDates[3];

		RetDate = retDates[1];
		RetMonth = retDates[0] + " " + retDates[3];


	}else{

		selectDates(roundTrip, oneWay, utils, tripType);

		DepDate = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_DATE);
		DepMonth = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_MONTH);
		RetDate = getStringDate(LOC_LS_SEARCHPAGE_RETURN_DATE);
		RetMonth = getStringDate(LOC_LS_SEARCHPAGE_RETURN_MONTH);
	}

    //Save flight dates
    updateValue("DEPARTURE DATE", DepDate + " " + DepMonth);
    updateValue("RETURN DATE", RetDate + " " + RetMonth);

    handleFlexiDates();
    selectPAXCountAndCabin();
    this.automaticScreenShot();
  }

  public void fillSearchPageforAjaxList() throws Exception{

    WaitUtils.waitForElementVisible(getTest(), LOC_IN_SEARCHPAGE_FROM, 60);
    WebElement from = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_FROM);
    WebElement to = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_TO);
    WebElement roundTrip = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_RT);
    WebElement oneWay = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_OW);
    CommonUtils utils = new CommonUtils(getTest());
    String[] tripType = new String[]{"OW","RT"};

    //Enter the values in the fields and select the value from ajax list based on the values entered

    fillDestinationFieldsbyText(from, getValue("From").trim(), "FROM filed could not be entered");
    fillDestinationFieldsbyText(to, getValue("To").trim(), "TO filed could not be entered");

    reporter.reportPassed("Search Page ", "From : "+getValue("From").trim());
    reporter.reportPassed("Search Page ", "To : "+getValue("To").trim());


    selectDates(roundTrip, oneWay, utils, tripType);

    String DepDate = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_DATE);
    String DepMonth = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_MONTH);
    String RetDate = getStringDate(LOC_LS_SEARCHPAGE_RETURN_DATE);
    String RetMonth = getStringDate(LOC_LS_SEARCHPAGE_RETURN_MONTH);

    //Save flight dates
    updateValue("DEPARTURE DATE", DepDate + " " + DepMonth);
    updateValue("RETURN DATE", RetDate + " " + RetMonth);

    handleFlexiDates();
    selectPAXCountAndCabin();
    this.automaticScreenShot();

  }
//Test Id 5457
  public void validateSearchPageInfo() throws Exception{
	    validateFromNToFields();
	    validateRTandOWRadio();
	    validateFlexi();
	    validatePAXCounts();

	  }

  public void fillSearchPages() throws Exception{
		WaitUtils.waitForElementVisible(getTest(), LOC_IN_SEARCHPAGE_FROM, 60);
		WebElement from = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_FROM);
		WaitUtils.waitForElementVisible(getTest(), LOC_IN_SEARCHPAGE_TO, 60);
		WebElement to = CheckUtils.getElement(getTest(), LOC_IN_SEARCHPAGE_TO);
		WebElement roundTrip = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_RT);
		WebElement oneWay = CheckUtils.getElement(getTest(), LOC_RB_SEARCHPAGE_OW);
		CommonUtils utils = new CommonUtils(getTest());
		String[] tripType = new String[]{"OW","RT"};

		//Enter the values in the fields

		fillDestinationFields(from, getValue("From").trim(), "FROM filed could not be entered");
		fillDestinationFields(to, getValue("To").trim(), "TO filed could not be entered");

		selectDates(roundTrip, oneWay, utils, tripType);

		String DepDate = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_DATE);
		String DepMonth = getStringDate(LOC_LS_SEARCHPAGE_DEPARTURE_MONTH);
		String RetDate = getStringDate(LOC_LS_SEARCHPAGE_RETURN_DATE);
		String RetMonth = getStringDate(LOC_LS_SEARCHPAGE_RETURN_MONTH);

		//Save flight dates
		updateValue("DEPARTURE DATE", DepDate + " " + DepMonth);
		updateValue("RETURN DATE", RetDate + " " + RetMonth);

		handleFlexiDates();
		selectPAXCountAndCabins();
		this.automaticScreenShot();
	}

}
