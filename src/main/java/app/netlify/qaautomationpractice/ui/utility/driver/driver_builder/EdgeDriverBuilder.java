package app.netlify.qaautomationpractice.ui.utility.driver.driver_builder;

import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.EdgeCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class EdgeDriverBuilder implements DriverBuilder {
    @Override
    public WebDriver createDriverInstance() {
        EdgeCapabilities capabilities = new EdgeCapabilities();
        EdgeOptions options = capabilities.getOptions();
        WebDriverManager.edgedriver()
                        .setup();
        return new EdgeDriver(options);
    }
}
