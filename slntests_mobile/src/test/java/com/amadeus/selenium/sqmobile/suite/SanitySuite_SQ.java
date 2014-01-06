package com.amadeus.selenium.sqmobile.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.amadeus.selenium.sqmobile.test.Test_SQMobile_FareDeals_BookingFlow;
import com.amadeus.selenium.sqmobile.test.Test_SQMobile_FareDeals_FT_Cities;
import com.amadeus.selenium.sqmobile.test.Test_SQMobile_FareDeals_FlexibleDates;
import com.amadeus.selenium.sqmobile.test.Test_SQMobile_FareDeals_Pax_Restriction;
import com.amadeus.selenium.sqmobile.test.Test_SQMobile_FareDeals_Stay_Restriction;
import com.amadeus.selenium.sqmobile.test.Test_SQMobile_FareDeals_TravelPeriod;
import com.amadeus.selenium.sqmobile.test.Test_SQMobile_Sample_Booking_Flow_OW;
import com.amadeus.selenium.sqmobile.test.Test_SQMobile_Sample_Booking_Flow_RT;


@RunWith(Suite.class)

//** ALL AB
@SuiteClasses(value = {
     Test_SQMobile_Sample_Booking_Flow_OW.class
    , Test_SQMobile_Sample_Booking_Flow_RT.class
    , Test_SQMobile_FareDeals_FT_Cities.class
    , Test_SQMobile_FareDeals_TravelPeriod.class
    , Test_SQMobile_FareDeals_Pax_Restriction.class
    , Test_SQMobile_FareDeals_Stay_Restriction.class
    , Test_SQMobile_FareDeals_FlexibleDates.class
    , Test_SQMobile_FareDeals_BookingFlow.class
})

public class SanitySuite_SQ {
  // Suite to valid the basic functional tests
}