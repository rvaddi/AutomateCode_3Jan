package com.amadeus.selenium.sqmobile.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)

//** ALL AB
@SuiteClasses(value = {
    SanitySuite_AirChina.class,
    SanitySuite_EgyptAir.class,
    SanitySuite_AirCarab.class,
    SanitySuite_ELAL.class,
    SanitySuite_IcelandAir.class,
    SanitySuite_MEA.class,
    SanitySuite_Saudi.class,
    SanitySuite_TAM.class
})
public class SanitySuite {
  // Suite to valid the basic functional tests
}