package com.amadeus.selenium.sqmobile.page.payment.customers;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.payment.CommonPaymentPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * TAM Payment page
 * @author vbalasubramanian
 *
 */
public class TAMPaymentPage extends CommonPaymentPage  {

	protected static final  By LOC_IN_DOCUMENT_CHECKIN_ID = By.id("DOCUMENT_TYPE_1");
	protected static final By LOC_IN_DOCUMENT_CHECKIN_NUMBER = By.id("GEN_DOCUMENT_NUMBER_1");

	protected static final By LOC_IN_FULL_HOLDER_NAME = By.id("AIR_CC_FULL_HOLDER_NAME");
	protected static final By LOC_IN_FULL_HOLDER_NUMBER= By.id("AIR_CC_CUSTOMER_NUMBER");

	protected static final By LOC_IN_FULL_HOLDER_DOB_DAY= By.id("sscDobDay");
	protected static final By LOC_IN_FULL_HOLDER_DOB_MONTH= By.id("sscDobMonth");
	protected static final By LOC_IN_FULL_HOLDER_DOB_YEAR= By.id("sscDobYear");

	protected final static By LOC_BT_PAYMENT_CANCEL_BOOKING = By.cssSelector(".validation.cancel");

	protected final static By LOC_ID_CYBERSOURCE = By.id("w7_cyberSource");
	protected final static By LOC_ID_CYBERSOURCE_IMG_TAG = By.tagName("img");
	protected final static By LOC_ID_CYBERSOURCE_FLASH = By.id("thm_fp");
	protected final static By LOC_ID_CYBERSOURCE_JSCRIPT_TAG = By.tagName("script");
	protected final static String SRC_ATTRIBUTE = "src";
	protected final static String COMPARE_CYBERSOURCE_IMG_STRING = "clear.png";
	protected final static String COMPARE_CYBERSOURCE_JSCRIPT_STRING = "check.js";



	public TAMPaymentPage(SeleniumSEPTest test) throws Exception {
		super(test);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Fill Adress Details
	 */
	@Override
	public void fillAddressDetails(String Address1, String Address2, String City, String State, String zipCode,
			String Country) throws Exception {
		// TODO Auto-generated method stub
		super.fillAddressDetails(Address1, Address2, City, State, zipCode, Country);
	}

	/**
	 * Fill Identification Checkin
	 * @param id
	 * @param documentNo
	 */
	public void fillIdentificationCheckin(String id , String documentNo){
		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_DOCUMENT_CHECKIN_ID, "Document checkin id not present");
		FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_DOCUMENT_CHECKIN_ID, id, "document checkin id not selected");
		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_DOCUMENT_CHECKIN_NUMBER, "Document checkin number not entered");
		FillUtils.fillInputOrFail(getTest(), LOC_IN_DOCUMENT_CHECKIN_NUMBER, documentNo, "document checkin id not selected");
	}

	/**
	 * Fill Social Security Card info
	 * @throws IOException
	 */
	public void fillSocialSecurityCardInfo() throws IOException{
		String CardHolderName = getValue("Card Holder Name");
		String CardNumber =getValue("Card Number");
		CardNumber = CardNumber.substring(0, CardNumber.length()-2);

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_NAME, "Full holder name field not present");
		FillUtils.fillInputOrFail(getTest(), LOC_IN_FULL_HOLDER_NAME, CardHolderName, "full holder name not entered");

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_NUMBER, "full holder number not present");
		FillUtils.fillInputOrFail(getTest(), LOC_IN_FULL_HOLDER_NUMBER, CardNumber, "full holder number not present");

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_DOB_DAY, "full holder number not present");
		FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_FULL_HOLDER_DOB_DAY, "1", "full holder number not present");

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_DOB_MONTH, "full holder number not present");
		FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_FULL_HOLDER_DOB_MONTH, "Jan", "full holder number not present");

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_DOB_YEAR, "full holder number not present");
		FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_FULL_HOLDER_DOB_YEAR, "1990", "full holder number not present");
	}

	/**
	 * Cancel Booking
	 *
	 */
	@Override
	public void cancelBooking() throws Exception {

		ClickUtils
		.clickButtonOrFail(getTest(), LOC_BT_PAYMENT_CANCEL_BOOKING, "CancelButton Couldn't be pressed");
		driver.switchTo().alert().accept();
	}

	@Override
	public ConfirmationPage clickContinue()throws Exception {
		CheckUtils.getElement(getTest(), LOC_CB_PAYMENT_CHECK_BOX).click();
		WaitUtils.wait(1);
		WebElement confirm = CheckUtils.getElement(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE);
		if (confirm!=null && confirm.isDisplayed()){
			confirm.click();
			waitForOverlayLoading(200);
			reporter.reportPassed("TAMPayment Page", "Confirm Purchase button is clicked");
		}
		else{
			reporter.reportFailed("TAMPayment Page", "Confirm purchase button is not displayed");
		}

		return PageFactory.getPageObject(ConfirmationPage.class);
	}
	//****************************************************************************

	/**
	 * Method to validate cyberSource on PURCPage for TAM
	 * @author rshivaswamy
	 */
	public void cyberSourceValidation() throws Exception{

		boolean isImagePresent = false;
		boolean isJScriptPresent = false;
		boolean isFlashPresent = false;

		WebElement cyberSource = CheckUtils.getElement(getTest(), LOC_ID_CYBERSOURCE);

		/*
		 * get the image src string and check for "clear.png"
		 */
		String imageLocation = (CheckUtils.getElement(getTest(), cyberSource, LOC_ID_CYBERSOURCE_IMG_TAG)).getAttribute(SRC_ATTRIBUTE);

		if(imageLocation.contains(COMPARE_CYBERSOURCE_IMG_STRING)){
			isImagePresent = true;
		}

		/*
		 * check wthr flash player is displayed or not
		 */
		WebElement flashPlayer = CheckUtils.getElement(getTest(), cyberSource, LOC_ID_CYBERSOURCE_FLASH);
		if(flashPlayer.isDisplayed()){
			isFlashPresent = true;
		}

		/*
		 * Check wthr JScript is displayed or not
		 */
		String jScriptLocation = CheckUtils.getElement(getTest(), cyberSource, LOC_ID_CYBERSOURCE_JSCRIPT_TAG).getAttribute(SRC_ATTRIBUTE);
		if(jScriptLocation.contains(COMPARE_CYBERSOURCE_JSCRIPT_STRING)){
			isJScriptPresent = true;
		}

		if( isImagePresent && isJScriptPresent && isFlashPresent ){
			reporter.reportPassed("TAMPayment Page", "Cyber Source validation was SUCCESSFULL");
		}else{
			reporter.reportFailed("TAMPayment Page", "Cyber Source validation was NOT SUCCESSFULL");
		}

	}

	public void fillIncorrectSecurityCardInfo(String socialSecurityCardNo) throws IOException{
		String CardHolderName = getValue("Card Holder Name");
		String CardNumber =socialSecurityCardNo;
		CardNumber = CardNumber.substring(0, CardNumber.length()-2);

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_NAME, "Full holder name field not present");
		FillUtils.fillInputOrFail(getTest(), LOC_IN_FULL_HOLDER_NAME, CardHolderName, "full holder name not entered");

		fillIncorrectInfo(CardNumber);

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_DOB_DAY, "full holder number not present");
		FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_FULL_HOLDER_DOB_DAY, "1", "full holder number not present");

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_DOB_MONTH, "full holder number not present");
		FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_FULL_HOLDER_DOB_MONTH, "Jan", "full holder number not present");

		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_DOB_YEAR, "full holder number not present");
		FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_IN_FULL_HOLDER_DOB_YEAR, "1990", "full holder number not present");
	}
	public void fillIncorrectInfo(String socialSecurityCardNo){
		String CardNumber =socialSecurityCardNo;
		CardNumber = CardNumber.substring(0, CardNumber.length()-2);
		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_NUMBER, "full holder number not present");
		FillUtils.fillInputOrFail(getTest(), LOC_IN_FULL_HOLDER_NUMBER, CardNumber, "full holder number not present");
	}
	public void fillSocialSecurityCardNo() throws Exception{
		String CardNumber =getValue("Card Number");
		CardNumber = CardNumber.substring(0, CardNumber.length()-2);
		CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_FULL_HOLDER_NUMBER, "full holder number not present");
		FillUtils.fillInputOrFail(getTest(), LOC_IN_FULL_HOLDER_NUMBER, CardNumber, "full holder number not present");
	}
	public TAMPaymentPage clickContinueAnyWay()throws Exception {
		CheckUtils.getElement(getTest(), LOC_CB_PAYMENT_CHECK_BOX).click();
		WaitUtils.wait(1);
		WebElement confirm = CheckUtils.getElement(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE);
		if (confirm!=null && confirm.isDisplayed()){
			confirm.click();
			waitForOverlayLoading(200);
			reporter.reportPassed("TAMPayment Page", "Confirm Purchase button is clicked");
		}
		else{
			reporter.reportFailed("TAMPayment Page", "Confirm purchase button is not displayed");
		}

		return PageFactory.getPageObject(TAMPaymentPage.class);
	}

	public ConfirmationPage clickContinue1()throws Exception {
		waitForOverlayLoading(200);
		WebElement confirm = CheckUtils.getElement(getTest(), LOC_BT_PAYMENT_CONFIRM_PURCHASE);
		if (confirm!=null && confirm.isDisplayed()){
			confirm.click();
			waitForOverlayLoading(200);
			reporter.reportPassed("TAMPayment Page", "Confirm Purchase button is clicked");
		}
		else{
			reporter.reportFailed("TAMPayment Page", "Confirm purchase button is not displayed");
		}

		return PageFactory.getPageObject(ConfirmationPage.class);
	}

	public void fillIncorrectCreditNo() throws Exception
	{
		WebElement cardNumberElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_NUMBER);
		fillSocialSecurityCardNo();
		for(int i=1;i<=2;i++){
			String cardNo=getValue("Card Number").trim();
			//String cardNoDigit = null;

			StringBuffer cardNoDigit= new StringBuffer(cardNo);
			int len=cardNoDigit.length();
			if(i==1){
				cardNoDigit.substring(0, len - i);
				System.out.println("card no :1 " + cardNoDigit);
			}
			if(i==2){
				cardNoDigit.append("5934");
				cardNoDigit.substring(0, len);
				System.out.println("card no :2 " + cardNoDigit);
			}


			//Update Card Number Digits '11'
			if (cardNumberElt!=null && cardNumberElt.isDisplayed()){
				if (!cardNumberElt.getAttribute("value").equals("")){
					reporter.reportFailed("Payment Page", "Card Number field is not empty");
				}
				cardNumberElt.clear();
				FillUtils.fillInputOrFail(getTest(), cardNumberElt, cardNoDigit.toString(), "Card Number  Digits "+i+" not entered");
				reporter.reportPassed("Card Number Digit "+i+"  filled : ", cardNoDigit.toString());
			}
			else {
				reporter.reportFailed("Payment Page", "Card Number field is not displayed");
			}
			clickContinueAnyWay();
		}
	}
	public void fillCorrectCreditNo() throws Exception{
		WebElement cardNumberElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_NUMBER);
		 WebElement cardCVVElt = CheckUtils.getElement(getTest(), LOC_IN_PAYMENT_CARD_CVV);
		String cardNo=getValue("Card Number").trim();
		 String CardCVV = getValue("Card CVV").trim();
		//Update Card Number Digits '11'
		if (cardNumberElt!=null && cardNumberElt.isDisplayed()){
			if (!cardNumberElt.getAttribute("value").equals("")){
				reporter.reportFailed("Payment Page", "Card Number field is not empty");
			}
			cardNumberElt.clear();
			System.out.println("correct credit :::::::"+cardNo);
			FillUtils.fillInputOrFail(getTest(), cardNumberElt, cardNo, "Card Number  Digits not entered");
			reporter.reportPassed("Card Number Digit1  filled : ", cardNo);
		}
		else {
			reporter.reportFailed("Payment Page", "Card Number field is not displayed");
		}
		 WaitUtils.wait(3);
		  //Update Card CVV
		    if (cardCVVElt!=null && cardCVVElt.isDisplayed()){
		      if (!cardCVVElt.getAttribute("value").equals("")){
		        reporter.reportFailed("Payment Page", "Card CVV field is not empty");
		      }
		      cardCVVElt.clear();
		      FillUtils.fillInputOrFail(getTest(), cardCVVElt, CardCVV, "Card CVV not entered");
		      reporter.reportPassed("CardCVV filled : ", CardCVV);
		    }
		    else {
		      reporter.reportFailed("Payment Page", "Card Holder Name field is not displayed");
		      }
	}
}
