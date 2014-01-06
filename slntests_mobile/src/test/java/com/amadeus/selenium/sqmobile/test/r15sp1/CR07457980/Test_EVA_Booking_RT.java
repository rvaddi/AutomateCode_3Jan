package com.amadeus.selenium.sqmobile.test.r15sp1.CR07457980;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.page.availabililty.ITINAvailPage;
import com.amadeus.selenium.sqmobile.page.review.FareReviewPage;
import com.amadeus.selenium.sqmobile.page.search.customers.EVASearchPage;

public class Test_EVA_Booking_RT extends SeleniumSEPTestAdvanced{


	@Override
	public void localSetUp(){
		setDriverClass(FirefoxDriver.class);
	}


	@Before
	public void resizeBrowser(){
		getDriverInstance().manage().window().setSize(new Dimension(600,800));
	}

	@Test
	public void scenario() throws Exception{

//		openPage("file://///nceetvdev30/qad/SeleniumTests/TestData/SQMobile/TAM.html");
//		openPage("file:\\\\D:\\Work\\Docs\\CR07457980.html");
		openPage("file://///nceetvdev30/qad/SeleniumTests/TestData/SQMobile/CR07457980.html");
		
		// Click on submit button
		driver.findElement(By.className("submitButton")).click();

		System.out.println(driver.getWindowHandle());
		Set<String> windowHandles = driver.getWindowHandles();

		switchToBookingPage(windowHandles);

		EVASearchPage searchPage= PageFactory.getPageObject(EVASearchPage.class);
		searchPage.fillSearchPage();
		searchPage.clickSearchButton();

		ITINAvailPage itinAvailPage = PageFactory.getPageObject(ITINAvailPage.class);
		itinAvailPage.validatePage();
		itinAvailPage.selectDirectOutBoundDirectInBoundFlight();
		
		FareReviewPage fareReviewPage = PageFactory.getPageObject(FareReviewPage.class);
	    fareReviewPage.validateFareReviewPage();
	    
	}


	private void switchToBookingPage(Set<String> windowHandles) {
		
		for (String string : windowHandles) {
			driver.switchTo().window(string);
			String pageTitle = driver.getTitle();
			if(pageTitle.contains("Book")){
				break;
			}
		}
	}
}
