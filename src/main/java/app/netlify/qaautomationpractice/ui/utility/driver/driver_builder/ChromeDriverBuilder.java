package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.ChromeCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeDriverBuilder implements DriverBuilder {
    @Override
    public ChromeDriver createDriverInstance() {
        ChromeCapabilities capabilities = new ChromeCapabilities();
        ChromeOptions options = capabilities.getOptions();
        String operatingSystemString = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "platform");
        OperatingSystem operatingSystem = OperatingSystem.valueOf(operatingSystemString);
        WebDriverManager.chromedriver()
                        .operatingSystem(operatingSystem)
                        .setup();
        return new ChromeDriver(options);
    }

}
