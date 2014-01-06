package com.amadeus.selenium.sqmobile.page.payment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.extension.ByList;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class CommonPaymentPage extends SqMobileCommonPage implements IPayment {


  protected final static By LOC_IN_PAYMENT_CARDHOLDER_NAME = By.id("AIR_CC_NAME_ON_CARD");
  protected final static By LOC_IN_PAYMENT_CARD_TYPE = By.id("AIR_CC_TYPE");
  protected final static By LOC_IN_PAYMENT_CARD_NUMBER = By.id("AIR_CC_NUMBER");
  protected final static By LOC_IN_PAYMENT_CARD_EXPIRY_MONTH = By.id("CCexpiryDateMonth");
  protected final static By LOC_IN_PAYMENT_CARD_EXPIRY_YEAR = By.id("CCexpiryDateYear");
  protected final static By LOC_IN_PAYMENT_CARD_CVV= By.className("securitymask");
  protected final static By LOC_IN_PAYMENT_ADDRESS_1= By.id("AIR_CC_ADDRESS_FIRSTLINE");
  protected final static By LOC_IN_PAYMENT_ADDRESS_2 = By.id("AIR_CC_ADDRESS_SECONDLINE");
  protected final static By LOC_IN_PAYMENT_ADDRESS_CITY = By.id("AIR_CC_ADDRESS_CITY");
  protected final static By LOC_IN_PAYMENT_ADDRESS_STATE= By.id("AIR_CC_ADDRESS_STATE");
  protected final static By LOC_IN_PAYMENT_ADDRESS_CODE = By.id("AIR_CC_ADDRESS_ZIPCODE");
  protected final static By LOC_IN_PAYMENT_ADDRESS_COUNTRY = By.id("AIR_CC_ADDRESS_COUNTRY_TXT");
  protected final static By LOC_TX_PAYMENT_ADDRESS_PREFILL = By.id("checkbox1");
  protected final static By LOC_PB_PAYMENT_FARE_CONDITIONS_POPUP_CLOSE = By.className("calendarClose");
  protected final static By LOC_CB_PAYMENT_CHECK_BOX = By.id("CheckPenaliesBox");
  protected final static By LOC_BT_PAYMENT_CONFIRM_PURCHASE = By.id("btnConfirm");
  protected final static By LOC_BT_PAYMENT_CANCEL_BOOKING = By.cssSelector(".buttonDirection.floatL");
  protected final static By LOC_PB_PAYMENT_PAGE_PRICE_DETAILS_POPUP_CLOSE = By.className("calendarClose");
  protected final static By LOC_TX_PAYMENT_PAGE_PRICE_DETAILS_POPUP_TOTAL_PRICE = new ByList(By.id("fareBrkdwnPriceTotal"),
      By.className("withoutInsuranceTotal"));
  protected final static By LOC_TX_PAYMENT_PAGE_PRICE_DETAILS = By.className("priceDetails");
  protected final static By LOC_TX_PAYMENT_PAGE_PRICE_DETAILS_POPUP_FARE_TABLE = By.className("fareTabPop");
  protected final static By LOC_TX_PAYMENT_PAGE_PRICE_DETAILS_POPUP_FARE = By.cssSelector(".wrapper.pop");
  protected final static By LOC_PB_PAYMENT_TO_PAY = By.cssSelector(".floatR.insureInfo");
  protected final static By LOC_IN_BOOKING_CONDITIONS_CATEGORY= By.className("umnrancpopup");

  //----------------------------------------------------------------------------


  public CommonPaymentPage(SeleniumSEPTest test) throws Exception{
    super(test);

  }

  private String pageName = "Payment Page";


  /**
   * To Fill Payment Details
   *  - History Created by : Vigneshwaran Balasubramanian
   */

  public void fillPaymentDetail(String Address1,String Address2,String City,String State,String zipCode,String Country) throws Exception {

    //Update Payment section
    fillPaymentInfo();

    //Update Address section
    fillAddressDetails(Address1,Address2,City,State,zipCode,Country);

  }


  public ConfirmationPage clickContinue()throws Exception {
    CheckUtils.getElement(getTest(), LOC_CB_PAYMENT_CHECK_BOX).click();
    WaitUtils.wait(1);
    WebElement confirm = CheckUtils.getElement(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE);
    if (confirm!=null && confirm.isDisplayed()){
      confirm.click();
      waitForOverlayLoading(120);
      reporter.reportPassed(pageName, "Confirm Purchase button is clicked");
    }
    else{
      reporter.reportFailed(pageName, "Confirm purchase button is not displayed");
    }

    return PageFactory.getPageObject(ConfirmationPage.class);
  }
  //****************************************************************************




  /**
   * To Fill Payment Details
   * @throws Exception
   *  - History Created by : Vigneshwaran Balasubramanian
   */
  public void fillPaymentInfo()throws Exception {

    WebElement cardHolderNameElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARDHOLDER_NAME);
    WebElement cardTypeElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_TYPE);
    WebElement cardNumberElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_NUMBER);
    WebElement cardExpiryMonthElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_EXPIRY_MONTH);
    WebElement cardExpiryYearElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_EXPIRY_YEAR);
    WebElement cardCVVElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_CVV);

    CommonUtils utils =  PageFactory.getPageObject(CommonUtils.class);
    String Dep = utils.addDate("dd MMMM yyyy",  new Integer (getValue("Card Expiry Days")));
    String CardExpiryMonth = (Dep.split(" "))[1];
    String CardExpiryYear = (Dep.split(" "))[2];

    String CardHolderName = getValue("Card Holder Name");
    String CardType =getValue("Card Type").trim();
    String CardNumber =getValue("Card Number").trim();
    String CardCVV = getValue("Card CVV").trim();

    WaitUtils.wait(3);

    //Update Card Holder Name
    if (cardHolderNameElt!=null && cardHolderNameElt.isDisplayed()){
      cardHolderNameElt.clear();
      FillUtils.fillInputOrFail(getTest(), cardHolderNameElt, CardHolderName, "Card Holder not  entered");
      reporter.reportPassed("CardHolder Name filled : ", CardHolderName);
    }
    else {
      reporter.reportFailed(pageName, "Card Holder Name field is not displayed");}

    //Select Card Type
    if (cardTypeElt!=null && cardTypeElt.isDisplayed()){
      Select cardtype = new Select (cardTypeElt);
      List<WebElement> options = cardtype.getOptions();
      if (options.size()==4){
        reporter.reportPassed(pageName, "All the 4 card types are displayed");
      }
      cardtype.selectByValue(CardType);
      reporter.reportPassed("Card Type : ",  "Card Type Selected successfully");
    }
    else {
      reporter.reportFailed(pageName, "Card Type field is not displayed");}

    //Update Card Number
    if (cardNumberElt!=null && cardNumberElt.isDisplayed()){
      if (!cardNumberElt.getAttribute("value").equals("")){
        reporter.reportFailed(pageName, "Card Number field is not empty");
      }
      cardNumberElt.clear();
      FillUtils.fillInputOrFail(getTest(), cardNumberElt, CardNumber, "Card Number not entered");
      reporter.reportPassed("Card Number filled : ", CardNumber);
    }
    else {
      reporter.reportFailed(pageName, "Card Number field is not displayed");}

    //Update Card expiry date
    if (cardExpiryMonthElt!=null && cardExpiryMonthElt.isDisplayed()){
      Select cardexpiryMonth = new Select (cardExpiryMonthElt);
      cardexpiryMonth.selectByVisibleText(CardExpiryMonth);
      reporter.reportPassed("CardExpiry Month filled : ", CardExpiryMonth);
    }
    else {
      reporter.reportFailed(pageName, "Card Expiry Month  field is not displayed");}

    if (cardExpiryYearElt!=null && cardExpiryYearElt.isDisplayed()){
      Select cardexpiryYear = new Select (cardExpiryYearElt);
      cardexpiryYear.selectByVisibleText(CardExpiryYear);
    reporter.reportPassed("CardExpiry Year filled : ", CardExpiryYear);
    }
    else {
      reporter.reportFailed(pageName, "Card Expiry Year field is not displayed");}

    //Update Card CVV
    if (cardCVVElt!=null && cardCVVElt.isDisplayed()){
      if (!cardCVVElt.getAttribute("value").equals("")){
        reporter.reportFailed(pageName, "Card CVV field is not empty");
      }
      cardCVVElt.clear();
      FillUtils.fillInputOrFail(getTest(), cardCVVElt, CardCVV, "Card CVV not entered");
      reporter.reportPassed("CardCVV filled : ", CardCVV);
    }
    else {
      reporter.reportFailed(pageName, "Card Holder Name field is not displayed");}
  }

  /**
   * To Fill Address Details
   * @param Address1
   * @param Address2
   * @param City
   * @param State
   * @param zipCode
   * @param Country
   * @throws Exception
   *  - History Created by : Vigneshwaran Balasubramanian
   */
  public void fillAddressDetails(String Address1,String Address2,String City,String State,String zipCode,String Country)throws Exception {

    WebElement addressLine1 = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_ADDRESS_1);
    if (addressLine1!=null && addressLine1.isDisplayed()){
      addressLine1.clear();
      FillUtils.fillInputOrFail(getTest(), addressLine1,Address1, "Address Line 1 not entered");
      reporter.reportPassed("AddressLine 1 filled : ", Address1);
    }
    else{
      reporter.reportFailed(pageName, "Address line One field is not displayed");}

    WebElement addressLine2 = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_ADDRESS_2);
    if (addressLine2!=null && addressLine2.isDisplayed()){
      addressLine2.clear();
      FillUtils.fillInputOrFail(getTest(), addressLine2, Address2, "Address Line 2 not entered");
      reporter.reportPassed("AddressLine 2 filled : ", Address2);
    }
    else{
      reporter.reportFailed(pageName, "Address line two field is not displayed");}

    WebElement addressCity = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_ADDRESS_CITY);
    if (addressCity!=null && addressCity.isDisplayed()){
      addressCity.clear();
      FillUtils.fillInputOrFail(getTest(), addressCity, City, "City not entered");
      reporter.reportPassed("City filled : ", City);
    }
    else{
      reporter.reportFailed(pageName, "Address city field is not displayed");}

    WebElement addressState = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_ADDRESS_STATE);
    if (addressState!=null && addressState.isDisplayed()){
      addressState.clear();
      FillUtils.fillInputOrFail(getTest(), addressState, State, "State not entered");
      reporter.reportPassed("Address State filled : ", State);
    }
    else{
      reporter.reportFailed(pageName, "Address state field is not displayed");}

    WebElement addressCode = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_ADDRESS_CODE);
    if (addressCode!=null && addressCode.isDisplayed()){
      addressCode.clear();
      FillUtils.fillInputOrFail(getTest(), addressCode, zipCode, "Address code not entered");
      reporter.reportPassed("Address Code filled : ", zipCode);
    }
    else{
      reporter.reportFailed(pageName, "Address Zip code field is not displayed");}

    WebElement addressCountry = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_ADDRESS_COUNTRY);
    if (addressCountry!=null && addressCountry.isDisplayed()){
      addressCountry.clear();
      FillUtils.fillInputOrFail(getTest(), addressCountry, Country, "Country Field not entered");
      reporter.reportPassed("Address Country filled : ", Country);
    }
    else{
      reporter.reportFailed(pageName, "Address Country field is not displayed");}
  }


  // ****************************************************************************
  // FUNCTION NAME :fillPromotionCode
  // INPUT PARAM :Promotion code that is needed to be filled in the PromotionCode Section
  // OUTPUT PARAM :NA
  // DESCRIPTION :To fill Promotion Code
  // NOTE :None

  public void cancelBooking() throws Exception {

    ClickUtils
    .clickButtonOrFail(getTest(), LOC_BT_PAYMENT_CANCEL_BOOKING, "CancelButton Couldn't be pressed");
    driver.switchTo().alert().accept();
  }
  // ****************************************************************************

  /*
   * Method need to be verified if all Element id used is the same , or needs any modification
   *Vigneshwaran
   */
  // ****************************************************************************
  // FUNCTION NAME :validatePriceDetailsPopUp
  // INPUT PARAM :NA
  // OUTPUT PARAM :NA
  // DESCRIPTION :To validate the Price Details pop Up
  // NOTE :None

  public void validatePriceDetailsPopUp() throws Exception {

    WebElement PriceDetails = CheckUtils.getElement(getTest(), LOC_TX_PAYMENT_PAGE_PRICE_DETAILS);
    PriceDetails.click();

    List<WebElement> fareTables = CheckUtils.getElements(getTest(), LOC_TX_PAYMENT_PAGE_PRICE_DETAILS_POPUP_FARE);
    WebElement fareTable = null;
    for (WebElement table : fareTables) {
      if (table.isDisplayed()) {
        fareTable = table;
      }
    }

    List<WebElement> priceTableHeader = fareTable.findElements(By.className("faretabcolor"));
    if (!priceTableHeader.get(0).getText().contains("TOTAL FARE") ||
        !priceTableHeader.get(1).getText().contains("TAXES AND SURCHARGES")) {
      reporter.reportFailed(pageName, "The price details are not displayed properly in the View Price Details Pop Up");
    }

    List<WebElement> priceTableDetails = fareTable.findElements(LOC_TX_PAYMENT_PAGE_PRICE_DETAILS_POPUP_FARE_TABLE);

    if (priceTableDetails.get(1).getText().contains("(SGAD) Passenger Service Charge") ||
        priceTableDetails.get(1).getText().contains("(OPAE) Aviation Levy") ||
        priceTableDetails.get(1).getText().contains("(PHTO) Travel / Non-Resident Head Tax ") ||
        priceTableDetails.get(1).getText().contains("(OOSE) Passenger Security Service Charge") ||
        priceTableDetails.get(1).getText().contains("(GBAD) Air Passenger Duty (APD) (Domestic/International)") ||
        priceTableDetails.get(1).getText().contains("(UBAS) Passenger Service Charge - (Domestic/International)")) {
      reporter.reportPassed(pageName, "Government Sub-Taxes are Displayed properly");
    }
    else {
      reporter.reportFailed("Govt Sub-Taxes : ", priceTableDetails.get(1).getText());
    }

    if (priceTableDetails.get(1).getText().contains("(YQAC) Airline Fuel Surcharge ") ||
        priceTableDetails.get(1).getText().contains("(YQAD) Airline Insurance")) {
      reporter.reportPassed(pageName, "Airlines Sub-Taxes are Displayed properly");
    }
    else {
      reporter.reportFailed("AirLines Sub-Taxes Displayed : ", priceTableDetails.get(1).getText());
    }

    if (priceTableDetails.get(0).getText().contains("Adult Passenger") ||
        priceTableDetails.get(1).getText().contains("Adult Passenger")) {
      if (!priceTableDetails.get(2).getText().contains("Charges to the airlines")) {
        reporter.reportFailed(pageName, "Airlines Charges are not displayed properly for the ADULT Passengers");
      }
      if (!priceTableDetails.get(3).getText().contains("Government, authority and airport charges")) {
        reporter.reportFailed("Government Charges for Adult Pax : ", priceTableDetails.get(3).getText());
      }
    }
    else {
      reporter.reportFailed(pageName,
      "The price details are not displayed properly  for ADULT Passengers in the View Price Details Pop Up");
    }

    if (!getValue("NB CHILD").contains("0")) {
      if (priceTableDetails.get(0).getText().contains("Child Passenger") ||
          priceTableDetails.get(1).getText().contains("Child Passenger")) {

        if (!priceTableDetails.get(5).getText().contains("Government, authority and airport charges")) {
          reporter.reportFailed("Govt charges for Child Pax : ", priceTableDetails.get(5).getText());
        }
        if (!priceTableDetails.get(4).getText().contains("Charges to the airlines")) {
          reporter.reportFailed("Airline Charges for Child Pax",priceTableDetails.get(4).getText());
        }
      }
      else {
        reporter.reportFailed(pageName,
        "The price details are not displayed properly  for CHILD Passengers in the View Price Details Pop Up");
      }
    }

    String totalPrice = null;
    List<WebElement> totPriceList = CheckUtils.getElements(getTest(),
        LOC_TX_PAYMENT_PAGE_PRICE_DETAILS_POPUP_TOTAL_PRICE);
    for (WebElement totPrice : totPriceList) {
      if (totPrice.isDisplayed()) {
        totalPrice = totPrice.getText().trim();
      }
    }

    if (totalPrice.equals(getValue("TOTAL FARE").trim())) {
      reporter.reportPassed("Total Price matched  : ", totalPrice);
    }
    else {
      reporter.reportFailed("Total Price Displayed  : ", totalPrice);
    }

    List<WebElement> closeLinks = CheckUtils.getElements(getTest(), LOC_PB_PAYMENT_PAGE_PRICE_DETAILS_POPUP_CLOSE);
    for (WebElement close : closeLinks) {
      if (close.isDisplayed()) {
        close.click();
        break;
      }
    }
  }


  // ****************************************************************************
  // FUNCTION NAME :balanceToPay
  // INPUT PARAM :NA
  // OUTPUT PARAM :NA
  // DESCRIPTION :To check the amount be paid
  // NOTE :None

  public String balanceToPay() throws Exception {

    List<WebElement> toPay = CheckUtils.getElements(getTest(), LOC_PB_PAYMENT_TO_PAY);
    String toBePaid = toPay.get(1).getText();
    String amtToPay = toBePaid.split(" ")[1];
    addValue("TO PAY", amtToPay);
    return amtToPay;
  }
  // ****************************************************************************

  public void fillPayDetailWIthOutCardNO(String Address1,String Address2,String City,String State,String zipCode,String Country) throws Exception {
	  fillAddressDetails(Address1,Address2,City,State,zipCode,Country);
	  fillPaymentInfoWithOutCardNo();
	  }
  public void fillPaymentInfoWithOutCardNo()throws Exception {
	    WebElement cardHolderNameElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARDHOLDER_NAME);
	    WebElement cardTypeElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_TYPE);
	    WebElement cardNumberElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_NUMBER);
	    WebElement cardExpiryMonthElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_EXPIRY_MONTH);
	    WebElement cardExpiryYearElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_EXPIRY_YEAR);
	    WebElement cardCVVElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_CVV);

	    CommonUtils utils =  PageFactory.getPageObject(CommonUtils.class);
	    String Dep = utils.addDate("dd MMMM yyyy",  new Integer (getValue("Card Expiry Days")));
	    String CardExpiryMonth = (Dep.split(" "))[1];
	    String CardExpiryYear = (Dep.split(" "))[2];

	    String CardHolderName = getValue("Card Holder Name");
	    String CardType =getValue("Card Type").trim();
	    String CardNumber =getValue("Card Number").trim();
	    String CardCVV = getValue("Card CVV").trim();

	    WaitUtils.wait(3);

	    //Update Card Holder Name
	    if (cardHolderNameElt!=null && cardHolderNameElt.isDisplayed()){
	      cardHolderNameElt.clear();
	      FillUtils.fillInputOrFail(getTest(), cardHolderNameElt, CardHolderName, "Card Holder not  entered");
	      reporter.reportPassed("CardHolder Name filled : ", CardHolderName);
	    }
	    else {
	      reporter.reportFailed(pageName, "Card Holder Name field is not displayed");}

	    //Select Card Type
	    if (cardTypeElt!=null && cardTypeElt.isDisplayed()){
	      Select cardtype = new Select (cardTypeElt);
	      List<WebElement> options = cardtype.getOptions();
	      if (options.size()==4){
	        reporter.reportPassed(pageName, "All the 4 card types are displayed");
	      }
	      cardtype.selectByValue(CardType);
	      reporter.reportPassed("Card Type : ",  "Card Type Selected successfully");
	    }
	    else {
	      reporter.reportFailed(pageName, "Card Type field is not displayed");}

	    //Update Card Number
	    if (cardNumberElt!=null && cardNumberElt.isDisplayed()){
	      if (!cardNumberElt.getAttribute("value").equals("")){
	        reporter.reportFailed(pageName, "Card Number field is not empty");
	      }
	      cardNumberElt.clear();
	  /*    FillUtils.fillInputOrFail(getTest(), cardNumberElt, CardNumber, "Card Number not entered");*/
	      reporter.reportPassed("Card Number filled -- Empty : ", CardNumber);
	    }
	    else {
	      reporter.reportFailed(pageName, "Card Number field is not displayed");}

	    //Update Card expiry date
	    if (cardExpiryMonthElt!=null && cardExpiryMonthElt.isDisplayed()){
	      Select cardexpiryMonth = new Select (cardExpiryMonthElt);
	      cardexpiryMonth.selectByVisibleText(CardExpiryMonth);
	      reporter.reportPassed("CardExpiry Month filled : ", CardExpiryMonth);
	    }
	    else {
	      reporter.reportFailed(pageName, "Card Expiry Month  field is not displayed");}

	    if (cardExpiryYearElt!=null && cardExpiryYearElt.isDisplayed()){
	      Select cardexpiryYear = new Select (cardExpiryYearElt);
	      cardexpiryYear.selectByVisibleText(CardExpiryYear);
	    reporter.reportPassed("CardExpiry Year filled : ", CardExpiryYear);
	    }
	    else {
	      reporter.reportFailed(pageName, "Card Expiry Year field is not displayed");}

	    //Update Card CVV
	    if (cardCVVElt!=null && cardCVVElt.isDisplayed()){
	      if (!cardCVVElt.getAttribute("value").equals("")){
	        reporter.reportFailed(pageName, "Card CVV field is not empty");
	      }
	      cardCVVElt.clear();
	      FillUtils.fillInputOrFail(getTest(), cardCVVElt, CardCVV, "Card CVV not entered");
	      reporter.reportPassed("CardCVV filled : ", CardCVV);
	    }
	    else {
	      reporter.reportFailed(pageName, "Card Holder Name field is not displayed");}
	  }
}
