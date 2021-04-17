package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.ui.utility.driver.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class RemoteDriverBuilder {
    private RemoteDriverBuilder() {
    }

    public static WebDriver createDriverInstance(URL gridURL, DriverType type) {
        Capabilities capabilities = type.getDriverCapabilities()
                                        .getOptions();
        WebDriverManager.seleniumServerStandalone()
                        .setup();
        return new RemoteWebDriver(gridURL, capabilities);
    }
}
