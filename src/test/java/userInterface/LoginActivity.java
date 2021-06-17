package userInterface;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userInterface.core.BaseScreen;

public class LoginActivity extends BaseScreen {

    public LoginActivity(AndroidDriver<WebElement> driver) {
        super(driver);
        waitForActivity("ar.com.redlink.login.LoginActivity");
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ar.com.redlink.custom.linkTesting:id/username")
    private AndroidElement txtUsuario;

    public void ingresarTexto(String username) {
        txtUsuario.sendKeys(username);
    }
}
