package userInterface.core;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Clock;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.SECONDS;

public class BaseScreen {
    public AndroidDriver<WebElement> driver;
    private final WebDriverWait wait;

    public BaseScreen(AndroidDriver<WebElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    protected void waitForActivity(String activityName) {
        wait.until(d -> driver.currentActivity().equals(activityName));
    }

    protected String getSource() {
        return driver.getPageSource();
    }

    protected Location getGeolocation() {
        return driver.location();
    }

    protected void setGeolocation(Location location) {
        driver.setLocation(location);
    }

    protected String getCurrentActivity() {
        return driver.currentActivity();
    }

    protected void toggleWifi() {
        driver.toggleWifi();
    }

    protected ScreenOrientation getOrientation() {
        return driver.getOrientation();
    }

    protected void setOrientation(ScreenOrientation orientation) {
        driver.rotate(orientation);
    }

    protected void lockDevice() {
        driver.lockDevice();
    }

    protected void lockDevice(int timeout) {
        driver.lockDevice(Duration.of(timeout, SECONDS));
    }

    protected void unLockDevice() {
        driver.unlockDevice();
    }

    protected boolean isDeviceLocked() {
        return driver.isDeviceLocked();
    }

    protected void pressAndroidKey(AndroidKey key) {
        driver.pressKey(new KeyEvent(key));
    }

    protected void pressAndHoldAndroidKey(AndroidKey key) {
        driver.longPressKey(new KeyEvent(key));
    }

    protected void hideKeyboard() {
        driver.hideKeyboard();
    }

    protected boolean isKeyboardShown() {
        return driver.isKeyboardShown();
    }

    protected AndroidTouchAction tSingleTap(AndroidElement element) {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        System.out.println(element.getCenter());
        System.out.println(driver.manage().window().getSize());
        return action.tap(TapOptions.tapOptions().withPosition(PointOption.point(element.getCenter())).withElement(ElementOption.element(element))).perform();
    }

    protected AndroidTouchAction tDoubleTap(AndroidElement element) {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        return action.tap(TapOptions.tapOptions().withTapsCount(2).withElement(ElementOption.element(element))).perform();
    }

    protected MultiTouchAction tPinchOpen(AndroidElement element) {
        MultiTouchAction multiAction = new MultiTouchAction(driver);
        AndroidTouchAction finger1 = new AndroidTouchAction(driver);
        AndroidTouchAction finger2 = new AndroidTouchAction(driver);
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        finger1.press(PointOption.point(x-200, y+200))
                .waitAction(WaitOptions.waitOptions(Duration.of(500, MILLIS)))
                .moveTo(PointOption.point(x - 500, y+500))
                .waitAction(WaitOptions.waitOptions(Duration.of(500, MILLIS)))
                .release();
        finger2.press(PointOption.point(x + 200, y - 200))
                .moveTo(PointOption.point(x + 500, y - 500))
                .release();
        multiAction.add(finger1);
        multiAction.add(finger2);
        multiAction.perform();
        return multiAction;
    }

    protected AndroidTouchAction tSwipe(AndroidElement element, SwipeTo swipe) {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        AndroidTouchAction partial = action
                .press(PointOption.point(element.getLocation()))
                .waitAction(WaitOptions.waitOptions(Duration.of(2, SECONDS)));
        switch (swipe) {
            case LEFT:
                partial = partial
                        .moveTo(PointOption.point(x - 500, y));
            case RIGHT:
                partial = partial
                        .moveTo(PointOption.point(x + 500, y));
        }
        partial.release().perform();
        return partial;
    }

    protected AndroidTouchAction tScrollScreen(ScrollTo scroll) {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        Dimension dimension = driver.manage().window().getSize();
        int x = dimension.getWidth();
        int y = dimension.getHeight();
        AndroidTouchAction partial = action
                .press(PointOption.point(x / 2, y / 2))
                .waitAction(WaitOptions.waitOptions(Duration.of(2, SECONDS)));
        switch (scroll) {
            case UP:
                partial = partial
                        .moveTo(PointOption.point(x / 2, y / 2 - 500));
            case DOWN:
                partial
                        .moveTo(PointOption.point(x / 2, y / 2 + 500));
        }
        partial.release().perform();
        return partial;
    }

    protected AndroidTouchAction tScrollElement(AndroidElement element, ScrollTo scroll) {
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        AndroidTouchAction partial = action
                .press(PointOption.point(element.getLocation()))
                .waitAction(WaitOptions.waitOptions(Duration.of(2, SECONDS)));
        switch (scroll) {
            case UP:
                partial = partial
                        .moveTo(PointOption.point(x, y - 500));
            case DOWN:
                partial = partial
                        .moveTo(PointOption.point(x, y + 500));
        }
        partial.release().perform();
        return partial;
    }

//    public AndroidElement scrollUntilElementAppears(AndroidBy by, int timeout) {
//        // TBD
//    }
}
