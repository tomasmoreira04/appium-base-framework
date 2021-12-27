package testCases;

import org.testng.annotations.Test;
import userInterface.activities.HomeActivity;

public class BasicTest extends BaseTest {
    @Test
    public void trankapalanka() throws InterruptedException {
        HomeActivity home = new HomeActivity(driver);
        home.tocarPerfil();
        Thread.sleep(1000);
    }
}
