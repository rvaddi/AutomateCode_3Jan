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
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.paxinfo.customers.MEAPaxInfoPage;
import com.amadeus.selenium.sqmobile.page.payment.PaymentPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.MEASearchPage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;
/**
 * A sample test for Booking Flow
 * @author RVADDI
 * Test ID : 6463 - MEA - 01A - Booking flow
 * Test ID : 6464 - MEA - 01B - Booking flow
 */
public class Test_MEA_Booking_Flow extends SeleniumSEPTestAdvanced{
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
		if (!getDriverInstance().getCurrentUrl().contains(getBaseUrl())) {
			HelperWelcome.openWelcomePage();
		}
		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		MEASearchPage searchPage= (MEASearchPage)homePage.sqMobileMenu.actionSearch(com.amadeus.selenium.sqmobile.menu.CommonMenuPage.Customer.MEA);
		searchPage.fillSearchPage();
		searchPage.clickSearchButton();
		searchPage.verifyWarningMessage();
		searchPage.modfiyDate();
		searchPage.clickSearchButton();
		OutBoundAvailPage outBoundAvailPage = PageFactory.getPageObject(OutBoundAvailPage.class);
		outBoundAvailPage.selectDirectFlight();
		InBoundAvailPage inBoundAvailPage = PageFactory.getPageObject(InBoundAvailPage.class);
		inBoundAvailPage.selectDirectFlight();
		FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
		fareReviewPage.validateFareReviewPage();
		MEAPaxInfoPage paxInfoPage = (MEAPaxInfoPage)fareReviewPage.actionContinue(com.amadeus.selenium.sqmobile.page.review.FareReviewPage.Customer.MEA);
		paxInfoPage.fillPaxInfo();
		paxInfoPage.fillContactInfo("Mobile", "", homePage.getValue("MobileNo"), homePage.getValue("Passenger_EmailId"), homePage.getValue("CountryCode"), homePage.getValue("SMSNo"));
		PaymentPage paymentPage = (PaymentPage)paxInfoPage.continuePayment(Customer.MEA);
		/* Having issue with navigation to Verify UI for the purchase page
		 * paymentPage.clickContinue();
    ConfirmationPage confirmationPage = PageFactory.getPageObject(ConfirmationPage.class);
    confirmationPage.validateConfirmationPage();*/
		/*   String failedSteps = this.getOutpuFolder();
   System.out.println("Fail"+failedSteps);
    if (failedSteps != null && failedSteps.length() > 0) {
      reporter.fail("Validation(s) failed");
    }*/
	}
}