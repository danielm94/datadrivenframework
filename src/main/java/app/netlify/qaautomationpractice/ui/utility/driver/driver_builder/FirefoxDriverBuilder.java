package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.FirefoxCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxDriverBuilder implements DriverBuilder {
    @Override
    public WebDriver createDriverInstance() {
        FirefoxCapabilities capabilities = new FirefoxCapabilities();
        FirefoxOptions options = capabilities.getOptions();
        WebDriverManager.firefoxdriver()
                        .setup();
        return new FirefoxDriver(options);
    }
}
