package com.amadeus.selenium.test.MeRciIA;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyBookingPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyFlightsPage;
/**
 * SELECT MEAL PREFERENCE FOR MULTI PAX
 * @author RVADDI
 * VERSION :: 1.0
 * TEST ID :: 5460
 *  URL :http://nceetvqanlb24.dev.amadeus.net/plnext/mobile4MSNRE/MWelcome.action?SITE=BFHOBFHO&LANGUAGE=GB&MT=A#merci-Mindex_A
 */
public class Test_EgyptAir_IA_SelectSeat_Multi_Pax extends SeleniumSEPTestAdvanced{
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
	public void selectSeatlMultiPaxTest() throws Exception {
		driver.manage().deleteAllCookies();
		HelperWelcome.openWelcomePage();
		if (!getDriverInstance().getCurrentUrl().contains(getBaseUrl())) {
			HelperWelcome.openWelcomePage();
		}
		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		homePage.validateHomePage();
		homePage.fillInfo(homePage.getValue("LastName"),homePage.getValue("Book RefNo"),false);
		MyBookingPage myBooking=PageFactory.getPageObject(MyBookingPage.class);
		myBooking.clickSelectSeats();
		reporter.reportPassed("Pass", "HomePage Passed");
		String failedSteps = this.getFailedSteps();
		if(failedSteps != null && failedSteps.length() > 0) {
			reporter.fail("Validation(s) failed");
		}
	}
}
