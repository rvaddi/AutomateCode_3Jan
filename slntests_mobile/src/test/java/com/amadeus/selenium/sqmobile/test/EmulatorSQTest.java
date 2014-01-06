package com.amadeus.selenium.sqmobile.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.component.Const;

public class EmulatorSQTest extends SeleniumSEPTest{

/*  @Override
  public FirefoxProfile localGetFirefoxProfile() throws Exception{
    FirefoxProfile EmulatorFirefoxProfile = super.localGetFirefoxProfile();
    EmulatorFirefoxProfile.setPreference("network.automatic-ntlm-auth.trusted-uris","community.amadeus.com");
    return EmulatorFirefoxProfile;
  }*/

  //@Test

/*  public AndroidDriver setUpAndroid(AndroidDriver driver) throws Exception{
    System.out.println("Initialize and reset Android Driver!");

    AndroidDriver driver2 = new AndroidDriver();
    driver2.close();
    driver2.quit();
    try { Thread.sleep(5000); } catch (InterruptedException e) {}
    driver = new AndroidDriver();  // And now use this to visit
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

    try {driver = new AndroidDriver(caps); } catch (Exception e){ e.printStackTrace();}
    //ImageUtils.getScreenRegion(Const.DEVICE);
    return driver;
  }*/


  public void initiateEmulator() throws Exception{

    Process p;
    Boolean vb = false;
    try {
      p = Runtime.getRuntime().exec("tasklist");
      BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      while (((line = reader.readLine()) != null) && (vb==false) )
      {
        if ((line.contains("emulator.exe")) || (line.contains("emulator-arm.exe"))) {
          System.out.println("Emulator is already running!");
          reporter.report("Emulator","Emulator is already running!");
          vb = true;
        }
        System.out.println(line);
      }
      if (vb == false) {

        //p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.EMULATOR_DIR +"emulator.exe -avd "+ Const.AVD +" -no-audio -no-boot-anim -scale .8 &",null,new File(Const.DEVICES_DIR+".android\\avd"));
        p = Runtime.getRuntime().exec(
            "cmd.exe /c start cmd.exe /K " + Const.EMULATOR_DIR + "emulator.exe -avd " + Const.AVD + " -noskin &");
        System.out.println(Const.EMULATOR_DIR + "emulator.exe -avd " + Const.AVD + " -noskin &");
        reporter.report("Emulator","cmd.exe /K "+ Const.EMULATOR_DIR +"emulator.exe -avd "+ Const.AVD +" &");
        Thread.sleep(230000);

        // Clicking Menu Button
        p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 82");
        reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 82");
        Thread.sleep(6000);

        // Install Application

        p = Runtime.getRuntime().exec(
            "cmd.exe /K " + Const.ADB_DIR + "adb -s emulator-5554 -e install -r " + Const.ADB_DIR +
                "android-server-2.21.0.apk");
        reporter.report("Emulator", "cmd.exe /K " + Const.ADB_DIR + "adb -s emulator-5554 -e install -r " +
            Const.ADB_DIR + "android-server-2.21.0.apk");
        Thread.sleep(59000);

        // Starting WebDriver
        p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true");
        reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true");
        Thread.sleep(5000);

        // Forwarding the traffic from the Host Machine to the Emulator
        p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 forward tcp:8080 tcp:8080");
        reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 forward tcp:8080 tcp:8080");
        Thread.sleep(3000);
      }
      else{

        // Clicking Menu Button
        p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 82");
        reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 82");
        Thread.sleep(6000);

        // Clicking Home Button
        p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 3");
        reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 3");
        Thread.sleep(6000);

        p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true");
        reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true");
        Thread.sleep(8000);
        p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 forward tcp:8080 tcp:8080");
        reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 forward tcp:8080 tcp:8080");
        Thread.sleep(2000);
      }
    }
    catch (IOException e) {
       e.printStackTrace();
    }
  }
/*  Process p;
  Boolean vb = false;
  try {
    p = Runtime.getRuntime().exec("tasklist");
    BufferedReader reader = new BufferedReader(new InputStreamReader(    p.getInputStream()));
    String line;
    while (((line = reader.readLine()) != null) && (vb==false) )
    {
      if ((line.contains("emulator.exe")) || (line.contains("emulator-arm.exe"))) {
        System.out.println("Emulator is already running!");
        reporter.report("Emulator","Emulator is already running!");
        vb = true;
        }
      System.out.println(line);
    }
    if (vb == false) {
      //p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.EMULATOR_DIR +"emulator.exe -avd "+ Const.AVD +" -no-audio -no-boot-anim -scale .8 &",null,new File(Const.DEVICES_DIR+".android\\avd"));
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.EMULATOR_DIR +"emulator.exe -avd "+ Const.AVD +" &");
      System.out.println("cmd.exe /K "+ Const.EMULATOR_DIR +"emulator.exe -avd "+ Const.AVD +" -no-audio -no-boot-anim -scale .8 &");
      Thread.sleep(45000);
      reporter.report("Emulator","cmd.exe /K "+ Const.EMULATOR_DIR +"emulator.exe -avd "+ Const.AVD +" -no-audio -no-boot-anim -scale .8 &");
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb kill-server");
      reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb kill-server");
      System.out.println(Const.ADB_DIR +"adb kill-server");
      Thread.sleep(1000);
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb start-server");
      reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb start-server");
      Thread.sleep(10000);
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 82");
      reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 82");
      System.out.println(Const.ADB_DIR +"adb shell input keyevent 82");
      Thread.sleep(5000);
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb " + "shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true");
      reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb " + "shell am start -a android.intent.action.MAIN -n org.openqa.selenium.android.app/.MainActivity -e debug true");
      Thread.sleep(10000);
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 forward tcp:8080 tcp:8080");
      reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 forward tcp:8080 tcp:8080");
      Thread.sleep(1000);
    }
    else{
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb kill-server");
      Thread.sleep(1000);
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb start-server");
      Thread.sleep(10000);
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb shell input keyevent 82");
      Thread.sleep(10000);
      Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb shell am start -n org.openqa.selenium.android.app/org.openqa.selenium.android.app.MainActivity");
      Thread.sleep(5000);
      p = Runtime.getRuntime().exec("cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 forward tcp:8080 tcp:8080");
      reporter.report("Emulator","cmd.exe /K "+ Const.ADB_DIR +"adb -s emulator-5554 forward tcp:8080 tcp:8080");
      Thread.sleep(1000);
    }
  }
  catch (IOException e) {
    e.printStackTrace();
  }
}*/
}
