package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.IECapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public final class IEDriverBuilder implements DriverBuilder {
    @Override
    public WebDriver createDriverInstance() {
        IECapabilities capabilities = new IECapabilities();
        InternetExplorerOptions options = capabilities.getOptions();
        WebDriverManager.iedriver()
                        .setup();
        return new InternetExplorerDriver(options);
    }
}

