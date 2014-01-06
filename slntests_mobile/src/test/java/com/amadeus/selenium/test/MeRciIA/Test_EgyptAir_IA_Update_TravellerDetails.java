package com.amadeus.selenium.test.MeRciIA;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.test.SeleniumSQTest;



/**
 * SELECT MEAL PREFERENCE FOR MULTI PAX
 * @author RVADDI
 * VERSION :: 1.0
 * TEST ID :: 5459
 */
public class Test_EgyptAir_IA_Update_TravellerDetails extends SeleniumSQTest{
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
	public void updateTravellerDetailsTest() throws Exception {
		driver.manage().deleteAllCookies();
		HelperWelcome.openWelcomePage();
		if (!getDriverInstance().getCurrentUrl().contains(getBaseUrl())) {
			HelperWelcome.openWelcomePage();
		}
		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		homePage.validateHomePage();
		homePage.fillInfo(homePage.getValue("LastName"),homePage.getValue("Book RefNo"),false);

		reporter.reportPassed("Pass", "HomePage Passed");

		/* URL using :http://nceetvqanlb24.dev.amadeus.net/plnext/mobile4MS/MPNRValidate.action?SITE=BFHOBFHO&LANGUAGE=GB
		 * 		Getting below error after click on 'GetTrip'
		 *   Error : request could not be processed because the system is already processing a request from you.
		 *    If you were trying to confirm a booking, please review your trip list to check whether your trip has been confirmed.
		 *
		 *     */

		 String failedSteps = this.getFailedSteps();
		    if(failedSteps != null && failedSteps.length() > 0) {
		      reporter.fail("Validation(s) failed");
		    }

	}
}
