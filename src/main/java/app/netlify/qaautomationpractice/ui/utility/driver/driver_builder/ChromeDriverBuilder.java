package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.ChromeCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeDriverBuilder implements DriverBuilder {
    @Override
    public ChromeDriver createDriverInstance() {
        ChromeCapabilities capabilities = new ChromeCapabilities();
        ChromeOptions options = capabilities.getOptions();
        WebDriverManager.chromedriver()
                        .setup();
        return new ChromeDriver(options);
    }

}
