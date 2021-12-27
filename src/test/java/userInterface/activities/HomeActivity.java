package userInterface.activities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import userInterface.core.BaseScreen;

public class HomeActivity extends BaseScreen {
    public HomeActivity(AndroidDriver<WebElement> driver) {
        super(driver);
        waitForActivity("com.instagram.mainactivity.MainActivity");
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "Inicio")
    private AndroidElement btnInicio;

    @AndroidFindBy(accessibility = "Buscar y explorar")
    private AndroidElement btnBuscar;

    @AndroidFindBy(accessibility = "Reels")
    private AndroidElement btnReels;

    @AndroidFindBy(accessibility = "Tienda")
    private AndroidElement btnTienda;

    @AndroidFindBy(accessibility = "Perfil")
    private AndroidElement btnPerfil;

    public void tocarPerfil() {
        tSingleTap(btnPerfil);
    }
}
