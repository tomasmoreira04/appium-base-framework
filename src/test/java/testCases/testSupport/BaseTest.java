package testCases.testSupport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public AppiumDriver driver;

    public AppiumDriver setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        return new AppiumDriver(new URL(""), caps);
    }
}
