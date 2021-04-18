package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.ui.utility.driver.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
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
        String operatingSystemString = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "platform");
        OperatingSystem operatingSystem = OperatingSystem.valueOf(operatingSystemString);
        WebDriverManager.seleniumServerStandalone()
                        .operatingSystem(operatingSystem)
                        .setup();
        return new RemoteWebDriver(gridURL, capabilities);
    }
}
