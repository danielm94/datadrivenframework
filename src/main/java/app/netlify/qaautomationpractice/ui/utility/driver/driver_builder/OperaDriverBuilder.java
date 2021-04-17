package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.OperaCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public final class OperaDriverBuilder implements DriverBuilder {
    @Override
    public WebDriver createDriverInstance() {
        OperaCapabilities capabilities = new OperaCapabilities();
        OperaOptions options = capabilities.getOptions();
        WebDriverManager.operadriver()
                        .setup();
        return new OperaDriver(options);
    }
}
