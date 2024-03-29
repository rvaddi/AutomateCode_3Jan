
package com.amadeus.selenium.sqmobile.page.paxinfo;

import java.text.MessageFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.extension.ByList;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.payment.IPayment;
import com.amadeus.selenium.sqmobile.page.payment.PaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.AirchinaPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.CarabPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.IcelandAirPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.SaudiPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.sqmobile.page.seat.SeatMapPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class PaxInfoPage extends SqMobileCommonPage implements IPaxInfo{


	protected static final By LOC_IN_PAX_INFO_SECTION_TITLE = By.className(".panel>header>h1");
	protected static final By LOC_IN_PAX_INFO_ITINERARY_SECTION = By.id("section_itinerary");
	protected static final By LOC_IN_PAX_INFO_TOTAL_PRICE = By.cssSelector(".data.price.total");
	protected static final By LOC_IN_PAX_INFO_PAX_DETAILS = By.cssSelector(".pax>div>span>div>h2>.data");
	protected static MessageFormat LOC_IN_PAX_INFO_PAX_DETAILS_TITLE = new MessageFormat("TITLE_{0}");
	protected static MessageFormat LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME = new MessageFormat("FIRST_NAME_{0}");
	protected static MessageFormat LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME = new MessageFormat("LAST_NAME_{0}");
	protected static MessageFormat LOC_IN_PAX_INFO_PAX_DETAILS_DOB_DAY = new MessageFormat("#paxDobDay_{0}");
	protected static MessageFormat LOC_IN_PAX_INFO_PAX_DETAILS_DOB_MONTH = new MessageFormat("#paxDobMonth_{0}");
	protected static MessageFormat LOC_IN_PAX_INFO_PAX_DETAILS_DOB_YEAR = new MessageFormat("#paxDobYear_{0}");
	protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_PROGRAM = By.cssSelector("input[id^='PREF_AIR_FREQ_AIRLINE_']");
	protected static final By LOC_TX_PAX_INFO_PAX_DETAILS_FF_NUMBER = By.cssSelector("input[id^='PREF_AIR_FREQ_NUMBER_']");
	protected static final By LOC_IN_PAX_INFO_CONTACT_TYPE = By.id("PREFERRED_PHONE_NO");
	protected static final By LOC_IN_PAX_INFO_CONTACT_COUNTRY = By.id("COUNTRY");
	protected static final By LOC_TX_PAX_INFO_CONTACT_COUNTRY_AUTO_COMPLETE = By.id("ui-active-menuitem");
	protected static final By LOC_IN_PAX_INFO_CONTACT_CODE = By.id("AREA_CODE");
	protected static final By LOC_IN_PAX_INFO_CONTACT_NUMBER = By.id("PHONE_NUMBER");
	protected static final By LOC_IN_PAX_INFO_CONTACT_EMAIL = By.name("CONTACT_POINT_EMAIL_1");
	protected static final By LOC_IN_PAX_INFO_SMS_NOTIFICATION = By.name("NOTIF_VALUE_1_1");
	protected static final By LOC_IN_PAX_INFO_MOBILE_NUMBER = By.name("CONTACT_POINT_MOBILE_1");

	protected static final By LOC_IN_PAX_INFO_UNMR_TEXT = By.id("warn18text");
	protected static final By LOC_IN_PAX_INFO_UNMR_LINK = By.id("alpi_umnranc");
	protected static final By LOC_BT_PAX_INFO_SELECT_SEAT = By.className("navigation");
	protected static final By LOC_BT_PAX_INFO_CONTINUE_PAYMENT = By.className("validation");
	protected static final By LOC_BT_PAX_INFO_BACK = By.className("back");
	protected static final By LOC_WL_PAX_INFO_PRICE_DETAILS_POPUP_CLOSE = By.className("close");
	protected static final By LOC_WL_PAX_INFO_PRICE_DETAILS_POPUP_TOTAL_PRICE = new ByList(By.id("fareBrkdwnPriceTotal"),
			By.className("withoutInsuranceTotal"));
	protected static final By LOC_IN_PAX_INFO_PRICE_DETAILS = By.className("priceDetails");
	protected static final By LOC_WL_PAX_INFO_PRICE_DETAILS_POPUP_FARE_TABLE = By.className("fareTabPop");
	protected static final By LOC_WL_PAX_INFO_PRICE_DETAILS_POPUP_FARE = By.cssSelector(".wrapper.pop");
	protected static final By LOC_PB_PAX_INFO_RETRIEVED_PNR_PRICE_DETAILS_POPUP_TOTAL_PRICE = By
	.cssSelector(".fareTabPop.totalPaxFareTable>tbody>tr>td");
	protected static final By LOC_IN_PRICE_DEATILS_POPUP_TEXT = By.cssSelector(".panel.price>header>hgroup>h1");
	protected static final By LOC_IN_PRICE_DETAILS_CURRENCY = By.cssSelector("#pricePopup>span>div>article>header>hgroup>h2>.data");
	protected static final By LOC_IN_PAX_FLIGHT_NO = By.cssSelector("flight-number");
	protected static final By LOC_IN_PAX_CITY_LOCATIONS = By.cssSelector(".location");
	protected static final By LOC_IN_PAX_DEP_FLIGHT_NO = By.cssSelector(".depature>.flight-number");
	protected static final By LOC_IN_SELECTED_ITINERARY_TEXT = By.cssSelector(".panel>header>h1");
	protected static final By LOC_IN_DEP_RETURN_LOCATION = By.cssSelector(".location");
	protected static final By LOC_IN_DEP_RETURN_SCHEDULE = By.cssSelector(".schedule");
	protected static final By LOC_IN_DEP_RETURN_CITIES = By.cssSelector(".city");
	protected static final By LOC_IN_DEP_RETURN_DATES = By.cssSelector(".date");

	protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_INFANT_FIRST_NAME = By.id("INFANT_FIRST_NAME_1");
	protected static final By LOC_IN_PAX_INFO_PAX_DETAILS_INFANT_LAST_NAME = By.id("INFANT_LAST_NAME_1");



	String pageName = "Passenger Information Page";

	public enum Customer{
		SAUDI , AIRCHINA,ICELANDAIR,TAM,CARAB,SQMOBILE,MEA
	}

	public PaxInfoPage(SeleniumSEPTest test) {
		super(test);
	}


	public void validatePaxInfoPage()throws Exception {
		//Validate Selected Itinerary section is displayed
		validateSelectedItinerary("Selected Itinerary");

		//Validate the Total price displayed
		validateTotalPrice();


		//Validate Passengers section
		validatePassengers();

		//Validate static text
		WebElement unmrLink = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_UNMR_LINK);
		String unmrText = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_UNMR_TEXT).getText();
		if (unmrLink != null &&
				unmrLink.isDisplayed() &&
				(unmrText
						.contains("To proceed to payment, please ensure that at least one passenger in the booking must be 18 years and above") || unmrText
						.contains("There must be at least one adult 18 years old and above in the booking."))) {
			reporter.reportPassed(pageName, "Static text for unaccompanied minor is displayed properly along with the conditions links");
		}
		else{
			reporter.reportFailed(pageName, "Static link s for unaccompanied minors is not displayed   properly");
		}

		addValue("TITLE", "");
		addValue("FIRST NAME", "");
		addValue("LAST NAME", "");

	}
	//****************************************************************************


	/**
	 * To Select Seat
	 * -History : Created by Vigneshwaran Balasubramanian
	 * @throws Exception
	 */

	public SeatMapPage clickSelectSeat() throws Exception {

		WebElement selectSeat = CheckUtils.getElement(getTest(), LOC_BT_PAX_INFO_SELECT_SEAT);
		if (selectSeat!=null && selectSeat.isDisplayed()){
			selectSeat.click();
			reporter.reportPassed(pageName, "Select Seat link is clicked");
		}
		else{
			reporter.reportFailed(pageName, "Select Seat link is not displayed");
		}

		return PageFactory.getPageObject(SeatMapPage.class);
	}


	/**
	 * Continue Payment , traverse to Payment page
	 * -Historty : created by Vigneshwaran   Balasubramanian
	 * @return
	 */

	public IPayment continuePayment(Customer customer)throws Exception {

		WebElement payment = CheckUtils.getElement(getTest(), LOC_BT_PAX_INFO_CONTINUE_PAYMENT);
		if (payment!=null && payment.isDisplayed()){
			payment.click();
			waitForOverlayLoading(60);
			reporter.reportPassed(pageName, "Continue to Payment link is clicked");
		}
		else{
			reporter.reportFailed(pageName, "Continue to Payment link is not displayed");
		}

		return getCusomerPaymentObject(customer);
	}

	/**
	 * Get customerpayment page object
	 * @param customer - Customer Enum
	 * @return
	 * @throws Exception
	 */
	public IPayment getCusomerPaymentObject(Customer customer) throws Exception{
		if(customer!=null){
			if(Customer.SAUDI.equals(customer)){
				return PageFactory.getPageObject(SaudiPaymentPage.class);
			}
			else if(Customer.AIRCHINA.equals(customer)){
				return PageFactory.getPageObject(AirchinaPaymentPage.class);
			}
			else if(Customer.ICELANDAIR.equals(customer)){
				return PageFactory.getPageObject(IcelandAirPaymentPage.class);
			}
			else if(Customer.CARAB.equals(customer)){
				return PageFactory.getPageObject(CarabPaymentPage.class);
			}
			else if(Customer.TAM.equals(customer)){
				return PageFactory.getPageObject(TAMPaymentPage.class);
			}
			else if(Customer.SQMOBILE.equals(customer)){
				return PageFactory.getPageObject(PaymentPage.class);
			}
			else if(Customer.MEA.equals(customer)){
				return PageFactory.getPageObject(PaymentPage.class);
			}
		}
		else{
			reporter.fail("Customer not found");
		}
		return null;
	}

	//Needs Validation
	//****************************************************************************
	// Implementation pending , this function will be implemented on previous page inputs
	//****************************************************************************
	public void validateSelectedItinerary(String str)throws Exception {
		WebElement selectedItineraryText = CheckUtils.getElements(getTest(), LOC_IN_SELECTED_ITINERARY_TEXT).get(1);
		if(selectedItineraryText.getText().equals(str)){
			reporter.report("Selected Itinerary Section", "Validating Selected Itinerary Section");

			WebElement flightNo = CheckUtils.getElements(getTest(), LOC_IN_PAX_FLIGHT_NO).get(0);
			if(!flightNo.getText().equals(getValue("DEPARTURE FLIGHT NUMBER").trim())){
				reporter.reportFailed("Flight Number", "Invalid Flight Number");
			}

			WebElement returnFlightNo = CheckUtils.getElement(getTest(), LOC_IN_PAX_DEP_FLIGHT_NO);
			if(!returnFlightNo.getText().equals(getValue("RETURN FLIGHT NUMBER"))){
				reporter.reportFailed(" Return Flight Number", "Invalid Return Flight Number");
			}

			//Validate Location
			List<WebElement> location = CheckUtils.getElements(getTest(), LOC_IN_DEP_RETURN_LOCATION);

			//Departure Location
			WebElement depLocations = location.get(0);
			if(depLocations!=null && depLocations.isDisplayed()){
				WebElement fromCity = depLocations.findElements(LOC_IN_DEP_RETURN_CITIES).get(0);
				if(fromCity.equals(getValue("DEP FROM"))){
					reporter.reportPassed("From City", "Valid From City");
				}

				WebElement toCity = depLocations.findElements(LOC_IN_DEP_RETURN_CITIES).get(1);
				if(toCity.equals(getValue("DEP TO"))){
					reporter.reportPassed("To City", "Valid To City");
				}
			}

			//Return Location
			WebElement returnLocations = location.get(1);
			if(returnLocations!=null && returnLocations.isDisplayed()){
				WebElement fromCity = returnLocations.findElements(LOC_IN_DEP_RETURN_CITIES).get(0);
				if(fromCity.equals(getValue("RETURN FROM"))){
					reporter.reportPassed("From City", "Valid From City");
				}

				WebElement toCity = returnLocations.findElements(LOC_IN_DEP_RETURN_CITIES).get(1);
				if(toCity.equals(getValue("RETURN TO"))){
					reporter.reportPassed("To City", "Valid To City");
				}
			}
			//Departure Schedule Date
			List<WebElement> scheduleDate = CheckUtils.getElements(getTest(), LOC_IN_DEP_RETURN_SCHEDULE);
			WebElement departureSchedule = scheduleDate.get(0);

			if(departureSchedule!=null && departureSchedule.isDisplayed()){
				WebElement fromdate = departureSchedule.findElements(LOC_IN_DEP_RETURN_DATES).get(0);
				if(fromdate.equals(getValue("DEP FROM DATE"))){
					reporter.reportPassed("From Date", "Valid From Date");
				}

				WebElement todate = departureSchedule.findElements(LOC_IN_DEP_RETURN_DATES).get(1);
				if(todate.equals(getValue("DEP TO DATE"))){
					reporter.reportPassed("To City", "Valid To Date");
				}
			}

			//Return Schedule Date
			WebElement returnSchedule = scheduleDate.get(1);

			if(returnSchedule!=null && returnSchedule.isDisplayed()){
				WebElement fromdate = returnSchedule.findElements(LOC_IN_DEP_RETURN_DATES).get(0);
				if(fromdate.equals(getValue("RET FROM DATE"))){
					reporter.reportPassed("From Date", "Valid From Date");
				}

				WebElement todate = returnSchedule.findElements(LOC_IN_DEP_RETURN_DATES).get(1);
				if(todate.equals(getValue("RET TO DATE"))){
					reporter.reportPassed("To City", "Valid To Date");
				}
			}

		}

	}

	/**
	 * Validate Total Price
	 * History : Created by Vigneshwaran Balasubramanian
	 * @throws Exception
	 */
	public void validateTotalPrice()throws Exception {

		String totalPrice = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_TOTAL_PRICE).getText();

		if (totalPrice.contains(getValue("CURRENCY").trim()) && totalPrice.contains(getValue("TOTAL FARE").trim())){
			reporter.reportPassed(pageName, "The total price section is displayed properly");
		}
		else{
			reporter.reportFailed(pageName, "The total price section is not displayed properly");
		}
	}


	//****************************************************************************
	// Implementation pending , this function will be implemented on previous page inputs
	//****************************************************************************

	public void validatePassengers()throws Exception {

		int nbPax = new Integer(getValue("NB ADULT").trim()) + new Integer(getValue("NB CHILD").trim());
		List<WebElement> HeaderFields = CheckUtils.getElements(getTest(), LOC_IN_PAX_INFO_SECTION_TITLE);

		if(HeaderFields.size()==nbPax+2){
			reporter.reportPassed(pageName, "All the passenger sections are displayed");
		}
		else {
			reporter.reportFailed(pageName, "The expected number of passenger sections is not displayed");
		}

		//Validate static text in all passenger sections
		int nbAdult = new Integer(getValue("NB ADULT").trim());
		int nbChild = new Integer(getValue("NB CHILD").trim());

		List<WebElement> fields = CheckUtils.getElements(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS);
		for (int i=0; i<nbAdult; i++){
			if (fields.get(i).getText().contains(": Adult")){
				reporter.reportPassed(pageName, "Static Text is displayed properly for Passenger " + (i+1));
			}
			else{
				reporter.reportFailed(pageName, "Static Text is not displayed properly for Passenger " + (i+1));
			}
		}

		for (int i=0; i<nbChild; i++){
			if (fields.get(i+nbAdult).getText().contains(": Child")){
				reporter.reportPassed(pageName, "Static Text is displayed properly for Passenger " + (i+1+nbAdult));
			}
			else{
				reporter.reportFailed(pageName, "Static Text is not displayed properly for Passenger " + (i+1+nbAdult));
			}
		}
	}

	/**
	 * Fill Passenger Infromation
	 * History : Created by Vigneshwaran Balasubramanian
	 * @throws Exception
	 */

	public void fillPaxInfo() throws Exception {

		List<WebElement> paxSections = CheckUtils.getElements(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS);
		String[] adultTitle = new String[] {"Mr", "Mrs","Ms"};
		String[] childTitle = new String[] {"Mr", "Miss"};
		WebElement titleName = null ;
		WebElement firstName = null;;
		WebElement lastName = null;;
		int noOfAdults = Integer.parseInt(getValue("Nb Adult"));
		int noOfChild = Integer.parseInt(getValue("Nb Child"));
		CommonUtils utils =  PageFactory.getPageObject(CommonUtils.class);
		String Date = utils.addDate("dd MMM yyyy",  -800);
		String childDOBDay = (Date.split(" "))[0];
		String childDOBMonth = (Date.split(" "))[1];
		String childDOBYear = (Date.split(" "))[2];


		int i = 0;


		if (paxSections!=null && paxSections.get(0).getText().contains("Adult")){
			for(int intSector=1; intSector<=noOfAdults; intSector++){
				Object formatArgs[] = {intSector};
				titleName = CheckUtils.getElement(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_TITLE.format(formatArgs)));
				if(titleName!=null && titleName.isDisplayed()){
					FillUtils.fillSelectByVisibleTextOrFail(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_TITLE.format(formatArgs)), "Mr", "Adult Title not entered");
					reporter.reportPassed("PaxInfo Page", "Adult title entered");
				}

				firstName = CheckUtils.getElement(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME.format(formatArgs)));
				if(firstName!=null && firstName.isDisplayed()&&firstName.getText().equals("")){
					FillUtils.fillInputOrFail(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME.format(formatArgs)), getValue("FIRSTNAME_ADT"), "First Name Field not entered");
					reporter.reportPassed("Adult FirstName Entered", getValue("FIRSTNAME_ADT"));
				}

				lastName = CheckUtils.getElement(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME.format(formatArgs)));
				if(lastName!=null && lastName.isDisplayed()&&lastName.getText().equals("")){
					FillUtils.fillInputOrFail(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME.format(formatArgs)), getValue("LASTNAME_ADT"), "Last Name Field not entered");
					reporter.reportPassed("Adult LastName Entered", getValue("LASTNAME_ADT"));
				}

				WebElement dayOfBirth = CheckUtils.getElement(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_DAY.format(formatArgs)));
				if(dayOfBirth!=null && dayOfBirth.isDisplayed()){
					FillUtils.fillSelectByValueOrFail(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_DAY.format(formatArgs)), "1", "Day of Birth not selected");
					reporter.reportPassed("Adult Birth-Date Entered", "1");
				}

				WebElement monthOfBirth = CheckUtils.getElement(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_MONTH.format(formatArgs)));
				if(monthOfBirth!=null && monthOfBirth.isDisplayed()){
					FillUtils.fillSelectByVisibleTextOrFail(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_MONTH.format(formatArgs)), "Jan", "Month of Birth not selected");
					reporter.reportPassed("Adult Birth-Month Entered", "Jan");
				}

				WebElement yearOfBirth = CheckUtils.getElement(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_YEAR.format(formatArgs)));
				if(yearOfBirth!=null && yearOfBirth.isDisplayed()){
					FillUtils.fillSelectByValueOrFail(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_YEAR.format(formatArgs)), "1980", "Year of Birth not selected");
					reporter.reportPassed("Adult Birth-Year Entered", "1980");
				}
			}
		}
		if ( paxSections!=null && paxSections.size()>noOfAdults && paxSections.get(noOfAdults).getText().contains("Child")){
			for(int intSector=noOfAdults+1; intSector<=noOfAdults+noOfChild; intSector++){
				Object formatArgs[] = {intSector};
				titleName = CheckUtils.getElement(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_TITLE.format(formatArgs)));
				if(titleName!=null && titleName.isDisplayed()){
					FillUtils.fillSelectByVisibleTextOrFail(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_TITLE.format(formatArgs)), childTitle[i], "Child Title not entered");
					reporter.reportPassed("PaxInfo Page", "Child title entered");
				}

				firstName = CheckUtils.getElement(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME.format(formatArgs)));
				if(firstName!=null && firstName.isDisplayed()&&firstName.getText().equals("")){
					waitForOverlayLoading(200);
					FillUtils.fillInputOrFail(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_FIRST_NAME.format(formatArgs)), getValue("FIRSTNAME_CHD"), "First Name Field not entered");
					reporter.reportPassed("Child FirstName Entered", getValue("FIRSTNAME_CHD"));
				}

				lastName = CheckUtils.getElement(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME.format(formatArgs)));
				if(lastName!=null && lastName.isDisplayed()&&lastName.getText().equals("")){
					FillUtils.fillInputOrFail(getTest(), By.id(LOC_IN_PAX_INFO_PAX_DETAILS_LAST_NAME.format(formatArgs)),getValue("LASTNAME_CHD") , "Last Name Field not entered");
					reporter.reportPassed("Child LastName Entered", getValue("LASTNAME_CHD"));
				}

				WebElement dayOfBirth = CheckUtils.getElement(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_DAY.format(formatArgs)));
				if(dayOfBirth!=null && dayOfBirth.isDisplayed()){
					FillUtils.fillSelectByValueOrFail(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_DAY.format(formatArgs)), childDOBDay, "Day of Birth not selected");
					reporter.reportPassed("Child Birth-Date Entered", childDOBDay);
				}

				WebElement monthOfBirth = CheckUtils.getElement(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_MONTH.format(formatArgs)));
				if(monthOfBirth!=null && monthOfBirth.isDisplayed()){
					FillUtils.fillSelectByVisibleTextOrFail(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_MONTH.format(formatArgs)), childDOBMonth, "Month of Birth not selected");
					reporter.reportPassed("Child Birth-Month Entered", childDOBMonth);
				}

				WebElement yearOfBirth = CheckUtils.getElement(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_YEAR.format(formatArgs)));
				if(yearOfBirth!=null && yearOfBirth.isDisplayed()){
					FillUtils.fillSelectByValueOrFail(getTest(), By.cssSelector(LOC_IN_PAX_INFO_PAX_DETAILS_DOB_YEAR.format(formatArgs)), childDOBYear, "Year of Birth not selected");
					reporter.reportPassed("Child Birth-Year Entered", childDOBYear);
				}
			}
		}

		if((!"".equalsIgnoreCase(getValue("Nb Infants")))){
			waitForOverlayLoading(200);
			String infantFirstName=getValue("FIRSTNAME_INFANT");
			String infantLastName=getValue("LASTNAME_INFANT");
			//FirstName - Infant
			CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_INFANT_FIRST_NAME, "Infant FirstName not present");
			FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_INFANT_FIRST_NAME, infantFirstName, "Infant FirstName not selected");
			// LastName - Infant
			CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_INFANT_LAST_NAME, "Infant LastName not present");
			FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_PAX_DETAILS_INFANT_LAST_NAME, infantLastName, "Infant LastName not selected");
		}
		/*    WaitUtils.wait(3);
    if(titleName!=null)
      updateValue("TITLE", titleName.getAttribute("value"));
    else
      reporter.reportFailed(pageName, "Pax TITLE couldn't be selected");

    if(firstName!=null)
      updateValue("FIRST NAME", firstName.getAttribute("value"));
    else
      reporter.reportFailed(pageName, "Pax FIRST NAME couldn't be selected");

    if(lastName!=null)
      updateValue("LAST NAME", lastName.getAttribute("value"));
    else
      reporter.reportFailed(pageName, "Pax LAST NAME couldn't be selected");*/
	}


	/**
	 * To Fill Contact Information
	 * History : Created by Vigneshwaran Balasubramanian
	 */
	public void fillContactInfo(String contacType ,String areaCode,String contactNumber , String email,String countryCode,String smsNotificationNumber) {

		WebElement contactType = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_TYPE);
		if (contactType!=null && contactType.isDisplayed()){
			Select ContactType = new Select (contactType);
			ContactType.selectByVisibleText(contacType);
		}

		WebElement contactCountry = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_COUNTRY);
		if (contactCountry!=null && contactCountry.isDisplayed()){
			contactCountry.clear();
			FillUtils.fillInputOrFail(getTest(), contactCountry, countryCode, "Country Code not entered");//contactCountry.sendKeys("Sin");
			WaitUtils.wait(3);
			List<WebElement> autoComplete1 = CheckUtils.getElements(getTest(), By.className("ui-corner-all"));
			for(WebElement opt : autoComplete1){
				if(opt.isDisplayed()){
					opt.click();
				}
			}
		}



		WebElement mobileNumber = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_MOBILE_NUMBER);
		if (mobileNumber!=null && mobileNumber.isDisplayed()){
			FillUtils.fillInputOrFail(getTest(), mobileNumber , contactNumber , "MobileNumber not entered");
		}



		WebElement areadCodeElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_CODE);
		if (areadCodeElt!=null && areadCodeElt.isDisplayed()){
			areadCodeElt.clear();
			FillUtils.fillInputOrFail(getTest(), areadCodeElt,areaCode, "Area Code not entered");
		}

		WebElement contactNumberElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_NUMBER);
		if (contactNumberElt!=null && contactNumberElt.isDisplayed()){
			contactNumberElt.clear();
			FillUtils.fillInputOrFail(getTest(), contactNumberElt, contactNumber, "Contact Number not entered");
		}

		WebElement contactEmailElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_CONTACT_EMAIL);
		if (contactEmailElt!=null && contactEmailElt.isDisplayed()){
			contactEmailElt.clear();

			FillUtils.fillInputOrFail(getTest(), contactEmailElt, email, "Email not entered");
		}

		WebElement smsNotificationElt = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_SMS_NOTIFICATION);
		if(smsNotificationElt!=null && smsNotificationElt.isDisplayed()){
			FillUtils.fillInputOrFail(getTest(), LOC_IN_PAX_INFO_SMS_NOTIFICATION, smsNotificationNumber, "SMS notification number not entered");
		}
	}


	/**
	 * Implementation Pending , HTML id's are missing
	 * To Validate Price Details Popup
	 * @throws Exception
	 */
	public void validatePriceDetailsPopUp(String title) throws Exception {

		WebElement PriceDetails = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PRICE_DETAILS);
		PriceDetails.click();

		WebElement priceDeatilsTitle = CheckUtils.getElement(getTest(), LOC_IN_PRICE_DEATILS_POPUP_TEXT);

		if(priceDeatilsTitle.getText().trim().equals(title)){
			reporter.report("Price Details popup", "Validating price details");
			WebElement currency = CheckUtils.getElements(getTest(), LOC_IN_PRICE_DETAILS_CURRENCY).get(0);
			if(currency.getText().trim().equals(getValue("CURRENCY"))){
				reporter.report("Currency","Currency is Valid");
			}
		}
		List<WebElement> closeLinks = CheckUtils.getElements(getTest(), LOC_WL_PAX_INFO_PRICE_DETAILS_POPUP_CLOSE);
		for (WebElement close : closeLinks) {
			if (close.isDisplayed()) {
				close.click();
				break;
			}
		}
	}



	public void validatePriceDetailsPopUpForRetrievedPNR() throws Exception {

		WebElement PriceDetails = CheckUtils.getElement(getTest(), LOC_IN_PAX_INFO_PRICE_DETAILS);
		PriceDetails.click();

		List<WebElement> fareTables = CheckUtils.getElements(getTest(), LOC_WL_PAX_INFO_PRICE_DETAILS_POPUP_FARE);
		WebElement fareTable = null;
		for (WebElement table : fareTables) {
			if (table.isDisplayed()) {
				fareTable = table;
			}
		}

		List<WebElement> priceTableHeader = fareTable.findElements(By.className("faretabcolor"));
		if (!priceTableHeader.get(0).getText().contains("TOTAL FARE") ||
				!priceTableHeader.get(1).getText().contains("TAXES AND SURCHARGES")) {
			reporter.reportFailed(getName(), "The price details are not displayed properly in the View Price Details Pop Up");
		}

		List<WebElement> priceTableDetails = fareTable.findElements(LOC_WL_PAX_INFO_PRICE_DETAILS_POPUP_FARE_TABLE);

		if (priceTableDetails.get(1).getText().contains("(SGAD) Passenger Service Charge") ||
				priceTableDetails.get(1).getText().contains("(OPAE) Aviation Levy") ||
				priceTableDetails.get(1).getText().contains("(PHTO) Travel / Non-Resident Head Tax ") ||
				priceTableDetails.get(1).getText().contains("(OOSE) Passenger Security Service Charge") ||
				priceTableDetails.get(1).getText().contains("(GBAD) Air Passenger Duty (APD) (Domestic/International)") ||
				priceTableDetails.get(1).getText().contains("(UBAS) Passenger Service Charge - (Domestic/International)")) {
			reporter.reportPassed(getName(), "Government Sub-Taxes are Displayed properly");
		}
		else {
			reporter.reportFailed(getName(), "Government Sub-Taxes are not Displayed properly");
		}

		if (priceTableDetails.get(1).getText().contains("(YQAC) Airline Fuel Surcharge ") ||
				priceTableDetails.get(1).getText().contains("(YQAD) Airline Insurance")) {
			reporter.reportPassed(getName(), "Airlines Sub-Taxes are Displayed properly");
		}
		else {
			reporter.reportFailed(getName(), "Airlines Sub-Taxes are not Displayed properly");
		}

		if (priceTableDetails.get(0).getText().contains("Adult Passenger") ||
				priceTableDetails.get(1).getText().contains("Adult Passenger")) {
			if (!priceTableDetails.get(2).getText().contains("Charges to the airlines")) {
				reporter.reportFailed(getName(), "Airlines Charges are not displayed properly for the ADULT Passengers");
			}
			if (!priceTableDetails.get(3).getText().contains("Government, authority and airport charges")) {
				reporter.reportFailed(getName(), "Government Charges are not displayed properly for the ADULT Passengers");
			}
		}
		else {
			reporter.reportFailed(getName(),
			"The price details are not displayed properly  for ADULT Passengers in the View Price Details Pop Up");
		}

		if (!getValue("NB CHILD").contains("0")) {
			if (priceTableDetails.get(0).getText().contains("Child Passenger") ||
					priceTableDetails.get(1).getText().contains("Child Passenger")) {

				if (!priceTableDetails.get(5).getText().contains("Government, authority and airport charges")) {
					reporter.reportFailed(getName(), "Government Charges are not displayed properly for the CHILD Passengers");
				}
				if (!priceTableDetails.get(4).getText().contains("Charges to the airlines")) {
					reporter.reportFailed(getName(), "Airlines Charges are not displayed properly for the CHILD Passengers");
				}
			}
			else {
				reporter.reportFailed(getName(),
				"The price details are not displayed properly  for CHILD Passengers in the View Price Details Pop Up");
			}
		}

		String totalPrice = CheckUtils
		.getElements(getTest(), LOC_PB_PAX_INFO_RETRIEVED_PNR_PRICE_DETAILS_POPUP_TOTAL_PRICE).get(2).getText()
		.trim();
		if (totalPrice.equals(getValue("TOTAL FARE").trim())) {
			reporter.reportPassed(pageName, "Total Price is displayed correctly in the Price details pop up");
		}
		else {
			reporter.reportFailed(pageName, "Total Price is not displayed correctly in the Price Details pop up");
		}

		List<WebElement> closeLinks = CheckUtils.getElements(getTest(), LOC_WL_PAX_INFO_PRICE_DETAILS_POPUP_CLOSE);
		for (WebElement close : closeLinks) {
			if (close.isDisplayed()) {
				close.click();
				break;
			}
		}
	}
}