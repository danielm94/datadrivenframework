package app.netlify.qaautomationpractice.utility.driver;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverManagerTypeGetter {
    public static DriverManagerType getDriverManagerType(String browser) {
        browser = browser.toLowerCase();
        if (browser.contains("firefox")) {
            return DriverManagerType.FIREFOX;
        } else if (browser.contains("chrome")) {
            return DriverManagerType.CHROME;
        } else if (browser.contains("edge")) {
            return DriverManagerType.EDGE;
        } else if (browser.contains("safari")) {
            return DriverManagerType.SAFARI;
        } else if (browser.contains("chromium")) {
            return DriverManagerType.CHROMIUM;
        } else if (browser.contains("explorer")) {
            return DriverManagerType.IEXPLORER;
        } else if (browser.contains("opera")) {
            return DriverManagerType.OPERA;
        } else if (browser.contains("phantom")) {
            return DriverManagerType.PHANTOMJS;
        } else {
            return null;
        }
    }
}
