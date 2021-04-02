package app.netlify.qaautomationpractice.utility.driver;

import app.netlify.qaautomationpractice.utility.data_io.PropertyReader;
import app.netlify.qaautomationpractice.utility.data_io.PropertyFile;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class OptionsManager {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(PropertyReader
                .getFileAsList(PropertyFile.CHROME_PROPERTIES));
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        options.setHeadless(Boolean
                .parseBoolean(PropertyReader
                        .getProperty(PropertyFile.FIREFOX_PROPERTIES, "headless")));
        firefoxProfile.setAcceptUntrustedCertificates(Boolean
                .parseBoolean(PropertyReader
                        .getProperty(PropertyFile.FIREFOX_PROPERTIES, "AcceptUntrustedCertificates")));
        firefoxProfile.setAssumeUntrustedCertificateIssuer(Boolean
                .parseBoolean(PropertyReader
                        .getProperty(PropertyFile.FIREFOX_PROPERTIES, "AssumeUntrustedCertificateIssuer")));
        options.setProfile(firefoxProfile);
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(PropertyReader
                .getFileAsList(PropertyFile.EDGE_PROPERTIES));
        return options;
    }
}
