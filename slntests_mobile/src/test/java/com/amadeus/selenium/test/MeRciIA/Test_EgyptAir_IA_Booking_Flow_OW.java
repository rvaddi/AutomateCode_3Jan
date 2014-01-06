/**
 *
 */
package com.amadeus.selenium.test.MeRciIA;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.test.SeleniumSQTest;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.InBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.EgyptAirPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.SQMobilePaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.PaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.EgyptAirPaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.SearchPage;
import com.amadeus.selenium.sqmobile.test.SeleniumSQTest;


/**
 * Check Mobile Booking for EgyptAir
 * @author RVADDI
 * Version :: 1.0
 * TestId ::  5457 and 5458
 * Port No:: 219
 *
 * URL :: http://nceetvqanlb24.dev.amadeus.net/plnext/mobile4MSNRE/MWelcome.action?SITE=NREJBFHO&LANGUAGE=GB&MT=A#merci-Mindex_A
 *
 */
public class Test_EgyptAir_IA_Booking_Flow_OW extends SeleniumSEPTestAdvanced{
	// Local Setup -- Required only during maintenance
	@Override
	public void localSetUp() throws Exception {
		// comment this once coding is done
		setDriverClass(FirefoxDriver.class);
		setBaseUrl("http://nceetvqanlb24.dev.amadeus.net/");
		setDebugMode(false);
	}

	@Before
	public void resizeBrowser() {
		getDriverInstance().manage().window().setSize(new Dimension(600, 800));
	}


	@Test
	public void bookingOWATest() throws Exception {
		driver.manage().deleteAllCookies();
		HelperWelcome.openWelcomePage();
		if (!getDriverInstance().getCurrentUrl().contains(getBaseUrl())) {
			HelperWelcome.openWelcomePage();
		}
		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		SearchPage searchPage = homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.EGYPTAIR);


		searchPage.validateSearchPageInfo();
		searchPage.fillSearchPages();
	    searchPage.clickSearchButton();
		reporter.report("search", "search Button clicked --raghava");

/*
 * Verify Calendar page
 *
 */
	    OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
	    outBoundAvailPage.selectDirectFlight();

	    reporter.report("Direct Flight", "Direct Flight pick --raghava");

	    InBoundAvailPage inBoundAvailPage = PageFactory.getPageObject(InBoundAvailPage.class);
	    inBoundAvailPage.selectDirectFlight();

	    FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
	    fareReviewPage.validateFareReviewPage();

	    EgyptAirPaxInfoPage paxInfoPage = (EgyptAirPaxInfoPage)fareReviewPage
	        .actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.EGYPTAIR);
	    paxInfoPage.fillPaxInfo();
	    paxInfoPage.fillEgyptContactInfo("4563253652", "email@email.com");

	    EgyptAirPaymentPage paymentPage = (EgyptAirPaymentPage)paxInfoPage.continuePayment("Visa");
	    // paymentPage.fillPaymentDetail("AddressLine-1 ", "Address Line 2", "City", "sa", "123454", "Singapore");

	    ConfirmationPage confirmationPage = paymentPage.clickContinue();
	    confirmationPage.validateConfirmationPage();

	    String failedSteps = this.getFailedSteps();
	    if (failedSteps != null && failedSteps.length() > 0) {
	      reporter.fail("Validation(s) failed");
	    }
	  }
}







