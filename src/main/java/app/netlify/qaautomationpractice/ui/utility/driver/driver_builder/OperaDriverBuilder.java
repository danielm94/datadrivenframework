package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.OperaCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public final class OperaDriverBuilder implements DriverBuilder {
    @Override
    public WebDriver createDriverInstance() {
        OperaCapabilities capabilities = new OperaCapabilities();
        OperaOptions options = capabilities.getOptions();
        String operatingSystemString = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "platform");
        OperatingSystem operatingSystem = OperatingSystem.valueOf(operatingSystemString);
        WebDriverManager.operadriver()
                        .operatingSystem(operatingSystem)
                        .setup();
        return new OperaDriver(options);
    }
}
