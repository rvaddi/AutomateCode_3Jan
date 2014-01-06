package com.amadeus.selenium.sqmobile.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.amadeus.selenium.sqmobile.test.Test_ELAL_Sample_Booking_Flow_OW;
import com.amadeus.selenium.sqmobile.test.Test_ELAL_Sample_Booking_Flow_RT;


@RunWith(Suite.class)

//** ALL AB
@SuiteClasses(value = {
    Test_ELAL_Sample_Booking_Flow_OW.class
    ,Test_ELAL_Sample_Booking_Flow_RT.class

})
public class SanitySuite_ELAL {
  // Suite to valid the basic functional tests
}