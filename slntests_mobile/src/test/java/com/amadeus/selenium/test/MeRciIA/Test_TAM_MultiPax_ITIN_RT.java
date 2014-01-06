package com.amadeus.selenium.test.MeRciIA;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.InBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.calendar.CalendarPage;
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
 * TestId :  7352 - ITIN - RT Bussiness Class MultiPAX DirectFlight Booking with Flexi Dates
 */
public class Test_TAM_MultiPax_ITIN_RT extends SeleniumSEPTestAdvanced{
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
		driver.manage().deleteAllCookies();
		HelperWelcome.openWelcomePage();
		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		// HomePage homePage = welcomepage.changeLocalPreferences("Iceland", "GB" , false);
		TAMSearchPage searchPage= (TAMSearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.TAM);
		searchPage.fillSearchPage();
		searchPage.clickSearchButton();

		CalendarPage calendar=PageFactory.getPageObject(CalendarPage.class);
//		calendar.validateCalendarPage();
		calendar.VerifyMarksForDateCombination();
//		calendar.ValidateMatrixFormatDate();
		calendar.ClickFlightForDateCombination();

		OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
		outBoundAvailPage.selectDirectFlight();

		/*InBoundAvailPage inBoundAvailPage = PageFactory.getPageObject(InBoundAvailPage.class);
		inBoundAvailPage.selectDirectFlight();*/

		FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
		fareReviewPage.validateFareReviewPage();
		TAMPaxInfoPage paxInfoPage = (TAMPaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.TAM);
		paxInfoPage.fillPaxInfo();
		paxInfoPage.fillContactInfo("Mobile", "", homePage.getValue("MobileNo"), homePage.getValue("Passenger_EmailId"), homePage.getValue("CountryCode"), homePage.getValue("SMSNo"));
		TAMPaymentPage paymentPage =  (TAMPaymentPage)paxInfoPage.continuePayment(Customer.TAM);
		paymentPage.fillPaymentDetail(homePage.getValue("ADDRESS_1"),homePage.getValue("ADDRESS_2"),homePage.getValue("CITY_PAYMENT"),
				homePage.getValue("STATE_PAYMENT"),homePage.getValue("ZIPCODE_PAYMENT"),homePage.getValue("COUNTRY_PAYMENT"));
		paymentPage.fillSocialSecurityCardInfo();
		paymentPage.clickContinue();
		ConfirmationPage confirmationPage = PageFactory.getPageObject(ConfirmationPage.class);
		confirmationPage.validateConfirmationPage();
		String failedSteps = this.getFailedSteps();
		if (failedSteps != null && failedSteps.length() > 0) {
			reporter.fail("Validation(s) failed");
		}
	}
}