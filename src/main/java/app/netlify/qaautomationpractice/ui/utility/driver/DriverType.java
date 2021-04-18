package app.netlify.qaautomationpractice.ui.utility.driver;

import app.netlify.qaautomationpractice.ui.utility.driver.browser_options.*;
import app.netlify.qaautomationpractice.ui.utility.driver.driver_builder.*;
import org.openqa.selenium.InvalidArgumentException;
/*
Author: Daniel Martins
Email: daniel.d.martins@outlook.com
*/

/**
 * Enum
 */
public enum DriverType {
    CHROME(new ChromeDriverBuilder(), new ChromeCapabilities()),
    FIREFOX(new FirefoxDriverBuilder(), new FirefoxCapabilities()),
    OPERA(new OperaDriverBuilder(), new OperaCapabilities()),
    EDGE(new EdgeDriverBuilder(), new EdgeCapabilities()),
    IEXPLORER(new IEDriverBuilder(), new IECapabilities());

    private final DriverBuilder driverBuilder;
    private final DriverCapabilities driverCapabilities;


    DriverType(DriverBuilder builder, DriverCapabilities driverCapabilities) {
        this.driverBuilder = builder;
        this.driverCapabilities = driverCapabilities;
    }

    public DriverBuilder getDriverBuilder() {
        return driverBuilder;
    }

    public DriverCapabilities getDriverCapabilities() {
        return driverCapabilities;
    }

    public static DriverType parseString(String browser) {
        browser = browser.toLowerCase();
        if (browser.contains("firefox")) {
            return DriverType.FIREFOX;
        } else if (browser.contains("chrome")) {
            return DriverType.CHROME;
        } else if (browser.contains("edge")) {
            return DriverType.EDGE;
        } else if (browser.contains("explorer")) {
            return DriverType.IEXPLORER;
        } else if (browser.contains("opera")) {
            return DriverType.OPERA;
        } else {
            throw new InvalidArgumentException("\"" + browser + "\" is not a supported browser.");
        }
    }
}
