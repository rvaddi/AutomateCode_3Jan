package com.amadeus.selenium.test.MeRciIA;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.flightstatus.CommonFlightStatusPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
/**
 * Contact us and Flight info in English
 * @author RVADDI
 * VERSION :: 1.0
 * TEST ID :: 5091
 * Tested URL :http://nceetvqanlb24.dev.amadeus.net/plnext/mobile4SVNRE/MWelcome.action?SITE=NREJBERG&LANGUAGE=GB&MT=A#merci-Mindex_A
 * Flight No:  SV1024
 */
public class Test_Saudi_IA_ContactUs_FlightInfo extends SeleniumSEPTestAdvanced {
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
	public void contactUsAndFlightInfoTest() throws Exception {
		driver.manage().deleteAllCookies();
		HelperWelcome.openWelcomePage();
		if (!getDriverInstance().getCurrentUrl().contains(getBaseUrl())) {
			HelperWelcome.openWelcomePage();
		}
		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		homePage.validateHomePage();
		homePage.clickOnFlightInfo();
		CommonFlightStatusPage flightStatusPage=PageFactory.getPageObject(CommonFlightStatusPage.class);
		flightStatusPage.fillFlightStatusInfo(homePage.getValue("FlightNumber"),Integer.parseInt(homePage.getValue("TotalDays")));
		flightStatusPage.actionFlightInfo();
		flightStatusPage.clickBack();
		reporter.reportPassed("Pass", "ContactUs & FlightInfo Passed");
	}
}
