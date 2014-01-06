package com.amadeus.selenium.test.MeRciIA;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.TAMPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.TAMSearchPage;
/**
 * A sample test for Booking Flow
 * @author RVADDI
 * TestId : 5080 - Check for Image and code snippets in PURC Page
 * TestId : 5081 - CyberSource revalidation check
 * TestId : 5083 - CPF in BR Language
 *
 */
public class Test_TAM_VerifyImgae_PURCPage extends SeleniumSEPTestAdvanced{
	//Local Setup -- Required only during maintenance
	@Override
	public void localSetUp() throws Exception {
		//comment this once coding is done
		setDriverClass(FirefoxDriver.class);
		setBaseUrl("http://nceetvqanlb24.dev.amadeus.net/");
		setDebugMode(false);
	}
	@Before
	public void resizeBrowser(){
		getDriverInstance().manage().window().setSize(new Dimension(600,800));
	}
	@Test
	public void scenario() throws Exception {
		HelperWelcome.openWelcomePage();
		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		// HomePage homePage = welcomepage.changeLocalPreferences("Iceland", "GB" , false);
		TAMSearchPage searchPage= (TAMSearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.TAM);
		searchPage.fillSearchPage();
		searchPage.clickSearchButton();
		OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
		outBoundAvailPage.selectDirectFlight();
		FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
		fareReviewPage.validateFareReviewPage();
		TAMPaxInfoPage paxInfoPage = (TAMPaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.TAM);
		paxInfoPage.fillPaxInfo();
		paxInfoPage.fillContactInfo("Mobile", "", homePage.getValue("MobileNo"), homePage.getValue("Passenger_EmailId"), homePage.getValue("CountryCode"), homePage.getValue("SMSNo"));
		TAMPaymentPage paymentPage =  (TAMPaymentPage)paxInfoPage.continuePayment(Customer.TAM);

		homePage.setInputValue("Card Number","afda432");
		String cardNo=homePage.getInputValue("Card Number");

		paymentPage.cyberSourceValidation();
		paymentPage.fillPayDetailWIthOutCardNO(homePage.getValue("ADDRESS_1"),homePage.getValue("ADDRESS_2"),homePage.getValue("CITY_PAYMENT"),
				homePage.getValue("STATE_PAYMENT"),homePage.getValue("ZIPCODE_PAYMENT"),homePage.getValue("COUNTRY_PAYMENT"));
		paymentPage.fillIncorrectSecurityCardInfo(cardNo);
		paymentPage.clickContinueAnyWay();

		paymentPage.fillIncorrectCreditNo();
		paymentPage.fillPaymentInfo();

		//paymentPage.cyberSourceValidation();
		paymentPage.clickContinue1();

		ConfirmationPage confirmationPage1 = PageFactory.getPageObject(ConfirmationPage.class);
		confirmationPage1.validateConfirmationPage();
		String failedSteps = this.getFailedSteps();
		if (failedSteps != null && failedSteps.length() > 0) {
			reporter.reportPassed("TAM", "Validation(s) failed");
		}
	}
}