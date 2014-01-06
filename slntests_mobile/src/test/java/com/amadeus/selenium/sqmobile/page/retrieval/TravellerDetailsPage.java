package com.amadeus.selenium.sqmobile.page.retrieval;

/**
 * Update Traveller Contact Details Page
 * @author Chaitanya Chandrashekar
 * Version :: 1.0
 * TestId ::  7156
 *
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class TravellerDetailsPage extends SqMobileCommonPage{

	protected final static By LOC_PASSENGER_INFO = By.cssSelector("nav[class*=tabs] li:nth-child(2) a");
	protected final static By LOC_HOME_PHONE = By.cssSelector("#CONTACT_POINT_HOME_PHONE");
	protected final static By LOC_EMAIL_ID = By.cssSelector("#CONTACT_POINT_EMAIL_1");
	protected final static By LOC_BUTTON_CANCEL = By.cssSelector(".validation.cancel");
	protected final static By LOC_BUTTON_SAVE = By.cssSelector("footer[class*=buttons] button:nth-child(2)");
	protected final static By LOC_MSG_AFTER_SAVE = By.cssSelector(".msg.info li:nth-child(1)");

	private boolean b =false;

	//Added by TestCase Id 7357
	protected final static By LOC_BUTTON_BACK = By.cssSelector(".back");


	public TravellerDetailsPage(SeleniumSEPTest test) throws Exception {
		super(test);
		// Check that we're on the right page.
		if(! WaitUtils.waitForElementPresent(getTest(), LOC_PASSENGER_INFO, 30)){
			reporter.fail("This is not Traveller Details page");
		}else{
			reporter.reportPassed("Traveller Details page", "Traveller Details page");
			Thread.sleep(5000);
			validateContactInfo("1234567", "test06@amadeus.com");
		}

	}
	public void validateContactInfo(String contactNumber , String email) throws Exception {
		WebElement contactNumberElt = CheckUtils.getElement(getTest(), LOC_HOME_PHONE);
		if (contactNumberElt!=null && contactNumberElt.isDisplayed()){
			contactNumberElt.clear();
			FillUtils.fillInputOrFail(getTest(), contactNumberElt, contactNumber, "Contact Number not entered");
			reporter.reportPassed("Home-Phone :", contactNumberElt.getText());
		}else{
			reporter.reportFailed("Home-Phone :", contactNumberElt.getText());
		}
		WebElement contactEmailElt = CheckUtils.getElement(getTest(), LOC_EMAIL_ID);
		if (contactEmailElt!=null && contactEmailElt.isDisplayed()){
			contactEmailElt.clear();
			FillUtils.fillInputOrFail(getTest(), contactEmailElt, email, "Email not entered");
			reporter.reportPassed("Email -id :", contactEmailElt.getText());
		}else{
			reporter.reportFailed("Email -id  :", contactEmailElt.getText());
		}

		clickSavePassengerInfo();
		//validateMessageAfterSave();
	}


	public void clickSavePassengerInfo()throws Exception {
		if(! WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_SAVE, 30)){
			reporter.fail("Save Button not found");
		}else{
			ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_SAVE, "Save Button not found","Save Button Clicked");
		}
	}

	public void clickCanclePassengerInfo()throws Exception {
		ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_CANCEL, "Cancel Button not found","Cancel Button Clicked");
		clickBackButton();
	}

	//Added for TestCase Id 7357
	public void clickBackButton()throws Exception {
		ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_BACK, "Back Button not Clicked","Back Button Clicked");
	}

	public void validateMessageAfterSave() throws Exception {
		WebElement msgAFterSave = CheckUtils.getElement(getTest(), LOC_MSG_AFTER_SAVE);
		if (msgAFterSave!=null && msgAFterSave.isDisplayed()){
			reporter.reportPassed("Message after click on Save:", msgAFterSave.getText());
		}else{
			reporter.reportFailed("Message after click on Save:", msgAFterSave.getText());
		}
	}
}
