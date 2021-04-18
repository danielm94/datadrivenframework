package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.IECapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public final class IEDriverBuilder implements DriverBuilder {
    @Override
    public WebDriver createDriverInstance() {
        IECapabilities capabilities = new IECapabilities();
        InternetExplorerOptions options = capabilities.getOptions();

        //I imagine it's impossible to run IE nowadays on anything other than Windows but hey, who knows.
        String operatingSystemString = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "platform");
        OperatingSystem operatingSystem = OperatingSystem.valueOf(operatingSystemString);
        WebDriverManager.iedriver()
                        .operatingSystem(operatingSystem)
                        .setup();
        return new InternetExplorerDriver(options);
    }
}

