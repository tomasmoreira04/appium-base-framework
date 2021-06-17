package testCases.testSupport;

import org.testng.annotations.Test;
import userInterface.LoginActivity;

public class TestLogin extends BaseTest {
    @Test
    public void verificarSiFunciona() throws InterruptedException {
        LoginActivity login = new LoginActivity(driver);
        login.ingresarTexto("auto01");
        Thread.sleep(5000);
    }
}
