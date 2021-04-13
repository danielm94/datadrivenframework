package app.netlify.qaautomationpractice.ui.utility.driver;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverManagerTypeParser {
    public static DriverManagerType getDriverManagerType(String browser) {
        browser = browser.toLowerCase();
        if (browser.contains("firefox")) {
            return DriverManagerType.FIREFOX;
        } else if (browser.contains("chrome")) {
            return DriverManagerType.CHROME;
        } else if (browser.contains("edge")) {
            return DriverManagerType.EDGE;
        } else if (browser.contains("explorer")) {
            return DriverManagerType.IEXPLORER;
        } else if (browser.contains("opera")) {
            return DriverManagerType.OPERA;
        } else {
            return null;
        }
    }
}
