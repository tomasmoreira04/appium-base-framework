package testCases.testSupport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import utils.CommandUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public AndroidDriver<WebElement> driver;

    @BeforeMethod
    public AndroidDriver<WebElement> setup() throws IOException {
        String adbName = getAdbName();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", getPlatformVersion(adbName));
        caps.setCapability("deviceName", getDeviceName(adbName));
        caps.setCapability("noReset", true);
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "C:\\Proyectos\\appium-base-framework\\app\\hbCustom.1.0.1.25.2 (Testing).apk");
//        caps.setCapability("appPackage", "ar.com.redlink.custom.linkTesting");
//        caps.setCapability("appActivity", "ar.com.redlink.login.LoginActivity");
        caps.setCapability("uiautomator2ServerInstallTimeout", 200000);
        caps.setCapability("gpsEnabled", true);
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static String getDeviceName(String adbName) throws IOException {
        final String[] adbShellCommands = {"adb", "-s", adbName, "shell", "getprop", "ro.product.model"};
        return CommandUtils.execute(adbShellCommands, line -> !line.isEmpty());
    }

    private static String getAdbName() throws IOException {
        final String[] adbDevicesCommands = {"adb", "devices"};
        String adbName = CommandUtils.execute(adbDevicesCommands, line -> line.contains("\tdevice"));
        if (adbName != null)
            return adbName.replace("\tdevice", "");
        throw new ExceptionInInitializerError("No se pudo obtener los dispositivos desde ADB, abortando");
    }

    private static String getPlatformVersion(String adbName) throws IOException {
        final String[] commands = {"adb", "-s", adbName, "shell", "getprop", "ro.build.version.release"};
        return CommandUtils.execute(commands, line -> !line.isEmpty());
    }

    public static void main(String[] args) throws IOException {
        String adbName = getAdbName();
        System.out.println(getPlatformVersion(adbName));
        System.out.println(getDeviceName(adbName));
    }
}
