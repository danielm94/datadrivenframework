package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.FirefoxCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxDriverBuilder implements DriverBuilder {
    @Override
    public WebDriver createDriverInstance() {
        FirefoxCapabilities capabilities = new FirefoxCapabilities();
        FirefoxOptions options = capabilities.getOptions();
        String operatingSystemString = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "platform");
        OperatingSystem operatingSystem = OperatingSystem.valueOf(operatingSystemString);
        WebDriverManager.firefoxdriver()
                        .operatingSystem(operatingSystem)
                        .setup();
        return new FirefoxDriver(options);
    }
}
