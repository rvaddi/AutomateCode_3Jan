package com.amadeus.selenium.test.MeRciIA;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.menu.customers.SQMobileMenu;
import com.amadeus.selenium.sqmobile.page.availabililty.InBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.availabililty.OutBoundAvailPage;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.TAMPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyFlightsPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyTripsPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.TAMSearchPage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * A sample test for Booking Flow
 * @author rvaddi
 * Test ID : 5063- OWD-RT with Stopover Operating Carrier display in Booking flow
 */
public class Test_TAM_Parameterised_Booking_Flow_RT extends SeleniumSEPTestAdvanced{
	@Override
	public void localSetUp() throws Exception {
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
		WelcomePage welcomepage = HelperWelcome.openWelcomePage();
		WaitUtils.wait(3);
		welcomepage.selectChangeLanguage();
		welcomepage.changeLocalPreferences("Iceland", "GB" , false);

		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		homePage.sqMobileMenu.clickMyTrips();
		WaitUtils.wait(5);WaitUtils.wait(5);WaitUtils.wait(5);

		MyTripsPage myTripsPage = PageFactory.getPageObject(MyTripsPage.class);
		myTripsPage.actionRetrieveBookingReference(welcomepage.getValue("LastName"), welcomepage.getValue("Book RefNo"), true);


		MyFlightsPage myFlightsPage = PageFactory.getPageObject(MyFlightsPage.class);
		myFlightsPage.clickMyMealPrefereance();
		WaitUtils.wait(5);WaitUtils.wait(5);WaitUtils.wait(5);WaitUtils.wait(5);


		String failedSteps = this.getFailedSteps();
		if (failedSteps != null && failedSteps.length() > 0) {
			reporter.fail("Validation(s) failed");
		}
	}
}