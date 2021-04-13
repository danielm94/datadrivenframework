package app.netlify.qaautomationpractice.ui.utility.driver;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.BrowserPropertyFile;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OptionsManager {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(PropertyReader
                .getFileAsList(BrowserPropertyFile.CHROME_PROPERTIES));
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        options.setHeadless(Boolean
                .parseBoolean(PropertyReader
                        .getProperty(BrowserPropertyFile.FIREFOX_PROPERTIES, "headless")));
        firefoxProfile.setAcceptUntrustedCertificates(Boolean
                .parseBoolean(PropertyReader
                        .getProperty(BrowserPropertyFile.FIREFOX_PROPERTIES, "AcceptUntrustedCertificates")));
        firefoxProfile.setAssumeUntrustedCertificateIssuer(Boolean
                .parseBoolean(PropertyReader
                        .getProperty(BrowserPropertyFile.FIREFOX_PROPERTIES, "AssumeUntrustedCertificateIssuer")));
        options.setProfile(firefoxProfile);
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(PropertyReader
                .getFileAsList(BrowserPropertyFile.EDGE_PROPERTIES));
        return options;
    }

    public static InternetExplorerOptions getIEOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.destructivelyEnsureCleanSession();
        HashMap<String, Object> capabilities = PropertyReader
                .getFileAsMap(BrowserPropertyFile.IEXPLORER_PROPERTIES);
        if (capabilities == null) {
            return null;
        }
        for (Map.Entry<String, Object> entry : capabilities.entrySet()) {
            options.setCapability(entry.getKey(), entry.getValue());
        }
        return options;
    }

    public static OperaOptions getOperaOptions() {
        OperaOptions options = new OperaOptions();
        options.addArguments(PropertyReader
                .getFileAsList(BrowserPropertyFile.OPERA_PROPERTIES));
        return options;
    }

    public static Capabilities getCapabilities(String browser) {
        Capabilities capabilities = new DesiredCapabilities();
        browser = browser.toLowerCase();
        if (browser.contains("firefox")) {
            capabilities = getFirefoxOptions();
        } else if (browser.contains("chrome")) {
            capabilities = getChromeOptions();
        } else if (browser.contains("edge")) {
            capabilities = getEdgeOptions();
        } else if (browser.contains("explorer")) {
            capabilities = getIEOptions();
        } else if (browser.contains("opera")) {
            capabilities = getOperaOptions();
        }
        return capabilities;
    }

    public static Capabilities getCapabilities(DriverManagerType driverManagerType) {
        Capabilities capabilities = new DesiredCapabilities();
        switch (Objects.requireNonNull(driverManagerType)) {
            case CHROME:
                capabilities = getChromeOptions();
                break;
            case FIREFOX:
                capabilities = getFirefoxOptions();
                break;
            case EDGE:
                capabilities = getEdgeOptions();
                break;
            case IEXPLORER:
                capabilities = getIEOptions();
                break;
            case OPERA:
                capabilities = getOperaOptions();
                break;
        }
        return capabilities;
    }
}
